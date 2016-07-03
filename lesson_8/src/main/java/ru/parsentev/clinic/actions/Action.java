package ru.parsentev.clinic.actions;

import ru.parsentev.clinic.IClinic;
import ru.parsentev.interact.Validator;

/**
 * Описывает действия программы.
 * @author parsentev
 * @since 11.08.2015
 */
public interface Action {

	/**
	 * Выполнить действие.
	 * @param clinic Клиника.
	 * @param validator Валидатор ввода.
	 */
	void execute(final IClinic clinic, final Validator validator);

	/**
	 * Описание действия.
	 * @return Описание.
	 */
	String intro();

	/**
	 * Ключ.
	 * @return ключ.
	 */
	int key();
}

