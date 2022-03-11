package ru.sfedu.autoHelper;

public class ConstantsValues {

    // config files for labs
    public static final String DEFAULT_HBN_CFG = "src/main/resources/hibernate.cfg.xml";
    public static final String LAB1_HBN_CFG = "src/main/java/ru/sfedu/autoHelper/lab1/lab1.hbn_cfg.xml";
    public static final String LAB2_HBN_CFG = "src/main/java/ru/sfedu/autoHelper/lab2/lab2.hbn_cfg.xml";
    public static final String LAB3_HBN_CFG = "src/main/java/ru/sfedu/autoHelper/lab3/lab3.hbn_cfg.xml";
    public static final String LAB4_HBN_CFG = "src/main/java/ru/sfedu/autoHelper/lab4/lab4.hbn_cfg.xml";
    public static final String LAB5_HBN_CFG = "src/main/java/ru/sfedu/autoHelper/lab5/lab5.hbn_cfg.xml";

    // log messages
    public static final String SESSION_IS_OPENED = "session is opened";
    public static final String FILE_WAS_INSERT = "file was insert successfully";
    public static final String SESSION_IS_CLOSED = "close is closed";
    public static final String OBJECT_WAS_DELETED = "object was deleted";
    public static final String OBJECT_WAS_UPDATED = "object was updated";
    public static final String LIST_OBJECT_WAS_READ = "list object was read";
    public static final String OBJECT_WAS_READ = "object was read";
    public static final String TRANSACTION_COMPLETED = "transaction completed";


    // info about DB
    public static final String DB_NAME = "panchenko";

    // SQL-queries for lab 1
    public static final String GET_DATABASE_SIZE = "SELECT table_schema, " +
            "SUM(data_length + index_length) \n " +
            "FROM information_schema.TABLES " +
            "GROUP BY table_schema;";
    public static final String GET_USER_LIST = "SELECT user FROM mysql.user";
    public static final String GET_TABLE_LIST = "SELECT table_name FROM information_schema.tables;";
    public static final String GET_DATA_TYPES = "SELECT table_type FROM information_schema.tables;";

    // sql-queries to lab 2
    public static final String SQL_SELECT_ALL_DATA_FROM_TEST_ENTITY = "FROM TestEntity";

    // sql-queries to lab 5
    public static final String GET_CAR_BY_ID = "SELECT * FROM car_lab5 WHERE id = ";
    public static final String SQL_SELECT_ALL_DATA_FROM_CAR = "FROM car_lab5";
    public static final String SQL_SELECT_STAR_FROM_CAR = "SELECT * FROM car_lab5";


    // Constants-names of columns or tables; for mapping too
    public static final String COLUMN_TEST_ENTITY_ID = "test_entity_id";
    public static final String COLUMN_TEST_ENTITY_NAME = "test_entity_name";
    public static final String COLUMN_TEST_ENTITY_CHECK = "test_entity_check";
    public static final String TEST_ENTITY = "TestEntity";
    public static final String CAR_ID_TITLE = "car_id";
    public static final String SPARE_PART_TABLE_LAB5 = "spare_part_lab5";
    public static final String CAR_TABLE_LAB5 = "car_lab5";
    public static final String USER_TABLE_LAB5 = "user_lab5";
    public static final String CAR_PROPERTIES_TABLE_LAB5 = "car_properties_lab5";
    public static final String INFO_CARD_TABLE_LAB5 = "info_card_lab5";
    public static final String INFO_CARD_USER = "info_card_user";
    public static final String USER_ID = "user_id";

