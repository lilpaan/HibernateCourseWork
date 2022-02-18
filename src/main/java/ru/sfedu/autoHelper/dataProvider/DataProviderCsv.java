package ru.sfedu.autoHelper.dataProvider;

import com.google.gson.Gson;
import com.opencsv.CSVWriter;
import com.opencsv.bean.CsvToBeanBuilder;
import com.opencsv.bean.StatefulBeanToCsv;
import com.opencsv.bean.StatefulBeanToCsvBuilder;
import com.opencsv.exceptions.CsvDataTypeMismatchException;
import com.opencsv.exceptions.CsvRequiredFieldEmptyException;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import ru.sfedu.autoHelper.ConstantsValues;
import ru.sfedu.autoHelper.entity.*;
import ru.sfedu.autoHelper.entity.enums.Status;
import ru.sfedu.autoHelper.entity.enums.TypeOfTransmission;
import ru.sfedu.autoHelper.entity.historyContent.HistoryContent;
import ru.sfedu.autoHelper.util.ConfigurationUtil;

import java.io.*;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.concurrent.TimeUnit;

/**
 * Класс для обеспечения взаимодействия данных с csv-файлами
 */
public class DataProviderCsv implements IDataProvider {
    private static final Logger logger = LogManager.getLogger(DataProviderCsv.class);

    public DataProviderCsv() {
    }

    /**
     * Метод для сохранения данных в csv-файле
     * @param list используется для передачи и дальнейшей записи данных
     * @param csvDir параметр для указания пути к csv-файлу
     * @return boolean-результат сохранения
     */
    public <T> boolean saveCsv (List <T> list, String csvDir) {
        boolean isSaved;
        try {
            // Инициализация записывателя данных
            CSVWriter csvWriter = new CSVWriter(new FileWriter(csvDir));
            // Конвертация из entity в csv-формат
            StatefulBeanToCsv<T> beanToCsv = new StatefulBeanToCsvBuilder<T>(csvWriter).build();
            // Добавление записи в файл
            beanToCsv.write(list);
            csvWriter.close();
            isSaved = true;
        } catch (IOException | CsvDataTypeMismatchException | CsvRequiredFieldEmptyException e) {
            logger.error(e);
            isSaved = false;
        }
        return isSaved;
    }

    /**
     * Чтение объектов из csv-файла
     * @param type передаётся тип обрабатываемого класса
     * @param csvDir параметр для указания пути к csv-файлу
     * @return считанные данные, упакованные в Optional
     */
    public <T>  Optional<List<T>> readCsv(Class<?> type, String csvDir) {
        Optional<List<T>> opList;
        // Инициализация файла
        File file = new File(csvDir);
        // Создание пути к файлу на случай, если их нет
        file.getParentFile().mkdirs();
        if(file.exists()) {
            try {
                // инициализация считывателя
                FileReader reader = new FileReader(csvDir);
                // считывание из csv в entity, который определяется в type
                opList = Optional.of(new CsvToBeanBuilder<T>(reader).withType((Class<? extends T>) type).build().parse());
            } catch (IOException e) {
                logger.error(e);
                opList = Optional.empty();
            }
        } else {
            opList = Optional.empty();
        }
        return opList;
    }

    @Override
    public boolean addNewCar(String model, String maker, float engineVolume, TypeOfTransmission transmission,
                             String color, int productionYear, float fuelAmount, String currentOilBrand,
                             String wheelsType, String insuranceRunOutDate, boolean spareAddRequest, String[] args) {
        boolean isCarAdded;
        // Создание объекта CarProperties
        CarProperties carProperties = new CarProperties();
        // Заполнение CarProperties
        Optional<CarProperties> optionalCarProperties =
                addProperties(fuelAmount, currentOilBrand, wheelsType, insuranceRunOutDate);
        if (optionalCarProperties.isPresent()) {
            carProperties = optionalCarProperties.get();
        }
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
            }
        } else {
            logger.info(ConstantsValues.SPARE_PART_WILL_NOT_BE_ADDED);
        }
        List<Object> cars = new ArrayList<>();
        // Запись данных в csv-файл
        try {
            if (readCsv(Car.class, ConfigurationUtil.getConfigurationEntry(ConstantsValues.CAR_CSV)).isPresent()) {
                cars = readCsv(Car.class, ConfigurationUtil.getConfigurationEntry(ConstantsValues.CAR_CSV)).get();
            }
        } catch (IOException e) {
            logger.error(e);
        }
        cars.add(car);
        try {
            isCarAdded = saveCsv(cars, ConfigurationUtil.getConfigurationEntry(ConstantsValues.CAR_CSV));
        } catch (IOException e) {
            logger.error(e);
            isCarAdded = false;
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
        boolean isAdded;
        if(number < 0){
            return false;
        }
        try {
            if (typeOfCard) {
                // Создание объекта BusinessCard и запись данных в csv-файл
                infoCard = new BusinessCard(company, description, phoneNumber, address, email, site);
                List<Object> cards = new ArrayList<>();
                if (readCsv(BusinessCard.class, ConfigurationUtil.getConfigurationEntry(ConstantsValues.BUSINESS_CARD_CSV)).isPresent()) {
                    cards = readCsv(BusinessCard.class, ConfigurationUtil.getConfigurationEntry(ConstantsValues.BUSINESS_CARD_CSV)).get();
                }
                cards.add(infoCard);
                saveCsv(cards, ConfigurationUtil.getConfigurationEntry(ConstantsValues.BUSINESS_CARD_CSV));
            } else {
                // Создание объекта DiscountCard и запись данных в csv-файл
                infoCard = new DiscountCard(company, description, nfc, number, barcode);
                List<Object> cards = new ArrayList<>();
                if (readCsv(DiscountCard.class, ConfigurationUtil.getConfigurationEntry(ConstantsValues.DISCOUNT_CARD_CSV)).isPresent()) {
                    cards = readCsv(DiscountCard.class, ConfigurationUtil.getConfigurationEntry(ConstantsValues.DISCOUNT_CARD_CSV)).get();
                }
                cards.add(infoCard);
                saveCsv(cards, ConfigurationUtil.getConfigurationEntry(ConstantsValues.DISCOUNT_CARD_CSV));
            }
            // Сохранение истории в MongoDB
            HistoryContent.saveHistoryContent(getClass().getName(), Status.SUCCESS, new Gson().toJson(infoCard));
            isAdded = true;
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
