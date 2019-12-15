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
public class Agence implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "agence_seq")
    @SequenceGenerator(name = "agence_seq", sequenceName = "agence_seq", allocationSize = 1)
    private Long id;

    @OneToMany(mappedBy = "agence", cascade = CascadeType.ALL)
    private Set<Client> client;

    @OneToMany(mappedBy = "agence", cascade = CascadeType.ALL)
    private Set<GestClient> gestclient;

    @OneToMany(mappedBy = "agence", cascade = CascadeType.ALL)
    private Set<GestTransac> gesttransac;

public Agence(){

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
     * @return Set<Client> return the client
     */
    public Set<Client> getClient() {
        return client;
    }

    /**
     * @param client the client to set
     */
    public void setClient(Set<Client> client) {
        this.client = client;
    }





    /**
     * @return Set<GestClient> return the gestclient
     */
    public Set<GestClient> getGestclient() {
        return gestclient;
    }

    /**
     * @param gestclient the gestclient to set
     */
    public void setGestclient(Set<GestClient> gestclient) {
        this.gestclient = gestclient;
    }

    /**
     * @return Set<GestTransac> return the gesttransac
     */
    public Set<GestTransac> getGesttransac() {
        return gesttransac;
    }

    /**
     * @param gesttransac the gesttransac to set
     */
    public void setGesttransac(Set<GestTransac> gesttransac) {
        this.gesttransac = gesttransac;
    }

}
