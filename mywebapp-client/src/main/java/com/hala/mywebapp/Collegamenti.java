package com.hala.mywebapp;

import java.util.ArrayList;


import com.google.gwt.core.client.GWT;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.ui.*;

public class Collegamenti extends Composite implements IsWidget {

    public static final GreetingServiceAsync hALAServiceAsync = GWT.create(GreetingService.class);
    private static final CollegamentiUiBinder uiBinder = GWT.create(CollegamentiUiBinder.class);
    private ArrayList<Scenario> scenariStoria;
    Scenario attuale;
    Scenario scenarioIniziale;

    interface CollegamentiUiBinder extends UiBinder<Widget, Collegamenti> {
    }

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
    Button settaSuccessivo;
    
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



    public Collegamenti(String nomeStoria) {

        initWidget(uiBinder.createAndBindUi(this));
        String nome = nomeStoria;
        
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
                }
                else{
                    //GEstire TODO
                }
            }
        });

        

        buttonSettaScenarioIniziale.addClickHandler(new ClickHandler() {
            @Override
            public void onClick(ClickEvent event) {
                int index = listaScenari.getSelectedIndex();
                if (index != -1) { // controllo che sia stato selezionato uno scenario
                    attuale = scenariStoria.get(index);
                    hALAServiceAsync.settaScenarioIniziale(attuale, new AsyncCallback<Boolean>() {
                        @Override
                        public void onFailure(Throwable caught) {
                        }

                        @Override
                        public void onSuccess(Boolean result) {
                            if (result) {
                                messageLabel.setText("Scenario iniziale impostato con successo");
                                //attuale.addPrecedente("-1");
                                facciataSecondaria();
                            } else {
                                messageLabel.setText("Impossibile impostare lo scenario iniziale");
                            }
                        }
                    });
                } else {
                    messageLabel.setText("Selezionare uno scenario per impostarlo come iniziale");
                }
            }
        });

    
        settaSuccessivo.addClickHandler(new ClickHandler() {
            public void onClick(ClickEvent event) {
                int indexAttuale = listaScenari.getSelectedIndex();
                int indexCollegamento = menuScenariCollegamenti.getSelectedIndex();
                if (indexAttuale != -1 && indexCollegamento != -1) { // controllo che sia stato selezionato uno scenario
                    attuale = scenariStoria.get(indexAttuale);
                    Scenario scenarioDaCollegare = scenariStoria.get(indexCollegamento);
                    hALAServiceAsync.settaCollegamentoSuccessivo(attuale, scenarioDaCollegare,
                            new AsyncCallback<Boolean>() {
                                @Override
                                public void onFailure(Throwable caught) {
                                }

                                @Override
                                public void onSuccess(Boolean result) {
                                    if (result){
                                        messageLabel.setText("Collegamento successivo impostato con successo");
                                    }
                                    else{
                                        messageLabel.setText("Impossibile impostare il collegamento successivo");
                                    }
                                }
                            });
                } else {
                    messageLabel.setText("Selezionare uno scenario");
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
                            messageLabel.setText("Storia creata con successo");
                        } else {
                            messageLabel.setText("Impossibile creare la storia");
                        }
                    }
                });
            }
        });

        backButton.addClickHandler(new ClickHandler() {
            @Override
            public void onClick(ClickEvent event) {
                RootPanel.get("startTable").clear();
                RootPanel.get("startTable").add(new ScriviStoria()); // secondo me servira fare in modo che si torna
                                                                     // alla creazione scenari
            }
        });
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
        riempiLista(listaScenari);
        pagina.add(messageLabel);
        //listaScenari.setVisibleItemCount(scenariStoria.size());
        //menuScenari.setVisible(false);
        
    }

    private void facciataSecondaria() {
        pagina.remove(messageLabel);
        pagina.remove(ScenarioInizialePanel);
        pagina.add(ScenariDaCollegare);
        //pagina.remove(LscenarioIniziale);
        riempiLista(menuScenari);
        //menuScenari.setVisibleItemCount(scenariStoria.size());
        pagina.remove(backButton);
        pagina.add(CollegamentiPanel);
        riempiLista(menuScenariCollegamenti);
        pagina.add(messageLabel);
        pagina.add(terminaButton);
        pagina.add(backButton);
        
        
    }

    private void riempiLista(ListBox lb) {
        for (Scenario temp : scenariStoria) {
            lb.addItem(temp.getTestoScena());
        }
        lb.setSize("200px", "50px");
    }

}