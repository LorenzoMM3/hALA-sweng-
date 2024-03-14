package com.hala.mywebapp;

import java.io.Serializable;
import java.util.ArrayList;

public class Partita implements Serializable, PartitaI {

    public String id;
    public Utente giocatore;
    public Storia storia;
    public Scenario scenarioAttuale;
    public ArrayList<String> inventario;

    public Partita(Utente giocatore, Storia storia, String id){
        this.id = id;
        this.giocatore = giocatore;
        this.storia = storia;
        scenarioAttuale = storia.getScenarioIniziale();
        inventario = new ArrayList<String>();
    }

    public Partita(){
        this.id = "";
        this.giocatore = null;
        this.storia = null;
        scenarioAttuale = null;
        inventario = new ArrayList<String>();
    }

    public Utente getGiocatore(){
        return this.giocatore;
    }

    public String getId(){
        return this.id;
    }

    public void setId(String id){
        this.id = id;
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

    public Scenario getScenarioAttuale(){
        return this.scenarioAttuale;
    }

    public void setScenarioAttuale(Scenario scenarioAttuale){
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