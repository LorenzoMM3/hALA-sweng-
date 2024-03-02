package com.hala.mywebapp;

import java.util.ArrayList;
import java.util.Map;

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

    interface CollegamentiUiBinder extends UiBinder<Widget, Collegamenti> {
    }

    private static final CollegamentiUiBinder uiBinder = GWT.create(CollegamentiUiBinder.class);
    private ArrayList<String> scenariStoria;
    private String nomeStoria;
    private Map<String, Scenario> scenariNelSito;

    @UiField
    VerticalPanel Scenari;
    @UiField
    VerticalPanel ScenariCollegati;
    @UiField
    VerticalPanel ScenariDaCollegare;
    @UiField
    VerticalPanel vpCollegamenti;
    @UiField
    VerticalPanel ScenarioInizialePanel;
    @UiField
    VerticalPanel altriScenariPanel;

    @UiField
    Button buttonIniziale;
    @UiField
    Button buttonPrecedente;
    @UiField
    Button buttonSuccessivo;
    @UiField
    Button ScenarioSucc;
    @UiField
    Button backButton;

    @UiField
    Label scenarioIniziale;
    @UiField
    Label testoScenarioIniziale;
    @UiField
    Label altriScenari;
    @UiField
    Label testoScenario1;
    @UiField
    Label messageLabel;

    @UiField
    ListBox menuScenari;
    @UiField
    ListBox menuScenariSinistra;
    @UiField
    ListBox menuAltriScenari;
    @UiField
    ListBox nodo2;


    public Collegamenti(String nomeStoria, Map<String, Scenario> scenariNelSito) {

        initWidget(uiBinder.createAndBindUi(this));
        facciataIniziale();

        this.scenariNelSito = scenariNelSito;
        this.nomeStoria = nomeStoria;
        selezionaScenari();
        menuScenariSinistra.setVisibleItemCount(10);


        buttonIniziale.addClickHandler(new ClickHandler() {
            @Override
            public void onClick(ClickEvent event) {
                testoScenarioIniziale.setVisible(true);
                menuAltriScenari.setVisible(true);
                menuScenariSinistra.setVisible(true);
                buttonSuccessivo.setVisible(true);
                buttonIniziale.setVisible(false);
                
            }
        });

        ScenarioSucc.addClickHandler(new ClickHandler() {
            @Override
            public void onClick(ClickEvent event) {
                facciataSecondaria();
            }
        });

        backButton.addClickHandler(new ClickHandler() {
            @Override
            public void onClick(ClickEvent event) {
                RootPanel.get("startTable").clear();
                RootPanel.get("startTable").add(new ScriviStoria());
            }
        });

    }

    private void facciataIniziale() {
        // ScenariCollegati.setVisible(false);
        altriScenariPanel.setVisible(false);
        testoScenarioIniziale.setVisible(false);
        menuAltriScenari.setVisible(false);
        buttonSuccessivo.setVisible(false);
    }

    private void facciataSecondaria() {
        altriScenariPanel.setVisible(true);
        testoScenarioIniziale.setVisible(false);
        menuAltriScenari.setVisible(false);
        ScenarioInizialePanel.setVisible(false);
    }

    private void selezionaScenari() {
        for (Map.Entry<String, Scenario> entry : scenariNelSito.entrySet()) {
            if (entry.getValue().getNomeStoria() == nomeStoria) {
                scenariStoria.add(entry.getValue().getTestoScena());
            }
        }
    }

}
