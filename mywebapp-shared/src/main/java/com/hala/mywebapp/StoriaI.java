package com.hala.mywebapp;

public interface StoriaI {

    public void setNome(String nome);

    public void setNumeroScenari(String numeroScenari);

    public String getNumeroScenari();
    
    public void setScenarioIniziale(Scenario scenarioIniziale);

    public Scenario getScenarioIniziale();


    public String getNome();
    
}
