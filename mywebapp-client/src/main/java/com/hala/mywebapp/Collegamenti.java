package com.hala.mywebapp;

import java.util.ArrayList;
import java.util.HashMap;

import org.checkerframework.checker.units.qual.m;

import com.google.common.util.concurrent.ListenableScheduledFuture;
import com.google.gwt.core.client.GWT;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.ui.*;
import com.shapesecurity.salvation2.Values.Hash;

public class Collegamenti extends Composite implements IsWidget {

    public static final GreetingServiceAsync hALAServiceAsync = GWT.create(GreetingService.class);
    private static final CollegamentiUiBinder uiBinder = GWT.create(CollegamentiUiBinder.class);
    private ArrayList<Scenario> scenariStoria;

    Scenario scenarioIniziale;

    interface CollegamentiUiBinder extends UiBinder<Widget, Collegamenti> {
    }

    @UiField
    HorizontalPanel totale;

    @UiField
    VerticalPanel ScenarioInizialePanel;

    @UiField
    Button buttonSettaScenarioIniziale;

    @UiField
    Label messageLabel;

    @UiField
    ListBox menuScenari;

    @UiField
    ListBox listaScenari;

    @UiField
    Label LscenarioIniziale;

    @UiField
    Label SelezionaScenario1;

    @UiField
    VerticalPanel CollegamentiPanel;

    @UiField
    Label testoScenarioDaCollegare;

    @UiField
    ListBox menuScenariCollegamenti;

    @UiField
    VerticalPanel ScenariDaCollegare;

    @UiField
    VerticalPanel pagina;

    @UiField
    Button terminaButton;

    @UiField
    Button backButton;

    @UiField
    VerticalPanel gestioneIndovinello;

    @UiField
    ListBox menuIndovinelli;

    @UiField
    VerticalPanel gestioneScelte;

    @UiField
    VerticalPanel gestioneFinale;

    @UiField
    ListBox menuScelte;

    @UiField
    Button settaSuccessivoAScelta;

    @UiField
    Button settaSuccessivoIndovinello;

    @UiField
    Button settaSuccessivoFinale;

    @UiField
    ListBox scenariSenzaPrecedente;

