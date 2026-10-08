package tn.esprit.autoloc.autolocapi.service;

import tn.esprit.autoloc.autolocapi.domain.Client;

import java.util.List;

public interface IClientService {

    Client addClient(Client client);

    List<Client> getAllClients();

    Client getClientById(Long id);

    Client updateClient(Client client);

    void deleteClient(Long id);
}