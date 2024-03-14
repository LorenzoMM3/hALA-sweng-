package com.hala.mywebapp;

import java.io.Serializable;

public class ScenarioIndovinello extends Scenario implements Serializable {

    protected String domandaIndovinello;
    protected String rispostaIndovinello;
    protected TipologiaScenario tipologiaScenario;
    public String valId;

    public ScenarioIndovinello() {
        super();
    }

    public ScenarioIndovinello(String nome) {
        super(nome);
        // valId = id;
        rispostaIndovinello = "";
        tipologiaScenario = TipologiaScenario.INDOVINELLO;
    }

    /*
     * public ScenarioIndovinello(String titolo){
     * super(titolo);
     * rispostaIndovinello = "";
     * }
     */

    public void setValId(String id) {
        this.valId = id;
    }

    public String getValId() {
        return this.valId;
    }

    /*
     * public void setDomandaIndovinello(String domanda) {
     * this.domandaIndovinello = domanda;
     * }
     */

    public void setRispostaIndovinello(String opzioniSceltaTemp) {
        this.rispostaIndovinello = opzioniSceltaTemp;
    }

    public String getRispostaIndovinello() {
        return this.rispostaIndovinello;
    }

    public boolean verificaRisposta(String scelta) {
        if (this.rispostaIndovinello.equalsIgnoreCase(scelta)) {
            return true;
        } else {
            return false;
        }
        // Se true prosegue, se false continua a rispondere
    }

    public TipologiaScenario getTipologia() {
        return this.tipologiaScenario;
    }

    
}