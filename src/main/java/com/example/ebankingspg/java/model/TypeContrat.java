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
import lombok.*;

@Entity
@Builder
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class TypeContrat extends AbstractAuditableEntity<User, Long> implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "typecontrat_seq")
    @SequenceGenerator(name = "typecontrat_seq", sequenceName = "typecontrat_seq", allocationSize = 1)
    private Long id;
    private String name;
    private double Rate; //taux


    @OneToMany(mappedBy = "typecontrat", cascade = CascadeType.ALL)
    private Set<Account> Account;


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
     * @return String return the name
     */
    public String getName() {
        return name;
    }

    /**
     * @param name the name to set
     */
    public void setName(String name) {
        this.name = name;
    }




    /**
     * @return Set<Account> return the Account
     */
    public Set<Account> getAccount() {
        return Account;
    }

    /**
     * @param Account the Account to set
     */
    public void setAccount(Set<Account> Account) {
        this.Account = Account;
    }

}
