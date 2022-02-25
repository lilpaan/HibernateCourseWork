package ru.sfedu.autoHelper.lab3;

import org.junit.Test;
import ru.sfedu.autoHelper.lab3.joinedTable.DiscountCard;
import ru.sfedu.autoHelper.lab3.joinedTable.BusinessCard;

import java.util.Optional;

import static org.junit.Assert.assertTrue;

public class AutoHelperApiJoinedTableTest {
        IHibernateDataProvider dataProviderHibernate = new HibernateDataProvider();
    BusinessCard businessCard =
            new BusinessCard("businessCompany", "companyDescription",
                    "businessPhoneNumber", "businessAddress", "businessEmail",
                    "businessSite");
    DiscountCard discountCard =
            new DiscountCard(1L, "discountCompany", "discountDescription",
                    true, 12345, true);
        boolean success;

        /**
         * добавление объекта
         * тип: позитивный
         */
        @Test
        public void createPositive() {
            Optional<BusinessCard> optionalBusinessCard = Optional.of(businessCard);
            success = dataProviderHibernate.create(optionalBusinessCard);
            assertTrue(success);
        }

        /**
         * чтение объекта по id
         * тип: позитивный
         */
/*        @Test
        public void readByIdPositive() {
            Optional<TestEntity> optionalTestEntity;
            Object object;
            optionalTestEntity = dataProviderHibernate.create(object);
            if(optionalTestEntity.isPresent()){
                optionalTestEntity = dataProviderHibernate.readById(optionalTestEntity.get().getId());
                assertNotEquals( Optional.empty(), optionalTestEntity);
            } else {
                fail();
            }
        }*/

        /**
         * чтение всех объектов
         * тип: позитивный
         */
/*        @Test
        public void readAllPositive() {
            Optional<List<TestEntity>> optionalTestEntityList;
            optionalTestEntityList = dataProviderHibernate.readAll();
            assertNotEquals( Optional.empty(), optionalTestEntityList);
        }*/


        /**
         * обновление объекта
         * тип: позитивный
         */
/*        @Test
        public void updatePositive() {
            TestEntity newTestEntity = new TestEntity(2L, "newInfo", "dfdfsdd", currentDate,
                    true, new PanchenkoComponent("dsddd", 223332, "sdsql"));
            success = dataProviderHibernate.update(newTestEntity);
            assertTrue(success);
        }*/

        /**
         * удаление объекта по id
         * тип: позитивный
         */

/*
        @Test
        public void deletePositive() {
            success = dataProviderHibernate.delete(6L);
            assertTrue(success);
        }
*/


        /**
         * чтение объекта по id
         * тип: негативный
         */
 //       @Test
/*        public void readByIdNegative() {
            Optional<TestEntity> optionalTestEntity;
            optionalTestEntity = dataProviderHibernate.readById(667L);
            assertEquals( Optional.empty(), optionalTestEntity);
        }*/


        /**
         * обновление объекта
         * тип: негативный
         */
/*
        @Test
        public void updateNegative() {
            success = dataProviderHibernate.update(null);
            assertFalse(success);
        }

    }*/

}
