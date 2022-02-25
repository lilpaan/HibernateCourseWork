
package ru.sfedu.autoHelper.dataProvider;

import com.google.gson.Gson;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import ru.sfedu.autoHelper.ConstantsValues;
import ru.sfedu.autoHelper.entity.*;
import ru.sfedu.autoHelper.entity.enums.Status;
import ru.sfedu.autoHelper.entity.enums.TypeOfTransmission;
import ru.sfedu.autoHelper.entity.historyContent.HistoryContent;
import ru.sfedu.autoHelper.entity.xmlWriter.XmlAnyElements;
import ru.sfedu.autoHelper.util.ConfigurationUtil;

import javax.xml.bind.*;
import javax.xml.namespace.QName;
import javax.xml.transform.stream.StreamSource;
import java.io.*;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.concurrent.TimeUnit;



/**
 * Провайдер данных, обрабатывающий .xml формат
 */
public class DataProviderXml implements IDataProvider {
    private static final Logger logger = LogManager.getLogger(DataProviderXml.class);

    public DataProviderXml() {
    }

    /**
     * Метод для сохранения данных в xml-файле
     * @param rootName параметр для передачи наименования группы объектов
     * @param list список, содержащий объекты для сохранения
     * @param xmlDir путь к файлу сохранения
     * @return isSaved - результат сохранения boolean
     */
    public static <T> boolean saveXml(String rootName, List<T> list, String xmlDir) {
        boolean isSaved;
        try {
            Set<Class> types = new HashSet<>();
            types.add(XmlAnyElements.class);
            types.add(list.get(0).getClass());
            // Создание контекста
            JAXBContext context = JAXBContext.newInstance(types.toArray(new Class[0]));
            Marshaller marshaller = context.createMarshaller();
            // Задавание файлу отформатированный вид
            marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);
            // Создание контейнера для элементов
            JAXBElement<XmlAnyElements> jaxbElement = new JAXBElement<>(new QName(rootName), XmlAnyElements.class,
                    new XmlAnyElements(list));
            // Запись в файл
            marshaller.marshal(jaxbElement, new File(xmlDir));
            isSaved = true;
        } catch (JAXBException e) {
            logger.error(e);
            isSaved = false;
        }
        return isSaved;
    }

    /**
     * Чтение объекта из xml-файла
     * @param typeClass передаёт объект, откуда считается файл
     * @param xmlDir параметр для указания пути к xml-файлу
     * @return считанные данные, упакованные в Optional
     */
    public static <T> Optional<List<T>> readXml(Class<T> typeClass, String xmlDir) {
        Optional opList;
        File file = new File(xmlDir);
        // Создание пути к файлу на случай, если их нет
        file.getParentFile().mkdirs();
        if(file.exists()) {
            try {
                // Создание контекста с типом выбранного класса
                JAXBContext jaxbContext = JAXBContext.newInstance(XmlAnyElements.class, typeClass);
                // Инициализация считывателя
                Unmarshaller unmarshaller = jaxbContext.createUnmarshaller();
                XmlAnyElements list = unmarshaller.unmarshal(new StreamSource(new File(xmlDir)),
                        XmlAnyElements.class).getValue();
                // "Оборачивание" в Optional
                opList = Optional.of(list.getItems());
            } catch (JAXBException e) {
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
            } else {
                logger.info(ConstantsValues.SPARE_PART_WILL_NOT_BE_ADDED);
            }
        }
        // Запись данных в xml-файл
        List<Car> cars = new ArrayList<>();
        try {
            // Проверка на существование файла
            if (readXml(Car.class, ConfigurationUtil.getConfigurationEntry(ConstantsValues.CAR_XML)).isPresent()) {
                cars = readXml(Car.class, ConfigurationUtil.getConfigurationEntry(ConstantsValues.CAR_XML)).get();
            }
        } catch (IOException e) {
            logger.error(e);
        }
        cars.add(car);
        try {
            isCarAdded = saveXml(ConstantsValues.CAR_XML_ROOT, cars,
                    ConfigurationUtil.getConfigurationEntry(ConstantsValues.CAR_XML));
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
        if(number < 0){
            return false;
        }
        boolean isAdded;
        try {
            if (typeOfCard) {
                // Создание объекта BusinessCard и запись данных в xml-файл
                infoCard = new BusinessCard(company, description, phoneNumber, address, email, site);
                List<BusinessCard> cards = new ArrayList<>();
                if (readXml(BusinessCard.class,
                        ConfigurationUtil.getConfigurationEntry(ConstantsValues.BUSINESS_CARD_XML)).isPresent()) {
                    cards = readXml(BusinessCard.class,
                            ConfigurationUtil.getConfigurationEntry(ConstantsValues.BUSINESS_CARD_XML)).get();
                }
                cards.add((BusinessCard) infoCard);
                saveXml(ConstantsValues.BUSINESS_CARD_XML_ROOT, cards,
                        ConfigurationUtil.getConfigurationEntry(ConstantsValues.BUSINESS_CARD_XML));
            } else {
                // Создание объекта DiscountCard и запись данных в xml-файл
                infoCard = new DiscountCard(company, description, nfc, number, barcode);
                List<DiscountCard> cards = new ArrayList<>();
                if (readXml(DiscountCard.class,
                        ConfigurationUtil.getConfigurationEntry(ConstantsValues.DISCOUNT_CARD_XML)).isPresent()) {
                    cards = readXml(DiscountCard.class,
                            ConfigurationUtil.getConfigurationEntry(ConstantsValues.DISCOUNT_CARD_XML)).get();
                }
                cards.add((DiscountCard) infoCard);
                saveXml(ConstantsValues.DISCOUNT_CARD_XML_ROOT, cards,
                        ConfigurationUtil.getConfigurationEntry(ConstantsValues.DISCOUNT_CARD_XML));
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
