package mvc.model;


import java.util.List;

import es.uam.eps.sadp.grants.CCGG;
import es.uam.eps.sadp.grants.GrantRequest;
import es.uam.eps.sadp.grants.InvalidRequestException;
import es.uam.eps.sadp.grants.InvalidIDException;

import java.io.IOException;
import java.io.Serializable;
import java.time.LocalDate;

/**
 * Clase que define un Proyecto
 *
 * @author Daniel Prieto Fernández
 *         Silvia Tomey Prieto
 *         Patricia Matos Meza
 * @version 6/03/2020
 */

@SuppressWarnings("unused")
public abstract class Proyecto implements GrantRequest, Serializable {
    private String titulo;
    private String descripcion;
    private LocalDate fechaUltimoVoto;
    private LocalDate fechaCreacion;
    private Integer numVotos = 0;
    private Integer minVotos;
    private Integer importe;
    private Actor creador;
    private Estado estado;
    private String id;

    /**
     * Constructor de la clase Proyecto
     *
     * @param titulo         nombre del proyecto
     * @param descripcion    descripcion del proyecto
     * @param importe        importe destinado al proyecto
     * @param creador        usuario creador del proyecto
     */
    public Proyecto(String titulo, String descripcion, Integer importe, Actor creador) {
        this.titulo = titulo;
        this.descripcion = descripcion;
        this.fechaCreacion = LocalDate.now();
        this.importe = importe;
        this.creador = creador;
        this.estado = Estado.EN_ESPERA;
    }
    /**
     * Enumeracion que indica el estado del proyecto
     */
    public enum Estado {
    	ACTIVO, EN_ESPERA, CADUCADO, ESPERA_FINANC, FINANCIADO, NO_FINANCIADO
    }
    
    /**
     * Funci�n para pedir el t�tulo del proyecto
     * @return titulo del proyecto
     */
    public String getProjectTitle() {
        return this.titulo;
    }

    /**
     * Funcion para dar un titulo a un proyecto
     * @param titulo del proyecto
     */
    public void setTitulo(String titulo) {
        if(titulo.length() > 25) return;
    	
    	this.titulo = titulo;
    }

    /**
     * Funcion para pedir la descripcion del proyecto
     * @return descripcion del proyecto
     */
	public String getProjectDescription() {
        return descripcion;
    }

    /**
     * Funcion para establecer la descripcion del proyecto
     * @param descripcion del proyecto
     */
    public void setDescripcion(String descripcion) {
        if(descripcion.length() > 500) return;
    	
    	this.descripcion = descripcion;
    }

    /**
     * Funcion para pedir la fecha del ultimo voto
     * @return fecha del ultimo voto
     */
    public LocalDate getFechaUltimoVoto() {
        return fechaUltimoVoto;
    }

    /**
     * Funcion para establecer la fecha del ultimo voto
     * @param fechaUltimoVoto fecha ultimo voto
     */
    public void setFechaUltimoVoto(LocalDate fechaUltimoVoto) {
        this.fechaUltimoVoto = fechaUltimoVoto;
    }

    /**
     * Funcion para pedir la fecha de creacion del proyecto
     * @return fecha de creacion del proyecto
     */
    public LocalDate getFechaCreacion() {
        return fechaCreacion;
    }

    /**
     * Funcion para establecer la fecha de creacion del proyecto
     * @param fechaCreacion del proyecto
     */
    public void setFechaCreacion(LocalDate fechaCreacion) {
        this.fechaCreacion = fechaCreacion;
    }

    /**
     * Funcion para pedir el numero de votos de un proyecto
     * @return numero de votos del proyecto
     */
    public Integer getNumVotos() {
        return numVotos;
    }

    /**
     * Funcion para establecer el numero de votos de un proyecto
     * @param numVotos del proyecto
     */
    public void setNumVotos(Integer numVotos) {
        this.numVotos = numVotos;
    }

    /**
     * Funcion para pedir el importe destinado a un proyecto
     * @return importe destinado a un proyecto
     */
	public double getRequestedAmount() {
        return importe;
    }

    /**
     * Funcion para establecer el importe de un proyecto
     * @param importe del proyecto
     */
    public void setImporte(Integer importe) {
        this.importe = importe;
    }

    /**
     * Funcion para pedir el creador de un proyecto
     * @return creador del proyecto
     */
    public Actor getCreador() {
        return creador;
    }

    /**
     * Funcion para pedir el estado de un proyecto
     * @return estado del proyecto
     */
    public Estado getEstado() {
        return estado;
    }
    
    /**
     * Funcion para establecer el estado de un proyecto
     * @param estado del proyecto
     */
    public void setEstado(Estado estado) {
    	this.estado = estado;
    }
    
    /**
     * Funcion para obtener el minimo de votos necesarios para un proyecto
     * @return minimo de votos necesarios
     */
    public Integer getMinVotos() {
    	return minVotos;
    }
    
    /**
     * Funcion para establecer el numero minimo de votos necesarios para un proyecto
     * @param minVotos necesarios para el proyecto
     */
    public void setMinVotos(Integer minVotos) {
    	this.minVotos = minVotos;
    }
    
