package ru.parsentev.interact;

/**
 * TODO: comment
 * @author parsentev
 * @since 17.07.2015
 */
public class CalcExecutor {
	public static void main(String[] args) {
		final Calculator calculator = new Calculator();
		calculator.add(Double.valueOf(args[0]), Double.valueOf(args[0]));
	}
}
