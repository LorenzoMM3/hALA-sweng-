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
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.ListBox;
import com.google.gwt.user.client.ui.RootPanel;
import com.google.gwt.user.client.ui.TextBox;
import com.google.gwt.user.client.ui.Widget;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.view.client.AsyncDataProvider;
import com.google.gwt.view.client.CellPreviewEvent;
import com.google.gwt.view.client.HasData;
import com.google.gwt.view.client.Range;

public class VisualizzaCatalogo extends Composite {

    public static final GreetingServiceAsync hALAServiceAsync = GWT.create(GreetingService.class);

    private static final VisualizzaUiBinder UiB = GWT.create(VisualizzaUiBinder.class);

    private ArrayList<Storia> tutteLeStorie;

    private Storia storiaSelezionata;

    private List<Storia> risultatiRicerca;

    @UiField
    CellTable<Storia> tabella;

    @UiField
    Button backButton;

    @UiField
    TextBox nomeRicerca;

    @UiField
    Label messageLabel;

    @UiField
    Button ricerca;

    @UiField
    Button mostraTutti;

    @UiField
    ListBox filtroListBox;

    @UiField
    Button giocaButton;

    interface VisualizzaUiBinder extends UiBinder<Widget, VisualizzaCatalogo> {
    }

    public VisualizzaCatalogo(Utente utente) {
        initWidget(UiB.createAndBindUi(this));
        settaGrafica();
        storiaSelezionata = new Storia();
        risultatiRicerca = new ArrayList<Storia>();
        tutteLeStorie = new ArrayList<Storia>();
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
        creatoreStoria.setSortable(true);

        TextColumn<Storia> numeroScenariStoria = new TextColumn<Storia>() {
            @Override
            public String getValue(Storia storia) {
                return storia.getNumeroScenari();
            }
        };
        numeroScenariStoria.setSortable(true);

        TextColumn<Storia> scenarioIniziale = new TextColumn<Storia>() {
            @Override
            public String getValue(Storia storia) {
                // Restituisci solo una parte del testo, ad esempio i primi 50 caratteri
                String testoCompleto = storia.getScenarioIniziale().getTestoScena();
                return testoCompleto.length() > 50 ? testoCompleto.substring(0, 50) + "..." : testoCompleto;
            }
        };

        // Aggiunta delle colonne alla tabella
        tabella.addColumn(nomeStoria, "Nome Storia");
        tabella.addColumn(creatoreStoria, "Autore Storia");
        tabella.addColumn(numeroScenariStoria, "Numero Scenari");
        tabella.addColumn(scenarioIniziale, "Scenario Iniziale");

        // Chiamata asincrona per ottenere la lista delle storie
        hALAServiceAsync.ottieniStorie(new AsyncCallback<ArrayList<Storia>>() {
            @Override
            public void onFailure(Throwable throwable) {
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

                                // Seleziona la colonna per l'ordinamento
                                Comparator<Storia> comparator = null;
                                if (sortList.size() > 0) {
                                    ColumnSortList.ColumnSortInfo sortInfo = sortList.get(0);
                                    if (sortInfo.getColumn() == nomeStoria) {
                                        comparator = Comparator.comparing(Storia::getNome);
                                    } else if (sortInfo.getColumn() == creatoreStoria) {
                                        comparator = Comparator.comparing(storia -> storia.getUtente().getUsername());
                                    } else if (sortInfo.getColumn() == numeroScenariStoria) {
                                        comparator = Comparator.comparing(Storia::getNumeroScenari);
                                    }

                                    // Ordina la lista di storie in base al comparator
                                    if (comparator != null) {
                                        Collections.sort(tutteLeStorie,
                                                sortInfo.isAscending() ? comparator : comparator.reversed());
                                    }
                                }

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

        ricerca.addClickHandler(new ClickHandler() {
            @Override
            public void onClick(ClickEvent event) {

                // Converte il testo in minuscolo per una ricerca case-insensitive
                String nomeDaCercare = nomeRicerca.getText().toLowerCase();
                String filtro = filtroListBox.getSelectedItemText();

                if (!nomeDaCercare.isEmpty()) {

                    messageLabel.setText("");
                    // Lista per memorizzare le storie che corrispondono al criterio di ricerca
                    risultatiRicerca = new ArrayList<>();

                    if (filtro.equals("Nome Storia")) {
                        // Scorre tutte le storie per cercare quelle il cui nome contiene la stringa
                        // cercata
                        for (Storia storia : tutteLeStorie) {
                            if (storia.getNome().toLowerCase().contains(nomeDaCercare)) {
                                risultatiRicerca.add(storia);
                            }
                        }

                    } else if (filtro.equals("Autore Storia")) {
                        // Scorre tutte le storie per cercare quelle il cui nome dell'autore contiene la
                        // stringa
                        // cercata
                        for (Storia storia : tutteLeStorie) {
                            if (storia.getUtente().getUsername().toLowerCase().contains(nomeDaCercare)) {
                                risultatiRicerca.add(storia);
                            }
                        }

                    } else if (filtro.equals("Numero Scenari")) {
                        // Scorre tutte le storie per cercare quelle il cui numero scenari contiene la
                        // stringa
                        // cercata
                        for (Storia storia : tutteLeStorie) {
                            if (storia.getNumeroScenari().toLowerCase().contains(nomeDaCercare)) {
                                risultatiRicerca.add(storia);
                            }
                        }

                    } else {
                        Window.alert("Seleziona un filtro per la ricerca");
                    }

                    // Aggiorna la tabella con i risultati della ricerca
                    tabella.setRowCount(risultatiRicerca.size(), true);
                    tabella.setVisibleRange(0, risultatiRicerca.size());
                    AsyncDataProvider<Storia> provider = new AsyncDataProvider<Storia>() {
                        @Override
                        protected void onRangeChanged(HasData<Storia> display) {
                            int start = display.getVisibleRange().getStart();
                            int end = start + display.getVisibleRange().getLength();
                            end = end >= risultatiRicerca.size() ? risultatiRicerca.size() : end;
                            List<Storia> subList = risultatiRicerca.subList(start, end);
                            updateRowData(start, subList);
                        }
                    };
                    provider.addDataDisplay(tabella);

                    // Identifica l'elemento selezionato nella nuova lista dei risultati
                    int selectedIndex = tabella.getKeyboardSelectedRow();
                    if (selectedIndex != -1) {
                        storiaSelezionata = risultatiRicerca.get(selectedIndex);
                    } else {
                        storiaSelezionata = null;
                    }

                } else {
                    messageLabel.setStyleName("messaggios");
                    messageLabel.setText("Per cercare tutte le storie, clicca su 'Mostra Tutte'");
                }
            }
        });

        mostraTutti.addClickHandler(new ClickHandler() {
            @Override
            public void onClick(ClickEvent event) {
                if (utente == null) {
                    RootPanel.get("startTable").clear();
                    RootPanel.get("startTable").add(new VisualizzaCatalogo(null));
                    RootPanel.get().clear();
                } else {
                    RootPanel.get("startTable").clear();
                    RootPanel.get("startTable").add(new VisualizzaCatalogo(utente));
                    RootPanel.get().clear();
                }

            }
        });

        tabella.addCellPreviewHandler(new CellPreviewEvent.Handler<Storia>() {
            @Override
            public void onCellPreview(CellPreviewEvent<Storia> event) {
                // Verifica se l'evento è un clic sulla cella
                if ("click".equals(event.getNativeEvent().getType())) {
                    int index = event.getIndex();

                    // Ottenere l'oggetto Storia nella riga cliccata
                    Storia storiaCliccata = tutteLeStorie.get(index);

                    if (risultatiRicerca.isEmpty()) {
                        // Se non c'è ricerca in corso, impostiamo direttamente la storia selezionata
                        storiaSelezionata = storiaCliccata;
                    } else {
                        // Se c'è una ricerca in corso, dobbiamo aggiornare la storia selezionata
                        int absoluteIndex = tutteLeStorie.indexOf(storiaCliccata);
                        if (absoluteIndex != -1) {
                            storiaSelezionata = risultatiRicerca.get(absoluteIndex);
                        }
                    }

                    // Visualizza il testo completo dello scenario iniziale
                    Window.alert("Testo completo dello scenario iniziale:\n"
                            + storiaSelezionata.getScenarioIniziale().getTestoScena());
                }
            }
        });

        giocaButton.addClickHandler(new ClickHandler() {
            @Override
            public void onClick(ClickEvent event) {

                if (utente != null) {
                    int index = tabella.getKeyboardSelectedRow();
                    if (index != -1) {
                        // Storia storiaSelezionata = tutteLeStorie.get(index);
                        // Controllare se l'utente ha gia giocato a questa storia
                        // Se non ha ancora giocato:
                        Storia daGiocare = storiaSelezionata;
                        storiaSelezionata = null;
                        RootPanel.get("startTable").clear();
                        RootPanel.get("startTable").add(new SalvataggiPartita(daGiocare, utente));
                        // RootPanel.get("startTable").add(new GiocaStoria(daGiocare, utenteAttuale));
                        RootPanel.get().clear();
                        // Se ha gia giocato:

                    } else {
                        messageLabel.setStyleName("messaggioa");
                        messageLabel.setText("Seleziona una storia per giocare");
                    }
                } else {
                    messageLabel.setStyleName("messaggioa");
                    messageLabel.setText("Devi essere loggato per giocare");

                }

            }
        });

        backButton.addClickHandler(new ClickHandler() {
            @Override
            public void onClick(ClickEvent event) {

                if (utente != null) {
                    RootPanel.get("startTable").clear();
                    RootPanel.get("startTable").add(new HomePage(utente));
                    RootPanel.get().clear();
                } else {
                    RootPanel.get("startTable").clear();
                    RootPanel.get("startTable").add(new Starter());
                    RootPanel.get().clear();
                }

            }
        });
    }

    private void settaGrafica() {
        mostraTutti.setStyleName("lButton");
        ricerca.setStyleName("lButton");
        backButton.setStyleName("lButton");
        giocaButton.setStyleName("lButton");
    }

    @Override
    public Widget asWidget() {
        return this;
    }

}
