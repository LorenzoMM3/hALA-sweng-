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
    private Map<String, Partita> partiteNelSito;

    private int numeroScenari;
    private String numeroScenari2;
    private int numeroPartite;
    private String numeroPartite2;

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
            partiteNelSito = (Map<String, Partita>) db.hashMap("partiteNelSitoPresenti").createOrOpen();

            if (db == null) {
                numeroScenari = 0;
                numeroPartite = 0;
            }

        }
    }

    @Override
    public boolean signIn(Utente utente) {
        if (db == null || db.isClosed()) {
            openDB();
        }

        String username = utente.getUsername();

        if (utentiNelSito.containsKey(username)) {
            return false; // Non si può registrare
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

        Utente temp = utentiNelSito.get(username);

        if (utente != null && utente.getPassword().equals(temp.getPassword())) {
            utente.setIsLogged(true);
            utentiNelSito.put(username, utente);
            db.commit();
            convertToJsonUtenti();
            return true; // Credenziali corrette
        }

        return false; // Credenziali errate
    }

    @Override
    public boolean logOut(Utente utente) {
        utente.setIsLogged(false);
        utentiNelSito.put(utente.getUsername(), utente);
        db.commit();
        convertToJsonUtenti();
        return true;
    }

    @Override
    public boolean creaNuovaStoria(Storia nuovaStoria) {
        // Se il nome è già presente non si può creare
        if (db == null || db.isClosed()) {
            openDB();
        }
        System.out.println("Nome storia" + nuovaStoria.getNome());
        System.out.println("Nome creatore" + nuovaStoria.getUtente());
        String nomeStoria = nuovaStoria.getNome();
        System.out.println("Nome: " + nomeStoria);
        if (storieNelSito.containsKey(nomeStoria)) {
            return false; // Non si può registrare
        }
        System.out.println("Uno");
        storieNelSito.put(nomeStoria, nuovaStoria);
        System.out.println("Uno");
        db.commit();
        convertToJsonStorie();
        System.out.println("Uno");
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

            // Se è lo scenario iniziale
            if (x.getPrecedente().get(0).equals("-1")) {
                Storia s = storieNelSito.get(x.getNomeStoria());
                s.setScenarioIniziale(x);
                storieNelSito.put(x.getNomeStoria(), s);
                convertToJsonStorie();
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
        if (verifica) {
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

        } else {
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
        int i = 0;
        ArrayList<Storia> temp = new ArrayList<Storia>();
        for (Map.Entry<String, Storia> entry : storieNelSito.entrySet()) {
            temp.add(entry.getValue());
            System.out.println("storia nel map " + entry.getValue().getNome());
            System.out.println("storia nell'array " + temp.get(i).getNome());
            i++;
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

            // Controllo se è uno scenario iniziale
            ArrayList<String> precedenti = scenarioModificato.getPrecedente();
            if (precedenti.size() == 1 && precedenti.get(0).equals("-1")) {
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

    public void eliminaScenari(String nomeStoria) {
        for (Map.Entry<String, Scenario> entry : scenariNelSito.entrySet()) {
            if (entry.getValue().getNomeStoria().equalsIgnoreCase(nomeStoria)) {
                scenariNelSito.remove(entry.getKey());
                db.commit();
                salvaSuFileScenari(nomeStoria);
            }
        }
    }

    public String contaPartite() {
        if (db == null || db.isClosed()) {
            openDB();
        }
        numeroPartite++;
        numeroPartite2 = Integer.toString(numeroPartite);
        return numeroPartite2;
    }

    public Partita caricaPartita(Storia storia, Utente giocatore, boolean nuovoGioco) {
        System.out.println("Carica partita");
        System.out.println("Storia: " + storia.getNome());
        System.out.println("Scenario iniziale: " + storia.getScenarioIniziale().getValId());
        String nomeStoria = storia.getNome();
        String usernameGiocatore = giocatore.getUsername();
        // boolean iniziata = false;
        String idPartita = "-1";
        Partita daTornare;
        // controllo se esiste già la partita

        /*
         * for (Map.Entry<String, Partita> entry : partiteNelSito.entrySet()) {
         * Partita p = entry.getValue();
         * if (p.getStoria().getNome().equalsIgnoreCase(nomeStoria) &&
         * p.getGiocatore().getUsername().equalsIgnoreCase(usernameGiocatore)){
         * //iniziata = true; //se esiste setto a true
         * idPartita = p.getId(); //e riprendo il suo id
         * System.out.println("Scenario attuale: " + p.getScenarioAttuale().getValId());
         * }
         * }
         */
        Partita p = datiPartita(nomeStoria, usernameGiocatore);
        if (p != null) {
            idPartita = p.getId();
        }

        // se non è stata iniziata e sto facendo partire una nuova giocata oppure sto
        // facendo partire una nuova giocata (? ma non basta solo nuovoGioco?)
        if (idPartita.equals("-1") || (nuovoGioco)) { // potrei anche fare il controllo semplicemente se l'id è = -1
            if (nuovoGioco) {
                eliminaPartita(storia, giocatore);
            }
            contaPartite();// chiamo il contapartite per creare l'id
            String nuovoId = contaPartite();
            System.out.println("Storia, scenario iniziale:" + storia.getScenarioIniziale());
            daTornare = new Partita(giocatore, storia, nuovoId); // creo la partita
            System.out.println("Nuova partita creata, scenario iniziale:" + daTornare.getScenarioAttuale().getValId());
            partiteNelSito.put(nuovoId, daTornare);// la aggiungo nel db
            System.out.println("Partita inserita nella mappa, id scenario iniziale: "
                    + partiteNelSito.get(nuovoId).getScenarioAttuale().getValId());

            convertToJsonPartite();
            return daTornare;// la return

        } else if (!idPartita.equals("-1") && (!nuovoGioco)) { // ------si può fare direttamente l'else-if o anche solo
                                                               // else (per precisione metterei solo else)
            daTornare = partiteNelSito.get(idPartita);
            return daTornare;
        }
        return null; // Non dovrebbe mai arrivare qui*/

    }

    public Partita datiPartita(String storia, String utente) {
        for (Map.Entry<String, Partita> entry : partiteNelSito.entrySet()) {
            Partita p = entry.getValue();
            if (p.getStoria().getNome().equalsIgnoreCase(storia)
                    && p.getGiocatore().getUsername().equalsIgnoreCase(utente)) {
                // iniziata = true; //se esiste setto a true
                // idPartita = p.getId(); //e riprendo il suo id
                // System.out.println("Scenario attuale: " + p.getScenarioAttuale().getValId());
                return p;
            }
        }
        return null;
    }

    public void eliminaPartita(Storia storia, Utente utente) {
        for (Map.Entry<String, Partita> entry : partiteNelSito.entrySet()) {
            Partita p = entry.getValue();
            if (p.getStoria().getNome().equalsIgnoreCase(storia.getNome())
                    && p.getGiocatore().getUsername().equalsIgnoreCase(utente.getUsername())) {
                partiteNelSito.remove(entry.getKey());
                db.commit();
                convertToJsonPartite();
            }
        }
    }

    public Partita caricaSuccessivoIndovinello(Partita partita, String risposta) {

        Partita temp = partita;
        String id = temp.getId();
        ScenarioIndovinello attuale = (ScenarioIndovinello) temp.getScenarioAttuale();
        boolean rispostaCorretta = attuale.verificaRisposta(risposta);
        HashMap<String, String> successivi = attuale.getSuccessivo();
        String indiceSuccessivo = "";
        if (rispostaCorretta) {
            indiceSuccessivo = successivi.get("true");
        } else {
            indiceSuccessivo = successivi.get("false");
        }
        Scenario daTornare = scenariNelSito.get(indiceSuccessivo);
        temp.setScenarioAttuale(daTornare);
        partiteNelSito.put(id, temp);
        convertToJsonPartite();
        return temp;
    }

    public Partita caricaSuccessivoScelta(Partita partita, String scelta) {
        Partita temp = partita;
        String id = temp.getId();
        ScenarioAScelta attuale = (ScenarioAScelta) temp.getScenarioAttuale();
        HashMap<String, String> successivi = attuale.getSuccessivo();
        String indiceSuccessivo = successivi.get(scelta);
        Scenario daTornare = scenariNelSito.get(indiceSuccessivo);
        temp.setScenarioAttuale(daTornare);
        partiteNelSito.put(id, temp);
        convertToJsonPartite();
        return temp;
    }

    private void convertToJsonPartite() {
        if (db == null || db.isClosed()) {
            openDB();
        }

        try (PrintWriter pW = new PrintWriter(new FileWriter("partiteNelSito.json"))) {
            pW.println("{");

            boolean firstEntry = true;
            for (Map.Entry<String, Partita> entry : partiteNelSito.entrySet()) {
                Partita p = entry.getValue();

                if (!firstEntry) {
                    pW.println(",");
                }
                pW.println("  \"" + p.getId() + "\": {");
                pW.println("    \"Giocatore\": \"" + p.getGiocatore().getUsername() + "\",");
                pW.println("    \"Storia\": \"" + p.getStoria().getNome() + "\",");
                pW.println("    \"Id scenario attuale\": \"" + p.getScenarioAttuale().getValId() + "\",");
                pW.println("    \"Inventario\": \"" + p.getInventario() + "\"");
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

    public void logoutUtenti() {
        for (Map.Entry<String, Utente> entry : utentiNelSito.entrySet()) {
            Utente u = entry.getValue();
            u.setIsLogged(false);
            String key = entry.getKey();
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