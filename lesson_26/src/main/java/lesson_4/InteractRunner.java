package lesson_4;

import java.util.Scanner;

/**
 * Калькулятор. Поддерживает пользовательский ввод.
 * @author parsentev
 * @since 17.07.2015
 */
public class InteractRunner {
	public static void main(String[] args) {
		Calculator calculator = new Calculator();
		boolean reuse = false;
		try (final Validator validator = new Validator(new Scanner(System.in))) {
			do {
				final double first = reuse ? calculator.result() : validator.getDouble("Enter first arg : ");
				String operation = validator.getString("Enter operation : ");
				double second = validator.getDouble("Enter second arg : ");
				calculator.calc(operation, first, second);
				System.out.println(String.format("%s %s %s = %s", first, operation, second, calculator.result()));
				reuse = validator.compare("Do you want to reuse result? (y)", "y");
			} while (validator.compare("Do you want to continue? (y)", "y"));
		}
	}
}
