package model;

import java.time.LocalDate;

@SuppressWarnings("unused")
public class ProyectoSocial extends Proyecto {
    private String grupoEtnico;
    private boolean nacional;

    public ProyectoSocial(String titulo, String descripcion, LocalDate fechaCreacion, Integer importe, Actor creador,
                          String grupoEtnico, boolean nacional) {
        super(titulo, descripcion, fechaCreacion, importe, creador);
        this.grupoEtnico = grupoEtnico;
        this.nacional = nacional;
    }
}
