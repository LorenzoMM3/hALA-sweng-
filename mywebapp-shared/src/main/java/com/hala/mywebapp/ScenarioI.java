package com.hala.mywebapp;


public interface ScenarioI {

    public void setTestoScena(String testo);    

//    public void setSceltaGiocatore(int scelta);

//    public void setOpzioniScelte(ArrayList<String> opzioni);

    public void setDomanda(String domanda);

    public String getNomeStoria();

    public String getTestoScena();

//    public ArrayList<String> getOpzioniScelta();

    public String getDomandaScenario();

    public TipologiaScenario getTipologia();

}
