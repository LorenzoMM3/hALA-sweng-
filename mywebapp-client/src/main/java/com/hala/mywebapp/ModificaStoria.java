package com.hala.mywebapp;

import java.util.ArrayList;

import org.checkerframework.checker.guieffect.qual.UI;

import com.google.gwt.core.client.GWT;
import com.google.gwt.event.dom.client.ChangeEvent;
import com.google.gwt.event.dom.client.ChangeHandler;
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
    Label labelInformazioniScenario;

    @UiField
    ListBox informazioniScenario;

    @UiField
    Label labelModificaTesto;

    @UiField
    TextBox testoModificato;

    @UiField
    Label labelModificaDomanda;

    @UiField
    TextBox domandaModificata;

    @UiField
    Label labelModificaRisposta;

    @UiField
    TextBox rispostaModificata;


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
                settaElencoPrimoElemento();

            }
        });

        elencoScenari.addChangeHandler(new ChangeHandler() {

            @Override
            public void onChange(ChangeEvent event) {
                informazioniScenario.clear();
                int index = elencoScenari.getSelectedIndex();
                Scenario scenario = scenari.get(index);
                if (scenario.getTipologia().toString().equalsIgnoreCase("ASCELTA")){
                    mostraPerScelta();
                    informazioniScenario.addItem("Tipo: Scenario a scelta");
                    scenario = (ScenarioAScelta) scenario;
                    String testo = scenario.getTestoScena();
                    informazioniScenario.addItem("Testo scenario: " + testo);
                    String domanda = scenario.getDomandaCambioScenario();
                    informazioniScenario.addItem("Domanda cambio scenario: " + domanda);
                    informazioniScenario.addItem("Scelte disponibili:\n");
                    
                    int n = 1;
                    for (String s : ((ScenarioAScelta) scenario).getOpzioniScelta().keySet()){
                        informazioniScenario.addItem(n + " - " + s);
                        n++;
                    }

                } else if (scenario.getTipologia().toString().equalsIgnoreCase("INDOVINELLO")){
                    mostraPerIndovinello();
                    informazioniScenario.addItem("Tipo: Scenario con indovinello");
                    scenario = (ScenarioIndovinello) scenario;
                    String testo = scenario.getTestoScena();
                    informazioniScenario.addItem("Testo scenario: " + testo);
                    String indovinello = scenario.getDomandaCambioScenario();
                    informazioniScenario.addItem("Indovinello: " + indovinello);
                    String rispostaCorretta = ((ScenarioIndovinello) scenario).getRispostaIndovinello();
                    informazioniScenario.addItem("Risposta corretta: " + rispostaCorretta);                    
                } else {
                    nascondiPerFinale();

                    informazioniScenario.addItem("Tipo: Scenario finale");
                    String testo = scenario.getTestoScena();
                    informazioniScenario.addItem("Testo scenario: " + testo);
                }

                int size = informazioniScenario.getItemCount();
                informazioniScenario.setVisibleItemCount(size);
            }

        });
        

        modificaButton.addClickHandler(new ClickHandler() {
            
            @Override
            public void onClick(ClickEvent event) {
                String nuovoTesto = testoModificato.getText();
                testoModificato.setText("");

                String nuovaDomanda = domandaModificata.getText();
                domandaModificata.setText("");

                
                int index = elencoScenari.getSelectedIndex();
                scenarioDaModificare = scenari.get(index);
                scenarioModificato = scenarioDaModificare;
                if (nuovoTesto.trim().length() > 0){
                    scenarioModificato.setTestoScena(nuovoTesto);
                }

                if (scenarioDaModificare.getTipologia().toString().equalsIgnoreCase("ASCELTA")){
                    if (nuovaDomanda.trim().length() > 0){
                        ((ScenarioAScelta) scenarioModificato).setDomandaCambioScenario(nuovaDomanda);
                    }
                } else if (scenarioDaModificare.getTipologia().toString().equalsIgnoreCase("INDOVINELLO")){
                    if (nuovaDomanda.trim().length() > 0){
                        ((ScenarioIndovinello) scenarioModificato).setDomandaIndovinello(nuovaDomanda);
                        message.setText("Modifica di avvenuta con successo");
                    }
                    String nuovaRipostaCorretta = rispostaModificata.getText();
                    rispostaModificata.setText("");
                    if (nuovaRipostaCorretta.trim().length() > 0){
                        ((ScenarioIndovinello) scenarioModificato).setRispostaIndovinello(nuovaRipostaCorretta);
                        message.setText("Modifica rc avvenuta con successo");
                    }
                } else {
                    message.setText("errore");

                }


                hALAServiceAsync.modificaScenario(nomeStoria, scenarioDaModificare, scenarioModificato, new AsyncCallback<Boolean>(){
                    @Override
                    public void onFailure(Throwable throwable) {
                        GWT.log("Errore durante la chiamata asincrona al servizio remoto", throwable);
                    }
                    @Override
                    public void onSuccess(Boolean result) {
                        if (result){
                            //message.setText("Modifica avvenuta con successo");
                        }
                        else{
                            //message.setText("Errore durante la modifica");
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
        elencoScenari.clear();
        informazioniScenario.clear();
        labelIniziale.setStyleName("testi");
        labelSelezionaScenario.setStyleName("testi");
        informazioniScenario.setStyleName("testi");
        labelInformazioniScenario.setStyleName("testi");
        labelModificaTesto.setStyleName("testi");
        labelModificaDomanda.setStyleName("testi");
        labelModificaRisposta.setStyleName("testi");
        modificaButton.setStyleName("lButton");
        backButton.setStyleName("lButton");
        message.setStyleName("messaggio");
        elencoScenari.setSize("200px", "50px");
        informazioniScenario.setSize("400px", "50px");
        elencoScenari.setSelectedIndex(0);
    }

    private void settaElencoPrimoElemento(){
        informazioniScenario.clear();
        int index = elencoScenari.getSelectedIndex();
        Scenario scenario = scenari.get(index);
        if (scenario.getTipologia().toString().equalsIgnoreCase("ASCELTA")){
            mostraPerScelta();
            informazioniScenario.addItem("Tipo: Scenario a scelta");
            scenario = (ScenarioAScelta) scenario;
            String testo = scenario.getTestoScena();
            informazioniScenario.addItem("Testo scenario: " + testo);
            String domanda = scenario.getDomandaCambioScenario();
            informazioniScenario.addItem("Domanda cambio scenario: " + domanda);
            informazioniScenario.addItem("Scelte disponibili:\n");
            
            int n = 1;
            for (String s : ((ScenarioAScelta) scenario).getOpzioniScelta().keySet()){
                informazioniScenario.addItem(n + " - " + s);
                n++;
            }

        } else if (scenario.getTipologia().toString().equalsIgnoreCase("INDOVINELLO")){
            mostraPerIndovinello();
            informazioniScenario.addItem("Tipo: Scenario con indovinello");
            scenario = (ScenarioIndovinello) scenario;
            String testo = scenario.getTestoScena();
            informazioniScenario.addItem("Testo scenario: " + testo);
            String indovinello = ((ScenarioIndovinello)scenario).getDomandaIndovinello();
            informazioniScenario.addItem("Indovinello: " + indovinello);
            String rispostaCorretta = ((ScenarioIndovinello) scenario).getRispostaIndovinello();
            informazioniScenario.addItem("Risposta corretta: " + rispostaCorretta);                    
        } else {
            nascondiPerFinale();

            informazioniScenario.addItem("Tipo: Scenario finale");
            String testo = scenario.getTestoScena();
            informazioniScenario.addItem("Testo scenario: " + testo);
        }

        int size = informazioniScenario.getItemCount();
        informazioniScenario.setVisibleItemCount(size);
    
    }

    private void mostraPerScelta(){
        labelModificaDomanda.setText("Inserisci nuova domanda per il cambio di scenario:");
        domandaModificata.setVisible(true);
        labelModificaRisposta.setVisible(false);
        rispostaModificata.setVisible(false);
    }

    private void mostraPerIndovinello(){
        labelModificaDomanda.setText("Inserisci nuovo indovinello per il cambio di scenario:");
        domandaModificata.setVisible(true);
        labelModificaRisposta.setVisible(true);
        rispostaModificata.setVisible(true);
    }

    private void nascondiPerFinale(){
        labelModificaDomanda.setText("");
        domandaModificata.setVisible(false);
        labelModificaRisposta.setVisible(false);
        rispostaModificata.setVisible(false);
    }
}