    public Collegamenti(String nomeStoria, Utente utente) {

        initWidget(uiBinder.createAndBindUi(this));
        String nome = nomeStoria;
        settaGrafica();

        hALAServiceAsync.ottieniScenariStoria(nome, new AsyncCallback<ArrayList<Scenario>>() {

            @Override
            public void onFailure(Throwable caught) {
                System.err.println("Errore");
            }

            @Override
            public void onSuccess(ArrayList<Scenario> result) {
                if (result != null) {
                    scenariStoria = result;
                    setScenaIniziale();
                } else {
                    // GEstire TODO
                }
            }
        });

        buttonSettaScenarioIniziale.addClickHandler(new ClickHandler() {
            @Override
            public void onClick(ClickEvent event) {
                int index = listaScenari.getSelectedIndex();
                String testo = listaScenari.getItemText(index);
                if (index != -1) { // controllo che sia stato selezionato uno scenario
                    Scenario attuale = new Scenario();
                    for (Scenario temp : scenariStoria) {
                        if (temp.getTestoScena().equals(testo)) {
                            attuale = temp;
                        }
                    }
                    hALAServiceAsync.settaScenarioIniziale(attuale, new AsyncCallback<Boolean>() {
                        @Override
                        public void onFailure(Throwable caught) {
                        }

                        @Override
                        public void onSuccess(Boolean result) {
                            if (result) {
                                messageLabel.setStyleName("messaggios");
                                messageLabel.setText("Scenario iniziale impostato con successo");
                                // attuale.addPrecedente("-1");
                                facciataSecondaria();
                            } else {
                                messageLabel.setStyleName("messaggioa");
                                messageLabel.setText("Impossibile impostare lo scenario iniziale");
                            }
                        }
                    });
                } else {
                    messageLabel.setStyleName("messaggioa");
                    messageLabel.setText("Selezionare uno scenario per impostarlo come iniziale");
                }
            }
        });

        terminaButton.addClickHandler(new ClickHandler() {
            @Override
            public void onClick(ClickEvent event) {
                hALAServiceAsync.salvaSuFileScenari(nomeStoria, new AsyncCallback<Boolean>() {
                    @Override
                    public void onFailure(Throwable caught) {
                    }

                    @Override
                    public void onSuccess(Boolean result) {
                        if (result) {
                            messageLabel.setStyleName("messaggios");
                            messageLabel.setText("Storia creata con successo");

                            if (utente != null) {
                                RootPanel.get("startTable").clear();
                                RootPanel.get("startTable").add(new HomePage(utente));
                            } else {
                                RootPanel.get("startTable").clear();
                                RootPanel.get("startTable").add(new Starter());
                            }

                        } else {
                            messageLabel.setStyleName("messaggioa");
                            messageLabel.setText(
                                    "Impossibile creare la storia, devi collegare ancora i seguenti\n scenari:");
                            scenariSenzaPrecedente = new ListBox();

                            for (Scenario temp : scenariStoria) {
                                if (temp.getPrecedente().size() == 0 || temp.getPrecedente() == null) {
                                    scenariSenzaPrecedente.addItem("1: " + temp.getTestoScena()); // TODO: perchè non
                                                                                                  // viene mostrata?
                                }
                            }
                        }
                    }
                });
            }
        });

        backButton.addClickHandler(new ClickHandler() {
            @Override
            public void onClick(ClickEvent event) {
                RootPanel.get("startTable").clear();
                RootPanel.get("startTable").add(new ScriviStoria(utente));
                // secondo me servira fare in modo che si torna alla creazione scenari
            }
        });

        menuScenari.addChangeHandler(event -> {
            int index = menuScenari.getSelectedIndex();
            menuScenariCollegamenti.clear(); // Elimino i valori
            riempiLista(menuScenariCollegamenti); // Li rimetto
            if (index != -1) {
                String testo = menuScenari.getItemText(index);
                for (Scenario temp : scenariStoria) {
                    if (temp.getTestoScena().equals(testo)) {

                        for (int i = 0; i < menuScenariCollegamenti.getItemCount(); i++) {
                            String t = menuScenariCollegamenti.getValue(i);
                            if (testo.equals(t)) {
                                menuScenariCollegamenti.removeItem(i); // Elimino cosi da non poter collegare due
                                                                       // scenari uguali
                            }
                        }

                        if (temp.getTipologia().toString().equalsIgnoreCase("ASCELTA")) {
                            ScenarioAScelta temp2 = (ScenarioAScelta) temp;
                            mostraSchermataScelte(temp2);
                        } else if (temp.getTipologia().toString().equalsIgnoreCase("INDOVINELLO")) {
                            ScenarioIndovinello temp2 = (ScenarioIndovinello) temp;
                            mostraSchermataIndovinello(temp2);
                        } else if (temp.getTipologia().toString().equalsIgnoreCase("DEFAULT")) {
                            Scenario temp2 = temp;
                            mostraSchermataFinale(temp2);
                        } else {
                            messageLabel.setStyleName("messaggioa");
                            messageLabel.setText("Errore");
                        }

                    }
                }
            }
        });

        settaSuccessivoAScelta.addClickHandler(new ClickHandler() {
            @Override
            public void onClick(ClickEvent event) {
                scenariSenzaPrecedente.setVisible(false);
                messageLabel.setText("");
                int indexControllo = menuScenari.getSelectedIndex();
                String controllo = menuScenari.getItemText(indexControllo);
                if (controllo.equals("Seleziona")) {
                    messageLabel.setText("Selezionare uno scenario");
                } else {
                    int index1 = menuScenari.getSelectedIndex();
                    int index2 = menuScenariCollegamenti.getSelectedIndex();
                    if (index1 != -1 && index2 != -1) {
                        String testo1 = menuScenari.getItemText(index1);
                        String testo2 = menuScenariCollegamenti.getItemText(index2);
                        Scenario temp1 = new ScenarioAScelta();
                        int indiceScelta = menuScelte.getSelectedIndex();
                        String scelta = menuScelte.getItemText(indiceScelta);
                        Scenario temp2 = new Scenario();

                        for (Scenario temp : scenariStoria) {
                            if (temp.getTestoScena().equals(testo1)) {
                                temp1 = (ScenarioAScelta) temp;
                            } else if (temp.getTestoScena().equals(testo2)) {
                                if (temp.getTipologia().toString().equalsIgnoreCase("ASCELTA")) {
                                    temp2 = (ScenarioAScelta) temp;
                                } else if (temp.getTipologia().toString().equalsIgnoreCase("INDOVINELLO")) {
                                    temp2 = (ScenarioIndovinello) temp;
                                } else if (temp.getTipologia().toString().equalsIgnoreCase("DEFAULT")) {
                                    temp2 = (Scenario) temp;
                                }
                            }
                        }
                        hALAServiceAsync.settaCollegamentoSuccessivo(temp1, scelta, temp2,
                                new AsyncCallback<Boolean>() {
                                    @Override
                                    public void onFailure(Throwable caught) {
                                    }

                                    @Override
                                    public void onSuccess(Boolean result) {
                                        if (result) {
                                            messageLabel.setStyleName("messaggios");
                                            messageLabel.setText("Scenario successivo impostato con successo");
                                            // qui da aggiungere qualcosa che rimuova la scelta gia collegata
                                        } else {
                                            messageLabel.setStyleName("messaggioa");
                                            messageLabel.setText("Impossibile impostare lo scenario successivo");
                                        }
                                    }
                                });
                    }
                }
            }
        });

        settaSuccessivoIndovinello.addClickHandler(new ClickHandler() {

            @Override
            public void onClick(ClickEvent event) {
                scenariSenzaPrecedente.setVisible(false);
                messageLabel.setText("");
                int indexControllo = menuScenari.getSelectedIndex();
                String controllo = menuScenari.getItemText(indexControllo);
                if (controllo.equals("Seleziona")) {
                    messageLabel.setText("Selezionare uno scenario");
                } else {
                    int index1 = menuScenari.getSelectedIndex();
                    int index2 = menuScenariCollegamenti.getSelectedIndex();
                    String testo1 = menuScenari.getItemText(index1);
                    String testo2 = menuScenariCollegamenti.getItemText(index2);
                    Scenario temp1 = new ScenarioIndovinello();
                    String opzione = "";
                    if (menuIndovinelli.getSelectedIndex() == 0) {
                        opzione = "false"; // Risposta sbagliata
                    } else {
                        opzione = "true"; // Risposta corretta
                    }
                    Scenario temp2 = new Scenario();
                    if (index1 != -1 && index2 != -1) {
                        for (Scenario temp : scenariStoria) {
                            if (temp.getTestoScena().equals(testo1)) {
                                temp1 = (ScenarioIndovinello) temp;
                            } else if (temp.getTestoScena().equals(testo2)) {
                                if (temp.getTipologia().toString().equalsIgnoreCase("ASCELTA")) {
                                    temp2 = (ScenarioAScelta) temp;
                                } else if (temp.getTipologia().toString().equalsIgnoreCase("INDOVINELLO")) {
                                    temp2 = (ScenarioIndovinello) temp;
                                } else if (temp.getTipologia().toString().equalsIgnoreCase("DEFAULT")) {
                                    temp2 = (Scenario) temp;
                                }
                            }
                        }
                        hALAServiceAsync.settaCollegamentoSuccessivo(temp1, opzione, temp2,
                                new AsyncCallback<Boolean>() {
                                    @Override
                                    public void onFailure(Throwable caught) {
                                    }

                                    @Override
                                    public void onSuccess(Boolean result) {
                                        if (result) {
                                            messageLabel.setStyleName("messaggios");
                                            messageLabel
                                                    .setText("Scenario indovinello successivo impostato con successo");
                                            // qui da aggiungere qualcosa
                                        } else {
                                            messageLabel.setStyleName("messaggioa");
                                            messageLabel.setText(
                                                    "Impossibile impostare lo scenario indovinello successivo");
                                        }
                                    }
                                });
                    }
                }
            }
        });

    }

