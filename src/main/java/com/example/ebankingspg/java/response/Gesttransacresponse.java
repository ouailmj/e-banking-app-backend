package com.example.ebankingspg.java.response;

import com.example.ebankingspg.java.model.GestTransac;

import java.util.Set;

public class Gesttransacresponse {
  private Set<GestTransac> users;

    public Gesttransacresponse() {
    }

    public Gesttransacresponse(Set<GestTransac> users) {
        this.users = users;
    }

    public Set<GestTransac> getUsers() {
        return users;
    }

    public void setUsers(Set<GestTransac> users) {
        this.users = users;
    }
}
