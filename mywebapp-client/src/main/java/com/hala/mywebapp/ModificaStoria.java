package com.hala.mywebapp;

import java.util.ArrayList;

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
import com.google.gwt.user.client.ui.ListBox;
import com.google.gwt.user.client.ui.RootPanel;
import com.google.gwt.user.client.ui.TextBox;
import com.google.gwt.user.client.ui.Widget;

public class ModificaStoria extends Composite implements IsWidget{

    public static final GreetingServiceAsync hALAServiceAsync = GWT.create(GreetingService.class);
    private static final ModificaStoriaUiBinder uiBinder = GWT.create(ModificaStoriaUiBinder.class);
    ArrayList<Scenario> scenari;
    Scenario scenarioDaModificare;
    Scenario scenarioModificato;

    interface ModificaStoriaUiBinder extends UiBinder<Widget, ModificaStoria> {
    }

    @UiField 
    Label labelIniziale;

    @UiField
    Label labelSelezionaScenario;

    @UiField
    ListBox elencoScenari;

    @UiField
    Label labelModificaTesto;

    @UiField
    TextBox testoModificato;

    @UiField
    Button modificaButton;

    @UiField
    Label message;

    @UiField
    Button backButton;

    
    public ModificaStoria(Storia storiaDaModificare) {
        initWidget(uiBinder.createAndBindUi(this));
        settaGrafica();

        String nomeStoria = storiaDaModificare.getNome();
        labelIniziale.setText("Modifica la storia: " + nomeStoria);
        scenari = new ArrayList<Scenario>();

        hALAServiceAsync.ottieniScenariStoria(nomeStoria, new AsyncCallback<ArrayList<Scenario>>() {
            @Override
            public void onFailure(Throwable throwable) {
                GWT.log("Errore durante la chiamata asincrona al servizio remoto", throwable);
            }

            @Override
            public void onSuccess(ArrayList<Scenario> result) {
                scenari = result;
                for (Scenario s : scenari) {
                    scenari = result;
                    elencoScenari.addItem(s.getTestoScena());
                    
                }
            }
        });

        modificaButton.addClickHandler(new ClickHandler() {
            
            @Override
            public void onClick(ClickEvent event) {
                String nuovoTesto = testoModificato.getText();
                testoModificato.setText("");
                int index = elencoScenari.getSelectedIndex();
                scenarioDaModificare = scenari.get(index);
                scenarioModificato = scenarioDaModificare;
                scenarioModificato.setTestoScena(nuovoTesto);
                hALAServiceAsync.modificaScenario(nomeStoria, scenarioDaModificare, scenarioModificato, new AsyncCallback<Boolean>(){
                    @Override
                    public void onFailure(Throwable throwable) {
                        GWT.log("Errore durante la chiamata asincrona al servizio remoto", throwable);
                    }
                    @Override
                    public void onSuccess(Boolean result) {
                        if (result){
                            message.setText("Modifica avvenuta con successo");
                        }
                        else{
                            message.setText("Errore durante la modifica");
                        }
                    }
                });
            }
        });
    
        backButton.addClickHandler(new ClickHandler() {
            @Override
            public void onClick(ClickEvent event) {
                RootPanel.get("startTable").clear();
                RootPanel.get("startTable").add(new StorieCreateDaUtente(storiaDaModificare.getUtente().getUsername()));
            }
        });
    }

    private void settaGrafica() {
        labelIniziale.setStyleName("testi");
        labelSelezionaScenario.setStyleName("testi");
        labelModificaTesto.setStyleName("testi");
        modificaButton.setStyleName("lButton");
        backButton.setStyleName("lButton");
        message.setStyleName("messaggio");
        elencoScenari.setSize("200px", "50px");

    }

}
