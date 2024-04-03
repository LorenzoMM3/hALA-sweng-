package com.hala.mywebapp;

import java.util.ArrayList;

import com.google.gwt.core.client.GWT;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.ui.*;
import com.google.gwt.user.client.Window;

public class ScriviStoria extends Composite {

    public static final GreetingServiceAsync hALAServiceAsync = GWT.create(GreetingService.class);
    private static final ScriviStoriaUiBinder uiBinder = GWT.create(ScriviStoriaUiBinder.class);
    private ArrayList<String> opzioniSceltaTemp;
    private ArrayList<Scenario> scenariCreati;
    private String nomeStoriaTemp;
    private String idTemp = "";
    private ArrayList<String> oggettiSbloccabili;
    String tiposcena = "";

    interface ScriviStoriaUiBinder extends UiBinder<Widget, ScriviStoria> {
    }

    @UiField
    VerticalPanel titoloS;

    @UiField
    Label messageLabelCreate;

    @UiField
    Label messageLabel;

    @UiField
    Label labelTitolo;

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

    @UiField
    Button creaScenarioFinale;

    @UiField
    Button inserisciOggettoButton;

    @UiField
    TextBox inserisciOggettoTextBox;

    @UiField
    Label inserisciOggettoLabel;

    @UiField
    Anchor link1;

