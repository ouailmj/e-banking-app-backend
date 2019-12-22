package com.example.ebankingspg.java.request;

public class RechargeAccountRequest {
    private String rib;
    private Double somme;
    private int id_compte;

    public RechargeAccountRequest() {
    }

    public RechargeAccountRequest(String rib, Double somme, int id_compte) {
        this.rib = rib;
        this.somme = somme;
        this.id_compte = id_compte;
    }

    public String getRib() {
        return rib;
    }

    public void setRib(String rib) {
        this.rib = rib;
    }

    public Double getSomme() {
        return somme;
    }

    public void setSomme(Double somme) {
        this.somme = somme;
    }

    public int getId_compte() {
        return id_compte;
    }

    public void setId_compte(int id_compte) {
        this.id_compte = id_compte;
    }
}
