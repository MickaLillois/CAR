package com.Carceles.TP2.Controllers;

import com.Carceles.TP2.Models.Commande;
import com.Carceles.TP2.Models.Produit;
import com.Carceles.TP2.Models.Utilisateur;
import com.Carceles.TP2.Repositories.CommandeRepository;
import com.Carceles.TP2.Repositories.ProduitRepository;
import com.Carceles.TP2.Repositories.UtilisateurRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpSession;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashMap;

@Controller
public class CommandeController {
    private CommandeRepository commandeRepo;
    private ProduitRepository produitRepo;
    private UtilisateurRepository utilisateurRepo;

    @Autowired
    public CommandeController(CommandeRepository commandeRepo, ProduitRepository produitRepo, UtilisateurRepository utilisateurRepo) {
        this.produitRepo = produitRepo;
        this.commandeRepo = commandeRepo;
        this.utilisateurRepo = utilisateurRepo;
    }

    @RequestMapping(path = "/commander")
    public String enregistrerCommande(HttpSession session, Model model) {
        if(session.getAttribute("idUtilisateur") == null){
            return "connexion";
        }
        else{
            HashMap<Integer, Integer> panier = (HashMap<Integer, Integer>) session.getAttribute("panier");
            System.out.println("ici 1");
            if(panier.size() == 0){
                System.out.println("ici 2");
                return "redirect:panier?vide=true";
            }
            else {
                Integer idUtilisateur = (Integer) session.getAttribute("idUtilisateur");
                Float total = 0.00F;
                for (int id : panier.keySet()) {
                    Produit leProd = produitRepo.findById(id).get();
                    Integer qte = panier.get(id);
                    total += qte * leProd.getPrix();
                }
                Commande nouvCommande = new Commande(idUtilisateur, total, LocalDate.now());
                this.commandeRepo.save(nouvCommande);



                //////////////////////////
                // ajout lignes commande
                //////////////////////////

                Utilisateur utilisateur = utilisateurRepo.findById((Integer) session.getAttribute("idUtilisateur")).get();
                model.addAttribute("utilisateur", utilisateur);
                session.setAttribute("panier",null);
                return "redirect:profil";
            }
        }
    }

}
