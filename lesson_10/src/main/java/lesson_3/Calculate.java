package lesson_3;

/**
 * Class Класс для вычисление арифметических операций + - * / ^.
 * @author parsentev
 * @since 14.07.2015
 * @version 1
 */
public class Calculate {
	public static void main(String[] arg) {
		System.out.println("Calculate...");
		double first = Double.valueOf(arg[0]);
		double second = Double.valueOf(arg[1]);
		System.out.println(String.format("%s + %s = %s", first, second, add(first, second)));
		System.out.println(String.format("%s - %s = %s", first, second, substract(first, second)));
		System.out.println(String.format("%s * %s = %s", first, second, multiple(first, second)));
		System.out.println(String.format("%s / %s = %s", first, second, div(first, second)));
		System.out.println(String.format("%s ^ %s = %s", first, second, expand(first, (int) second)));
	}

	/**
	 * Сложение.
	 * @param first первый агрумент.
	 * @param second второй агрумент.
	 * @return результат
	 */
	public static double add(double first, double second) {
		return first + second;
	}

	/**
	 * Вычитание.
	 * @param first первый агрумент.
	 * @param second второй агрумент.
	 * @return результат
	 */
	public static double substract(double first, double second) {
		return first - second;
	}

	/**
	 * Умножение.
	 * @param first первый агрумент.
	 * @param second второй агрумент.
	 * @return результат
	 */
	public static double multiple(double first, double second) {
		return first * second;
	}

	/**
	 * Деление.
	 * @param first первый агрумент.
	 * @param second второй агрумент.
	 * @return результат
	 */
	public static double div(double first, double second) {
		return first / second;
	}

	/**
	 * Возведение в степень.
	 * @param first первый агрумент.
	 * @param second второй агрумент.
	 * @return результат
	 */
	public static double expand(double first, int second) {
		double result = first;
		for (int index=1;index!=second;++index) {
			result *= first;
		}
		return result;
	}
}

