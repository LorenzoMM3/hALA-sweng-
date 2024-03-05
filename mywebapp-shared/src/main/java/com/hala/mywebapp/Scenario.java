package com.hala.mywebapp;

import java.io.Serializable;
import java.util.ArrayList;

public class Scenario implements ScenarioI, Serializable {
    protected String domandaCambioScenario;
    protected String testoScena;
    protected String nomeStoria;
    protected TipologiaScenario tipologiaScenario;
    public String valId;
    protected ArrayList<String> precedente;
    protected ArrayList<String> successivo;

    public Scenario() {
        domandaCambioScenario = "";
        testoScena = "";
        nomeStoria = "";
        valId = "-1";
        precedente = new ArrayList<>();
        successivo = new ArrayList<>();
    }
    /* 
    public Scenario(String id, String nomeStoria) {
        valId = id;
        this.nomeStoria = nomeStoria;
        domandaCambioScenario = "";
        testoScena = "";
        tipologiaScenario = null;
        precedente = new ArrayList<>();
        successivo = new ArrayList<>();
    }

    */
    public Scenario(String nomeStoria) {
        valId = "";
        this.nomeStoria = nomeStoria;
        domandaCambioScenario = "";
        testoScena = "";
        tipologiaScenario = null;
        precedente = new ArrayList<>();
        successivo = new ArrayList<>();
    }

    public void setValId(String id) {
        this.valId = id;
    }
     
    public String getValId() {
        return this.valId;
    }
     
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

    public void addPrecedente(String id) {
        this.precedente.add(id);
    }

    public void addSuccessivo(String id) {
        this.successivo.add(id);
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
