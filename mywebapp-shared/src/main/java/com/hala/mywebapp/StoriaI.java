package com.hala.mywebapp;

public interface StoriaI {

    public void setNome(String nome);

    public void setIniziata(boolean iniziata);

    public void setScenarioIniziale(Scenario scenarioIniziale);

    public Scenario getScenarioIniziale();

    public boolean getIniziata();

    public String getNome();
    
}
