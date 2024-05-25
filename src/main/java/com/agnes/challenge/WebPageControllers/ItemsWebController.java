package com.agnes.challenge.WebPageControllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class ItemsWebController {

    @GetMapping("/items")
    public ModelAndView itemsPage() {
        return new ModelAndView("items");
    }
}
