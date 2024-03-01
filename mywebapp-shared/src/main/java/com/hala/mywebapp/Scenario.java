package com.hala.mywebapp;

import java.io.Serializable;
import java.util.ArrayList;

@SuppressWarnings("serial")
public class Scenario implements ScenarioI, Serializable{
    protected String testoScena;
    protected String domandaCambioScenario;
    protected String nomeStoria;
    protected TipologiaScenario tipologiaScenario;
    
    
    /* 
    public Scenario(String tipologia){
        testoScena = "";
        try {
            this.tipologia = TipologiaScenario.valueOf(tipologia.toUpperCase());
        } catch (IllegalArgumentException e) {
            System.err.println("Errore: Tipologia non esistente");
        }
        // sceltaGiocatore = "false" Non lo metterei perchè se dopo non si imposta la risposta va in automatico
    } */
    

    public Scenario(){

    }

    public Scenario(String nomeStoria){
        this.nomeStoria = nomeStoria;
        testoScena = "";
        domandaCambioScenario = "";
        tipologiaScenario = null;
    }

    public void setDomanda(String domanda){
        this.domandaCambioScenario = domanda;
    }

    @Override
    public void setTestoScena(String testo) {
        this.testoScena = testo;
    }

    public String getTestoScena(){
        return this.testoScena;
    }

    public String getDomandaScenario(){
        return domandaCambioScenario;
    }

    public String getNomeStoria(){
        return nomeStoria;
    }

    public TipologiaScenario getTipologia(){
        return tipologiaScenario;
    }

}
