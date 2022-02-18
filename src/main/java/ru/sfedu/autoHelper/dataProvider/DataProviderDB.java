package ru.sfedu.autoHelper.dataProvider;

import com.google.gson.Gson;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import ru.sfedu.autoHelper.ConstantsValues;
import ru.sfedu.autoHelper.entity.*;
import ru.sfedu.autoHelper.entity.enums.Status;
import ru.sfedu.autoHelper.entity.enums.TypeOfTransmission;
import ru.sfedu.autoHelper.entity.historyContent.HistoryContent;
import ru.sfedu.autoHelper.util.ConfigurationUtil;

import java.io.IOException;
import java.sql.*;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.Date;
import java.util.concurrent.TimeUnit;

/**
 * Класс для обеспечения взаимодействия данных с базой данных
 */
public class DataProviderDB  implements IDataProvider{
    private static final Logger logger = LogManager.getLogger(DataProviderDB.class);
    public DataProviderDB() {
    }

    /**
     * Метод, выполняющий переданный ему запрос
     * @param tableName наименование таблицы, с данными которой необходимо работать
     * @param query структура запроса
     * @return boolean-результат выполнения запроса
     */
    public static boolean doQuery(String tableName, String query) {
        boolean done;
        // Попытка подключения к базе данных
        try(Connection connection
                    = DriverManager.getConnection(ConfigurationUtil.getConfigurationEntry(ConstantsValues.JDBC_ADDRESS),
                ConfigurationUtil.getConfigurationEntry(ConstantsValues.JDBC_USER_NAME
                ), ConfigurationUtil.getConfigurationEntry(ConstantsValues.JDBC_PASSWORD))) {
            Class.forName(ConfigurationUtil.getConfigurationEntry(ConstantsValues.JDBC_DRIVER));
            // Создание удтверждения
            Statement statement = connection.createStatement();
            // Создание таблицы(если такой не существует
            statement.execute(tableName);
            // Выполнение запроса
            statement.execute(query);
            done = true;
        } catch (SQLException | ClassNotFoundException | IOException e) {
            logger.error(e);
            done = false;
        }
        return done;
    }

    @Override
    public boolean addNewCar(String model, String maker, float engineVolume, TypeOfTransmission transmission,
                             String color, int productionYear, float fuelAmount, String currentOilBrand,
                             String wheelsType, String insuranceRunOutDate, boolean spareAddRequest, String[] args) {
        boolean isCarAdded;
        String query;
        // Создание объекта CarProperties
        CarProperties carProperties = new CarProperties();
        // Заполнение CarProperties
        Optional<CarProperties> optionalCarProperties =
                addProperties(fuelAmount, currentOilBrand, wheelsType, insuranceRunOutDate);
        if (optionalCarProperties.isPresent()) {
            carProperties = optionalCarProperties.get();
        }
        // Создание объекта Car
        Car car = new Car(model, maker, engineVolume, transmission, color, productionYear, carProperties);
        logger.info(ConstantsValues.CHECK_FOR_SPARE_PART);
        // Проверка на необходимость заполнения SparePart
        if (spareAddRequest) {
            if(args == null){
                return false;
            }
            logger.info(ConstantsValues.SPARE_PART_WILL_BE_ADDED);
            Optional<Car> optionalSparePart = addSparePart(car, args[0], args[1], args[2]);
            if (optionalSparePart.isPresent()) {
                car = optionalSparePart.get();
            } else {
                logger.info(ConstantsValues.SPARE_PART_WILL_NOT_BE_ADDED);
            }
            // Формирование запроса со SparePart
            query = String.format(ConstantsValues.JDBC_INSERT_CAR, car.getModel(), car.getMaker(),
                    car.getEngineVolume(), car.getTransmission(), car.getColor(), car.getSparePart().getType(),
                    car.getSparePart().getMaker(), car.getSparePart().getInstallationDate(),
                    car.getCarProperties().getFuelAmount().toString(), car.getCarProperties().getCurrentOilBrand(),
                    car.getCarProperties().getWheelsType(), car.getCarProperties().getInsuranceRunOutDate());
            isCarAdded = doQuery(ConstantsValues.JDBC_CREATE_TABLE_CAR, query);
        } else {
            // Формирование запроса без SparePart
            query = String.format(ConstantsValues.JDBC_INSERT_CAR_WITHOUT_SPARE_PART, car.getModel(), car.getMaker(),
                    car.getEngineVolume().toString(), car.getTransmission(), car.getColor(),
                    car.getCarProperties().getFuelAmount().toString(), car.getCarProperties().getCurrentOilBrand(),
                    car.getCarProperties().getWheelsType(), car.getCarProperties().getInsuranceRunOutDate());
            isCarAdded = doQuery(ConstantsValues.JDBC_CREATE_TABLE_CAR_WITHOUT_SPARE_PART, query);
        }
        // Сохранение истории в MongoDB
        HistoryContent.saveHistoryContent(getClass().getName(), Status.SUCCESS, new Gson().toJson(car));
        return isCarAdded;
    }

