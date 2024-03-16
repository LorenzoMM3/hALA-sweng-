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
    Button giocaButton;

    @UiField
    Label riprendiLabel;

    @UiField
    Button riprendiButton;

    @UiField
    Button backButton;
    
    public SalvataggiPartita(Storia s, Utente u) {
        initWidget(UiB.createAndBindUi(this));
        storia = s;
        utente = u;
        riprendiButton.setVisible(false);
        riprendiLabel.setText("");
        settaGrafica();

        giocaButton.addClickHandler(new ClickHandler() {
        
            @Override
            public void onClick(ClickEvent event) {
                RootPanel.get("startTable").clear();
                RootPanel.get("startTable").add(new GiocaStoria(storia, utente, true));
            } 
        });

        riprendiButton.addClickHandler(new ClickHandler() {
        
            @Override
            public void onClick(ClickEvent event) {
                    RootPanel.get("startTable").clear();
                    RootPanel.get("startTable").add(new GiocaStoria(storia, utente, false));
            } 
        });

        backButton.addClickHandler(new ClickHandler() {
            @Override
            public void onClick(ClickEvent event) {
                RootPanel.get("startTable").clear();
                RootPanel.get("startTable").add(new VisualizzaCatalogo());
            }
        });

    }

    private void settaGrafica() {
        salutoLabel.setText("Ciao " + utente.getUsername() + ", vuoi giocare a " + storia.getNome() + "?");
        salutoLabel.setStyleName("testi");
        giocaButton.setStyleName("lButton");
        backButton.setStyleName("lButton");
        riprendiButton.setStyleName("lButton");
        riprendiLabel.setStyleName("testi");

        hALAServiceAsync.datiPartita(storia.getNome(), utente.getUsername(), new AsyncCallback<Partita>() {
            @Override
            public void onFailure(Throwable caught) {
                System.out.println("Errore nel caricamento della partita");
            }

            @Override
            public void onSuccess(Partita result) {
                if (result != null){
                    riprendiLabel.setText("l'ultima volta hai lasciato la partita in sospeso allo scenario:\n" + result.getScenarioAttuale().getTestoScena());
                    riprendiButton.setVisible(true);
                    Scenario attuale = result.getScenarioAttuale();
                    String scenarioInizialeDellaStoria = storia.getScenarioIniziale().getTestoScena();
                }
                // Altrimenti non aggiunge nulla
            }

            
        });

    }

    /*private void riempiListBox(ListBox lb) {
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
                    //qua si fa già un controllo se lo scenario attuale non è uguale allo scenario iniziale si aggiunge l'item
                    if (!attuale.getTestoScena().equals(scenarioInizialeDellaStoria)){
                        lb.addItem(attuale.getTestoScena());
                    } 
                }
                // Altrimenti non aggiunge nulla
            }

            
        });
    }*/




}
