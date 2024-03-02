package com.hala.mywebapp;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;

public class ScenarioAScelta extends Scenario implements Serializable {

    protected HashMap<String, String> opzioniScelta; // Oggetto, se non presente è null
    protected TipologiaScenario tipologiaScenario;

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

    public HashMap<String, String> getOpzioniScelta() {
        return opzioniScelta;
    }

    public TipologiaScenario getTipologia() {
        return this.tipologiaScenario;
    }

}
