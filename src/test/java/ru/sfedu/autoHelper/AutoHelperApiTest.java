package ru.sfedu.autoHelper;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.junit.Test;
import ru.sfedu.autoHelper.lab2.HibernateDataProvider;
import ru.sfedu.autoHelper.lab2.IHibernateDataProvider;
import ru.sfedu.autoHelper.lab2.PanchenkoComponent;
import ru.sfedu.autoHelper.lab2.TestEntity;

import java.util.Date;
import java.util.Optional;

import static org.junit.Assert.*;

/**
 * Класс для исполнения тестов
 */
public class AutoHelperApiTest {
    private static final Logger logger = LogManager.getLogger(AutoHelperApiTest.class);
    IHibernateDataProvider dataProviderHibernate = new HibernateDataProvider();
    boolean success;

// lab2
TestEntity testEntity;
    Date currentDate = new Date(System.currentTimeMillis());
    @Test
    public void createPositive(){
        testEntity = new TestEntity(null, "ddfdfdf", "dfdfsdd", currentDate, true, new PanchenkoComponent("dsddd", 223332, "sdsql"));
        success = dataProviderHibernate.create(testEntity);
        assertTrue(success);
    }

    @Test
    public void readByIdPositive() {
        Optional<TestEntity> optionalTestEntity;
        optionalTestEntity = dataProviderHibernate.readById(6L);
        assertNotEquals( Optional.empty(), optionalTestEntity);
    }

    @Test
    public void readAllPositive() {

    }

    @Test
    public void updatePositive() {
        TestEntity newTestEntity = new TestEntity(2L, "newInfo", "dfdfsdd", currentDate,
                true, new PanchenkoComponent("dsddd", 223332, "sdsql"));
        success = dataProviderHibernate.update(newTestEntity);
        assertTrue(success);
    }

    @Test
    public void deletePositive() {

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
