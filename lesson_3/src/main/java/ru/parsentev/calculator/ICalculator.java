/**
 * @checkstyle HeaderCheck
 */
package ru.parsentev.calculator;

/**
 * @author parsentev
 * @since 24.06.2016
 */
public interface ICalculator {

    double getResult();

    void add(double first, double second);

    void substract(double first, double second);

    void div(double first, double second);

    void multiple(double first, double second);

    void exp(double first, double second);
}
