package com.hala.mywebapp;

import com.google.gwt.core.client.GWT;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.ui.*;

public class ScriviStoria implements IsWidget {

    public HorizontalPanel mainPanel;
    public static final GreetingServiceAsync hALAServiceAsync = GWT.create(GreetingService.class);
    protected static String utenteAttivo = "";

    public ScriviStoria() {
       mainPanel = GWT.create(HorizontalPanel.class) ;
       initWidget();
    }

    private void initWidget() {
        Label storyLabel = new Label("Scrivi il titolo della storia:");
        TextBox storyField = new TextBox();
        Label scenarioInizialeLabel = new Label("Scrivi lo scenario iniziale:");
        TextBox scenarioInizialeField = new TextBox();
        Label primoScenarioLabel = new Label("Scrivi il primo scenario intermedio:");
        TextBox primoScenarioField = new TextBox();
        Button aggiungiIntermedio = new Button("aggiungi uno scenario intermedio");
        Label scenarioFinaleLabel = new Label("scrivi il primo scenario finale:");
        TextBox scenarioFinaleField = new TextBox();
        Button aggiungiFinale = new Button("aggiungi uno scenario finale");
        
        Label messageLabel = new Label();
        Button backButton = new Button("Back");

        VerticalPanel vp = GWT.create(VerticalPanel.class);
        VerticalPanel vpIniziale = GWT.create(VerticalPanel.class);
        VerticalPanel vpIntermedio = GWT.create(VerticalPanel.class);
        VerticalPanel vpFinale = GWT.create(VerticalPanel.class);
        vp.add(storyLabel);
        vp.add(storyField);
        vpIniziale.add(scenarioInizialeLabel);
        vpIniziale.add(scenarioInizialeField);
        vpIntermedio.add(primoScenarioLabel);
        vpIntermedio.add(primoScenarioField);
        vpIntermedio.add(aggiungiIntermedio);
        vpFinale.add(scenarioFinaleLabel);
        vpFinale.add(scenarioFinaleField);
        vpFinale.add(aggiungiFinale);
        vp.add(backButton);

        vp.setSpacing(30); //Spazio tra bottoni
        vpIniziale.setSpacing(30); //Spazio tra bottoni
        vpIntermedio.setSpacing(30); //Spazio tra bottoni
        vpFinale.setSpacing(30); //Spazio tra bottoni

        mainPanel.add(vp);
        mainPanel.add(vpIniziale);
        mainPanel.add(vpIntermedio);
        mainPanel.add(vpFinale);
        mainPanel.setSpacing(100); //Spazio tra i bottoni
        
		aggiungiIntermedio.addClickHandler(new ClickHandler() {
            public void onClick(ClickEvent event) {
               TextBox newTextBox = new TextBox();
                
                // Aggiungi la casella di testo al pannello radice della pagina
                vpIntermedio.add(newTextBox);
            }
        });

        aggiungiFinale.addClickHandler(new ClickHandler() {
            public void onClick(ClickEvent event) {
               TextBox newTextBox = new TextBox();
                
                // Aggiungi la casella di testo al pannello radice della pagina
                vpFinale.add(newTextBox);
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
        

    @Override
    public Widget asWidget() {
        return mainPanel;
     }
}
