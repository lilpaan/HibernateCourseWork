package ru.sfedu.autoHelper.lab4.componentMapTypeMapping;


import javax.persistence.Column;
import javax.persistence.Embeddable;

@Embeddable
public class InfoCard {
    @Column(nullable = true)
    protected String company;
    protected String description;

    public InfoCard(String company, String description) {
        this.company = company;
        this.description = description;
    }

    public InfoCard() {
    }
}
