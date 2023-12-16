package com.springteam.TechProduct.dto;

public class LoginDTO {
    private String userName;
    private String password;

    //constructor

    public LoginDTO(){}
    public LoginDTO(String userName, String password) {
        this.userName = userName;
        this.password = password;
    }

    //getter & setter

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    //to string

    @Override
    public String toString() {
        return "LoginDTO{" +
                "userName='" + userName + '\'' +
                ", password='" + password + '\'' +
                '}';
    }

}
