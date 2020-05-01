import mvc.model.Colectivo;
import mvc.model.Temis;
import mvc.model.Usuario;
import org.junit.Before;
import org.junit.Test;

import java.util.Arrays;

import static org.junit.Assert.*;
import static org.junit.jupiter.api.Assertions.assertNotNull;

@SuppressWarnings("unused")
public class ColectivoTest {

    private Colectivo c;
    private Usuario u;
    private Usuario extra;
    Temis pTemis;

    @Before
    public void setUp () {
        try {
            pTemis = Temis.getInstance();
            assertNotNull(pTemis);

            pTemis.registrarse("12345678A", "Daniel", "12345");
            pTemis.iniciaSesion("Daniel", "12345");

            this.u = pTemis.getUsuarioConectado();
            pTemis.cierraSesion();

            pTemis.registrarse("00000000B", "Extra", "12345");
            pTemis.iniciaSesion("Extra", "12345");

            this.extra = pTemis.getUsuarioConectado();

            this.c = new Colectivo("Test descripcion", "Test", u);
        }
        catch (Exception e){
            System.out.println(e.getMessage());
        }
    }

    @Test
    public void descripcionMas500 () {
        try {
            System.out.println("Ponemos una descripcion de 501 caracteres:");
            char[] descripcion = new char[501];
            Arrays.fill(descripcion, 'a');
            Colectivo c = new Colectivo(String.valueOf(descripcion), "Test", u);

        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    @Test
    public void descripcionMenos5() {
        try {
            System.out.println("Ponemos una descripcion de 2 caracteres:");
            Usuario u = new Usuario("Daniel", "12345678A", "12345");
            Colectivo c = new Colectivo("12", "Test", u);

        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    @Test
    public void unirRep() {
        //NO deberia poder unirse el representante
        assertFalse(this.c.unirse(u));
    }

    @Test
    public void unirse() {
        this.c.unirse(extra);
        assertTrue(c.getListaUsuario().contains(extra));
        //Que no se puedan unir usuarios repetidos
        assertFalse(c.unirse(extra));
        this.c.abandonar(extra);
    }

    @Test
    public void abandonar() {
        Usuario user = extra;
        assertFalse(c.abandonar(user));
        c.unirse(user);
        assertTrue(c.getListaUsuario().contains(user));
        c.abandonar(user);
        assertFalse(c.getListaUsuario().contains(user));
    }

    @Test
    public void subscribirseNoticias() {
        Usuario user = extra;
        //Si no esta en el colectivo no deberia dejarle subscribirse a noticias
        assertFalse(this.c.subscribirseNoticias(user));
        assertFalse(user.getSuscritoNoticias().contains(this.c));
        //Despues de unirse si
        this.c.unirse(user);
        assertTrue(c.getListaUsuario().contains(user));
        assertTrue(this.c.subscribirseNoticias(user));
        assertTrue(user.getSuscritoNoticias().contains(this.c));
    }

    @Test
    public void crearSubcolectivo() {
        try {
            Usuario u1 = extra;
            Usuario u2;

            assertTrue(c.crearSubcolectivo("Test1", "Description1"));
            assertNotNull(c.getSubcolectivos());
            assertEquals(c, c.getSubcolectivos().get(0).getPadre());
            c.unirse(u1);
            assertFalse(c.getSubcolectivos().get(0).unirse(u1));

            pTemis.cierraSesion();

            pTemis.registrarse("11111111B", "Extra2", "12345");
            pTemis.iniciaSesion("Extra2", "12345");

            u2 = pTemis.getUsuarioConectado();

            assertTrue(c.getSubcolectivos().get(0).unirse(u2));
            c.crearSubcolectivo("Test2", "Description2");
            assertNotNull(c.getSubcolectivos().get(1));
            assertEquals(c.getSubcolectivos().get(0).getPadre(), c.getSubcolectivos().get(1).getPadre());

            Temis pTemis = Temis.getInstance();

            pTemis.getColectivos().remove("Test1");
            pTemis.escribirFichero();

        }
        catch (Exception e){
            System.out.println(e.getMessage());
        }
    }

    @Test
    public void afinidad() {
        try {
            Colectivo c1 = new Colectivo("Colectivo test 1", "Colectivo 1", u);
            Colectivo c2 = new Colectivo("Colectivo test 2", "Colectivo 2", u);

            Usuario u1 = extra;
            Usuario u2;

            assertEquals(0.0, c1.calcularAfinidad(c2), 0);

            c1.unirse(u1);

            assertEquals(0.0, c1.calcularAfinidad(c2), 0);
            assertEquals(0.0, c2.calcularAfinidad(c1), 0);

            c2.unirse(u1);

            assertEquals(100.0, c1.calcularAfinidad(c2), 0);
            assertEquals(100.0, c2.calcularAfinidad(c1), 0);

            pTemis.cierraSesion();

            pTemis.registrarse("11111111B", "Extra2", "12345");
            pTemis.iniciaSesion("Extra2", "12345");

            u2 = pTemis.getUsuarioConectado();

            c1.unirse(u2);

            assertEquals(100.0, c1.calcularAfinidad(c2), 0);
            assertEquals(50.0, c2.calcularAfinidad(c1), 0);

        }
        catch (Exception e){
            System.out.println(e.getMessage());
        }
    }
}
