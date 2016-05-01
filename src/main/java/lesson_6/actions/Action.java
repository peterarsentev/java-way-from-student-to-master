package lesson_6.actions;

import lesson_4.Validator;
import lesson_6.IClinic;

/**
 * Описывает действия программы.
 * @author parsentev
 * @since 11.08.2015
 */
public interface Action {

	/**
	 * Выполнить действие.
	 * @param IClinic Клиника.
	 * @param validator Валидатор ввода.
	 */
	void execute(final IClinic IClinic, final Validator validator);

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
