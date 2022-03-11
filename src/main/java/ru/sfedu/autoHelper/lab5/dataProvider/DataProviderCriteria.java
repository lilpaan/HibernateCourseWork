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

public class DataProviderCriteria implements IHibernateDataProvider{
    private static final Logger logger = LogManager.getLogger(DataProviderCriteria.class)
    @Override
    public <T> Optional<T> create(T object) {
        return Optional.empty();
    }

    @Override
    public <T> Optional<T> readById(Class<T> typeClass, long id) {
/*        public <T> Optional<T> readById(Class<T> typeClass, long id) {
            try(Session session = HibernateUtil.openSession(ConstantsValues.LAB5_HBN_CFG)) {
                NativeQuery<Car> query = session.createNativeQuery(ConstantsValues.GET_CAR_BY_ID + id, Car.class);
                return (Optional<T>) Optional.ofNullable(query.getSingleResult());
            } catch (Exception e) {
                logger.error(e);
                return Optional.empty();
            }*/
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
