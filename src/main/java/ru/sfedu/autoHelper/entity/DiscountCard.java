package ru.sfedu.autoHelper.entity;

import com.opencsv.bean.CsvBindByName;
import ru.sfedu.autoHelper.ConstantsValues;

import javax.xml.bind.annotation.XmlRootElement;
import java.util.Objects;

/**
 * Класс, наследующий объект InfoCard. Используется для добавления дисконтной карты пользователя
 */
@XmlRootElement
public class DiscountCard extends InfoCard{
    @CsvBindByName(column = ConstantsValues.INFO_CARD_NFC)
    boolean nfc;
    @CsvBindByName(column = ConstantsValues.INFO_CARD_NUMBER)
    int number;
    @CsvBindByName(column = ConstantsValues.INFO_CARD_BARCODE)
    boolean barcode;

    /**
     * Конструктор для создания объекта дисконтной карты
     * @param company Наименование компании. Параметр, переданный от родительского класса
     * @param description Описание к инфокарте. Передан из родительского класса
     * @param nfc Наличие nfc-чипа
     * @param number Номер дисконтной карты
     * @param barcode Наличие штрихкода
     */
    public DiscountCard(String company, String description, boolean nfc, int number, boolean barcode) {
        super(company, description);
        this.nfc = nfc;
        this.number = number;
        this.barcode = barcode;
    }

    public DiscountCard() {
    }

    public boolean getNfc() {
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

    public boolean getBarcode() {
        return barcode;
    }

    public void setBarcode(boolean barcode) {
        this.barcode = barcode;
    }

    @Override
    public String toString() {
        return "DiscountCard{" +
                "nfc=" + nfc +
                ", number=" + number +
                ", barcode=" + barcode +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        DiscountCard that = (DiscountCard) o;
        return Objects.equals(nfc, that.nfc) && Objects.equals(number, that.number) && Objects.equals(barcode, that.barcode);
    }

    @Override
    public int hashCode() {
        return Objects.hash(nfc, number, barcode);
    }

}