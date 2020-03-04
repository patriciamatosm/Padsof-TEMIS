package model;


import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class Proyecto {
    private String titulo;
    private String descripcion;
    private LocalDate fechaUltimoVoto;
    private LocalDate fechaCreacion;
    private Integer numVotos;
    private Integer importe;

    List list = new ArrayList();

    //list.addAll(Actor a);

    public Proyecto (String titulo, String descripcion, LocalDate fechaUltimoVoto, LocalDate fechaCreacion, Integer numVotos, Integer importe){
        this.titulo = titulo;
        this.descripcion = descripcion;
        this.fechaUltimoVoto = fechaUltimoVoto;
        this.fechaCreacion = fechaCreacion;
        this.numVotos = numVotos;
        this.importe = importe;
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

    public LocalDate getFechaUltimoVoto() {
        return fechaUltimoVoto;
    }

    public void setFechaUltimoVoto(LocalDate fechaUltimoVoto) {
        this.fechaUltimoVoto = fechaUltimoVoto;
    }

    public LocalDate getFechaCreacion() {
        return fechaCreacion;
    }

    public void setFechaCreacion(LocalDate fechaCreacion) {
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


    /*public Boolean votar(Usuario u){
        return Boolean
    }*/
}



