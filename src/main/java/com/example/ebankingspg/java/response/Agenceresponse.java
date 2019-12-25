package com.example.ebankingspg.java.response;

import com.example.ebankingspg.java.model.Agency;

import java.util.Set;

public class Agenceresponse {
    private Set<Agency> agences;

    public Agenceresponse() {
    }

    public Agenceresponse(Set<Agency> agences){
      this.agences=agences;
    }



    /**
     * @return Set<Agency> return the agences
     */
    public Set<Agency> getAgences() {
        return agences;
    }

    /**
     * @param agences the agences to set
     */
    public void setAgences(Set<Agency> agences) {
        this.agences = agences;
    }

}
