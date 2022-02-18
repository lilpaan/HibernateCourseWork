package ru.sfedu.autoHelper.entity.xmlWriter;

import javax.xml.bind.annotation.XmlAnyElement;
import java.util.ArrayList;
import java.util.List;

/**
 * Класс-обёртка для xml-файлов
 */
public class XmlAnyElements<T> {
    @XmlAnyElement(lax = true)
    private final List<T> items;

    public XmlAnyElements(List<T> contents) {
        items = new ArrayList<T>(contents);
    }

    public XmlAnyElements() {
        this(new ArrayList<T>());
    }

    public List<T> getItems() {
        return items;
    }

}