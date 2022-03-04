package ru.sfedu.autoHelper.lab4.mapTypeMapping;

import ru.sfedu.autoHelper.ConstantsValues;

import javax.persistence.*;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

@Entity(name = ConstantsValues.USER_MAP_TYPE)
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    Long id;
    @ElementCollection
    @CollectionTable(name = ConstantsValues.INFO_CARD_MAP)
    @MapKeyColumn(name = ConstantsValues.INFO_CARD_KEY_COLUMN)
    @Column(name = ConstantsValues.INFO_CARDS)
    protected Map<String, String> infoCards = new HashMap<String, String>();

    public User(Long id, Map<String, String> infoCards) {
        this.id = id;
        this.infoCards = infoCards;
    }

    public User(Long id) {
        this.id = id;
    }

    public User() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Map<String, String> getInfoCards() {
        return infoCards;
    }

    public void setInfoCards(Map<String, String> infoCards) {
        this.infoCards = infoCards;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        User user = (User) o;
        return Objects.equals(id, user.id) && Objects.equals(infoCards, user.infoCards);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, infoCards);
    }

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", infoCards=" + infoCards +
                '}';
    }
}
