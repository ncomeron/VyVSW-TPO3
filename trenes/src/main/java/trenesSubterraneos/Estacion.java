package trenesSubterraneos;

import lineales.dinamicas.Lista;

public class Estacion {
    
    private String nombre, direccion, horario, accesibilidad;
    private boolean esExtremo;
    private Lista lineas;

    public Estacion(String nombre, String direccion, String horario, String accesibilidad, boolean esExtremo) {
        this.nombre = nombre;
        this.direccion = direccion;
        this.horario = horario;
        this.accesibilidad = accesibilidad;
        this.esExtremo = esExtremo;
        this.lineas = new Lista();
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getDireccion() {
        return direccion;
    }

    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }

    public String getHorario() {
        return horario;
    }

    public void setHorario(String horario) {
        this.horario = horario;
    }

    public String getAccesibilidad() {
        return accesibilidad;
    }

    public void setAccesibilidad(String accesibilidad) {
        this.accesibilidad = accesibilidad;
    }

    public boolean getEsExtremo() {
        return esExtremo;
    }

    public void setEsExtremo(boolean esExtremo) {
        this.esExtremo = esExtremo;
    }

    public Lista getLineas() {
        return lineas;
    }

    public void setLineas(Lista lineas) {
        this.lineas = lineas;
    }
    
    @Override
    public String toString() {
        return "{"+nombre+", "+direccion+", "+horario+", "+accesibilidad+", "+esExtremo+", "+lineas+"}"+"\n";
    }
}
