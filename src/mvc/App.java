package mvc;

import mvc.controller.*;
import mvc.model.Proyecto;
import mvc.model.ProyectoSocial;
import mvc.model.Temis;
import mvc.view.*;

import java.io.IOException;

public class App {
    public static void main(String[] args) throws IOException {
        MiGUI gui = new MiGUI("Bienvenido!");
        Controller controlador = new Controller(gui);
        gui.setController(controlador);
        //Temis.getInstance().escribirFichero();

    }
}
