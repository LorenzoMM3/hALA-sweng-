import static org.junit.Assert.*;
import org.junit.Before;
import org.junit.Test;

import com.hala.mywebapp.GreetingServiceImpl;
import com.hala.mywebapp.Utente;

public class GreetingServiceImplTest {

    private GreetingServiceImpl greetingService;

    @Before
    public void setUp() {
        greetingService = new GreetingServiceImpl();
    }

    @Test
    public void testGreet() {
        Utente utenteDaRegistrare = new Utente("UtenteDaRegistrare", "Password");
        assertEquals("UtenteDaRegistrare", utenteDaRegistrare.getUsername());
        assertEquals("Password", utenteDaRegistrare.getPassword());
        assertEquals(false, utenteDaRegistrare.getIsLogged());
        //Verifico la registrazione
        greetingService.signIn(utenteDaRegistrare);
        assertEquals(false, utenteDaRegistrare.getIsLogged());
        Utente attuale = greetingService.utenteAttuale; 
        assertEquals(null, attuale);
        //Verifico il login
        greetingService.logIn(utenteDaRegistrare);
        Utente loggato = greetingService.utenteAttuale;
        assertEquals("UtenteDaRegistrare", loggato.getUsername());
        assertEquals("Password", loggato.getPassword());
        assertEquals(true, loggato.getIsLogged());
    }

}