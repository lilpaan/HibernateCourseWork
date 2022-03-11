package ru.sfedu.autoHelper.lab5.dataProvider;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.hibernate.Session;
import org.hibernate.query.NativeQuery;
import ru.sfedu.autoHelper.ConstantsValues;
import ru.sfedu.autoHelper.lab5.entity.Car;
import ru.sfedu.autoHelper.util.HibernateUtil;

import java.util.List;
import java.util.Optional;

public class DataProviderNativeSQL implements IHibernateDataProvider{
    private static final Logger logger = LogManager.getLogger(DataProviderNativeSQL.class);
    @Override
    public <T> Optional<T> create(T object) {
        //Object result = HibernateUtil.doQuery(ConstantsValues.LAB5_HBN_CFG, ConstantsValues.GET_DATABASE_SIZE);
        return Optional.empty();
    }

    @Override
    public <T> Optional<T> readById(Class<T> typeClass, long id) {
        Object result = null;
        try(Session session = HibernateUtil.openSession(ConstantsValues.LAB5_HBN_CFG)) {
            NativeQuery<Car> query = session.createNativeQuery(ConstantsValues.GET_CAR_BY_ID + id, Car.class);
        } catch (Exception e) {
            logger.error(e);
            return Optional.empty();
        }
//        return Optional.of(result);
return null;
    }

    @Override
    public Optional<List<Car>> readAll() {
        return Optional.empty();
    }

    @Override
    public <T> boolean update(T object) {
        return false;
    }

    @Override
    public <T> boolean delete(T object) {
        return false;
    }
}
