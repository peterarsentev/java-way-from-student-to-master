package lesson_6.actions;

import lesson_4.Validator;
import lesson_6.Client;
import lesson_6.IClinic;

/**
 * Редактирование клиента.
 * @author parsentev
 * @since 11.08.2015
 */
public class UpdateClientAction implements Action {

	/**
	 * {@inheritDoc}
	 */
	@Override
	public void execute(IClinic IClinic, Validator validator) {
		final int clientId = validator.getInt("Select client ID : ");
		final String name = validator.getString("Enter client name : ");
		final Client client = IClinic.getById(clientId);
		client.setName(name);
		IClinic.editClient(client);
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public String intro() {
		return String.format("%s - update client", this.key());
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public int key() {
		return 3;
	}
}
