package com.hala.mywebapp;

import com.google.gwt.core.client.GWT;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.Widget;
import com.hala.mywebapp.VisualizzaCatalogo.VisualizzaUiBinder;

public class GiocaStoria extends Composite{
    public static final GreetingServiceAsync hALAServiceAsync = GWT.create(GreetingService.class);

    private static final GiocaUiBinder UiB = GWT.create(GiocaUiBinder.class);

    Partita partita;

    interface GiocaUiBinder extends UiBinder<Widget, GiocaStoria> {
    }


    @UiField
    Label testoScenarioLabel;

    @UiField
    Label domandaCambioScenarioLabel;

    @UiField
    Label messageLabel;

    public GiocaStoria(Storia storia, Utente giocatore) {
        initWidget(UiB.createAndBindUi(this));
        settaGrafica();
        partita = new Partita();

        hALAServiceAsync.caricaPartita(storia, giocatore, new AsyncCallback<Partita>() {
            @Override
            public void onFailure(Throwable throwable) {
                GWT.log("Errore durante la chiamata asincrona al servizio remoto", throwable);
            }

            @Override
            public void onSuccess(Partita result) {
                partita = result;
                riempiCampi();
            }
        });

    }

    public void settaGrafica(){
        testoScenarioLabel.setStyleName("testi");
        domandaCambioScenarioLabel.setStyleName("testi");
        messageLabel.setStyleName("messaggio");
    }

    public void riempiCampi(){
        Scenario scenarioAttuale = partita.getScenarioAttuale();
        String tipologia = scenarioAttuale.getTipologia().toString();
        String testo = scenarioAttuale.getTestoScena();
        testoScenarioLabel.setText(testo); 

        if(tipologia.equals("ASCELTA")){
            String domanda = ((ScenarioAScelta) scenarioAttuale).getDomandaCambioScenario();
            domandaCambioScenarioLabel.setText(domanda);
        
        }
        else if(tipologia.equals("INDOVINELLO")){
            String indovinello = ((ScenarioIndovinello) scenarioAttuale).getDomandaCambioScenario();
            domandaCambioScenarioLabel.setText(indovinello);
        }
        else if(tipologia.equals("DEFAULT")){

        }
    }
}
