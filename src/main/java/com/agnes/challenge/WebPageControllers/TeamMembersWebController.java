package com.agnes.challenge.WebPageControllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class TeamMembersWebController {

    @GetMapping("/members")
    public ModelAndView membersPage() {
        return new ModelAndView("members");
    }
}
