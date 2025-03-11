package main.repository;

import main.domain.Clients;
import java.util.ArrayList;
import java.util.List;

/**
 * Clasa Repository pentru functii CRUD pentru clienti.
 */

public class Repo_Clients {
	private List<Clients> clientsList;

	
/**
* Constructor care initializeaza lista de clienti.
*/
public Repo_Clients() {
	this.clientsList = new ArrayList<>();
	   }

/**
* Adauga un client nou la lista.
*
* @param client clientul de adaugat
*/
public void addClient(Clients client) {
	 clientsList.add(client);
}


/**
 * Gaseste un client dupa ID.
 *
 * @param id the ID of the client
 * @return the client with the specified ID, or null if not found
 */
public Clients getClientById(int id) {
    return clientsList.stream()
            .filter(client -> client.getId() == id)
            .findFirst()
            .orElse(null);
}


/**
 * Gaseste toti clientii.
 *
 * @return o lista cu toti clientii
 */
public List<Clients> getAllClients() {
    return new ArrayList<>(clientsList);
}

/**
 * Updates an existing client identified by their ID.
 *
 * @param id ID-ul clientului care trebuie actualizat 
 * @param updatedClient noile date ale clientului
 * @return true daca operatiunea s-a efectuat cu succes
 */
public boolean updateClient(int id, Clients updatedClient) {
    for (int i = 0; i < clientsList.size(); i++) {
        if (clientsList.get(i).getId() == id) {
            clientsList.set(i, updatedClient);
            return true;
        }
    }
    return false;
}

/**
 * Sterge un client dupa ID.
 *
 * @param id ID-ul clientului care trebuie sters
 * @return true daca operatiunea s-a efectuat cu succes
 */
public boolean deleteClient(int id) {
    return clientsList.removeIf(client -> client.getId() == id);
}
}

    
	

    

   