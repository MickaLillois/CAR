package com.Carceles.TP2.Repositories;

import com.Carceles.TP2.Models.Commande;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CommandeRepository extends JpaRepository<Commande, Integer> {
}
