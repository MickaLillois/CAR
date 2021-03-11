package com.Carceles.TP2;

import javax.persistence.Id;
import java.io.Serializable;
import java.util.Objects;

public class LignecommandeId implements Serializable {

    private Integer idCommande;
    private Integer idProduit;

    public LignecommandeId(Integer idCommande, Integer idProduit) {
        this.idCommande = idCommande;
        this.idProduit = idProduit;
    }

    public LignecommandeId() {
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        LignecommandeId that = (LignecommandeId) o;
        return Objects.equals(idCommande, that.idCommande) && Objects.equals(idProduit, that.idProduit);
    }

    @Override
    public int hashCode() {
        return Objects.hash(idCommande, idProduit);
    }
}
