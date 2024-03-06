package com.hala.mywebapp;

import com.google.gwt.core.client.GWT;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.IsWidget;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.RootPanel;
import com.google.gwt.user.client.ui.TextBox;
import com.google.gwt.user.client.ui.VerticalPanel;
import com.google.gwt.user.client.ui.Widget;
import com.hala.mywebapp.SignIn.SignInUiBinder;

public class Login extends Composite implements IsWidget {

    public static final GreetingServiceAsync hALAServiceAsync = GWT.create(GreetingService.class);
    private static final LoginUiBinder UiB = GWT.create(LoginUiBinder.class);
    protected static String utenteAttivo = "";

    interface LoginUiBinder extends UiBinder<Widget, Login> {
    }

    @UiField
    TextBox usernameField;

    @UiField
    TextBox passwordField;

    @UiField
    Button backButton;

    @UiField
    Button loginButton;

    @UiField
    Label messageLabel;

    public Login() {
        initWidget(UiB.createAndBindUi(this));

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
                            RootPanel.get("startTable").add(new HomePage(username));
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
        return this;
    }

}
