package com.hala.mywebapp;

import java.util.ArrayList;

import org.checkerframework.checker.units.qual.m;

import com.google.common.util.concurrent.ListenableScheduledFuture;
import com.google.gwt.core.client.GWT;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.ui.*;
import com.shapesecurity.salvation2.Values.Hash;

public class StorieCreateDaUtente extends Composite implements IsWidget {

    public static final GreetingServiceAsync hALAServiceAsync = GWT.create(GreetingService.class);
    private static final StorieCreateDaUtenteUiBinder uiBinder = GWT.create(StorieCreateDaUtenteUiBinder.class);

    interface StorieCreateDaUtenteUiBinder extends UiBinder<Widget, StorieCreateDaUtente> {
    }
    
    private ArrayList<Storia> storieUtente;

    @UiField
    Label labelIniziale;

    @UiField
    ListBox elencoStorie;

    @UiField
    Button modificaButton;

    @UiField
    Button eliminaButton;

    @UiField
    Button backButton;

    @UiField
    Label messageLabel;

    public StorieCreateDaUtente(String utente) {

        initWidget(uiBinder.createAndBindUi(this));
        settaGrafica();
        storieUtente = new ArrayList<Storia>();

        hALAServiceAsync.ottieniStorie(new AsyncCallback<ArrayList<Storia>>() {
            @Override
            public void onFailure(Throwable throwable) {
                GWT.log("Errore durante la chiamata asincrona al servizio remoto", throwable);
            }

            @Override
            public void onSuccess(ArrayList<Storia> result) {

                for (Storia s : result) {
                    if (s.getUtente().getUsername().equals(utente)) {
                        storieUtente.add(s);
                        String nomeStoria = s.getNome();
                        elencoStorie.addItem(nomeStoria);
                    }
                }
                elencoStorie.setVisibleItemCount(storieUtente.size());
            }
        });

        modificaButton.addClickHandler(new ClickHandler() {
            @Override
            public void onClick(ClickEvent event) {
                String nomeStoria = elencoStorie.getSelectedItemText();
                if(nomeStoria != null){
                    Storia daModificare = new Storia();
                    for (Storia s : storieUtente) {
                        if (s.getNome().equals(nomeStoria)) {
                            daModificare = s;
                        }
                    }
                    if (daModificare != null) {
                        RootPanel.get("startTable").clear();
                        RootPanel.get("startTable").add(new ModificaStoria(daModificare));
                    }
                } else {
                    messageLabel.setText("Nessuna storia selezionata. Seleziona una Storia da cancellare.");
                }
            }
        });

        eliminaButton.addClickHandler(new ClickHandler() {
            @Override
            public void onClick(ClickEvent event) {
                String nomeStoria = elencoStorie.getSelectedItemText();
                hALAServiceAsync.eliminaStoria(nomeStoria, new AsyncCallback<Boolean>() {
                    @Override
                    public void onFailure(Throwable throwable) {
                        GWT.log("Errore durante la chiamata asincrona al servizio remoto", throwable);
                    }

                    @Override
                    public void onSuccess(Boolean verifica) {
                        if (verifica) {
                            RootPanel.get("startTable").clear();
                            RootPanel.get("startTable").add(new StorieCreateDaUtente(utente));
                        }
                        else {
                            messageLabel.setText("Non è stato possibile eliminare la storia. Riprova.");
                        }
                    }
                });
            }
        });

        backButton.addClickHandler(new ClickHandler() {
            @Override
            public void onClick(ClickEvent event) {
                hALAServiceAsync.ottieniUtenteAttuale(new AsyncCallback<Utente>() {
                    public void onFailure(Throwable caught) {
                        GWT.log("Errore durante la chiamata asincrona al servizio remoto", caught);
                     }

                    public void onSuccess(Utente utente) {
                        if (utente != null){
                            Utente utenteAttuale = utente;

                            RootPanel.get("startTable").clear();
                            RootPanel.get("startTable").add(new HomePage(utenteAttuale.getUsername()));
                        } else {
                            RootPanel.get("startTable").clear();
                            RootPanel.get("startTable").add(new Starter());
                        }
                        
                    }
                });
            }
        });
    }

    private void settaGrafica() {
        labelIniziale.setStyleName("testi");
        messageLabel.setStyleName("messaggio");
        modificaButton.setStyleName("lButton");
        eliminaButton.setStyleName("lButton");
        backButton.setStyleName("lButton");
        elencoStorie.setSize("200px", "50px");
        
    }
}