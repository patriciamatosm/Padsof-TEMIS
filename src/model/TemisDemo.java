package model;

import java.io.IOException;

public class TemisDemo {
    public static void main(String[] args) throws Exception {
        Temis pTemis = null;

        pTemis = Temis.getInstance();
        pTemis.leerFichero();

        /* Nos registramos */
        System.out.println("Registrando a un usuario...");

        if (!pTemis.registrarse("00000000B", "Jane", "12345")){
            System.out.println("Error: No se ha podido registrar el usuario");
            return;
        }

        pTemis.getUsuarios().get("00000000B").aceptarRegistro();

        /* Iniciar sesion */

        System.out.println("Iniciando sesión con dni...");

        if (!pTemis.iniciaSesion("00000000B","12345")){
            System.out.println("Error: No se ha podido loggear el usuario con dni");
            return;
        }

        pTemis.cierraSesion();

        System.out.println("Iniciando sesión con nombre...");

        if (!pTemis.iniciaSesion("Jane","12345")){
            System.out.println("Error: No se ha podido loggear el usuario con nombre");
            return;
        }

        System.out.println("Usuario conectado: " + pTemis.getUsuarioConectado().toString());
        System.out.println("\n");







        /* Crear proyecto */

        System.out.println("Creando un proyecto social (funcionaria igual con el de infraestructura)...");

        Proyecto p = new ProyectoSocial("Proyecto1", "descripcion del proyecto1",
                10, pTemis.getUsuarioConectado(), "prueba", true);

        pTemis.anadirProyecto(p);
        System.out.println("Proyecto creado: " + pTemis.getProyectos().get("Proyecto1"));




























        System.out.println("\n");
        /* Crear colectivo */

         System.out.println("Creando un colectivo...");

         Colectivo c = new Colectivo("descripcion del colectivo", "Colectivo1",
                 pTemis.getUsuarioConectado());

         pTemis.anadirColectivo(c);
         System.out.println("Colectivo creado: " + pTemis.getColectivos().get("Colectivo1"));

         /* Creamos un usuario para que se una al colectivo */

        if (!pTemis.registrarse("00000000C", "John", "12345")){
            System.out.println("Error: No se ha podido registrar el usuario");
            return;
        }

        pTemis.getUsuarios().get("00000000C").aceptarRegistro();

        pTemis.cierraSesion();

        if (!pTemis.iniciaSesion("00000000C","12345")){
            System.out.println("Error: No se ha podido loggear el usuario con dni");
            return;
        }

        System.out.println("El usuario " +
                pTemis.getUsuarioConectado().getNombre() + " se une al colectivo anterior...");

        if (!pTemis.getColectivos().get("Colectivo1").unirse(pTemis.getUsuarioConectado())){
            System.out.println("Error: No se ha podido unir al colectivo");
            return;
        }

        System.out.println("Usuarios que pertenecen al colectivo: " +
                pTemis.getColectivos().get("Colectivo1").getListaUsuario());


        /* abandonamos colectivo*/

        System.out.println("El usuario " +
                pTemis.getUsuarioConectado().getNombre() + " abandona el colectivo anterior...");

        if (!pTemis.getColectivos().get("Colectivo1").abandonar(pTemis.getUsuarioConectado())){
            System.out.println("Error: No se ha podido abandonar colectivo");
            return;
        }

        System.out.println("Usuarios que pertenecen al colectivo: " +
                pTemis.getColectivos().get("Colectivo1").getListaUsuario());

        pTemis.cierraSesion();

        /* Creamos subcolectivo */

        if (!pTemis.iniciaSesion("Jane","12345")){
            System.out.println("Error: No se ha podido loggear el usuario con nombre");
            return;
        }

        System.out.println("Creamos subcolectivo al colectivo creado");

        if (!pTemis.getColectivos().get("Colectivo1").crearSubcolectivo("hijo1colectivo1",
                "hijo del Colectivo1")){
            System.out.println("Error: No se ha podido crear subcolectivo");
            return;
        }

        System.out.println("Subcolectivos de " + c.getNombre() + ": " +
                pTemis.getColectivos().get("Colectivo1").getSubcolectivos());





        pTemis.cierraSesion();
        pTemis.getUsuarios().remove("00000000B");
        pTemis.getUsuarios().remove("00000000C");
        pTemis.getColectivos().remove("Colectivo1");
        pTemis.getColectivos().remove("Proyecto1");
        pTemis.escribirFichero();

    }
}
