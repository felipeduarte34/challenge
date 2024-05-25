package com.agnes.challenge.RestControllers;

import com.agnes.challenge.DTO.ClientDTO;
import com.agnes.challenge.Entities.Client;
import com.agnes.challenge.Services.ClientService;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;
import java.util.List;

@RestController
@RequestMapping("/api/v1")
public class ClientController {

    final
    ClientService clientService;

    public ClientController(final ClientService clientService) {
        this.clientService = clientService;
    }

    @GetMapping("/client/{clientId}")
    public ResponseEntity<ClientDTO> getClientById(@PathVariable Integer clientId) {

        Client clientById = clientService.getClientById(clientId);
        if (clientById != null) {
            ClientDTO clientDTO = new ClientDTO (clientById);
            return new ResponseEntity<>(clientDTO, HttpStatus.OK);

        } else {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "No client with the id " + clientId + " was found.");
        }
    }

    @GetMapping(value = "/client", produces = "application/json")
    public ResponseEntity<List<ClientDTO>> getAllClients() {
        List<ClientDTO> allClients = clientService.getAllClients();
        return new ResponseEntity<>(allClients, HttpStatus.OK);
    }

    @PostMapping(value = "client", consumes = "application/json")
    public ResponseEntity<ClientDTO> createClient(@RequestBody ClientDTO clientDTO) {
        Client client = clientService.saveClient(clientDTO);
        return new ResponseEntity<>(new ClientDTO(client), HttpStatus.OK);
    }

    @PutMapping(value = "/client/{clientId}", consumes = "application/json", produces = "application/json")
    public ResponseEntity<ClientDTO> updateClientById(@PathVariable(name = "clientId") Integer clientId, @RequestBody ClientDTO clientDTO) {
        Client p = clientService.getClientById(clientId);
        if (p != null) {
            Client client = clientService.updateClientById(clientId, clientDTO);
            return new ResponseEntity<>(new ClientDTO(client), HttpStatus.OK);
        }
        else {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Could not find client with the id: " + clientId);
        }
    }

    @DeleteMapping("/client/{clientId}")
    public void deleteClientById(@PathVariable Integer clientId) {

        if  (clientService.isClientIdPresent (clientId)) {
            clientService.deleteClientById (clientId);

        } else {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "No client with the id " + clientId + " was found.");
        }
    }
}
