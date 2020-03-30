package model;

import java.io.Serializable;
import java.time.LocalDate;

/**
 * Clase que define un Proyecto de Infraestructura
 *
 * @author Daniel Prieto Fernández
 *         Silvia Tomey Prieto
 *         Patricia Matos Meza
 * @version 6/03/2020
 */

@SuppressWarnings("unused")
public class ProyectoInfraestructura extends Proyecto implements Serializable {
    private String distrito;
    private String urlCroquis;
    private String descripcionEspecifica;

    /**
     * Constructor de la clase ProyectoInfraestructura
     *
     * @param titulo         			nombre del proyecto
     * @param descripcion    			descripcion del proyecto
     * @param importe        			importe destinado al proyecto
     * @param creador        			usuario creador del proyecto
     * @param distrito					distrito en que se desarrolla el proyecto
     * @param urlCroquis				url que dirige al croquis del proyecto
     * @param descripcionEspecifica		descripcion concreta del proyecto
     */
    
    public ProyectoInfraestructura(String titulo, String descripcion, Integer importe,
                                   Actor creador, String distrito, String urlCroquis, String descripcionEspecifica) {
        super(titulo, descripcion, importe, creador);
        this.distrito = distrito;
        this.urlCroquis = urlCroquis;
        this.descripcionEspecifica = descripcionEspecifica;
    }

    /**
     * Funcion para pedir el distrito del proyecto
     * @return distrito del proyecto
     */
	public String getDistrito() {
		return distrito;
	}

	/**
	 * Funcion para establecer el dsitrito del proyecto
	 * @param distrito del proyecto
	 */
	public void setDistrito(String distrito) {
		this.distrito = distrito;
	}

	/**
	 * Funcion para pedir la url del croquis del proyecto
	 * @return url del croquis
	 */
	public String getUrlCroquis() {
		return urlCroquis;
	}

	/**
	 * Funcion para establecer la url del croquis del proyecto
	 * @param urlCroquis del proyecto
	 */
	public void setUrlCroquis(String urlCroquis) {
		this.urlCroquis = urlCroquis;
	}

	/**
	 * Funcion para pedir la descripcion especifica del proyecto
	 * @return descripcion especifica del proyecto
	 */
	public String getDescripcionEspecifica() {
		return descripcionEspecifica;
	}

	/**
	 * Funcion para establecer la descripcion especifica del proyecto
	 * @param descripcionEspecifica del proyecto
	 */
	public void setDescripcionEspecifica(String descripcionEspecifica) {
		this.descripcionEspecifica = descripcionEspecifica;
	}

}
