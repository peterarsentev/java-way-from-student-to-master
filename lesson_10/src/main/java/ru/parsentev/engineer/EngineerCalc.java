package ru.parsentev.engineer;


import ru.parsentev.calculator.Calculator;

/**
 * TODO: comment
 * @author parsentev
 * @since 20.08.2015
 */
public class EngineerCalc extends Calculator {

	public double percent(double value, int percent) throws UserException {
		if (percent > 0) {
			return value * percent / 100;
		} else {
			throw new UserException("Percent could not be 0 or less.");
		}
	}

	public Option<Double> percentUniversal(double value, int percent) {
		return percent > 0 ? new Option<Double>(value * percent / 100) : Option.EMPTY;
	}
}
