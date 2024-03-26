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
import com.google.gwt.user.client.ui.Widget;

public class Logout extends Composite implements IsWidget {

    public static final GreetingServiceAsync hALAServiceAsync = GWT.create(GreetingService.class);
    private static final LogoutUiBinder UiB = GWT.create(LogoutUiBinder.class);

    interface LogoutUiBinder extends UiBinder<Widget, Logout> {
    }

    @UiField
    Button sendButton;

    @UiField
    Button backButton;

    @UiField
    Label messageLabel;

    public Logout(Utente utente) {
        initWidget(UiB.createAndBindUi(this));
        settaGrafica();

        sendButton.addClickHandler(new ClickHandler() {
            public void onClick(ClickEvent event) {
                hALAServiceAsync.logOut(utente, new AsyncCallback<Boolean>() {

                    @Override
                    public void onFailure(Throwable throwable) {

                        messageLabel.setText("Non è stato possibile effettuare l'operazione. Riprova.");
                        GWT.log("Errore durante la chiamata asincrona al servizio remoto", throwable);
                    }

                    @Override
                    public void onSuccess(Boolean verifica) {
                        if (verifica) {
                            RootPanel.get("startTable").clear();
                            RootPanel.get("startTable").add(new Starter());
                        }
                    }
                });
            }
        });

        backButton.addClickHandler(new ClickHandler() {
            @Override
            public void onClick(ClickEvent event) {

                if (utente != null) {

                    RootPanel.get("startTable").clear();
                    RootPanel.get("startTable").add(new HomePage(utente));
                } else {
                    RootPanel.get("startTable").clear();
                    RootPanel.get("startTable").add(new Starter());
                }

            }
        });

    }

    private void settaGrafica() {
        sendButton.setStyleName("lButton");
        backButton.setStyleName("lButton");
        messageLabel.setStyleName("messaggioa");
    }

    @Override
    public Widget asWidget() {
        return this;
    }
}
