package org.generation.api.dto;

public class LoginRequest {

    private String email;
    private String pass;

    public LoginRequest(String email, String pass){
        this.email = email;
        this.pass = pass;
    }// constructor LoginRequest

    public String getEmail() {
        return email;
    }// getEmail

    public void setEmail(String email) {
        this.email = email;
    }// setEmail

    public String getPass() {
        return pass;
    }// getPass

    public void setPass(String pass) {
        this.pass = pass;
    }// setPass
}// class LoginRequest
