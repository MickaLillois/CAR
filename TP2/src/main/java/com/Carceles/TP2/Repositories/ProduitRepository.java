package com.Carceles.TP2.Repositories;

import com.Carceles.TP2.Models.Produit;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public interface ProduitRepository extends JpaRepository<Produit, Integer> {
    @Query("select p from Produit p "
            + " where p.qteStock > 0")
    ArrayList<Produit> findProduitsDispos();
}