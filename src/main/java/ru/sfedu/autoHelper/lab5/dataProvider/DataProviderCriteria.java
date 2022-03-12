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
    /*CriteriaQuery<Car> carCriteriaQuery;*/

    @Override
    public <T> Optional<T> create(T object) {
        return Optional.empty();
    }

    @Override
    @SuppressWarnings(ConstantsValues.UNCHECKED)
    public <T> Optional<T> readById(Class<T> typeClass, long id) {
        try (Session session = HibernateUtil.openSession(ConstantsValues.LAB5_HBN_CFG)) {
            criteriaBuilder = session.getCriteriaBuilder();
            CriteriaQuery<Car> carCriteriaQuery;
            carCriteriaQuery = criteriaBuilder.createQuery(Car.class);
            Root<Car> carRoot = carCriteriaQuery.from(Car.class);
            carCriteriaQuery.select(carRoot).where(criteriaBuilder.equal(carRoot.get("id"), id));
            Query query = session.createQuery(carCriteriaQuery);
            List<Car> results = query.getResultList();
                return (Optional<T>) Optional.of(results.get(0));
        }
       catch ( Exception e) {
        logger.error(e);
        return Optional.empty();
        }
    }
/*        try (Session session = getSession(Constants.LAB5_HBN_CFG)){
            CriteriaBuilder cb = session.getCriteriaBuilder();
            CriteriaQuery<User> cr = cb.createQuery(User.class);
            Root<User> root = cr.from(User.class);
            cr.select(root).where(cb.gt(root.get("id"), id));
            Query query = session.createQuery(cr);
            List<User> results = query.getResultList();
            if (results.size() > 0){
                return Optional.of(results.get(0));
            }
            return Optional.empty();*/

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
