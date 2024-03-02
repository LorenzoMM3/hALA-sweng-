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
    private Map<String, Utente> utentiNelSito;
    private Map<String, Storia> storieNelSito;
    private Map<String, Scenario> scenariNelSito;
    private int numeroScenari = 0;
    private String numeroScenari2;

    public Utente utenteAttuale;

    @Override
    public void initData() {
        openDB();
    }

    private void openDB() {
        if (db == null || db.isClosed()) {
            db = DBMaker.fileDB("file.db").make();
            utentiNelSito = (Map<String, Utente>) db.hashMap("utenteStorage").createOrOpen();
            storieNelSito = (Map<String, Storia>) db.hashMap("storieNelSitoPresenti").createOrOpen();
            scenariNelSito = (Map<String, Scenario>) db.hashMap("scenariNelSitoPresenti").createOrOpen();
        }
    }

    @Override
    public boolean signIn(Utente utente) {
        openDB();
        String username = utente.getUsername();
        String password = utente.getPassword();
        for (String user : utentiNelSito.keySet()) { // Si dovrebbe poter togliere il for
            if (utentiNelSito.containsKey(username)) {
                return false; // Non si può registrare
            }
        }
        utentiNelSito.put(username, utente);
        db.commit();
        convertToJsonUtenti();
        return true; // Si può registrare
    }

    @Override
    public boolean logIn(Utente utente) {
        openDB();
        String username = utente.getUsername();
        String password = utente.getPassword();

        Utente utenteSalvato = utentiNelSito.get(username);

        if (utenteSalvato != null && utenteSalvato.getPassword().equals(password)) {
            utenteSalvato.setIsLogged(true);
            utentiNelSito.put(utenteSalvato.getUsername(), utenteSalvato);
            db.commit();
            convertToJsonUtenti();
            utenteAttuale = utenteSalvato;
            return true; // Credenziali corrette
        }

        return false; // Credenziali errate
    }

    @Override
    public boolean logOut(String username) {
        Utente utenteSalvato = utentiNelSito.get(username);
        utenteSalvato.setIsLogged(false);
        utentiNelSito.put(utenteSalvato.getUsername(), utenteSalvato);
        db.commit();
        convertToJsonUtenti();
        return true;
    }

    @Override
    public Utente ottieniUtenteAttuale() {
        return utenteAttuale;
    }

    @Override
    public boolean creaNuovaStoria(Storia nuovaStoria) {
        // Se il nome è già presente non si può creare
        openDB();
        String nomeStoria = nuovaStoria.getNome();
        if (storieNelSito.containsKey(nomeStoria)) {
            return false; // Non si può registrare
        }

        storieNelSito.put(nomeStoria, nuovaStoria);
        db.commit();
        convertToJsonStorie();
        return true; // Si può registrare
    }

    /*
     * Per creare il metodo ricorda che è necessario aggiungerlo al
     * "GreetingService" e al "GreetingServiceAsync"
     */
    public boolean aggiungiScenarioAScelta(Scenario scenario) {
        openDB();
        // String nomeStoria = scenario.getNomeStoria();
        numeroScenari2 = contaScenari();
        scenariNelSito.put(numeroScenari2, scenario);
        db.commit();
        convertToJsonScenari();
        return true;
    }

    public boolean aggiungiScenarioIndovinello(Scenario scenario) {
        openDB();
        // String nomeStoria = scenario.getNomeStoria();
        numeroScenari2 = contaScenari();
        scenariNelSito.put(numeroScenari2, scenario);
        db.commit();
        convertToJsonScenari();
        return true;
    }

    // Per convertire in json gli scenari: va completato con quelli mancanti
    private void convertToJsonScenari() {
        openDB();

        try (PrintWriter pW = new PrintWriter(new FileWriter("scenariNelSito.json"))) {
            pW.println("{");

            boolean firstEntry = true;
            for (Map.Entry<String, Scenario> entry : scenariNelSito.entrySet()) {
                String tipologiaTemp = entry.getValue().getTipologia().toString();
                if (!firstEntry) {
                    pW.println(",");
                }
                pW.println("  \"" + entry.getKey() + "\": {");
                pW.println("    \"Nome Storia di appartenenza\": \"" + entry.getValue().getNomeStoria() + "\",");
                pW.println("    \"Testo Scenario\": \"" + entry.getValue().getTestoScena() + "\",");

                if (tipologiaTemp.equalsIgnoreCase("ASCELTA")) {
                    ScenarioAScelta scenarioTemp = (ScenarioAScelta) entry.getValue();
                    pW.println("    \"Domanda scelta\": \"" + scenarioTemp.getDomandaCambioScenario() + "\",");
                    pW.println("    \"Opzioni scelta\": \"" + scenarioTemp.getOpzioniScelta() + "\"");
                }

                if (tipologiaTemp.equalsIgnoreCase("INDOVINELLO")) {
                    ScenarioIndovinello scenarioTemp = (ScenarioIndovinello) entry.getValue();
                    pW.println("    \"Domanda Indovinello\": \"" + scenarioTemp.getDomandaIndovinello() + "\",");
                    pW.println("    \"Risposta Indovinello\": \"" + scenarioTemp.getRispostaIndovinello() + "\"");
                }

                pW.println("  }");

                firstEntry = false;
            }

            pW.println("}");

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void convertToJsonStorie() {
        openDB();

        try (PrintWriter pW = new PrintWriter(new FileWriter("storieNelSito.json"))) {
            pW.println("{");

            boolean firstEntry = true;
            for (Map.Entry<String, Storia> entry : storieNelSito.entrySet()) {
                if (!firstEntry) {
                    pW.println(",");
                }
                pW.println("  \"" + entry.getKey() + "\": {");
                pW.println("    \"nome\": \"" + entry.getValue().getNome() + "\",");
                pW.println("    \"creatore\": \"" + entry.getValue().getUtente().getUsername() + "\",");
                pW.println("    \"iniziata\": " + entry.getValue().getIniziata());
                pW.println("  }");

                firstEntry = false;
            }

            pW.println("}");

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void convertToJsonUtenti() {
        openDB();

        try (PrintWriter pW = new PrintWriter(new FileWriter("utentiNelSito.json"))) {
            pW.println("{");

            boolean firstEntry = true;
            for (Map.Entry<String, Utente> entry : utentiNelSito.entrySet()) {
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

    public String contaScenari() {
        openDB();
        for (Map.Entry<String, Scenario> entry : scenariNelSito.entrySet()) {
            numeroScenari++;
        }
        String numSc2 = Integer.toString(numeroScenari);
        return numSc2;

    }

}