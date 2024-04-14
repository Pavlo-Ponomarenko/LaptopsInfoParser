package org.parser.parsing;

import java.util.Map;

/**
 * An interface for a single file parsing
 * @author Pavlo Ponomarenko
 */
public interface Parser {

    /**
     * Forms statistic for the given parameter from the file
     * @param parameter The parameter for statistic
     * @return The reference for a current statistic object
     * @throws ParsingException If an error occurred during file parsing
     */
    Map<String, Integer> formStatistic(String parameter) throws ParsingException;
}