    // lab 4 constants;
    public static final String USER_INFO_CARD_FIRST = "userInfoCardFirst";
    public static final String USER_INFO_CARD_SECOND = "userInfoCardSecond";
    public static final String USER_INFO_CARD_THIRD = "userInfoCardThird";
    public static final String USER_INFO_CARD_FIRST_NEW = "userInfoCardFirstNEW";
    public static final String USER_INFO_CARD_SECOND_NEW = "userInfoCardSecondNEW";
    public static final String USER_INFO_CARD_THIRD_NEW = "userInfoCardThirdNEW";
    public static final String USER_INFO_CARD_FIRST_INFO = "FirstInfo";
    public static final String USER_INFO_CARD_SECOND_INFO = "SecondInfo";
    public static final String USER_INFO_CARD_THIRD_INFO = "ThirdInfo";
    public static final String USER_INFO_CARD_FIRST_NEW_INFO = "FirstInfoNEW";
    public static final String USER_INFO_CARD_SECOND_NEW_INFO = "SecondInfoNEW";
    public static final String USER_INFO_CARD_THIRD_NEW_INFO = "ThirdInfoNEW";
    public static final String INFO_CARD_LIST = "info_card_list";
    public static final String INFO_CARD_KEY_COLUMN = "info_card_key";
    public static final String INFO_CARD_MAP = "info_card_map";
    public static final String INFO_CARD_MAP_COMPONENT = "info_card_map_component";
    public static final String INFO_CARDS = "info_cards";
    public static final String USER_SET_TYPE = "user_set_type";
    public static final String USER_LIST_TYPE = "user_list_type";
    public static final String USER_MAP_COMPONENT_TYPE = "user_map_component_type";
    public static final String USER_MAP_TYPE = "user_map_type";
    public static final String USER_COMPONENT_TYPE = "user_component_type";
    public static final String INFO_CARD_COMPONENT = "info_card_component";
    public static final String INFO_CARD_COMPONENT_DESCRIPTION = "info_card_description";

    // constants uses in lab3
    public static final String INFO_CARD_ID = "info_card_id";
    public static final String INFO_CARD_JOINED_TABLE = "InfoCardJoinedTable";
    public static final String DISCOUNT_CARD_JOINED_TABLE = "DiscountCardJoinedTable";
    public static final String BUSINESS_CARD_JOINED_TABLE = "BusinessCardJoinedTable";
    public static final String INFO_CARD_SINGLE_TABLE = "InfoCardSingleTable";
    public static final String DISCOUNT_CARD_SINGLE_TABLE = "DiscountCardSingleTable";
    public static final String BUSINESS_CARD_SINGLE_TABLE = "BusinessCardSingleTable";
    public static final String DISCOUNT_CARD_MAPPED_SUPERCLASS = "DiscountCardMappedSuperclass";
    public static final String BUSINESS_CARD_MAPPED_SUPERCLASS = "BusinessCardMappedSuperclass";
    public static final String INFO_CARD_TABLE_PER_CLASS = "InfoCardTablePerClass";
    public static final String DISCOUNT_CARD_TABLE_PER_CLASS = "DiscountCardTablePerClass";
    public static final String BUSINESS_CARD_TABLE_PER_CLASS = "BusinessCardTablePerClass";

    // Ключи для environment.properties
    public static final String CAR_CSV = "CAR_CSV";
    public static final String DISCOUNT_CARD_CSV = "DISCOUNT_CARD_CSV";
    public static final String BUSINESS_CARD_CSV = "BUSINESS_CARD_CSV";
    public static final String CAR_XML = "CAR_XML";
    public static final String DISCOUNT_CARD_XML = "DISCOUNT_CARD_XML";
    public static final String BUSINESS_CARD_XML = "BUSINESS_CARD_XML";

    // Константы для обозначения групп элементов в XML-файлах
    public static final String CAR_XML_ROOT = "cars";
    public static final String DISCOUNT_CARD_XML_ROOT = "discountCards";
    public static final String BUSINESS_CARD_XML_ROOT = "businessCards";

    // Группа констант для работы данными объекта Car
    public static final String CAR_MODEL = "model";
    public static final String CAR_MAKER = "maker";
    public static final String CAR_ENGINE_VOLUME = "engine volume";
    public static final String CAR_TRANSMISSION = "transmission";
    public static final String CAR_COLOR = "color";
    public static final String CAR_PRODUCTION_YEAR = "production year";
    public static final String CAR_SPARE_ADD_REQUEST = "spare add request";

