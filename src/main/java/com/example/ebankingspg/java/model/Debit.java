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
public class Debit implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "debit_seq")
    @SequenceGenerator(name = "debit_seq", sequenceName = "debit_seq", allocationSize = 1)
    private Long id;
    private String requete;
    private Date datecreation;
    private boolean success;


    public Debit(){}

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
     * @return String return the requete
     */
    public String getRequete() {
        return requete;
    }

    /**
     * @param requete the requete to set
     */
    public void setRequete(String requete) {
        this.requete = requete;
    }

    /**
     * @return Date return the datecreation
     */
    public Date getDatecreation() {
        return datecreation;
    }

    /**
     * @param datecreation the datecreation to set
     */
    public void setDatecreation(Date datecreation) {
        this.datecreation = datecreation;
    }

    /**
     * @return boolean return the success
     */
    public boolean isSuccess() {
        return success;
    }

    /**
     * @param success the success to set
     */
    public void setSuccess(boolean success) {
        this.success = success;
    }

}
