package com.agnes.challenge.WebPageControllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class ProjectsWebController {

    @GetMapping("/projects")
    public ModelAndView projectsPage() {
        return new ModelAndView("projects");
    }
}
