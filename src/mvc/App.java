package mvc;

import mvc.controller.*;
import mvc.view.*;

import java.io.IOException;

public class App {
    public static void main(String[] args) throws IOException {
        MiGUI gui = new MiGUI("Bienvenido!");
        Controller controlador = new Controller(gui);
         
    }
}
