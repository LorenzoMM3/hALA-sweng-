package com.hala.mywebapp;

import java.io.Serializable;
import java.util.ArrayList;

public class Partita implements Serializable, PartitaI {

    public Utente giocatore;
    public Storia storia;
    public String scenarioAttuale;
    public ArrayList<String> inventario;

    public Partita(Utente giocatore, Storia storia){
        this.giocatore = giocatore;
        this.storia = storia;
        scenarioAttuale = "";
        inventario = new ArrayList<String>();
    }

    public Partita(){
        this.giocatore = null;
        this.storia = null;
        scenarioAttuale = "";
        inventario = new ArrayList<String>();
    }

    public Utente getGiocatore(){
        return this.giocatore;
    }

    public void setGiocatore(Utente utente){
        this.giocatore = utente;
    }

    public Storia getStoria(){
        return this.storia;
    }

    public void setStoria(Storia storia){
        this.storia = storia;
    }

    public String getScenarioAttuale(){
        return this.scenarioAttuale;
    }

    public void setScenarioAttuale(String scenarioAttuale){
        this.scenarioAttuale = scenarioAttuale;
    }

    public ArrayList<String> getInventario(){
        return this.inventario;
    }

    public void addInventario(String oggetto){
        this.inventario.add(oggetto);
    }

    public void setInventario(ArrayList<String> inventario){
        this.inventario = inventario;
    }
    
}