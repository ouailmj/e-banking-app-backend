package com.example.ebankingspg.java.request;

public class Addtypecontrat {
    private String name;
    private double Rate;

    public Addtypecontrat(String name, double rate) {
        this.name = name;
        Rate = rate;
    }

    public Addtypecontrat() {

    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public double getRate() {
        return Rate;
    }

    public void setRate(double rate) {
        Rate = rate;
    }
}
