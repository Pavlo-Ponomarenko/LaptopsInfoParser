package org.parser.xml;

import java.util.Map;

/**
 * An interface for writing statistics to a xml file
 * @author Pavlo Ponomarenko
 */
public interface XMLBuilder {

    /**
     * Marshals statistics to a xml file
     * @param data The data that will be marshaled
     * @param fileName The file that will contain statistics
     */
    void marshalToXML(Map<String,Integer> data, String fileName);
}
