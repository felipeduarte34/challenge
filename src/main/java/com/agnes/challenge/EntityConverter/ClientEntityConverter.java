package com.agnes.challenge.EntityConverter;

import com.agnes.challenge.DTO.ClientDTO;
import com.agnes.challenge.Entities.Client;
import org.springframework.stereotype.Service;

@Service
public class ClientEntityConverter implements EntityConverter<Client, ClientDTO> {

    @Override
    public ClientDTO convertToDTO(Client clientEntity) {
        ClientDTO clientDTO = new ClientDTO();
        clientDTO.clientName = clientEntity.getName();
        clientDTO.description = clientEntity.getDescription();
        return clientDTO;
    }

    @Override
    public Client convertToEntity(ClientDTO clientDTO) {
        Client clientEntity = new Client();
        clientEntity.setName(clientDTO.clientName);
        clientEntity.setDescription(clientDTO.description);
        return clientEntity;
    }
}
