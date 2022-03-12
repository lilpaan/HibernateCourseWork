package ru.sfedu.autoHelper.lab5;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.junit.Test;
import ru.sfedu.autoHelper.lab5.dataProvider.DataProviderHQL;
import ru.sfedu.autoHelper.lab5.dataProvider.IHibernateDataProvider;
import ru.sfedu.autoHelper.lab5.entity.Car;
import ru.sfedu.autoHelper.lab5.entity.CarProperties;
import ru.sfedu.autoHelper.lab5.entity.SparePart;

import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;

import static org.junit.Assert.*;

/**
 * Класс для исполнения тестов с HQL провайдером данных
 */
public class HQLTest {
    private static final Logger logger = LogManager.getLogger(HQLTest.class);
    IHibernateDataProvider dataProviderHQL = new DataProviderHQL();
    Car car;
    Car newCar;
    boolean success;

    /**
     * добавление объекта
     * тип: позитивный
     */
    @Test
    public void createPositive() {
        Optional<Car> optionalCar;
        if (CarInitForTest.initCar().isPresent()) {
            car = CarInitForTest.initCar().get();
        } else {
            fail();
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
        Optional<Car> optionalCar;
        if (CarInitForTest.initCar().isPresent()) {
            car = CarInitForTest.initCar().get();
        } else {
            fail();
        }
        optionalCar = dataProviderHQL.create(car);
        if(optionalCar.isPresent()) {
            optionalCar = dataProviderHQL.readById(Car.class, optionalCar.get().getId());
            assertNotEquals( Optional.empty(), optionalCar);
        } else {
            fail();
        }
    }

    /**
     * Получение всех объектов
     * тип: позитивный
     */
    @Test
    public void readAllPositive() {
        Optional<List<Car>> optionalCarList;
        optionalCarList = dataProviderHQL.readAll();
        assertNotEquals( Optional.empty(), optionalCarList);
    }

    /**
     * обновление объекта
     * тип: позитивный
     */
    @Test
    public void updatePositive() {
        Optional<Car> optionalCar;
        if (CarInitForTest.initCar().isPresent() && CarInitForTest.initNewCar().isPresent()) {
            car = CarInitForTest.initCar().get();
            newCar = CarInitForTest.initNewCar().get();
        } else {
            fail();
        }
        optionalCar = dataProviderHQL.create(car);
        if (optionalCar.isPresent()) {
            newCar.setId(optionalCar.get().getId());
            success = dataProviderHQL.update(newCar);
        }
        assertTrue(success);
    }

    /**
     * удаление объекта по id
     * тип: позитивный
     */
    @Test
    public void deletePositive() {
        Optional<Car> optionalCar;
        if (CarInitForTest.initCar().isPresent()) {
            car = CarInitForTest.initCar().get();
        } else {
            fail();
        }
        optionalCar = dataProviderHQL.create(car);
        if(optionalCar.isPresent()) {
            success = dataProviderHQL.delete(optionalCar.get());
        } else {
            fail();
        }
        assertTrue(success);
        // для удаления по конкретным id
/*        Optional<Car> optionalCar;
        optionalCar = dataProviderHQL.readById(Car.class, 338L);
        if (optionalCar.isPresent()) {
            success = dataProviderHQL.delete(optionalCar.get());
        } else {
            fail();
        }
        assertTrue(success);*/
    }

}

