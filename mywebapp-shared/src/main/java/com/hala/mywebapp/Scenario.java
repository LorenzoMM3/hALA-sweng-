package com.hala.mywebapp;

import java.io.Serializable;
import java.util.ArrayList;

public class Scenario implements ScenarioI, Serializable {
    protected String domandaCambioScenario;
    protected String testoScena;
    protected String nomeStoria;
    protected TipologiaScenario tipologiaScenario;

    /*
     * public Scenario(String tipologia){
     * testoScena = "";
     * try {
     * this.tipologia = TipologiaScenario.valueOf(tipologia.toUpperCase());
     * } catch (IllegalArgumentException e) {
     * System.err.println("Errore: Tipologia non esistente");
     * }
     * // sceltaGiocatore = "false" Non lo metterei perchè se dopo non si imposta la
     * risposta va in automatico
     * }
     */

    public Scenario() {

    }

    public void setDomandaCambioScenario(String domanda) {
        this.domandaCambioScenario = domanda;
    }

    public String getDomandaCambioScenario() {
        return this.domandaCambioScenario;
    }

    public Scenario(String nomeStoria) {
        this.nomeStoria = nomeStoria;
        testoScena = "";
        tipologiaScenario = null;
    }

    @Override
    public void setTestoScena(String testo) {
        this.testoScena = testo;
    }

    public String getTestoScena() {
        return this.testoScena;
    }

    public String getNomeStoria() {
        return nomeStoria;
    }

    public TipologiaScenario getTipologia() {
        return tipologiaScenario;
    }

}
