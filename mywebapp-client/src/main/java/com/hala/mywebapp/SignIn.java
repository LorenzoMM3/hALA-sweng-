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

public class SignIn extends Composite implements IsWidget {

    public static final GreetingServiceAsync hALAServiceAsync = GWT.create(GreetingService.class);
    private static final SignInUiBinder UiB = GWT.create(SignInUiBinder.class);

    interface SignInUiBinder extends UiBinder<Widget, SignIn> {
    }

    @UiField
    TextBox usernameField;

    @UiField
    VerticalPanel signinPanel;

    @UiField
    Label usernameLabel;

    @UiField
    Label passwordLabel;

    @UiField
    TextBox passwordField;

    @UiField
    Button sendButton;

    @UiField
    Button backButton;

    @UiField
    Label messageLabel;

    @UiField
    Button startButton;

    public SignIn() {
        initWidget(UiB.createAndBindUi(this));
        startButton.setVisible(false);
        settaGrafica();

        sendButton.addClickHandler(new ClickHandler() {
            public void onClick(ClickEvent event) {
                if (!(usernameField.getText().isEmpty()) && !(passwordField.getText().isEmpty())) {
                    String username = usernameField.getText();
                    String password = passwordField.getText();
                    Utente utente = new Utente(username, password);
                    hALAServiceAsync.signIn(utente, new AsyncCallback<Boolean>() {

                        @Override
                        public void onFailure(Throwable throwable) {
                            messageLabel.setStyleName("messaggioa");
                            messageLabel.setText("Non è stato possibile effettuare l'operazione. Riprova.");
                            GWT.log("Errore durante la chiamata asincrona al servizio remoto", throwable);
                        }

                        @Override
                        public void onSuccess(Boolean verifica) {
                            if (verifica) {
                                messageLabel.setStyleName("messaggios");
                                messageLabel.setText("Registrazione effettuata! Benvenuto " + username
                                        + "! Clicca sul tasto per effettuare il Login ed iniziare a giocare.");
                                startButton.setVisible(true);
                            } else {
                                messageLabel.setStyleName("messaggioa");
                                messageLabel.setText("Credenziali già in uso, cambia Username e riprova.");
                                startButton.setVisible(false);
                            }
                        }
                    });
                } else {
                    messageLabel.setStyleName("messaggioa");
                    messageLabel.setText("Forse manca qualcosa");
                }
            }

        });

        backButton.addClickHandler(new ClickHandler() {
            @Override
            public void onClick(ClickEvent event) {
                RootPanel.get("startTable").clear();
                RootPanel.get("startTable").add(new Starter());
            }
        });

        startButton.addClickHandler(new ClickHandler() {
            @Override
            public void onClick(ClickEvent event) {
                RootPanel.get("startTable").clear();
                RootPanel.get("startTable").add(new Login());
            }
        });
    }

    private void settaGrafica() {
        startButton.setStyleName("lButton");
        signinPanel.setStyleName("sPanel");
        backButton.setStyleName("lButton");
        sendButton.setStyleName("lButton");
        usernameLabel.setStyleName("testi");
        passwordLabel.setStyleName("testi");
    }

    @Override
    public Widget asWidget() {
        return this;
    }
}
