package ru.sfedu.autoHelper.lab1;

import java.util.Optional;

/**
 * Интерфейс провайдера данных Hibernate для выполнения задач лабораторной работы 1
 */
public interface IHibernateDataProvider {

    /**
     * Получение размера всех баз данных
     * @param labConfig путь к файлу конфигурации
     * @return результат sql-запроса - размер всех баз данных
     */
    Optional<Integer> getDatabaseSize(String labConfig);

    /**
     * Получение списка пользователей СУБД
     * @param labConfig путь к файлу конфигурации
     * @return результат sql-запроса - список пользователей СУБД
     */
    Optional<Object> getUserList(String labConfig);

    /**
     * Получение получение таблиц, созданных в СУБД
     * @param labConfig путь к файлу конфигурации
     * @return результат sql-запроса - таблицы, созданные в СУБД
     */
    Optional<Object> getTableList(String labConfig);

    /**
     * Получение типов таблиц, созданных в СУБД
     * @param labConfig путь к файлу конфигурации
     * @return результат sql-запроса - типы таблиц, созданных в СУБД
     */
    Optional<Object> getDataTypes(String labConfig);
}
