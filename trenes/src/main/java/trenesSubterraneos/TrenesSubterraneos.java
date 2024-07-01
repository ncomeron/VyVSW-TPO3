package trenesSubterraneos;

import grafos.Grafo;
import especificos.Diccionario;
import java.util.HashMap;
import lineales.dinamicas.Lista;

public class TrenesSubterraneos {
    
    private Grafo redDeRieles;
    private Diccionario estaciones;
    private HashMap trenes, lineas;
    
    public TrenesSubterraneos() {
        redDeRieles = new Grafo();
        estaciones = new Diccionario();
        trenes = new HashMap();
        lineas = new HashMap();
    }

    public TrenesSubterraneos(Diccionario estaciones) {
        this.estaciones = estaciones;
    }
    
    //Metodo que realiza la creacion de los trenes y sus respectivas actualizaciones dentro de las demas estructuras.
    public boolean altaTren(String numTren, String linea) {
        boolean salida = false;
        if (!trenes.containsKey(numTren) && lineas.containsKey(linea)) {;
            trenes.put(numTren, linea);
            Linea l = (Linea) lineas.get(linea);
            l.getTrenesFuncionando().insertar(numTren, l.getTrenesFuncionando().longitud() + 1);
            salida = true;
        }
        return salida;
    }
    
    //Metodo que realiza la eliminacion de los trenes y sus respectivas actualizaciones dentro de las demas estructuras.
    public boolean bajaTren(String numTren) {
        boolean salida = trenes.containsKey(numTren);
        if (salida) {
            Linea l = (Linea)lineas.get(trenes.get(numTren));
            l.getTrenesFuncionando().eliminar(l.getTrenesFuncionando().localizar(numTren));
            trenes.remove(numTren);
        }
        return salida;
    }
    
    //Metodo que realiza la creacion de las estaciones y sus respectivas actualizaciones dentro de las demas estructuras.
    public boolean altaEstacion(String nombre, String direccion, String horario, String accesibilidad, boolean esExtremo) {
        Estacion unaEstacion = new Estacion(nombre, direccion, horario, accesibilidad, esExtremo);
        boolean salida = estaciones.insertar(nombre, unaEstacion);
        if (salida)
            redDeRieles.insertarVertice(nombre);
        return salida;
    }
    
    //Metodo que realiza la eliminacion de las estaciones y sus respectivas actualizaciones dentro de las demas estructuras.
    public boolean bajaEstacion(String estacion) {
        boolean salida = estaciones.existeClave(estacion);
        if (salida) {
            Estacion e = (Estacion)estaciones.obtenerInformacion(estacion);
            if (!e.getEsExtremo()) {
                redDeRieles.eliminarVertice(estacion);
                estaciones.eliminar(estacion);
            }
            else
                salida = false;
        }
        return salida;
    }
    
    //Metodo que realiza la vinculacion entre estaciones.
    public boolean altaTramoDeRieles(String origen, String destino, int tiempo) {
        return redDeRieles.insertarArco(origen, destino, tiempo);
    }
    
    //Metodo que realiza la desvinculacion entre estaciones.
    public boolean bajaTramoDeRieles(String origen, String destino) {
        return redDeRieles.eliminarArco(origen, destino);
    }
    
    //Metodo que realiza la creacion de las lineas y sus respectivas actualizaciones dentro de las demas estructuras.
    public boolean altaLinea(String linea, String color, String estacionIni, String estacionFin) {
        boolean salida = false;
        if (!lineas.containsKey(linea)) {
            if ((estaciones.existeClave(estacionIni)) && (estaciones.existeClave(estacionFin))) {
                Linea unaLinea = new Linea(linea, color, estacionIni, estacionFin);
                Lista listaEstaciones = redDeRieles.caminoMasCorto(estacionIni, estacionFin);
                agregarLinea(linea, listaEstaciones);
                lineas.put(linea, unaLinea);
                salida = true;
            }
        }
        return salida;
    }
    
    //Metodo privado que, dado el trayecto de estaciones que realiza la linea, añade a cada estacion
    //que dicha linea pasa por ella.
    private void agregarLinea(String linea, Lista listaEstaciones) {
        for (int i = 1; i <= listaEstaciones.longitud(); i++) {
            Estacion e = (Estacion)estaciones.obtenerInformacion((Comparable)listaEstaciones.recuperar(i));
            e.getLineas().insertar(linea, e.getLineas().longitud() + 1);
        }
    }
    
    //Metodo que busca un tren y en caso de existir se devuelve su informacion en forma de string.
    public String mostrarDatosTren(String tren) {
        String salida = "Dicho tren NO existe.";
        boolean existe = trenes.containsKey(tren);
        if (existe)
            salida = trenes.get(tren).toString();
        return salida;
    }
    
    //Metodo que busca una estacion y en caso de existir se devuelve su informacion en forma de string.
    public String mostrarDatosEstacion(String estacion) {
        String salida = "Dicha estacion NO existe.";
        boolean existe = estaciones.existeClave(estacion);
        if (existe)
            salida = estaciones.obtenerInformacion(estacion).toString();
        return salida;
    }
    
    public Lista estacionSegunCadena(String cadena) {
        return estaciones.listarRango(cadena, cadena+="ZZ");
    }
    
    //Metodo que retorna una lista con el camino por el cual se llega a su destino a traves de menos estaciones.
    public Lista caminoMenosEstaciones(String origen, String destino) {
        return redDeRieles.caminoMasCorto(origen, destino);
    }
    
    //Metodo que retorna una lista con el camino por el cual se llega a su destino en la menor cantidad de tiempo.
    public Object[] caminoMenorTiempo(String origen, String destino) {
        return redDeRieles.caminoMenorTiempo(origen, destino);
    }
    
    //Metodo el cual imprime por pantalla las estructuras.
    public void mostrarEstructuras() {
        System.out.println("Red de Rieles: "+"\n"+redDeRieles.toString());
        System.out.println("Estaciones: "+"\n"+estaciones.listarDatos().toString());
        System.out.println("Trenes: "+trenes.toString());
        System.out.println("Lineas: "+"\n"+lineas.toString());
    }
    
    public void mostrarEstaciones() {
        System.out.println("Estaciones: "+"\n"+estaciones.toString());
    }
    
    public void mostrarSubte() {
        System.out.println("Red de Rieles: "+"\n"+redDeRieles.toString());
    }
    
    public void mostrarLineas() {
        System.out.println("Lineas: "+"\n"+lineas.toString());
    }
    
    public void mostrarTrenes() {
        System.out.println("Trenes: "+trenes.toString());
    }
    
    //Metodo que modifica la linea de un tren, modificando tambien la lista de trenesFuncionando de linea.
    public boolean modificarTrenExistente(String tren, String linea) {
        boolean salida = false;
        if (trenes.containsKey(tren) && lineas.containsKey(linea)) {
            String t = (String)trenes.get(tren);
            if (t.equals("null")) {
                //Actualizo valor linea del tren
                trenes.replace(tren, linea);
                //Actualizo mapa lineas
                Linea l = (Linea)lineas.get(linea);
                l.getTrenesFuncionando().insertar(tren, l.getTrenesFuncionando().longitud() + 1);
            }
            else {
                Linea l = (Linea)lineas.get(t);
                l.getTrenesFuncionando().eliminar(l.getTrenesFuncionando().localizar(tren));
                trenes.replace(tren, linea);
                l = (Linea)lineas.get(linea);
                l.getTrenesFuncionando().insertar(tren, l.getTrenesFuncionando().longitud() + 1);
            }
        salida = true;    
        }
        return salida;
    }
}