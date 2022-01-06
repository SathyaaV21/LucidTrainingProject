package com.example.demo.model;



public class AuthenticationResponse {

    private final String Jwt;

    public AuthenticationResponse(String Jwt) {
        this.Jwt = Jwt;
    }

    public String getJwt() {
        return Jwt;
    }
}
