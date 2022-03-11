package ru.sfedu.autoHelper.lab5;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.junit.Test;
import ru.sfedu.autoHelper.lab5.dataProvider.IHibernateDataProvider;
import ru.sfedu.autoHelper.lab5.dataProvider.dataProviderHQL;
import ru.sfedu.autoHelper.lab5.entity.Car;
import ru.sfedu.autoHelper.lab5.entity.CarProperties;
import ru.sfedu.autoHelper.lab5.entity.SparePart;

import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

import static org.junit.Assert.assertNotEquals;
import static org.junit.Assert.fail;

/**
 * Класс для исполнения тестов с HQL провайдером данных
 */
public class HQLTest {
    private static final Logger logger = LogManager.getLogger(HQLTest.class);
    IHibernateDataProvider dataProviderHQL = new dataProviderHQL();
    Car car;
    boolean success;


    /**
     * добавление объекта
     * тип: позитивный
     */
    @Test
    public void createPositive() {
        Optional<Car> optionalCar;
        try {
            car = initCar();
        } catch (Exception e) {
            logger.error(e);
        }
        optionalCar = dataProviderHQL.create(car);
        assertNotEquals(Optional.empty(), optionalCar);
    }

    /**
     * чтение объекта по id
     * тип: позитивный
     */
    @Test
    public void readByIdPositive() {

        Optional<Car> optionalCar = dataProviderHQL.create(car);
        if(optionalCar.isPresent()) {
            optionalCar = dataProviderHQL.readById(Car.class, optionalCar.get().getId());
            assertNotEquals( Optional.empty(), optionalCar);
        } else {
            fail();
        }
    }

    /**
     * обновление объекта
     * тип: позитивный
     */
    @Test
    public void updatePositive() {
        Set<SparePart> spareParts = new HashSet<>();
        SparePart sparePart1 = new SparePart(1L, "type1", "maker1", "11/11/2021");
        SparePart sparePart2 = new SparePart(2L, "type2", "maker2", "12/12/2021");
        CarProperties carProperties = new CarProperties(1L, 10, "LukOil", "Winter", "22/12/2022");
        spareParts.add(sparePart1);
        spareParts.add(sparePart2);
        Car car = new Car(1L, spareParts, "carModel", "carMaker", 11, "carColor", 2011, carProperties);
/*        Optional<BusinessCard> optionalCar = dataProviderHibernate.create(businessCardForUpdate);
        Optional<DiscountCard> optionalDiscountCard = dataProviderHibernate.create(discountCardForUpdate);
        if(optionalCar.isPresent() && optionalDiscountCard.isPresent()) {
            newBusinessCard.setId(optionalCar.get().getId());
            newDiscountCard.setId(optionalDiscountCard.get().getId());
            businessSuccess = dataProviderHibernate.update(newBusinessCard);
            discountSuccess = dataProviderHibernate.update(newDiscountCard);
        }
        else {
            fail();
        }
        assertTrue(businessSuccess);
        assertTrue(discountSuccess);*/
    }

    /**
     * удаление объекта по id
     * тип: позитивный
     */
    @Test
    public void deletePositive() {
        Set<SparePart> spareParts = new HashSet<>();
        SparePart sparePart1 = new SparePart(1L, "type1", "maker1", "11/11/2021");
        SparePart sparePart2 = new SparePart(2L, "type2", "maker2", "12/12/2021");
        CarProperties carProperties = new CarProperties(1L, 10, "LukOil", "Winter", "22/12/2022");
        spareParts.add(sparePart1);
        spareParts.add(sparePart2);
        Car car = new Car(1L, spareParts, "carModel", "carMaker", 11, "carColor", 2011, carProperties);
/*        Optional<BusinessCard> optionalCar = dataProviderHibernate.create(businessCardForDelete);
        Optional<DiscountCard> optionalDiscountCard = dataProviderHibernate.create(discountCardForDelete);
        if(optionalCar.isPresent() && optionalDiscountCard.isPresent()) {
            businessSuccess = dataProviderHibernate.delete(new BusinessCard(optionalCar.get().getId()));
            discountSuccess = dataProviderHibernate.delete(new DiscountCard(optionalDiscountCard.get().getId()));
        } else {
            fail();
        }
        // для удаления по конкретным id
*//*        businessCard = new BusinessCard(49L);
        discountCard = new DiscountCard(50L);
        businessSuccess = dataProviderHibernate.delete(businessCard);
        discountSuccess = dataProviderHibernate.delete(discountCard);*//*
        assertTrue(businessSuccess);
        assertTrue(discountSuccess);
    }*/
    }

    public Car initCar() {
        // init objects
        Car car = new Car();
        CarProperties carProperties = new CarProperties(10, "LukOil", "Winter",
                "22/12/2022");
        Set<SparePart> spareParts = new HashSet<>();

        // add data to spareParts and set in car
        SparePart sparePart1 = new SparePart( "type1", "maker1", "11/11/2021");
        SparePart sparePart2 = new SparePart( "type2", "maker2", "12/12/2021");
        sparePart1.setCar(car);
        sparePart2.setCar(car);
        spareParts.add(sparePart1);
        spareParts.add(sparePart2);
        car.setSpareParts(spareParts);

        // create car object
        car = new Car("carModel", "carMaker", 11, "carColor", 2011,
                carProperties);
        return car;
    }
}

