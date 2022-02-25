package ru.sfedu.autoHelper.lab2;

import org.junit.Test;
import java.util.Date;
import java.util.List;
import java.util.Optional;

import static org.junit.Assert.*;

/**
 * Класс для исполнения тестов
 */
public class AutoHelperApiTest {
    IHibernateDataProvider dataProviderHibernate = new HibernateDataProvider();
    Date currentDate = new Date(System.currentTimeMillis());
    TestEntity testEntity;
    boolean success;


    /**
     * добавление объекта
     * тип: позитивный
     */
    @Test
    public void createPositive() {
        Optional<TestEntity> optionalTestEntity;
        testEntity = new TestEntity("name!", "descr!", currentDate, true, new PanchenkoComponent("compName!", 223332, "sql!"));
        optionalTestEntity = dataProviderHibernate.create(testEntity);
        assertNotEquals(Optional.empty(), optionalTestEntity);
    }

    /**
     * чтение объекта по id
     * тип: позитивный
     */
    @Test
    public void readByIdPositive() {
        Optional<TestEntity> optionalTestEntity;
        testEntity = new TestEntity("nahme!", "descr!", currentDate, true,
                new PanchenkoComponent("compName!", 223332, "sql!"));
        optionalTestEntity = dataProviderHibernate.create(testEntity);
        if(optionalTestEntity.isPresent()){
            optionalTestEntity = dataProviderHibernate.readById(optionalTestEntity.get().getId());
            assertNotEquals( Optional.empty(), optionalTestEntity);
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
     * чтение объекта по id
     * тип: негативный
     */
    @Test
    public void readByIdNegative() {
        Optional<TestEntity> optionalTestEntity;
        optionalTestEntity = dataProviderHibernate.readById(667L);
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
