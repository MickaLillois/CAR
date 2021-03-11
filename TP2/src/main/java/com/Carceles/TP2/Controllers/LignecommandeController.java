package com.Carceles.TP2.Controllers;

import com.Carceles.TP2.Models.Lignecommande;
import com.Carceles.TP2.Repositories.LignecommandeRepository;
import com.Carceles.TP2.Repositories.ProduitRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Controller
public class LignecommandeController {
    private LignecommandeRepository ligneRepo;
    private ProduitRepository produitRepo;

    @Autowired
    public LignecommandeController(LignecommandeRepository ligneRepo, ProduitRepository produitRepo){
        this.ligneRepo = ligneRepo;
        this.produitRepo = produitRepo;
    }

    @RequestMapping(path = "/detail")
    public String detailCommande(Model model, @RequestParam(required = false) String id){
        List<Lignecommande> listeLigne = (List<Lignecommande>) ligneRepo.findByIdCommande(Integer.parseInt(id));
        model.addAttribute("listeLigne", listeLigne);
        model.addAttribute("produitRepo",this.produitRepo);
        return "detail";
    }
}
