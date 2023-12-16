package com.springteam.TechProduct.dto;

public class LoginResponeDTO {
    private String accessToken;
    private String tokenType;

    public LoginResponeDTO(String accessToken){
        this.accessToken = accessToken;
        tokenType = "Bearer";
    }

    public String getAccessToken() {
        return accessToken;
    }

    public String getTokenType() {
        return tokenType;
    }
}
