package com.example.ebankingspg.java.model;

import java.io.Serializable;
import java.util.Date;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
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
public class Client extends User implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "client_seq")
    @SequenceGenerator(name = "client_seq", sequenceName = "client_seq", allocationSize = 1)
    private Long id;


    @OneToMany(mappedBy = "client", cascade = CascadeType.ALL)
    private Set<Account> account;

    @ManyToOne
    @JoinColumn(name = "agenceId")
    @JsonBackReference
    private Agence agence;



    public Client(){}

    public Client(String firstname, String lastname, String numtel, String email, String adress, Agence agence, String password){
      super(firstname, lastname, numtel, email , adress, password);
      this.agence=agence;
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
     * @return Set<Account> return the account
     */
    public Set<Account> getAccount() {
        return account;
    }

    /**
     * @param account the account to set
     */
    public void setAccount(Set<Account> account) {
        this.account = account;
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
