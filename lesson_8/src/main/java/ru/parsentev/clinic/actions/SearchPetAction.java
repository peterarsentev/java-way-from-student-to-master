package ru.parsentev.clinic.actions;

import ru.parsentev.interact.Validator;
import ru.parsentev.clinic.Client;
import ru.parsentev.clinic.IClinic;
import ru.parsentev.clinic.actions.Action;

/**
 * ����� ������� �� ������.
 * @author parsentev
 * @since 11.08.2015
 */
public class SearchPetAction implements Action {

	/**
	 * {@inheritDoc}
	 */
	@Override
	public void execute(IClinic clinic, Validator validator) {
		String key = validator.getString("Search pet by name : ");
		for (Client client : clinic.searchPet(key)) {
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
