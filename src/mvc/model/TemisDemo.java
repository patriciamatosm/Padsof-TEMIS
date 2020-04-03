package mvc.model;

import java.time.LocalDate;

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
                1001, pTemis.getUsuarioConectado(), "prueba", true);

        pTemis.anadirProyecto(p);
        pTemis.getProyectos().get("Proyecto1").aceptarProyecto();
        System.out.println("Proyecto creado: " + pTemis.getProyectos().get("Proyecto1"));

        if (!pTemis.registrarse("00000000C", "John", "12345")){
            System.out.println("Error: No se ha podido registrar el usuario");
            return;
        }

        pTemis.getUsuarios().get("00000000C").aceptarRegistro();

        /* Apoyar proyectp */
        System.out.println("Apoyando proyecto como " + pTemis.getUsuarioConectado().getNombre() + "...");
        pTemis.cierraSesion();


        if (!pTemis.iniciaSesion("00000000C","12345")){
            System.out.println("Error: No se ha podido loggear el usuario con dni");
            return;
        }

        if(!pTemis.getProyectos().get("Proyecto1").votar(pTemis.getUsuarioConectado())){
            System.out.println("Error: No se ha podido votar");
            return;
        }


        System.out.println("Numero de votantes de Proyecto1: " + pTemis.getProyectos().get("Proyecto1").getNumVotos());

        pTemis.cierraSesion();

        if (!pTemis.iniciaSesion("Jane","12345")){
            System.out.println("Error: No se ha podido loggear el usuario con nombre");
            return;
        }

        /*Proyecto caducado*/

        System.out.println("El proyecto no ha sido votado en 30 dias...");
        pTemis.getProyectos().get("Proyecto1").setFechaUltimoVoto(LocalDate.of(2020, 2, 29));
        pTemis.getProyectos().get("Proyecto1").caducado();

        System.out.println("Estado del proyecto luego de 30 dias: " +
                pTemis.getProyectos().get("Proyecto1").getEstado().name());


        pTemis.getProyectos().get("Proyecto1").setFechaUltimoVoto(LocalDate.now());
        pTemis.getProyectos().get("Proyecto1").setEstado(Proyecto.Estado.ACTIVO);

        /*Financiacion*/

        System.out.println("Pediremos financiacion sin tener el numero de votos...");
        pTemis.getProyectos().get("Proyecto1").pedirFinanciacion();
        System.out.println("Estado del proyecto: " + pTemis.getProyectos().get("Proyecto1").getEstado());

        System.out.println("Pediremos financiacion teniendo el numero de votos...");
        int votosAntes = pTemis.getProyectos().get("Proyecto1").getNumVotos();
        pTemis.getProyectos().get("Proyecto1").setMinVotos(10);
        pTemis.getProyectos().get("Proyecto1").setNumVotos(pTemis.getProyectos().get("Proyecto1").getMinVotos());
        pTemis.getProyectos().get("Proyecto1").pedirFinanciacion();
        Thread.sleep(1000);
        System.out.println("Estado del proyecto: " + pTemis.getProyectos().get("Proyecto1").getEstado());
        pTemis.getProyectos().get("Proyecto1").setNumVotos(votosAntes);

        System.out.println("Damos financiacion...");
        Double importe;
        importe = pTemis.getProyectos().get("Proyecto1").financiacion();
        if(importe == null){
            System.out.println("Proyecto sigue pendiente");
        } else if(importe == 0){
            System.out.println("Proyecto no financiado");
        } else {
            System.out.println("Proyecto financiado con " + importe.toString());
        }
        System.out.println("Estado del proyecto: " + pTemis.getProyectos().get("Proyecto1").getEstado());



        System.out.println("\n");
        /* Crear colectivo */

         System.out.println("Creando un colectivo...");

         Colectivo c = new Colectivo("descripcion del colectivo", "Colectivo1",
                 pTemis.getUsuarioConectado());

         pTemis.anadirColectivo(c);
         System.out.println("Colectivo creado: " + pTemis.getColectivos().get("Colectivo1"));

         /* Creamos un usuario para que se una al colectivo */

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

        pTemis.cierraSesion();

        /* creamos más usuarios para comprobar funcionalidad de votar */

        if (!pTemis.registrarse("00000000D", "Eric", "12345")){
            System.out.println("Error: No se ha podido registrar el usuario");
            return;
        }
        pTemis.getUsuarios().get("00000000D").aceptarRegistro();
        if (!pTemis.iniciaSesion("00000000D","12345")){
            System.out.println("Error: No se ha podido loggear el usuario con dni");
            return;
        }

        if (!pTemis.getColectivos().get("Colectivo1").unirse(pTemis.getUsuarioConectado())){
            System.out.println("Error: No se ha podido unir al colectivo");
            return;
        }

        pTemis.cierraSesion();

        if (!pTemis.registrarse("00000000E", "Javier", "12345")){
            System.out.println("Error: No se ha podido registrar el usuario");
            return;
        }
        pTemis.getUsuarios().get("00000000E").aceptarRegistro();

        if (!pTemis.iniciaSesion("00000000E","12345")){
            System.out.println("Error: No se ha podido loggear el usuario con dni");
            return;
        }

        if (!pTemis.getColectivos().get("Colectivo1").unirse(pTemis.getUsuarioConectado())){
            System.out.println("Error: No se ha podido unir al colectivo");
            return;
        }

        pTemis.cierraSesion();

        if (!pTemis.iniciaSesion("00000000C","12345")){
            System.out.println("Error: No se ha podido loggear el usuario con dni");
            return;
        }

        /* votamos como colectivo */

        System.out.println("Votamos proyecto como colectivo... Numero de votos actual: " +
                pTemis.getProyectos().get("Proyecto1").getNumVotos());

        System.out.println("Usuarios que pertenecen al colectivo: " +
                pTemis.getColectivos().get("Colectivo1").getListaUsuario());

        if(!pTemis.getProyectos().get("Proyecto1").votarUsuarios(
                pTemis.getColectivos().get("Colectivo1").getListaUsuario())){
            System.out.println("Error: No se ha podido votar");
            return;
        }

        System.out.println("Numero de votantes de Proyecto1 despues de votar: " +
                pTemis.getProyectos().get("Proyecto1").getNumVotos());


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


        System.out.println("\n");
        /* Afinidad y popularidad */
        Colectivo c2 = new Colectivo("descripcion del colectivo", "Colectivo2",
                pTemis.getUsuarioConectado());

        pTemis.anadirColectivo(c2);
        System.out.println("Colectivo creado: " + pTemis.getColectivos().get("Colectivo2"));

        pTemis.cierraSesion();

        if (!pTemis.iniciaSesion("00000000E","12345")){
            System.out.println("Error: No se ha podido loggear el usuario con dni");
            return;
        }

        if (!pTemis.getColectivos().get("Colectivo2").unirse(pTemis.getUsuarioConectado())){
            System.out.println("Error: No se ha podido unir al colectivo");
            return;
        }


        pTemis.getColectivos().get("Colectivo1").calcularAfinidad(c2);
        System.out.println("Colectivos 1 y 2 tienen un " + pTemis.getColectivos().get("Colectivo1").calcularAfinidad(c2)
                + "% de afinidad\n");


        pTemis.cierraSesion();
        pTemis.getUsuarios().remove("00000000B");
        pTemis.getUsuarios().remove("00000000C");
        pTemis.getUsuarios().remove("00000000D");
        pTemis.getUsuarios().remove("00000000E");
        pTemis.getColectivos().remove("Colectivo1");
        pTemis.getColectivos().remove("Colectivo2");
        pTemis.getColectivos().remove("Proyecto1");
        pTemis.escribirFichero();

    }
}
