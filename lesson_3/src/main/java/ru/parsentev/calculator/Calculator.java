/**
 */
package ru.parsentev.calculator;

/**
 * Calculator.
 * @author Petr Arsentev (parsentev@yandex.ru)
 * @version $Id$
 * @since 31.05.2016
 */
public class Calculator implements ICalculator {
    /**
     * Store result.
     */
    private double result;

    /**
     * Get result.
     * @return Result.
     */
    @Override
    public final double getResult() {
        return this.result;
    }

    /**
     * Add numbers.
     * @param first First
     * @param second Second
     */
    @Override
    public final void add(final double first, final double second) {
        this.result = first + second;
    }

    @Override
    public void substract(double first, double second) {
        this.result = first - second;
    }

    @Override
    public void div(double first, double second) {
        this.result = first / second;
    }

    @Override
    public void multiple(double first, double second) {
        this.result = first * second;
    }

    @Override
    public void exp(double first, double second) {
        double result = first;
        for (int index=1;index!=second;++index) {
            result *= first;
        }
        this.result = result;
    }
}
