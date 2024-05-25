package com.agnes.challenge.RestControllers;

import com.agnes.challenge.DTO.ProjectDTO;
import com.agnes.challenge.Entities.Project;
import com.agnes.challenge.Services.ProjectService;

import org.springframework.data.repository.query.Param;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;
import java.util.List;

@RestController
@RequestMapping("/api/v1")
public class ProjectController {

    final
    ProjectService projectService;

    public ProjectController(final ProjectService projectService) {
        this.projectService = projectService;
    }


    @GetMapping("/project/{projectId}")
    public ResponseEntity<ProjectDTO> getProjectById(@PathVariable Integer projectId) {

        Project projectById = projectService.getProjectById(projectId);
        if (projectById != null) {
            ProjectDTO projectDTO = new ProjectDTO(projectById);
            return new ResponseEntity<>(projectDTO, HttpStatus.OK);

        } else {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "No project with the id " + projectId + " was found.");
        }
    }

    @GetMapping(value = "/project", produces = "application/json")
    public ResponseEntity<List<ProjectDTO>> getAllProjects(@RequestParam(value = "project_search_client_id", required = false) String clientId) {
        List<ProjectDTO> allProjects = projectService.getAllProjects(clientId);
        return new ResponseEntity<>(allProjects, HttpStatus.OK);
    }

    @PostMapping(value = "project", consumes = "application/json")
    public ResponseEntity<ProjectDTO> createProject(@RequestBody ProjectDTO projectDTO) {

        Project project = projectService.saveProject(projectDTO);
        return new ResponseEntity<>(new ProjectDTO(project), HttpStatus.OK);
    }

    @PutMapping(value = "/project/{projectId}", consumes = "application/json", produces = "application/json")
    public ResponseEntity<ProjectDTO> updateProjectById(@PathVariable(name = "projectId") Integer projectId, @RequestBody ProjectDTO projectDTO) {

        Project p = projectService.getProjectById(projectId);
        if (p != null) {
            Project project = projectService.updateProjectById(projectId, projectDTO);
            return new ResponseEntity<>(new ProjectDTO(project), HttpStatus.OK);
        }
        else {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Could not find project with the id: " + projectId);
        }
    }

    @DeleteMapping("/project/{projectId}")
    public void deleteProjectById(@PathVariable Integer projectId) {
        if (projectService.isProjectIdPresent(projectId)) {
            projectService.deleteProjectById(projectId);
        } else {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "No project with the id " + projectId + " was found.");
        }
    }
}
