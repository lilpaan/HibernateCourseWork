package ru.sfedu.autoHelper.dataProvider;
import ru.sfedu.autoHelper.entity.*;
import ru.sfedu.autoHelper.entity.enums.TypeOfTransmission;

import java.util.Optional;

/**
 * Интерфейс для инициализации методов классов, являющихся провайдерами данных
 */
public interface IDataProvider {
    /**
     * Добавление автомобиля пользователя
     * @param model модель автомобиля
     * @param maker страна-производитель
     * @param engineVolume объём двигателя
     * @param transmission тип трансмиссии, выбраный с помощью enum typeOfTransmission
     * @param color цвет автомобиля
     * @param productionYear год выпуска
     * @param fuelAmount средний расход топлива(заполняется пользователем)
     * @param currentOilBrand бренд автомобильного масла, используемого в настоящее время
     * @param wheelsType тип резины
     * @param insuranceRunOutDate дата истечения срока страховки
     * @param spareAddRequest параметр для проверки необходимости занесения запчасти
     * @param args занесение параметров для запчасти
     * @return флаг isCarAdded - результат добавления
     */
    boolean addNewCar(String model, String maker, float engineVolume, TypeOfTransmission transmission, String color,
                      int productionYear, float fuelAmount, String currentOilBrand, String wheelsType,
                      String insuranceRunOutDate, boolean spareAddRequest, String[] args);

    /**
     * Добавление информации об автомобильной запчасти
     * @param car объект, переданный из addNewCar
     * @param type тип автомобильной запчасти
     * @param maker производитель автомобильной запчасти
     * @param installationDate дата установки детали
     * @return заполненный объект car, передающийся в addNewCar
     */
    Optional<Car> addSparePart(Car car, String type, String maker, String installationDate);

    /**
     * Добавление специфичных характеристик автомобиля
     * @param fuelAmount средний расход топлива
     * @param currentOilBrand используемый бренд автомобильного масла
     * @param wheelsType тип резины
     * @param insuranceRunOutDate дата истечения срока автомобильной страховки
     * @return заполненный объект CarProperties, передающийся в addNewCar
     */
    Optional<CarProperties> addProperties(float fuelAmount, String currentOilBrand, String wheelsType, String insuranceRunOutDate);

    /**
     * Сообщение о приближении окончания срока действия страховки
     * @param restOfDays остаток дней до окончания срока страховки
     * @return isWarned - флаг-результат выполнения
     */
    boolean warnInsuranceRunOut(int restOfDays);

    /**
     * Создание инфокарточки: визитная карточка организации/мастера по ремонту авто
     * или дисконтная карту заправочной станции
     * @param company компания, предоставляющая карту
     * @param typeOfCard тип карты: true-визитная, false-дисконтная
     * @param descriptionRequest флаг для проверки необходимости добавления описания
     * @param phoneNumber номер телефона
     * @param address адрес
     * @param email электронная почта
     * @param site веб-сайт
     * @param nfc для дисконтной карты - проверка на наличие nfc-чипа
     * @param number номер дисконтной карты
     * @param barcode проверка на наличие штрих-кода для дисконтной карты
     * @param args параметр для передачи описания в addDescription
     * @return isCarCreated - флаг для проверки успешности создания
     */
    boolean createInfoCard(String company, boolean typeOfCard, String phoneNumber, String address, String email,
                           String site, boolean nfc, int number, boolean barcode, boolean descriptionRequest,
                           String[] args);

    /**
     * Выбрать тип инфокарточки: Визитная карта СТО или дисконтная карта АЗС, сохранение выбранной карты
     * @param company компания, предоставляющая услуги
     * @param description описание к карте, может отсутствовать
     * @param typeOfCard тип карты: true-визитная, false-дисконтная
     * @param phoneNumber номер телефона
     * @param address адрес
     * @param email электронная почта
     * @param site сайт
     * @param nfc для дисконтной карты - проверка на наличие nfc-чипа
     * @param number номер дисконтной карты
     * @param barcode проверка на наличие штрих-кода для дисконтной карты
     * @return isAdded - флаг для проверки успешного добавления
     */
    boolean selectType(String company, String description, boolean typeOfCard, String phoneNumber, String address,
                       String email, String site, boolean nfc, int number, boolean barcode);

    /**
     * Добавить описание к инфокарточке
     * @param infoCard объект, переданный из createInfoCard для добавления описания
     * @param description описание к инфокарточке
     * @return объект infoCard, передающийся в createInfoCard
     */
    Optional<InfoCard> addDescription(InfoCard infoCard, String description);

}
