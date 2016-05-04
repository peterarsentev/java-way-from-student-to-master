package lesson_6.actions;

import lesson_4.Validator;
import lesson_6.Client;
import lesson_6.IClinic;

/**
 * Поиск клеинта по имени
 * @author parsentev
 * @since 11.08.2015
 */
public class SearchClientAction implements Action {

	/**
	 * {@inheritDoc}
	 */
	@Override
	public void execute(IClinic clinic, Validator validator) {
		String key = validator.getString("Search client by name : ");
		for (Client client : clinic.searchClient(key)) {
			System.out.println(client);
		}
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public String intro() {
		return String.format("%s - search client", this.key());
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public int key() {
		return 7;
	}
}
