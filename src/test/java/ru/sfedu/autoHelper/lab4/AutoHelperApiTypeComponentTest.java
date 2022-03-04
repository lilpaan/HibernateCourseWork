package ru.sfedu.autoHelper.lab4;

import org.junit.Test;
import ru.sfedu.autoHelper.lab4.componentTypeMapping.InfoCard;
import ru.sfedu.autoHelper.lab4.componentTypeMapping.User;

import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

import static org.junit.Assert.*;

public class AutoHelperApiTypeComponentTest {
    IHibernateDataProvider dataProviderHibernate = new HibernateDataProvider();
    User user = new User();
    User newUser = new User();
    InfoCard firstInfoCard = new InfoCard("firstCompany", "firstDescription");
    InfoCard secondInfoCard = new InfoCard("secondCompany", "secondDescription");
    InfoCard thirdInfoCard = new InfoCard("thirdCompany", "thirdDescription");
    InfoCard firstInfoCardNew = new InfoCard("firstCompanyNEW", "firstDescriptionNEW");
    InfoCard secondInfoCardNew = new InfoCard("secondCompanyNEW", "secondDescriptionNEW");
    InfoCard thirdInfoCardNew = new InfoCard("thirdCompanyNEW", "thirdDescriptionNEW");
    Set<InfoCard> infoCards;
    Set<InfoCard> newInfoCards;
    boolean success;

    /**
     * добавление объекта
     * тип: позитивный
     */
    @Test
    public void createPositive() {
        Optional<Object> optional;
        infoCards = new HashSet<>();
        infoCards.add(firstInfoCard);
        infoCards.add(secondInfoCard);
        infoCards.add(thirdInfoCard);
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
        infoCards.add(firstInfoCard);
        infoCards.add(secondInfoCard);
        infoCards.add(thirdInfoCard);
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
        infoCards.add(firstInfoCard);
        infoCards.add(secondInfoCard);
        infoCards.add(thirdInfoCard);
        // create user which will be updated
        user.setInfoCards(infoCards);
        optional = dataProviderHibernate.create(user);
        // prepare values for update
        newInfoCards.add(firstInfoCardNew);
        newInfoCards.add(secondInfoCardNew);
        newInfoCards.add(thirdInfoCardNew);
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
        infoCards.add(firstInfoCard);
        infoCards.add(secondInfoCard);
        infoCards.add(thirdInfoCard);
        user.setInfoCards(infoCards);
        dataProviderHibernate.delete(user);
        // Для удаления по кокретному id
/*            user = new User(213L);
            dataProviderHibernate.delete(user);*/
    }

}
