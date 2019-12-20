package com.example.ebankingspg.java.request;

public class AddClientRequest {
    private String firstname;
    private String lastname;
    private String email;
    private String address;
    private String phone;
    private AddImage image;
    private long subsciber;

    public AddClientRequest(String firstname, String lastname, String email, String address, String phone, AddImage image, long subsciber) {
        this.firstname = firstname;
        this.lastname = lastname;
        this.email = email;
        this.address = address;
        this.phone = phone;
        this.image = image;
        this.subsciber = subsciber;
    }

    public AddClientRequest() {
    }

    public long getSubsciber() {
        return subsciber;
    }

    public void setSubsciber(long subsciber) {
        this.subsciber = subsciber;
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

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public AddImage getImage() {
        return image;
    }

    public void setImage(AddImage image) {
        this.image = image;
    }
}
