package com.hala.mywebapp;

import java.io.Serializable;

public class Scenario implements ScenarioI, Serializable{
    protected String testoScena;
    protected String domandaCambioScenario;
    protected boolean sceltaGiocatore;
    protected TipologiaScenario tipologia;
    // Rappresenta lo scenario in cui si esce decidendo una delle due strade
    
    public Scenario(){

    }
    
    public Scenario(String tipologia){
        testoScena = "";
        try {
            this.tipologia = TipologiaScenario.valueOf(tipologia.toUpperCase());
        } catch (IllegalArgumentException e) {
            System.err.println("Errore: Tipologia non esistente");
        }
        // sceltaGiocatore = "false" Non lo metterei perchè se dopo non si imposta la risposta va in automatico
    }   

    public void setTestoScena(String testo){
        this.testoScena = testo;
    }

    public void setSceltaGiocatore(boolean scelta){
        this.sceltaGiocatore = scelta;
    }

    public void setDomanda(String domanda){
        this.domandaCambioScenario = domanda;
    }

    public String getTestoScena(){
        return this.testoScena;
    }

    public boolean getSceltaGiocatore(){
        return sceltaGiocatore;
    }

    public String getDomandaScenario(){
        return domandaCambioScenario;
    }

}
