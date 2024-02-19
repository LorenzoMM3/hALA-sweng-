package com.hala.mywebapp;

public class Utente implements UtenteI{
    public String username;
    public String password;
    public boolean isLogged;

    public Utente(String username, String password){
        this.username = username;
        this.password = password;
        this.isLogged = false;
    }

    public String getUsername(){
        return this.username;
    }

    public String getPassword(){
        return this.password;
    }

    public boolean getIsLogged(){
        return isLogged;
    }

    public void setIsLogged(boolean value){
        this.isLogged = value;
    }
}
