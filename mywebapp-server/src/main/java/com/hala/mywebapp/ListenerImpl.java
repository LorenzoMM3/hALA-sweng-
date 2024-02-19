package com.hala.mywebapp;

import jakarta.servlet.ServletContextEvent;
import jakarta.servlet.ServletContextListener;

;

public class ListenerImpl implements ServletContextListener {
    @Override
    public void contextInitialized(ServletContextEvent sce) {
        System.out.println("Context initialized.");
    }

    @Override
    public void contextDestroyed(ServletContextEvent sce) {

    }
}
