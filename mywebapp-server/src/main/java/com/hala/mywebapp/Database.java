package com.hala.mywebapp;

import org.mapdb.DB;
import org.mapdb.DBException;
import org.mapdb.DBMaker;

import com.google.gwt.user.client.Window;

import java.util.Map;

public class Database {
    private static Database instance;
    public DB db;
    private Map<String, Utente> utentiNelSito;
    private Map<String, Storia> storieNelSito;
    private Map<String, Scenario> scenariNelSito;
    private Map<String, Partita> partiteNelSito;

    private Database() {
        openDB();
    }

    public static synchronized Database getInstance() {
        if (instance == null) {
            instance = new Database();
        }
        return instance;
    }

    @SuppressWarnings("unchecked")
    public synchronized void openDB() {
        if (db != null && !db.isClosed()) {
            db.close();
        }
        try {// db = DBMaker.fileDB("file.db").make();
            db = DBMaker.fileDB("file.db").checksumHeaderBypass()
                    .closeOnJvmShutdown().make();
            utentiNelSito = (Map<String, Utente>) db.hashMap("utenteStorage").createOrOpen();
            storieNelSito = (Map<String, Storia>) db.hashMap("storieNelSitoPresenti").createOrOpen();
            scenariNelSito = (Map<String, Scenario>) db.hashMap("scenariNelSitoPresenti").createOrOpen();
            partiteNelSito = (Map<String, Partita>) db.hashMap("partiteNelSitoPresenti").createOrOpen();
        } catch (DBException.VolumeIOError volumeIOError) {
            Window.alert(
                    "Errore del Database. Riprova");
        }
    }

    // Metodi per ottenere i vari hashmap

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

    public DB getDB() {
        return db;
    }

    // Metodi per altre operazioni sul database

    // Chiudi il database
    public synchronized void closeDB() {
        try {
            if (db != null && !db.isClosed()) {
                db.close();
            }
        } catch (DBException.VolumeIOError volumeIOError) {
            Window.alert(
                    "Errore del Database. Riprova");
        }
    }
}
