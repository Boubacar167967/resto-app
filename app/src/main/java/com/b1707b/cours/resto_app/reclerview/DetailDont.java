package com.b1707b.cours.resto_app.reclerview;

public class DetailDont {
    private String nature;
    private String date;
    private String montant;

    public DetailDont(String nature, String date, String montant) {
        this.nature = nature;
        this.date = date;
        this.montant = montant;
    }

    public String getNature() {
        return nature;
    }

    public void setNature(String nature) {
        this.nature = nature;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getMontant() {
        return montant;
    }

    public void setMontant(String montant) {
        this.montant = montant;
    }
}
