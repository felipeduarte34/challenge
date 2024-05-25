package com.agnes.challenge.Services;

import com.agnes.challenge.DTO.ClientDTO;
import com.agnes.challenge.Entities.Client;
import com.agnes.challenge.EntityConverter.ClientEntityConverter;
import com.agnes.challenge.Repositories.ClientRepository;
import javassist.NotFoundException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class ClientService {

    final
    ClientRepository clientRepository;

    final
    ClientEntityConverter clientEntityConverter;

    public ClientService(final ClientRepository clientRepository, final ClientEntityConverter clientEntityConverter) {
        this.clientRepository = clientRepository;
        this.clientEntityConverter = clientEntityConverter;
    }

    @Transactional
    public Boolean isClientIdPresent(Integer id) {

        return clientRepository.findById(id).isPresent();
    }

    @Transactional
    public Client getClientById(Integer id){
        return clientRepository.findByClientId(id);
    }

    @Transactional
    public List<Client> findByName(String title) {
        return clientRepository.findByName(title);
    }

    @Transactional
    public Client saveClient(ClientDTO clientDTO) {
        Client clientToBeSaved = new Client(clientDTO);
        return clientRepository.save(clientToBeSaved);
    }

    @Transactional
    public List<ClientDTO> getAllClients() {

        List<Client> all = clientRepository.findAll();
        List<ClientDTO> dtos = new ArrayList<>();
        for (Client client : all) {
            ClientDTO temp = new ClientDTO();
            temp.clientId = client.getClientId();
            temp.clientName = client.getName();
            temp.description = client.getDescription();
            dtos.add(temp);
        }
        return dtos;
    }

    @Transactional
    public Client updateClientById(Integer id, ClientDTO clientToBeUpdated) {

        Client client;

        Optional<Client> clientOptional = clientRepository.findById(id);
        if (!clientOptional.isPresent()) {

            throw new RuntimeException("Could not find client with the id: " + id);
        } else {
            client = clientOptional.get();

            client.setName(clientToBeUpdated.clientName);
            client.setDescription(clientToBeUpdated.description);
        }
        return clientRepository.save(client);
    }

    @Transactional
    public void deleteClientById(Integer id) {

        clientRepository.deleteById(id);
    }
}