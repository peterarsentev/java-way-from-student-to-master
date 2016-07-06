package lesson_6;

import lesson_4.Validator;
import lesson_6.actions.Action;

import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Scanner;
import java.util.concurrent.ConcurrentHashMap;

/**
 * ��������� �������.
 * @author parsentev
 * @since 11.08.2015
 */
public class ClinicUI {
	private final IClinic clinic;
	private final Validator validator;
	private final Map<Integer, Action> actions = new ConcurrentHashMap<>();

	public ClinicUI(final IClinic clinic, final Validator validator) {
		this.clinic = clinic;
		this.validator = validator;
	}

	/**
	 * �������� ����� �������� � �������.
	 * @param action
	 */
	public void loadAction(final Action action) {
		this.actions.put(action.key(), action);
	}

	public void show() {
		this.intro();
		do {
			doAction(validator);
		} while (validator.compare("Do you want to continue? (y)", "y"));
	}

	/**
	 * ��������� ������� �������� ������������ � ��������� ���.
	 * @param validator ���������.
	 */
	private void doAction(final Validator validator) {
		this.actions.get(
				validator.getIntFromList(
						"Enter operation : ",
						this.actions.keySet()
				)
		).execute(this.clinic, validator);
	}

	private void intro() {
		System.out.println("Welcome to clinic");
		for (final Action action : this.actions.values()) {
			System.out.println(action.intro());
		}
	}
}