    public ScriviStoria(Utente utente) {
        initWidget(uiBinder.createAndBindUi(this));
        hideAdditionalFields();
        disabilitaTutto();
        scenariCreati = new ArrayList<>();
        oggettiSbloccabili = new ArrayList<>();
        settaGrafica();

        inserisciStoria.addClickHandler(new ClickHandler() {

            @Override
            public void onClick(ClickEvent event) {
                
                    if (!titoloStoria.getText().isEmpty()) {
                        message.setText("");
                        nomeStoriaTemp = titoloStoria.getText().toUpperCase();
                        Storia nuovaStoria = new Storia(nomeStoriaTemp, utente);
                        hALAServiceAsync.creaNuovaStoria(nuovaStoria, new AsyncCallback<Boolean>() {
                            public void onFailure(Throwable caught) {
                            };

                            public void onSuccess(Boolean verifica) {
                                if (verifica) {
                                    messageLabelCreate.setStyleName("messaggios");
                                    messageLabelCreate.setText("Storia creata con successo.");
                                    titoloS.remove(inserisciStoria);
                                    attivaTutto();
                                    menuTipoScenario.setSelectedIndex(0);
                                    showAdditionalFieldsAScelta();
                                    opzioniSceltaTemp = new ArrayList<>();
                                } else {

                                    messageLabelCreate.setStyleName("messaggioa");
                                    messageLabelCreate.setText("Nome già esistente, riprova con un altro nome.");
                                }
                            };
                        });
                    } else {
                        message.setStyleName("messaggioa");
                        message.setText("Forse manca qualcosa... Inserisci il titolo della storia");
                    }

                
            }

        });

        menuTipoScenario.addChangeHandler(event -> {
            message.setText("");
            if ("Scenario a scelta".equals(menuTipoScenario.getSelectedValue())) {
                hideAdditionalFieldsIndovinello();
                hideAdditionalFieldsFinale();
                showAdditionalFieldsAScelta();
                opzioniSceltaTemp = new ArrayList<>();
            } else if ("Scenario con indovinello".equals(menuTipoScenario.getSelectedValue())) {
                hideAdditionalFieldsAScelta();
                hideAdditionalFieldsFinale();
                showAdditionalFieldsIndovinello();
            } else if ("Scenario finale".equals(menuTipoScenario.getSelectedValue())) {
                hideAdditionalFieldsAScelta();
                hideAdditionalFieldsIndovinello();
                showAdditionalFieldsFinale();
            }

        });

        altraScelta.addClickHandler(new ClickHandler() {
            @Override
            public void onClick(ClickEvent event) {
                message.setText("");
                if (!scelta.getText().isEmpty() && !scelta.getText().trim().equals("") && scelta.getText().length() <= 50) {
                    String oggettoTemp = oggetto.getText(); // Uguale a "" se non serve
                    opzioniSceltaTemp.add(scelta.getText() + ";" + oggettoTemp);
                    oggetto.setText("");
                    scelta.setText("");
                } else {
                    message.setStyleName("messaggioa");
                    message.setText("Scelta non aggiunta. Il testo non può essere vuoto o troppo lungo.");
                    Window.scrollTo(0, 0);
                }
            }

        });

        inserisciOggettoButton.addClickHandler(new ClickHandler() {
            @Override
            public void onClick(ClickEvent event) {
                message.setText("");
                String oggetto = inserisciOggettoTextBox.getText();
                if (!oggetto.trim().equals("")) {
                    oggetto = oggetto.toUpperCase();
                    oggetto = oggetto.trim();
                    oggettiSbloccabili.add(oggetto);
                    message.setStyleName("messaggios");
                    message.setText("Oggetto Sbloccabile Inserito nello Scenario.");
                    inserisciOggettoTextBox.setText("");
                } else {
                    message.setStyleName("messaggioa");
                    message.setText("Oggetto non aggiunto. Inserire un Oggetto Sbloccabile nella casella di testo.");
                    Window.scrollTo(0, 0);
                }
            }
        });

        creaScenarioAScelta.addClickHandler(new ClickHandler() {
            @Override
            public void onClick(ClickEvent event) {
                if (controlloScenari()) {

                    idTemp = "";
                    hALAServiceAsync.prossimoId(new AsyncCallback<String>() {
                        @Override
                        public void onFailure(Throwable caught) {
                        }

                        @Override
                        public void onSuccess(String result) {
                            message.setText("");
                            idTemp = result;
                            ScenarioAScelta scenario = new ScenarioAScelta(nomeStoriaTemp);
                            scenario.setValId(idTemp);
                            scenario.setTestoScena(testoScenarioField.getText());
                            if (domandaFieldAScelta.getText().length() <= 100) {
                                scenario.setDomandaCambioScenario(domandaFieldAScelta.getText());
                                scenario.setOpzioniScelte(opzioniSceltaTemp);
                                scenario.setOggettiCheSblocca(oggettiSbloccabili);

                                message.setText("");
                                vpScenario.add(message);
                                hALAServiceAsync.aggiungiScenarioAScelta(idTemp, scenario, new AsyncCallback<Boolean>() {
                                    @Override
                                    public void onFailure(Throwable caught) {
                                    }

                                    public void onSuccess(Boolean verifica) {
                                        if (verifica) {
                                            message.setStyleName("messaggios");
                                            message.setText("Scenario a scelta creato con successo");
                                            scenariCreati.add(scenario);
                                            // Resetto i field così da liberarli per la creazione di un nuovo scenario
                                            testoScenarioField.setText("");
                                            domandaFieldAScelta.setText("");
                                            scelta.setText("");
                                            // Libero l'array di scelte
                                            opzioniSceltaTemp.clear();
                                            oggettiSbloccabili.clear();

                                        } else {
                                            message.setStyleName("messaggioa");
                                            message.setText("Errore nella creazione dello scenario a scelta");
                                            // Resetto i field così da liberarli per la creazione di un nuovo scenario
                                            testoScenarioField.setText("");
                                            domandaFieldAScelta.setText("");
                                            scelta.setText("");
                                            // Libero l'array di scelte
                                            opzioniSceltaTemp.clear();
                                            oggettiSbloccabili.clear();
                                        }
                                    }
                                });
                            } else {
                                message.setStyleName("messaggioa");
                                message.setText("Domanda troppo lunga. Inserisci una domanda più corta.");
                                Window.scrollTo(0, 0);
                            }

                        }

                    });
                } else {
                    message.setStyleName("messaggioa");
                    message.setText("Riempi tutti i campi o aggiungi ancora una scelta (devono essere almeno due).");
                    Window.scrollTo(0, 0);
                }

            }
        });

        creaScenarioIndovinello.addClickHandler(new ClickHandler() {
            @Override
            public void onClick(ClickEvent event) {
                if (controlloScenari()) {// chiamo il metodo di controllo degli scenari
                    idTemp = "";
                    hALAServiceAsync.prossimoId(new AsyncCallback<String>() { // !!!
                        @Override
                        public void onFailure(Throwable caught) {
                        }

                        @Override
                        public void onSuccess(String result) {
                            idTemp = result;
                            message.setText("");
                            ScenarioIndovinello scenario = new ScenarioIndovinello(nomeStoriaTemp);

                            scenario.setValId(idTemp);
                            
                            scenario.setTestoScena(testoScenarioField.getText());

                            if (domandaFieldIndovinello.getText().length() <= 100){
                                scenario.setDomandaCambioScenario(domandaFieldIndovinello.getText());
                                scenario.setRispostaIndovinello(rispostaFieldIndovinello.getText());
                                scenario.setOggettiCheSblocca(oggettiSbloccabili);

                                message.setText("");
                                vpScenario.add(message);

                                hALAServiceAsync.aggiungiScenarioIndovinello(idTemp, scenario,
                                        new AsyncCallback<Boolean>() {
                                            @Override
                                            public void onFailure(Throwable caught) {
                                            }

                                            public void onSuccess(Boolean verifica) {
                                                if (verifica) {
                                                    scenariCreati.add(scenario);
                                                    message.setStyleName("messaggios");
                                                    message.setText("Scenario Indovinello creato con successo");
                                                    testoScenarioField.setText("");
                                                    domandaFieldIndovinello.setText("");
                                                    rispostaFieldIndovinello.setText("");
                                                    oggettiSbloccabili.clear();

                                                } else {
                                                    message.setStyleName("messaggioa");
                                                    message.setText("Errore nella creazione dello scenario indovinello");
                                                    testoScenarioField.setText("");
                                                    domandaFieldIndovinello.setText("");
                                                    rispostaFieldIndovinello.setText("");
                                                    oggettiSbloccabili.clear();
                                                }
                                            }
                                        });
                            } else {
                            message.setStyleName("messaggioa");
                            message.setText("Domanda troppo lunga. Inserisci una domanda più corta.");
                            Window.scrollTo(0, 0);
                            }
                        }
                    });
                    
                } else {
                    message.setStyleName("messaggioa");
                    message.setText("Scenario non creato. Riempi prima tutti i campi.");
                    Window.scrollTo(0, 0);
                }

            }
        });

        creaScenarioFinale.addClickHandler(new ClickHandler() {
            @Override
            public void onClick(ClickEvent event) {
                if (controlloScenari()) {// chiamo il metodo di controllo degli scenari
                    idTemp = "";
                    hALAServiceAsync.prossimoId(new AsyncCallback<String>() { // !!!
                        @Override
                        public void onFailure(Throwable caught) {
                        }

                        @Override
                        public void onSuccess(String result) {
                            idTemp = result;
                            Scenario scenario = new Scenario(nomeStoriaTemp);

                            scenario.setValId(idTemp);
                            scenario.setTestoScena(testoScenarioField.getText());

                            message.setText("");
                            vpScenario.add(message);

                            hALAServiceAsync.aggiungiScenarioFinale(idTemp, scenario,
                                    new AsyncCallback<Boolean>() {
                                        @Override
                                        public void onFailure(Throwable caught) {
                                        }

                                        public void onSuccess(Boolean verifica) {
                                            if (verifica) {
                                                scenariCreati.add(scenario);
                                                message.setStyleName("messaggios");
                                                message.setText("Scenario finale creato con successo");
                                                testoScenarioField.setText("");

                                            } else {
                                                message.setStyleName("messaggioa");
                                                message.setText("Errore nella creazione dello scenario finale");
                                                testoScenarioField.setText("");
                                            }
                                        }
                                    });
                        }

                    });
                } else {
                    message.setStyleName("messaggioa");
                    message.setText("Scenario non creato. Inserisci il testo dello scenario finale.");
                    Window.scrollTo(0, 0);
                }

            }
        });

        backButton.addClickHandler(new ClickHandler() {
            @Override
            public void onClick(ClickEvent event) {
                hALAServiceAsync.eliminaStoria(nomeStoriaTemp,
                        new AsyncCallback<Boolean>() {
                            @Override
                            public void onFailure(Throwable caught) {
                            }

                            public void onSuccess(Boolean verifica) {
                                if (utente != null) {
                                    RootPanel.get("startTable").clear();
                                    RootPanel.get("startTable").add(new HomePage(utente));
                                } else {
                                    RootPanel.get("startTable").clear();
                                    RootPanel.get("startTable").add(new Starter());
                                }

                            }
                        });
            }
        });

        link1.addClickHandler(event -> {
            String istruzioni = "1. Inserisci il titolo della storia\n"
                    + "2. Scegli il tipo di scenario: a scelta, a indovinello o finale\n" +
            "2.1 a scelta: inserisci le opzioni tra le quali il giocatore dovrà fare la sua scelta\n" +
            "2.1.1 è facoltativo aggiungere il vincolo di un oggetto da avere nell'inventario per poter fare quella scelta\n" +
            "2.2 a indovinello: l'utente dovrà rispondere ad un indovinello\n" +
            "2.3 finale: sarà la conclusione della giocata\n" +
            "3. Inserisci il testo dello scenario\n" +
            "4. Inserisci l'oggetto (anche più di uno) che l'utente può raccogliere nello scenario (è facoltativo)\n" +
            "5. Clicca \"crea\" e ricomincia per tutti gli scenari che vuoi creare\n" +
            "6. Una volta scritti tutti gli scenari clicca il bottone per creare i collegamenti\n" +
            "6.1 All'inizio setta lo scenario da cui il giocatore partirà\n" +
            "6.2 Fai i collegamenti in base a quale scenario vuoi che il giocatore venga portato per le varie scelte che prende\n";
            Window.alert(istruzioni);
        });

       

        creaCollegamenti.addClickHandler(new ClickHandler() {

            @Override
            public void onClick(ClickEvent event) {
                if (scenariCreati.size() > 1) {// controllo per verificare che le storie create abbiano almeno due
                                               // scenari
                    RootPanel.get("startTable").clear();
                    RootPanel.get("startTable").add(new Collegamenti(nomeStoriaTemp, utente));
                } else {
                    message.setStyleName("messaggioa");
                    message.setText(
                            "Forse manca qualcosa... (Consiglio: inserisci almeno 2 scenari nella storia, se crei uno scenario a scelta inserisci almeno due scelte).");
                }
            }

        });
    }

