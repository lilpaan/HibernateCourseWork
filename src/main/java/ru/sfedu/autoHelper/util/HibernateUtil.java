package ru.sfedu.autoHelper.util;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.boot.MetadataSources;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;
import org.hibernate.query.NativeQuery;
import org.hibernate.service.ServiceRegistry;
import ru.sfedu.autoHelper.ConstantsValues;
import ru.sfedu.autoHelper.entity.Car;
import ru.sfedu.autoHelper.lab2.TestEntity;

import java.io.File;

/**
 * Вспомогательный класс для взаимодействия с Hibernate
 */
public class HibernateUtil {
    private static SessionFactory sessionFactory;

    /**
     * Создание фабрики
     *© проф. Жмайлов Б.Б. ЮФУ, ИВТиП, каф. ИИТ
     * @param lab1HbnCfg путь к файлу конфигурации
     */
    public static SessionFactory getSessionFactory(String lab1HbnCfg) {
        File file;
        if (sessionFactory == null) {
            if (lab1HbnCfg == null){
                file = new File(ConstantsValues.DEFAULT_HBN_CFG);
            } else {
                file = new File(lab1HbnCfg);
            }
            Configuration configuration = new Configuration().configure(file);
            ServiceRegistry serviceRegistry
                    = new StandardServiceRegistryBuilder()
                    .applySettings(configuration.getProperties()).build();
            MetadataSources metadataSources =
                    new MetadataSources(serviceRegistry);
            metadataSources.addAnnotatedClass(Car.class);// Аннотированная сущность
            metadataSources.addAnnotatedClass(TestEntity.class);
            sessionFactory = metadataSources.buildMetadata().buildSessionFactory();
        }

        return sessionFactory;
    }

    /**
     * Метод для выполнения запросов
     * @param labConfig путь к файлу конфигурации
     * @param querySet sql-запрос
     * @return объект, в который помещается результат выполнения запроса
     */
    public static Object doQuery(String labConfig, String querySet) {
        Session session = openSession(labConfig);
        NativeQuery query = session.createSQLQuery(querySet);
        Object result = query.getResultList();
        session.close();
        return result;
    }

    public static Session openSession(String labConfig) {
        SessionFactory sessionFactory =  HibernateUtil.getSessionFactory(labConfig);
        return sessionFactory.openSession();
    }
}

