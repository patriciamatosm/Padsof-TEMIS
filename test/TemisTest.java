
import mvc.model.*;
import org.junit.Test;


import java.io.IOException;

import static org.junit.jupiter.api.Assertions.*;


public class TemisTest {

    @Test
    public void testGetInstance() throws IOException {

        Temis pTemis;
        pTemis = Temis.getInstance();
        assertNotNull(pTemis);


        /*Probaremos ahora escribir en un archivo */
        Usuario tester = new Usuario("Patricia", "00000000B", "12345");
        pTemis.getUsuarios().put(tester.getDni(), tester);
        pTemis.escribirFichero();

        pTemis = Temis.getInstance();
        assertNotNull(pTemis);
        pTemis.leerFichero();
        assertEquals(tester.getDni(), pTemis.getUsuarios().get(tester.getDni()).getDni());
        pTemis.getUsuarios().remove(tester.getDni());
        pTemis.escribirFichero();
    }

    @Test
    public void testregistrarse1() throws IOException {

        Temis pTemis;
        pTemis = Temis.getInstance();

        assertTrue(pTemis.registrarse("00000000B", "Patricia", "12345"));
        assertTrue(pTemis.getUsuarios().containsKey("00000000B"));

        pTemis.getUsuarios().remove("00000000B");
        pTemis.escribirFichero();
    }

    @Test
    public void testregistrarse2() throws IOException {

        Temis pTemis;
        pTemis = Temis.getInstance();

        assertTrue(pTemis.registrarse("00000000B", "Patricia", "12345"));
        assertTrue(pTemis.getUsuarios().containsKey("00000000B"));
        /*comprobamos que falle*/
        assertFalse(pTemis.registrarse("00000000B", "Patricia", "12345"));

        pTemis.getUsuarios().remove("00000000B");
        pTemis.escribirFichero();
    }


    @Test
    public void testiniciaSesion1() throws IOException {
        Temis pTemis;
        pTemis = Temis.getInstance();

        pTemis.registrarse("00000000B", "Patricia", "12345");
        assertTrue(pTemis.iniciaSesion("00000000B", "12345"));
        assertTrue(pTemis.getUsuarios().get("00000000B").isLogueado());
        assertSame(pTemis.getUsuarioConectado(), pTemis.getUsuarios().get("00000000B"));

        pTemis.getUsuarios().remove("00000000B");
        pTemis.escribirFichero();
    }

    @Test
    public void testiniciaSesion2() throws IOException {
        Temis pTemis;
        pTemis = Temis.getInstance();

        pTemis.registrarse("00000000B", "Patricia", "12345");
        assertTrue(pTemis.iniciaSesion("Patricia", "12345"));
        assertTrue(pTemis.getUsuarios().get("00000000B").isLogueado());
        assertSame(pTemis.getUsuarioConectado(), pTemis.getUsuarios().get("00000000B"));

        pTemis.getUsuarios().remove("00000000B");
        pTemis.escribirFichero();
    }

    @Test
    public void testcierraSesion() throws IOException {
        Temis pTemis;
        pTemis = Temis.getInstance();

        pTemis.registrarse("00000000B", "Patricia", "12345");
        pTemis.iniciaSesion("Patricia", "12345");

        pTemis.cierraSesion();
        assertSame(pTemis.getUsuarioConectado(), null);

        pTemis.getUsuarios().remove("00000000B");
        pTemis.escribirFichero();
    }

    @Test
    public void testanadirProyecto() throws IOException {
        Temis pTemis;
        pTemis = Temis.getInstance();
        pTemis.registrarse("00000000B", "Patricia", "12345");

        Proyecto p = new ProyectoSocial("Test", "descripcion test",
                10, pTemis.getUsuarios().get("00000000B"), "je", true);

        pTemis.anadirProyecto(p);
        assertTrue(pTemis.getProyectos().containsKey("Test"));

        pTemis.getProyectos().remove("Test");
        pTemis.getUsuarios().remove("00000000B");
        pTemis.escribirFichero();
    }

    @Test
    public void testanadirColectivo() throws IOException {
        Temis pTemis;
        pTemis = Temis.getInstance();
        pTemis.registrarse("00000000B", "Patricia", "12345");

        Colectivo c = null;
        try {
            c = new Colectivo("Test descripcion", "Test",
                    pTemis.getUsuarios().get("00000000B"));
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }

        pTemis.anadirColectivo(c);
        assertTrue(pTemis.getColectivos().containsKey("Test"));

        pTemis.getColectivos().remove("Test");
        pTemis.getUsuarios().remove("00000000B");
        pTemis.escribirFichero();
    }
}
