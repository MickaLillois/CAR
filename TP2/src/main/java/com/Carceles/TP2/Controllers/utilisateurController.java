package com.Carceles.TP2.Controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class utilisateurController {
    @GetMapping("/utilisateurs")
    public String index() {return "utilisateurs";}
}
