package org.parser;

import org.parser.console.Console;
import org.parser.console.ConsoleImpl;
import org.parser.console.OutputStyle;
import org.parser.parsing.ParsersPool;
import org.parser.parsing.ParsersPoolImpl;
import org.parser.parsing.ParsingException;
import org.parser.xml.XMLBuilder;
import org.parser.xml.XMLBuilderImpl;

import java.util.List;
import java.util.Map;

/**
 * An entry point for the application
 * @author Pavlo Ponomarenko
 */
public class App {

    private static final Console console = new ConsoleImpl();

    public static void main(String[] args) {
        String dir = args[0];
        String parameter = args[1];
        console.printMessage("Analyzing files in \"" + dir + "\" by parameter \"" + parameter + "\":");
        console.printMessage("The result:", OutputStyle.BOLD, OutputStyle.GREEN);
        long startTime = System.currentTimeMillis();
        ParsersPool pool = new ParsersPoolImpl(dir);
        long endTime = System.currentTimeMillis();
        long duration = endTime - startTime;
        Map<String,Integer> result = pool.parseFiles(parameter);
        console.printStatistic(result, OutputStyle.GREEN);
        XMLBuilder builder = new XMLBuilderImpl();
        String fileName = String.format("statistics_by_%s.xml", parameter);
        builder.marshalToXML(result, fileName);
        console.printMessage("The result was saved in " + fileName, OutputStyle.ITALIC);
        console.printMessage("The analyze was performed in " + duration + " milliseconds", OutputStyle.ITALIC);
        console.printMessage("Errors: " + pool.getErrors().size(), OutputStyle.RED, OutputStyle.BOLD);
        List<String> errors = pool.getErrors().stream().map(ParsingException::toString).toList();
        console.printList(errors);
    }
}
