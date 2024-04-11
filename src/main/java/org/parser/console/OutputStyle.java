package org.parser.console;

/**
 * Styles for console output formatting
 * @author Pavlo Ponomarenko
 */
public enum OutputStyle {

    GREEN("\u001B[32m"),
    RED("\u001B[31m"),
    BOLD("\u001B[1m"),
    ITALIC("\u001B[3m");

    public static final String ANSI_RESET = "\u001B[0m";

    private final String style;

    OutputStyle(String style) {
        this.style = style;
    }

    @Override
    public String toString() {
        return style;
    }
}
