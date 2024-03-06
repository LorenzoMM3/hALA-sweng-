package com.hala.mywebapp;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import com.google.gwt.core.client.GWT;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.user.cellview.client.CellTable;
import com.google.gwt.user.cellview.client.ColumnSortEvent.AsyncHandler;
import com.google.gwt.user.cellview.client.ColumnSortList;
import com.google.gwt.user.cellview.client.TextColumn;
import com.google.gwt.user.client.Timer;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.IsWidget;
import com.google.gwt.user.client.ui.RootPanel;
import com.google.gwt.user.client.ui.Widget;
import com.google.gwt.view.client.AsyncDataProvider;
import com.google.gwt.view.client.HasData;
import com.google.gwt.view.client.Range;

public class VisualizzaCatalogo extends Composite {

    // Servizio asincrono GWT per le chiamate RPC
    public static final GreetingServiceAsync hALAServiceAsync = GWT.create(GreetingService.class);

    // Interfaccia di UiBinder per associare la parte grafica del widget
    private static final VisualizzaUiBinder UiB = GWT.create(VisualizzaUiBinder.class);

    // Lista di tutte le storie da visualizzare
    private ArrayList<Storia> tutteLeStorie;

    // Tabella per visualizzare le storie
    //private CellTable<Storia> tabella;

    @UiField
    CellTable<Storia> tabella;

    @UiField
    Button backButton;

    // Interfaccia di UiBinder
    interface VisualizzaUiBinder extends UiBinder<Widget, VisualizzaCatalogo> {
    }

    // Costruttore del widget
    public VisualizzaCatalogo() {
        // Inizializza il widget associando la parte grafica
        initWidget(UiB.createAndBindUi(this));

        // Inizializza la lista delle storie
        tutteLeStorie = new ArrayList<Storia>();

        // Inizializza la tabella per visualizzare le storie
        //tabella = new CellTable<Storia>();

        // Colonna per il nome della storia
        TextColumn<Storia> nomeStoria = new TextColumn<Storia>() {
            @Override
            public String getValue(Storia storia) {
                return storia.getNome();
            }
        };
        nomeStoria.setSortable(true);

        // Colonna per il creatore della storia
        TextColumn<Storia> creatoreStoria = new TextColumn<Storia>() {
            @Override
            public String getValue(Storia storia) {
                return storia.getUtente().getUsername();
            }
        };

        
        // Aggiunta delle colonne alla tabella
        tabella.addColumn(nomeStoria, "Nome Storia");
        tabella.addColumn(creatoreStoria, "Autore Storia");

        // Chiamata asincrona per ottenere la lista delle storie
        hALAServiceAsync.ottieniStorie(new AsyncCallback<ArrayList<Storia>>() {
            @Override
            public void onFailure(Throwable throwable) {
                // Gestione degli errori in caso di fallimento della chiamata
                GWT.log("Errore durante la chiamata asincrona al servizio remoto", throwable);
            }

            @Override
            public void onSuccess(ArrayList<Storia> result) {
                // In caso di successo, popola la lista di storie e aggiorna la tabella
                tutteLeStorie = result;
                tabella.setRowCount(tutteLeStorie.size(), true);
                tabella.setVisibleRange(0, tutteLeStorie.size());
                AsyncDataProvider<Storia> provider = new AsyncDataProvider<Storia>() {

                    @Override
                    protected void onRangeChanged(HasData<Storia> display) {
                        final Range range = display.getVisibleRange();
                        final ColumnSortList sortList = tabella.getColumnSortList();

                        new Timer() {
                            @Override
                            public void run() {
                                int start = range.getStart();
                                int end = start + range.getLength();

                                // Ordina la lista di storie in base alla colonna selezionata
                                Collections.sort(tutteLeStorie, new Comparator<Storia>() {
                                    public int compare(Storia o1, Storia o2) {
                                        if (o1 == o2) {
                                            return 0;
                                        }

                                        // Confronta le colonne dei nomi
                                        int diff = -1;
                                        if (o1 != null) {
                                            diff = (o2 != null) ? o1.getNome().compareTo(o2.getNome()) : 1;
                                        }
                                        return sortList.get(0).isAscending() ? diff : -diff;
                                    }
                                });

                                // Estrae i dati nella portata della tabella
                                List<Storia> dataInRange = tutteLeStorie.subList(start, end);

                                // Aggiorna la tabella con i dati estratti
                                tabella.setRowData(start, dataInRange);
                            }
                        }.schedule(2000);
                    }
                };

                // Collega il provider alla tabella
                provider.addDataDisplay(tabella);

                // Aggiunge un gestore per l'ordinamento delle colonne
                AsyncHandler columnSortHandler = new AsyncHandler(tabella);
                tabella.addColumnSortHandler(columnSortHandler);

                // Imposta l'ordinamento predefinito per la colonna del nome
                tabella.getColumnSortList().push(nomeStoria);

                // Aggiunge la tabella al pannello principale
                RootPanel.get().add(tabella);
            }
        });
    
    
        backButton.addClickHandler(new ClickHandler() {
            @Override
            public void onClick(ClickEvent event) {
                RootPanel.get("startTable").clear();
                RootPanel.get("startTable").add(new Starter());
                RootPanel.get().clear();
            }
        });
    }

    @Override
    public Widget asWidget() {
        return this;
    }

}
