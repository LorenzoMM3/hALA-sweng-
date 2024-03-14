package com.hala.mywebapp;

import java.util.ArrayList;

public interface PartitaI {
    
    public Utente getGiocatore();

    public void setGiocatore(Utente utente);

    public Storia getStoria();

    public void setStoria(Storia storia);

    public Scenario getScenarioAttuale();

    public void setScenarioAttuale(Scenario scenarioAttuale);

    public ArrayList<String> getInventario();

    public void addInventario(String oggetto);

    public void setInventario(ArrayList<String> inventario);
}
