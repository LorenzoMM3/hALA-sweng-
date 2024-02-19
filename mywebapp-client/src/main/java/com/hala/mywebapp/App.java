package com.hala.mywebapp;

import com.google.gwt.core.client.EntryPoint;
import com.google.gwt.core.client.GWT;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.ui.*;

public class App implements EntryPoint{

    public void onModuleLoad() {
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