package ru.sfedu.autoHelper.lab5.entity;

import ru.sfedu.autoHelper.ConstantsValues;

import javax.persistence.*;
import java.util.Objects;

@Entity(name = ConstantsValues.SPARE_PART_TABLE_LAB5)
public class SparePart {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    Long id;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = ConstantsValues.CAR_ID_TITLE, nullable = false)
    protected Car car;
    String type;
    String maker;
    String installationDate;

    public SparePart(String type, String maker, String installationDate) {
        this.type = type;
        this.maker = maker;
        this.installationDate = installationDate;
    }

    public SparePart() {
    }

    public SparePart(Long id, String type, String maker, String installationDate) {
        this.id = id;
        this.type = type;
        this.maker = maker;
        this.installationDate = installationDate;
    }

    public SparePart(Long id, Car car, String type, String maker, String installationDate) {
        this.id = id;
        this.car = car;
        this.type = type;
        this.maker = maker;
        this.installationDate = installationDate;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Car getCar() {
        return car;
    }

    public void setCar(Car car) {
        this.car = car;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getMaker() {
        return maker;
    }

    public void setMaker(String maker) {
        this.maker = maker;
    }

    public String getInstallationDate() {
        return installationDate;
    }

    public void setInstallationDate(String installationDate) {
        this.installationDate = installationDate;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        SparePart sparePart = (SparePart) o;
        return Objects.equals(id, sparePart.id) && Objects.equals(car, sparePart.car) && Objects.equals(type, sparePart.type) && Objects.equals(maker, sparePart.maker) && Objects.equals(installationDate, sparePart.installationDate);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, car, type, maker, installationDate);
    }

    @Override
    public String toString() {
        return "SparePart{" +
                "id=" + id +
                ", car=" + car +
                ", type='" + type + '\'' +
                ", maker='" + maker + '\'' +
                ", installationDate='" + installationDate + '\'' +
                '}';
    }

}
