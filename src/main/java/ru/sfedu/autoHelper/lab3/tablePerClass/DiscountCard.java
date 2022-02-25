package ru.sfedu.autoHelper.lab3.tablePerClass;

import ru.sfedu.autoHelper.ConstantsValues;

import javax.persistence.Entity;
import java.util.Objects;

/**
 * Класс, наследующий объект InfoCard. Используется для добавления дисконтной карты пользователя
 */
@Entity(name = ConstantsValues.DISCOUNT_CARD_TABLE_PER_CLASS)
public class DiscountCard extends InfoCard {
    boolean nfc;
    int number;
    boolean barcode;

    /**
     * Конструктор для создания объекта дисконтной карты
     * @param id Идентификатор инфокарты
     * @param company Наименование компании. Параметр, переданный от родительского класса
     * @param description Описание к инфокарте. Передан из родительского класса
     * @param nfc Наличие nfc-чипа
     * @param number Номер дисконтной карты
     * @param barcode Наличие штрихкода
     */
    public DiscountCard(Long id, String company, String description, boolean nfc, int number, boolean barcode) {
        super(id, company, description);
        this.nfc = nfc;
        this.number = number;
        this.barcode = barcode;
    }

    public DiscountCard(boolean nfc, int number, boolean barcode) {
        this.nfc = nfc;
        this.number = number;
        this.barcode = barcode;
    }

    public DiscountCard() {
    }

    public boolean isNfc() {
        return nfc;
    }

    public void setNfc(boolean nfc) {
        this.nfc = nfc;
    }

    public int getNumber() {
        return number;
    }

    public void setNumber(int number) {
        this.number = number;
    }

    public boolean isBarcode() {
        return barcode;
    }

    public void setBarcode(boolean barcode) {
        this.barcode = barcode;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;
        DiscountCard that = (DiscountCard) o;
        return nfc == that.nfc && number == that.number && barcode == that.barcode;
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), nfc, number, barcode);
    }

    @Override
    public String toString() {
        return "DiscountCard{" +
                "nfc=" + nfc +
                ", number=" + number +
                ", barcode=" + barcode +
                ", id=" + id +
                ", company='" + company + '\'' +
                ", description='" + description + '\'' +
                '}';
    }

}