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
public class Devise implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "devise_seq")
    @SequenceGenerator(name = "devise_seq", sequenceName = "devise_seq", allocationSize = 1)
    private Long id;
    private String nom;
    private double taux_change;
    private Date lastmodif;

   public Devise(){

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
     * @return String return the nom
     */
    public String getNom() {
        return nom;
    }

    /**
     * @param nom the nom to set
     */
    public void setNom(String nom) {
        this.nom = nom;
    }

    /**
     * @return double return the taux_change
     */
    public double getTaux_change() {
        return taux_change;
    }

    /**
     * @param taux_change the taux_change to set
     */
    public void setTaux_change(double taux_change) {
        this.taux_change = taux_change;
    }

    /**
     * @return Date return the lastmodif
     */
    public Date getLastmodif() {
        return lastmodif;
    }

    /**
     * @param lastmodif the lastmodif to set
     */
    public void setLastmodif(Date lastmodif) {
        this.lastmodif = lastmodif;
    }

}
