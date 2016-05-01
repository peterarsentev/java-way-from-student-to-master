package lesson_6.actions;

import lesson_4.Validator;
import lesson_6.Client;
import lesson_6.IClinic;
import lesson_6.Pet;

/**
 * Редактирование питомца.
 * @author parsentev
 * @since 11.08.2015
 */
public class UpdatePetAction implements Action {

	/**
	 * {@inheritDoc}
	 */
	@Override
	public void execute(IClinic IClinic, Validator validator) {
		final int clientId = validator.getInt("Select client ID : ");
		final int petId = validator.getInt("Select pet ID : ");
		final String name = validator.getString("Enter pet nick : ");
		final Client client = IClinic.getById(clientId);
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
