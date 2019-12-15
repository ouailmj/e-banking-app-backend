package com.example.ebankingspg.java.model;

import java.io.Serializable;
import java.util.Set;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;

import com.fasterxml.jackson.annotation.JsonBackReference;
import lombok.*;

@Entity
public class GestTransac extends User implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "gesttransac_seq")
    @SequenceGenerator(name = "gesttransacn_seq", sequenceName = "gesttransac_seq", allocationSize = 1)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "agencyId")
    @JsonBackReference
    private Agency agency;

public GestTransac(){

}

public GestTransac(String firstname, String lastname, String numtel, String email, String adress, String password, String token){
  super(firstname, lastname, numtel, email , adress, password, token);
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

  }


