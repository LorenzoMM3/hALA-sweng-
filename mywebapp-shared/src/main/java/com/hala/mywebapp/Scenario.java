package com.hala.mywebapp;

import java.io.Serializable;
import java.util.ArrayList;

public class Scenario implements ScenarioI, Serializable {
    protected String domandaCambioScenario;
    protected String testoScena;
    protected String nomeStoria;
    protected TipologiaScenario tipologiaScenario;
    // protected String id;
    protected ArrayList<String> precedente;
    protected ArrayList<String> successivo;

    public Scenario() {
        domandaCambioScenario = "";
        testoScena = "";
        nomeStoria = "";
        // id = "-1";
        precedente = new ArrayList<>();
        successivo = new ArrayList<>();
    }

    public Scenario(String nomeStoria) {
        // id = "-1";
        this.nomeStoria = nomeStoria;
        domandaCambioScenario = "";
        testoScena = "";
        tipologiaScenario = null;
        precedente = new ArrayList<>();
        successivo = new ArrayList<>();
    }

    /*
     * public void setId(String id) {
     * this.id = id;
     * }
     * 
     * public String getId() {
     * return this.id;
     * }
     */
    public void setPrecedente(ArrayList<String> precedente) {
        this.precedente = precedente;
    }

    public void setSuccessivo(ArrayList<String> successivo) {
        this.successivo = successivo;
    }

    public ArrayList<String> getPrecedente() {
        return this.precedente;
    }

    public ArrayList<String> getSuccessivo() {
        return this.successivo;
    }

    public void addPrecedente(String precedente) {
        this.precedente.add(precedente);
    }

    public void addSuccessivo(String successivo) {
        this.successivo.add(successivo);
    }

    public void setDomandaCambioScenario(String domanda) {
        this.domandaCambioScenario = domanda;
    }

    public String getDomandaCambioScenario() {
        return this.domandaCambioScenario;
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
