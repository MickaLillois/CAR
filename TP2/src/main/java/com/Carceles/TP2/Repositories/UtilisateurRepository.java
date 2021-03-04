package com.Carceles.TP2.Repositories;

import com.Carceles.TP2.Models.Utilisateur;
import org.springframework.data.repository.CrudRepository;

import java.util.Optional;

public interface UtilisateurRepository extends CrudRepository<Utilisateur, Long> { }
