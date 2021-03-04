package com.Carceles.TP2.Controllers;

import com.Carceles.TP2.Models.Utilisateur;
import com.Carceles.TP2.Repositories.UtilisateurRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import java.util.ArrayList;

@Controller
public class UtilisateurController {

    @Autowired
    UtilisateurRepository utilisateurRepository;

    @GetMapping("/utilisateurs")
    public String mainUtilisateur() {return "utilisateurs";}

    @GetMapping("/utilisateurs/nouveau")
    public String creerUtilisateur() {return "utilisateurCreateOrUpdateForm";}

    @PostMapping("/utilisateurs/processNouveau")
    public String processCreerUtilisateur(@RequestParam("mail_addr") String mail_addr,
                                          @RequestParam("nom") String nom,
                                          @RequestParam("prenom") String prenom,
                                          @RequestParam("pseudo") String pseudo,
                                          @RequestParam("password") String password) {
        Utilisateur newUtilisateur = new Utilisateur(mail_addr,nom,prenom,pseudo,password);
        this.utilisateurRepository.save(newUtilisateur);
        return "redirect:/utilisateurs/liste";
    }

    @GetMapping("/utilisateurs/liste")
    public String listerUtilisateurs(Model model) {
        ArrayList<Utilisateur> listeUtilisateurs = (ArrayList<Utilisateur>) this.utilisateurRepository.findAll();
        model.addAttribute("listeUtilisateursHTML",listeUtilisateurs);
        return "utilisateursListe";
    }
}
