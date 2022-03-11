package ru.sfedu.autoHelper.lab5.entity;

import com.sun.istack.NotNull;
import ru.sfedu.autoHelper.ConstantsValues;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.util.Objects;

@Entity(name = ConstantsValues.CAR_PROPERTIES_TABLE_LAB5)
public class CarProperties {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    Long id;
    float fuelAmount;
    String currentOilBrand;
    String wheelsType;
    String insuranceRunOutDate;

    public CarProperties() {
    }

    public CarProperties(Long id, float fuelAmount, String currentOilBrand, String wheelsType, String insuranceRunOutDate) {
        this.id = id;
        this.fuelAmount = fuelAmount;
        this.currentOilBrand = currentOilBrand;
        this.wheelsType = wheelsType;
        this.insuranceRunOutDate = insuranceRunOutDate;
    }

    public CarProperties(float fuelAmount, String currentOilBrand, String wheelsType, String insuranceRunOutDate) {
        this.fuelAmount = fuelAmount;
        this.currentOilBrand = currentOilBrand;
        this.wheelsType = wheelsType;
        this.insuranceRunOutDate = insuranceRunOutDate;
    }

    public CarProperties(Long id) {
        this.id = id;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public float getFuelAmount() {
        return fuelAmount;
    }

    public void setFuelAmount(float fuelAmount) {
        this.fuelAmount = fuelAmount;
    }

    public String getCurrentOilBrand() {
        return currentOilBrand;
    }

    public void setCurrentOilBrand(String currentOilBrand) {
        this.currentOilBrand = currentOilBrand;
    }

    public String getWheelsType() {
        return wheelsType;
    }

    public void setWheelsType(String wheelsType) {
        this.wheelsType = wheelsType;
    }

    public String getInsuranceRunOutDate() {
        return insuranceRunOutDate;
    }

    public void setInsuranceRunOutDate(String insuranceRunOutDate) {
        this.insuranceRunOutDate = insuranceRunOutDate;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        CarProperties that = (CarProperties) o;
        return Float.compare(that.fuelAmount, fuelAmount) == 0 && Objects.equals(id, that.id) && Objects.equals(currentOilBrand, that.currentOilBrand) && Objects.equals(wheelsType, that.wheelsType) && Objects.equals(insuranceRunOutDate, that.insuranceRunOutDate);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, fuelAmount, currentOilBrand, wheelsType, insuranceRunOutDate);
    }

    @Override
    public String toString() {
        return "CarProperties{" +
                "id=" + id +
                ", fuelAmount=" + fuelAmount +
                ", currentOilBrand='" + currentOilBrand + '\'' +
                ", wheelsType='" + wheelsType + '\'' +
                ", insuranceRunOutDate='" + insuranceRunOutDate + '\'' +
                '}';
    }

}
