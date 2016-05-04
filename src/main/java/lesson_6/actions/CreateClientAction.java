package lesson_6.actions;

import lesson_4.Validator;
import lesson_6.Client;
import lesson_6.IClinic;

/**
 * Создание клиента.
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