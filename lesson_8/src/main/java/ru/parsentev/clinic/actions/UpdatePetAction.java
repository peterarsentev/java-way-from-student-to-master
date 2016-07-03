package ru.parsentev.clinic.actions;

import ru.parsentev.clinic.Client;
import ru.parsentev.clinic.IClinic;
import ru.parsentev.clinic.Pet;
import ru.parsentev.clinic.actions.Action;
import ru.parsentev.interact.Validator;

/**
 * �������������� �������.
 * @author parsentev
 * @since 11.08.2015
 */
public class UpdatePetAction implements Action {

	/**
	 * {@inheritDoc}
	 */
	@Override
	public void execute(IClinic clinic, Validator validator) {
		final int clientId = validator.getInt("Select client ID : ");
		final int petId = validator.getInt("Select pet ID : ");
		final String name = validator.getString("Enter pet nick : ");
		final Client client = clinic.getById(clientId);
		Pet pet = client.getPetById(petId);
		pet.setName(name);
		client.editPet(pet);
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public String intro() {
		return String.format("%s - update pet", this.key());
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public int key() {
		return 4;
	}
}
