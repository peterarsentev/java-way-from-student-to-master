package lesson_6.actions;

import lesson_4.Validator;
import lesson_6.Cat;
import lesson_6.Dog;
import lesson_6.IClinic;
import lesson_6.Pet;

import java.util.Arrays;

/**
 * Создание питомца.
 * @author parsentev
 * @since 11.08.2015
 */
public class CreatePetAction implements Action {

	/**
	 * {@inheritDoc}
	 */
	@Override
	public void execute(IClinic IClinic, Validator validator) {
		int clientId = validator.getInt("Select client ID : ");
		int type = validator.getIntFromList("Select pet - 1. Dog, 2. Cat, : ", Arrays.asList(1, 2));
		String nick = validator.getString("Enter pet name : ");
		Pet pet = type == 1 ? new Dog(nick) : new Cat(nick);
		IClinic.addPet(clientId, pet);
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public String intro() {
		return String.format("%s - create pet", this.key());
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public int key() {
		return 2;
	}
}
