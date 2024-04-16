package org.parser.xml;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;

/**
 * An entity for writing items of statistics to a xml file
 * @author Pavlo Ponomarenko
 */
@XmlAccessorType(XmlAccessType.FIELD)
public class Item {

    @XmlElement
    private String value;
    @XmlElement
    private int count;

    public Item(String value, int count) {
        this.value = value;
        this.count = count;
    }
}
