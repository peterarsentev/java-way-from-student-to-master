package ru.parsentev.interact;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Mock IO
 *
 * @author parsentev
 * @since 14.06.2016
 */
public class MockIO implements IO {
    private static final Logger log = LoggerFactory.getLogger(MockIO.class);
    private int index = 0;
    private final String[] anwsers;
    private final StringBuilder out = new StringBuilder();

    public MockIO(String[] anwsers) {
        this.anwsers = anwsers;
    }

    @Override
    public String read() {
        return this.anwsers[index++];
    }

    @Override
    public void println(Object value) {
        this.out.append(value).append("\n");
    }

    public String getOut() {
        return this.out.toString();
    }
}
