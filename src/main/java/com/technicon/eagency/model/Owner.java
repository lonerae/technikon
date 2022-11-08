package com.technicon.eagency.model;

/**
 *
 * @author Nick
 */
public class Owner {

    private long id;
    private String name;
    private String surname;
    private String address;
    private String phoneNumber;
    private String email;
    private String username;
    private String password;

    public Owner() {
    }

    public Owner(long id, String name, String surname, String address, String phoneNumber, String email, String username, String password) {
        this.id = id;
        this.surname = surname;
        this.address = address;
        this.phoneNumber = phoneNumber;
        this.email = email;
        this.username = username;
        this.password = password;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    @Override
    public String toString() {
        return "Owner{" + "id=" + id + ", name=" + name + ", surname=" + surname
                + ", address=" + address + ", phoneNumber=" + phoneNumber
                + ", email=" + email + ", username=" + username + ", password=" + password + '}';
    }

}
