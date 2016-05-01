package lesson_6.actions;

import lesson_4.Validator;
import lesson_6.IClinic;

/**
 * Удаление клиента
 * @author parsentev
 * @since 11.08.2015
 */
public class DeleteClientAction implements Action {

	/**
	 * {@inheritDoc}
	 */
	@Override
	public void execute(IClinic IClinic, Validator validator) {
		int clientId = validator.getInt("Select client ID : ");
		IClinic.deleteClient(clientId);
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public String intro() {
		return String.format("%s - delete client", this.key());
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public int key() {
		return 5;
	}
}
