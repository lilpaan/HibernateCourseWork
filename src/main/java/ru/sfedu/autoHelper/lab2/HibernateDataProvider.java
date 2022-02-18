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
    boolean isCreated;

    @Override
    public boolean create(TestEntity testEntity) {
        try {
            Session session = HibernateUtil.openSession(ConstantsValues.LAB2_HBN_CFG);
            logger.info(ConstantsValues.SESSION_IS_OPENED);
            session.save(testEntity);
            session.close();
            isCreated = true;
        } catch (HibernateException e) {
            logger.error(e);
            isCreated = false;
        }
        /*        Transaction transaction = null;
        try (Session session = getSession(Constants.LAB2_HBN_CFG)) {
            transaction = session.beginTransaction();
            session.save(testEntity);
            transaction.commit();
            logger.info("insert successful");
            return true;
        } catch (Exception e){
            logger.error(e);
            if (transaction != null) {
                transaction.rollback();
            }*/
        return isCreated;
    }

    @Override
    public Optional<TestEntity> readById(long id) {
/*        public Optional<TestEntity> getById(long id) {
            logger.info("start getById");
            logger.debug("start getById with id: " + id);
            Transaction transaction = null;
            Optional<TestEntity> optional;
            try (Session session = getSession(Constants.LAB2_HBN_CFG)) {
                transaction = session.beginTransaction();
                optional = Optional.ofNullable(session.get(TestEntity.class, id));
                transaction.commit();
                logger.info("getById successful");
                return optional;
            } catch (Exception e){
                logger.error(e);
                if (transaction != null) {
                    transaction.rollback();
                }
                return Optional.empty();
            }*/
        return Optional.empty();
    }

    @Override
    public Optional<List<TestEntity>> readAll() {
  /*      public Optional<List<TestEntity>> selectAll() {
            logger.info("start select");
            List<TestEntity> list;
            try (Session session = getSession(Constants.LAB2_HBN_CFG)) {
                list = session.createQuery("from TestEntity ", TestEntity.class).list();
                logger.info("select successful");
                return Optional.ofNullable(list);
            }*/
        return Optional.empty();
    }

    @Override
    public void update(TestEntity testEntity) {
/*        public boolean update(TestEntity testEntity) {
            logger.info("start update");
            logger.debug("start update with entity: " + testEntity);
            Transaction transaction = null;
            try (Session session = getSession(Constants.LAB2_HBN_CFG)) {
                transaction = session.beginTransaction();
                session.update(testEntity);
                transaction.commit();
                logger.info("update successful");
                return true;
            } catch (Exception e){
                logger.error(e);
                if (transaction != null) {
                    transaction.rollback();
                }
                return false;
            }*/
    }

    @Override
    public void delete(long id) {
/*        public boolean delete(long id) {
            logger.info("start delete");
            logger.debug("start delete with id: " + id);
            Transaction transaction = null;
            try (Session session = getSession(Constants.LAB2_HBN_CFG)) {
                transaction = session.beginTransaction();
                session.delete(new TestEntity(id));
                transaction.commit();
                logger.info("delete successful");
                return true;
            } catch (Exception e){
                logger.error(e);
                if (transaction != null) {
                    transaction.rollback();
                }
                return false;
            }*/
    }

}