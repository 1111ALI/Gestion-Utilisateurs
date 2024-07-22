package com.almo.gestionUtilisateur.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class ViewController {
    @GetMapping({" ", "/"})
    public String home(){

        return "index";
    }
    @GetMapping("/contact")
    public String contact(){

        return "contact";
    }

}
