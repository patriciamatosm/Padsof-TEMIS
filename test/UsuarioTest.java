
import mvc.model.Colectivo;
import mvc.model.Usuario;
import org.junit.Test;

import static org.junit.Assert.*;

@SuppressWarnings("unused")
public class UsuarioTest {

    @Test
    public void addSuscritoNoticias() {
        try {
            Usuario u = new Usuario("Patricia", "00000000B", "12345");
            Colectivo c = new Colectivo("Test descripcion", "Test", u);

            u.addSuscritoNoticias(c);
            assertTrue(u.getSuscritoNoticias().contains(c));
        }
        catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    @Test
    public void setLogueado() {
        Usuario u = new Usuario("Patricia", "00000000B", "12345");
        assertFalse(u.isLogueado());
        u.setLogueado(true);
        assertTrue(u.isLogueado());
    }

    @Test
    public void bloquearUsuario() {
        Usuario u = new Usuario("Patricia", "00000000B", "12345");
        u.bloquearUsuario();
        assertEquals(u.getEstado(), Usuario.EstadoUsuario.BLOQUEADO);
    }

    @Test
    public void desbloquearUsuario() {
        Usuario u = new Usuario("Patricia", "00000000B", "12345");
        u.bloquearUsuario();
        assertEquals(u.getEstado(), Usuario.EstadoUsuario.BLOQUEADO);
        u.desbloquearUsuario();
        assertEquals(u.getEstado(), Usuario.EstadoUsuario.ACTIVO);
    }

    @Test
    public void aceptarRegistro() {
        Usuario u = new Usuario("Patricia", "00000000B", "12345");
        u.aceptarRegistro();
        assertEquals(u.getEstado(), Usuario.EstadoUsuario.ACTIVO);
    }

    @Test
    public void addColectivosPropios() {
        try {
            Usuario u = new Usuario("Patricia", "00000000B", "12345");
            Colectivo c = new Colectivo("Test descripcion", "Test", u);

            u.addColectivosPropios(c);
            assertTrue(u.getColectivosPropios().contains(c));
        }
        catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }
}
