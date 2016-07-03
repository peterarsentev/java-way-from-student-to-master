package ru.parsentev.clinic.actions;

import ru.parsentev.interact.Validator;
import ru.parsentev.clinic.Cat;
import ru.parsentev.clinic.Dog;
import ru.parsentev.clinic.IClinic;
import ru.parsentev.clinic.Pet;
import ru.parsentev.clinic.actions.Action;

import java.util.Arrays;

/**
 * �������� �������.
 * @author parsentev
 * @since 11.08.2015
 */
public class CreatePetAction implements Action {

	/**
	 * {@inheritDoc}
	 */
	@Override
	public void execute(IClinic clinic, Validator validator) {
		int clientId = validator.getInt("Select client ID : ");
		int type = validator.getIntFromList("Select pet - 1. Dog, 2. Cat, : ", Arrays.asList(1, 2));
		String nick = validator.getString("Enter pet name : ");
		Pet pet = type == 1 ? new Dog(nick) : new Cat(nick);
		clinic.addPet(clientId, pet);
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
