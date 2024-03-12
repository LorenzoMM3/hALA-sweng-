package com.hala.mywebapp;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Map;

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

	public boolean aggiungiScenarioAScelta(String id, Scenario scenario);

	public boolean aggiungiScenarioIndovinello(String id, Scenario scenario);

	public boolean aggiungiScenarioFinale(String id, Scenario scenario);

	public boolean settaScenarioIniziale(Scenario scenario);

	// public void settaCollegamentoPrecedente(Scenario scenario, Scenario
	// collegamento);

	public boolean settaCollegamentoSuccessivo(Scenario scenario, String opzione,Scenario collegamento);

	public boolean salvaSuFileScenari(String nomeStoria);

	public String prossimoId();

	public ArrayList<Scenario> ottieniScenariStoria(String nomeStoria);

	public ArrayList<Storia> ottieniStorie();

	public boolean modificaScenario(String nomeStoria, Scenario scenarioDaModificare, Scenario scenarioModificato);

	public boolean eliminaStoria(String nomeStoria);
}
