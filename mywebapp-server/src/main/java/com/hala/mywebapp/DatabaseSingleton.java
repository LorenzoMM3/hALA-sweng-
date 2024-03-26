package com.hala.mywebapp;

import org.mapdb.DB;
import org.mapdb.DBMaker;

import java.util.Map;

public class DatabaseSingleton {
    private static DatabaseSingleton instance;
    public DB db;
    private Map<String, Utente> utentiNelSito;
    private Map<String, Storia> storieNelSito;
    private Map<String, Scenario> scenariNelSito;
    private Map<String, Partita> partiteNelSito;

    private DatabaseSingleton() {
        openDB();
    }

    public static synchronized DatabaseSingleton getInstance() {
        if (instance == null) {
            instance = new DatabaseSingleton();
        }
        return instance;
    }

    private void openDB() {
        if (db != null && !db.isClosed()) {
            db.close();
        }

        db = DBMaker.fileDB("file.db").make();
        utentiNelSito = (Map<String, Utente>) db.hashMap("utenteStorage").createOrOpen();
        storieNelSito = (Map<String, Storia>) db.hashMap("storieNelSitoPresenti").createOrOpen();
        scenariNelSito = (Map<String, Scenario>) db.hashMap("scenariNelSitoPresenti").createOrOpen();
        partiteNelSito = (Map<String, Partita>) db.hashMap("partiteNelSitoPresenti").createOrOpen();

        // Altre inizializzazioni se necessario
    }

    // Metodi per ottenere i vari hashmap e altre operazioni sul database

    public Map<String, Utente> getUtentiNelSito() {
        return utentiNelSito;
    }

    public Map<String, Storia> getStorieNelSito() {
        return storieNelSito;
    }

    public Map<String, Scenario> getScenariNelSito() {
        return scenariNelSito;
    }

    public Map<String, Partita> getPartiteNelSito() {
        return partiteNelSito;
    }

    // Metodi per altre operazioni sul database

    // Chiudi il database
    public void closeDB() {
        if (db != null && !db.isClosed()) {
            db.close();
        }
    }
}
