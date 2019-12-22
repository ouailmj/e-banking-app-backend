package com.example.ebankingspg.java.request;

public class AddAccountRequest {

    private Double balance;
    private String typecontrat;
    private long id_manager_client;
    private long id_client;

    public AddAccountRequest(Double balance, String typecontrat, long id_manager_client, long id_client) {
        this.balance = balance;
        this.typecontrat = typecontrat;
        this.id_manager_client = id_manager_client;
        this.id_client = id_client;
    }

    public Double getBalance() {
        return balance;
    }

    public void setBalance(Double balance) {
        this.balance = balance;
    }

    public String getTypecontrat() {
        return typecontrat;
    }

    public void setTypecontrat(String typecontrat) {
        this.typecontrat = typecontrat;
    }

    public long getId_manager_client() {
        return id_manager_client;
    }

    public void setId_manager_client(long id_manager_client) {
        this.id_manager_client = id_manager_client;
    }

    public long getId_client() {
        return id_client;
    }

    public void setId_client(long id_client) {
        this.id_client = id_client;
    }

    public AddAccountRequest() {
    }


}
