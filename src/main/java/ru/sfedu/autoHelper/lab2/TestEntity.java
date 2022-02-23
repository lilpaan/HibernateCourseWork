package ru.sfedu.autoHelper.lab2;

import ru.sfedu.autoHelper.ConstantsValues;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

@Entity(name = ConstantsValues.TEST_ENTITY)
public class TestEntity implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = ConstantsValues.COLUMN_TEST_ENTITY_ID)
    private Long id;
    @Column(name = ConstantsValues.COLUMN_TEST_ENTITY_NAME)
    private String name;
    private String description;
    private Date dateCreated;
    @Column(name = ConstantsValues.COLUMN_TEST_ENTITY_CHECK)
    private Boolean check;
    @Embedded
    private PanchenkoComponent panchenkoComponent;

    public TestEntity() {
    }

    public TestEntity(Long id) {
        this.id = id;
    }

    public TestEntity(String name, String description, Date dateCreated, Boolean check, PanchenkoComponent panchenkoComponent) {
        this.name = name;
        this.description = description;
        this.dateCreated = dateCreated;
        this.check = check;
        this.panchenkoComponent = panchenkoComponent;
    }

    public TestEntity(Long id, String name, String description, Date dateCreated, Boolean check, PanchenkoComponent panchenkoComponent) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.dateCreated = dateCreated;
        this.check = check;
        this.panchenkoComponent = panchenkoComponent;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
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
