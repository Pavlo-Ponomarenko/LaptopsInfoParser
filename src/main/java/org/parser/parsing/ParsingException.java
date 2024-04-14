package org.parser.parsing;

/**
 * An exception for errors that occur during file parsing
 * @author Pavlo Ponomarenko
 */
public class ParsingException extends Exception {

    private final String fileName;

    public ParsingException(String message, Throwable cause, String fileName) {
        super(message, cause);
        this.fileName = fileName;
    }

    @Override
    public String toString() {
        return "Parsing failed in the file " + fileName + " - " + getMessage();
    }
}
