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
    private String nomeStoria;

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
    Label scenarioIniziale;
    
    @UiField
    Label MenuLateraleScenari;

    @UiField
    HorizontalPanel pagina;

    @UiField
    VerticalPanel CollegamentiPanel;

    @UiField
    Label testoScenarioDaCollegare;

    @UiField
    ListBox scenariConCuiCollegare;

    @UiField
    Button settaPrecedente;

    @UiField
    Button settaSuccessivo;

    @UiField
    Button ScenarioSucc;

    public Collegamenti(String nomeStoria, ArrayList<Scenario> scenari) {
        initWidget(uiBinder.createAndBindUi(this));
        scenariStoria = scenari;
        setScenaIniziale();

        buttonSettaScenarioIniziale.addClickHandler(new ClickHandler() {
            @Override
            public void onClick(ClickEvent event) {
                int index = listaScenari.getSelectedIndex();
                if (index != -1) {  // controllo che sia stato selezionato uno scenario
                    Scenario scenarioIniziale = scenariStoria.get(index);
                    hALAServiceAsync.settaScenarioIniziale(nomeStoria, scenarioIniziale, new AsyncCallback<Boolean>() {
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
        

        backButton.addClickHandler(new ClickHandler() {
            @Override
            public void onClick(ClickEvent event) {
                RootPanel.get("startTable").clear();
                RootPanel.get("startTable").add(new ScriviStoria());    // secondo me servira fare in modo che si torna alla creazione scenari
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
        riempiLista(menuScenari);
        menuScenari.setVisibleItemCount(scenariStoria.size());
    }

    private void facciataSecondaria() {
        messageLabel.setText("");
        pagina.remove(ScenarioInizialePanel);
        pagina.remove(ScenarioSucc);
        pagina.remove(backButton);
        pagina.add(CollegamentiPanel);
        pagina.add(ScenarioSucc);
        pagina.add(backButton);
    }

    private void riempiLista(ListBox lb) {
        for (Scenario temp : scenariStoria) {
            lb.addItem(temp.getTestoScena());
        }
        lb.setSize("200px", "200px");
    }

}