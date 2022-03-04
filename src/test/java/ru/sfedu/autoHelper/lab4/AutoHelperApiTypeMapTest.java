package ru.sfedu.autoHelper.lab4;

import org.junit.Test;
import ru.sfedu.autoHelper.ConstantsValues;
import ru.sfedu.autoHelper.lab4.mapTypeMapping.User;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

import static org.junit.Assert.*;

public class AutoHelperApiTypeMapTest {
    IHibernateDataProvider dataProviderHibernate = new HibernateDataProvider();
    User user = new User();
    User newUser = new User();
    Map<String, String> infoCards;
    Map<String, String> newInfoCards;
    boolean success;

    /**
     * добавление объекта
     * тип: позитивный
     */
    @Test
    public void createPositive() {
        Optional<Object> optional;
        infoCards = new HashMap<>();
        infoCards.put(ConstantsValues.USER_INFO_CARD_FIRST, ConstantsValues.USER_INFO_CARD_FIRST_INFO);
        infoCards.put(ConstantsValues.USER_INFO_CARD_SECOND, ConstantsValues.USER_INFO_CARD_SECOND_INFO);
        infoCards.put(ConstantsValues.USER_INFO_CARD_THIRD, ConstantsValues.USER_INFO_CARD_THIRD_INFO);
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
        infoCards.put(ConstantsValues.USER_INFO_CARD_FIRST, ConstantsValues.USER_INFO_CARD_FIRST_INFO);
        infoCards.put(ConstantsValues.USER_INFO_CARD_SECOND, ConstantsValues.USER_INFO_CARD_SECOND_INFO);
        infoCards.put(ConstantsValues.USER_INFO_CARD_THIRD, ConstantsValues.USER_INFO_CARD_THIRD_INFO);
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
        infoCards.put(ConstantsValues.USER_INFO_CARD_FIRST, ConstantsValues.USER_INFO_CARD_FIRST_INFO);
        infoCards.put(ConstantsValues.USER_INFO_CARD_SECOND, ConstantsValues.USER_INFO_CARD_SECOND_INFO);
        infoCards.put(ConstantsValues.USER_INFO_CARD_THIRD, ConstantsValues.USER_INFO_CARD_THIRD_INFO);
        // create user which will be updated
        user.setInfoCards(infoCards);
        optional = dataProviderHibernate.create(user);
        // prepare values for update
        newInfoCards.put(ConstantsValues.USER_INFO_CARD_FIRST_NEW, ConstantsValues.USER_INFO_CARD_FIRST_NEW_INFO);
        newInfoCards.put(ConstantsValues.USER_INFO_CARD_SECOND_NEW, ConstantsValues.USER_INFO_CARD_SECOND_NEW_INFO);
        newInfoCards.put(ConstantsValues.USER_INFO_CARD_THIRD_NEW, ConstantsValues.USER_INFO_CARD_THIRD_NEW_INFO);
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
        infoCards.put(ConstantsValues.USER_INFO_CARD_FIRST, ConstantsValues.USER_INFO_CARD_FIRST_INFO);
        infoCards.put(ConstantsValues.USER_INFO_CARD_SECOND, ConstantsValues.USER_INFO_CARD_SECOND_INFO);
        infoCards.put(ConstantsValues.USER_INFO_CARD_THIRD, ConstantsValues.USER_INFO_CARD_THIRD_INFO);
        user.setInfoCards(infoCards);
        dataProviderHibernate.delete(user);
        // Для удаления по кокретному id
/*            user = new User(254L);
            dataProviderHibernate.delete(user);*/
    }

}
