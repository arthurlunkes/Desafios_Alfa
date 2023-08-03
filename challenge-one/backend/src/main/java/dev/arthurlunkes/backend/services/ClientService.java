package dev.arthurlunkes.backend.services;

import dev.arthurlunkes.backend.models.ClientModel;
import dev.arthurlunkes.backend.repositories.ClientRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ClientService {

    final private ClientRepository clientRepository;

    public ClientService(ClientRepository clientRepository) {
        this.clientRepository = clientRepository;
    }

    public List<ClientModel> findAll() {
        return clientRepository.findAll();
    }

}
