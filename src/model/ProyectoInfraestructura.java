package model;

import java.io.Serializable;
import java.time.LocalDate;

@SuppressWarnings("unused")
public class ProyectoInfraestructura extends Proyecto implements Serializable {
    private String distrito;
    private String urlCroquis;
    private String descripcionEspecifica;

    public ProyectoInfraestructura(String titulo, String descripcion, String fechaCreacion, Integer minVotos, Integer importe,
                                   Actor creador, Estado estado, String distrito, String urlCroquis, String descripcionEspecifica) {
        super(titulo, descripcion, fechaCreacion, minVotos, importe, creador, estado);
        this.distrito = distrito;
        this.urlCroquis = urlCroquis;
        this.descripcionEspecifica = descripcionEspecifica;
    }

	public String getDistrito() {
		return distrito;
	}

	public void setDistrito(String distrito) {
		this.distrito = distrito;
	}

	public String getUrlCroquis() {
		return urlCroquis;
	}

	public void setUrlCroquis(String urlCroquis) {
		this.urlCroquis = urlCroquis;
	}

	public String getDescripcionEspecifica() {
		return descripcionEspecifica;
	}

	public void setDescripcionEspecifica(String descripcionEspecifica) {
		this.descripcionEspecifica = descripcionEspecifica;
	}

}