    // Константы - элементы enum-файла TypeOfTransmission
    public static final String TYPE_OF_TRANSMISSION_MECHANICAL = "mechanical";
    public static final String TYPE_OF_TRANSMISSION_AUTOMATIC = "automatic";
    public static final String TYPE_OF_TRANSMISSION_ROBOTIC = "robotic";
    public static final String TYPE_OF_TRANSMISSION_VARIABLE = "variable";

    // Константы для заполнения инфологов
    public static final String CHECK_FOR_SPARE_PART = "check for the need to add a spare part...";
    public static final String SPARE_PART_WILL_BE_ADDED = "spare part will be added";
    public static final String SPARE_PART_WILL_NOT_BE_ADDED = "spare part will not be added";
    public static final String CHECK_FOR_DESCRIPTION = "check for the need to add a description...";
    public static final String DESCRIPTION_WILL_BE_ADDED = "description will be added";
    public static final String DESCRIPTION_WILL_NOT_BE_ADDED = "description will not be added";

    // Группа констант для работы данными объекта InfoCard
    public static final String INFO_CARD_COMPANY = "company";
    public static final String INFO_CARD_DESCRIPTION = "description";
    public static final String INFO_CARD_PHONE_NUMBER = "phone number";
    public static final String INFO_CARD_ADDRESS = "address";
    public static final String INFO_CARD_EMAIL = "email";
    public static final String INFO_CARD_SITE = "site";
    public static final String INFO_CARD_NFC = "nfc";
    public static final String INFO_CARD_NUMBER = "number";
    public static final String INFO_CARD_BARCODE = "barcode";
    public static final String INFO_CARD_TYPE_OF_CARD = "type of card";
    public static final String INFO_CARD_DESCRIPTION_REQUEST = "description request";


    // Группа констант для работы данными объекта SparePart
    public static final String SPARE_PART_TYPE = "type";
    public static final String SPARE_PART_INSTALLATION_DATE = "installation date";
    public static final String SPARE_PART_MAKER = "spare part maker";

    // Группа констант для работы данными объекта CarProperties
    public static final String CAR_PROPERTIES_FUEL_AMOUNT = "fuel amount";
    public static final String CAR_PROPERTIES_CURRENT_OIL_BRAND = "current oil brand";
    public static final String CAR_PROPERTIES_WHEELS_TYPE = "wheels type";
    public static final String CAR_PROPERTIES_LOW_INSURANCE = "INSURANCE RUN OUT COMING! DAYS UNTIL END: ";
    public static final String CAR_PROPERTIES_INSURANCE_RUN_OUT_DATE = "insurance run out date";

    // Группа констант для работы данными объекта User
    public static final String USER_INFO_CARD = "info cards";
    public static final String USER_CAR = "user car";

    // Паттерн для даты
    public static final String PATTERN_DATE = "dd/MM/yyyy";

    // Количетство дней - если до окочания страховки меньше, чем эта константа, то вызывается предупреждение
    public static final int EXPIRING_INSURANCE = 30;

    // Константа, задающая имя исполнителя в классе HistoryContent
    public static final String ACTOR_NAME = "system";

    // Группа констант для взаимодействия с MongoDB, включая ключи для environment.properties
    public static final String MONGODB_HOST = "MONGODB_HOST";
    public static final String MONGODB_DATABASE_NAME = "MONGODB_DATABASE_NAME";
    public static final String MONGODB_COLLECTION_NAME = "MONGODB_COLLECTION_NAME";
    public static final String MONGODB_DATE_TEMPLATE = "yyyy/MM/dd";
    public static final String MONGODB_COLLECTION_ALREADY_EXIST = "collection already exist";

