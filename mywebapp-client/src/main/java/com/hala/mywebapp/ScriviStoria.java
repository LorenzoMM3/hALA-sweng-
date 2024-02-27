package com.hala.mywebapp;

import com.google.gwt.core.client.GWT;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.IsWidget;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.RootPanel;
import com.google.gwt.user.client.ui.TextBox;
import com.google.gwt.user.client.ui.VerticalPanel;
import com.google.gwt.user.client.ui.Widget;

public class ScriviStoria implements IsWidget {

    public VerticalPanel mainPanel;
    public static final GreetingServiceAsync hALAServiceAsync = GWT.create(GreetingService.class);
    protected static String utenteAttivo = "";

    public ScriviStoria() {
       mainPanel = GWT.create(VerticalPanel.class) ;
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
        vp.add(storyLabel);
        vp.add(storyField);
        vp.add(scenarioInizialeLabel);
        vp.add(scenarioInizialeField);
		vp.add(primoScenarioLabel);
        vp.add(primoScenarioField);
        vp.add(aggiungiIntermedio);
        vp.add(scenarioFinaleLabel);
        vp.add(scenarioFinaleField);
        vp.add(aggiungiFinale);
        vp.add(backButton);
        mainPanel.add(vp);
        
		aggiungiIntermedio.addClickHandler(new ClickHandler() {
            public void onClick(ClickEvent event) {
               TextBox newTextBox = new TextBox();
                
                // Aggiungi la casella di testo al pannello radice della pagina
                vp.add(newTextBox);
            }
        });

        aggiungiFinale.addClickHandler(new ClickHandler() {
            public void onClick(ClickEvent event) {
               TextBox newTextBox = new TextBox();
                
                // Aggiungi la casella di testo al pannello radice della pagina
                vp.add(newTextBox);
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
