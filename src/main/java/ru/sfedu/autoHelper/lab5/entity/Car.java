package ru.sfedu.autoHelper.lab5.entity;

import org.apache.logging.log4j.core.tools.Generate;
import ru.sfedu.autoHelper.ConstantsValues;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

@Entity(name = ConstantsValues.CAR_TABLE_LAB5)
public class Car {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    Long id;
    @OneToMany(mappedBy = ConstantsValues.CAR, fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    protected Set<SparePart> spareParts = new HashSet<>();
    String model;
    String maker;
    float engineVolume;
    String color;
    int productionYear;
    @OneToOne(fetch = FetchType.LAZY, optional = false, cascade = CascadeType.ALL)
    @JoinColumn(unique = true)
    protected CarProperties carProperties;

    public Car() {
    }

    public Car(String model, String maker, float engineVolume, String color, int productionYear, CarProperties carProperties) {
        this.model = model;
        this.maker = maker;
        this.engineVolume = engineVolume;
        this.color = color;
        this.productionYear = productionYear;
        this.carProperties = carProperties;
    }

    public Car(Set<SparePart> spareParts, String model, String maker, float engineVolume, String color, int productionYear, CarProperties carProperties) {
        this.spareParts = spareParts;
        this.model = model;
        this.maker = maker;
        this.engineVolume = engineVolume;
        this.color = color;
        this.productionYear = productionYear;
        this.carProperties = carProperties;
    }

    public Car(Long id, Set<SparePart> spareParts, String model, String maker, float engineVolume, String color, int productionYear, CarProperties carProperties) {
        this.id = id;
        this.spareParts = spareParts;
        this.model = model;
        this.maker = maker;
        this.engineVolume = engineVolume;
        this.color = color;
        this.productionYear = productionYear;
        this.carProperties = carProperties;
    }

    public Car(Long id) {
        this.id = id;
    }

    public Car(String model, String maker, float engineVolume, String color, int productionYear) {
        this.model = model;
        this.maker = maker;
        this.engineVolume = engineVolume;
        this.color = color;
        this.productionYear = productionYear;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Set<SparePart> getSpareParts() {
        return spareParts;
    }

    public void setSpareParts(Set<SparePart> spareParts) {
        this.spareParts = spareParts;
    }

    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public String getMaker() {
        return maker;
    }

    public void setMaker(String maker) {
        this.maker = maker;
    }

    public float getEngineVolume() {
        return engineVolume;
    }

    public void setEngineVolume(float engineVolume) {
        this.engineVolume = engineVolume;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public int getProductionYear() {
        return productionYear;
    }

    public void setProductionYear(int productionYear) {
        this.productionYear = productionYear;
    }

    public CarProperties getCarProperties() {
        return carProperties;
    }

    public void setCarProperties(CarProperties carProperties) {
        this.carProperties = carProperties;
    }

}
