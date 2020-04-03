package mvc.model;

import java.io.Serializable;

/**
 * Clase que define un Proyecto Social
 *
 * @author Daniel Prieto Fernández
 *         Silvia Tomey Prieto
 *         Patricia Matos Meza
 * @version 6/03/2020
 */

@SuppressWarnings("unused")
public class ProyectoSocial extends Proyecto implements Serializable {
    private String grupoEtnico;
    private boolean nacional;

    /**
     * Constructor de la clase ProyectoSocial
     *
     * @param titulo         			nombre del proyecto
     * @param descripcion    			descripcion del proyecto
     * @param importe        			importe destinado al proyecto
     * @param creador        			usuario creador del proyecto
     * @param grupoEtnico				grupo al que va orientado el proyecto
     * @param nacional					determina si el proyecto es a nivel nacional o no
     */
    
    public ProyectoSocial(String titulo, String descripcion, Integer importe,
            Actor creador, String grupoEtnico, boolean nacional) {
        super(titulo, descripcion, importe, creador);
        this.grupoEtnico = grupoEtnico;
        this.nacional = nacional;
    }

    /**
     * Funcion para pedir el grupo al que va dirigido el proyecto
     * @return grupo al que va dirigido el proyecto
     */
	public String getGrupoEtnico() {
		return grupoEtnico;
	}

	/**
	 * Funcio para establcer el grupo al que va dirigido el proyecto
	 * @param grupoEtnico al que va dirigido el proyecto
	 */
	public void setGrupoEtnico(String grupoEtnico) {
		this.grupoEtnico = grupoEtnico;
	}

	/**
	 * Funcion para preguntar si el proyecto es o no a nivel nacional
	 * @return true para nacional, false en caso contrario
	 */
	public boolean isNacional() {
		return nacional;
	}

	/**
	 * Funcion para establecer si el proyecto es nacional o no
	 * @param nacional (true o false)
	 */
	public void setNacional(boolean nacional) {
		this.nacional = nacional;
	}
	
    /**
     * Funcion para pedir el tipo de proyecto
     * @return tipo de proyecto
     */
	public ProjectKind getProjectKind() {
		return ProjectKind.Social;
	}
	
	/**
	 * Funcion para obtener la informacion extra del proyecto
	 * @return informacion extra del proyecto
	 */
	public String getExtraData() {
		return "Grupo Etnico: " + grupoEtnico + ", nacional: " + nacional;
	}
}
