package grafos;

import lineales.dinamicas.Cola;
import lineales.dinamicas.Lista;

public class Grafo {

    private NodoVert inicio;
    
    public Grafo() {
        this.inicio = null;
    }
    
    public boolean insertarVertice(Object elem) {
        boolean res = false;
        NodoVert aux = ubicarVertice(elem);
        if (aux == null) {
            inicio = new NodoVert(elem, inicio);
            res = true;
        }
        return res;
    }

    public boolean eliminarVertice(Object elem) {
        boolean res = false;
        if (inicio != null) {
            NodoAdy auxAdy = null;
            NodoVert aux = inicio;
            if (aux.getElem().equals(elem)) {
                auxAdy = aux.getPrimerAdy();
                inicio = inicio.getSigVertice();
                res = true;
            } else {
                while (!res && aux.getSigVertice() != null) {
                    if (aux.getSigVertice().getElem().equals(elem)) {
                        auxAdy = aux.getSigVertice().getPrimerAdy();
                        aux.setSigVertice(aux.getSigVertice().getSigVertice());
                        res = true;
                    }
                    aux = aux.getSigVertice();
                }
            }
            if (res) {
                while (auxAdy != null) {
                    eliminarArcoAux(auxAdy.getVertice(), elem);
                    auxAdy = auxAdy.getSigAdyacente();
                }
            }
        }
        return res;
    }

    private NodoVert ubicarVertice(Object buscado) {
        NodoVert aux = inicio;
        while (aux != null && !(aux.getElem().equals(buscado))) {
            aux = aux.getSigVertice();
        }
        return aux;
    }

    public boolean existeVertice(Object buscado) {
        boolean res = false;
        NodoVert aux = inicio;
        while (!res && aux != null) {
            if (aux.getElem().equals(buscado)) 
                res = true;
            aux = aux.getSigVertice();
        }
        return res;
    }

    public boolean insertarArco(Object origen, Object destino, int etiqueta) {
        boolean res = false;
        if (inicio != null) {
            NodoVert nodoOrigen = null;
            NodoVert nodoDestino = null;
            NodoVert aux1 = inicio;
            boolean encontroOrigen = false;
            boolean encontroDestino = false;
            while (aux1 != null && (!encontroOrigen || !encontroDestino)) {
                if (aux1.getElem().equals(origen)) {
                    nodoOrigen = aux1;
                    encontroOrigen = true;
                }
                if (aux1.getElem().equals(destino)) {
                    nodoDestino = aux1;
                    encontroDestino = true;
                }
                aux1 = aux1.getSigVertice();
            }
            if (nodoOrigen != null && nodoDestino != null) {
                if (nodoOrigen.getPrimerAdy() == null) {
                    nodoOrigen.setPrimerAdy(new NodoAdy(nodoDestino, etiqueta));
                    res = true;
                } else {
                    NodoAdy aux2 = nodoOrigen.getPrimerAdy();
                    while (aux2 != null && !(aux2.getVertice().getElem().equals(destino))) {
                        if (aux2.getSigAdyacente() == null) {
                            aux2.setSigAdyacente(new NodoAdy(nodoDestino, etiqueta));
                            res = true;
                        }
                        aux2 = aux2.getSigAdyacente();
                    }
                }
                if (res) {
                    NodoAdy aux3 = new NodoAdy(nodoOrigen, etiqueta);
                    aux3.setSigAdyacente(nodoDestino.getPrimerAdy());
                    nodoDestino.setPrimerAdy(aux3);
                }
            }
        }
        return res;
    }

    public boolean eliminarArco(Object origen, Object destino) {
        boolean res = false;
        NodoVert nodoOrigen = null;
        NodoVert nodoDestino = null;
        NodoVert aux = inicio;
        boolean encontroOrigen = false;
        boolean encontroDestino = false;
        while (aux != null && (!encontroOrigen || !encontroDestino)) {
            if (aux.getElem().equals(origen)) {
                nodoOrigen = aux;
                encontroOrigen = true;
            }
            if (aux.getElem().equals(destino)) {
                nodoDestino = aux;
                encontroDestino = true;
            }
            aux = aux.getSigVertice();
        }
        if (nodoOrigen != null && nodoDestino != null) {
            res = eliminarArcoAux(nodoOrigen, destino);
            if (res) 
                eliminarArcoAux(nodoDestino, origen);
        }
        return res;
    }

