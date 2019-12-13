package com.example.ebankingspg.java.model;
import java.io.Serializable;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Entity;

@Entity
public class Users_roles implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "YOUR_ENTITY_SEQ6")
    @SequenceGenerator(name = "YOUR_ENTITY_SEQ6", sequenceName = "YOUR_ENTITY_SEQ6", allocationSize = 1)
    private Long id;
    private String username;
    private String role;

    public Users_roles() {

    }

    public Users_roles(String username, String role) {
        this.username = username;
        this.role = role;
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
     * @return String return the role
     */
    public String getRole() {
        return role;
    }

    /**
     * @param role the role to set
     */
    public void setRole(String role) {
        this.role = role;
    }

}
