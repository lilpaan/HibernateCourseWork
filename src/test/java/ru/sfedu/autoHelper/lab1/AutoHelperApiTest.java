package ru.sfedu.autoHelper.lab1;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.junit.Test;

/**
 * Класс для исполнения тестов к лабораторной работе 1
 */
public class AutoHelperApiTest {
    private static final Logger logger = LogManager.getLogger(ru.sfedu.autoHelper.lab1.AutoHelperApiTest.class);
    IHibernateDataProvider dataProviderHibernate = new HibernateDataProvider();

    /**
     * Получение размера всех баз данных
     * Тип: позитивный
     */
    @Test
    public void getDatabaseSizePositive(){
        Object dbSize = 0;
        if (dataProviderHibernate.getDatabaseSize(null).isPresent()) {
            dbSize = dataProviderHibernate.getDatabaseSize(null).get();
        }
        logger.info(dbSize);
    }

    /**
     * Получение списка пользователей СУБД
     * Тип: позитивный
     */
    @Test
    public void getUserListPositive(){
        if (dataProviderHibernate.getUserList(null).isPresent()) {
            Object resultSet = dataProviderHibernate.getUserList(null).get();
            logger.info(resultSet);
        }
    }

    /**
     * Получение получение таблиц, созданных в СУБД
     * Тип: позитивный
     */
    @Test
    public void getTableListPositive(){
        if (dataProviderHibernate.getTableList(null).isPresent()) {
            Object resultSet = dataProviderHibernate.getTableList(null).get();
            logger.info(resultSet);
        }
    }

    /**
     * Получение типов таблиц, созданных в СУБД
     * Тип: позитивный
     */
    @Test
    public void getDataTypesPositive(){
        if (dataProviderHibernate.getDataTypes(null).isPresent()) {
            Object resultSet = dataProviderHibernate.getDataTypes(null).get();
            logger.info(resultSet);
        }
    }

}
