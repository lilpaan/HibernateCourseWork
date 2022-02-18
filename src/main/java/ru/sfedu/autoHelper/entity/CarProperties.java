package ru.sfedu.autoHelper.entity;

import com.opencsv.bean.CsvBindByName;
import ru.sfedu.autoHelper.ConstantsValues;

import javax.xml.bind.annotation.XmlRootElement;
import java.util.Objects;

/**
 * Класс для добавления специфичных характеристик автомобиля
 */
@XmlRootElement
public class CarProperties {
    @CsvBindByName(column = ConstantsValues.CAR_PROPERTIES_FUEL_AMOUNT)
    float fuelAmount;
    @CsvBindByName(column = ConstantsValues.CAR_PROPERTIES_CURRENT_OIL_BRAND)
    String currentOilBrand;
    @CsvBindByName(column = ConstantsValues.CAR_PROPERTIES_WHEELS_TYPE)
    String wheelsType;
    @CsvBindByName(column = ConstantsValues.CAR_PROPERTIES_INSURANCE_RUN_OUT_DATE)
    String insuranceRunOutDate;

    /**
     * Конструктор для добавления характеристик
     * @param fuelAmount Средний расход топлива(заполняется пользователем)
     * @param currentOilBrand Текущий бренд используемого автомобильного масла
     * @param wheelsType Тип установленной резины
     * @param insuranceRunOutDate Дата окончания страховки. Используется warnInsuranceRunOut, если необходимо
     */
    public CarProperties(Float fuelAmount, String currentOilBrand, String wheelsType, String insuranceRunOutDate) {
        this.fuelAmount = fuelAmount;
        this.currentOilBrand = currentOilBrand;
        this.wheelsType = wheelsType;
        this.insuranceRunOutDate = insuranceRunOutDate;
    }

    public CarProperties() {
    }

    public Float getFuelAmount() {
        return fuelAmount;
    }

    public void setFuelAmount(Float fuelAmount) {
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
    public String toString() {
        return "CarProperties{" +
                "fuelAmount=" + fuelAmount +
                ", currentOilBrand='" + currentOilBrand + '\'' +
                ", wheelsType='" + wheelsType + '\'' +
                ", insuranceRunOutDate=" + insuranceRunOutDate +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        CarProperties that = (CarProperties) o;
        return Objects.equals(fuelAmount, that.fuelAmount) && Objects.equals(currentOilBrand, that.currentOilBrand)
                && Objects.equals(wheelsType, that.wheelsType)
                && Objects.equals(insuranceRunOutDate, that.insuranceRunOutDate);
    }

    @Override
    public int hashCode() {
        return Objects.hash(fuelAmount, currentOilBrand, wheelsType, insuranceRunOutDate);
    }

}