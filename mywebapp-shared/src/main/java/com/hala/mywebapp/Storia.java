package com.hala.mywebapp;

import java.util.ArrayList;

public class Storia implements StoriaI{
    public String nome;
    public boolean iniziata;
    public Scenario scenarioIniziale;
    // public ArrayList<Scenario> scenari;    boh potrebbe essere un'idea ma non credo sia molto corretto

    public Storia(String nome){
        this.nome = nome;
        this.iniziata = false;
    }

    public String getNome(){
        return this.nome;
    }

    public boolean getIniziata(){
        return this.iniziata;
    }

    public Scenario getScenarioIniziale(){
        return this.scenarioIniziale;
    }

    public void setNome(String nome){
        this.nome = nome;
    }

    public void setScenarioIniziale(Scenario scenarioIniziale){
        this.scenarioIniziale = scenarioIniziale;
    }

    public void setIniziata(boolean iniziata){
        this.iniziata = iniziata;
    }


}