    private void settaGrafica() {
        backButton.setStyleName("lButton");
        terminaButton.setStyleName("lButton");
        settaSuccessivoAScelta.setStyleName("lButton");
        buttonSettaScenarioIniziale.setStyleName("lButton");
        settaSuccessivoIndovinello.setStyleName("lButton");
        settaSuccessivoFinale.setStyleName("lButton");
        // messageLabel.setStyleName("messaggio");
        LscenarioIniziale.setStyleName("testi");
        scenariSenzaPrecedente.setVisible(false);
    }

    private void mostraSchermataScelte(ScenarioAScelta temp) {
        menuScelte.clear();
        gestioneIndovinello.setVisible(false);
        gestioneFinale.setVisible(false);
        gestioneScelte.setVisible(true);
        HashMap<String, String> opzioni = temp.getOpzioniScelta();
        for (HashMap.Entry<String, String> entry : opzioni.entrySet()) {
            menuScelte.addItem(entry.getKey());
        }
    }

    private void mostraSchermataIndovinello(ScenarioIndovinello temp) {
        gestioneScelte.setVisible(false);
        gestioneFinale.setVisible(false);
        gestioneIndovinello.setVisible(true);
    }

    private void mostraSchermataFinale(Scenario temp) {
        gestioneScelte.setVisible(false);
        gestioneFinale.setVisible(true);
        gestioneIndovinello.setVisible(false);
    }

