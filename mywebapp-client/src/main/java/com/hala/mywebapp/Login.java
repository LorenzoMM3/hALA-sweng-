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

public class Login implements IsWidget {

    public VerticalPanel mainPanel;
    public static final GreetingServiceAsync hALAServiceAsync = GWT.create(GreetingService.class);
    protected static String utenteAttivo = "";

    public Login() {
       mainPanel = GWT.create(VerticalPanel.class) ;
       initWidget();
    }

    private void initWidget() {
        Label usernameLabel = new Label("Username:");
        TextBox usernameField = new TextBox();
        Label passwordLabel = new Label("Password:");
        TextBox passwordField = new TextBox();
        Button loginButton = new Button("Login");
        Label messageLabel = new Label();
        Button backButton = new Button("Back");
        loginButton.addStyleName("Login");

        VerticalPanel vp = GWT.create(VerticalPanel.class);
        vp.add(usernameLabel);
        vp.add(usernameField);
        vp.add(passwordLabel);
        vp.add(passwordField);
		vp.add(loginButton);
        vp.add(backButton);
		vp.add(messageLabel);
        mainPanel.add(vp);
        
		loginButton.addClickHandler(new ClickHandler() {
            public void onClick(ClickEvent event) {
                String username = usernameField.getText();
                String password = passwordField.getText();
                Utente utente = new Utente(username, password);
                hALAServiceAsync.logIn(utente, new AsyncCallback<Boolean>() {

                @Override
                public void onFailure(Throwable throwable) {
                    messageLabel.setText("Non è stato possibile effettuare l'operazione. Riprova."); 
                    GWT.log("Errore durante la chiamata asincrona al servizio remoto", throwable);
                }

                @Override
                public void onSuccess(Boolean verifica) {
                    if (verifica) {
                        utenteAttivo = username;
                        messageLabel.setText("Login effettuato con successo!");
                        RootPanel.get("startTable").clear();
                        RootPanel.get("startTable").add(new HomePage());
                    } else {
                        messageLabel.setText("Credenziali errate. Riprova.");
                    }
                }
            });
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
