package lesson_6;

import lesson_4.Validator;
import lesson_6.actions.Action;

import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Scanner;

/**
 * Интерфейс клиники.
 * @author parsentev
 * @since 11.08.2015
 */
public class ClinicUI {
	private final IClinic IClinic = new Clinic();
	private final Map<Integer, Action> actions = new LinkedHashMap<>();

	/**
	 * Добавить новое действие в клинику.
	 * @param action
	 */
	public void loadAction(final Action action) {
		this.actions.put(action.key(), action);
	}

	public void show() {
		try (final Validator validator = new Validator(new Scanner(System.in))) {
			this.intro();
			do {
				doAction(validator);
			} while (validator.compare("Do you want to continue? (y)", "y"));
		}
	}

	/**
	 * Запросить выбрать действия пользователя и выполнить его.
	 * @param validator валидатор.
	 */
	private void doAction(final Validator validator) {
		this.actions.get(
				validator.getIntFromList(
						"Enter operation : ",
						this.actions.keySet()
				)
		).execute(this.IClinic, validator);
	}

	private void intro() {
		System.out.println("Welcome to clinic");
		for (final Action action : this.actions.values()) {
			System.out.println(action.intro());
		}
	}
}
