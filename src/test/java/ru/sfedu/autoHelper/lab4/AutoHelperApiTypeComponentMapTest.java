package ru.sfedu.autoHelper.lab4;

import org.junit.Test;
import ru.sfedu.autoHelper.ConstantsValues;
import ru.sfedu.autoHelper.lab4.componentMapTypeMapping.InfoCard;
import ru.sfedu.autoHelper.lab4.componentMapTypeMapping.User;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

import static org.junit.Assert.*;

public class AutoHelperApiTypeComponentMapTest {
    IHibernateDataProvider dataProviderHibernate = new HibernateDataProvider();
    User user = new User();
    User newUser = new User();
    InfoCard infoCardFirst = new InfoCard("companyFirst", "descriptionFirst");
    InfoCard infoCardSecond = new InfoCard("companySecond", "descriptionSecond");
    InfoCard infoCardThird = new InfoCard("companyThird", "descriptionThird");
    InfoCard infoCardFirstNew = new InfoCard("companyFirstNEW", "descriptionFirstNEW");
    InfoCard infoCardSecondNew = new InfoCard("companySecondNEW", "descriptionSecondNEW");
    InfoCard infoCardThirdNew = new InfoCard("companyThirdNEW", "descriptionThirdNEW");
    Map<String, InfoCard> infoCards;
    Map<String, InfoCard> newInfoCards;
    boolean success;

    /**
     * добавление объекта
     * тип: позитивный
     */
    @Test
    public void createPositive() {
        Optional<Object> optional;
        infoCards = new HashMap<>();
        infoCards.put(ConstantsValues.USER_INFO_CARD_FIRST, infoCardFirst);
        infoCards.put(ConstantsValues.USER_INFO_CARD_SECOND, infoCardSecond);
        infoCards.put(ConstantsValues.USER_INFO_CARD_THIRD, infoCardThird);
        user.setInfoCards(infoCards);
        optional = dataProviderHibernate.create(user);
        assertNotEquals(Optional.empty(), optional);
    }

    /**
     * чтение объекта по id
     * тип: позитивный
    */
    @Test
    public void readByIdPositive() {
        Optional<User> optional;
        infoCards = new HashMap<>();
        infoCards.put(ConstantsValues.USER_INFO_CARD_FIRST, infoCardFirst);
        infoCards.put(ConstantsValues.USER_INFO_CARD_SECOND, infoCardSecond);
        infoCards.put(ConstantsValues.USER_INFO_CARD_THIRD, infoCardThird);
        user.setInfoCards(infoCards);
        optional = dataProviderHibernate.create(user);
        if (optional.isPresent()) {
            Optional<User> optionalUser = dataProviderHibernate.readById(User.class, optional.get().getId());
            assertNotEquals(Optional.empty(), optionalUser);
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
        Optional<User> optional;
        infoCards = new HashMap<>();
        newInfoCards = new HashMap<>();
        // create values before update
        infoCards.put(ConstantsValues.USER_INFO_CARD_FIRST, infoCardFirst);
        infoCards.put(ConstantsValues.USER_INFO_CARD_SECOND, infoCardSecond);
        infoCards.put(ConstantsValues.USER_INFO_CARD_THIRD, infoCardThird);
        // create user which will be updated
        user.setInfoCards(infoCards);
        optional = dataProviderHibernate.create(user);
        // prepare values for update
        newInfoCards.put(ConstantsValues.USER_INFO_CARD_FIRST_NEW, infoCardFirstNew);
        newInfoCards.put(ConstantsValues.USER_INFO_CARD_SECOND_NEW, infoCardSecondNew);
        newInfoCards.put(ConstantsValues.USER_INFO_CARD_THIRD_NEW, infoCardThirdNew);
        newUser.setInfoCards(newInfoCards);
        // updating
        if (optional.isPresent()) {
            newUser.setId(optional.get().getId());
            success = dataProviderHibernate.update(newUser);
        }
        assertTrue(success);
    }

    /**
     * удаление объекта
     * тип: позитивный
    */
    @Test
    public void deletePositive() {
        infoCards = new HashMap<>();
        infoCards.put(ConstantsValues.USER_INFO_CARD_FIRST, infoCardFirst);
        infoCards.put(ConstantsValues.USER_INFO_CARD_SECOND, infoCardSecond);
        infoCards.put(ConstantsValues.USER_INFO_CARD_THIRD, infoCardThird);
        user.setInfoCards(infoCards);
        dataProviderHibernate.delete(user);
        // Для удаления по кокретному id
/*            user = new User(254L);
            dataProviderHibernate.delete(user);*/
    }

}
