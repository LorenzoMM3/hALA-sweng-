package com.hala.mywebapp;

import java.util.ArrayList;
import java.util.HashMap;

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
import com.google.gwt.user.client.ui.ListBox;
import com.google.gwt.user.client.ui.RootPanel;
import com.google.gwt.user.client.ui.TextBox;
import com.google.gwt.user.client.ui.Widget;
import com.hala.mywebapp.VisualizzaCatalogo.VisualizzaUiBinder;
import com.shapesecurity.salvation2.Values.Hash;

public class GiocaStoria extends Composite {
    public static final GreetingServiceAsync hALAServiceAsync = GWT.create(GreetingService.class);

    private static final GiocaUiBinder UiB = GWT.create(GiocaUiBinder.class);

    Scenario scenarioAttuale;
    Partita partita;
    HashMap<String, String> opzioni;

    interface GiocaUiBinder extends UiBinder<Widget, GiocaStoria> {
    }

    @UiField
    Label testoScenarioLabel;

    @UiField
    Label domandaCambioScenarioLabel;

    @UiField
    Label rispostaCambioScenarioLabel;

    @UiField
    Label propostaOggettiSbloccabili;

    @UiField
    ListBox listBoxOggettiSbloccabili;

    @UiField
    Button buttonOggettiSbloccabili;

    @UiField
    Label labelOggettiSbloccabili;

    @UiField
    TextBox inserimentoRispostaTB;

    @UiField
    ListBox opzioniLB;

    @UiField
    Button invioRispostaButton;

    @UiField
    Button invioSelezioneSceltaButton;

    @UiField
    Button backButton;

    @UiField
    Button terminaButton;

    @UiField
    Label messageLabel;

