package com.Carceles.TP2.Controllers;

import com.Carceles.TP2.Models.Commande;
import com.Carceles.TP2.Models.Utilisateur;
import com.Carceles.TP2.Repositories.CommandeRepository;
import com.Carceles.TP2.Repositories.UtilisateurRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpSession;
import java.util.*;

@Controller
public class UtilisateurController {
    private UtilisateurRepository utilisateurRepo;
    private CommandeRepository commandeRepo;

    @Autowired
    public UtilisateurController(UtilisateurRepository utilisateurRepo, CommandeRepository commandeRepo) {
        this.utilisateurRepo = utilisateurRepo;
        this.commandeRepo = commandeRepo;
    }

    @RequestMapping(path = "/utilisateurs")
    public String mainUtilisateur() {return "utilisateurs";}

    @RequestMapping(path = "/connexion")
    public String connexion(){ return "connexion";}

    @RequestMapping(path = "/deconnexion")
    public String deconnexion(){ return "deconnexion";}

    @RequestMapping(path = "/inscription")
    public String inscription(){ return "inscription";}

    @RequestMapping(path = "/profil")
    public String profil(HttpSession session, Model model){
        Utilisateur utilisateur = utilisateurRepo.findById((Integer) session.getAttribute("idUtilisateur")).get();
        List<Commande> listeCommande = commandeRepo.findByIdUtilisateur(utilisateur.getId());
        model.addAttribute("utilisateur", utilisateur);
        model.addAttribute("listeCommande", listeCommande);
        return "profil";
    }

    @RequestMapping(path = "/checkConnexion")
    public String checkConnexion(Model model, HttpSession session, @RequestParam(required = false) String mail, @RequestParam(required = false) String mdpForm){
        Utilisateur utilisateur = null;
        if(session.getAttribute("idUtilisateur") == null) {
            if (mail.equals("") || mdpForm.equals("")){
                return "redirect:connexion?vide=true";
            }
            else{
                if (utilisateurRepo.findByMail(mail).size() == 0){
                    return "redirect:connexion?existe=false";
                }
                else if(utilisateurRepo.findByMail(mail).get(0).getMdp().equals(mdpForm)){
                    utilisateur = (Utilisateur) utilisateurRepo.findByMail(mail).get(0);
                }
                else{
                    return "redirect:connexion?mdp=false";
                }
                session.setAttribute("idUtilisateur", utilisateur.getId());
            }
        }
        else{
            utilisateur = utilisateurRepo.findById((Integer) session.getAttribute("idUtilisateur")).get();
        }
        List<Commande> listeCommande = commandeRepo.findByIdUtilisateur(utilisateur.getId());
        model.addAttribute("utilisateur", utilisateur);  //model ici = request dans jsp
        model.addAttribute("listeCommande", listeCommande);
        return "profil";
    }

    @RequestMapping(path = "/checkInscription")
    public String checkInscription(Model model,
                                   HttpSession session,
                                   @RequestParam(required = false) String mail,
                                   @RequestParam(required = false) String nom,
                                   @RequestParam(required = false) String prenom,
                                   @RequestParam(required = false) String pseudo,
                                   @RequestParam(required = false) String mdpForm){
        if (mail.equals("") || nom.equals("") || prenom.equals("") || pseudo.equals("") || mdpForm.equals("")){
            return "redirect:inscription?vide=true";
        }
        else if(utilisateurRepo.findByMail(mail).size() != 0){
            return "redirect:inscription?existe=true";
        }
        else{
            Utilisateur utilisateur = new Utilisateur(mail,nom,prenom,pseudo,mdpForm);
            this.utilisateurRepo.save(utilisateur);
            session.setAttribute("idUtilisateur", utilisateur.getId());
            List<Commande> listeCommande = commandeRepo.findByIdUtilisateur(utilisateur.getId());
            model.addAttribute("utilisateur", utilisateur);  //model ici = request dans jsp
            model.addAttribute("listeCommande", listeCommande);
            return "profil";
        }
    }









    /*@GetMapping("/utilisateurs/nouveau")
    public String creerUtilisateur() {return "utilisateurCreateOrUpdateForm";}*/

    /*@PostMapping("/utilisateurs/processNouveau")
    public String processCreerUtilisateur(@RequestParam("mail_addr") String mail_addr,
                                          @RequestParam("nom") String nom,
                                          @RequestParam("prenom") String prenom,
                                          @RequestParam("pseudo") String pseudo,
                                          @RequestParam("password") String password) {
        Utilisateur newUtilisateur = new Utilisateur(mail_addr,nom,prenom,pseudo,password);
        this.utilisateurRepository.save(newUtilisateur);
        return "redirect:/utilisateurs/liste";
    }*/

    /*@PostMapping("/utilisateurs/verifConnexion")
    public String checkConnexion(@RequestParam("mail_addr") String mail_addr, @RequestParam("password") String password){
        if(this.utilisateurRepository.getMailId(mail_addr) != null){

        }
        return ""; //remplir le redirect
    }*/

    /*@GetMapping("/utilisateurs/liste")
    public String listerUtilisateurs(Model model) {
        ArrayList<Utilisateur> listeUtilisateurs = (ArrayList<Utilisateur>) this.utilisateurRepository.findAll();
        model.addAttribute("listeUtilisateursHTML",listeUtilisateurs);
        return "utilisateursListe";
    }*/
}
