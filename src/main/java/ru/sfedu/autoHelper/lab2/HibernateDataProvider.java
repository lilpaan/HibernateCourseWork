package ru.sfedu.autoHelper.lab2;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import ru.sfedu.autoHelper.ConstantsValues;
import ru.sfedu.autoHelper.util.HibernateUtil;

import java.util.List;
import java.util.Optional;

/**
 * Класс, содержащий методы для реализации основных CRUD операций.
 */
public class HibernateDataProvider implements IHibernateDataProvider {
    private static final Logger logger = LogManager.getLogger(HibernateDataProvider.class);

    @Override
    public Optional<TestEntity> create(TestEntity testEntity) {
        Optional<TestEntity> optionalTestEntity;
        try(Session session = HibernateUtil.openSession(ConstantsValues.LAB2_HBN_CFG)) {
            session.beginTransaction();
            logger.info(ConstantsValues.SESSION_IS_OPENED);
            session.save(testEntity);
            session.getTransaction().commit();
            logger.info(ConstantsValues.FILE_WAS_INSERT);
            optionalTestEntity = Optional.of(testEntity);
            return optionalTestEntity;
        } catch (HibernateException e) {
            logger.error(e);
            return Optional.empty();
        }
    }

    @Override
    public Optional<TestEntity> readById(long id) {
        Optional<TestEntity> optionalTestEntity;
        try {
            Session session = HibernateUtil.openSession(ConstantsValues.LAB2_HBN_CFG);
            logger.info(ConstantsValues.SESSION_IS_OPENED);
            optionalTestEntity = Optional.of(session.get(TestEntity.class, id));
            logger.info(ConstantsValues.OBJECT_WAS_READ);
        } catch (Exception e) {
            logger.error(e);
            return Optional.empty();
        }
        return optionalTestEntity;
    }

    @Override
    public Optional<List<TestEntity>> readAll() {
        Optional<List<TestEntity>> testEntityList;
        try {
            Session session = HibernateUtil.openSession(ConstantsValues.LAB2_HBN_CFG);
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
    }

    @Override
    public boolean update(TestEntity testEntity) {
        boolean isUpdated;
        try {
            Session session = HibernateUtil.openSession(ConstantsValues.LAB2_HBN_CFG);
            session.beginTransaction();
            logger.info(ConstantsValues.SESSION_IS_OPENED);
            session.update(testEntity);
            session.getTransaction().commit();
            logger.info(ConstantsValues.OBJECT_WAS_UPDATED);
            isUpdated = true;
        } catch (Exception e) {
            logger.error(e);
            isUpdated = false;
        }
        return isUpdated;
    }

    @Override
    public boolean delete(long id) {
        boolean isDeleted;
        try {
            Session session = HibernateUtil.openSession(ConstantsValues.LAB2_HBN_CFG);
            logger.info(ConstantsValues.SESSION_IS_OPENED);
            session.delete(new TestEntity(id));
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