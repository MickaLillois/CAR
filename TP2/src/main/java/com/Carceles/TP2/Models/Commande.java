package com.Carceles.TP2.Models;

import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import java.time.LocalDate;

@Entity
@Table
public class Commande {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private Integer idUtilisateur;
    private Float prix;
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private LocalDate date;

    public Commande(){}

    public Commande(Integer idUtilisateur, Float prix, LocalDate date) {
        this.idUtilisateur = idUtilisateur;
        this.prix = prix;
        this.date = date;
    }

    @Override
    public String toString() {
        return "Commande{" +
                "id=" + id +
                ", idUtilisateur=" + idUtilisateur +
                ", prix=" + prix +
                ", date=" + date +
                '}';
    }

    public Integer getId() {
        return id;
    }
    public void setId(Integer id) {
        this.id = id;
    }
    public Integer getIdUtilisateur() {
        return idUtilisateur;
    }
    public void setIdUtilisateur(Integer idUtilisateur) {
        this.idUtilisateur = idUtilisateur;
    }
    public Float getPrix() {
        return prix;
    }
    public void setPrix(Float prix) {
        this.prix = prix;
    }
    public LocalDate getDate() {
        return date;
    }
    public void setDate(LocalDate date) {
        this.date = date;
    }
}
