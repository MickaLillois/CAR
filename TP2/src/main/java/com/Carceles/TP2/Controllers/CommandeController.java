package com.Carceles.TP2.Controllers;

import com.Carceles.TP2.Models.Produit;
import com.Carceles.TP2.Repositories.CommandeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpSession;
import java.util.ArrayList;
import java.util.HashMap;

@Controller
public class CommandeController {
    private CommandeRepository repository;

    @Autowired
    public CommandeController(CommandeRepository repository) {
        this.repository = repository;
    }

    @RequestMapping(path = "/commander")
    public String enregistrerCommande(HttpSession session) {
        HashMap<Integer, Integer> panier = (HashMap<Integer, Integer>) session.getAttribute("panier");

        return "";
    }
}
