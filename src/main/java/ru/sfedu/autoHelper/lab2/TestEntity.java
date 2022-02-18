package ru.sfedu.autoHelper.lab2;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

@Entity
public class TestEntity implements Serializable {
    @Id
    @GeneratedValue
    long id;
    String name;
    String description;
    Date dateCreated;
    Boolean check;
    @Embedded
    PanchenkoComponent panchenkoComponent;

    public TestEntity() {
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Date getDateCreated() {
        return dateCreated;
    }

    public void setDateCreated(Date dateCreated) {
        this.dateCreated = dateCreated;
    }

    public Boolean getCheck() {
        return check;
    }

    public void setCheck(Boolean check) {
        this.check = check;
    }

    public PanchenkoComponent getPanchenkoComponent() {
        return panchenkoComponent;
    }

    public void setPanchenkoComponent(PanchenkoComponent panchenkoComponent) {
        this.panchenkoComponent = panchenkoComponent;
    }
}
