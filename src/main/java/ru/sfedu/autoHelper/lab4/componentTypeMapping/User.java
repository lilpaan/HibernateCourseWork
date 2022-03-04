package ru.sfedu.autoHelper.lab4.componentTypeMapping;

import ru.sfedu.autoHelper.ConstantsValues;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

@Entity(name = ConstantsValues.USER_COMPONENT_TYPE)
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    Long id;
    @ElementCollection
    @CollectionTable(name = ConstantsValues.INFO_CARD_COMPONENT)
    @AttributeOverride(
            name = ConstantsValues.INFO_CARD_DESCRIPTION,
            column = @Column(name = ConstantsValues.INFO_CARD_COMPONENT_DESCRIPTION, nullable = false)
    )
    protected Set<InfoCard> infoCards = new HashSet<>();

    public User(Long id, Set<InfoCard> infoCards) {
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

    public Set<InfoCard> getInfoCards() {
        return infoCards;
    }

    public void setInfoCards(Set<InfoCard> infoCards) {
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
