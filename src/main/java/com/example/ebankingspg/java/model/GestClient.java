package com.example.ebankingspg.java.model;

import java.io.Serializable;
import java.util.Date;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.SequenceGenerator;
import javax.validation.constraints.NotNull;

import com.fasterxml.jackson.annotation.JsonBackReference;

@Entity
public class GestClient implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "gestclient_seq")
    @SequenceGenerator(name = "gestclient_seq", sequenceName = "gestclient_seq", allocationSize = 1)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "agenceId")
    @JsonBackReference
    private Agence agence;



public GestClient(){

}

    /**
     * @return Long return the id
     */
    public Long getId() {
        return id;
    }

    /**
     * @param id the id to set
     */
    public void setId(Long id) {
        this.id = id;
    }

    /**
     * @return Agence return the agence
     */
    public Agence getAgence() {
        return agence;
    }

    /**
     * @param agence the agence to set
     */
    public void setAgence(Agence agence) {
        this.agence = agence;
    }



}
