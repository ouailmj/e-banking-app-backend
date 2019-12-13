package com.example.ebankingspg.java.model;

import java.io.Serializable;
import java.util.Date;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.SequenceGenerator;
import javax.validation.constraints.NotNull;

@Entity

public class User implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "user_seq")
    @SequenceGenerator(name = "user_seq", sequenceName = "user_seq", allocationSize = 1)
    private Long id;
    private String username;
    private String password;
    private String firstname;
    private String lastname;
    private String email;
    private String numtel;
    private Date datecreation;
    private Date dateupdate;
    private String adress;
    private String status;
    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL)
    private Set<Account> account;

    public User(){}

    public User(String status) {
        this.status = status;
    }

    public User(String password, String email) {
        this.password = password;
        this.email = email;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
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
     * @return String return the username
     */
    public String getUsername() {
        return username;
    }

    /**
     * @param username the username to set
     */
    public void setUsername(String username) {
        this.username = username;
    }

    /**
     * @return String return the password
     */
    public String getPassword() {
        return password;
    }

    /**
     * @param password the password to set
     */
    public void setPassword(String password) {
        this.password = password;
    }


    /**
     * @return String return the firstname
     */
    public String getFirstname() {
        return firstname;
    }

    /**
     * @param firstname the firstname to set
     */
    public void setFirstname(String firstname) {
        this.firstname = firstname;
    }

    /**
     * @return String return the lastname
     */
    public String getLastname() {
        return lastname;
    }

    /**
     * @param lastname the lastname to set
     */
    public void setLastname(String lastname) {
        this.lastname = lastname;
    }

    /**
     * @return String return the email
     */
    public String getEmail() {
        return email;
    }

    /**
     * @param email the email to set
     */
    public void setEmail(String email) {
        this.email = email;
    }

    /**
     * @return String return the numtel
     */
    public String getNumtel() {
        return numtel;
    }

    /**
     * @param numtel the numtel to set
     */
    public void setNumtel(String numtel) {
        this.numtel = numtel;
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
     * @return Date return the dateupdate
     */
    public Date getDateupdate() {
        return dateupdate;
    }

    /**
     * @param dateupdate the dateupdate to set
     */
    public void setDateupdate(Date dateupdate) {
        this.dateupdate = dateupdate;
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
     * @return String return the adress
     */
    public String getAdress() {
        return adress;
    }

    /**
     * @param adress the adress to set
     */
    public void setAdress(String adress) {
        this.adress = adress;
    }

}
