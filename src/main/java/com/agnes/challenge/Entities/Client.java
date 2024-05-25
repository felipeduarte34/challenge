package com.agnes.challenge.Entities;

import com.agnes.challenge.DTO.ClientDTO;
import com.agnes.challenge.DTO.ProjectDTO;
import javax.persistence.*;

@Entity
@Table(name = "client")
public class Client {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "client_seq")
    @SequenceGenerator(name = "client_seq", sequenceName = "client_client_id_seq", allocationSize = 1)
    @Column(name = "client_id")
    Integer clientId;

    @Column(name = "name", nullable = false)
    String name;

    @Column(name = "description")
    String description;


    public Client() {
    }

    public Client(ClientDTO clientDTO) {

        this.name = clientDTO.clientName;
        this.description = clientDTO.description;

    }

    public Integer getClientId() {
        return clientId;
    }

    public void setClientId(Integer clientId) {
        this.clientId = clientId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    @Override
    public String toString() {
        return "Client{" +
                "clientId=" + clientId +
                ", name='" + name + '\'' +
                ", description='" + description + '\'' +
                '}';
    }
}
