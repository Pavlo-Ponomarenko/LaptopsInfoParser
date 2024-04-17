package org.parser.parsing;

import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * An implementation of ParsersPool interface
 * @author Pavlo Ponomarenko
 */
public class ParsersPoolImpl implements ParsersPool {

    private final Map<String, Integer> statistic = new HashMap<>();
    private final List<ParsingException> errors = new CopyOnWriteArrayList<>();
    private final File[] files;

    public ParsersPoolImpl(String path) {
        File directory = new File(path);
        files = directory.listFiles();
    }

    @Override
    public Map<String, Integer> parseFiles(String param) {
        ExecutorService executor = Executors.newFixedThreadPool(files.length);
        Parser[] parsers = getParsersArr(files.length);
        CountDownLatch latch = new CountDownLatch(files.length);
        for (Parser parser : parsers) {
            executor.submit(() -> {
                try {
                    parser.formStatistic(param);
                } catch (ParsingException e) {
                    errors.add(e);
                } finally {
                    latch.countDown();
                }
            });
        }
        try {
            latch.await();
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        } finally {
            executor.shutdown();
        }
        return statistic;
    }

    private Parser[] getParsersArr(int size) {
        Parser[] arr = new Parser[size];
        for (int i = 0; i < files.length; i++) {
            arr[i] = new ParserImpl(files[i].getAbsolutePath(), statistic);
        }
        return arr;
    }

    @Override
    public List<ParsingException> getErrors() {
        return new ArrayList<>(errors);
    }
}
