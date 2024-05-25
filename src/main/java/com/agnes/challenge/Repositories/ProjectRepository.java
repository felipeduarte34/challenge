package com.agnes.challenge.Repositories;

import com.agnes.challenge.Entities.Project;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import java.util.List;
import java.util.Optional;

@Repository
public interface ProjectRepository extends JpaRepository<Project, Integer> {

    List<Project> findByName(String name);

    Project findProjectByProjectId(Integer projectId);

    @Query(value = "SELECT * FROM project WHERE client_id = :clientId", nativeQuery = true)
    List<Project> findAllProjectsByClientId(@Param("clientId") Integer clientId);
    @Query(value = "SELECT * FROM project WHERE status_id = :statusId", nativeQuery = true)
    List<Project> findAllProjectsByStatusId(@Param("statusId") Integer statusId);
}
