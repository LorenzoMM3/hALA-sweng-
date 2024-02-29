package com.hala.mywebapp;

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
    Button backButton;

    @UiField
    ListBox menuTipoScenario;

    @UiField
    TextBox testoScenarioFieldAScelta;

    @UiField
    TextBox domandaFieldAScelta;

    @UiField
    TextBox sceltaUnoField;

    @UiField
    TextBox sceltaDueField;

    @UiField
    Label labelTestoScenarioAScelta;

    @UiField
    Label labelDomandaAScelta;

    @UiField
    Label labelSceltaUno;

    @UiField
    Label labelSceltaDue;

    @UiField
    Button creaScenarioAScelta;

    @UiField
    Label labelTestoScenarioIndovinello;

    @UiField
    TextBox testoScenarioFieldIndovinello;

    @UiField
    Label labelDomandaIndovinello;

    @UiField
    TextBox domandaFieldIndovinello;

    @UiField
    Label labelRispostaIndovinello;

    @UiField
    TextBox rispostaIndovinelloField;

    @UiField
    Button creaScenarioIndovinello;

    @UiField
    Label labelTestoScenarioOggetto;

    @UiField
    TextBox testoScenarioFieldOggetto;

    @UiField
    Label labelOggetto;

    @UiField
    TextBox oggettoNecessario;

    @UiField
    Button creaScenarioOggetto;

    

    public ScriviStoria() {
        initWidget(uiBinder.createAndBindUi(this));
        hideAdditionalFields();

        inserisciStoria.addClickHandler(new ClickHandler() {
            @Override
            public void onClick(ClickEvent event) {
                String titoloNuovaStoria = titoloStoria.getText().toUpperCase();
                
                hALAServiceAsync.ottieniUtenteAttuale(new AsyncCallback<Utente>() {
                    public void onFailure(Throwable caught){
                        System.err.println("Errore qui");
                    };
                    public void onSuccess(Utente utente){
                        final Utente utenteAttuale = utente;
                        Storia nuovaStoria = new Storia(titoloNuovaStoria, utenteAttuale);
                        hALAServiceAsync.creaNuovaStoria(nuovaStoria, new AsyncCallback<Boolean>() {
                            public void onFailure(Throwable caught) {};
                            public void onSuccess(Boolean verifica){
                                if (verifica){
                                    messageLabelCreate.setText("Storia creata con successo");
                                    titoloS.remove(inserisciStoria);
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

        creaScenarioAScelta.addClickHandler(new ClickHandler() {
            @Override
            public void onClick(ClickEvent event) {
                testoScenarioFieldAScelta.setText("");
                domandaFieldAScelta.setText("");
                sceltaUnoField.setText("");
                sceltaDueField.setText("");
            }
        });

        creaScenarioIndovinello.addClickHandler(new ClickHandler() {
            @Override
            public void onClick(ClickEvent event) {
                testoScenarioFieldIndovinello.setText("");
                domandaFieldIndovinello.setText("");
                rispostaIndovinelloField.setText("");
            }
        });

        creaScenarioOggetto.addClickHandler(new ClickHandler() {
            @Override
            public void onClick(ClickEvent event) {
                testoScenarioFieldOggetto.setText("");
                oggettoNecessario.setText("");
            }
        });

        backButton.addClickHandler(new ClickHandler() {
            @Override
            public void onClick(ClickEvent event) {
                RootPanel.get("startTable").clear();
                RootPanel.get("startTable").add(new Login());
            }
        });
    }

    private void hideAdditionalFields(){
        hideAdditionalFieldsAScelta();
        hideAdditionalFieldsIndovinello();
        hideAdditionalFieldsOggetto();
    }

    private void showAdditionalFieldsAScelta() {
        vpScenario.add(labelTestoScenarioAScelta);
        vpScenario.add(testoScenarioFieldAScelta);
        vpScenario.add(labelDomandaAScelta);
        vpScenario.add(domandaFieldAScelta);
        vpScenario.add(labelSceltaUno);
        vpScenario.add(sceltaUnoField);
        vpScenario.add(labelSceltaDue);
        vpScenario.add(sceltaDueField);
        vpScenario.add(creaScenarioAScelta);
    }
    
    private void hideAdditionalFieldsAScelta() {
        vpScenario.remove(labelTestoScenarioAScelta);
        vpScenario.remove(testoScenarioFieldAScelta);
        vpScenario.remove(labelDomandaAScelta);
        vpScenario.remove(domandaFieldAScelta);
        vpScenario.remove(labelSceltaUno);
        vpScenario.remove(sceltaUnoField);
        vpScenario.remove(labelSceltaDue);
        vpScenario.remove(sceltaDueField);
        vpScenario.remove(creaScenarioAScelta);
    }
    
    private void showAdditionalFieldsIndovinello() {
        vpScenario.add(labelTestoScenarioIndovinello);
        vpScenario.add(testoScenarioFieldIndovinello);
        vpScenario.add(labelDomandaIndovinello);
        vpScenario.add(domandaFieldIndovinello);
        vpScenario.add(labelRispostaIndovinello);
        vpScenario.add(rispostaIndovinelloField);
        vpScenario.add(creaScenarioIndovinello);
    }
    
    private void hideAdditionalFieldsIndovinello() {
        vpScenario.remove(labelTestoScenarioIndovinello);
        vpScenario.remove(testoScenarioFieldIndovinello);
        vpScenario.remove(labelDomandaIndovinello);
        vpScenario.remove(domandaFieldIndovinello);
        vpScenario.remove(labelRispostaIndovinello);
        vpScenario.remove(rispostaIndovinelloField);
        vpScenario.remove(creaScenarioIndovinello);
    }
    
    private void showAdditionalFieldsOggetto() {
        vpScenario.add(labelTestoScenarioOggetto);
        vpScenario.add(testoScenarioFieldOggetto);
        vpScenario.add(labelOggetto);
        vpScenario.add(oggettoNecessario);
        vpScenario.add(creaScenarioOggetto);
    }
    
    private void hideAdditionalFieldsOggetto() {
        vpScenario.remove(labelTestoScenarioOggetto);
        vpScenario.remove(testoScenarioFieldOggetto);
        vpScenario.remove(labelOggetto);
        vpScenario.remove(oggettoNecessario);
        vpScenario.remove(creaScenarioOggetto);
    
    }
    
    
}
