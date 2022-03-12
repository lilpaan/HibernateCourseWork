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

public class DataProviderNativeSQL {
    private static final Logger logger = LogManager.getLogger(DataProviderNativeSQL.class);

    @SuppressWarnings(ConstantsValues.UNCHECKED)
    public <T> Optional<T> readById(Class<T> typeClass, long id) {
        try(Session session = HibernateUtil.openSession(ConstantsValues.LAB5_HBN_CFG)) {
            long start = System.currentTimeMillis();
            NativeQuery<Car> query = session.createNativeQuery(ConstantsValues.GET_CAR_BY_ID + id, Car.class);
            long end = System.currentTimeMillis();
            logger.warn(ConstantsValues.NATIVE_SQL + ConstantsValues.READ_BY_ID + ConstantsValues.EXECUTE_TIME + (end-start));
            return (Optional<T>) Optional.ofNullable(query.getSingleResult());
        } catch (Exception e) {
            logger.error(e);
            return Optional.empty();
        }
    }

    public Optional<List<Car>> readAll() {
        List<Car> carList;
        try(Session session = HibernateUtil.openSession(ConstantsValues.LAB5_HBN_CFG)) {
            long start = System.currentTimeMillis();
            NativeQuery<Car> query = session.createNativeQuery(ConstantsValues.SQL_SELECT_STAR_FROM_CAR, Car.class);
            carList = query.getResultList();
            long end = System.currentTimeMillis();
            logger.warn(ConstantsValues.NATIVE_SQL + ConstantsValues.READ_ALL + ConstantsValues.EXECUTE_TIME + (end-start));
            return Optional.of(carList);
        } catch (Exception e) {
            logger.error(e);
            return Optional.empty();
        }
    }

}
