package ru.sfedu.autoHelper.lab4.setTypeMapping;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

@Entity
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    Long id;
    @ElementCollection
    protected Set<String> infoCards = new HashSet<String>();

    public User() {
    }

    public User(Long id) {
        this.id = id;
    }

    public User(Long id, Set<String> infoCards) {
        this.id = id;
        this.infoCards = infoCards;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Set<String> getInfoCards() {
        return infoCards;
    }

    public void setInfoCards(Set<String> infoCards) {
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
