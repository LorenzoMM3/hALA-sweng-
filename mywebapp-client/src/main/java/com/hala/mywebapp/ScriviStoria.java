package com.hala.mywebapp;

import java.util.ArrayList;

import org.checkerframework.checker.guieffect.qual.UI;

import com.google.gwt.core.client.GWT;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.ui.*;

public class ScriviStoria extends Composite implements IsWidget {

    public static final GreetingServiceAsync hALAServiceAsync = GWT.create(GreetingService.class);
    private static final ScriviStoriaUiBinder uiBinder = GWT.create(ScriviStoriaUiBinder.class);
    private ArrayList<String> opzioniSceltaTemp;
    private String nomeStoriaTemp;

    interface ScriviStoriaUiBinder extends UiBinder<Widget, ScriviStoria> {}

    @UiField
    VerticalPanel titoloS;

    @UiField
    Label messageLabelCreate;

    @UiField
    TextBox titoloStoria;

    @UiField
    Button inserisciStoria;

    @UiField
    VerticalPanel vpScenario;

    @UiField
    Label scriviScenarioLabel;

    @UiField
    Label tipologiaLabel;

    @UiField
    Button backButton;

    @UiField
    ListBox menuTipoScenario;

    @UiField
    TextBox testoScenarioField;

    @UiField
    TextBox domandaFieldAScelta;

    @UiField
    TextBox scelta;

    @UiField
    Label labelTestoScenario;

    @UiField
    Label labelDomandaAScelta;

    @UiField
    Label labelScelta;

    @UiField
    Button creaScenarioAScelta;

    @UiField
    Button altraScelta;

    @UiField
    Label labelDomandaIndovinello;

    @UiField
    TextBox domandaFieldIndovinello;

    @UiField
    Label labelRispostaIndovinello;

    @UiField
    TextBox rispostaFieldIndovinello;

    @UiField
    Button creaScenarioIndovinello;

    @UiField
    Label labelOggetto;

    @UiField
    TextBox oggettoNecessario;

    @UiField
    Button creaScenarioOggetto;

    @UiField
    Label message;

