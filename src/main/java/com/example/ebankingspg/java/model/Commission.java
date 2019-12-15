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
public class Commission implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "commission_seq")
    @SequenceGenerator(name = "commission_seq", sequenceName = "commission_seq", allocationSize = 1)
    private Long id;
    private double TVA;
    private double transfert;


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
     * @return double return the TVA
     */
    public double getTVA() {
        return TVA;
    }

    /**
     * @param TVA the TVA to set
     */
    public void setTVA(double TVA) {
        this.TVA = TVA;
    }

    /**
     * @return double return the transfert
     */
    public double getTransfert() {
        return transfert;
    }

    /**
     * @param transfert the transfert to set
     */
    public void setTransfert(double transfert) {
        this.transfert = transfert;
    }

}
