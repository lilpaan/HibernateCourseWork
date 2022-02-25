package ru.sfedu.autoHelper.lab3;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import ru.sfedu.autoHelper.ConstantsValues;
import ru.sfedu.autoHelper.util.HibernateUtil;
import java.util.Optional;

public class HibernateDataProvider implements IHibernateDataProvider{
    private static final Logger logger = LogManager.getLogger(ru.sfedu.autoHelper.lab3.HibernateDataProvider.class);

    @Override
    public <T> boolean create(Optional<T> object) {
        boolean isCreated;
        Object requiredObject = null;
        try(Session session = HibernateUtil.openSession(ConstantsValues.LAB3_HBN_CFG)) {
            session.beginTransaction();
            logger.info(ConstantsValues.SESSION_IS_OPENED);
            if(object.isPresent()) {
                requiredObject = object.get();
            }
            session.save(requiredObject);
            session.getTransaction().commit();
            logger.info(ConstantsValues.FILE_WAS_INSERT);
            isCreated = true;
        } catch (HibernateException e) {
            logger.error(e);
            isCreated = false;
        }
        return isCreated;
    }

    @Override
    public <T> Optional<T> readById(Class<T> typeClass, long id) {
        Optional<T> optional;
        try (Session session = HibernateUtil.openSession(ConstantsValues.LAB3_HBN_CFG)) {
            logger.info(ConstantsValues.SESSION_IS_OPENED);
            optional = Optional.ofNullable(session.get(typeClass, id));
            logger.info(ConstantsValues.OBJECT_WAS_READ);
        } catch (Exception e) {
            logger.error(e);
            return Optional.empty();
        }
        return optional;
    }

/*    @Override
// ????? List <T> list
// ???? <T> Optional<List<T>>
    public Optional<List<T>> readAll() {
        Optional<List<T>> testEntityList;
        try {
            Session session = HibernateUtil.openSession(ConstantsValues.LAB3_HBN_CFG);
            logger.info(ConstantsValues.SESSION_IS_OPENED);
            testEntityList = Optional.ofNullable(session.createQuery
                    (ConstantsValues.SQL_SELECT_ALL_DATA, TestEntity.class).getResultList());
            logger.info(ConstantsValues.LIST_OBJECT_WAS_READ);
            session.close();
            logger.debug(ConstantsValues.SESSION_IS_CLOSED);
        } catch (Exception e) {
            logger.error(e);
            return Optional.empty();
        }
        return testEntityList;
    }*/

    @Override
    public <T> boolean update(Optional<T> object) {
        boolean isUpdated;
        try {
            Session session = HibernateUtil.openSession(ConstantsValues.LAB3_HBN_CFG);
            session.beginTransaction();
            logger.info(ConstantsValues.SESSION_IS_OPENED);
            session.update(object);
            session.getTransaction().commit();
            logger.info(ConstantsValues.OBJECT_WAS_UPDATED);
            logger.debug(ConstantsValues.SESSION_IS_CLOSED);
            isUpdated = true;
        } catch (Exception e) {
            logger.error(e);
            isUpdated = false;
        }
        return isUpdated;
    }

    @Override
    public <T> boolean delete(Optional<T> object) {
        boolean isDeleted;
        try {
            Session session = HibernateUtil.openSession(ConstantsValues.LAB3_HBN_CFG);
            logger.info(ConstantsValues.SESSION_IS_OPENED);
            session.delete(object);
            logger.info(ConstantsValues.OBJECT_WAS_DELETED);
            session.close();
            logger.debug(ConstantsValues.SESSION_IS_CLOSED);
            isDeleted = true;
        } catch (Exception e) {
            logger.error(e);
            isDeleted = false;
        }
        return isDeleted;
    }

}
