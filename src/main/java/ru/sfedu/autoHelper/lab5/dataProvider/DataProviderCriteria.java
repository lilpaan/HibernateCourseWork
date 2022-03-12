package ru.sfedu.autoHelper.lab5.dataProvider;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.hibernate.Session;
import ru.sfedu.autoHelper.ConstantsValues;
import ru.sfedu.autoHelper.lab5.entity.Car;
import ru.sfedu.autoHelper.util.HibernateUtil;

import javax.persistence.Query;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import java.util.List;
import java.util.Optional;

public class DataProviderCriteria implements IHibernateDataProvider {
    private static final Logger logger = LogManager.getLogger(DataProviderCriteria.class);
    CriteriaBuilder criteriaBuilder;
    CriteriaQuery<Car> carCriteriaQuery;

    @Override
    public <T> Optional<T> create(T object) {
        return Optional.empty();
    }

    @Override
    @SuppressWarnings(ConstantsValues.UNCHECKED)
    public <T> Optional<T> readById(Class<T> typeClass, long id) {
        try (Session session = HibernateUtil.openSession(ConstantsValues.LAB5_HBN_CFG)) {
            long start = System.currentTimeMillis();
            criteriaBuilder = session.getCriteriaBuilder();
            carCriteriaQuery = criteriaBuilder.createQuery(Car.class);
            Root<Car> carRoot = carCriteriaQuery.from(Car.class);
            Query query = session.createQuery(carCriteriaQuery.select(carRoot).where(criteriaBuilder.equal(carRoot
                    .get(ConstantsValues.ID), id)));
            long end = System.currentTimeMillis();
            logger.warn(ConstantsValues.CRITERIA + ConstantsValues.READ_BY_ID + ConstantsValues.EXECUTE_TIME
                    + (end-start));
            return (Optional<T>) Optional.of(query.getSingleResult());
        }
        catch (Exception e) {
            logger.error(e);
            return Optional.empty();
        }
    }

    @Override
    @SuppressWarnings(ConstantsValues.UNCHECKED)
    public Optional<List<Car>> readAll() {
        try (Session session = HibernateUtil.openSession(ConstantsValues.LAB5_HBN_CFG)) {
            long start = System.currentTimeMillis();
            criteriaBuilder = session.getCriteriaBuilder();
            carCriteriaQuery = criteriaBuilder.createQuery(Car.class);
            Root<Car> carRoot = carCriteriaQuery.from(Car.class);
            Query query = session.createQuery(carCriteriaQuery.select(carRoot));
            long end = System.currentTimeMillis();
            logger.warn(ConstantsValues.CRITERIA + ConstantsValues.READ_ALL + ConstantsValues.EXECUTE_TIME + (end-start));
            return Optional.of(query.getResultList());
        }
        catch (Exception e) {
            logger.error(e);
            return Optional.empty();
        }
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