    // Константы для обозначения регуляргных выражений, используемых при проверке введённых данных
    public static final String REGEX_CAR_MODEL = "[a-zA-Z0-9_ ]{1,70}";
    public static final String REGEX_CAR_MAKER = "[a-zA-Z_ ]{1,90}";
    public static final String REGEX_CAR_ENGINE_VOLUME = "^([+-]?\\d*\\.?\\d*)$";
    public static final String REGEX_CAR_COLOR = "[a-zA-Z_ ]{1,30}";
    public static final String REGEX_CAR_PRODUCTION_YEAR = "^[+-]?\\d{4}";
    public static final String REGEX_CAR_PROPERTIES_FUEL_AMOUNT = "^([+-]?\\d*\\.?\\d*)$";
    public static final String REGEX_CAR_PROPERTIES_CURRENT_OIL_BRAND = "[a-zA-Z_ ]{1,25}";
    public static final String REGEX_CAR_PROPERTIES_WHEELS_TYPE = "[a-zA-Z0-9_ ]{1,30}";
    public static final String REGEX_DATE = "[0-9]{2}/[0-9]{2}/[0-9]{4}";
    public static final String REGEX_SPARE_PART_TYPE = "[a-zA-Z_ ]{1,100}";
    public static final String REGEX_SPARE_PART_MAKER = "[a-zA-Z_ ]{1,70}";
    public static final String REGEX_INFO_CARD_COMPANY = "[a-zA-Z_ ]{1,100}";
    public static final String REGEX_PHONE_NUMBER = "^((8|\\+7)[\\- ]?)?(\\(?\\d{3}\\)?[\\- ]?)?[\\d\\- ]{7,9}$";
    public static final String REGEX_INFO_CARD_ADDRESS = "[a-zA-Z0-9_ ]{1,200}";
    public static final String REGEX_EMAIL = "^([\\w\\.\\-]+)@([\\w\\-]+)((\\.(\\w){2,3})+)$";
    public static final String REGEX_SITE =
            "[-a-zA-Z0-9@:%._\\+~#=]{1,256}\\.[a-zA-Z0-9()]{1,6}\\b([-a-zA-Z0-9()@:%_\\+.~#?&//=]*)";
    public static final String REGEX_INFO_CARD_NUMBER = "^[+-]?\\d{1,30}";
    public static final String REGEX_INFO_CARD_DESCRIPTION = "[a-zA-Z0-9_ ]{1,1000}";

    // Описание различных возможных ошибок
    public static final String ERROR_NOT_POSITIVE_VALUE = "Enter a positive value of ";
    public static final String ERROR_INVALID_SYMBOLS =
            " variable: data entry error. Please check the correctness of input. Invalid value: ";
    public static final String ERROR_EMPTY_LIST = "Argument list is empty";
    public static final String ERROR_EMPTY_DATA_PROVIDER = "Empty value of data provider";
    public static final String ERROR_METHOD_NOT_INPUTTED = "Method was not inputted";
    public static final String ERROR_VARIABLE_NOT_INPUTTED = " was not inputted";
    public static final String ERROR_VARIABLE_IS_EMPTY = " is empty";
    public static final String ERROR_WRONG_DATA_PROVIDER_INPUT = "Wrong data provider input";
    public static final String ERROR_WRONG_METHOD_NAME = "Wrong method input name";
    public static final String ERROR_WRONG_DATE_FORMAT = "wrong date format";

    // Группа констант для вывода некоторых информационных сообщений
    public static final String INFO_MAYBE_WRONG_REQUEST_SPARE_PART = "Perhaps there is information in spare part";
    public static final String INFO_MAYBE_WRONG_REQUEST_DESCRIPTION = "Perhaps there is information in description";

    // Константа для корректного отображения ошибки ввода метода
    public static final String CHOSEN_METHOD = "chosen method";

    // Константы для обозначения истинного или ложного утверждения
    public static final String TRUE_STATEMENT = "true";
    public static final String FALSE_STATEMENT = "false";

    // Используется при проверке на наполненность строки
    public static final String EMPTY_STRING = "";

    // Константы-варианты выбора провайдера данных
    public static final String CSV = "csv";
    public static final String XML = "xml";
    public static final String DB = "db";

    // Константы-варианты выбора метода из use-case
    public static final String CAR = "car";
    public static final String CARD = "card";
    public static final String DATA_PROVIDER = "data provider";

    // Группа констант для работы с ConfigurationUtil
    public static final String DEFAULT_CONFIG_PATH = "./src/main/resources/environment.properties";
    public static final String PROPERTIES_DIR = "config.path";

