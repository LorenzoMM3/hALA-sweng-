import static org.junit.Assert.*;

import java.util.ArrayList;

import org.junit.Before;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runners.MethodSorters;

import com.hala.mywebapp.GreetingServiceImpl;
import com.hala.mywebapp.Scenario;
import com.hala.mywebapp.ScenarioAScelta;
import com.hala.mywebapp.ScenarioIndovinello;
import com.hala.mywebapp.Storia;
import com.hala.mywebapp.Utente;
import com.hala.mywebapp.Partita;

@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class GreetingServiceImplTest {

    private GreetingServiceImpl greetingService;

    @Before
    public void setUp() {
        greetingService = new GreetingServiceImpl();

    }

    @Test
    public void ainizializzazione() {
        chiudi();
        greetingService.initData();
        chiudi();
    }

    @Test
    public void btestUtente() {
        chiudi();

        // Test Registrazione

        Utente utente1 = new Utente("Utente1", "Password1");
        Utente utente2 = new Utente("Utente2", "Password2");
        Utente utente3 = new Utente("Utente3", "Password3");

        // Verifico la registrazione
        greetingService.signIn(utente1);
        greetingService.signIn(utente2);
        greetingService.signIn(utente3);

        assertEquals(false, greetingService.signIn(utente1));
        assertEquals(false, greetingService.signIn(utente2));
        assertEquals(false, greetingService.signIn(utente3));

        assertEquals(false, utente1.getIsLogged());
        assertEquals(false, utente2.getIsLogged());
        assertEquals(false, utente3.getIsLogged());

        // Verifico il login
        assertEquals(true, greetingService.logIn(utente1));
        assertEquals(true, greetingService.logIn(utente2));
        assertEquals(true, greetingService.logIn(utente3));

        // verifico isLogged
        assertEquals(true, utente1.getIsLogged());
        assertEquals(true, utente2.getIsLogged());
        assertEquals(true, utente3.getIsLogged());

        // Verifico logout
        assertEquals(true, greetingService.logOut(utente1));
        assertEquals(true, greetingService.logOut(utente2));
        assertEquals(true, greetingService.logOut(utente3));

        // verifico isLogged
        assertEquals(false, utente1.getIsLogged());
        assertEquals(false, utente2.getIsLogged());
        assertEquals(false, utente3.getIsLogged());

        chiudi();
    }

    @Test
    public void ctestStoria() {
        chiudi();
        Utente utente4 = new Utente("Utente4", "Password4");
        Storia storia1 = new Storia("storia1", utente4);
        greetingService.creaNuovaStoria(storia1);
        ArrayList<Storia> storie = greetingService.ottieniStorie();
        ArrayList<String> nomiStorie = new ArrayList<String>();
        for (Storia storia : storie) {
            nomiStorie.add(storia.getNome());
        }
        assertEquals(true, nomiStorie.contains(storia1.getNome()));
        assertEquals("storia1", storia1.getNome());
        assertEquals("Utente4", storia1.getUtente().getUsername());

        chiudi();
    }

    @Test
    public void dtestScenari() {
        chiudi();
        // scenario1 a scelta
        Scenario scenario1 = new ScenarioAScelta("storia1");
        scenario1.setTestoScena("Testo dello scenario a scelta storia1");
        scenario1.setDomandaCambioScenario("Domanda dello scenario a scelta storia1");
        ArrayList<String> opzioni = new ArrayList<String>();
        opzioni.add("Opzione1;Oggetto1");
        opzioni.add("Opzione2;Oggetto2");
        ((ScenarioAScelta) scenario1).setOpzioniScelte(opzioni);
        scenario1.addOggettoCheSblocca("Oggetto1");
        scenario1.addOggettoCheSblocca("Oggetto2");
        String idScenario1 = greetingService.prossimoId();
        scenario1.setValId(idScenario1);
        greetingService.aggiungiScenarioAScelta(idScenario1, scenario1);

        // scenario2 finale
        Scenario scenario2 = new Scenario("storia1");
        scenario2.setTestoScena("Testo dello scenario finale storia1");
        String idScenario2 = greetingService.prossimoId();
        scenario2.setValId(idScenario2);
        greetingService.aggiungiScenarioFinale(idScenario2, scenario2);

        ArrayList<Scenario> scenari = new ArrayList<>();

        // scenario3 indovinello
        Scenario scenario3 = new ScenarioIndovinello("storia1");
        scenario3.setTestoScena("Testo dello scenario indovinello storia1");
        scenario3.setDomandaCambioScenario("Domanda dello scenario indovinello  storia1");
        scenario3.addOggettoCheSblocca("Oggetto3");
        ((ScenarioIndovinello) scenario3).setRispostaIndovinello("rc");
        String idScenario3 = greetingService.prossimoId();
        scenario3.setValId(idScenario3);
        greetingService.aggiungiScenarioIndovinello(idScenario3, scenario3);

        // collegamenti
        greetingService.settaScenarioIniziale(scenario1);
        greetingService.settaCollegamentoSuccessivo(scenario1, "Opzione1", scenario2);
        greetingService.settaCollegamentoSuccessivo(scenario1, "Opzione2", scenario3);
        greetingService.settaCollegamentoSuccessivo(scenario3, "true", scenario2);

        scenari.add(scenario1);
        scenari.add(scenario2);
        scenari.add(scenario3);

        ArrayList<Scenario> scenariCollegati = greetingService.ottieniScenariStoria("storia1");

        // controllo che siano collegati tutti
        assertEquals(true, greetingService.controlloCollegamenti(scenariCollegati));
        // salvo su file
        assertEquals(true, greetingService.salvaSuFileScenari("storia1"));

        chiudi();
    }

    @Test
    public void etestPartita() {

        chiudi();
        creaStoria();

        Utente utente5 = new Utente("Utente5", "Password5");

        Storia storia = greetingService.ottieniStoria("storia3");

        Partita partita = greetingService.caricaPartita(storia, utente5, false);

        Partita dp = greetingService.datiPartita(storia.getNome(), utente5.getUsername());
        assertEquals("storia3", dp.getStoria().getNome());
        assertEquals("Utente5", dp.getGiocatore().getUsername());

        // gioca

        partita.addInventario("Oggetto1");
        String oggetto = "Oggetto1";
        assertEquals(true, partita.controllaOggetto(oggetto));
        partita = greetingService.caricaSuccessivoScelta(partita, "Opzione1");
        Scenario scenarioAttuale = partita.getScenarioAttuale();
        assertEquals("Testo dello scenario finale storia3", scenarioAttuale.getTestoScena());

        // elimina partita
        greetingService.eliminaPartita(storia, utente5);
        assertEquals(null, greetingService.datiPartita(storia.getNome(), utente5.getUsername()));

        // Utente ricomincia a giocare
        Partita partita2 = greetingService.caricaPartita(storia, utente5, false);
        assertEquals("storia3", partita2.getStoria().getNome());
        assertEquals("Utente5", partita2.getGiocatore().getUsername());
        assertEquals("Testo dello scenario a scelta storia3", partita2.getScenarioAttuale().getTestoScena());

        partita2.addInventario("Oggetto2");
        partita2 = greetingService.caricaSuccessivoScelta(partita2, "Opzione2");

        // Utente torna alla homepage ed esce dalla partita
        // Ricomincia a giocare
        partita2 = greetingService.caricaPartita(storia, utente5, false);
        assertEquals("Testo dello scenario indovinello storia3", partita2.getScenarioAttuale().getTestoScena());

        greetingService.caricaSuccessivoIndovinello(partita2, "rc");
        assertEquals("Testo dello scenario finale storia3", partita2.getScenarioAttuale().getTestoScena());

        // chiusura
        chiudi();

    }

    private void creaStoria() {
        Utente utente6 = new Utente("Utente6", "Password6");
        Storia storia3 = new Storia("storia3", utente6);
        greetingService.creaNuovaStoria(storia3);
        ScenarioAScelta scenario1 = new ScenarioAScelta("storia3");
        scenario1.setTestoScena("Testo dello scenario a scelta storia3");
        scenario1.setDomandaCambioScenario("Domanda dello scenario a scelta storia3");
        ArrayList<String> opzioni = new ArrayList<String>();
        opzioni.add("Opzione1;Oggetto1");
        opzioni.add("Opzione2;Oggetto2");
        ((ScenarioAScelta) scenario1).setOpzioniScelte(opzioni);
        scenario1.addOggettoCheSblocca("Oggetto1");
        scenario1.addOggettoCheSblocca("Oggetto2");
        String idScenario1 = greetingService.prossimoId();
        scenario1.setValId(idScenario1);
        greetingService.aggiungiScenarioAScelta(idScenario1, scenario1);

        // scenario2 finale
        Scenario scenario2 = new Scenario("storia3");
        scenario2.setTestoScena("Testo dello scenario finale storia3");
        String idScenario2 = greetingService.prossimoId();
        scenario2.setValId(idScenario2);
        greetingService.aggiungiScenarioFinale(idScenario2, scenario2);

        ArrayList<Scenario> scenari = new ArrayList<>();

        // scenario3 indovinello
        ScenarioIndovinello scenario3 = new ScenarioIndovinello("storia3");
        scenario3.setTestoScena("Testo dello scenario indovinello storia3");
        scenario3.setDomandaCambioScenario("Domanda dello scenario indovinello storia3");
        scenario3.addOggettoCheSblocca("Oggetto3");
        ((ScenarioIndovinello) scenario3).setRispostaIndovinello("rc");
        String idScenario3 = greetingService.prossimoId();
        scenario3.setValId(idScenario3);
        greetingService.aggiungiScenarioIndovinello(idScenario3, scenario3);

        // collegamenti
        greetingService.settaScenarioIniziale(scenario1);
        greetingService.settaCollegamentoSuccessivo(scenario1, "Opzione1", scenario2);
        greetingService.settaCollegamentoSuccessivo(scenario1, "Opzione2", scenario3);
        greetingService.settaCollegamentoSuccessivo(scenario3, "true", scenario2);

        ArrayList<Scenario> scenariCollegati = greetingService.ottieniScenariStoria("storia3");

    }

    private void chiudi() {
        greetingService.closeDatabase();
    }
}