package com.hala.mywebapp;

import java.io.Serializable;

public class ScenarioIndovinello extends Scenario implements Serializable{
    
    protected String rispostaIndovinello;

    public ScenarioIndovinello(){

    }
    
    public ScenarioIndovinello(String titolo){
        super(titolo);
        rispostaIndovinello = "";
    }

    public void setSceltaGiocatore(String scelta){
        if (this.rispostaIndovinello.equalsIgnoreCase(scelta)){
            this.sceltaGiocatore = true;
        } else {
            this.sceltaGiocatore = false;
        }
        // Se true prosegue, se false continua a rispondere
    }

}
