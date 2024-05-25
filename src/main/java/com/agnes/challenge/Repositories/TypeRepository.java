package com.agnes.challenge.Repositories;

import com.agnes.challenge.Entities.Type;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.Optional;

public interface TypeRepository extends JpaRepository<Type, Integer> {

    Optional<Type> findByName(String typeName);
}
