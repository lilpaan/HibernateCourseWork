package ru.sfedu.autoHelper.lab3;

import org.junit.Test;
import ru.sfedu.autoHelper.lab3.joinedTable.DiscountCard;
import ru.sfedu.autoHelper.lab3.joinedTable.BusinessCard;

import java.util.List;
import java.util.Optional;

import static org.junit.Assert.*;

public class AutoHelperApiJoinedTableTest {
    IHibernateDataProvider dataProviderHibernate = new HibernateDataProvider();
    BusinessCard businessCard;
    DiscountCard discountCard;
    boolean businessSuccess;
    boolean discountSuccess;

        /**
         * добавление объекта
         * тип: позитивный
         */
        @Test
        public void createPositive() {
            businessCard = new BusinessCard("businessCompany", "companyDescription",
                    "businessPhoneNumber", "businessAddress", "businessEmail",
                    "businessSite");
            discountCard =
            new DiscountCard("discountCompany", "discountDescription", true, 12345,
                    true);
            Optional<BusinessCard> optionalBusinessCard = dataProviderHibernate.create(businessCard);
            Optional<DiscountCard> optionalDiscountCard = dataProviderHibernate.create(discountCard);
            assertNotEquals(Optional.empty(), optionalBusinessCard);
            assertNotEquals(Optional.empty(), optionalDiscountCard);

        }

        /**
         * чтение объекта по id
         * тип: позитивный
         */
        @Test
        public void readByIdPositive() {
            businessCard = new BusinessCard("businessCompany", "companyDescription",
                    "businessPhoneNumber", "businessAddress", "businessEmail",
                    "businessSite");
            discountCard =
                    new DiscountCard("discountCompany", "discountDescription", true, 12345,
                            true);
            Optional<BusinessCard> optionalBusinessCard = dataProviderHibernate.create(businessCard);
            Optional<DiscountCard> optionalDiscountCard = dataProviderHibernate.create(discountCard);
            if(optionalBusinessCard.isPresent() && (optionalDiscountCard.isPresent())){
                optionalBusinessCard
                        = dataProviderHibernate.readById(BusinessCard.class, optionalBusinessCard.get().getId());
                optionalDiscountCard
                        = dataProviderHibernate.readById(DiscountCard.class, optionalDiscountCard.get().getId());
                assertNotEquals( Optional.empty(), optionalBusinessCard);
                assertNotEquals( Optional.empty(), optionalDiscountCard);
            } else {
                fail();
            }
        }

        /**
         * обновление объекта
         * тип: позитивный
         */
        @Test
        public void updatePositive() {
            BusinessCard newBusinessCard = new BusinessCard(10L, "newBusinessCompany", "newCompanyDescription",
                    "newBusinessPhoneNumber", "newBusinessAddress", "newBusinessEmail",
                    "newBusinessSite");
            DiscountCard newDiscountCard =
                    new DiscountCard(11L, "newDiscountCompany", "newDiscountDescription", false, 54321,
                            false);
            businessSuccess = dataProviderHibernate.update(newBusinessCard);;
            discountSuccess = dataProviderHibernate.update(newDiscountCard);
            assertTrue(businessSuccess);
            assertTrue(discountSuccess);
        }

        /**
         * удаление объекта по id
         * тип: позитивный
         */
        @Test
        public void deletePositive() {
            businessCard = new BusinessCard(38L);
            discountCard = new DiscountCard(39L);
            businessSuccess = dataProviderHibernate.delete(businessCard);
            discountSuccess = dataProviderHibernate.delete(discountCard);
            assertTrue(businessSuccess);
            assertTrue(discountSuccess);
        }

}