    // metodo per controllare se gli scenari hanno tutti i campi pieni e lo scenario
    // a scelta abbia almeno due opzioni di scelta
    private boolean controlloScenari() {
        if ("Scenario a scelta".equals(menuTipoScenario.getSelectedValue())) {
            if (opzioniSceltaTemp.size() > 1 && !(testoScenarioField.getText().isEmpty())
                    && !(domandaFieldAScelta.getText().isEmpty())) {
                return true;
            }
        }
        if ("Scenario con indovinello".equals(menuTipoScenario.getSelectedValue())) {
            if (!(testoScenarioField.getText().isEmpty()) && !(domandaFieldIndovinello.getText().isEmpty())
                    && !(rispostaFieldIndovinello.getText().isEmpty())) {
                return true;
            }
        }
        if ("Scenario finale".equals(menuTipoScenario.getSelectedValue())) {
            if (!(testoScenarioField.getText().isEmpty())) {
                return true;
            }
        }
        return false;
    }

    private void disabilitaTutto() {
        labelTestoScenario.setVisible(false);
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
        inserisciOggettoLabel.setVisible(false);
        inserisciOggettoTextBox.setVisible(false);
        inserisciOggettoButton.setVisible(false);
        creaCollegamenti.setVisible(false);
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
        inserisciOggettoLabel.setVisible(true);
        inserisciOggettoTextBox.setVisible(true);
        inserisciOggettoButton.setVisible(true);
        creaCollegamenti.setVisible(true);

    }

