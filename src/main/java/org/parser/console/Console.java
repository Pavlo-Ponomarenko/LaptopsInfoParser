package org.parser.console;

import java.util.List;
import java.util.Map;

public interface Console {

    void printMessage(String message, OutputStyle... styles);

    void printList(List<String> items, OutputStyle... styles);

    void printStatistic(Map<String, Integer> statistic, OutputStyle... styles);
}
