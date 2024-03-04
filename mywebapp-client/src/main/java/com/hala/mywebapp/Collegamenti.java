package com.hala.mywebapp;

import java.util.ArrayList;

import org.checkerframework.checker.guieffect.qual.UI;

import com.google.gwt.cell.client.TextCell;
import com.google.gwt.core.client.GWT;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.user.cellview.client.CellList;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.ui.*;
import java.util.Map;
import com.google.gwt.safehtml.shared.SafeHtmlBuilder;

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
    Button backButton;

    @UiField
    Label messageLabel;

    @UiField
    ListBox menuScenari;

    @UiField
    ListBox listaScenari;

    @UiField
    Label LscenarioIniziale;

    @UiField
    Label MenuLateraleScenari;

    @UiField
    HorizontalPanel pagina;

    @UiField
    VerticalPanel CollegamentiPanel;

    @UiField
    Label testoScenarioDaCollegare;

    @UiField
    Button settaSuccessivo;

    @UiField
    Button terminaButton;
    

    @UiField
    ListBox menuScenariCollegamenti;


    public Collegamenti(String nomeStoria, ArrayList<Scenario> scenari) {

        initWidget(uiBinder.createAndBindUi(this));
        scenariStoria = scenari;
        setScenaIniziale();

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
                    // Scenario attuale = scenariStoria.get(indice); //!!
                    attuale = scenariStoria.get(indexAttuale);
                    Scenario scenarioDaCollegare = scenariStoria.get(indexCollegamento);
                    hALAServiceAsync.settaCollegamentoSuccessivo(attuale, scenarioDaCollegare,
                            new AsyncCallback<Boolean>() {
                                @Override
                                public void onFailure(Throwable caught) {
                                    messageLabel.setText("C'è stato un errore");
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
        pagina.remove(CollegamentiPanel);
        riempiLista(listaScenari);
        listaScenari.setVisibleItemCount(scenariStoria.size());
        menuScenari.setVisible(false);
        // menuScenari.setVisibleItemCount(scenariStoria.size());
        // pagina.remove(ScenarioSucc);
        pagina.remove(terminaButton);
    }

    private void facciataSecondaria() {
        pagina.remove(ScenarioInizialePanel);
        pagina.remove(LscenarioIniziale);
        // pagina.remove(ScenarioSucc);
        riempiLista(menuScenari);
        menuScenari.setVisibleItemCount(scenariStoria.size());
        pagina.remove(backButton);
        pagina.add(CollegamentiPanel);
        // testoScenarioDaCollegare.setText(attuale.getTestoScena());
        // pagina.add(ScenarioSucc);
        pagina.add(backButton);
        // testoScenarioDaCollegare.setText(scenariStoria.get(indice).getTestoScena());
        riempiLista(menuScenariCollegamenti);
        // indice++;
        pagina.add(terminaButton);

    }

    private void riempiLista(ListBox lb) {
        for (Scenario temp : scenariStoria) {
            // if (!temp.getTestoScena().equalsIgnoreCase(attuale.getTestoScena()))
            lb.addItem(temp.getTestoScena());
        }

        lb.setSize("200px", "200px");
    }

}