    private void setScenaIniziale() {
        facciataIniziale();
    }

    private void facciataIniziale() {

        pagina.remove(messageLabel);
        pagina.remove(CollegamentiPanel);
        pagina.remove(terminaButton);
        pagina.remove(ScenariDaCollegare);
        pagina.remove(terminaButton);
        riempiListaTranneFinali(listaScenari);
        pagina.add(messageLabel);
        pagina.remove(gestioneScelte);
        pagina.remove(gestioneIndovinello);
        pagina.remove(gestioneFinale);
        listaScenari.setSelectedIndex(0);
        // listaScenari.setVisibleItemCount(scenariStoria.size());
        // menuScenari.setVisible(false);

    }

    private void facciataSecondaria() {
        pagina.remove(messageLabel);
        pagina.remove(ScenarioInizialePanel);
        pagina.add(ScenariDaCollegare);
        // pagina.remove(LscenarioIniziale);
        riempiListaTranneFinali(menuScenari);
        menuScenari.addItem("Seleziona");
        // menuScenari.setVisibleItemCount(scenariStoria.size());
        pagina.remove(backButton);
        pagina.add(gestioneScelte);
        pagina.add(gestioneIndovinello);
        pagina.add(gestioneFinale);
        gestioneScelte.setVisible(false);
        gestioneIndovinello.setVisible(false);
        gestioneFinale.setVisible(false);
        pagina.add(CollegamentiPanel);
        riempiLista(menuScenariCollegamenti);
        pagina.add(messageLabel);
        pagina.add(terminaButton);
        pagina.add(backButton);
        listaScenari.setSelectedIndex(0);
        menuScenariCollegamenti.setSelectedIndex(0);
        menuScenari.setSelectedIndex(menuScenari.getItemCount() - 1);

    }

    private void riempiListaTranneFinali(ListBox lb) {
        for (Scenario temp : scenariStoria) {
            if (!temp.getTipologia().toString().equalsIgnoreCase("DEFAULT")) {
                lb.addItem(temp.getTestoScena());
            }
        }
        lb.setSize("200px", "50px");
    }

    private void riempiLista(ListBox lb) {
        for (Scenario temp : scenariStoria) {
            lb.addItem(temp.getTestoScena());
        }
        lb.setSize("200px", "50px");
    }

}
