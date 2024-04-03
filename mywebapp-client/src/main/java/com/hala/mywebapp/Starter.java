package com.hala.mywebapp;

import com.google.gwt.core.client.GWT;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.Panel;
import com.google.gwt.user.client.ui.RootPanel;
import com.google.gwt.user.client.ui.Widget;

public class Starter extends Composite {

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
    Label desc1;
    @UiField
    Label desc2;

    @UiField
    Panel bottoniPanel;

    @UiField
    Panel bottoncini;

    public Starter() {
        initWidget(uiBinder.createAndBindUi(this));
        settaGrafica();

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
                RootPanel.get("startTable").add(new VisualizzaCatalogo(null));
            }
        });

    }

    private void settaGrafica() {
        loginButton.setStyleName("sButton");
        signinButton.setStyleName("sButton");
        visualizzaCatalogoButton.setStyleName("sButton");
        desc1.setStyleName("testi2");
        desc2.setStyleName("testi3");
        bottoniPanel.setStyleName("centramento");
        bottoncini.setStyleName("centramento2");
    }

    @Override
    public Widget asWidget() {
        return this;
    }
}