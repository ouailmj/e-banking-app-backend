package com.example.ebankingspg.java.response;

import com.example.ebankingspg.java.model.GestClient;

import java.util.Set;

public class Gestclientresponse {
  private Set<GestClient> users;

    public Gestclientresponse() {
    }

    public Gestclientresponse(Set<GestClient> users) {
        this.users = users;
    }

    public Set<GestClient> getUsers() {
        return users;
    }

    public void setUsers(Set<GestClient> users) {
        this.users = users;
    }
}
