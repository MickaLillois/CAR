package com.Carceles.TP2.Repositories;

import com.Carceles.TP2.Models.Produit;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public interface ProduitRepository extends CrudRepository<Produit, Long> {
    @Query("select p from Produit p "
            + " where p.qte_stock > 0")
    ArrayList<Produit> findProduitsDispos();
}