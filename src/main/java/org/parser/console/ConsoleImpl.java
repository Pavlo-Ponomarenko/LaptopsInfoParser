package org.parser.console;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;

/**
 * An implementation of Console interface
 * @author Pavlo Ponomarenko
 */
public class ConsoleImpl implements Console {

    @Override
    public void printMessage(String message, OutputStyle... styles) {
        StringBuilder builder = getBuilder(styles);
        String result = builder.append(message).append(OutputStyle.ANSI_RESET).toString();
        System.out.println(result);
    }

    @Override
    public void printList(List<String> items, OutputStyle... styles) {
        StringBuilder builder = getBuilder(styles);
        for (String item : items) {
            builder.append(" - ").append(item).append("\n");
        }
        builder.append(OutputStyle.ANSI_RESET);
        System.out.println(builder);
    }

    @Override
    public void printStatistic(Map<String, Integer> statistic, OutputStyle... styles) {
        StringBuilder builder = getBuilder(styles);
        List<String> items = statistic.entrySet().stream().sorted(Map.Entry.comparingByValue()).map(Map.Entry::getKey).toList();
        List<String> paramTypes = new ArrayList<>(items);
        Collections.reverse(paramTypes);
        for (String paramType : paramTypes) {
            builder.append(" | ")
                    .append(paramType)
                    .append(" -> ")
                    .append(statistic.get(paramType))
                    .append("\n");
        }
        builder.append(OutputStyle.ANSI_RESET);
        System.out.println(builder);
    }

    private StringBuilder getBuilder(OutputStyle[] styles) {
        StringBuilder builder = new StringBuilder();
        for (OutputStyle style : styles) {
            builder.append(style.toString());
        }
        return builder;
    }
}