    @Override
    public Optional<Car> addSparePart(Car car, String type, String maker, String installationDate) {
        if (car == null){
            return Optional.empty();
        }
        // Создание объекта sparePart
        SparePart sparePart = new SparePart(type, maker, installationDate);
        try {
            // Добавление sparePart к car
            car.setSparePart(sparePart);
        } catch (Exception e) {
            logger.error(e);
        }
        return Optional.of(car);
    }

    @Override
    public Optional<CarProperties> addProperties(float fuelAmount, String currentOilBrand, String wheelsType,
                                                 String insuranceRunOutDate) {
        Optional<CarProperties> optionalCarProperties = Optional.empty();
        // Создание объекта carProperties
        CarProperties carProperties = new CarProperties(fuelAmount, currentOilBrand, wheelsType, insuranceRunOutDate);
        Date insuranceRunOutDateInDate;
        // Попытка перевода в формат Date
        try {
            insuranceRunOutDateInDate = new SimpleDateFormat(ConstantsValues.PATTERN_DATE).parse(insuranceRunOutDate);
        } catch (ParseException e) {
            logger.error(ConstantsValues.ERROR_WRONG_DATE_FORMAT);
            return optionalCarProperties;
        }
        Date currentDate = Calendar.getInstance().getTime();
        optionalCarProperties = Optional.of(carProperties);
        // Высчитывание остатка дней до конца страховки
        int restOfDays = (int) TimeUnit.DAYS.convert(insuranceRunOutDateInDate.getTime() - currentDate.getTime(),
                TimeUnit.MILLISECONDS);
        if (restOfDays < ConstantsValues.EXPIRING_INSURANCE){
            warnInsuranceRunOut(restOfDays);
        }
        return optionalCarProperties;
    }

    @Override
    public boolean warnInsuranceRunOut(int restOfDays) {
        boolean isWarned;
        // Проверка на передачу отрицательного значения
        if(restOfDays < 0){
            return false;
        }
        // Предупреждение об окончании страховки с указанием количества дней
        try {
            logger.warn(ConstantsValues.CAR_PROPERTIES_LOW_INSURANCE + restOfDays);
            isWarned = true;
        } catch (Exception e) {
            logger.error(e);
            isWarned = false;
        }
        return isWarned;
    }

    @Override
    public boolean createInfoCard(String company, boolean typeOfCard, String phoneNumber, String address, String email,
                                  String site, boolean nfc, int number, boolean barcode, boolean descriptionRequest,
                                  String[] args) {
        boolean isCardCreated;
        // Создание объекта InfoCard
        InfoCard infoCard = new InfoCard(company);
        try {
            logger.info(ConstantsValues.CHECK_FOR_DESCRIPTION);
            // Проверка на необходимость добавления описания
            if(descriptionRequest) {
                if(args == null) {
                    return false;
                }
                logger.info(ConstantsValues.DESCRIPTION_WILL_BE_ADDED);
                Optional<InfoCard> optionalInfoCard = addDescription(infoCard, args[0]);
                if (optionalInfoCard.isPresent()) {
                    infoCard = optionalInfoCard.get();
                }
            } else {
                logger.info(ConstantsValues.DESCRIPTION_WILL_NOT_BE_ADDED);
            }
            // Задание флагу-результату значения
            isCardCreated = selectType(infoCard.getCompany(), infoCard.getDescription(), typeOfCard, phoneNumber,
                    address, email, site, nfc, number, barcode);
        } catch (Exception e) {
            logger.error(e);
            isCardCreated = false;
        }
        return isCardCreated;
    }

    @Override
    public boolean selectType(String company, String description, boolean typeOfCard, String phoneNumber,
                              String address, String email, String site, boolean nfc, int number, boolean barcode) {
        InfoCard infoCard;
        if(number < 0){
            return false;
        }
        boolean isAdded;
        String query;
        try {
            // Выбор типа инфокарточки
            if (typeOfCard) {
                // Создание объекта BusinessCard и формирование запроса
                infoCard = new BusinessCard(company, description, phoneNumber, address, email, site);
                query = String.format(ConstantsValues.JDBC_INSERT_BUSINESS_CARD, company, description, phoneNumber,
                        address, email, site);
                        isAdded = doQuery(ConstantsValues.JDBC_CREATE_TABLE_BUSINESS_CARD, query);
            } else {
                // Создание объекта DiscountCard и формирование запроса
                infoCard = new DiscountCard(company, description, nfc, number, barcode);
                query = String.format(ConstantsValues.JDBC_INSERT_DISCOUNT_CARD, company, description,
                        nfc, number, barcode);
                isAdded = doQuery(ConstantsValues.JDBC_CREATE_TABLE_DISCOUNT_CAR, query);
            }
            // Сохранение истории в MongoDB
            HistoryContent.saveHistoryContent(getClass().getName(), Status.SUCCESS, new Gson().toJson(infoCard));
        } catch (Exception e) {
            logger.error(e);
            isAdded = false;
        }
        return isAdded;
    }

    @Override
    public Optional<InfoCard> addDescription(InfoCard infoCard, String description) {
        try {
            // Присвоение description объекту InfoCard
            infoCard.setDescription(description);
        } catch (Exception e) {
            logger.error(e);
            return Optional.empty();
        }
        return Optional.of(infoCard);
    }

}