    private boolean eliminarArcoAux(NodoVert nodoOrigen, Object elemDestino) {
        boolean res = false;
        NodoAdy aux = nodoOrigen.getPrimerAdy();
        if (aux != null) {
            if (nodoOrigen.getPrimerAdy().getVertice().getElem().equals(elemDestino)) {
                nodoOrigen.setPrimerAdy(nodoOrigen.getPrimerAdy().getSigAdyacente());
                res = true;
            } else {
                while (!res && aux.getSigAdyacente() != null) {
                    if (aux.getSigAdyacente().getVertice().getElem().equals(elemDestino)) {
                        aux.setSigAdyacente(aux.getSigAdyacente().getSigAdyacente());
                        res = true;
                    }
                    aux = aux.getSigAdyacente();
                }
            }
        }
        return res;
    }

    public boolean existeArco(Object origen, Object destino) {
        boolean res = false;
        NodoVert nodoOrigen = null;
        NodoVert nodoDestino = null;
        NodoVert aux = inicio;
        boolean encontroOrigen = false;
        boolean encontroDestino = false;
        while (aux != null && (!encontroOrigen || !encontroDestino)) {
            if (aux.getElem().equals(origen)) {
                nodoOrigen = aux;
                encontroOrigen = true;
            }
            if (aux.getElem().equals(destino)) {
                nodoDestino = aux;
                encontroDestino = true;
            }
            aux = aux.getSigVertice();
        }
        NodoAdy auxAdy = nodoOrigen.getPrimerAdy();
        while (!res && auxAdy != null) {
            if (auxAdy.getVertice().equals(nodoDestino)) 
                res = true;
            auxAdy = auxAdy.getSigAdyacente();
        }
        return res;
    }

    public boolean existeCamino(Object origen, Object destino) {
        boolean res = false;
        NodoVert nodoOrigen = null;
        NodoVert nodoDestino = null;
        NodoVert aux = inicio;
        boolean encontroOrigen = false;
        boolean encontroDestino = false;
        while (aux != null && (!encontroOrigen || !encontroDestino)) {
            if (aux.getElem().equals(origen)) {
                nodoOrigen = aux;
                encontroOrigen = true;
            }
            if (aux.getElem().equals(destino)) {
                nodoDestino = aux;
                encontroDestino = true;
            }
            aux = aux.getSigVertice();
        }
        if (nodoOrigen != null && nodoDestino != null) {
            Lista visitados = new Lista();
            res = existeCaminoAux(nodoOrigen, destino, visitados);
        }
        return res;
    }

    private boolean existeCaminoAux(NodoVert aux, Object destino, Lista visitados) {
        boolean res = false;
        if (aux != null) {
            if (aux.getElem().equals(destino)) {
                res = true;
            }
            else {
                visitados.insertar(aux.getElem(), visitados.longitud() + 1);
                NodoAdy auxAdy = aux.getPrimerAdy();
                while (!res && auxAdy != null) {
                    if (visitados.localizar(auxAdy.getVertice().getElem()) < 0) {
                        res = existeCaminoAux(auxAdy.getVertice(), destino, visitados);
                    }
                    auxAdy = auxAdy.getSigAdyacente();
                }
            }
        }
        return res;
    }

    public Lista listarArcos() {
        Lista res = new Lista();
        Lista visitados = new Lista();
        NodoVert aux = inicio;
        while (aux != null) {
            visitados.insertar(aux.getElem(), visitados.longitud() + 1);
            NodoAdy auxAdy = aux.getPrimerAdy();
            while (auxAdy != null) {
                if (visitados.localizar(auxAdy.getVertice().getElem()) < 0) {
                    String[] nuevoArco = {aux.getElem().toString(), auxAdy.getVertice().getElem().toString(), "" + auxAdy.getEtiqueta()};
                    res.insertar(nuevoArco, res.longitud() + 1);
                }
                auxAdy = auxAdy.getSigAdyacente();
            }
            aux = aux.getSigVertice();
        }
        return res;
    }

