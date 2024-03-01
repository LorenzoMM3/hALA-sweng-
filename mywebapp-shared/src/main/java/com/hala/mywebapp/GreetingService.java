package com.hala.mywebapp;

import com.google.gwt.user.client.rpc.RemoteService;
import com.google.gwt.user.client.rpc.RemoteServiceRelativePath;

/**
 * The client side stub for the RPC service.
 */
@RemoteServiceRelativePath("greet")
public interface GreetingService extends RemoteService {
	public void initData();
	public boolean signIn(Utente utente);
	public boolean logIn(Utente utente);
	public boolean logOut(String username);
	public Utente ottieniUtenteAttuale();
	public boolean creaNuovaStoria(Storia nuovaStoria);
	public boolean aggiungiScenarioAScelta(Scenario scenario);
}
