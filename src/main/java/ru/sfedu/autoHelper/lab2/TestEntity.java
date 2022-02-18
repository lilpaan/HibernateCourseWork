package ru.sfedu.autoHelper.lab2;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

@Entity
public class TestEntity implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "test_entity_id")
    private Long id;
    @Column(name = "test_entity_name")
    private String name;
    private String description;
    private Date dateCreated;
    @Column(name = "test_entity_check")
    private Boolean check;
    @Embedded
    private PanchenkoComponent panchenkoComponent;

    public TestEntity() {
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
