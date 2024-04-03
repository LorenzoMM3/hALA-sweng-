package com.hala.mywebapp;

import com.google.gwt.core.client.EntryPoint;
import com.google.gwt.core.client.GWT;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.ui.*;

public class App implements EntryPoint{
public static final GreetingServiceAsync hALAServiceAsync = GWT.create(GreetingService.class);

    public void onModuleLoad() {
        try{
            RootPanel.get("startTable").add(new Starter());
        }
        catch (Exception e){
            System.out.println("Errore: " + e.getMessage());
        }
        
    }
}

/*
mvn gwt:codeserver -pl *-client -am
mvn jetty:run -pl *-server -am -Denv=dev
http://localhost:8080
 
netstat -ano | find "9876"
taskkill /F /PID 
 */