    public Lista listarEnProfundidad() {
        Lista visitados = new Lista();
        NodoVert aux = inicio;
        while (aux != null) {
            if (visitados.localizar(aux.getElem()) < 0) {
                listarEnProfundidadAux(aux, visitados);
            }
            aux = aux.getSigVertice();
        }
        return visitados;
    }

    private void listarEnProfundidadAux(NodoVert aux, Lista visitados) {
        if (aux != null) {
            visitados.insertar(aux.getElem(), visitados.longitud() + 1);
            NodoAdy auxAdy = aux.getPrimerAdy();
            while (auxAdy != null) {
                if (visitados.localizar(auxAdy.getVertice().getElem()) < 0) {
                    listarEnProfundidadAux(auxAdy.getVertice(), visitados);
                }
                auxAdy = auxAdy.getSigAdyacente();
            }
        }
    }

    public Lista listarEnAnchura() {
        Lista visitados = new Lista();
        NodoVert aux = inicio;
        while (aux != null) {
            if (visitados.localizar(aux.getElem()) < 0) {
                listarEnAnchuraAux(aux, visitados);
            }
            aux = aux.getSigVertice();
        }
        return visitados;
    }

    private void listarEnAnchuraAux(NodoVert ini, Lista visitados) {
        Cola orden = new Cola();
        orden.poner(ini);
        while (!orden.esVacia()) {
            NodoVert aux = (NodoVert)orden.obtenerFrente();
            orden.sacar();
            if (visitados.localizar(aux.getElem()) < 0) {
                visitados.insertar(aux.getElem(), visitados.longitud() + 1);
            }
            NodoAdy auxAdy = aux.getPrimerAdy();
            while (auxAdy != null) {
                if (visitados.localizar(auxAdy.getVertice().getElem()) < 0) {
                    orden.poner(auxAdy.getVertice());
                }
                auxAdy = auxAdy.getSigAdyacente();
            }
        }
    }
    
    public Lista caminoMasCorto(Object origen, Object destino) {
        Lista camino = new Lista();
        Lista caminoMasCorto = new Lista();
        NodoVert nodoOrigen = ubicarVertice(origen);
        NodoVert nodoDestino = ubicarVertice(destino);
        if ((nodoOrigen != null) && (nodoDestino != null)) 
           caminoMasCorto = caminoMasCortoAux(nodoOrigen, destino, camino, caminoMasCorto);
        return caminoMasCorto;
    }
    
    private Lista caminoMasCortoAux(NodoVert aux, Object destino, Lista camino, Lista caminoMasCorto) {
        NodoAdy ady;
        if (aux != null) {
            if (aux.getElem().equals(destino)) {
                camino.insertar(aux.getElem(), camino.longitud() + 1);
                if ((camino.longitud() < caminoMasCorto.longitud()) || (caminoMasCorto.longitud() == 0))
                    caminoMasCorto = camino.clonar(); 
                camino.eliminar(camino.longitud());
            }
            else {
                camino.insertar(aux.getElem(), camino.longitud() + 1);
                ady = aux.getPrimerAdy();
                while (ady != null) {
                    if (camino.localizar(ady.getVertice().getElem()) < 0) {
                        if ((caminoMasCorto.longitud() == 0) || ((camino.longitud() + 1) < caminoMasCorto.longitud()))
                           caminoMasCorto = caminoMasCortoAux(ady.getVertice(), destino, camino, caminoMasCorto);
                    }
                    ady = ady.getSigAdyacente();
                }
                camino.eliminar(camino.longitud());
            }
        }
        return caminoMasCorto;
    }
    
