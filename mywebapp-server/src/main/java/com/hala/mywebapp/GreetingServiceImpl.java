package com.hala.mywebapp;

import com.google.gwt.core.client.GWT;
import com.google.gwt.user.server.rpc.jakarta.RemoteServiceServlet;

import java.io.File;
import java.io.FileWriter;
import java.io.PrintWriter;
import java.util.Map;
import java.io.IOException;

import org.mapdb.*;

/**
 * The server side implementation of the RPC service.
 */
@SuppressWarnings("serial")
public class GreetingServiceImpl extends RemoteServiceServlet implements GreetingService {

	DB db;
	HTreeMap<String, String> data;
	
	@Override
	public void initData(){
        openDB();
		
	}
	
    private void openDB(){
        if (db == null || db.isClosed()){
            db = DBMaker.fileDB("file.db").make();
            data = db.hashMap("dataStorage").keySerializer(Serializer.STRING).valueSerializer(Serializer.STRING).createOrOpen();
        }
    }

	@Override
	public boolean signIn(String username, String password){
        openDB();
		for (String user : data.keySet()) {
            if (user.equalsIgnoreCase(username)) {
                return false; // Non si può registrare
            }
        }
		data.put(username, password);
		db.commit();
		convertToJson();
		return true; // Si può registrare
	}

	@Override
	public boolean logIn(String username, String password){
        openDB();
		for (String user : data.keySet()) {
            if (user.equalsIgnoreCase(username)) {
                if (data.get(user).equals(password)){
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
