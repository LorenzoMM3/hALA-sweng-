package com.hala.mywebapp;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;

public class Scenario implements ScenarioI, Serializable {
    protected String domandaCambioScenario;
    protected String testoScena;
    protected String nomeStoria;
    protected TipologiaScenario tipologiaScenario;
    public String valId;
    protected ArrayList<String> precedente;
    protected HashMap<String, String> successivo; //Testo opzione = indice successivo
    protected ArrayList<String> oggettiCheSblocca;

    public Scenario() {
        domandaCambioScenario = "";
        testoScena = "";
        nomeStoria = "";
        valId = "-1";
        tipologiaScenario = null;
        precedente = new ArrayList<>();
        successivo = new HashMap<>();
        oggettiCheSblocca = new ArrayList<>();
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
        tipologiaScenario = TipologiaScenario.DEFAULT;
        precedente = new ArrayList<>();
        successivo = new HashMap<>();
    }

    public void setValId(String id) {
        this.valId = id;
    }
     
    public String getValId() {
        return this.valId;
    }
     
    public ArrayList<String> getOggettiCheSblocca() {
        return oggettiCheSblocca;
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

    public void addPrecedente(String id) {
        this.precedente.add(id);
    }

    public void addSuccessivo(String key, String id) {
        this.successivo.put(key, id);
    }

    public void addOggettoCheSblocca(String oggetto) {
        this.oggettiCheSblocca.add(oggetto);
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

    public void setOggettiCheSblocca(ArrayList<String> oggettiCheSblocca) {
        this.oggettiCheSblocca = oggettiCheSblocca;
    }
    
    public TipologiaScenario getTipologia() {
        return tipologiaScenario;
    }

}
