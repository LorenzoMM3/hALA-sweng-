package com.hala.mywebapp;


import com.google.gwt.core.client.GWT;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.ListBox;
import com.google.gwt.user.client.ui.RootPanel;
import com.google.gwt.user.client.ui.Widget;

public class SalvataggiPartita extends Composite{
    
    public static final GreetingServiceAsync hALAServiceAsync = GWT.create(GreetingService.class);
    private static final SalvataggiPartitaUiBinder UiB = GWT.create(SalvataggiPartitaUiBinder.class);
    Utente utente;
    Storia storia;
    Partita caricamento;

    interface SalvataggiPartitaUiBinder extends UiBinder<Widget, SalvataggiPartita> {
    }

    @UiField
    Label salutoLabel;

    @UiField
    ListBox salvataggiLB;

    @UiField
    Button giocaButton;
    
    public SalvataggiPartita(Storia s, Utente u) {
        initWidget(UiB.createAndBindUi(this));
        storia = s;
        utente = u;
        settaGrafica();

        giocaButton.addClickHandler(new ClickHandler() {
        
            @Override
            public void onClick(ClickEvent event) {
                int index = salvataggiLB.getSelectedIndex();
                if (index == 0){
                    RootPanel.get("startTable").clear();
                    RootPanel.get("startTable").add(new GiocaStoria(storia, utente, true));
                }
                else {
                    RootPanel.get("startTable").clear();
                    RootPanel.get("startTable").add(new GiocaStoria(storia, utente, false));
                }
            } 
        });

    }

    private void settaGrafica() {
        salutoLabel.setText("Ciao " + utente.getUsername() + ", vuoi caricare una partita salvata?");
        salutoLabel.setStyleName("testi");
        giocaButton.setStyleName("lButton");
        riempiListBox(salvataggiLB);
    }

    private void riempiListBox(ListBox lb) {
        hALAServiceAsync.caricaPartita(storia, utente, false, new AsyncCallback<Partita>() {
            @Override
            public void onFailure(Throwable caught) {
                System.out.println("Errore nel caricamento della partita");
            }

            @Override
            public void onSuccess(Partita result) {
                if (result != null){
                    Scenario attuale = result.getScenarioAttuale();
                    String scenarioInizialeDellaStoria = storia.getScenarioIniziale().getTestoScena();
                    if (!attuale.getTestoScena().equals(scenarioInizialeDellaStoria)){
                        lb.addItem(attuale.getTestoScena());
                    } 
                }
                // Altrimenti non aggiunge nulla
            }

            
        });
    }




}