    public GiocaStoria(Storia storia, Utente giocatore, boolean nuovoGioco) {
        initWidget(UiB.createAndBindUi(this));
        settaGrafica();
        partita = new Partita();
        scenarioAttuale = new Scenario();

        hALAServiceAsync.caricaPartita(storia, giocatore, nuovoGioco, new AsyncCallback<Partita>() {
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

        invioSelezioneSceltaButton.addClickHandler(new ClickHandler() {

            @Override
            public void onClick(ClickEvent event) {
                String scelta = opzioniLB.getSelectedItemText();
                String oggetto = opzioni.get(scelta);
                boolean possiedeOggetto = false;
                if (oggetto.length() > 0) {
                    // Controllo se l'utente possiede l'oggetto necessario per la scelta
                    possiedeOggetto = partita.controllaOggetto(oggetto);
                } else {
                    possiedeOggetto = true;
                }

                if (!possiedeOggetto) {
                    messageLabel.setStyleName("messaggioa");
                    messageLabel.setText("Non possiedi l'oggetto necessario per questa scelta");
                    return;
                } else {
                    messageLabel.setText("");
                    hALAServiceAsync.caricaSuccessivoScelta(partita, scelta, new AsyncCallback<Partita>() {
                        @Override
                        public void onFailure(Throwable throwable) {
                            GWT.log("Errore durante la chiamata asincrona al servizio remoto", throwable);
                        }

                        @Override
                        public void onSuccess(Partita result) {
                            partita = result;
                            opzioni.clear();
                            riempiCampi();
                        }
                    });
                }

            }

        });

        backButton.addClickHandler(new ClickHandler() {
            @Override
            public void onClick(ClickEvent event) {
                hALAServiceAsync.ottieniUtenteAttuale(new AsyncCallback<Utente>() {
                    public void onFailure(Throwable caught) {
                        System.err.println("Errore qui");
                    };

                    public void onSuccess(Utente utente) {
                        if (utente != null) {
                            Utente utenteAttuale = utente;
                            RootPanel.get("startTable").clear();
                            RootPanel.get("startTable").add(new HomePage(utenteAttuale.getUsername()));
                        } else {
                            RootPanel.get("startTable").clear();
                            RootPanel.get("startTable").add(new Starter());
                        }
                    }
                });
            }
        });

        buttonOggettiSbloccabili.addClickHandler(new ClickHandler() {
            @Override
            public void onClick(ClickEvent event) {
                int selectedIndex = listBoxOggettiSbloccabili.getSelectedIndex();
                if (selectedIndex != -1) {
                    String oggettoSbloccabile = listBoxOggettiSbloccabili.getItemText(selectedIndex);
                    partita.addInventario(oggettoSbloccabile);
                    labelOggettiSbloccabili.setStyleName("messaggios");
                    labelOggettiSbloccabili.setText("Oggetto Raccolto");
                }

            }
        });

        terminaButton.addClickHandler(new ClickHandler() {
            @Override
            public void onClick(ClickEvent event) {
                hALAServiceAsync.ottieniUtenteAttuale(new AsyncCallback<Utente>() {
                    public void onFailure(Throwable caught) {
                        System.err.println("Errore qui");
                    };

                    public void onSuccess(Utente utente) {
                        if (utente != null) {
                            Utente utenteAttuale = utente;

                            hALAServiceAsync.eliminaPartita(storia, utenteAttuale, new AsyncCallback<Boolean>() {
                                @Override
                                public void onFailure(Throwable throwable) {
                                    GWT.log("Errore durante la chiamata asincrona al servizio remoto", throwable);
                                }

                                @Override
                                public void onSuccess(Boolean verifica) {
                                    RootPanel.get("startTable").clear();
                                    RootPanel.get("startTable").add(new HomePage(utenteAttuale.getUsername()));
                                }
                            });

                        } else {
                            RootPanel.get("startTable").clear();
                            RootPanel.get("startTable").add(new Starter()); // da sistemareeeeeee (nel termina non va
                                                                            // questo pezzo, metteremo un mess)
                        }
                    }
                });
            }
        });

    }

    public void proponiOggettiRaccoglibili(Scenario scenarioAttuale) {
        if (scenarioAttuale.getOggettiCheSblocca().size() != 0) {
            ArrayList<String> oggettiSbloccabili = scenarioAttuale.getOggettiCheSblocca();
            for (String oggettoSbloccabile : oggettiSbloccabili) {
                if (!partita.getInventario().contains(oggettoSbloccabile)) {
                    mostraPropostaOggetto();
                    propostaOggettiSbloccabili.setText("Puoi raccogliere i seguenti oggetti:");
                    listBoxOggettiSbloccabili.addItem(oggettoSbloccabile);
                }
            }
            return;
        } else {
            return;
        }
    }

    public void mostraPropostaOggetto() {
        listBoxOggettiSbloccabili.setVisible(true);
        buttonOggettiSbloccabili.setVisible(true);
        propostaOggettiSbloccabili.setVisible(true);
        labelOggettiSbloccabili.setVisible(true);
    }

    public void nascondiPropostaOggetto() {
        listBoxOggettiSbloccabili.setVisible(false);
        buttonOggettiSbloccabili.setVisible(false);
        propostaOggettiSbloccabili.setVisible(false);
        labelOggettiSbloccabili.setVisible(false);
    }

    public void settaGrafica() {
        buttonOggettiSbloccabili.setStyleName("lButton");
        terminaButton.setStyleName("lButton");
        propostaOggettiSbloccabili.setStyleName("testi");
        backButton.setStyleName("lButton");
        testoScenarioLabel.setStyleName("testi");
        domandaCambioScenarioLabel.setStyleName("testi");
        rispostaCambioScenarioLabel.setStyleName("testi");
        invioRispostaButton.setStyleName("lButton");
        invioSelezioneSceltaButton.setStyleName("lButton");
    }

    public void riempiCampi() {
        nascondiPropostaOggetto();
        opzioni = new HashMap<>();
        scenarioAttuale = partita.getScenarioAttuale();
        String tipologia = scenarioAttuale.getTipologia().toString();
        String testo = scenarioAttuale.getTestoScena();
        testoScenarioLabel.setText(testo);

        if (tipologia.equals("ASCELTA")) {
            proponiOggettiRaccoglibili(scenarioAttuale);
            String domanda = ((ScenarioAScelta) scenarioAttuale).getDomandaCambioScenario();
            domandaCambioScenarioLabel.setText(domanda);
            opzioni = ((ScenarioAScelta) scenarioAttuale).getOpzioniScelta();
            mostraPerScelta();

        } else if (tipologia.equals("INDOVINELLO")) {
            proponiOggettiRaccoglibili(scenarioAttuale);
            mostraPerIndovinello();
            String indovinello = ((ScenarioIndovinello) scenarioAttuale).getDomandaCambioScenario();
            domandaCambioScenarioLabel.setText(indovinello);

        } else if (tipologia.equals("DEFAULT")) {
            mostraPerFinale();
        }
    }

    private void mostraPerScelta() {
        opzioniLB.setVisible(true);
        rispostaCambioScenarioLabel.setText("Seleziona l'opzione:");
        testoScenarioLabel.setVisible(true);
        inserimentoRispostaTB.setVisible(false);
        invioRispostaButton.setVisible(false);
        invioSelezioneSceltaButton.setVisible(true);
        terminaButton.setVisible(false);
        backButton.setVisible(true);

        riempiLB(opzioniLB);
    }

    private void mostraPerIndovinello() {
        rispostaCambioScenarioLabel.setText("Inserisci la tua risposta:");
        testoScenarioLabel.setVisible(true);
        inserimentoRispostaTB.setVisible(true);
        invioRispostaButton.setVisible(true);
        opzioniLB.setVisible(false);
        invioSelezioneSceltaButton.setVisible(false);
        terminaButton.setVisible(false);
        backButton.setVisible(true);

    }

    private void mostraPerFinale() {
        testoScenarioLabel.setVisible(true);
        terminaButton.setVisible(true);
        backButton.setVisible(false);
        propostaOggettiSbloccabili.setVisible(false);
        buttonOggettiSbloccabili.setVisible(false);
        labelOggettiSbloccabili.setVisible(false);
        listBoxOggettiSbloccabili.setVisible(false);
        domandaCambioScenarioLabel.setVisible(false);
        rispostaCambioScenarioLabel.setVisible(false);
        inserimentoRispostaTB.setVisible(false);
        invioRispostaButton.setVisible(false);
        opzioniLB.setVisible(false);
        invioSelezioneSceltaButton.setVisible(false);

    }

    private void riempiLB(ListBox lb) {
        lb.clear();
        for (String opzione : opzioni.keySet()) {
            lb.addItem(opzione);
        }
        lb.setVisibleItemCount(opzioni.size());
    }
}