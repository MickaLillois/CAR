package com.Carceles.TP2.Models;

import com.Carceles.TP2.LignecommandeId;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import java.time.LocalDate;

@Entity
@Table
@IdClass(LignecommandeId.class)
public class Lignecommande {
    @Id
    @Column(name="IDCOMMANDE")
    private Integer idCommande;
    @Id
    @Column(name="IDPRODUIT")
    private Integer idProduit;
    private Float prixunitaire;
    private Integer qte;

    public Lignecommande() {}

    public Lignecommande(Integer idCommande, Integer idProduit, Float prixunitaire, Integer qte) {
        this.idCommande = idCommande;
        this.idProduit = idProduit;
        this.prixunitaire = prixunitaire;
        this.qte = qte;
    }

    @Override
    public String toString() {
        return "Lignecommande{" +
                "idCommande=" + idCommande +
                ", idProduit=" + idProduit +
                ", prixunitaire=" + prixunitaire +
                ", qte=" + qte +
                '}';
    }

    public Integer getIdCommande() {
        return idCommande;
    }
    public void setIdCommande(Integer idCommande) {
        this.idCommande = idCommande;
    }
    public Integer getIdProduit() {
        return idProduit;
    }
    public void setIdProduit(Integer idProduit) {
        this.idProduit = idProduit;
    }
    public Float getPrixunitaire() {
        return prixunitaire;
    }
    public void setPrixunitaire(Float prixunitaire) {
        this.prixunitaire = prixunitaire;
    }
    public Integer getQte() {
        return qte;
    }
    public void setQte(Integer qte) {
        this.qte = qte;
    }
}
