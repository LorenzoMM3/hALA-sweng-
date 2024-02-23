package com.hala.mywebapp;

import org.mapdb.*;

import com.google.gwt.user.server.rpc.jakarta.RemoteServiceServlet;

import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Map;

@SuppressWarnings("serial")
public class GreetingServiceImpl extends RemoteServiceServlet implements GreetingService {

    private DB db;
    private HTreeMap<String, String> data;

    @Override
    public void initData() {
        openDB();
    }

    private void openDB() {
        if (db == null || db.isClosed()) {
            db = DBMaker.fileDB("file.db").make();
            data = db.hashMap("dataStorage").keySerializer(Serializer.STRING).valueSerializer(Serializer.STRING).createOrOpen();
        }
    }

    @Override
    public boolean signIn(Utente utente) {
        openDB();
        String username = utente.getUsername();
        String password = utente.getPassword();
        for (String user : data.keySet()) {
            if (user.equals(username)) {
                return false; // Non si può registrare
            }
        }
        data.put(username, password);
        db.commit();
        convertToJson();
        return true; // Si può registrare
    }

    @Override
    public boolean logIn(Utente utente) {
        openDB();
        String username = utente.getUsername();
        String password = utente.getPassword();
        for (String user : data.keySet()) {
            if (user.equals(username)) {
                if (data.get(user).equals(password)) {
                    return true; // Credenziali corrette
                }
            }
        }
        return false; // Credenziali errate
    }

    private void convertToJson() {
        openDB();
        try (PrintWriter pW = new PrintWriter(new FileWriter("data.json"))) {
            pW.println("{");
            boolean firstEntry = true;
            for (Map.Entry<String, String> entry : data.entrySet()) {
                if (!firstEntry) {
                    pW.println(",");
                }
                pW.println("  \"" + entry.getKey() + "\": \"" + entry.getValue() + "\"");
                firstEntry = false;
            }
            pW.println("}");

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
