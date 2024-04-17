package org.parser;

import org.junit.BeforeClass;
import org.junit.Test;
import org.parser.parsing.Parser;
import org.parser.parsing.ParserImpl;
import org.parser.parsing.ParsingException;

import java.util.HashMap;
import java.util.Map;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

public class ParserTest {

    private static final String dir = "src/test/resources/";

    private static Map<String, Integer> expectedStatForProducers;
    private static Map<String, Integer> expectedStatForOptionalPorts;

    @BeforeClass
    public static void setUpBeforeClass() {
        expectedStatForProducers = Map.of(
                "Apple", 1,
                "Lenovo", 1,
                "Dell", 1,
                "Asus", 1,
                "Microsoft", 1
        );

        expectedStatForOptionalPorts = Map.of(
                "Thunderbolt 4", 2,
                "USB 4", 1,
                "SDXC", 1,
                "card slot", 1,
                "HDMI", 2,
                "eSIM slot", 1,
                "USB", 3,
                "Type-C", 2,
                "MicroSD", 1,
                "card reader", 1
        );
    }

    @Test
    public void testAnalyzeSingleFileByProducers() throws ParsingException {
        // given
        Parser parser = new ParserImpl(dir + "correct_input/dataset1.json", new HashMap<>());
        // when
        Map<String, Integer> result = parser.formStatistic("producer");
        // then
        assertEquals(expectedStatForProducers, result);
    }

    @Test
    public void testAnalyzeSingleFileByOptionalPorts() throws ParsingException {
        // given
        Parser parser = new ParserImpl(dir + "correct_input/dataset1.json", new HashMap<>());
        // when
        Map<String, Integer> result = parser.formStatistic("optional_ports");
        // then
        assertEquals(expectedStatForOptionalPorts, result);
    }

    @Test
    public void testParseInvalidFile() {
        // given
        Parser parser = new ParserImpl(dir + "wrong_input/dataset1.json", new HashMap<>());
        ParsingException exception = null;
        // when
        try {
            parser.formStatistic("memory");
        } catch (ParsingException e) {
            exception = e;
        }
        // then
        assertNotNull(exception);
        assertEquals("Unexpected end-of-input within/between Object entries", exception.getMessage().substring(0, 53));
    }

    @Test
    public void testParseEmptyFile() throws ParsingException {
        // given
        Parser parser = new ParserImpl(dir + "empty.json", new HashMap<>());
        // when
        Map<String, Integer> result = parser.formStatistic("memory");
        // then
        assertEquals(new HashMap<String, Integer>(), result);
    }

    @Test
    public void testAnalyzeByInvalidParameter() throws ParsingException {
        // given
        Parser parser = new ParserImpl(dir + "correct_input/dataset1.json", new HashMap<>());
        // when
        Map<String, Integer> result = parser.formStatistic("_");
        // then
        assertEquals(new HashMap<String, Integer>(), result);
    }
}