    public ScriviStoria() {
        initWidget(uiBinder.createAndBindUi(this));
        hideAdditionalFields();
        disabilitaTutto();

        inserisciStoria.addClickHandler(new ClickHandler() {
            @Override
            public void onClick(ClickEvent event) {
                nomeStoriaTemp = titoloStoria.getText().toUpperCase();
                
                hALAServiceAsync.ottieniUtenteAttuale(new AsyncCallback<Utente>() {
                    public void onFailure(Throwable caught){
                        System.err.println("Errore qui");
                    };
                    public void onSuccess(Utente utente){
                        final Utente utenteAttuale = utente;
                        Storia nuovaStoria = new Storia(nomeStoriaTemp, utenteAttuale);
                        hALAServiceAsync.creaNuovaStoria(nuovaStoria, new AsyncCallback<Boolean>() {
                            public void onFailure(Throwable caught) {};
                            public void onSuccess(Boolean verifica){
                                if (verifica){
                                    messageLabelCreate.setText("Storia creata con successo");
                                    titoloS.remove(inserisciStoria);
                                    attivaTutto();
                                } else {
                                    messageLabelCreate.setText("Nome già esistente, riprova");
                                }
                            };
                        });
                    };

                });
                
            }
            
        });
        
        menuTipoScenario.addChangeHandler(event -> {
            if ("Scenario a scelta".equals(menuTipoScenario.getSelectedValue())) {
                showAdditionalFieldsAScelta();
                opzioniSceltaTemp = new ArrayList<>();
            } else {
                hideAdditionalFieldsAScelta();
            }

            if ("Scenario con indovinello".equals(menuTipoScenario.getSelectedValue())) {
                showAdditionalFieldsIndovinello();
            } else {
                hideAdditionalFieldsIndovinello();
            }

            if ("Scenario con oggetto".equals(menuTipoScenario.getSelectedValue())) {
                showAdditionalFieldsOggetto();
            } else {
                hideAdditionalFieldsOggetto();
            }
        });

        altraScelta.addClickHandler(new ClickHandler() {

            @Override
            public void onClick(ClickEvent event) {
                opzioniSceltaTemp.add(scelta.getText());
                scelta.setText("");
            }
            
        });
        
        /*
         * Evento per il bottone "creaScenarioAScelta" che permette di creare un nuovo scenario
         */
        creaScenarioAScelta.addClickHandler(new ClickHandler() {
            @Override
            public void onClick(ClickEvent event) {
                // Creazione nuovo scenario
                ScenarioAScelta scenario = new ScenarioAScelta(nomeStoriaTemp);
                // E' necessario settare tutti gli attributi prelevandoli dai textField
                // Iniziamo con gli attributi comuni a tutti gli scenari:
                scenario.setTestoScena(testoScenarioField.getText());
                scenario.setDomandaCambioScenario(domandaFieldAScelta.getText());
                // Attributi aggiuntivi:
                scenario.setOpzioniScelte(opzioniSceltaTemp);

                // E' necessario ora richiamare il server per poter effettuare metodi su questo scenario:
                // La struttura è sempre simile
                // Ricorda l'asyncallback vuole i due metodi onfailure e onsuccess che partono in base a se il metodo nel server si conclude con successo o meno
                hALAServiceAsync.aggiungiScenarioAScelta(scenario, new AsyncCallback<Boolean>() {
                    @Override
                    public void onFailure(Throwable caught) {}
                    public void onSuccess(Boolean verifica){
                        if (verifica){
                            message.setText("Scenario a scelta creato con successo");
                        }
                        else{
                            message.setText("Errore nella creazione dello scenario a scelta");
                        }
                    }
                });
                // Resetto i field così da liberarli per la creazione di un nuovo scenario
                testoScenarioField.setText("");
                domandaFieldAScelta.setText("");
                scelta.setText("");
                // Libero l'array di scelte
                opzioniSceltaTemp.clear();
            }
        });



        creaScenarioIndovinello.addClickHandler(new ClickHandler() {
            @Override
            public void onClick(ClickEvent event) {
                ScenarioIndovinello scenario = new ScenarioIndovinello(nomeStoriaTemp);

                scenario.setTestoScena(testoScenarioField.getText());

                scenario.setDomandaIndovinello(domandaFieldIndovinello.getText());
                scenario.setRispostaIndovinello(rispostaFieldIndovinello.getText());

                hALAServiceAsync.aggiungiScenarioIndovinello(scenario, new AsyncCallback<Boolean>() {
                    @Override
                    public void onFailure(Throwable caught) {}
                    public void onSuccess(Boolean verifica){
                        if (verifica){
                            message.setText("Scenario Indovinello creato con successo");
                        }
                        else{
                            message.setText("Errore nella creazione dello scenario indovinello");
                        }
                    }
                });
                // Resetto i field così da liberarli per la creazione di un nuovo scenario
                testoScenarioField.setText("");
                domandaFieldIndovinello.setText("");
                rispostaFieldIndovinello.setText("");
            }
        });



        creaScenarioOggetto.addClickHandler(new ClickHandler() {
            @Override
            public void onClick(ClickEvent event) {
                ScenarioOggetto scenario = new ScenarioOggetto(nomeStoriaTemp);
        
                scenario.setTestoScena(testoScenarioField.getText());
                scenario.setOggetto(oggettoNecessario.getText());
        
                hALAServiceAsync.aggiungiScenarioOggetto(scenario, new AsyncCallback<Boolean>() {
                    @Override
                    public void onFailure(Throwable caught) {}
                    public void onSuccess(Boolean verifica){
                        if (verifica){
                            message.setText("Scenario Oggetto creato con successo");
                        }
                        else{
                            message.setText("Errore nella creazione dello scenario oggetto");
                        }
                    }
                });
                // Resetto i field così da liberarli per la creazione di un nuovo scenario
                testoScenarioField.setText("");
                oggettoNecessario.setText("");
            }
        });
        

        backButton.addClickHandler(new ClickHandler() {
            @Override
            public void onClick(ClickEvent event) {
                RootPanel.get("startTable").clear();
                RootPanel.get("startTable").add(new HomePage());
            }
        });
    }

