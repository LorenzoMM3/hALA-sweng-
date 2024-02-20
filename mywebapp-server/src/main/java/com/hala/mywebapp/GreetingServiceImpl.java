package com.hala.mywebapp;

import com.google.gwt.core.client.GWT;
import com.google.gwt.user.server.rpc.jakarta.RemoteServiceServlet;
import org.mapdb.*;

/**
 * The server side implementation of the RPC service.
 */
@SuppressWarnings("serial")
public class GreetingServiceImpl extends RemoteServiceServlet implements GreetingService {

	DB db = DBMaker.fileDB("file.db").make();
	HTreeMap<String, String> data = db.hashMap("dataStorage").keySerializer(Serializer.STRING).valueSerializer(Serializer.STRING).create();
	
	@Override
	public void initData(){
	}
	
	@Override
	public boolean signIn(String username, String password){
		for (String user : data.keySet()) {
            if (user.equalsIgnoreCase(username)) {
                return false; // Non si può registrare
            }
        }
		data.put(username, password);
		db.commit();
		return true; // Si può registrare
	}

	@Override
	public boolean logIn(String username, String password){
		for (String user : data.keySet()) {
            if (user.equalsIgnoreCase(username)) {
                if (data.get(user).equals(password)){
					return true; // Credenziali corrette
				}
            }
        }
		return false; // Credenziali errate
	}


}
