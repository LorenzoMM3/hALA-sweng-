package com.hala.mywebapp;

import java.util.ArrayList;
import java.util.HashMap;

public class ScenarioIndovinello extends Scenario{

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

    public void setValId(String id) {
        this.valId = id;
    }

    public String getValId() {
        return this.valId;
    }

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

    public void setPrecedente(ArrayList<String> precedente) {
        this.precedente = precedente;
    }

    public void setSuccessivo(HashMap<String, String> successivo) {
        this.successivo = successivo;
    }

    public ArrayList<String> getPrecedente() {
        return this.precedente;
    }

    public HashMap<String, String> getSuccessivo() {
        return this.successivo;
    }

}