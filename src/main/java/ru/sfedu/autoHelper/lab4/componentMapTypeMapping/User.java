package ru.sfedu.autoHelper.lab4.componentMapTypeMapping;

import ru.sfedu.autoHelper.ConstantsValues;

import javax.persistence.*;
import java.util.HashMap;
import java.util.Map;

@Entity(name = ConstantsValues.USER_MAP_COMPONENT_TYPE)
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    Long id;
    @ElementCollection
    @CollectionTable(name = ConstantsValues.INFO_CARD_MAP_COMPONENT)
    @MapKeyColumn(name = ConstantsValues.INFO_CARDS)
    protected Map<String, InfoCard> childClasses = new HashMap<String, InfoCard>();

    public User() {
    }

    public User(Long id) {
        this.id = id;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Map<String, InfoCard> getChildClasses() {
        return childClasses;
    }

    public void setChildClasses(Map<String, InfoCard> childClasses) {
        this.childClasses = childClasses;
    }
}
