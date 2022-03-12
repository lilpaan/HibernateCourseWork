package ru.sfedu.autoHelper.lab5;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.junit.Test;
import ru.sfedu.autoHelper.lab5.dataProvider.DataProviderCriteria;
import ru.sfedu.autoHelper.lab5.dataProvider.DataProviderHQL;
import ru.sfedu.autoHelper.lab5.dataProvider.DataProviderNativeSQL;
import ru.sfedu.autoHelper.lab5.dataProvider.IHibernateDataProvider;
import ru.sfedu.autoHelper.lab5.entity.Car;

public class TimeTest {
    private static final Logger logger = LogManager.getLogger(TimeTest.class);
    IHibernateDataProvider dataProviderCriteria = new DataProviderCriteria();
    IHibernateDataProvider dataProviderNativeSQL = new DataProviderNativeSQL();
    IHibernateDataProvider dataProviderHQL = new DataProviderHQL();
    Car car;

    /**
     * чтение объекта по id
     * тип: позитивный
     */
    @Test
    public void readByIdPositive() {
dataProviderHQL.readById(Car.class, 338L);
dataProviderNativeSQL.readById(Car.class, 338L);
dataProviderCriteria.readById(Car.class, 338L);
    }

    /**
     * чтение всех объектов
     * тип: позитивный
     */
    @Test
    public void TimeReadAllPositive() {
        dataProviderCriteria.readAll();
        dataProviderNativeSQL.readAll();
        dataProviderHQL.readAll();
    }
}
