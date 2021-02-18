package com.Carceles.TP2.Controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class UtilisateurController {
    @GetMapping("/utilisateurs")
    public String index() {return "utilisateurs";}

    @GetMapping("/utilisateurs/nouveau")
    public String listerUtilisateurs() {return "utilisateurCreateOrUpdateForm";}

    @GetMapping("/utilisateurs/liste")
    public String creerUtilisateur() {return "utilisateursListe";}
}
