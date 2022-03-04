package ru.sfedu.autoHelper.lab4.listTypeMapping;

import ru.sfedu.autoHelper.ConstantsValues;

import javax.persistence.*;
import java.util.*;

@Entity(name = ConstantsValues.USER_LIST_TYPE)
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    Long id;
    @ElementCollection
    @CollectionTable(name = ConstantsValues.INFO_CARD_LIST)
    @OrderColumn
    @Column(name = ConstantsValues.INFO_CARDS)
    protected List<String> infoCards = new ArrayList<String>();

    public User(Long id, List<String> infoCards) {
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

    public List<String> getInfoCards() {
        return infoCards;
    }

    public void setInfoCards(List<String> infoCards) {
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