    public Object[] caminoMenorTiempo(Object origen, Object destino) {
        Lista camino = new Lista();
        Lista caminoMenorTiempo = new Lista();
        NodoVert nodoOrigen = ubicarVertice(origen);
        NodoVert nodoDestino = ubicarVertice(destino);
        Object[] arreglo = new Object[2];
        arreglo[0] = caminoMenorTiempo;
        arreglo[1] = 0.0;
        if ((nodoOrigen != null) && (nodoDestino != null)) 
           arreglo = caminoMenorTiempoAux(nodoOrigen, destino, camino, arreglo, 0);
        return arreglo;
    }
    
    private Object[] caminoMenorTiempoAux(NodoVert aux, Object destino, Lista camino, Object[] arreglo, double suma) {
        NodoAdy ady;
        Lista temp;
        int longitud;
        if (aux != null) {
            temp = (Lista) arreglo[0];
            longitud = temp.longitud();
            if (aux.getElem().equals(destino)) {
                camino.insertar(aux.getElem(), camino.longitud() + 1);
                if (((suma < (double)arreglo[1]) || ((double)arreglo[1] == 0))) {
                    arreglo[0] = camino.clonar();
                    arreglo[1] = suma;
                }
                camino.eliminar(camino.longitud());
            }
            else {
                camino.insertar(aux.getElem(), camino.longitud() + 1);
                ady = aux.getPrimerAdy();
                while(ady != null) {   
                    suma += ady.getEtiqueta();
                    if (camino.localizar(ady.getVertice().getElem()) < 0) { 
                        if(((suma < (double)arreglo[1]) || ((double)arreglo[1] == 0))) 
                           arreglo = caminoMenorTiempoAux(ady.getVertice(), destino, camino, arreglo, suma);
                    }
                    suma -= ady.getEtiqueta();
                    ady = ady.getSigAdyacente();
                }
                camino.eliminar(camino.longitud());
            }
        }
        return arreglo;
    }
    
    public Lista caminoMasLargo(Object origen, Object destino) {
        NodoVert nodoOrigen = ubicarVertice(origen);
        Lista camino = new Lista();
        if (nodoOrigen != null && existeCamino(origen, destino)) {
            caminoMasLargoAux(nodoOrigen, destino, camino, 0);
        }
        return camino;
    }

    private double caminoMasLargoAux(NodoVert aux, Object destino, Lista camino, double suma) {
        if (aux != null) {
            if (aux.getElem().equals(destino)) {
                camino.insertar(destino, camino.longitud() + 1);
            } else if (camino.localizar(aux.getElem()) < 0) {
                double sumaMaxima;
                double nuevaSuma;
                Lista temp = new Lista();
                Lista cambio = new Lista();
                camino.insertar(aux.getElem(), camino.longitud() + 1);
                cambio.copiar(camino);
                temp.copiar(camino);
                NodoAdy ady = aux.getPrimerAdy();
                sumaMaxima = caminoMasLargoAux(ady.getVertice(), destino, camino, suma + 1);
                ady = ady.getSigAdyacente();
                while (ady != null) {
                    nuevaSuma = caminoMasLargoAux(ady.getVertice(), destino, temp, suma + 1);
                    if (nuevaSuma > 0) {
                        if (sumaMaxima == 0) {
                            sumaMaxima = nuevaSuma;
                            camino.copiar(temp);
                        } else {
                            if (nuevaSuma > sumaMaxima) {
                                sumaMaxima = nuevaSuma;
                                camino.copiar(temp);
                            }
                        }
                    }
                    temp.copiar(cambio);
                    ady = ady.getSigAdyacente();
                }
                suma = sumaMaxima;
            } else {
                suma = 0;
            }
        }
        return suma;
    }

    public Lista caminoMayorLongitud(Object origen, Object destino) {
        NodoVert nodoOrigen = ubicarVertice(origen);
        Lista camino = new Lista();
        if (nodoOrigen != null && existeCamino(origen, destino)) {
            caminoMayorLongitudAux(nodoOrigen, destino, camino, 0);
        }
        return camino;
    }

