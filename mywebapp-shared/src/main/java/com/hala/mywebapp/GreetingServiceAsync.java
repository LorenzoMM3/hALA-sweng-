package com.hala.mywebapp;

import java.util.Map;

import com.google.gwt.user.client.rpc.AsyncCallback;

/**
 * The async counterpart of <code>GreetingService</code>.
 */
public interface GreetingServiceAsync {
	public void initData(AsyncCallback<Boolean> callback);

	public void signIn(Utente utente, AsyncCallback<Boolean> callback);

	public void logIn(Utente utente, AsyncCallback<Boolean> callback);

	public void logOut(String username, AsyncCallback<Boolean> callback);

	public void creaNuovaStoria(Storia nuovaStoria, AsyncCallback<Boolean> callback);

	public void ottieniUtenteAttuale(AsyncCallback<Utente> callback);

	public void aggiungiScenarioAScelta(Scenario scenario, AsyncCallback<Boolean> callback);

	public void aggiungiScenarioIndovinello(Scenario scenario, AsyncCallback<Boolean> callback);

	public void settaScenarioIniziale(Scenario scenario, AsyncCallback<Boolean> callback);
	// public void settaCollegamentoPrecedente(Scenario scenario, Scenario
	// collegamento, AsyncCallback<Void> callback);

	public void settaCollegamentoSuccessivo(Scenario scenario, Scenario collegamento, AsyncCallback<Boolean> callback);

	public void salvaSuFileScenari(String nomeStoria, AsyncCallback<Boolean> callback);

}
