package com.example.ebankingspg.java.request;

public class Addgestclientrequest {
    private String firstname;
    private String lastname;
    private String email;
    private String address;
    private String phone;
    private String password;
    private Long idagence;

    public Addgestclientrequest(String firstname, String lastname, String email, String address, String phone, String password, Long idagence) {
        this.firstname = firstname;
        this.lastname = lastname;
        this.email = email;
        this.address = address;
        this.phone = phone;
        this.password=password;
this.idagence=idagence;
    }

    public Addgestclientrequest() {
    }

    public String getFirstname() {
        return firstname;
    }

    public void setFirstname(String firstname) {
        this.firstname = firstname;
    }

    public String getLastname() {
        return lastname;
    }

    public void setLastname(String lastname) {
        this.lastname = lastname;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }



    /**
     * @return String return the password
     */
    public String getPassword() {
        return password;
    }

    /**
     * @param password the password to set
     */
    public void setPassword(String password) {
        this.password = password;
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
     * @return Long return the idagence
     */
    public Long getIdagence() {
        return idagence;
    }

    /**
     * @param idagence the idagence to set
     */
    public void setIdagence(Long idagence) {
        this.idagence = idagence;
    }

}
