package org.parser;

import org.junit.BeforeClass;
import org.junit.Test;
import org.parser.parsing.ParsersPool;
import org.parser.parsing.ParsersPoolImpl;
import org.parser.parsing.ParsingException;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import static org.junit.Assert.assertEquals;

public class ParsersPoolTest {

    private static final String dir = "src/test/resources/";
    private static Map<String, Integer> expectedStatForProducers;
    private static Map<String, Integer> expectedStatForOptionalPorts;

    @BeforeClass
    public static void setUpBeforeClass() {
        expectedStatForProducers = Map.of(
                "Apple", 2,
                "Lenovo", 4,
                "Dell", 3,
                "Asus", 2,
                "Microsoft", 4
        );

        expectedStatForOptionalPorts = Map.of(
                "Thunderbolt 4", 8,
                "USB 4", 3,
                "SDXC", 3,
                "card slot", 3,
                "HDMI", 7,
                "eSIM slot", 5,
                "USB", 7,
                "Type-C", 5,
                "MicroSD", 3,
                "card reader", 3
        );
    }

    @Test
    public void testAnalyzeMultipleFilesByProducers() {
        // given
        ParsersPool pool = new ParsersPoolImpl(dir + "correct_input");
        // when
        Map<String,Integer> result = pool.parseFiles("producer");
        // then
        assertEquals(expectedStatForProducers, result);
    }

    @Test
    public void testAnalyzeMultipleFilesByOptionalPorts() {
        // given
        ParsersPool pool = new ParsersPoolImpl(dir + "correct_input");
        // when
        Map<String,Integer> result = pool.parseFiles("optional_ports");
        // then
        assertEquals(expectedStatForOptionalPorts, result);
    }

    @Test
    public void testParseInvalidFiles() {
        // given
        ParsersPool pool = new ParsersPoolImpl(dir + "wrong_input");
        // when
        pool.parseFiles("memory");
        // then
        assertEquals(2, pool.getErrors().size());
    }
}
