package com.hala.mywebapp;

import org.checkerframework.checker.units.qual.A;
import org.mapdb.*;

import com.google.gwt.user.server.rpc.jakarta.RemoteServiceServlet;

import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Map;

@SuppressWarnings("serial")
public class GreetingServiceImpl extends RemoteServiceServlet implements GreetingService {

    private DB db;
    private Map<String, Utente> utentiNelSito;
    private Map<String, Storia> storieNelSito;
    private Map<String, Scenario> scenariNelSito;
    private Map<String, Scenario> scenariPresenti; // Vorrei sostituire scenariNelSito ma ne creo due per assicurarmi
                                                   // che funzioni
    private int numeroScenari;;
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
            scenariPresenti = (Map<String, Scenario>) db.hashMap("scenariPresenti").createOrOpen();
            if (db == null){
                numeroScenari = 0;
            }
            
        }
    }

    @Override
    public boolean signIn(Utente utente) {
        if (db == null || db.isClosed()) {
            openDB();
        }
        
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
        if (db == null || db.isClosed()) {
            openDB();
        }
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
        if (db == null || db.isClosed()) {
            openDB();
        }
        String nomeStoria = nuovaStoria.getNome();
        if (storieNelSito.containsKey(nomeStoria)) {
            return false; // Non si può registrare
        }

        storieNelSito.put(nomeStoria, nuovaStoria);
        db.commit();
        convertToJsonStorie();
        return true; // Si può registrare
    }

    public boolean aggiungiScenarioAScelta(String id, Scenario scenario) {
        if (db == null || db.isClosed()) {
            openDB();
        }
        // String nomeStoria = scenario.getNomeStoria();
        //numeroScenari2 = contaScenari();
        //scenario.setValId(numeroScenari2);
        scenariNelSito.put(id, scenario);
        System.out.println("Ho creato lo scenario a scelta con id" + id);
        db.commit();
        convertToJsonScenari(); //Da togliere
        return true;
    }

    public boolean aggiungiScenarioIndovinello(String id, Scenario scenario) {
        if (db == null || db.isClosed()) {
            openDB();
        }
        // String nomeStoria = scenario.getNomeStoria();
        //numeroScenari2 = contaScenari();
        //scenario.setValId(numeroScenari2);
        scenariNelSito.put(id, scenario);
        System.out.println("Ho creato lo scenario indovinello con id" + id);
        System.out.println("Ho creato lo scenario indovinello con testo" + scenario.getTestoScena());
        db.commit();
        convertToJsonScenari(); //Da togliere
        return true;
    }

    // Per convertire in json gli scenari: va completato con quelli mancanti
    private void convertToJsonScenari() {
        if (db == null || db.isClosed()) {
            openDB();
        }

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
        if (db == null || db.isClosed()) {
            openDB();
        }

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
        if (db == null || db.isClosed()) {
            openDB();
        }

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
        if (db == null || db.isClosed()) {
            openDB();
        }
        for (Map.Entry<String, Scenario> entry : scenariNelSito.entrySet()) {
            numeroScenari++;
        }
        String numSc2 = Integer.toString(numeroScenari);
        return numSc2;
    }

    public boolean settaScenarioIniziale(Scenario scenario) {
        if (db == null || db.isClosed()) {
            openDB();
        }
        for (Map.Entry<String, Scenario> entry : scenariNelSito.entrySet()) {
            if (entry.getValue().getValId().equalsIgnoreCase(scenario.getValId())) {
                //entry.getKey(); // ID scenario
                System.out.println("Ho trovato lo scenario da settare come iniziale");
                String k = entry.getKey();
                Scenario x = entry.getValue();
                String id = "-1";
                x.addPrecedente(id); //Controllare !!!
                scenariNelSito.put(k,x);
                System.out.println("Ho settato il precedente a -1"); 
                System.out.println("Che ha quindi:" + scenariNelSito.get(k).getPrecedente()); 
                return true;
            }

        }
        return false;
    }

    public boolean settaCollegamentoSuccessivo(Scenario attuale, Scenario daCollegare) {
        
        String keyAttuale = trovaChiavePerScenario(attuale);
        String keyDaCollegare = trovaChiavePerScenario(daCollegare);

        if (!keyAttuale.equals("-1") || !keyDaCollegare.equals("-1")) {

            Scenario x = scenariNelSito.get(keyAttuale);
            x.addSuccessivo(keyDaCollegare); 
            scenariNelSito.put(keyAttuale, x);
            
            System.out.println("attuale.getSuccessivo() " + scenariNelSito.get(keyAttuale).getSuccessivo());
            
            Scenario y = scenariNelSito.get(keyDaCollegare);
            y.addPrecedente(keyAttuale);
            scenariNelSito.put(keyDaCollegare, y);
            
            System.out.println("daCollegare.getPrecedente() " + scenariNelSito.get(keyDaCollegare).getPrecedente());
            return true;
        } else {
            // Gestione caso non trovato
            System.err.println("Errore: Chiave non trovata per uno degli scenari.");
            return false;
        }
    }
    
    private String trovaChiavePerScenario(Scenario scenario) {
        System.out.println("Sto cercando la chiave");
        for (Map.Entry<String, Scenario> entry : scenariNelSito.entrySet()) {
            if (scenario.getValId().equalsIgnoreCase(entry.getValue().getValId())) {
                System.out.println("Chiave trovata");
                return entry.getKey();
            }
        }
        System.out.println("Chiave non trovata");
        return "-1"; // Chiave non trovata
    }

    public boolean salvaSuFileScenari(String nomeStoria) {
        ArrayList<Scenario> temp = new ArrayList<Scenario>();
        boolean trovato = false;
        for (Map.Entry<String, Scenario> entry : scenariNelSito.entrySet()) {
            if ((entry.getValue().getNomeStoria()).equalsIgnoreCase(nomeStoria)) {
                temp.add(entry.getValue());
                trovato = true;
            }
        }

        if (trovato) {
            try (PrintWriter pW = new PrintWriter(new FileWriter("scenariCollegati.json"))) {
                pW.println("{");

                boolean firstEntry = true;
                for (Map.Entry<String, Scenario> entry2 : scenariNelSito.entrySet()) {
                    String tipologiaTemp = entry2.getValue().getTipologia().toString();
                    if (!firstEntry) {
                        pW.println(",");
                    }
                    pW.println("  \"" + entry2.getKey() + "\": {");
                    pW.println(" \"Id\": \"" + entry2.getValue().getValId() + "\",");
                    pW.println("    \"Nome Storia di appartenenza\": \"" + entry2.getValue().getNomeStoria() + "\",");
                    pW.println("    \"Testo Scenario\": \"" + entry2.getValue().getTestoScena() + "\",");

                    if (tipologiaTemp.equalsIgnoreCase("ASCELTA")) {
                        ScenarioAScelta scenarioTemp = (ScenarioAScelta) entry2.getValue();
                        pW.println("    \"Domanda scelta\": \"" + scenarioTemp.getDomandaCambioScenario() + "\",");
                        pW.println("    \"Opzioni scelta\": \"" + scenarioTemp.getOpzioniScelta() + "\",");
                    }

                    if (tipologiaTemp.equalsIgnoreCase("INDOVINELLO")) {
                        ScenarioIndovinello scenarioTemp = (ScenarioIndovinello) entry2.getValue();
                        pW.println("    \"Domanda Indovinello\": \"" + scenarioTemp.getDomandaIndovinello() + "\",");
                        pW.println("    \"Risposta Indovinello\": \"" + scenarioTemp.getRispostaIndovinello() + "\",");
                    }

                    Scenario x = entry2.getValue();
                    pW.println("    \"Scenari precedenti\": \"" + x.getPrecedente() + "\",");
                    pW.println("    \"Scenari successivi\": \"" + x.getSuccessivo() + "\"");

                    pW.println("}");

                    firstEntry = false;
                }

                pW.println("}");
                return true;
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return false;

    }

   
    public String prossimoId(){
        numeroScenari2 = contaScenari();
        System.out.println("Il prossimo id è " + numeroScenari2);
        
        return numeroScenari2;
    }

    @Override
    public ArrayList<Scenario> ottieniScenariStoria(String nomeStoria) {
        System.out.println("Sono fuori1");
        ArrayList<Scenario> temp = new ArrayList<Scenario>();
        System.out.println("Sono fuori2");
        for (Map.Entry<String, Scenario> entry : scenariNelSito.entrySet()) {
            if ((entry.getValue().getNomeStoria()).equalsIgnoreCase(nomeStoria)) {
                temp.add(entry.getValue());
                System.out.println("Ho trovato" + entry.getValue().getTestoScena());
            }
        }
        System.out.println("Sto uscendo");
        return temp;
    }
}