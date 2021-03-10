package com.Carceles.TP2.Controllers;

import com.Carceles.TP2.Models.Produit;
import com.Carceles.TP2.Models.Utilisateur;
import com.Carceles.TP2.Repositories.ProduitRepository;
import com.Carceles.TP2.Repositories.UtilisateurRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import javax.servlet.http.HttpSession;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

@Controller
public class ProduitController {
    private ProduitRepository repository;

    @Autowired
    public ProduitController(ProduitRepository repository) {
        this.repository = repository;
    }


    /*@GetMapping("/produits/liste")
    public String listerProduits(Model model) {
        ArrayList<Produit> listeProduits = (ArrayList<Produit>) this.produitRepository.findAll();
        model.addAttribute("listeProduitsHTML",listeProduits);
        return "produitsListe";
    }*/

    @RequestMapping(path = "/carte")
    public String listerProduitsDispos(Model model) {
        ArrayList<Produit> listeProduits = this.repository.findProduitsDispos();
        model.addAttribute("listeProduits",listeProduits);
        return "carte";
    }

    @RequestMapping(path = "/panier")
    public String ajouterPanier(@RequestParam(required = false) String id, HttpSession session, Model model){
        if(session.getAttribute("panier") == null){
            session.setAttribute("panier", new HashMap<Integer,Integer>());
        }
        if(id != null){
            Produit leProduit = (Produit) repository.findById(Integer.parseInt(id)).get();
            HashMap<Integer, Integer> panier = (HashMap<Integer, Integer>) session.getAttribute("panier");
            if(panier.containsKey(Integer.parseInt(id))){
                panier.replace(Integer.parseInt(id), (panier.get(Integer.parseInt(id)) + 1));
            }else{
                panier.put(Integer.parseInt(id), 1);
            }
            session.setAttribute("panier",panier);
        }
        model.addAttribute("repository",repository);
        return "panier";
    }

    @RequestMapping(path = "/retirer")
    public String retirerPanier(@RequestParam(required = false) String id, HttpSession session) {
        HashMap<Integer,Integer> panier = (HashMap<Integer,Integer>) session.getAttribute("panier");

        if(id != null) {
            int idRetirer = Integer.parseInt(id);
            if (panier.containsKey(idRetirer)) {
                panier.replace(idRetirer, (panier.get(idRetirer) - 1));
            }
            if (panier.get(idRetirer) < 1) {
                panier.remove(idRetirer);
            }
            session.setAttribute("panier", panier);
        }
        return "redirect:panier";
    }


    /*@PostMapping("/produits/processNouveau")
    public String processCreerProduit(@RequestParam("idprod") String idprod,
                                          @RequestParam("nom") String nom,
                                          @RequestParam("desc") String desc,
                                          @RequestParam("prix") String prix,
                                          @RequestParam("qteStock") String qteStock) {
        Produit newProduit = new Produit(nom,desc,Float.parseFloat(prix),Integer.parseInt(qteStock));
        this.repository.save(newProduit);
        return "redirect:/produits/liste";
    }*/
}
