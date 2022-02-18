package ru.sfedu.autoHelper;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.junit.Test;
import ru.sfedu.autoHelper.lab2.HibernateDataProvider;
import ru.sfedu.autoHelper.lab2.IHibernateDataProvider;
import ru.sfedu.autoHelper.lab2.PanchenkoComponent;
import ru.sfedu.autoHelper.lab2.TestEntity;

import java.util.Date;

import static org.junit.Assert.assertTrue;

/**
 * Класс для исполнения тестов
 */
public class AutoHelperApiTest {
    private static final Logger logger = LogManager.getLogger(AutoHelperApiTest.class);
    IHibernateDataProvider dataProviderHibernate = new HibernateDataProvider();

// lab2
TestEntity testEntity;
    Date currentDate = new Date(System.currentTimeMillis());
    @Test
    public void insertPositive(){
        boolean success;
        testEntity = new TestEntity(null, "ddfdfdf", "dfdfsdd", currentDate, true, new PanchenkoComponent("dsddd", 223332, "sdsql"));
        success = dataProviderHibernate.create(testEntity);
        assertTrue(success);
        //dataProviderHibernate.create("df", "dfdf");
    }


   /* // lab1
    *//**
     * Получение размера всех баз данных
     * Тип: позитивный
     *//*
    @Test
    public void getDatabaseSizePositive(){
        Object dbSize = 0;
        if (dataProviderHibernate.getDatabaseSize(null).isPresent()) {
            dbSize = dataProviderHibernate.getDatabaseSize(null).get();
        }
        logger.info(dbSize);
    }

    *//**
     * Получение списка пользователей СУБД
     * Тип: позитивный
     *//*
    @Test
    public void getUserListPositive(){
        if (dataProviderHibernate.getUserList(null).isPresent()) {
            Object resultSet = dataProviderHibernate.getUserList(null).get();
            logger.info(resultSet);
        }
    }

    *//**
     * Получение получение таблиц, созданных в СУБД
     * Тип: позитивный
     *//*
    @Test
    public void getTableListPositive(){
        if (dataProviderHibernate.getTableList(null).isPresent()) {
            Object resultSet = dataProviderHibernate.getTableList(null).get();
            logger.info(resultSet);
        }
    }

    *//**
     * Получение типов таблиц, созданных в СУБД
     * Тип: позитивный
     *//*
    @Test
    public void getDataTypesPositive(){
        if (dataProviderHibernate.getDataTypes(null).isPresent()) {
            Object resultSet = dataProviderHibernate.getDataTypes(null).get();
            logger.info(resultSet);
        }
    }
*/
}
