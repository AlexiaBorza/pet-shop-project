package main.services;
import java.util.List;

import main.domain.Animals;
import main.repository.Repo_Animals;

import main.domain.Clients;
import java.util.ArrayList;

public class ClientsServices {
	private Repo_Clients repo_clients;


public class Repo_Clients {
    private List<Clients> clientsList;

    public Repo_Clients() {
        this.clientsList = new ArrayList<>();
    }

    public void addClient(Clients client) {
        clientsList.add(client);
    }

    public Clients getClientById(int id) {
        return clientsList.stream()
                .filter(client -> client.getId() == id)
                .findFirst()
                .orElse(null);
    }

    public List<Clients> getAllClients() {
        return new ArrayList<>(clientsList);
    }


    public boolean updateClient(int id, Clients updatedClient) {
        for (int i = 0; i < clientsList.size(); i++) {
            if (clientsList.get(i).getId() == id) {
                clientsList.set(i, updatedClient);
                return true;
            }
        }
        return false;
    }

    public boolean deleteClient(int id) {
        return clientsList.removeIf(client -> client.getId() == id);
    }
}
}
