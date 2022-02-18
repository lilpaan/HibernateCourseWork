package ru.sfedu.autoHelper.entity;

import com.opencsv.bean.CsvBindByName;
import ru.sfedu.autoHelper.ConstantsValues;

import javax.xml.bind.annotation.XmlRootElement;
import java.util.Objects;

/**
 * Родительский класс для инфокарт. Содержит в себе общие параметры двух типов карт
 * @see BusinessCard
 * @see DiscountCard
 */
@XmlRootElement
public class InfoCard {
    @CsvBindByName(column = ConstantsValues.INFO_CARD_COMPANY)
    String company;
    @CsvBindByName(column = ConstantsValues.INFO_CARD_DESCRIPTION)
    String description;

    /**
     * Конструктор для создания объекта инфокарты
     * @param company компания, предоставляющее услуги
     * @param description описание карточки
     */
    public InfoCard(String company, String description) {
        this.company = company;
        this.description = description;
    }

    public InfoCard(String company) {
        this.company = company;
    }

    public InfoCard() {
    }

    public String getCompany() {
        return company;
    }

    public void setCompany(String company) {
        this.company = company;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    @Override
    public String toString() {
        return "InfoCard{" +
                "company='" + company + '\'' +
                ", description='" + description + '\'' +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        InfoCard infoCard = (InfoCard) o;
        return Objects.equals(company, infoCard.company) && Objects.equals(description, infoCard.description);
    }

    @Override
    public int hashCode() {
        return Objects.hash(company, description);
    }

}
