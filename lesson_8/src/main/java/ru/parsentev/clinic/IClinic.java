package ru.parsentev.clinic;

import java.util.Collection;

/**
 * TODO: comment
 * @author parsentev
 * @since 02.11.2015
 */
public interface IClinic {
	void addClient(Client client);

	void addPet(int id, Pet pet);

	void editClient(Client client);

	void editPet(int id, Pet pet);

	void deleteClient(int id);

	void deletePet(int clientId, int petId);

	Collection<Client> searchClient(String name);

	Collection<Client> searchPet(String nick);

	Collection<Client> getClients();

	Client getById(int clientId);
}
