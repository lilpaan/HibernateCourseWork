package ru.sfedu.autoHelper.lab2;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.junit.Test;
import java.util.Date;
import java.util.List;
import java.util.Optional;

import static org.junit.Assert.*;

/**
 * Класс для исполнения тестов
 */
public class AutoHelperApiTest {
    private static final Logger logger = LogManager.getLogger(AutoHelperApiTest.class);
    IHibernateDataProvider dataProviderHibernate = new HibernateDataProvider();
    boolean success;
    Date currentDate = new Date(System.currentTimeMillis());


    /**
     * добавление объекта
     * тип: позитивный
     */
    @Test
    public void createPositive() {
        Optional<TestEntity> optionalTestEntity;
        TestEntity testEntity = new TestEntity("name!", "descr!", currentDate, true,
                new PanchenkoComponent("compName!", 223332, "sql!"));
        optionalTestEntity = dataProviderHibernate.create(testEntity);
        assertNotEquals(Optional.empty(), optionalTestEntity);
    }

    /**
     * чтение объекта по id
     * тип: позитивный
     */
    @Test
    public void readByIdPositive() {
        long id;
        Optional<TestEntity> optionalTestEntityy;
        TestEntity testEntityy = new TestEntity("name!", "descr!", currentDate, true,
                new PanchenkoComponent("compName!", 223332, "sql!"));
        optionalTestEntityy = dataProviderHibernate.create(testEntityy);
        if(optionalTestEntityy.isPresent()){
            id = optionalTestEntityy.get().getId();
            optionalTestEntityy = dataProviderHibernate.readById(id);
            assertNotEquals( Optional.empty(), optionalTestEntityy);
        } else {
            fail();
        }
    }


    /**
     * чтение всех объектов
     * тип: позитивный
     */
    @Test
    public void readAllPositive() {
        Optional<List<TestEntity>> optionalTestEntityList;
        optionalTestEntityList = dataProviderHibernate.readAll();
        assertNotEquals( Optional.empty(), optionalTestEntityList);
    }


    /**
     * обновление объекта
     * тип: позитивный
     */
    @Test
    public void updatePositive() {
        TestEntity newTestEntity = new TestEntity(2L, "newInfo", "dfdfsdd", currentDate,
                true, new PanchenkoComponent("dsddd", 223332, "sdsql"));
        success = dataProviderHibernate.update(newTestEntity);
        assertTrue(success);
    }

/**
     * удаление объекта по id
     * тип: позитивный
     */

    @Test
    public void deletePositive() {
        success = dataProviderHibernate.delete(6L);
        assertTrue(success);
    }

/**
     * добавление объекта
     * тип: негативный
     */


/*    @Test
    public void createNegative() {
        testEntity = new TestEntity(null, "ddfdfdf", "dfdfsdd", currentDate, true, null);
        success = dataProviderHibernate.create(testEntity);
        assertFalse(success);
    }
    */
    /**
     * чтение объекта по id
     * тип: негативный
     */


    public void readByIdNegative() {
        Optional<TestEntity> optionalTestEntity;
        optionalTestEntity = dataProviderHibernate.readById(66L);
        assertEquals( Optional.empty(), optionalTestEntity);
    }


    /**
     * обновление объекта
     * тип: негативный
     */

    @Test
    public void updateNegative() {
        success = dataProviderHibernate.update(null);
        assertFalse(success);
    }

}
