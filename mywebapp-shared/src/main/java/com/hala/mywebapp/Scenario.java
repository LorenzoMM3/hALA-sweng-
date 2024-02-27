package com.hala.mywebapp;

public class Scenario implements ScenarioI{
    protected String testoScena;
    protected String domandaCambioScenario;
    protected boolean sceltaGiocatore;

    // Rappresenta lo scenario in cui si esce decidendo una delle due strade
    
    public Scenario(){
        testoScena = "";
        // sceltaGiocatore = "false" Non lo metterei perchè se dopo non si imposta la risposta va in automatico
    }   

    public void setTestoScena(String testo){
        this.testoScena = testo;
    }

    public void setSceltaGiocatore(boolean scelta){
        this.sceltaGiocatore = scelta;
    }

    public void setDomanda(String domanda){
        this.domandaCambioScenario = domanda;
    }

    public String getTestoScena(){
        return this.testoScena;
    }

    public boolean getSceltaGiocatore(){
        return sceltaGiocatore;
    }

    public String getDomandaScenario(){
        return domandaCambioScenario;
    }

}
