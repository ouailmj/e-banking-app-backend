package com.example.ebankingspg.java.request;

public class AdddeviseRequest {

    private String name;
    private Double exchangeRate;



    public AdddeviseRequest(String name, Double exchangeRate) {
       this.name=name;
       this.exchangeRate=exchangeRate;


    }


    AdddeviseRequest(){

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
     * @return Double return the exchangeRate
     */
    public Double getExchangeRate() {
        return exchangeRate;
    }

    /**
     * @param exchangeRate the exchangeRate to set
     */
    public void setExchangeRate(Double exchangeRate) {
        this.exchangeRate = exchangeRate;
    }

}
