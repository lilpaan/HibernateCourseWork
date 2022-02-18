package ru.sfedu.autoHelper.entity;

import com.opencsv.bean.CsvBindByName;
import com.opencsv.bean.CsvRecurse;
import ru.sfedu.autoHelper.ConstantsValues;
import ru.sfedu.autoHelper.entity.enums.TypeOfTransmission;

import javax.xml.bind.annotation.XmlRootElement;
import java.util.Objects;

/**
 * Класс, задействованный при добавлении автомобиля пользователя
 * Включает возможность добавления запчасти(зависит от параметра SpareAddRequest)
 */
@XmlRootElement
public class Car {
    @CsvBindByName(column = ConstantsValues.CAR_MODEL)
    String model;
    @CsvBindByName(column = ConstantsValues.CAR_MAKER)
    String maker;
    @CsvBindByName(column = ConstantsValues.CAR_ENGINE_VOLUME)
    float engineVolume;
    @CsvBindByName(column = ConstantsValues.CAR_TRANSMISSION)
    TypeOfTransmission transmission;
    @CsvBindByName(column = ConstantsValues.CAR_COLOR)
    String color;
    @CsvBindByName(column = ConstantsValues.CAR_PRODUCTION_YEAR)
    int productionYear;
    @CsvRecurse
    SparePart sparePart;
    @CsvRecurse
    CarProperties carProperties;

    /**
     * Конструктор для создания объекта автомобиля
     * @param model Модель автомобиля
     * @param maker Страна-производитель
     * @param engineVolume Объём двигателя
     * @param transmission Тип трансмиссии. Выбор типа происходит из enum TypeOfTransmission
     * @param color Цвет автомобиля
     * @param productionYear Год выпуска
     * @param carProperties Объект, содержащий специфичные характеристики автомобиля
     */
    public Car(String model, String maker, Float engineVolume, TypeOfTransmission transmission, String color,
               int productionYear, CarProperties carProperties) {
        this.model = model;
        this.maker = maker;
        this.engineVolume = engineVolume;
        this.transmission = transmission;
        this.color = color;
        this.productionYear = productionYear;
        this.carProperties = carProperties;
    }

    public Car() {
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

    public Float getEngineVolume() {
        return engineVolume;
    }

    public void setEngineVolume(Float engineVolume) {
        this.engineVolume = engineVolume;
    }

    public TypeOfTransmission getTransmission() {
        return transmission;
    }

    public void setTransmission(TypeOfTransmission transmission) {
        this.transmission = transmission;
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

    public SparePart getSparePart() {
        return sparePart;
    }

    public void setSparePart(SparePart sparePart) {
        this.sparePart = sparePart;
    }

    public CarProperties getCarProperties() {
        return carProperties;
    }

    public void setCarProperties(CarProperties carProperties) {
        this.carProperties = carProperties;
    }

    @Override
    public String toString() {
        return "Car{" +
                "model='" + model + '\'' +
                ", maker='" + maker + '\'' +
                ", engineVolume=" + engineVolume +
                ", transmission=" + transmission +
                ", color='" + color + '\'' +
                ", productionYear=" + productionYear +
                ", sparePart=" + sparePart +
                ", carProperties=" + carProperties +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Car car = (Car) o;
        return Objects.equals(model, car.model) && Objects.equals(maker, car.maker) && Objects.equals(engineVolume, car.engineVolume) && transmission == car.transmission && Objects.equals(color, car.color) && Objects.equals(productionYear, car.productionYear) && Objects.equals(sparePart, car.sparePart) && Objects.equals(carProperties, car.carProperties);
    }

    @Override
    public int hashCode() {
        return Objects.hash(model, maker, engineVolume, transmission, color, productionYear, sparePart, carProperties);
    }

}