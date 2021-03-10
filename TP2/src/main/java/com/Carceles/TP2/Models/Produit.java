package com.Carceles.TP2.Models;

import javax.persistence.*;

@Entity
@Table
public class Produit {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private String nom;
    private String desc;
    private Float prix;
    @Column(name="QTESTOCK")
    private Integer qteStock;

    public Produit() { }

    public Produit(String nom, String desc, Float prix, Integer qteStock) {
        this.nom = nom;
        this.desc = desc;
        this.prix = prix;
        this.qteStock = qteStock;
    }

    @Override
    public String toString() {
        return "Produit{" +
                "id=" + id +
                ", nom='" + nom + '\'' +
                ", desc='" + desc + '\'' +
                ", prix=" + prix +
                ", qteStock=" + qteStock +
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

    public Integer getQteStock() {
        return qteStock;
    }

    public void setQteStock(Integer qteStock) {
        this.qteStock = qteStock;
    }
}
