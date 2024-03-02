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

public class Collegamenti extends Composite implements IsWidget {

    public static final GreetingServiceAsync hALAServiceAsync = GWT.create(GreetingService.class);

    interface CollegamentiUiBinder extends UiBinder<Widget, Collegamenti> {
    }

    private static final CollegamentiUiBinder uiBinder = GWT.create(CollegamentiUiBinder.class);
    private ArrayList<String> opzioniSceltaTemp;
    private String nomeStoriaTemp;

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
    ListBox menuAltriScenari;
    @UiField
    ListBox nodo2;

    public Collegamenti() {
        initWidget(uiBinder.createAndBindUi(this));
        facciataIniziale();

        buttonIniziale.addClickHandler(new ClickHandler() {
            @Override
            public void onClick(ClickEvent event) {
                testoScenarioIniziale.setVisible(true);
                menuAltriScenari.setVisible(true);
                buttonSuccessivo.setVisible(true);
            }
        });

        buttonSuccessivo.addClickHandler(new ClickHandler() {
            @Override
            public void onClick(ClickEvent event) {
                facciataSecondaria();
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

    private void facciataIniziale() {
        // ScenariCollegati.setVisible(false);
        altriScenariPanel.setVisible(false);
        testoScenarioIniziale.setVisible(false);
        menuAltriScenari.setVisible(false);
    }

    private void facciataSecondaria() {
        altriScenariPanel.setVisible(true);
        testoScenarioIniziale.setVisible(false);
        menuAltriScenari.setVisible(false);
        ScenarioInizialePanel.setVisible(false);
    }

}
