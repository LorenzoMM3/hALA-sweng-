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

public class Logout implements IsWidget {

    public VerticalPanel mainPanel;
    public static final GreetingServiceAsync hALAServiceAsync = GWT.create(GreetingService.class);
    
    public Logout() {
       mainPanel = GWT.create(VerticalPanel.class) ;
       initWidget();
    }

    private void initWidget() {
        Label domandaLabel = new Label("Sei sicuro di voler uscire?");
        Button yesButton = new Button("Si");
        Button noButton = new Button("No");
        Label messageLabel = new Label();
        Button backButton = new Button("Back");

        VerticalPanel vp = GWT.create(VerticalPanel.class);
        vp.add(domandaLabel);
        vp.add(yesButton);
        vp.add(noButton);
        vp.add(messageLabel);
        vp.add(backButton);
        mainPanel.add(vp);
        
		yesButton.addClickHandler(new ClickHandler() {
            public void onClick(ClickEvent event) {
                String username = Login.utenteAttivo;
                hALAServiceAsync.logOut(username, new AsyncCallback<Boolean>() {
        
                    @Override
                    public void onFailure(Throwable throwable) {
                        messageLabel.setText("Non è stato possibile effettuare l'operazione. Riprova."); 
                        GWT.log("Errore durante la chiamata asincrona al servizio remoto", throwable);
                    }
        
                    @Override
                    public void onSuccess(Boolean verifica) {
                        if (verifica) {
                            messageLabel.setText("Logout effettuato con successo!");
                        } else {
                            messageLabel.setText("Credenziali errate. Riprova.");
                        }
                    }
                });
            }
        });
        

        noButton.addClickHandler(new ClickHandler() {
            public void onClick(ClickEvent event) {
                RootPanel.get("startTable").clear();
                RootPanel.get("startTable").add(new Starter());
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
