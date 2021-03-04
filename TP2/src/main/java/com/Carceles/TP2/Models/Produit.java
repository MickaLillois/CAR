package com.Carceles.TP2.Models;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="PRODUIT")
public class Produit {
    @Id
    @Column(name="ID_PROD")
    private Integer id;
    @Column(name="NOM_PROD")
    private String nom;
    @Column(name="DESC_PROD")
    private String desc;
    @Column(name="PRIX_PROD")
    private Float prix;
    @Column(name="QTE_STOCK_PROD")
    private Integer qte_stock;

    public Produit() { }

    public Produit(Integer id, String nom, String desc, Float prix, Integer qte_stock) {
        this.id = id;
        this.nom = nom;
        this.desc = desc;
        this.prix = prix;
        this.qte_stock = qte_stock;
    }

    @Override
    public String toString() {
        return "Produit{" +
                "id=" + id +
                ", nom='" + nom + '\'' +
                ", desc='" + desc + '\'' +
                ", prix=" + prix +
                ", qte_stock=" + qte_stock +
                '}';
    }

    //Getters and setters
    public Integer getId() {
        return id;
    }
    public void setId(Integer id) {
        this.id = id;
    }
    public String getNom() {
        return nom;
    }
    public void setNom(String nom) {
        this.nom = nom;
    }
    public String getDesc() {
        return desc;
    }
    public void setDesc(String desc) {
        this.desc = desc;
    }
    public Float getPrix() {
        return prix;
    }
    public void setPrix(Float prix) {
        this.prix = prix;
    }
    public Integer getQte_stock() {
        return qte_stock;
    }
    public void setQte_stock(Integer qte_stock) {
        this.qte_stock = qte_stock;
    }
}
