package lesson_6.actions;

import lesson_4.Validator;
import lesson_6.Client;
import lesson_6.IClinic;

/**
 * TODO: comment
 * @author parsentev
 * @since 11.08.2015
 */
public class ShowAction implements Action {

	/**
	 * {@inheritDoc}
	 */
	@Override
	public void execute(IClinic clinic, Validator validator) {
		for (Client client : clinic.getClients()) {
			System.out.println(client);
		}
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public String intro() {
		return String.format("%s - show clients", this.key());
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public int key() {
		return 0;
	}
}
