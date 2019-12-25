package com.example.ebankingspg.java.request;

public class Addagencerequest {
    private String name;
    private String address;
    private String phone;


    public Addagencerequest(String name, String address, String phone) {
        this.name=name;
        this.address = address;
        this.phone = phone;


    }

    public Addagencerequest() {
    }



    /**
     * @return String return the nom
     */
    public String getName() {
        return name;
    }

    /**
     * @param name the nom to set
     */
    public void setNom(String name) {
        this.name = name;
    }

    /**
     * @return String return the phone
     */
    public String getPhone() {
        return phone;
    }

    /**
     * @param phone the phone to set
     */
    public void setPhone(String phone) {
        this.phone = phone;
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

}
