package com.hala.mywebapp;

import java.util.ArrayList;

import com.google.gwt.core.client.GWT;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.ui.*;
import com.google.gwt.core.client.GWT;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.hala.mywebapp.Collegamenti.CollegamentiUiBinder;

public class HomePage extends Composite implements IsWidget {

    public VerticalPanel mainPanel;
    public static final GreetingServiceAsync hALAServiceAsync = GWT.create(GreetingService.class);
    private static final HomePageUiBinder uiBinder = GWT.create(HomePageUiBinder.class);

    interface HomePageUiBinder extends UiBinder<Widget, HomePage> {
    }

    @UiField
    Label benvenuto;

    @UiField
    Button giocaButton;

    @UiField
    Button visualizzaButton;

    @UiField
    Button logoutButton;

    @UiField
    Button scriviButton;

    @UiField
    Button backButton;

    public HomePage(String utente) {
        initWidget(uiBinder.createAndBindUi(this));
        mainPanel = GWT.create(VerticalPanel.class);
        benvenuto.setText("ciao " + utente);

        scriviButton.addClickHandler(new ClickHandler() {
            public void onClick(ClickEvent event) {
                RootPanel.get("startTable").clear();
                RootPanel.get("startTable").add(new ScriviStoria());
            }
        });

        logoutButton.addClickHandler(new ClickHandler() {
            @Override
            public void onClick(ClickEvent event) {
                RootPanel.get("startTable").clear();
                RootPanel.get("startTable").add(new Logout());
            }
        });

        visualizzaButton.addClickHandler(new ClickHandler() {
            @Override
            public void onClick(ClickEvent event) {
                RootPanel.get("startTable").clear();
                RootPanel.get("startTable").add(new VisualizzaCatalogo());
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
}
