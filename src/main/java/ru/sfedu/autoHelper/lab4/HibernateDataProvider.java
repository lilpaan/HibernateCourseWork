package ru.sfedu.autoHelper.lab4;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import ru.sfedu.autoHelper.ConstantsValues;
import ru.sfedu.autoHelper.util.HibernateUtil;

import java.util.Optional;

public class HibernateDataProvider implements IHibernateDataProvider {
    private static final Logger logger = LogManager.getLogger(HibernateDataProvider.class);

    @Override
    public <T> Optional<T> create(T object) {
        Optional<T> optionalObject;
        try(Session session = HibernateUtil.openSession(ConstantsValues.LAB4_HBN_CFG)) {
            session.beginTransaction();
            logger.info(ConstantsValues.SESSION_IS_OPENED);
            session.save(object);
            session.getTransaction().commit();
            logger.info(ConstantsValues.FILE_WAS_INSERT);
            optionalObject = Optional.ofNullable(object);
        } catch (HibernateException e) {
            logger.error(e);
            optionalObject = Optional.empty();
        }
        return optionalObject;
    }

    @Override
    public <T> Optional<T> readById(Class<T> typeClass, long id) {
        Optional<T> optional;
        try (Session session = HibernateUtil.openSession(ConstantsValues.LAB4_HBN_CFG)) {
            logger.info(ConstantsValues.SESSION_IS_OPENED);
            session.beginTransaction();
            logger.info(ConstantsValues.SESSION_IS_OPENED);
            optional = Optional.ofNullable(session.get(typeClass, id));
            session.getTransaction().commit();
            logger.info(ConstantsValues.OBJECT_WAS_READ);
        } catch (Exception e) {
            logger.error(e);
            return Optional.empty();
        }
        return optional;
    }

    @Override
    public <T> boolean update(T object) {
        boolean isUpdated;
        try (Session session = HibernateUtil.openSession(ConstantsValues.LAB4_HBN_CFG)) {
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
    public <T> boolean delete(T object) {
        boolean isDeleted;
        try (Session session = HibernateUtil.openSession(ConstantsValues.LAB4_HBN_CFG)) {
            logger.info(ConstantsValues.SESSION_IS_OPENED);
            session.beginTransaction();
            session.delete(object);
            logger.info(ConstantsValues.OBJECT_WAS_DELETED);
            session.getTransaction().commit();
            logger.debug(ConstantsValues.TRANSACTION_COMPLETED);
            isDeleted = true;
        } catch (Exception e) {
            logger.error(e);
            isDeleted = false;
        }
        return isDeleted;
    }

}
