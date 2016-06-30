package ru.parsentev.calculator;

import org.junit.Test;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.*;

/**
 * TODO: comment
 *
 * @author parsentev
 * @since 24.06.2016
 */
public class SimpleCalcTest {
    @Test
    public void testWhenPassArgToAddItShouldReturnSumm() {
        final ICalculator calc = new SimpleCalc();
        calc.add(2, 2);
        final double result = calc.getResult();
        assertThat(result, is(4d));
    }
}