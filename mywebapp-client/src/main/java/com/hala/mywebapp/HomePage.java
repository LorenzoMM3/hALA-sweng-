package com.hala.mywebapp;

import com.google.gwt.core.client.GWT;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.RootPanel;
import com.google.gwt.user.client.ui.VerticalPanel;
import com.google.gwt.user.client.ui.Widget;

public class HomePage extends Composite{

    public VerticalPanel mainPanel;
    public static final GreetingServiceAsync hALAServiceAsync = GWT.create(GreetingService.class);
    private static final HomePageUiBinder uiBinder = GWT.create(HomePageUiBinder.class);

    interface HomePageUiBinder extends UiBinder<Widget, HomePage> {
    }

    @UiField
    Label benvenuto;

    @UiField
    VerticalPanel hpPanel;

    @UiField
    VerticalPanel bPanel;

    @UiField
    VerticalPanel tastiPanel;

    @UiField
    Button visualizzaButton;

    @UiField
    Button logoutButton;

    @UiField
    Button scriviButton;

    @UiField
    Button modificaButton;

    public HomePage(Utente utente) {
        initWidget(uiBinder.createAndBindUi(this));
        mainPanel = GWT.create(VerticalPanel.class);
        settaGrafica();
        benvenuto.setText("Ciao " + utente.getUsername() + ", cosa vuoi fare?");

        scriviButton.addClickHandler(new ClickHandler() {
            public void onClick(ClickEvent event) {
                RootPanel.get("startTable").clear();
                RootPanel.get("startTable").add(new ScriviStoria(utente));
            }
        });

        logoutButton.addClickHandler(new ClickHandler() {
            @Override
            public void onClick(ClickEvent event) {
                RootPanel.get("startTable").clear();
                RootPanel.get("startTable").add(new Logout(utente));
            }
        });

        modificaButton.addClickHandler(new ClickHandler() {
            @Override
            public void onClick(ClickEvent event) {
                RootPanel.get("startTable").clear();
                RootPanel.get("startTable").add(new StorieCreateDaUtente(utente));
            }
        });

        visualizzaButton.addClickHandler(new ClickHandler() {
            @Override
            public void onClick(ClickEvent event) {
                RootPanel.get("startTable").clear();
                RootPanel.get("startTable").add(new VisualizzaCatalogo(utente));
            }
        });

    }

    private void settaGrafica() {
        benvenuto.setStyleName("messaggios");
        logoutButton.setStyleName("lButton");
        scriviButton.setStyleName("lButton");
        modificaButton.setStyleName("lButton");
        visualizzaButton.setStyleName("lButton");
        bPanel.setStyleName("mPanel");
        tastiPanel.setStyleName("sPanel");
    }
}
