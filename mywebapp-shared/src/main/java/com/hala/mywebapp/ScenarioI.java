package com.hala.mywebapp;

import java.util.ArrayList;
import java.util.HashMap;

public interface ScenarioI {
    public void setValId(String id);
    public String getValId();
    public ArrayList<String> getOggettiCheSblocca();
    public void setPrecedente(ArrayList<String> precedente);
    public void setSuccessivo(HashMap<String, String> successivo);
    public ArrayList<String> getPrecedente();
    public HashMap<String, String> getSuccessivo();
    public void addPrecedente(String id);
    public void addSuccessivo(String key, String id);
    public void addOggettoCheSblocca(String oggetto);
    public void setDomandaCambioScenario(String domanda);
    public String getDomandaCambioScenario();
    public void setTestoScena(String testo);
    public String getTestoScena();
    public String getNomeStoria();
    public void setOggettiCheSblocca(ArrayList<String> oggettiCheSblocca);
    public TipologiaScenario getTipologia();
}
