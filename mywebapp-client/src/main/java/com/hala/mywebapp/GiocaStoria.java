package com.hala.mywebapp;

import com.gargoylesoftware.htmlunit.javascript.host.dom.Text;
import com.google.gwt.core.client.GWT;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.TextBox;
import com.google.gwt.user.client.ui.Widget;
import com.hala.mywebapp.VisualizzaCatalogo.VisualizzaUiBinder;

public class GiocaStoria extends Composite{
    public static final GreetingServiceAsync hALAServiceAsync = GWT.create(GreetingService.class);

    private static final GiocaUiBinder UiB = GWT.create(GiocaUiBinder.class);

    Scenario scenarioAttuale;
    Partita partita;

    interface GiocaUiBinder extends UiBinder<Widget, GiocaStoria> {
    }


    @UiField
    Label testoScenarioLabel;

    @UiField
    Label domandaCambioScenarioLabel;

    @UiField
    Label rispostaCambioScenarioLabel;

    @UiField
    TextBox inserimentoRispostaTB;

    @UiField
    Button invioRispostaButton;
    
    @UiField
    Label messageLabel;

    public GiocaStoria(Storia storia, Utente giocatore) {
        initWidget(UiB.createAndBindUi(this));
        settaGrafica();
        partita = new Partita();
        scenarioAttuale = new Scenario();

        hALAServiceAsync.caricaPartita(storia, giocatore, new AsyncCallback<Partita>() {
            @Override
            public void onFailure(Throwable throwable) {
                GWT.log("Errore durante la chiamata asincrona al servizio remoto", throwable);
            }

            @Override
            public void onSuccess(Partita result) {
                partita = result;
                messageLabel.setText("Benvenuto in " + partita.getStoria().getNome() + ", scenario attuale: " + partita.getScenarioAttuale().getTestoScena() + ", successivi: " + partita.getScenarioAttuale().getSuccessivo());
                riempiCampi();
            }
        });

        invioRispostaButton.addClickHandler(new ClickHandler() {

            @Override
            public void onClick(ClickEvent event) {
                String risposta = inserimentoRispostaTB.getText();
               inserimentoRispostaTB.setText(""); 
               hALAServiceAsync.caricaSuccessivoIndovinello(partita, risposta, new AsyncCallback<Partita>() {
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
            
        });
    }

    

    public void settaGrafica(){
        testoScenarioLabel.setStyleName("testi");
        domandaCambioScenarioLabel.setStyleName("testi");
        rispostaCambioScenarioLabel.setStyleName("testi");
        invioRispostaButton.setStyleName("lButton");
        messageLabel.setStyleName("messaggio");
    }

    public void riempiCampi(){
        scenarioAttuale = partita.getScenarioAttuale();
        String tipologia = scenarioAttuale.getTipologia().toString();
        String testo = scenarioAttuale.getTestoScena();
        testoScenarioLabel.setText(testo); 

        if(tipologia.equals("ASCELTA")){
            String domanda = ((ScenarioAScelta) scenarioAttuale).getDomandaCambioScenario();
            domandaCambioScenarioLabel.setText(domanda);
            mostraPerScelta();
        
        }
        else if(tipologia.equals("INDOVINELLO")){
            mostraPerIndovinello();
            String indovinello = ((ScenarioIndovinello) scenarioAttuale).getDomandaCambioScenario();
            domandaCambioScenarioLabel.setText(indovinello);


        }
        else if(tipologia.equals("DEFAULT")){
            mostraPerFinale();
        }
    }

    private void mostraPerScelta(){
        testoScenarioLabel.setVisible(true);
        rispostaCambioScenarioLabel.setVisible(false);
        inserimentoRispostaTB.setVisible(false);
        invioRispostaButton.setVisible(false);
    }

    private void mostraPerIndovinello(){
        testoScenarioLabel.setVisible(true);
    }

    private void mostraPerFinale(){
        testoScenarioLabel.setVisible(true);
        domandaCambioScenarioLabel.setVisible(false);
        rispostaCambioScenarioLabel.setVisible(false);
        inserimentoRispostaTB.setVisible(false);
        invioRispostaButton.setVisible(false);
    }
}