    private double caminoMayorLongitudAux(NodoVert aux, Object destino, Lista camino, double suma) {
        if (aux != null) {
            if (aux.getElem().equals(destino)) {
                camino.insertar(destino, camino.longitud() + 1);
            } else if (camino.localizar(aux.getElem()) < 0) {
                double sumaMaxima;
                double nuevaSuma;
                Lista temp = new Lista();
                Lista cambio = new Lista();
                camino.insertar(aux.getElem(), camino.longitud() + 1);
                cambio.copiar(camino);
                temp.copiar(camino);
                NodoAdy ady = aux.getPrimerAdy();
                sumaMaxima = caminoMayorLongitudAux(ady.getVertice(), destino, camino, suma + ady.getEtiqueta());
                ady = ady.getSigAdyacente();
                while (ady != null) {
                    nuevaSuma = caminoMayorLongitudAux(ady.getVertice(), destino, temp, suma + ady.getEtiqueta());
                    if (nuevaSuma > 0) {
                        if (sumaMaxima == 0) {
                            sumaMaxima = nuevaSuma;
                            camino.copiar(temp);
                        } else {
                            if (nuevaSuma > sumaMaxima) {
                                sumaMaxima = nuevaSuma;
                                camino.copiar(temp);
                            }
                        }
                    }
                    temp.copiar(cambio);
                    ady = ady.getSigAdyacente();
                }
                suma = sumaMaxima;
            } else {
                suma = 0;
            }
        }
        return suma;
    }

    public boolean esVacio() {
        return (inicio == null);
    }

    public void vaciar() {
        inicio = null;
    }

    public Grafo clonar() {
        Grafo clon = new Grafo();
        if (inicio != null) {
            clon.inicio = new NodoVert(inicio.getElem());
            NodoVert aux = inicio;
            NodoVert auxClon = clon.inicio;
            while (aux.getSigVertice() != null) {
                aux = aux.getSigVertice();
                auxClon.setSigVertice(new NodoVert(aux.getElem()));
                auxClon = auxClon.getSigVertice();
            }
            aux = inicio;
            auxClon = clon.inicio;
            while (aux != null) {
                NodoAdy auxAdy = aux.getPrimerAdy();
                auxClon.setPrimerAdy(new NodoAdy(clon.ubicarVertice(auxAdy.getVertice().getElem()), auxAdy.getEtiqueta()));
                NodoAdy auxAdyClon = auxClon.getPrimerAdy();
                while (auxAdy.getSigAdyacente() != null) {
                    auxAdy = auxAdy.getSigAdyacente();
                    auxAdyClon.setSigAdyacente(new NodoAdy(clon.ubicarVertice(auxAdy.getVertice().getElem()), auxAdy.getEtiqueta()));
                    auxAdyClon = auxAdyClon.getSigAdyacente();
                }
                aux = aux.getSigVertice();
                auxClon = auxClon.getSigVertice();
            }
        }
        return clon;
    }

    @Override
    public String toString() {
        String res = "";
        if (inicio != null) {
            Lista visitados = new Lista();
            NodoVert aux = inicio;
            res += "{";
            while (aux != null) {
                if (visitados.localizar(aux.getElem()) < 0) {
                    res += toStringAux(aux, visitados);
                }
                aux = aux.getSigVertice();
                res += "]";
                if (aux != null) {
                    res += ", "+"\n";
                }
            }
            res += "}";
        }
        else
            res = "{Ø}";
        return res;
    }

    private String toStringAux(NodoVert aux, Lista visitados) {
        String res = "";
        if (aux != null) {
            visitados.insertar(aux.getElem(), visitados.longitud() + 1);
            res += aux.getElem().toString() + "[";
            NodoAdy ady = aux.getPrimerAdy();
            while (ady != null) {
                res += ady.getVertice().getElem().toString() + "(" + ady.getEtiqueta() + "min)";
                ady = ady.getSigAdyacente();
                if (ady != null) {
                    res += ", ";
                }
            }
        }
        return res;
    }
}
