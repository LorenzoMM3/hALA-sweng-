package com.hala.mywebapp;

import java.io.Serializable;
import java.util.ArrayList;

@SuppressWarnings("serial")
public class ScenarioIndovinello extends Scenario implements Serializable{
    
    protected String domandaIndovinello;
    protected String rispostaIndovinello;
    protected TipologiaScenario tipologiaScenario;
    
    public ScenarioIndovinello(){

    }

    public ScenarioIndovinello(String nome){
        super(nome);
        rispostaIndovinello = "";
        tipologiaScenario = TipologiaScenario.INDOVINELLO;
    }
    
    /* 
    public ScenarioIndovinello(String titolo){
        super(titolo);
        rispostaIndovinello = "";
    }
    */

    public void setDomandaIndovinello(String domanda){
        this.domandaIndovinello = domanda;
    }

    public void setRispostaIndovinello(String opzioniSceltaTemp){
        this.rispostaIndovinello = opzioniSceltaTemp;
    }

    public boolean verificaRisposta(String scelta){
        if (this.rispostaIndovinello.equalsIgnoreCase(scelta)){
            return true;
        } else {
            return false;
        }
        // Se true prosegue, se false continua a rispondere
    }

}
