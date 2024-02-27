package com.hala.mywebapp;

public class ScenarioIndovinello extends Scenario{
    
    protected String rispostaIndovinello;

    public ScenarioIndovinello(){
        super();
        rispostaIndovinello = "";
    }

    public void setSceltaGiocatore(String scelta){
        if (this.rispostaIndovinello.equalsIgnoreCase(scelta)){
            this.sceltaGiocatore = true;
        } else {
            this.sceltaGiocatore = false;
        }
        // Se true prosegue, se false continua a rispondere
    }

}
