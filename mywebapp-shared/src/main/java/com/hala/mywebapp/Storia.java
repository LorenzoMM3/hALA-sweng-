package com.hala.mywebapp;

import java.io.Serializable;
import java.util.ArrayList;


public class Storia implements StoriaI, Serializable{
    public Utente creatore;
    public String nome;
    public boolean iniziata;
    public Scenario scenarioIniziale;
    // public ArrayList<Scenario> scenari;    boh potrebbe essere un'idea ma non credo sia molto corretto

   
    public Storia(){

    }

    public Storia(String nome, Utente utente){
        this.nome = nome;
        this.creatore = utente;
        this.iniziata = false;
        scenarioIniziale = new Scenario(); //!!
    }

    public String getNome(){
        return this.nome;
    }

    public Utente getUtente(){
        return creatore;
    }
    
    public boolean getIniziata(){
        return this.iniziata;
    }

    public Scenario getScenarioIniziale(){
        return this.scenarioIniziale;
    }

    public void setNome(String nome){
        this.nome = nome;
    }

    public void setScenarioIniziale(Scenario scenarioIniziale){
        this.scenarioIniziale = scenarioIniziale;
    }

    public void setIniziata(boolean iniziata){
        this.iniziata = iniziata;
    }


}
