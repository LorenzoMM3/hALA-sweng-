package com.hala.mywebapp;

import com.google.gwt.core.client.GWT;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.resources.client.ClientBundle;
import com.google.gwt.resources.client.ImageResource;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.HorizontalPanel;
import com.google.gwt.user.client.ui.Image;
import com.google.gwt.user.client.ui.IsWidget;
import com.google.gwt.user.client.ui.RootPanel;
import com.google.gwt.user.client.ui.VerticalPanel;
import com.google.gwt.user.client.ui.Widget;

public class Starter implements IsWidget {

    private VerticalPanel mainPanel;

    public Starter() {
        mainPanel = GWT.create(VerticalPanel.class) ;
        initWidget();
    }

    private void initWidget() {
        Button signInButton = new Button("SignIn");
        Button loginButton = new Button("Login");
        Button homePageButton = new Button("HomePage");
        Button logoutButton = new Button("LogOut");

        MyResources resources = GWT.create(MyResources.class);
        Image immStart = new Image(resources.immagineStart());
        immStart.setSize("10%", "10%");

        HorizontalPanel buttonPanel = GWT.create(HorizontalPanel.class);
        buttonPanel.add(signInButton);
        buttonPanel.add(loginButton);
        buttonPanel.add(homePageButton);
        buttonPanel.add(logoutButton);

        mainPanel.add(buttonPanel);
        mainPanel.add(immStart);

        signInButton.addClickHandler(new ClickHandler() {
            public void onClick(ClickEvent event) {
                RootPanel.get("startTable").clear();
                RootPanel.get("startTable").add(new SignIn());
            }
        });

        loginButton.addClickHandler(new ClickHandler() {
            public void onClick(ClickEvent event) {
                RootPanel.get("startTable").clear();
                RootPanel.get("startTable").add(new Login());
            }
        });

        logoutButton.addClickHandler(new ClickHandler() {
            public void onClick(ClickEvent event) {
                RootPanel.get("startTable").clear();
                RootPanel.get("startTable").add(new Logout());
            }
        });

    }

    
    @Override
    public Widget asWidget() {
        return mainPanel;
    }

    interface MyResources extends ClientBundle {
        @Source("pictures/immagineStart.jpg")
        ImageResource immagineStart();
    }


}
