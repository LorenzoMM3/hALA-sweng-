package com.hala.mywebapp;

import java.io.Serializable;


public class Storia implements StoriaI, Serializable{
    public Utente creatore;
    public String nome;
    public Scenario scenarioIniziale;
    // public ArrayList<Scenario> scenari;    boh potrebbe essere un'idea ma non credo sia molto corretto
    public String numeroScenari;
   
    public Storia(){

    }

    public Storia(String nome, Utente utente){
        this.nome = nome;
        this.creatore = utente;
        scenarioIniziale = new Scenario(); //!!
        numeroScenari = "0";
    }

    public String getNome(){
        return this.nome;
    }

    public Utente getUtente(){
        return creatore;
    }
    
    public Scenario getScenarioIniziale(){
        return this.scenarioIniziale;
    }

    public void setNome(String nome){
        this.nome = nome;
    }

    public void setNumeroScenari(String numeroScenari){
        this.numeroScenari = numeroScenari;
    }

    public String getNumeroScenari(){
        return this.numeroScenari;
    }

    public void setScenarioIniziale(Scenario scenarioIniziale){
        this.scenarioIniziale = scenarioIniziale;
    }


}