    /**
     * Funcion para que un usuario vote a un proyecto
     * @param u usuario que vota
     * @return true si se ha sumado el voto al proyecto correctamente, false si ocurre lo contrario.
     */
    public boolean votar(Usuario u){
    	if(this.estado != Estado.ACTIVO && this.estado != Estado.ESPERA_FINANC) return false;
    	if(!u.isLogueado()) return false;
    	
        if(u.getListaProyecto().contains(this)) {
            return false;
        }
        u.anadirProyecto(this);
        this.setFechaUltimoVoto(LocalDate.now());
        this.setNumVotos(this.getNumVotos()+1);
        return true;
    }
    
    /**
     * Funcion para que un colectivo vote un proyecto
     * @param usuarios que votan desde el colectivo
     * @return true si se ha sumado el voto al proyecto correctamente, false si ocurre lo contrario.
     */
    public boolean votarUsuarios(List<Usuario> usuarios) {
    	if(this.estado == Estado.CADUCADO) return false;
    	
    	for (Usuario u : usuarios) {
    		if(!u.getListaProyecto().contains(this)) {
    			u.anadirProyecto(this);
    			this.setFechaUltimoVoto(LocalDate.now());
            	this.setNumVotos(this.getNumVotos()+1);
    		}
    	}
    	return true;
    }
    
    /**
     * Funcion para poner un proyecto como caducado cuando sobrepasa el tiempo limite establecido
     */
    public void caducado() {
    	
    	LocalDate fechaUltimo = this.getFechaUltimoVoto();
		
    	LocalDate fechaActual = LocalDate.now();

    	LocalDate dias = (fechaActual.minusDays(30));

    	if(dias.isEqual(fechaUltimo) || dias.isAfter(fechaUltimo)) {
    		this.estado = Estado.CADUCADO;
    	}
    }
    
    /**
     * Funcion para aceptar un proyecto que estaba en espera
     */
    public void aceptarProyecto() {
    	if(this.estado == Estado.EN_ESPERA) this.estado = Estado.ACTIVO;
    }

    /**
     * Funcion para no aceptar un proyecto que estaba en espera
     */
    public void noAceptarProyecto() {
        this.estado = Estado.CADUCADO;
    }
    
    /**
     * Funcion para aceptar financiacion para un proyecto que estaba en espera de financiarse
     */
    public void aceptarFinanciacion() {
    	if(this.estado == Estado.ESPERA_FINANC) this.estado = Estado.FINANCIADO;
    }
 
    /**
     * Funcion para calcular la popularidad de un proyecto
     * @param usuarios que apoyan el proyecto
     * @return numero de votos que tiene el proyecto
     */
    public Integer calcularPopularidad(List<Usuario> usuarios) {
    	for(Usuario u : usuarios) {
    		if(u.getListaProyecto().contains(this)) numVotos++;
    	}
    	return numVotos;
    }
    
    /**
     * Funcion para comprobar si un proyecto puede pasar a esperar financiacion
     * @return true si el proyecto cumple con los requisitos, false en el caso contrario
     */
    public boolean esperarFinanc() {
    	if(this.estado == Estado.CADUCADO) return false;
    	
    	if(this.getNumVotos() >= this.getMinVotos() && this.estado == Estado.ACTIVO) {
    		this.estado = Estado.ESPERA_FINANC;
    		return true;
    	}
    	return false;
    }

    /**
     * FUncion que pide al admin pedir solicitud para financiacion
     */
    public void pedirAdminSolicitud(){
        if(this.estado != Estado.CADUCADO){
            Temis.getInstance().solicitudes.add(this);
        }
    }

    /**
     * Funcion que deniega solicitud de financiacion
     */
    public void denegarSolicitud(){
        Temis.getInstance().solicitudes.remove(this);
    }
    
    /**
     * Funcion para enviar notificaciones a los usuarios subscritos al proyecto
     * @param n notificacion que se envia a los usuarios
     */
    public void notificaCambio(Notificacion n) {
    	n.enviarNotificacion();
    }

    @Override
    public String toString() {
        return "Proyecto " + titulo +
                ": " + descripcion +
                " . Creado por: " + creador.getNombre();
    }

    /**
     * Funcion para solicitar financiacion
     */
    public void pedirFinanciacion() {
    	CCGG pasarela = CCGG.getGateway();
        GrantRequest solicitud = this;
        try {
			this.id = pasarela.submitRequest(solicitud);
		} catch (IOException | InvalidRequestException e) {
			e.printStackTrace();
		}
        this.setEstado(Estado.ESPERA_FINANC);
    }
    
   /**
    * Funcion para comprobar si la financiacion ha sido concedida
    * @return la cantidad concedida, 0 si no ha sido aprobada, o null si sigue pendiente
    */
    public Double financiacion() {
        CCGG pasarela = CCGG.getGateway();
    	Double result = null;
    	pasarela.setDate(LocalDate.now().plusDays(11));
		try {
			result = pasarela.getAmountGranted(this.id);
            if(result > 0.0) this.setEstado(Estado.FINANCIADO);
            else if(result == 0.0) this.setEstado(Estado.NO_FINANCIADO);
		} catch (IOException | InvalidIDException e) {
			e.printStackTrace();
		}

    	return result; 
    }
    
}





