package lesson_12;

import lesson_6.Client;
import lesson_6.Clinic;
import lesson_6.Pet;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Collection;

/**
 * TODO: comment
 * @author parsentev
 * @since 04.05.2016
 */
public class SynchClinic extends Clinic {
	private static final Logger log = LoggerFactory.getLogger(SynchClinic.class);

	@Override
	public synchronized void addClient(Client client) {
		super.addClient(client);
	}

	@Override
	public synchronized void addPet(int id, Pet pet) {
		super.addPet(id, pet);
	}

	@Override
	public synchronized void editClient(Client client) {
		super.editClient(client);
	}

	@Override
	public synchronized void editPet(int id, Pet pet) {
		super.editPet(id, pet);
	}

	@Override
	public synchronized void deleteClient(int id) {
		super.deleteClient(id);
	}

	@Override
	public synchronized void deletePet(int clientId, int petId) {
		super.deletePet(clientId, petId);
	}

	@Override
	public synchronized Collection<Client> searchClient(String name) {
		return super.searchClient(name);
	}

	@Override
	public synchronized Collection<Client> searchPet(String nick) {
		return super.searchPet(nick);
	}

	@Override
	public synchronized Collection<Client> getClients() {
		return super.getClients();
	}

	@Override
	public synchronized Client getById(int clientId) {
		return super.getById(clientId);
	}
}
