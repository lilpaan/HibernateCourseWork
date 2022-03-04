package ru.sfedu.autoHelper.lab3;

import org.junit.Test;
import ru.sfedu.autoHelper.lab3.tablePerClass.BusinessCard;
import ru.sfedu.autoHelper.lab3.tablePerClass.DiscountCard;

import java.util.Optional;

import static org.junit.Assert.*;
import static org.junit.Assert.assertTrue;

public class AutoHelperApiTablePerClassTest {
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
                "businessPhoneNumber", "businessAddress", "businessEmail", "businessSite");
        discountCard = new DiscountCard("discountCompany", "discountDescription", true,
                12345, true);
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
                "businessPhoneNumber", "businessAddress", "businessEmail", "businessSite");
        discountCard = new DiscountCard("discountCompany", "discountDescription", true,
                12345, true);
        Optional<BusinessCard> optionalBusinessCard = dataProviderHibernate.create(businessCard);
        Optional<DiscountCard> optionalDiscountCard = dataProviderHibernate.create(discountCard);
        if(optionalBusinessCard.isPresent() && (optionalDiscountCard.isPresent())) {
            optionalBusinessCard = dataProviderHibernate.readById(BusinessCard.class, optionalBusinessCard.get()
                    .getId());
            optionalDiscountCard = dataProviderHibernate.readById(DiscountCard.class, optionalDiscountCard.get()
                    .getId());
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
        BusinessCard businessCardForUpdate = new BusinessCard("businessCompany", "companyDescription",
                "businessPhoneNumber", "businessAddress", "businessEmail", "businessSite");
        DiscountCard discountCardForUpdate = new DiscountCard("discountCompany", "discountDescription",
                true, 12345, true);
        BusinessCard newBusinessCard = new BusinessCard( "newBusinessCompany",
                "newCompanyDescription", "newBusinessPhoneNumber", "newBusinessAddress",
                "newBusinessEmail", "newBusinessSite");
        DiscountCard newDiscountCard = new DiscountCard("newDiscountCompany",
                "newDiscountDescription", false, 54321, false);
        Optional<BusinessCard> optionalBusinessCard = dataProviderHibernate.create(businessCardForUpdate);
        Optional<DiscountCard> optionalDiscountCard = dataProviderHibernate.create(discountCardForUpdate);
        if(optionalBusinessCard.isPresent() && optionalDiscountCard.isPresent()) {
            newBusinessCard.setId(optionalBusinessCard.get().getId());
            newDiscountCard.setId(optionalDiscountCard.get().getId());
            businessSuccess = dataProviderHibernate.update(newBusinessCard);
            discountSuccess = dataProviderHibernate.update(newDiscountCard);
        }
        else {
            fail();
        }
        assertTrue(businessSuccess);
        assertTrue(discountSuccess);
    }

    /**
     * удаление объекта по id
     * тип: позитивный
     */
    @Test
    public void deletePositive() {
        BusinessCard businessCardForDelete = new BusinessCard("businessCompany", "companyDescription",
                "businessPhoneNumber", "businessAddress", "businessEmail", "businessSite");
        DiscountCard discountCardForDelete = new DiscountCard("discountCompany", "discountDescription",
                true, 12345, true);
        Optional<BusinessCard> optionalBusinessCard = dataProviderHibernate.create(businessCardForDelete);
        Optional<DiscountCard> optionalDiscountCard = dataProviderHibernate.create(discountCardForDelete);
        if(optionalBusinessCard.isPresent() && optionalDiscountCard.isPresent()) {
            businessSuccess = dataProviderHibernate.delete(new BusinessCard(optionalBusinessCard.get().getId()));
            discountSuccess = dataProviderHibernate.delete(new DiscountCard(optionalDiscountCard.get().getId()));
        } else {
            fail();
        }
        // для удаления по конкретным id
/*        businessCard = new BusinessCard(49L);
        discountCard = new DiscountCard(50L);
        businessSuccess = dataProviderHibernate.delete(businessCard);
        discountSuccess = dataProviderHibernate.delete(discountCard);*/
        assertTrue(businessSuccess);
        assertTrue(discountSuccess);
    }

}
