import mvc.model.*;
import org.junit.Test;

import static org.junit.Assert.*;

public class NotificacionTest {

    @Test
    public void enviarNotificacion() {
        try {
            Usuario u = new Usuario("Patricia", "00000000B", "12345");
            Colectivo c = new Colectivo("Test descripcion", "Test", u);
            Proyecto p = new ProyectoSocial("Test", "descripcion test", 10, u, "je", true);
            Notificacion n = new Notificacion(p, c, "Test");
            n.enviarNotificacion();

            assertTrue(c.getNotificacionesRecibidas().contains(n));
            assertEquals(c.getNotificacionesRecibidas().get(0).getMensaje(), "Test");
        }
        catch (Exception e){
            System.out.println(e.getMessage());
        }
    }
}
