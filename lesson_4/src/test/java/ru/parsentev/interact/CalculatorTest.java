package ru.parsentev.interact;

import org.junit.Test;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.*;

/**
 * TODO: comment
 *
 * @author parsentev
 * @since 01.06.2016
 */
public class CalculatorTest {
    @Test
    public void whenAddShouldSummateIt() {
        final Calculator calc = new Calculator();
        calc.add(1, 1);
        final double result = calc.result();
        assertThat(result, is(2d));
    }
}