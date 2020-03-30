package model;

import java.io.Serializable;
import java.time.LocalDate;

import model.Proyecto.Estado;

@SuppressWarnings("unused")
public class ProyectoSocial extends Proyecto implements Serializable {
    private String grupoEtnico;
    private boolean nacional;

    public ProyectoSocial(String titulo, String descripcion, String fechaCreacion, Integer minVotos, Integer importe,
            Actor creador, Estado estado, String grupoEtnico, boolean nacional) {
        super(titulo, descripcion, fechaCreacion, minVotos, importe, creador, estado);
        this.grupoEtnico = grupoEtnico;
        this.nacional = nacional;
    }

	public String getGrupoEtnico() {
		return grupoEtnico;
	}

	public void setGrupoEtnico(String grupoEtnico) {
		this.grupoEtnico = grupoEtnico;
	}

	public boolean isNacional() {
		return nacional;
	}

	public void setNacional(boolean nacional) {
		this.nacional = nacional;
	}
}
