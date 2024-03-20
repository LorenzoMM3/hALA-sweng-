import static org.junit.Assert.*;

import java.util.ArrayList;

import org.junit.Before;
import org.junit.Test;
import org.mapdb.DBMaker;

import com.hala.mywebapp.GreetingServiceImpl;
import com.hala.mywebapp.Scenario;
import com.hala.mywebapp.ScenarioAScelta;
import com.hala.mywebapp.ScenarioIndovinello;
import com.hala.mywebapp.Storia;
import com.hala.mywebapp.Utente;
import org.mapdb.DBMaker;
import org.mapdb.DBMaker;

public class GreetingServiceImplTest {

    private GreetingServiceImpl greetingService;

    @Before
    public void setUp() {
        greetingService = new GreetingServiceImpl();

    }

    @Test
    public void inizializzazione() {
        chiudi();
        greetingService.initData();
        chiudi();
    }

    @Test
    public void testUtente() {
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
    public void testStoria() {
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
    public void testScenari() {
        chiudi();
        Scenario scenario1 = new ScenarioAScelta("storia1");
        scenario1.setTestoScena("Testo dello scenario a scelta");
        scenario1.setDomandaCambioScenario("Domanda dello scenario a scelta");
        ArrayList<String> opzioni = new ArrayList<String>();
        opzioni.add("Opzione1;Oggetto1");
        opzioni.add("Opzione2;Oggetto2");
        ((ScenarioAScelta) scenario1).setOpzioniScelte(opzioni);
        scenario1.addOggettoCheSblocca("Oggetto1");
        scenario1.addOggettoCheSblocca("Oggetto2");
        String idScenario1 = greetingService.prossimoId();
        scenario1.setValId(idScenario1);
        greetingService.aggiungiScenarioAScelta(idScenario1, scenario1);

        Scenario scenario2 = new Scenario("storia1");
        scenario2.setTestoScena("Testo dello scenario finale");
        String idScenario2 = greetingService.prossimoId();
        scenario2.setValId(idScenario2);
        greetingService.aggiungiScenarioFinale(idScenario2, scenario2);

        ArrayList<Scenario> scenari = new ArrayList<>();
        scenari.add(scenario1);
        scenari.add(scenario2);

        Scenario scenario3 = new ScenarioIndovinello("storia1");
        scenario3.setTestoScena("Testo dello scenario indovinello");
        scenario3.setDomandaCambioScenario("Domanda dello scenario indovinello");
        scenario3.addOggettoCheSblocca("Oggetto3");
        ((ScenarioIndovinello) scenario3).setRispostaIndovinello("rc");
        String idScenario3 = greetingService.prossimoId();
        scenario3.setValId(idScenario3);
        greetingService.aggiungiScenarioIndovinello(idScenario3, scenario3);

        greetingService.settaScenarioIniziale(scenario1);
        greetingService.settaCollegamentoSuccessivo(scenario1, "Opzione1", scenario2);
        greetingService.settaCollegamentoSuccessivo(scenario1, "Opzione2", scenario3);
        greetingService.settaCollegamentoSuccessivo(scenario3, "rc", scenario2);
        assertEquals(true, greetingService.controlloCollegamenti(scenari));

        chiudi();
    }

    private void chiudi() {
        greetingService.closeDatabase();
    }
}