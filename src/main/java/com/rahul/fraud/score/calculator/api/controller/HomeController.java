package com.rahul.fraud.score.calculator.api.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

/**
 * Home redirection to swagger api documentation 
 */
@Controller
public class HomeController {
    @GetMapping(value = "/")
    public String index() {
         return "redirect:swagger-ui.html";
    }
}
