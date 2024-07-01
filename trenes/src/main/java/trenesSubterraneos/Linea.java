package trenesSubterraneos;

import lineales.dinamicas.Lista;

public class Linea {
    
    private String linea, color, estacionIni, estacionFin;
    private Lista trenesFuncionando;

    public Linea(String linea, String color, String estacionIni, String estacionFin) {
        this.linea = linea;
        this.color = color;
        this.estacionIni = estacionIni;
        this.estacionFin = estacionFin;
        trenesFuncionando = new Lista();
    }

    public String getNombre() {
        return linea;
    }

    public void setLinea(String linea) {
        this.linea = linea;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public Lista getTrenesFuncionando() {
        return trenesFuncionando;
    }

    public void setTrenesFuncionando(Lista trenesFuncionando) {
        this.trenesFuncionando = trenesFuncionando;
    }

    public String getEstacionIni() {
        return estacionIni;
    }

    public void setEstacionIni(String estacionIni) {
        this.estacionIni = estacionIni;
    }

    public String getEstacionFin() {
        return estacionFin;
    }

    public void setEstacionFin(String estacionFin) {
        this.estacionFin = estacionFin;
    }
    
    @Override
    public String toString() {
        return "{"+color+", "+estacionIni+", "+estacionFin+", "+trenesFuncionando.toString()+"}"+"\n";
    }
}
