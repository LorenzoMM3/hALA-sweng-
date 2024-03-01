package com.hala.mywebapp;

import java.io.Serializable;

@SuppressWarnings("serial")
public class ScenarioIndovinello extends Scenario implements Serializable{
    
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

    public boolean verificaRisposta(String scelta){
        if (this.rispostaIndovinello.equalsIgnoreCase(scelta)){
            return true;
        } else {
            return false;
        }
        // Se true prosegue, se false continua a rispondere
    }

}
