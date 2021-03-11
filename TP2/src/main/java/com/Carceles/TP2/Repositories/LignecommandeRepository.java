package com.Carceles.TP2.Repositories;

import com.Carceles.TP2.Models.Commande;
import com.Carceles.TP2.Models.Lignecommande;
import com.Carceles.TP2.Models.Utilisateur;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface LignecommandeRepository extends JpaRepository<Lignecommande, Integer> {
    List<Lignecommande> findByIdCommande(Integer idCommande);
}
