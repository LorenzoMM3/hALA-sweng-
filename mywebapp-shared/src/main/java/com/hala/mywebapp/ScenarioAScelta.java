package com.hala.mywebapp;

import java.io.Serializable;
import java.util.ArrayList;

public class ScenarioAScelta extends Scenario implements Serializable{

    protected ArrayList<String> opzioniScelta;
    protected int sceltaGiocatore; //Indice dell'array    
    private static final long serialVersionUID = 123456789L;

    public ScenarioAScelta(){
        super();
    }

    public ScenarioAScelta(String nome){
        super(nome);
        opzioniScelta = new ArrayList<>();
        sceltaGiocatore = -1;
        this.tipologiaScenario = TipologiaScenario.ASCELTA;
    }

    public void setOpzioniScelte(ArrayList<String> opzioni){
        this.opzioniScelta = opzioni;
    }

    public void setSceltaGiocatore(int scelta){
        if (scelta >= 0 && scelta < opzioniScelta.size()){
            this.sceltaGiocatore = scelta;
        } 
    }

    public ArrayList<String> getOpzioniScelta(){
        return opzioniScelta;
    }

    public String getDomandaScenario(){
        return domandaCambioScenario;
    }

}
