package com.hala.mywebapp;

import org.checkerframework.checker.units.qual.A;
import org.checkerframework.checker.units.qual.s;
import org.mapdb.*;

import com.google.gwt.user.server.rpc.jakarta.RemoteServiceServlet;

import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

@SuppressWarnings("serial")
public class GreetingServiceImpl extends RemoteServiceServlet implements GreetingService {

    private DB db;
    private Map<String, Utente> utentiNelSito;
    private Map<String, Storia> storieNelSito;
    private Map<String, Scenario> scenariNelSito;
    private ArrayList<Partita> partite;

    private int numeroScenari;
    private String numeroScenari2;
    private int numeroPartite;
    private String numeroPartite2;


    public Utente utenteAttuale;

    @Override
    public void initData() {
        openDB();
    }

    private void openDB() {
        if (db != null && !db.isClosed()) {
            db.close();
        }

        if (db == null || db.isClosed()) {
            db = DBMaker.fileDB("file.db").make();
            utentiNelSito = (Map<String, Utente>) db.hashMap("utenteStorage").createOrOpen();
            storieNelSito = (Map<String, Storia>) db.hashMap("storieNelSitoPresenti").createOrOpen();
            scenariNelSito = (Map<String, Scenario>) db.hashMap("scenariNelSitoPresenti").createOrOpen();
            if (partite == null) {
                partite = new ArrayList<Partita>();
                numeroPartite = 0;
            }
            if (db == null) {
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
        utenteAttuale = null;
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
        scenariNelSito.put(id, scenario);
        db.commit();
        return true;
    }

    public boolean aggiungiScenarioFinale(String id, Scenario scenario) {
        if (db == null || db.isClosed()) {
            openDB();
        }
        scenariNelSito.put(id, scenario);
        db.commit();
        return true;
    }

    public boolean aggiungiScenarioIndovinello(String id, Scenario scenario) {
        if (db == null || db.isClosed()) {
            openDB();
        }
        scenariNelSito.put(id, scenario);
        db.commit();
        return true;
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
                pW.println("    \"Nome\": \"" + entry.getValue().getNome() + "\",");
                pW.println("    \"Autore\": \"" + entry.getValue().getUtente().getUsername() + "\",");
                pW.println("    \"Scenario iniziale\": \"" + entry.getValue().getScenarioIniziale().getValId() + "\",");
                pW.println("    \"Numero Scenari\": \"" + entry.getValue().getNumeroScenari() + "\"");
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
        String nomeStoria = "";
        Scenario temp = new Scenario();
        for (Map.Entry<String, Scenario> entry : scenariNelSito.entrySet()) {
            if (entry.getValue().getValId().equalsIgnoreCase(scenario.getValId())) {
                nomeStoria = entry.getValue().getNomeStoria();
                String k = entry.getKey();
                Scenario x = entry.getValue();
                String id = "-1";
                x.addPrecedente(id);
                temp = x;
                scenariNelSito.put(k, x);
            }

        }
        if (!nomeStoria.equals("")) {
            for (Storia s : storieNelSito.values()) {
                if (s.getNome().equalsIgnoreCase(nomeStoria)) {
                    Storia tempS = s;
                    tempS.setScenarioIniziale(temp);
                    String key = tempS.getNome();
                    storieNelSito.put(key, tempS);
                    return true;
                }
            }
        }
        return false;
    }

    public boolean settaCollegamentoSuccessivo(Scenario attuale, String opzione, Scenario daCollegare) {

        // Opzione è, negli scenari a scelta, il testo dell'opzione, negli indovinelli è
        // true o false
        String keyAttuale = trovaChiavePerScenario(attuale);
        String keyDaCollegare = trovaChiavePerScenario(daCollegare);

        
        if (!keyAttuale.equals("-2") || !keyDaCollegare.equals("-2")) {
            
            Scenario x = scenariNelSito.get(keyAttuale);
            String tipologia = x.getTipologia() + "";
            if (tipologia.equals("ASCELTA")) {
                ScenarioAScelta temp = (ScenarioAScelta) x;
                temp.addSuccessivo(opzione, keyDaCollegare);
                scenariNelSito.put(keyAttuale, temp);
            } else if (tipologia.equals("INDOVINELLO")) {
                ScenarioIndovinello temp = (ScenarioIndovinello) x;
                temp.addSuccessivo(opzione, keyDaCollegare);
                scenariNelSito.put(keyAttuale, temp);
            } else {
                System.err.println("Errore: Tipologia non riconosciuta.");
            }

            Scenario y = scenariNelSito.get(keyDaCollegare);
            y.addPrecedente(keyAttuale);
            scenariNelSito.put(keyDaCollegare, y);

            //Se è lo scenario iniziale
            if (x.getPrecedente().get(0).equals("-1")){
                Storia s = storieNelSito.get(x.getNomeStoria());
                s.setScenarioIniziale(x);
                storieNelSito.put(x.getNomeStoria(), s);
                convertToJsonStorie();
                System.out.println("x: " + x.getValId());
                System.out.println("successivi: " + x.getSuccessivo());
            }
            db.commit();
            return true;
        } else {
            System.err.println("Errore: Chiave non trovata per uno degli scenari.");
            return false;
        }
    }

    private String trovaChiavePerScenario(Scenario scenario) {
        for (Map.Entry<String, Scenario> entry : scenariNelSito.entrySet()) {
            if (scenario.getValId().equalsIgnoreCase(entry.getValue().getValId())) {
                return entry.getKey();
            }
        }
        return "-2"; // Chiave non trovata
    }

    public boolean controlloCollegamenti(ArrayList<Scenario> temp) {

        for (Scenario t : temp) {
            if (t.getPrecedente().isEmpty() && t.getPrecedente() == null) {
                return false;
            }
        }
        return true;
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

        
        if (trovato && controlloCollegamenti(temp)) {
            try (PrintWriter pW = new PrintWriter(new FileWriter("scenariCollegati.json"))) {
                salvaNumeroScenariPerStoria(nomeStoria, true);
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
                        pW.println("    \"Domanda Indovinello\": \"" + scenarioTemp.getDomandaCambioScenario() + "\",");
                        pW.println("    \"Risposta Indovinello\": \"" + scenarioTemp.getRispostaIndovinello() + "\",");
                    }

                    Scenario x = entry2.getValue();
                    pW.println(
                            "    \"Oggetti Sbloccabili tramite lo Scenario\": \"" + x.getOggettiCheSblocca() + "\",");
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
        salvaNumeroScenariPerStoria(nomeStoria, false);
        return false;

    }

    private void salvaNumeroScenariPerStoria(String nomeStoria, boolean verifica) {
        int numeroScenari = 0;
        if (verifica){
            for (Map.Entry<String, Scenario> entry : scenariNelSito.entrySet()) {
                if ((entry.getValue().getNomeStoria()).equalsIgnoreCase(nomeStoria)) {
                    numeroScenari++;
                }
            }
            for (Map.Entry<String, Storia> entry : storieNelSito.entrySet()) {
                if ((entry.getValue().getNome()).equalsIgnoreCase(nomeStoria)) {
                    Storia s = entry.getValue();
                    String n = numeroScenari + "";
                    String k = entry.getKey();
                    s.setNumeroScenari(n);
                    storieNelSito.put(k, s);
                }
            }
            
        }
        else {
            for (Map.Entry<String, Storia> entry : storieNelSito.entrySet()) {
                if ((entry.getValue().getNome()).equalsIgnoreCase(nomeStoria)) {
                    Storia s = entry.getValue();
                    String n = numeroScenari + "";
                    String k = entry.getKey();
                    s.setNumeroScenari(n);
                    storieNelSito.put(k, s);
                }
            }
        }

        db.commit();
        convertToJsonStorie();
        
    }

    public String prossimoId() {
        numeroScenari2 = contaScenari();
        return numeroScenari2;
    }

    @Override
    public ArrayList<Scenario> ottieniScenariStoria(String nomeStoria) {
        ArrayList<Scenario> temp = new ArrayList<Scenario>();
        for (Map.Entry<String, Scenario> entry : scenariNelSito.entrySet()) {
            if ((entry.getValue().getNomeStoria()).equalsIgnoreCase(nomeStoria)) {
                temp.add(entry.getValue());
            }
        }
        return temp;
    }

    public ArrayList<Storia> ottieniStorie() {
        ArrayList<Storia> temp = new ArrayList<Storia>();
        for (Map.Entry<String, Storia> entry : storieNelSito.entrySet()) {
            temp.add(entry.getValue());
        }
        return temp;
    }

    public boolean modificaScenario(String nomeStoria, Scenario scenarioDaModificare, Scenario scenarioModificato) {
        String key = trovaChiavePerScenario(scenarioDaModificare);
        if (!key.equals("-1")) {
            if (scenarioModificato.getTipologia().equals(TipologiaScenario.ASCELTA)) {
                ScenarioAScelta temp = (ScenarioAScelta) scenarioModificato;
                scenariNelSito.put(key, temp);
            } else if (scenarioModificato.getTipologia().equals(TipologiaScenario.INDOVINELLO)) {
                ScenarioIndovinello temp = (ScenarioIndovinello) scenarioModificato;
                scenariNelSito.put(key, temp);
            } else {
                Scenario temp = scenarioModificato;
                scenariNelSito.put(key, temp);
            }

            // scenariNelSito.put(key, temp);

            //Controllo se è uno scenario iniziale
            ArrayList<String> precedenti = scenarioModificato.getPrecedente();
            if (precedenti.size() == 1 && precedenti.get(0).equals("-1")){
                Storia s = storieNelSito.get(nomeStoria);
                s.setScenarioIniziale(scenarioModificato);
                storieNelSito.put(nomeStoria, s);
                convertToJsonStorie();
            }
            salvaSuFileScenari(nomeStoria);
            db.commit();
            return true;
        }
        return false;
    }

    public boolean eliminaStoria(String nomeStoria) {
        for (Map.Entry<String, Storia> entry : storieNelSito.entrySet()) {
            if (entry.getValue().getNome().equalsIgnoreCase(nomeStoria)) {
                eliminaScenari(nomeStoria);
                storieNelSito.remove(entry.getKey());
                db.commit();
                convertToJsonStorie();
                return true;
            }
        }
        return false;
    }

    private void eliminaScenari(String nomeStoria) {
        for (Map.Entry<String, Scenario> entry : scenariNelSito.entrySet()) {
            if (entry.getValue().getNomeStoria().equalsIgnoreCase(nomeStoria)) {
                scenariNelSito.remove(entry.getKey());
                db.commit();
                salvaSuFileScenari(nomeStoria);
            }
        }
    }

    private String contaPartite() {
        if (db == null || db.isClosed()) {
            openDB();
        }
        numeroPartite++;
        numeroPartite2 = Integer.toString(numeroPartite);
        return numeroPartite2;
    }

    

    public Partita caricaPartita(Storia storia, Utente giocatore, boolean ricomincia){
        String nomeStoria = storia.getNome();
        //System.out.println("Inizio carica partita");
        //System.out.println("Nome storia: " + nomeStoria);
        String usernameGiocatore = giocatore.getUsername();
        //System.out.println("Username giocatore: " + usernameGiocatore);
        boolean iniziata = false;
        Partita daTornare;
        for (Partita p : partite){
            if (p.getStoria().getNome().equalsIgnoreCase(nomeStoria) && p.getGiocatore().getUsername().equalsIgnoreCase(usernameGiocatore)){
                iniziata = true;
                
            }
        }
        
        if (!iniziata || ricomincia){  
            contaPartite();
            String nuovoId = contaPartite();
            daTornare = new Partita(giocatore, storia, nuovoId);
            //System.out.println("Nuova partita creata con id: " + daTornare.getId());
            //System.out.println("Storia: " + daTornare.getStoria().getNome());
            //System.out.println("Giocatore: " + daTornare.getGiocatore().getUsername());
            //System.out.println("Scenario attuale: " + daTornare.getScenarioAttuale().getValId());
            //System.out.println("Scenari successivi: " + daTornare.getScenarioAttuale().getSuccessivo());
            partite.add(daTornare);
            aggiornaPartita(daTornare);
            convertToJsonPartite();
            //System.out.println("Partita aggiunta");
            return daTornare;
        } else { //La partita è già iniziata
            
            for (Partita p : partite){
                if (p.getStoria().getNome().equalsIgnoreCase(nomeStoria) && p.getGiocatore().getUsername().equalsIgnoreCase(usernameGiocatore)){
                    daTornare = p;
                    aggiornaPartita(daTornare);
                    return daTornare;
                }
            }
            
        } 
        return null; // Non dovrebbe mai arrivare qui*/
        
    }

    public Partita caricaSuccessivoIndovinello(Partita partita, String risposta){
        //System.out.println("Id attuale: " + partita.getScenarioAttuale().getValId());
        ScenarioIndovinello attuale = (ScenarioIndovinello)partita.getScenarioAttuale();
        boolean rispostaCorretta = attuale.verificaRisposta(risposta);
        HashMap<String, String> successivi = attuale.getSuccessivo();
        //System.out.println("successivi: " + successivi);
        String indiceSuccessivo = "";
        if (rispostaCorretta){
            indiceSuccessivo = successivi.get("true");
        }
        else {
            indiceSuccessivo = successivi.get("false");
        }
        //System.out.println("Indice successivo: " + indiceSuccessivo);
        Scenario daTornare = scenariNelSito.get(indiceSuccessivo);
        //System.out.println("Nuovo id: " + daTornare.getValId());
        partita.setScenarioAttuale(daTornare);
        //System.out.println("Nuovo id prova: " + daTornare.getValId());
        aggiornaPartita(partita);
        return partita;
    }

    public Partita caricaSuccessivoScelta(Partita partita, String scelta){
        ScenarioAScelta attuale = (ScenarioAScelta)partita.getScenarioAttuale();
        HashMap<String, String> successivi = attuale.getSuccessivo();
        String indiceSuccessivo = successivi.get(scelta);
        Scenario daTornare = scenariNelSito.get(indiceSuccessivo);
        partita.setScenarioAttuale(daTornare);
        aggiornaPartita(partita);
        return partita;
    }

    private void aggiornaPartita(Partita partita) { //NEcessario iteratore perchè altrimenti non si può modificare la lista in caricaPartita
        Iterator<Partita> iterator = partite.iterator();
        while (iterator.hasNext()) {
            Partita p = iterator.next();
            if (p.getId().equalsIgnoreCase(partita.getId())) {
                iterator.remove(); // Rimuovi l'elemento corrente dalla lista
                partite.add(partita); // Aggiungi il nuovo elemento
                System.out.println("Partita aggiornata a:" + partita.getScenarioAttuale().getValId());
                convertToJsonPartite();
                break; // Esci dal ciclo una volta aggiornato l'elemento
            }
        }
    }
    

    

    private void convertToJsonPartite(){
        System.out.println("ConvertToJsonPartite");
        if (db == null || db.isClosed()) {
            openDB();
        }

        try (PrintWriter pW = new PrintWriter(new FileWriter("partiteNelSito.json"))) {
            pW.println("{");

            boolean firstEntry = true;
            for (Partita p: partite) {
                System.out.println("Partita: " + p.getId());
                if (!firstEntry) {
                    pW.println(",");
                }
                pW.println("  \"" + p.getId() + "\": {");
                pW.println("    \"Giocatore\": \"" + p.getGiocatore().getUsername() + "\",");
                pW.println("    \"Storia\": \"" + p.getStoria().getNome() + "\",");
                pW.println("    \"Id scenario attuale\": \"" + p.getScenarioAttuale().getValId() + "\",");
                pW.println("    \"Inventario\": " + p.getInventario());
                pW.println("  }");

                firstEntry = false;
            }

            pW.println("}");

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void closeDatabase() {
        if (db != null && !db.isClosed()) {
            db.close();
        }
    }

    private void logoutUtenti() {
        for (Map.Entry<String, Utente> entry : utentiNelSito.entrySet()) {
            Utente u = entry.getValue();
            u.setIsLogged(false);
            String key = entry.getKey();
            utenteAttuale = null;
            utentiNelSito.put(key, u);
        }
        db.commit();
        convertToJsonUtenti();
    }

    @Override
    public void destroy() {
        logoutUtenti();
        closeDatabase();
        super.destroy();
    }

}