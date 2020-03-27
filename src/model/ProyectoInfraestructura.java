package model;

import java.time.LocalDate;

@SuppressWarnings("unused")
public class ProyectoInfraestructura extends Proyecto{
    private String distrito;
    private String urlCroquis;
    private String descripcionEspecifica;

    public ProyectoInfraestructura(String titulo, String descripcion, LocalDate fechaCreacion, Integer importe,
                                   Actor creador, String distrito, String urlCroquis, String descripcionEspecifica) {
        super(titulo, descripcion, fechaCreacion, importe, creador);
        this.distrito = distrito;
        this.urlCroquis = urlCroquis;
        this.descripcionEspecifica = descripcionEspecifica;
    }

}
