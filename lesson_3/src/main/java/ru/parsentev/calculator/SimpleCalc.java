package ru.parsentev.calculator;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import static org.slf4j.LoggerFactory.getLogger;

/**
 * @author parsentev
 * @since 24.06.2016
 */
public class SimpleCalc implements ICalculator {
    private static final Logger log = getLogger(SimpleCalc.class);

    @Override
    public double getResult() {
        return 0;
    }

    @Override
    public void add(double first, double second) {

    }
}
