package ru.sfedu.autoHelper.entity;

import com.opencsv.bean.CsvBindByName;
import ru.sfedu.autoHelper.ConstantsValues;

import javax.xml.bind.annotation.XmlRootElement;
import java.util.Objects;

/**
 * Класс, реализующий возможность добавления автозапчасти и содержании её в отдельном объекте
 */
@XmlRootElement
public class SparePart {
    @CsvBindByName(column = ConstantsValues.SPARE_PART_TYPE)
    String type;
    @CsvBindByName(column = ConstantsValues.SPARE_PART_MAKER)
    String maker;
    @CsvBindByName(column = ConstantsValues.SPARE_PART_INSTALLATION_DATE)
    String installationDate;

    /**
     * Конструктор для создания запчасти
     * @param type тип запчасти
     * @param maker производитель
     * @param installationDate дата установки запчасти
     */
    public SparePart(String type, String maker, String installationDate) {
        this.type = type;
        this.maker = maker;
        this.installationDate = installationDate;
    }

    public SparePart() {
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
    public String toString() {
        return "SparePart{" +
                "type='" + type + '\'' +
                ", maker='" + maker + '\'' +
                ", installationDate=" + installationDate +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        SparePart sparePart = (SparePart) o;
        return Objects.equals(type, sparePart.type) && Objects.equals(maker, sparePart.maker) && Objects.equals(installationDate, sparePart.installationDate);
    }

    @Override
    public int hashCode() {
        return Objects.hash(type, maker, installationDate);
    }

}