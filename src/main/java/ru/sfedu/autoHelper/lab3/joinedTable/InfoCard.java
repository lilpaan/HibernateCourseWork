package ru.sfedu.autoHelper.lab3.joinedTable;

import ru.sfedu.autoHelper.ConstantsValues;

import javax.persistence.*;
import java.util.Objects;

/**
 * Родительский класс для инфокарт. Содержит в себе общие параметры двух типов карт
 * @see BusinessCard
 * @see DiscountCard
 */

@Entity(name = ConstantsValues.INFO_CARD_JOINED_TABLE)
@Inheritance(strategy = InheritanceType.JOINED)
public class InfoCard {
    @Id
    Long id;
    String company;
    String description;

    /**
     * Конструктор для создания объекта инфокарты
     * @param id идентификатор инфокарты
     * @param company компания, предоставляющее услуги
     * @param description описание карточки
     */
    public InfoCard(Long id, String company, String description) {
        this.id = id;
        this.company = company;
        this.description = description;
    }

    public InfoCard() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
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
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        InfoCard infoCard = (InfoCard) o;
        return Objects.equals(id, infoCard.id) && Objects.equals(company, infoCard.company) && Objects.equals(description, infoCard.description);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, company, description);
    }

    @Override
    public String toString() {
        return "InfoCard{" +
                "id=" + id +
                ", company='" + company + '\'' +
                ", description='" + description + '\'' +
                '}';
    }

}