    private void disabilitaTutto(){
        scriviScenarioLabel.setVisible(false);
        tipologiaLabel.setVisible(false);
        menuTipoScenario.setVisible(false);
        testoScenarioField.setVisible(false);
        domandaFieldAScelta.setVisible(false);
        scelta.setVisible(false);
        labelTestoScenario.setVisible(false);
        labelDomandaAScelta.setVisible(false);
        labelScelta.setVisible(false);
        creaScenarioAScelta.setVisible(false);
        altraScelta.setVisible(false);
        labelDomandaIndovinello.setVisible(false);
        domandaFieldIndovinello.setVisible(false);
        labelRispostaIndovinello.setVisible(false);
        rispostaFieldIndovinello.setVisible(false);
        creaScenarioIndovinello.setVisible(false);
        labelOggetto.setVisible(false);
        oggettoNecessario.setVisible(false);
        creaScenarioOggetto.setVisible(false);
    }

    private void attivaTutto(){
        scriviScenarioLabel.setVisible(true);
        tipologiaLabel.setVisible(true);
        menuTipoScenario.setVisible(true);
        testoScenarioField.setVisible(true);
        domandaFieldAScelta.setVisible(true);
        scelta.setVisible(true);
        labelTestoScenario.setVisible(true);
        labelDomandaAScelta.setVisible(true);
        labelScelta.setVisible(true);
        creaScenarioAScelta.setVisible(true);
        altraScelta.setVisible(true);
        labelDomandaIndovinello.setVisible(true);
        domandaFieldIndovinello.setVisible(true);
        labelRispostaIndovinello.setVisible(true);
        rispostaFieldIndovinello.setVisible(true);
        creaScenarioIndovinello.setVisible(true);
        labelOggetto.setVisible(true);
        oggettoNecessario.setVisible(true);
        creaScenarioOggetto.setVisible(true);

    }

    private void hideAdditionalFields(){
        hideAdditionalFieldsAScelta();
        hideAdditionalFieldsIndovinello();
        hideAdditionalFieldsOggetto();
    }

    private void showAdditionalFieldsAScelta() {
        vpScenario.add(labelDomandaAScelta);
        vpScenario.add(domandaFieldAScelta);
        vpScenario.add(labelScelta);
        vpScenario.add(scelta);
        vpScenario.add(altraScelta);
        vpScenario.add(creaScenarioAScelta);
    }
    
    private void hideAdditionalFieldsAScelta() {
        vpScenario.remove(labelDomandaAScelta);
        vpScenario.remove(domandaFieldAScelta);
        vpScenario.remove(labelScelta);
        vpScenario.remove(scelta);
        vpScenario.remove(altraScelta);
        vpScenario.remove(creaScenarioAScelta);
    }
    
    private void showAdditionalFieldsIndovinello() {
        vpScenario.add(labelDomandaIndovinello);
        vpScenario.add(domandaFieldIndovinello);
        vpScenario.add(labelRispostaIndovinello);
        vpScenario.add(rispostaFieldIndovinello);
        vpScenario.add(creaScenarioIndovinello);
    }
    
    private void hideAdditionalFieldsIndovinello() {
        vpScenario.remove(labelDomandaIndovinello);
        vpScenario.remove(domandaFieldIndovinello);
        vpScenario.remove(labelRispostaIndovinello);
        vpScenario.remove(rispostaFieldIndovinello);
        vpScenario.remove(creaScenarioIndovinello);
    }
    
    private void showAdditionalFieldsOggetto() {
        vpScenario.add(labelOggetto);
        vpScenario.add(oggettoNecessario);
        vpScenario.add(creaScenarioOggetto);
    }
    
    private void hideAdditionalFieldsOggetto() {
        vpScenario.remove(labelOggetto);
        vpScenario.remove(oggettoNecessario);
        vpScenario.remove(creaScenarioOggetto);
    
    }
    
    
}
