package lesson_6.actions;

import lesson_4.Validator;
import lesson_6.Client;
import lesson_6.IClinic;

/**
 * Поиск питомца по кличке.
 * @author parsentev
 * @since 11.08.2015
 */
public class SearchPetAction implements Action {

	/**
	 * {@inheritDoc}
	 */
	@Override
	public void execute(IClinic IClinic, Validator validator) {
		String key = validator.getString("Search pet by name : ");
		for (Client client : IClinic.searchPet(key)) {
			System.out.println(client);
		}
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public String intro() {
		return String.format("%s - search pet", this.key());
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public int key() {
		return 8;
	}
}
