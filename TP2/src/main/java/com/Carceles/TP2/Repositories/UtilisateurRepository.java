package com.Carceles.TP2.Repositories;

import com.Carceles.TP2.Models.Produit;
import com.Carceles.TP2.Models.Utilisateur;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public interface UtilisateurRepository extends JpaRepository<Utilisateur, Integer> {
    /*@Query("select addr_mail from utilisateur u "
            + " where u.addr_mail = ?1")
    String getMailId(String mail_addr);
    */

    List<Utilisateur> findByMail(String mail);

}
