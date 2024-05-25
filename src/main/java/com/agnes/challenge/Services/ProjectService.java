package com.agnes.challenge.Services;

import com.agnes.challenge.DTO.ProjectDTO;
import com.agnes.challenge.Entities.Client;
import com.agnes.challenge.Entities.Project;
import com.agnes.challenge.Entities.Status;
import com.agnes.challenge.Entities.TeamMembers;
import com.agnes.challenge.EntityConverter.ProjectEntityConverter;
import com.agnes.challenge.Repositories.ClientRepository;
import com.agnes.challenge.Repositories.ProjectRepository;
import com.agnes.challenge.Repositories.StatusRepository;
import com.agnes.challenge.Repositories.TeamMembersRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class ProjectService {

    final
    ProjectRepository projectRepository;

    final
    StatusRepository statusRepository;

    final
    ClientRepository clientRepository;

    final
    TeamMembersRepository teamMembersRepository;

    final
    ProjectEntityConverter projectEntityConverter;

    public ProjectService(final ProjectRepository projectRepository, final StatusRepository statusRepository, final ClientRepository clientRepository,
        final TeamMembersRepository teamMembersRepository, final ProjectEntityConverter projectEntityConverter) {
        this.projectRepository = projectRepository;
        this.statusRepository = statusRepository;
        this.clientRepository = clientRepository;
        this.teamMembersRepository = teamMembersRepository;
        this.projectEntityConverter = projectEntityConverter;
    }

    @Transactional
    public Boolean isProjectIdPresent(Integer id) {

        return projectRepository.findById(id).isPresent();
    }

    @Transactional
    public Project getProjectById(Integer id){
        return projectRepository.findProjectByProjectId(id);
    }

    @Transactional
    public List<Project> findByName(String title) {
        return projectRepository.findByName(title);
    }

    @Transactional
    public Project saveProject(ProjectDTO projectDTO) {

        Client client = clientRepository.findByClientId(projectDTO.clientId);
        Optional<Status> byStatusName = statusRepository.findByStatusName(projectDTO.statusName);
        Optional<TeamMembers> byMemberId = teamMembersRepository.findByMemberId(projectDTO.teamMemberId);

        TeamMembers teamMembers = null;
        if (!byMemberId.isPresent()) {

            TeamMembers newMember = new TeamMembers(projectDTO.teamMemberId);
            teamMembers = teamMembersRepository.save(newMember);
        } else {
            teamMembers = byMemberId.get();
        }

        Status status = null;
        if (!byStatusName.isPresent()) {

            Status newStatus = new Status(projectDTO.statusName);
            status = statusRepository.save(newStatus);
        } else {

            status = byStatusName.get();
        }

        Project projectToBeSaved = new Project(projectDTO, client, teamMembers, status);
        return projectRepository.save(projectToBeSaved);
    }

    @Transactional
    public List<ProjectDTO> getAllProjects(String clientId) {
        List<Project> all = new ArrayList<>();
        if (clientId != null && !clientId.equals("0")) {
            all = projectRepository.findAllProjectsByClientId(Integer.parseInt(clientId));
        } else {
            all = projectRepository.findAll();
        }
        List<ProjectDTO> dtos = new ArrayList<>();
        for (Project project : all) {
            ProjectDTO temp = new ProjectDTO();
            temp.projectId = project.getProjectId();
            temp.projectName = project.getName();
            temp.description = project.getDescription();
            temp.statusName = project.getStatusOfProject().getStatusName();
            temp.clientName = project.getClient().getName();
            temp.clientId = project.getClient().getClientId();
            temp.teamMemberId = project.getTeamMemberOfProject().getMemberId();
            temp.teamMemberOfProjectLastName = project.getTeamMemberOfProject().getLastName();
            temp.teamMemberOfProjectFirstName = project.getTeamMemberOfProject().getFirstName();
            temp.teamMemberOfProjectEmailAddress = project.getTeamMemberOfProject().getEmailAddress();
            dtos.add(temp);
        }
        return dtos;
    }

    @Transactional
    public Project updateProjectById(Integer id, ProjectDTO projectToBeUpdated) {

        Project project;
        Status status;
        TeamMembers teamMembers;

        Client client = clientRepository.findByClientId(projectToBeUpdated.clientId);

        Optional<Status> statusOptional = statusRepository.findByStatusName(projectToBeUpdated.statusName);

        Optional<TeamMembers> teamMembersOptional = teamMembersRepository.findByMemberId(projectToBeUpdated.teamMemberId);

        Optional<Project> projectOptional = projectRepository.findById(id);
        if (!projectOptional.isPresent()) {

            throw new RuntimeException("Could not find project with the id: " + id);
        } else {

            project = projectOptional.get();

            status = statusOptional.get();
            status.setStatusName(projectToBeUpdated.statusName);

            teamMembers = teamMembersOptional.get();
            teamMembers.setMemberId(projectToBeUpdated.teamMemberId);

            project.setName(projectToBeUpdated.projectName);
            project.setDescription(projectToBeUpdated.description);
            project.setStatusOfProject(status);
            project.setTeamMemberOfProject(teamMembers);
            project.setClient(client);
        }
        return projectRepository.save(project);
    }

    @Transactional
    public void deleteProjectById(Integer id) {

        projectRepository.deleteById(id);
    }
}