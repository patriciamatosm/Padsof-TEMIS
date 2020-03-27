
import org.junit.Test;
import model.*;
import static org.junit.Assert.*;


public class UsuarioTest {

    @Test
    public void addSuscritoNoticias() {
        Usuario u = new Usuario("Patricia", "00000000B", "12345");
        Colectivo c = new Colectivo("Test descripcion", "Test", u);

        u.addSuscritoNoticias(c);
        assertTrue(u.getSuscritoNoticias().contains(c));

        u = null;
        c = null;
    }

    @Test
    public void setLogueado() {
        Usuario u = new Usuario("Patricia", "00000000B", "12345");
        assertFalse(u.isLogueado());
        u.setLogueado(true);
        assertTrue(u.isLogueado());
        u = null;
    }

    @Test
    public void bloquearUsuario() {
        Usuario u = new Usuario("Patricia", "00000000B", "12345");
        u.bloquearUsuario();
        assertEquals(u.getEstado(), Usuario.EstadoUsuario.BLOQUEADO);
        u = null;
    }

    @Test
    public void desbloquearUsuario() {
        Usuario u = new Usuario("Patricia", "00000000B", "12345");
        u.bloquearUsuario();
        assertEquals(u.getEstado(), Usuario.EstadoUsuario.BLOQUEADO);
        u.desbloquearUsuario();
        assertEquals(u.getEstado(), Usuario.EstadoUsuario.ACTIVO);
        u = null;
    }

    @Test
    public void aceptarRegistro() {
        Usuario u = new Usuario("Patricia", "00000000B", "12345");
        u.aceptarRegistro();
        assertEquals(u.getEstado(), Usuario.EstadoUsuario.ACTIVO);
        u = null;
    }
}
