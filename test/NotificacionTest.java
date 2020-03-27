import model.*;
import org.junit.Test;

import java.time.LocalDate;

import static org.junit.Assert.*;

public class NotificacionTest {

    @Test
    public void enviarNotificacion() {
        Usuario u = new Usuario("Patricia", "00000000B", "12345");
        Colectivo c = new Colectivo("Test descripcion", "Test", u);
        Proyecto p = new ProyectoSocial("Test", "descripcion test", LocalDate.now(),
                10, u, "je", true);
        Notificacion n = new Notificacion(p, c, "Test");
        n.enviarNotificacion();

        assertTrue(c.getNotificacionesRecibidas().contains(n));
        assertEquals(c.getNotificacionesRecibidas().get(0).getMensaje(), "Test");
    }
}