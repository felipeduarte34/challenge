package com.agnes.challenge.RestControllers;

import com.agnes.challenge.DTO.TeamMembersDTO;
import com.agnes.challenge.Entities.TeamMembers;
import com.agnes.challenge.Services.TeamMembersService;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;
import java.util.List;


@RestController
@RequestMapping("/api/v1")
public class TeamMembersController {

    final
    TeamMembersService teamMembersService;

    public TeamMembersController(final TeamMembersService teamMembersService) {
        this.teamMembersService = teamMembersService;
    }

    @GetMapping(value = "/member", produces = "application/json")
    public ResponseEntity<List<TeamMembersDTO>> getAllMembers() {

        List<TeamMembersDTO> allMembersDTO = teamMembersService.getAllMembers();
        return new ResponseEntity<>(allMembersDTO, HttpStatus.OK);
    }

    @GetMapping(value = "/member/firstName/{firstName}", produces = "application/json")
    public ResponseEntity<TeamMembersDTO> getTeamMemberByFirstName(@PathVariable String firstName) {

        TeamMembers teamMemberDTO = teamMembersService.findByFirstName(firstName);
        if (teamMemberDTO != null) {
            TeamMembersDTO teamMembersDTO = new TeamMembersDTO(teamMemberDTO);
            return new ResponseEntity<>(teamMembersDTO, HttpStatus.OK);
        } else {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "No team member with first name: " + firstName + " was found.");
        }
    }

    @GetMapping(value = "member/lastName/{lastName}", produces = "application/json")
    public ResponseEntity<TeamMembersDTO> getTeamMemberByLastName(@PathVariable String lastName) {

        TeamMembers teamMemberDTO = teamMembersService.findByLastName(lastName);
        if (teamMemberDTO != null) {
            TeamMembersDTO teamMembersDTO = new TeamMembersDTO(teamMemberDTO);
            return new ResponseEntity<>(teamMembersDTO, HttpStatus.OK);
        } else {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "No team member with last name: " + lastName + " was found.");
        }
    }

    @GetMapping(value = "/member/emailAddress/{emailAddress}", produces = "application/json")
    public ResponseEntity<TeamMembersDTO> getTeamMemberByEmailAddress(@PathVariable String emailAddress) {

        TeamMembers teamMemberDTO = teamMembersService.findByEmailAdddress(emailAddress);
        if (teamMemberDTO != null) {
            TeamMembersDTO teamMembersDTO = new TeamMembersDTO(teamMemberDTO);
            return new ResponseEntity<>(teamMembersDTO, HttpStatus.OK);
        } else {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "No team member with the email address: " + emailAddress + " was found.");
        }
    }

    @PostMapping(value = "member", consumes = "application/json")
    public ResponseEntity<TeamMembersDTO> createMember(@RequestBody TeamMembersDTO teamMembersDTO) {

        TeamMembers teamMembers = teamMembersService.saveTeamMember(teamMembersDTO);
        return new ResponseEntity<>(new TeamMembersDTO(teamMembers), HttpStatus.OK);
    }

    @DeleteMapping("/member/{memberId}")
    public void deleteMemberById(@PathVariable Integer memberId) {

        teamMembersService.deleteTeamMemberById(memberId);
    }

    @PutMapping(value = "/member/{memberId}", consumes = "application/json", produces = "application/json")
    public ResponseEntity<TeamMembersDTO> updateMemberById(@PathVariable(name = "memberId") Integer memberId, @RequestBody TeamMembersDTO teamMembersDTO) {

        TeamMembers t = teamMembersService.getMemberById(memberId);
        if (t != null) {
            TeamMembers teamMembers = teamMembersService.updateMemberById(memberId, teamMembersDTO);
            return new ResponseEntity<>(new TeamMembersDTO(teamMembers), HttpStatus.OK);
        }
        else {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Could not find project with the id: " + memberId);
        }
    }
}
