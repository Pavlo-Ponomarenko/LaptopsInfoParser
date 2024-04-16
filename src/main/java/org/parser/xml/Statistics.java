package org.parser.xml;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;

/**
 * An entity for writing statistics to a xml file
 * @author Pavlo Ponomarenko
 */
@XmlRootElement
@XmlAccessorType(XmlAccessType.FIELD)
public class Statistics {

    @XmlElement(name = "item")
    private List<Item> items;

    public Statistics() {}

    public Statistics(Map<String, Integer> stat) {
        List<Item> sortedItems = stat.entrySet().stream().sorted(Map.Entry.comparingByValue())
                .map((entry) -> new Item(entry.getKey(), entry.getValue())).toList();
        items = new ArrayList<>(sortedItems);
        Collections.reverse(items);
    }
}
