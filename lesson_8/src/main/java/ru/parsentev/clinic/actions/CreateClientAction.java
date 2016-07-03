package ru.parsentev.clinic.actions;

import ru.parsentev.interact.Validator;
import ru.parsentev.clinic.Client;
import ru.parsentev.clinic.IClinic;
import ru.parsentev.clinic.actions.Action;

/**
 * �������� �������.
 * @author parsentev
 * @since 11.08.2015
 */
public class CreateClientAction implements Action {

	/**
	 * {@inheritDoc}
	 */
	@Override
	public void execute(IClinic clinic, Validator validator) {
		final String name = validator.getString("Enter client name : ");
		final Client client = new Client();
		client.setName(name);
		clinic.addClient(client);
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public String intro() {
		return String.format("%s - create client", this.key());
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public int key() {
		return 1;
	}
}