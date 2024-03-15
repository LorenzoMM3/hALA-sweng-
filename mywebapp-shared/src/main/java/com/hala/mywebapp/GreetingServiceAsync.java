package com.hala.mywebapp;

import java.util.ArrayList;
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

	public void aggiungiScenarioAScelta(String id, Scenario scenario, AsyncCallback<Boolean> callback);

	public void aggiungiScenarioIndovinello(String id, Scenario scenario, AsyncCallback<Boolean> callback);

	public void aggiungiScenarioFinale(String id, Scenario scenario, AsyncCallback<Boolean> callback);

	public void settaScenarioIniziale(Scenario scenario, AsyncCallback<Boolean> callback);
	// public void settaCollegamentoPrecedente(Scenario scenario, Scenario
	// collegamento, AsyncCallback<Void> callback);

	public void settaCollegamentoSuccessivo(Scenario scenario, String opzione, Scenario collegamento, AsyncCallback<Boolean> callback);

	public void salvaSuFileScenari(String nomeStoria, AsyncCallback<Boolean> callback);

	public void prossimoId(AsyncCallback<String> callback);

	public void ottieniScenariStoria(String nomeStoria, AsyncCallback<ArrayList<Scenario>> callback);

	public void ottieniStorie(AsyncCallback<ArrayList<Storia>> callback);

	public void modificaScenario(String nomeStoria, Scenario scenarioDaModificare, Scenario scenarioModificato, AsyncCallback<Boolean> callback);

	public void eliminaStoria(String nomeStoria, AsyncCallback<Boolean> callback);

	public void caricaPartita(Storia storia, Utente giocatore, boolean nuovoGioco, AsyncCallback<Partita> callback);

	public void caricaSuccessivoIndovinello(Partita partita, String risposta, AsyncCallback<Partita> callback);

	public void caricaSuccessivoScelta(Partita partita, String opzione, AsyncCallback<Partita> callback);
}
