package ru.sfedu.autoHelper.entity;

import com.opencsv.bean.CsvBindByName;
import ru.sfedu.autoHelper.ConstantsValues;

import javax.xml.bind.annotation.XmlRootElement;
import java.util.ArrayList;
import java.util.Objects;


/**
 * Класс для связки конкретного пользователя с его автомобилем и инфокартами
 */
@XmlRootElement
public class User {
    @CsvBindByName(column = ConstantsValues.USER_INFO_CARD)
    ArrayList<InfoCard> infoCardArrayList;
    @CsvBindByName(column = ConstantsValues.USER_CAR)
    Car car;

    /**
     * Конструктор для возможности добавления пользователя
     * @param infoCardArrayList список, содержащий все инфокарточки
     * @param car автомобиль пользователя
     */
    public User(ArrayList<InfoCard> infoCardArrayList, Car car) {
        this.infoCardArrayList = infoCardArrayList;
        this.car = car;
    }

    public ArrayList<InfoCard> getCardArrayList() {
        return infoCardArrayList;
    }

    public void setCardArrayList(ArrayList<InfoCard> infoCardArrayList) {
        this.infoCardArrayList = infoCardArrayList;
    }

    public Car getCar() {
        return car;
    }

    public void setCar(Car car) {
        this.car = car;
    }

    @Override
    public String toString() {
        return "User{" +
                "infoCardArrayList=" + infoCardArrayList +
                ", car=" + car +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        User user = (User) o;
        return Objects.equals(infoCardArrayList, user.infoCardArrayList) && Objects.equals(car, user.car);
    }

    @Override
    public int hashCode() {
        return Objects.hash(infoCardArrayList, car);
    }

}
