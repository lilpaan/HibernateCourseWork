package ru.sfedu.autoHelper;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.junit.Test;
import ru.sfedu.autoHelper.lab1.HibernateDataProvider;
import ru.sfedu.autoHelper.lab1.IHibernateDataProvider;

/**
 * Класс для исполнения тестов
 */
public class AutoHelperApiTest {
    private static final Logger logger = LogManager.getLogger(AutoHelperApiTest.class);
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
