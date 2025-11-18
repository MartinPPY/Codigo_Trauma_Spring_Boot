package com.martin.codigo.trauma.app.models;

public class UserDto {

    private String username;
    private String name;
    private String lastname;
    private Integer phone;
    private String password;
    private String email;

    public UserDto() {

    }

    public UserDto(String username, String name, String lastname, Integer phone, String password, String email) {
        this.username = username;
        this.name = name;
        this.lastname = lastname;
        this.phone = phone;
        this.password = password;
        this.email = email;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getLastname() {
        return lastname;
    }

    public void setLastname(String lastname) {
        this.lastname = lastname;
    }

    public Integer getPhone() {
        return phone;
    }

    public void setPhone(Integer phone) {
        this.phone = phone;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    

}
