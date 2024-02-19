package com.hala.mywebapp;

import com.google.gwt.core.client.EntryPoint;
import com.google.gwt.core.client.GWT;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.ui.*;

public class App implements EntryPoint{
public static final GreetingServiceAsync hALAServiceAsync = GWT.create(GreetingService.class);

    public void onModuleLoad() {
        hALAServiceAsync.initData(new AsyncCallback<Boolean>() {
                @Override
                public void onFailure(Throwable throwable) {
                    try {
                        throw new Exception("Errore nel caricamento dei dati");
                    } catch (Exception e) {
                        // TODO Auto-generated catch block
                        e.printStackTrace();
                    }
                }

                @Override
                public void onSuccess(Boolean verifica) {
                }
            });
        RootPanel.get("startTable").add(new Starter());
    }
}

/*
 * mvn gwt:codeserver -pl *-client -am
 * mvn jetty:run -pl *-server -am -Denv=dev
 * http://localhost:8080
 * 
 * netstat -ano | find "9876"
 * taskkill /F /PID 14728
 */