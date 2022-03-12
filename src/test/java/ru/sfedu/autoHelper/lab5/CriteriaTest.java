package ru.sfedu.autoHelper.lab5;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.junit.Test;
import ru.sfedu.autoHelper.lab5.dataProvider.DataProviderCriteria;
import ru.sfedu.autoHelper.lab5.dataProvider.DataProviderHQL;
import ru.sfedu.autoHelper.lab5.dataProvider.IHibernateDataProvider;
import ru.sfedu.autoHelper.lab5.entity.Car;

import java.util.List;
import java.util.Optional;

import static org.junit.Assert.assertNotEquals;
import static org.junit.Assert.fail;

public class CriteriaTest {
    private static final Logger logger = LogManager.getLogger(CriteriaTest.class);
    DataProviderCriteria dataProviderCriteria = new DataProviderCriteria();
    IHibernateDataProvider dataProviderHQL = new DataProviderHQL();
    Car car;

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
            optionalCar = dataProviderCriteria.readById(Car.class, optionalCar.get().getId());
        } else {
            fail();
        }
        assertNotEquals( Optional.empty(), optionalCar);
    }

    /**
     * Получение всех объектов
     * тип: позитивный
     */
    @Test
    public void readAllPositive() {
        Optional<List<Car>> optionalCarList;
        optionalCarList = dataProviderCriteria.readAll();
        assertNotEquals( Optional.empty(), optionalCarList);
    }


}
