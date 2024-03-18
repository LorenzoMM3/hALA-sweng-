package com.hala.mywebapp;

import com.google.gwt.core.client.GWT;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.uibinder.client.UiHandler;
import com.google.gwt.user.client.ui.*;

public class Starter extends Composite implements IsWidget {

    private static final StarterUiBinder uiBinder = GWT.create(StarterUiBinder.class);

    interface StarterUiBinder extends UiBinder<Widget, Starter> {
    }

    @UiField
    Button loginButton;

    @UiField
    Button signinButton;

    @UiField
    Button visualizzaCatalogoButton;

    @UiField
    Panel totale;

    @UiField
    Panel descrizione;

    @UiField
    Panel bottoniPanel;

    @UiField
    Label desc1;
    @UiField
    Label desc2;
    @UiField
    Label desc3;
    @UiField
    Label desc4;

    public Starter() {
        initWidget(uiBinder.createAndBindUi(this));
        loginButton.setStyleName("sButton");
        signinButton.setStyleName("sButton");
        visualizzaCatalogoButton.setStyleName("sButton");
        desc1.setStyleName("testi");
        desc2.setStyleName("testi");
        desc3.setStyleName("testi");
        desc4.setStyleName("testi");

        signinButton.addClickHandler(new ClickHandler() {
            @Override
            public void onClick(ClickEvent event) {
                RootPanel.get("startTable").clear();
                RootPanel.get("startTable").add(new SignIn());
            }
        });

        loginButton.addClickHandler(new ClickHandler() {
            @Override
            public void onClick(ClickEvent event) {
                RootPanel.get("startTable").clear();
                RootPanel.get("startTable").add(new Login());
            }
        });

        visualizzaCatalogoButton.addClickHandler(new ClickHandler() {
            @Override
            public void onClick(ClickEvent event) {
                RootPanel.get("startTable").clear();
                RootPanel.get("startTable").add(new VisualizzaCatalogo());
            }
        });

    }

    @Override
    public Widget asWidget() {
        return this;
    }
}