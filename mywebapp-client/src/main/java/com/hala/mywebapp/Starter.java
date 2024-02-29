package com.hala.mywebapp;

import com.google.gwt.core.client.GWT;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.uibinder.client.UiHandler;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.IsWidget;
import com.google.gwt.user.client.ui.RootPanel;
import com.google.gwt.user.client.ui.Widget;
import com.google.gwt.user.client.ui.*;

public class Starter extends Composite implements IsWidget {

    private static final StarterUiBinder uiBinder = GWT.create(StarterUiBinder.class);

    interface StarterUiBinder extends UiBinder<Widget, Starter> {}

    @UiField
    Button loginButton;

    @UiField
    Button signinButton;

    @UiField
    Button logoutButton;

    @UiField
    Button visualizzaStoriaButton;

    public Starter() {
        initWidget(uiBinder.createAndBindUi(this));
        //loginButton.getElement().getStyle().setColor("red");

    }

    @UiHandler("signinButton")
    void onClickSignin(ClickEvent event) {
        RootPanel.get("startTable").clear();
        RootPanel.get("startTable").add(new SignIn());
    }

    @UiHandler("loginButton")
    public void onClickLogin(ClickEvent event) {
        RootPanel.get("startTable").clear();
        RootPanel.get("startTable").add(new Login());
    }

    @UiHandler("logoutButton")
    public void onClickLogout(ClickEvent event) {
        RootPanel.get("startTable").clear();
        RootPanel.get("startTable").add(new Logout());
    }


    @Override
    public Widget asWidget() {
        return this;
    }
}