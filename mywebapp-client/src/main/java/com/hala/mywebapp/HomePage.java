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

public class HomePage implements IsWidget {

    public VerticalPanel mainPanel;
    public static final GreetingServiceAsync hALAServiceAsync = GWT.create(GreetingService.class);
    protected static String utenteAttivo = "";

    public HomePage() {
       mainPanel = GWT.create(VerticalPanel.class) ;
       initWidget();
    }

    private void initWidget() {

        /* Label welcomeLabel = new Label("Ciao " + Utente.getUsername() + "!");
        welcomeLabel.setStyleName("homeWelcomeLabel"); */
        
        Button visualizza = new Button("Visualizza storie");
        Button gioca = new Button("Gioca ad una storia");
        Button scrivi = new Button("Scrivi una storia");
        Button backButton = new Button("Back");
        Button logOut = new Button("LogOut");

        VerticalPanel vp = GWT.create(VerticalPanel.class);
        vp.add(visualizza);
        vp.add(gioca);
        vp.add(scrivi);
        vp.add(backButton);
		vp.add(logOut);
        mainPanel.add(vp);
        
		scrivi.addClickHandler(new ClickHandler() {
            public void onClick(ClickEvent event) {
                RootPanel.get("startTable").clear();
                RootPanel.get("startTable").add(new ScriviStoria());
            }
        });

        backButton.addClickHandler(new ClickHandler() {
            @Override
            public void onClick(ClickEvent event) {
                RootPanel.get("startTable").clear();
                RootPanel.get("startTable").add(new Starter());
            }
        });

    }
        

    @Override
    public Widget asWidget() {
        return mainPanel;
     }
}
