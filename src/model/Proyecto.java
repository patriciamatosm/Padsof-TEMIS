package model;


import java.util.Date;
import java.util.List;
import java.io.Serializable;
import java.text.ParseException;
import java.text.SimpleDateFormat;

public class Proyecto implements Serializable {
    private String titulo;
    private String descripcion;
    private String fechaUltimoVoto;
    private String fechaCreacion;
    private Integer numVotos = 0;
    private Integer minVotos;
    private Integer importe;
    private Actor creador;
    private Estado estado;


    public Proyecto(String titulo, String descripcion, String fechaCreacion, Integer minVotos,
                    Integer importe, Actor creador, Estado estado) {
        this.titulo = titulo;
        this.descripcion = descripcion;
        this.fechaCreacion = fechaCreacion;
        this.minVotos = minVotos;
        this.importe = importe;
        this.creador = creador;
        this.estado = estado;
    }

    public enum Estado {
    	ACTIVO, EN_ESPERA, CADUCADO, ESPERA_FINANC, FINANCIADO
    }
    
    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public String getFechaUltimoVoto() {
        return fechaUltimoVoto;
    }

    public void setFechaUltimoVoto(String fechaUltimoVoto) {
        this.fechaUltimoVoto = fechaUltimoVoto;
    }

    public String getFechaCreacion() {
        return fechaCreacion;
    }

    public void setFechaCreacion(String fechaCreacion) {
        this.fechaCreacion = fechaCreacion;
    }

    public Integer getNumVotos() {
        return numVotos;
    }

    public void setNumVotos(Integer numVotos) {
        this.numVotos = numVotos;
    }

    public Integer getImporte() {
        return importe;
    }

    public void setImporte(Integer importe) {
        this.importe = importe;
    }

    public Actor getCreador() {
        return creador;
    }

    public Estado getEstado() {
        return estado;
    }
    
    public void setEstado(Estado estado) {
    	this.estado = estado;
    }
    
    public Integer getMinVotos() {
    	return minVotos;
    }
    
    public void setMinVotos(Integer minVotos) {
    	this.minVotos = minVotos;
    }
    
    public Boolean votar(Usuario u){
    	if(this.estado == Estado.CADUCADO) return false;
    	
        if(u.getListaProyecto().contains(this)) {
        	return false;
        }else {
        	u.anadirProyecto(this);
        	return true;
        }
    }
    
    public Boolean votarUsuarios(List<Usuario> usuarios) {
    	if(this.estado == Estado.CADUCADO) return false;
    	
    	for (Usuario u : usuarios) {
    		if(!u.getListaProyecto().contains(this)) {
    			u.anadirProyecto(this);
    		}
    	}
    	return true;
    }
    
    public Boolean caducado() {
    	
    	Date fecha = new Date();
    	
    	SimpleDateFormat format = new SimpleDateFormat("aaaa-mm-dd");
    	
    	Date fechaUltimo = null;
		try {
			fechaUltimo = format.parse(this.fechaUltimoVoto);
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    	Date fechaActual = null;
		try {
			fechaActual = format.parse(fecha.toString());
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    	
    	int dias=(int) ((fechaActual.getTime()-fechaUltimo.getTime())/86400000);

    	if(dias >= 30) {
    		this.estado = Estado.CADUCADO;
    		return true;
    	}
    	return false;
    }
    
    public void aceptarProyecto() {
    	if(this.estado == Estado.EN_ESPERA) this.estado = Estado.ACTIVO;
    }
    
    public void aceptarFinanciacion() {
    	if(this.estado == Estado.ESPERA_FINANC) this.estado = Estado.FINANCIADO;
    }
 
    public Integer calcularPopularidad(List<Usuario> usuarios) {
    	for(Usuario u : usuarios) {
    		if(u.getListaProyecto().contains(this)) numVotos++;
    	}
    	return numVotos;
    }
    
    public Boolean esperarFinanc() {
    	if(this.estado == Estado.CADUCADO) return false;
    	
    	if(this.getNumVotos() >= this.getMinVotos() && this.estado == Estado.ACTIVO) {
    		this.estado = Estado.ESPERA_FINANC;
    		return true;
    	}
    	return false;
    }
    
    public void notificaCambio(Notificacion n) {
    	n.enviarNotificacion();
    }
}