    // Группа констант работы JDBC и взаимодействия с MySQL, включая ключи для environment.properties
    public static final String JDBC_ADDRESS = "JDBC_ADDRESS";
    public static final String JDBC_DRIVER = "JDBC_DRIVER";
    public static final String JDBC_USER_NAME = "JDBC_USER_NAME";
    public static final String JDBC_PASSWORD = "JDBC_PASSWORD";
    public static final String JDBC_CREATE_TABLE_CAR = "CREATE TABLE IF NOT EXISTS " +
            "Cars (" +
            "id SERIAL, " +
            "model TEXT, " +
            "maker TEXT, " +
            "engineVolume TEXT, " +
            "transmission TEXT, " +
            "color TEXT, " +
            "sparePartType TEXT, " +
            "sparePartMaker TEXT, " +
            "sparePartInstallationDate TEXT, " +
            "carPropertiesFuelAmount TEXT, " +
            "carPropertiesCurrentOilBrand TEXT, " +
            "carPropertiesWheelsType TEXT, " +
            "carPropertiesInsuranceRunOutDate TEXT);";
    // сначала в
    public static final String JDBC_CREATE_TABLE_CAR_WITHOUT_SPARE_PART = "CREATE TABLE IF NOT EXISTS " +
            "Cars (" +
            "id SERIAL, " +
            "model TEXT, " +
            "maker TEXT, " +
            "engineVolume TEXT, " +
            "transmission TEXT, " +
            "color TEXT, " +
            "carPropertiesFuelAmount TEXT, " +
            "carPropertiesCurrentOilBrand TEXT, " +
            "carPropertiesWheelsType TEXT, " +
            "carPropertiesInsuranceRunOutDate TEXT);";
    public static final String JDBC_CREATE_TABLE_BUSINESS_CARD = "CREATE TABLE IF NOT EXISTS " + "BusinessCards ("
            + "id SERIAL, "
            + "company TEXT, "
            + "description TEXT, "
            + "phoneNumber TEXT, "
            + "address TEXT, "
            + "email TEXT, "
            + "site TEXT);";
    public static final String JDBC_CREATE_TABLE_DISCOUNT_CAR = "CREATE TABLE IF NOT EXISTS " + "DiscountCards ("
            + "id SERIAL, "
            + "company TEXT, "
            + "description TEXT, "
            + "nfc TEXT, "
            + "number INT, "
            + "barcode TEXT);";
    public static final String JDBC_INSERT_CAR = "INSERT INTO Cars(id, model, maker, engineVolume, transmission, " +
            "color, sparePartType, sparePartMaker, sparePartInstallationDate, carPropertiesFuelAmount, " +
            "carPropertiesCurrentOilBrand, carPropertiesWheelsType, carPropertiesInsuranceRunOutDate) " +
            "VALUES (DEFAULT, '%s', '%s', '%s', '%s', '%s', '%s', '%s', '%s', '%s', '%s', '%s', '%s');";
    public static final String JDBC_INSERT_CAR_WITHOUT_SPARE_PART = "INSERT INTO Cars(id, model, maker, engineVolume, " +
            "transmission, color, carPropertiesFuelAmount, carPropertiesCurrentOilBrand, carPropertiesWheelsType, " +
            "carPropertiesInsuranceRunOutDate) VALUES (DEFAULT, '%s', '%s', '%s', '%s', '%s', '%s', " +
            "'%s', '%s', '%s');";
    public static final String JDBC_INSERT_BUSINESS_CARD = "INSERT INTO BusinessCards(id, company, description, " +
            "phoneNumber, address, email, site) VALUES (DEFAULT, '%s', '%s', '%s', '%s', '%s', '%s');";
    public static final String JDBC_INSERT_DISCOUNT_CARD = "INSERT INTO DiscountCards(id, company, description, " +
            "nfc, number, barcode) VALUES (DEFAULT, '%s', '%s', '%s', '%d', '%s');";

    // hello world for different debug situations
    public static final String HELLO_WORLD = "Hello world!";
    public static final String UNCHECKED = "unchecked";

}