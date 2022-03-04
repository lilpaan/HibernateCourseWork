package ru.sfedu.autoHelper.util;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.boot.MetadataSources;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;
import org.hibernate.query.NativeQuery;
import org.hibernate.service.ServiceRegistry;
import ru.sfedu.autoHelper.ConstantsValues;
import ru.sfedu.autoHelper.lab2.TestEntity;
import ru.sfedu.autoHelper.lab3.mappedSuperclass.BusinessCard;
import ru.sfedu.autoHelper.lab3.mappedSuperclass.DiscountCard;
import ru.sfedu.autoHelper.lab3.mappedSuperclass.InfoCard;
import ru.sfedu.autoHelper.lab4.setTypeMapping.User;

import java.io.File;

/**
 * Вспомогательный класс для взаимодействия с Hibernate
 */
public class HibernateUtil {
    private static SessionFactory sessionFactory;

    /**
     * Создание фабрики
     *© проф. Жмайлов Б.Б. ЮФУ, ИВТиП, каф. ИИТ
     * @param labhbncfg путь к файлу конфигурации
     */
    public static SessionFactory getSessionFactory(String labhbncfg) {
        File file;
        if (sessionFactory == null) {
            if (labhbncfg == null){
                file = new File(ConstantsValues.DEFAULT_HBN_CFG);
            } else {
                file = new File(labhbncfg);
            }
            Configuration configuration = new Configuration().configure(file);
            ServiceRegistry serviceRegistry
                    = new StandardServiceRegistryBuilder()
                    .applySettings(configuration.getProperties()).build();
            MetadataSources metadataSources =
                    new MetadataSources(serviceRegistry);
            // аннотированная сущность для лабораторной работы 2
            metadataSources.addAnnotatedClass(TestEntity.class);
            // аннотированные сущности для лабораторной работы 3. стратегия Mapped Superclass
            metadataSources.addAnnotatedClass(InfoCard.class);
            metadataSources.addAnnotatedClass(BusinessCard.class);
            metadataSources.addAnnotatedClass(DiscountCard.class);
            // аннотированные сущности для лабораторной работы 3. стратегия Single Table
            metadataSources.addAnnotatedClass(ru.sfedu.autoHelper.lab3.singleTable.InfoCard.class);
            metadataSources.addAnnotatedClass(ru.sfedu.autoHelper.lab3.singleTable.BusinessCard.class);
            metadataSources.addAnnotatedClass(ru.sfedu.autoHelper.lab3.singleTable.DiscountCard.class);
            // аннотированные сущности для лабораторной работы 3. стратегия Joined Table
            metadataSources.addAnnotatedClass(ru.sfedu.autoHelper.lab3.joinedTable.InfoCard.class);
            metadataSources.addAnnotatedClass(ru.sfedu.autoHelper.lab3.joinedTable.BusinessCard.class);
            metadataSources.addAnnotatedClass(ru.sfedu.autoHelper.lab3.joinedTable.DiscountCard.class);
            // аннотированные сущности для лабораторной работы 3. стратегия Table Per Class
            metadataSources.addAnnotatedClass(ru.sfedu.autoHelper.lab3.tablePerClass.InfoCard.class);
            metadataSources.addAnnotatedClass(ru.sfedu.autoHelper.lab3.tablePerClass.BusinessCard.class);
            metadataSources.addAnnotatedClass(ru.sfedu.autoHelper.lab3.tablePerClass.DiscountCard.class);
            // аннотированные сущности для лабораторной работы 4. отображение коллекции типа Set
            metadataSources.addAnnotatedClass(User.class);
            metadataSources.addAnnotatedClass(ru.sfedu.autoHelper.lab4.listTypeMapping.User.class);
            metadataSources.addAnnotatedClass(ru.sfedu.autoHelper.lab4.mapTypeMapping.User.class);
            metadataSources.addAnnotatedClass(ru.sfedu.autoHelper.lab4.componentTypeMapping.User.class);
            //metadataSources.addAnnotatedClass(ru.sfedu.autoHelper.lab4.mapComponentTypeMapping.User.class);

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

