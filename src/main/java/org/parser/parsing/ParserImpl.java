package org.parser.parsing;

import com.fasterxml.jackson.core.JsonFactory;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonToken;

import java.io.File;
import java.io.IOException;
import java.util.Map;

/**
 * An implementation of Parser interface
 * @author Pavlo Ponomarenko
 */
public class ParserImpl implements Parser {

    private final String file;
    private final Map<String, Integer> statistic;

    private final static String VALUES_DELIMITER = ", ";

    public ParserImpl(String file, Map<String, Integer> statistic) {
        this.file = file;
        this.statistic = statistic;
    }

    @Override
    public Map<String, Integer> formStatistic(String parameter) throws ParsingException {
        JsonFactory factory = new JsonFactory();
        try (JsonParser parser  = factory.createParser(new File(file))) {
            parser.nextToken();
            retrieveParameter(parser, parameter);
        } catch (IOException ex) {
            throw new ParsingException(ex.getMessage(), ex.getCause(), file);
        }
        return statistic;
    }

    private void retrieveParameter(JsonParser parser, String parameter) throws IOException {
        while(!parser.isClosed()) {
            JsonToken token = parser.nextToken();
            if (!token.equals(JsonToken.START_OBJECT) && !token.equals(JsonToken.END_OBJECT)) {
                String paramName = parser.getCurrentName();
                parser.nextToken();
                String value = parser.getValueAsString();
                if (parameter.equals(paramName)) {
                    if (value.contains(VALUES_DELIMITER)) {
                        String[] values = value.split(VALUES_DELIMITER);
                        for (String item : values) {
                            updateCurrentState(item);
                        }
                    } else {
                        updateCurrentState(value);
                    }
                }
            }
        }
    }

    private void updateCurrentState(String value) {
        synchronized (statistic) {
            statistic.putIfAbsent(value, 0);
            Integer previousValue = statistic.get(value);
            statistic.replace(value, ++previousValue);
        }
    }
}
