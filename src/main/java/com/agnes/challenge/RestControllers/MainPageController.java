package com.agnes.challenge.RestControllers;

import com.agnes.challenge.Services.ProjectService;
import com.agnes.challenge.Services.StatusService;
import com.agnes.challenge.Services.TeamMembersService;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class MainPageController {

    final
    ProjectService projectService;

    final
    StatusService statusService;

    final
    TeamMembersService teamMembersService;

    public MainPageController(final ProjectService projectService, final StatusService statusService, final TeamMembersService teamMembersService) {
        this.projectService = projectService;
        this.statusService = statusService;
        this.teamMembersService = teamMembersService;
    }

    @GetMapping("/")
    public ModelAndView mainPage() {
        return new ModelAndView("main_page");
    }
}
