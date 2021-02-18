package com.Carceles.TP2.Models;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="UTILISATEUR")
public class Utilisateur {
    @Id
    @Column(name="MAIL_UTI")
    private String addr_mail;
    @Column(name="NOM_UTI")
    private String nom;
    @Column(name="PRENOM_UTI")
    private String prenom;
    @Column(name="PSEUDO_UTI")
    private String pseudo;
    @Column(name="PWD_UTI")
    private String mot_de_passe;

    public Utilisateur(String addr_mail, String nom, String prenom, String pseudo, String mot_de_passe) {
        this.addr_mail = addr_mail;
        this.nom = nom;
        this.prenom = prenom;
        this.pseudo = pseudo;
        this.mot_de_passe = mot_de_passe;
    }

    //Getters and Setters
    public String getAddr_mail() {
        return addr_mail;
    }
    public void setAddr_mail(String addr_mail) {
        this.addr_mail = addr_mail;
    }
    public String getNom() {
        return nom;
    }
    public void setNom(String nom) {
        this.nom = nom;
    }
    public String getPrenom() {
        return prenom;
    }
    public void setPrenom(String prenom) {
        this.prenom = prenom;
    }
    public String getMot_de_passe() {
        return mot_de_passe;
    }
    public void setMot_de_passe(String mot_de_passe) {
        this.mot_de_passe = mot_de_passe;
    }
}
