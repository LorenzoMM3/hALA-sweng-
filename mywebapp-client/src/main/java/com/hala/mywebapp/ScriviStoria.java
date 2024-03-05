package com.hala.mywebapp;

import java.util.ArrayList;
import java.util.Map;

import org.checkerframework.checker.guieffect.qual.UI;

import com.google.gwt.core.client.GWT;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.dev.util.collect.HashMap;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.ui.*;
import com.hala.mywebapp.GreetingServiceAsync;
import com.hala.mywebapp.GreetingService;

public class ScriviStoria extends Composite implements IsWidget {

    public static final GreetingServiceAsync hALAServiceAsync = GWT.create(GreetingService.class);
    private static final ScriviStoriaUiBinder uiBinder = GWT.create(ScriviStoriaUiBinder.class);
    private ArrayList<String> opzioniSceltaTemp;
    private ArrayList<Scenario> scenariCreati;
    private String nomeStoriaTemp;
    private int numeroScen = 0;
    private Map<String, Scenario> scenariNelSito;
    private String idTemp = "";
    interface ScriviStoriaUiBinder extends UiBinder<Widget, ScriviStoria> {
    }

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
    Label message;

    @UiField
    Label labelSceltaOggetto;

    @UiField
    TextBox oggetto;

    @UiField
    Button creaCollegamenti;

    public ScriviStoria() {
        initWidget(uiBinder.createAndBindUi(this));
        hideAdditionalFields();
        disabilitaTutto();
        scenariCreati = new ArrayList<>();
        
        inserisciStoria.addClickHandler(new ClickHandler() {
            @Override
            public void onClick(ClickEvent event) {
                nomeStoriaTemp = titoloStoria.getText().toUpperCase();

                hALAServiceAsync.ottieniUtenteAttuale(new AsyncCallback<Utente>() {
                    public void onFailure(Throwable caught) {
                        System.err.println("Errore qui");
                    };

                    public void onSuccess(Utente utente) {
                        final Utente utenteAttuale = utente;
                        Storia nuovaStoria = new Storia(nomeStoriaTemp, utenteAttuale);
                        hALAServiceAsync.creaNuovaStoria(nuovaStoria, new AsyncCallback<Boolean>() {
                            public void onFailure(Throwable caught) {
                            };

                            public void onSuccess(Boolean verifica) {
                                if (verifica) {
                                    messageLabelCreate.setText("Storia creata con successo");
                                    titoloS.remove(inserisciStoria);
                                    attivaTutto();
                                    menuTipoScenario.setSelectedIndex(0);
                                    showAdditionalFieldsAScelta();
                                    opzioniSceltaTemp = new ArrayList<>();
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
            message.setText("");
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
        });

        altraScelta.addClickHandler(new ClickHandler() {

            @Override
            public void onClick(ClickEvent event) {

                String oggettoTemp = oggetto.getText(); // Uguale a "" se non serve
                opzioniSceltaTemp.add(scelta.getText() + ";" + oggettoTemp);
                oggetto.setText("");
                scelta.setText("");
            }

        });

        creaScenarioAScelta.addClickHandler(new ClickHandler() {
            @Override
            public void onClick(ClickEvent event) {
                //Chiedo al server l'id per la creazione
                idTemp = "";
                hALAServiceAsync.prossimoId(new AsyncCallback<String>() {
                    @Override
                    public void onFailure(Throwable caught) {
                    }

                    @Override
                    public void onSuccess(String result) {
                        idTemp = result;
                    }
                
                });

                ScenarioAScelta scenario = new ScenarioAScelta(idTemp, nomeStoriaTemp);
                scenario.setValId(idTemp);
                scenario.setTestoScena(testoScenarioField.getText());
                scenario.setDomandaCambioScenario(domandaFieldAScelta.getText());
                scenario.setOpzioniScelte(opzioniSceltaTemp);

                message.setText("");
                vpScenario.add(message);
                hALAServiceAsync.aggiungiScenarioAScelta(idTemp, scenario, new AsyncCallback<Boolean>() {
                    @Override
                    public void onFailure(Throwable caught) {
                    }

                    public void onSuccess(Boolean verifica) {
                        if (verifica) {
                            message.setText("Scenario a scelta creato con successo");
                            scenariCreati.add(scenario);

                        } else {
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
                idTemp = "";
                hALAServiceAsync.prossimoId(new AsyncCallback<String>() { //!!!
                    @Override
                    public void onFailure(Throwable caught) {
                    }

                    @Override
                    public void onSuccess(String result) {
                        idTemp = result;
                    }
                
                });

                ScenarioIndovinello scenario = new ScenarioIndovinello(idTemp, nomeStoriaTemp);

                scenario.setValId(idTemp);
                scenario.setTestoScena(testoScenarioField.getText());

                scenario.setDomandaIndovinello(domandaFieldIndovinello.getText());
                scenario.setRispostaIndovinello(rispostaFieldIndovinello.getText());

                message.setText("");
                vpScenario.add(message);

                hALAServiceAsync.aggiungiScenarioIndovinello(idTemp, scenario, new AsyncCallback<Boolean>() {
                    @Override
                    public void onFailure(Throwable caught) {
                    }

                    public void onSuccess(Boolean verifica) {
                        if (verifica) {
                            message.setText("Scenario Indovinello creato con successo");
                            scenariCreati.add(scenario);
                        } else {
                            message.setText("Errore nella creazione dello scenario indovinello");
                        }
                    }
                });
                testoScenarioField.setText("");
                domandaFieldIndovinello.setText("");
                rispostaFieldIndovinello.setText("");
            }
        });

        backButton.addClickHandler(new ClickHandler() {
            @Override
            public void onClick(ClickEvent event) {
                RootPanel.get("startTable").clear();
                RootPanel.get("startTable").add(new HomePage());
            }
        });

        creaCollegamenti.addClickHandler(new ClickHandler() {
            @Override
            public void onClick(ClickEvent event) {
                RootPanel.get("startTable").clear();
                RootPanel.get("startTable").add(new Collegamenti(nomeStoriaTemp, scenariCreati));
            }
        });
    }

    private void disabilitaTutto() {
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
        labelSceltaOggetto.setVisible(false);
        oggetto.setVisible(false);
        // creaCollegamenti.setVisible(false);
    }

    private void attivaTutto() {
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
        labelSceltaOggetto.setVisible(true);
        oggetto.setVisible(true);

    }

    private void hideAdditionalFields() {
        hideAdditionalFieldsAScelta();
        hideAdditionalFieldsIndovinello();
    }

    private void showAdditionalFieldsAScelta() {
        vpScenario.add(labelDomandaAScelta);
        vpScenario.add(domandaFieldAScelta);
        vpScenario.add(labelScelta);
        vpScenario.add(scelta);
        vpScenario.add(labelSceltaOggetto);
        vpScenario.add(oggetto);
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
        vpScenario.remove(labelSceltaOggetto);
        vpScenario.remove(oggetto);
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

}
