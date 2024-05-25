package com.agnes.challenge.Repositories;

import com.agnes.challenge.Entities.Client;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.List;
import java.util.Optional;

@Repository
public interface ClientRepository extends JpaRepository<Client, Integer> {

    List<Client> findByName(String name);
    Client findByClientId(Integer clientId);

}
