package org.parser.console;

import java.util.List;
import java.util.Map;

/**
 * An interface for console output implementation
 * @author Pavlo Ponomarenko
 */
public interface Console {

    /**
     * Prints a simple info message in the console
     * @param message The message that will be printed
     * @param styles The list of styles that format the text of the message
     */
    void printMessage(String message, OutputStyle... styles);

    /**
     * Prints a list in the console
     * @param items The items of the list that will be printed
     * @param styles The list of styles that format the text of the items
     */
    void printList(List<String> items, OutputStyle... styles);

    /**
     * Prints statistic in the console
     * @param statistic The statistic that will be printed
     * @param styles The list of styles that format the text of the statistic
     */
    void printStatistic(Map<String, Integer> statistic, OutputStyle... styles);
}
