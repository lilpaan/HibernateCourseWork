package ru.sfedu.autoHelper.lab4;

import org.junit.Test;
import ru.sfedu.autoHelper.ConstantsValues;
import ru.sfedu.autoHelper.lab4.setTypeMapping.User;

import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

import static org.junit.Assert.*;

public class AutoHelperApiTypeSetTest {
    IHibernateDataProvider dataProviderHibernate = new HibernateDataProvider();
    User user = new User();
    User newUser = new User();
    Set<String> infoCards;
    Set<String> newInfoCards;
    boolean success;

        /**
         * добавление объекта
         * тип: позитивный
         */
        @Test
        public void createPositive() {
            Optional<Object> optional;
            infoCards = new HashSet<>();
            infoCards.add(ConstantsValues.USER_INFO_CARD_FIRST);
            infoCards.add(ConstantsValues.USER_INFO_CARD_SECOND);
            infoCards.add(ConstantsValues.USER_INFO_CARD_THIRD);
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
            infoCards = new HashSet<>();
            infoCards.add(ConstantsValues.USER_INFO_CARD_FIRST);
            infoCards.add(ConstantsValues.USER_INFO_CARD_SECOND);
            infoCards.add(ConstantsValues.USER_INFO_CARD_THIRD);
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
            infoCards = new HashSet<>();
            newInfoCards = new HashSet<>();
            // create values before update
            infoCards.add(ConstantsValues.USER_INFO_CARD_FIRST);
            infoCards.add(ConstantsValues.USER_INFO_CARD_SECOND);
            infoCards.add(ConstantsValues.USER_INFO_CARD_THIRD);
            // create user which will be updated
            user.setInfoCards(infoCards);
            optional = dataProviderHibernate.create(user);
            // prepare values for update
            newInfoCards.add(ConstantsValues.USER_INFO_CARD_FIRST_NEW);
            newInfoCards.add(ConstantsValues.USER_INFO_CARD_SECOND_NEW);
            newInfoCards.add(ConstantsValues.USER_INFO_CARD_THIRD_NEW);
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
            infoCards = new HashSet<>();
            infoCards.add(ConstantsValues.USER_INFO_CARD_FIRST);
            infoCards.add(ConstantsValues.USER_INFO_CARD_SECOND);
            infoCards.add(ConstantsValues.USER_INFO_CARD_THIRD);
            user.setInfoCards(infoCards);
            dataProviderHibernate.delete(user);
            // Для удаления по кокретному id
/*            user = new User(213L);
            dataProviderHibernate.delete(user);*/
        }

}
