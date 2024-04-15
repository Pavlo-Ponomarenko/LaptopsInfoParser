package org.parser.parsing;

import java.util.List;
import java.util.Map;

/**
 * An interface for implementing pools of parsers
 * @author Pavlo Ponomarenko
 */
public interface ParsersPool {

    /**
     * Gathers statistic from different parsing threads
     * @param param The parameter that is analyzed for the statistic
     * @return The reference for a common statistic object
     */
    Map<String, Integer> parseFiles(String param);

    /**
     * Returns a list of errors that occurred in files
     * @return The list of parsing errors
     */
    List<ParsingException> getErrors();
}
