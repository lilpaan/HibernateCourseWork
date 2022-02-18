package ru.sfedu.autoHelper.lab1;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.annotations.NamedNativeQueries;
import ru.sfedu.autoHelper.ConstantsValues;
import ru.sfedu.autoHelper.util.HibernateUtil;

import java.util.List;
import java.util.Optional;

/**
 * Класс для взаимодействия с данными с использованием библиотеки Hibernate
 */
public class HibernateDataProvider implements IHibernateDataProvider{

    @Override
    public Optional<Integer> getDatabaseSize(String labConfig) {
        Object result = HibernateUtil.doQuery(labConfig, ConstantsValues.GET_DATABASE_SIZE);
        List list1 = (List) result;
        int dbSize = 0;
        for(Object list2 : list1) {
            Object[] list3 = (Object[]) list2;
            dbSize += Integer.parseInt(list3[1].toString());
        }
        return Optional.of(dbSize);
    }

    @Override
    public Optional<Object> getUserList(String labConfig) {
        Object result = HibernateUtil.doQuery(labConfig, ConstantsValues.GET_USER_LIST);
            return Optional.of(result);
    }

    @Override
    public Optional<Object> getTableList(String labConfig) {
        Object result = HibernateUtil.doQuery(labConfig, ConstantsValues.GET_TABLE_LIST);
        return Optional.of(result);
    }

    @Override
    public Optional<Object> getDataTypes(String labConfig) {
        Object result = HibernateUtil.doQuery(labConfig, ConstantsValues.GET_DATA_TYPES);
        return Optional.of(result);
    }

}
