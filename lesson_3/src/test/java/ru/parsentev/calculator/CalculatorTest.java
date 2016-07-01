/**
 * @checkstyle HeaderCheck
 */
package ru.parsentev.calculator;

import org.junit.Test;
import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.*;

/**
 * Тест для калькутятора.
 * @author parsentev
 * @since 14.07.2015
 */
public class CalculatorTest {
    @Test
    public void testWhenPassArgToAddItShouldReturnSumm() {
        final ICalculator calc = new Calculator();
        calc.add(2, 2);
        final double result = calc.getResult();
        assertThat(result, is(4d));
    }

    @Test
    public void testWhenPassArgToSubstractItShouldReturnSubstract() {
        final ICalculator calc = new Calculator();
        calc.substract(2, 2);
        final double result = calc.getResult();
        assertThat(result, is(0d));
    }

    @Test
    public void testWhenPassArgToMultipleItShouldReturnMultiple() {
        final ICalculator calc = new Calculator();
        calc.multiple(2, 2);
        final double result = calc.getResult();
        assertThat(result, is(4d));
    }

    @Test
    public void testWhenPassArgToDivItShouldReturnDiv() {
        final ICalculator calc = new Calculator();
        calc.div(2, 2);
        final double result = calc.getResult();
        assertThat(result, is(1d));
    }

    @Test
    public void testWhenPassArgToExpandItShouldReturnExpand() {
        final ICalculator calc = new Calculator();
        calc.exp(2, 2);
        final double result = calc.getResult();
        assertThat(result, is(4d));
    }
}
