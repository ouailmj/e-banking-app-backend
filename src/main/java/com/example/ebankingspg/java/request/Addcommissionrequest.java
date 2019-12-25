package com.example.ebankingspg.java.request;

public class Addcommissionrequest {

    private double TVA;
    private double transfert;


public Addcommissionrequest(){}
public Addcommissionrequest(double TVA, double transfert ){}


    /**
     * @return Double return the transfert
     */
    public Double getTransfert() {
        return transfert;
    }

    /**
     * @param transfert the transfert to set
     */
    public void setTransfert(Double transfert) {
        this.transfert = transfert;
    }


    /**
     * @return double return the TVA
     */
    public double getTVA() {
        return TVA;
    }

    /**
     * @param TVA the TVA to set
     */
    public void setTVA(double TVA) {
        this.TVA = TVA;
    }

}