    private void hideAdditionalFields() {
        hideAdditionalFieldsAScelta();
        hideAdditionalFieldsIndovinello();
        hideAdditionalFieldsFinale();
    }

    private void showAdditionalFieldsAScelta() {
        vpScenario.add(labelDomandaAScelta);
        vpScenario.add(domandaFieldAScelta);
        vpScenario.add(labelScelta);
        vpScenario.add(scelta);
        vpScenario.add(labelSceltaOggetto);
        vpScenario.add(oggetto);
        vpScenario.add(altraScelta);
        aggiungiOggettifields();
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
        rimuoviOggettiFields();
        vpScenario.remove(oggetto);
    }

    private void showAdditionalFieldsIndovinello() {
        vpScenario.add(labelDomandaIndovinello);
        vpScenario.add(domandaFieldIndovinello);
        vpScenario.add(labelRispostaIndovinello);
        vpScenario.add(rispostaFieldIndovinello);
        aggiungiOggettifields();
        vpScenario.add(creaScenarioIndovinello);
    }

    private void showAdditionalFieldsFinale() {
        vpScenario.add(creaScenarioFinale);
    }

    private void hideAdditionalFieldsFinale() {
        vpScenario.remove(creaScenarioFinale);
        rimuoviOggettiFields();
    }

