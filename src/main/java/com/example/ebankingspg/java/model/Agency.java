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
import lombok.*;

@Entity
@Builder
@Getter
@Setter
@AllArgsConstructor
public class Agency extends AbstractAuditableEntity<User, Long> implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "agence_seq")
    @SequenceGenerator(name = "agence_seq", sequenceName = "agence_seq", allocationSize = 1)
    private Long id;
    private String nom;
    private String address;
    private String numtel;

    public Agency(){}

    @OneToMany(mappedBy = "agency", cascade = CascadeType.ALL)
    private Set<Client> client;

    @OneToMany(mappedBy = "agency", cascade = CascadeType.ALL)
    private Set<GestClient> gestClients;

    @OneToMany(mappedBy = "agency", cascade = CascadeType.ALL)
    private Set<GestTransac> gestTransacs;


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
     * @return Set<GestClient> return the gestClients
     */
    public Set<GestClient> getGestClients() {
        return gestClients;
    }

    /**
     * @param gestClients the gestClients to set
     */
    public void setGestClients(Set<GestClient> gestClients) {
        this.gestClients = gestClients;
    }

    /**
     * @return Set<GestTransac> return the gestTransacs
     */
    public Set<GestTransac> getGestTransacs() {
        return gestTransacs;
    }

    /**
     * @param gestTransacs the gestTransacs to set
     */
    public void setGestTransacs(Set<GestTransac> gestTransacs) {
        this.gestTransacs = gestTransacs;
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
     * @return String return the address
     */
    public String getAddress() {
        return address;
    }

    /**
     * @param address the address to set
     */
    public void setAddress(String address) {
        this.address = address;
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

}
