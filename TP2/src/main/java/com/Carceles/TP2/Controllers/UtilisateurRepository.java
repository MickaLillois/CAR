package com.Carceles.TP2.Controllers;

import com.Carceles.TP2.Models.Utilisateur;
import org.springframework.data.repository.CrudRepository;

public interface UtilisateurRepository extends CrudRepository<Utilisateur, Long> {}
