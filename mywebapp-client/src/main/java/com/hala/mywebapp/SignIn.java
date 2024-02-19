package com.hala.mywebapp;



import com.google.gwt.core.client.GWT;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.IsWidget;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.TextBox;
import com.google.gwt.user.client.ui.VerticalPanel;
import com.google.gwt.user.client.ui.Widget;

public class SignIn implements IsWidget {

    private VerticalPanel mainPanel;
//    private static final GreetingServiceAsync hALAServiceAsync = GWT.create(GreetingService.class);
    
    public SignIn() {
       mainPanel = GWT.create(VerticalPanel.class) ;
       initWidget();
    }

    private void initWidget() {
        Label usernameLabel = new Label("Username:");
        TextBox usernameField = new TextBox();
        Label passwordLabel = new Label("Password:");
        TextBox passwordField = new TextBox();
        Button sendButton = new Button("signIn");
        Label messageLabel = new Label();
        sendButton.addStyleName("SignIn");

        VerticalPanel vp = GWT.create(VerticalPanel.class);
        vp.add(usernameLabel);
        vp.add(usernameField);
        vp.add(passwordLabel);
        vp.add(passwordField);
		vp.add(sendButton);
		vp.add(messageLabel);
        mainPanel.add(vp);
        /* 
		sendButton.addClickHandler(new ClickHandler() {
            public void onClick(ClickEvent event) {
                String username = usernameField.getText();
                String password = passwordField.getText();
                hALAServiceAsync.signIn(username, password, new AsyncCallback<Boolean>() {
                @Override
                public void onFailure(Throwable throwable) {
                    messageLabel.setText("Non è stato possibile effettuare l'operazione. Riprova."); 
                }

                @Override
                public void onSuccess(Boolean verifica) {
                    if (verifica) {
                        messageLabel.setText("Registrazione effettuata! Benvenuto!");
                    } else
                        messageLabel.setText("Non è stato possibile effettuare l'operazione. Riprova.");
                }
            });
            }
        });
        */
    }

    @Override
    public Widget asWidget() {
        return mainPanel;
     }
    
}
