package ru.sfedu.autoHelper.lab2;

import javax.persistence.Embeddable;

/**
 * Компонент, вмещающий в себя краткую информацию о сотруднике некой компании Panchenko
 */
@Embeddable
public class PanchenkoComponent {
    String name;
    int phoneNumber;
    String mainLanguage;

    public PanchenkoComponent() {
    }

    public PanchenkoComponent(String name, int phoneNumber, String mainLanguage) {
        this.name = name;
        this.phoneNumber = phoneNumber;
        this.mainLanguage = mainLanguage;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(int phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getMainLanguage() {
        return mainLanguage;
    }

    public void setMainLanguage(String mainLanguage) {
        this.mainLanguage = mainLanguage;
    }

}
