package ru.sfedu.autoHelper.lab4.componentMapTypeMapping;


import javax.persistence.Column;
import javax.persistence.Embeddable;
import java.util.Objects;

@Embeddable
public class InfoCard {
    @Column
    protected String company;
    protected String description;

    public InfoCard(String company, String description) {
        this.company = company;
        this.description = description;
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

    @Override
    public String toString() {
        return "InfoCard{" +
                "company='" + company + '\'' +
                ", description='" + description + '\'' +
                '}';
    }
}
