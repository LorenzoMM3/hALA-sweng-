import static org.junit.Assert.*;
import org.junit.Before;
import org.junit.Test;

import com.hala.mywebapp.GreetingServiceImpl;
import com.hala.mywebapp.Storia;
import com.hala.mywebapp.Utente;
import com.hala.mywebapp.Scenario;
import com.hala.mywebapp.Partita;

public class GreetingServiceImplTest {

    private GreetingServiceImpl greetingService;

    @Before
    public void setUp() {
        greetingService = new GreetingServiceImpl();
    }

    @Test
    public void testGreet() {

        // Test Registrazione

        Utente utente1 = new Utente("Utente1", "Password1");
        Utente utente2 = new Utente("Utente2", "Password2");
        Utente utente3 = new Utente("Utente3", "Password3");

        // Verifico la registrazione
        greetingService.signIn(utente1);
        greetingService.signIn(utente2);
        greetingService.signIn(utente3);

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
        greetingService.logOut(utente1);
        greetingService.logOut(utente2);
        greetingService.logOut(utente3);

        // verifico isLogged
        assertEquals(false, utente1.getIsLogged());
        assertEquals(false, utente2.getIsLogged());
        assertEquals(false, utente3.getIsLogged());

        // verifica creazione storia
        Storia storia1 = new Storia("prova2", utente1);
        greetingService.creaNuovaStoria(storia1);
        // assertEquals(true, greetingService.ottieniStorie().contains(storia1));
        assertEquals("prova2", storia1.getNome());
        assertEquals("Utente1", storia1.getUtente().getUsername());

        // verifica creazione scenario
        Scenario scenario1 = new Scenario(storia1.getNome());
        String id = greetingService.prossimoId();
        greetingService.aggiungiScenarioAScelta(id, scenario1);

        Scenario scenario2 = new Scenario(storia1.getNome());
        id = greetingService.prossimoId();
        greetingService.aggiungiScenarioIndovinello(id, scenario2);

        Scenario scenario3 = new Scenario(storia1.getNome());
        id = greetingService.prossimoId();
        greetingService.aggiungiScenarioFinale(id, scenario3);

    }

}