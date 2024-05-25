package com.agnes.challenge.DTO;

import com.agnes.challenge.Entities.Client;

public class ClientDTO {

    public int clientId;
    public String clientName;
    public String description;

    public ClientDTO() {
    }

    public ClientDTO(Client clientEntity) {
        this.clientId = clientEntity.getClientId();
        this.clientName = clientEntity.getName();
        this.description = clientEntity.getDescription();
    }

    public int getClientId() {
        return clientId;
    }

    public void setClientId(int clientId) {
        this.clientId = clientId;
    }

    public String getClientName() {
        return clientName;
    }

    public void setClientName(String clientName) {
        this.clientName = clientName;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    @Override
    public String toString() {
        return "ClientDTO{" +
                "clientId=" + clientId +
                ", clientName='" + clientName + '\'' +
                ", description='" + description + '\'' +
                '}';
    }
}
