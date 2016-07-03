package ru.parsentev.clinic.actions;

import ru.parsentev.interact.Validator;
import ru.parsentev.clinic.IClinic;
import ru.parsentev.clinic.actions.Action;

/**
 * �������� �������.
 * @author parsentev
 * @since 11.08.2015
 */
public class DeletePetAction implements Action {

	/**
	 * {@inheritDoc}
	 */
	@Override
	public void execute(IClinic clinic, Validator validator) {
		int clientId = validator.getInt("Select client ID :");
		int petId = validator.getInt("Select pet ID :");
		clinic.deletePet(clientId, petId);
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public String intro() {
		return String.format("%s - delete pet", this.key());
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public int key() {
		return 6;
	}
}
