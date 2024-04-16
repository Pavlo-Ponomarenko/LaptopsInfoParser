package org.parser.xml;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import java.io.File;
import java.util.Map;

/**
 * An implementation of XMLBuilder
 * @author Pavlo Ponomarenko
 */
public class XMLBuilderImpl implements XMLBuilder {

    @Override
    public void marshalToXML(Map<String, Integer> data, String fileName) {
        Statistics statistic = new Statistics(data);
        try {
            File outputFile = new File(fileName);
            JAXBContext jaxbContext = JAXBContext.newInstance(Statistics.class);
            Marshaller marshaller = jaxbContext.createMarshaller();
            marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);
            marshaller.marshal(statistic, outputFile);
        } catch(JAXBException e) {
            e.printStackTrace();
        }
    }
}
