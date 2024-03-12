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

public class ModificaStoria extends Composite implements IsWidget {

    public static final GreetingServiceAsync hALAServiceAsync = GWT.create(GreetingService.class);
    private static final ModificaStoriaUiBinder uiBinder = GWT.create(ModificaStoriaUiBinder.class);

    interface ModificaStoriaUiBinder extends UiBinder<Widget, ModificaStoria> {
    }

    // private ArrayList<Storia> storie;
    private ArrayList<Storia> storieUtente;
    private String Storie;

    @UiField
    Label elencoStorie;

    public ModificaStoria(String utente) {

        initWidget(uiBinder.createAndBindUi(this));

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
                        Storie = Storie + "\n" + s.getNome();
                    }
                }
                elencoStorie.setText(Storie);

            }
        });

    }
}