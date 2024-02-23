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
    private Map<String, Utente> data;

    @Override
    public void initData() {
        openDB();
    }

    private void openDB() {
        if (db == null || db.isClosed()) {
            db = DBMaker.fileDB("file.db").make();
            data = (Map<String, Utente>) db.hashMap("utenteStorage").createOrOpen();        }
    }

    @Override
    public boolean signIn(Utente utente) {
        openDB();
        String username = utente.getUsername();
        String password = utente.getPassword();
        for (String user : data.keySet()) { //Si dovrebbe poter togliere il for
            if (data.containsKey(username)) {
                return false; // Non si può registrare
            }
        }
        data.put(username, utente);
        db.commit();
        convertToJson();
        return true; // Si può registrare
    }

    @Override
    public boolean logIn(Utente utente) {
        openDB();
        String username = utente.getUsername();
        String password = utente.getPassword();
    
        Utente utenteSalvato = data.get(username);
    
        if (utenteSalvato != null && utenteSalvato.getPassword().equals(password)) {
            utenteSalvato.setIsLogged(true);
            data.put(utenteSalvato.getUsername(), utenteSalvato);
            db.commit();
            convertToJson();
            return true; // Credenziali corrette
        }
        
        return false; // Credenziali errate
    }

    private void convertToJson() {
        openDB();
    
        try (PrintWriter pW = new PrintWriter(new FileWriter("data.json"))) {
            pW.println("{");

            boolean firstEntry = true;
            for (Map.Entry<String, Utente> entry : data.entrySet()) {
                if (!firstEntry) {
                    pW.println(",");
                }
                pW.println("  \"" + entry.getKey() + "\": {");
                pW.println("    \"username\": \"" + entry.getValue().getUsername() + "\",");
                pW.println("    \"password\": \"" + entry.getValue().getPassword() + "\",");
                pW.println("    \"isLogged\": " + entry.getValue().getIsLogged());
                pW.println("  }");

                firstEntry = false;
            }

            pW.println("}");

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

	@Override
	public boolean logOut(String username) {
		Utente utenteSalvato = data.get(username);
        utenteSalvato.setIsLogged(false);
        data.put(utenteSalvato.getUsername(), utenteSalvato);
        db.commit();
        convertToJson();
        return true;
	}
}
