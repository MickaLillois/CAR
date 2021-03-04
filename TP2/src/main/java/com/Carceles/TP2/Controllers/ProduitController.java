package com.Carceles.TP2.Controllers;

import com.Carceles.TP2.Models.Produit;
import com.Carceles.TP2.Models.Utilisateur;
import com.Carceles.TP2.Repositories.ProduitRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import java.util.ArrayList;
import java.util.List;

@Controller
public class ProduitController {
    @Autowired
    ProduitRepository produitRepository;

    @GetMapping("/produits")
    public String mainUtilisateur() {return "produits";}

    /*@GetMapping("/produits/liste")
    public String listerProduits(Model model) {
        ArrayList<Produit> listeProduits = (ArrayList<Produit>) this.produitRepository.findAll();
        model.addAttribute("listeProduitsHTML",listeProduits);
        return "produitsListe";
    }*/

    @GetMapping("/produits/liste")
    public String listerProduits(Model model) {
        ArrayList<Produit> listeProduits = this.produitRepository.findProduitsDispos();
        model.addAttribute("listeProduitsHTML",listeProduits);
        return "produitsListe";
    }

    @GetMapping("/produits/nouveau")
    public String creerProduit() {return "produitCreateOrUpdateForm";}

    @PostMapping("/produits/processNouveau")
    public String processCreerProduit(@RequestParam("idprod") String idprod,
                                          @RequestParam("nom") String nom,
                                          @RequestParam("desc") String desc,
                                          @RequestParam("prix") String prix,
                                          @RequestParam("qteStock") String qteStock) {
        Produit newProduit = new Produit(Integer.parseInt(idprod),nom,desc,Float.parseFloat(prix),Integer.parseInt(qteStock));
        this.produitRepository.save(newProduit);
        return "redirect:/produits/liste";
    }
}
