package com.hala.mywebapp;

import java.io.Serializable;
import java.util.ArrayList;

public class ScenarioOggetto extends Scenario implements Serializable {
    
    protected String oggettoNecessario;

    public ScenarioOggetto(){
        
    }
    
    public ScenarioOggetto(String titolo){
        super(titolo);
        oggettoNecessario = "";
    }

    public void setOggetto(String oggetto){ // Cambiare input in base a cosa si può usare in gwt
        this.oggettoNecessario = oggetto;
    }

    public void setSceltaGiocatore(ArrayList<String> oggettiPosseduti){ // Se gwt non permette liste si possono scrivere tutti gli oggetti in formato "ogg1;ogg2;ogg3;..." e fare lo split
        if (oggettiPosseduti.contains(oggettoNecessario)){
            this.sceltaGiocatore = true;
        } else {
            this.sceltaGiocatore = false;
        }
        // Se true prosegue per la via in cui si usa l'oggetto, se false prosegue per l'altra
        // Si potrebbe anche porre la domanda: "Si desidera utilizzare l'oggetto?", passare la risposta in input al metodo e 
        // ritornare true se - oggetto posseduto - risposta true
    }
}
