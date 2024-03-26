package com.hala.mywebapp;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;

public class ScenarioAScelta extends Scenario implements Serializable {

    protected HashMap<String, String> opzioniScelta; // Testo Opzione=Oggetto, se non presente è null
    protected TipologiaScenario tipologiaScenario;
    public String valId;

    public ScenarioAScelta() {
        super();
    }

    public ScenarioAScelta(String nome) {
        super(nome);
        opzioniScelta = new HashMap<>();
        this.tipologiaScenario = TipologiaScenario.ASCELTA;
    }

    public void setOpzioniScelte(ArrayList<String> opzioni) {
        for (String op : opzioni) {
            String[] temp = op.split(";");
            if (temp[1] != null) {
                this.opzioniScelta.put(temp[0], temp[1]);
            } else {
                this.opzioniScelta.put(temp[0], "");
            }
        }
    }

    public void setValId(String id) {
        this.valId = id;
    }

    public String getValId() {
        return this.valId;
    }

    public HashMap<String, String> getOpzioniScelta() {
        return opzioniScelta;
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
