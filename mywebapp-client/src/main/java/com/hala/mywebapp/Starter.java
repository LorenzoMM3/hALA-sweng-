package com.hala.mywebapp;

import com.google.gwt.core.client.GWT;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.resources.client.ClientBundle;
import com.google.gwt.resources.client.ImageResource;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.HasHorizontalAlignment;
import com.google.gwt.user.client.ui.HasVerticalAlignment;
import com.google.gwt.user.client.ui.HorizontalPanel;
import com.google.gwt.user.client.ui.Image;
import com.google.gwt.user.client.ui.IsWidget;
import com.google.gwt.user.client.ui.RootPanel;
import com.google.gwt.user.client.ui.VerticalPanel;
import com.google.gwt.user.client.ui.Widget;
import com.ibm.icu.text.ListFormatter.Style;


public class Starter implements IsWidget {

    private VerticalPanel mainPanel;

    public Starter() {
        mainPanel = GWT.create(VerticalPanel.class) ;
        initWidget();
    }

    private void initWidget() {
        Button signInButton = new Button("SignIn");
        Button loginButton = new Button("Login");
        Button visualizzaButton = new Button("Visualizza storie");
        Button logoutButton = new Button("LogOut");
    
        // Imposta il colore di sfondo e il colore del testo per i pulsanti
        signInButton.getElement().getStyle().setProperty("background", "#ADD8E6"); // Blu chiaro
        signInButton.getElement().getStyle().setColor("white");
        loginButton.getElement().getStyle().setProperty("background", "#ADD8E6"); // Blu chiaro
        loginButton.getElement().getStyle().setColor("white");
        visualizzaButton.getElement().getStyle().setProperty("background", "#ADD8E6"); // Blu chiaro
        visualizzaButton.getElement().getStyle().setColor("white");
        logoutButton.getElement().getStyle().setProperty("background", "#ADD8E6"); // Blu chiaro
        logoutButton.getElement().getStyle().setColor("white");

        // Imposta lo stile del font e le dimensioni del testo per i pulsanti
        signInButton.getElement().getStyle().setProperty("fontFamily", "Arial, sans-serif"); // Imposta il font su Arial
        signInButton.getElement().getStyle().setProperty("fontSize", "18px"); // Aumenta la dimensione del testo
        loginButton.getElement().getStyle().setProperty("fontFamily", "Arial, sans-serif");
        loginButton.getElement().getStyle().setProperty("fontSize", "18px");
        visualizzaButton.getElement().getStyle().setProperty("fontFamily", "Arial, sans-serif");
        visualizzaButton.getElement().getStyle().setProperty("fontSize", "18px");
        logoutButton.getElement().getStyle().setProperty("fontFamily", "Arial, sans-serif");
        logoutButton.getElement().getStyle().setProperty("fontSize", "18px");



        MyResources resources = GWT.create(MyResources.class);
        Image immStart1 = new Image(resources.immagineStart());
        immStart1.setSize("50%", "50%"); // Aumenta le dimensioni dell'immagine
    
        Image immStart2 = new Image(resources.immagineStart());
        immStart2.setSize("50%", "50%"); // Aumenta le dimensioni dell'immagine
    
        VerticalPanel buttonPanel = new VerticalPanel(); // Usa VerticalPanel per impilare i pulsanti verticalmente
        buttonPanel.setSpacing(10); // Add some spacing between buttons
        buttonPanel.setHorizontalAlignment(HasHorizontalAlignment.ALIGN_CENTER); // Allinea i pulsanti al centro orizzontalmente
    
        // Aumenta le dimensioni dei pulsanti
        signInButton.getElement().getStyle().setProperty("width", "200px");
        loginButton.getElement().getStyle().setProperty("width", "200px");
        visualizzaButton.getElement().getStyle().setProperty("width", "200px");
        logoutButton.getElement().getStyle().setProperty("width", "200px");
        signInButton.getElement().getStyle().setProperty("height", "60px");
        loginButton.getElement().getStyle().setProperty("height", "60px");
        visualizzaButton.getElement().getStyle().setProperty("height", "60px");
        logoutButton.getElement().getStyle().setProperty("height", "60px");
    
        buttonPanel.add(signInButton);
        buttonPanel.add(loginButton);
        buttonPanel.add(visualizzaButton);
        buttonPanel.add(logoutButton);
    
        // Allinea il pannello dei pulsanti al centro orizzontalmente
        buttonPanel.setWidth("100%");
        buttonPanel.getElement().getStyle().setProperty("margin", "auto");
    
        HorizontalPanel imagePanel = new HorizontalPanel();
        imagePanel.setWidth("100%");
        imagePanel.setHorizontalAlignment(HasHorizontalAlignment.ALIGN_CENTER);
    
        imagePanel.add(immStart1);
        imagePanel.add(buttonPanel);
        imagePanel.add(immStart2);
    
        mainPanel.add(imagePanel);
    
        // Imposta il colore di sfondo per il pannello principale
        mainPanel.getElement().getStyle().setBackgroundColor("#00008B"); // Blu scuro
    
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
