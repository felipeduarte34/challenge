package com.agnes.challenge.Repositories;

import com.agnes.challenge.Entities.TeamMembers;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.Optional;

public interface TeamMembersRepository extends JpaRepository<TeamMembers, Integer> {

    Optional<TeamMembers> findByMemberId (Integer id);

    TeamMembers findByLastName(String lastName);

    TeamMembers findByFirstName(String firstName);

    TeamMembers findByEmailAddress(String emailAddress);
}