    private void hideAdditionalFieldsIndovinello() {
        vpScenario.remove(labelDomandaIndovinello);
        vpScenario.remove(domandaFieldIndovinello);
        vpScenario.remove(labelRispostaIndovinello);
        vpScenario.remove(rispostaFieldIndovinello);
        rimuoviOggettiFields();
        vpScenario.remove(creaScenarioIndovinello);
    }

    private void aggiungiOggettifields() {
        vpScenario.add(inserisciOggettoLabel);
        vpScenario.add(inserisciOggettoTextBox);
        vpScenario.add(inserisciOggettoButton);
    }

    private void rimuoviOggettiFields() {
        vpScenario.remove(inserisciOggettoLabel);
        vpScenario.remove(inserisciOggettoTextBox);
        vpScenario.remove(inserisciOggettoButton);
    }

    private void settaGrafica() {
        RootPanel.get("startTable").clear();
        backButton.setStyleName("lButton");
        inserisciStoria.setStyleName("lButton");
        creaScenarioAScelta.setStyleName("lButton");
        altraScelta.setStyleName("lButton");
        creaScenarioIndovinello.setStyleName("lButton");
        creaCollegamenti.setStyleName("lButton");
        creaScenarioFinale.setStyleName("lButton");
        inserisciOggettoButton.setStyleName("lButton");
        labelTitolo.setStyleName("testi");
        scriviScenarioLabel.setStyleName("testi");
        tipologiaLabel.setStyleName("testi");
        labelTestoScenario.setStyleName("testi");
        messageLabel.setStyleName("messaggio");
        titoloS.setStyleName("sPanel");
        inserisciOggettoLabel.setStyleName("testi");
        labelSceltaOggetto.setStyleName("testi");
        labelRispostaIndovinello.setStyleName("testi");
        labelDomandaIndovinello.setStyleName("testi");
        labelScelta.setStyleName("testi");
        labelDomandaAScelta.setStyleName("testi");
        titoloStoria.setStyleName("textBox");
        testoScenarioField.setStyleName("textBox");
        domandaFieldAScelta.setStyleName("textBox");
        scelta.setStyleName("textBox");
        domandaFieldIndovinello.setStyleName("textBox");
        rispostaFieldIndovinello.setStyleName("textBox");
        oggetto.setStyleName("textBox");
        inserisciOggettoTextBox.setStyleName("textBox");
        menuTipoScenario.setStyleName("listBox");
        link1.setStyleName("messaggioa");
        
    }
}