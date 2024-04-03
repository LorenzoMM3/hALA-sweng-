package com.hala.mywebapp;

import jakarta.servlet.ServletContextEvent;
import jakarta.servlet.ServletContextListener;


public class ListenerImpl implements ServletContextListener {

    GreetingServiceImpl greetingServiceImpl = new GreetingServiceImpl();
    @Override
    public void contextInitialized(ServletContextEvent sce) {
        greetingServiceImpl.initData();
        System.out.println("Context initialized.");
    }

    @Override
    public void contextDestroyed(ServletContextEvent sce) {
        try {
            // Chiudi tutte le risorse necessarie
            greetingServiceImpl.initData();
            if (Database.getInstance().getDB().isClosed()) {
                Database.getInstance().openDB();  
            }
            greetingServiceImpl.logoutUtenti();
            Database.getInstance().closeDB();
        } catch (Exception e) {
            System.err.println("Errore durante la chiusura del server: " + e.getMessage());
            e.printStackTrace(); // Stampa lo stack trace completo per debug
        }
        System.out.println("Context destroyed.");
    }
}
