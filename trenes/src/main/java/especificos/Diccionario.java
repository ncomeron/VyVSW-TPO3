package especificos;

/* Ineficiencia a la hora de autobalancear debido al alturaAux(), hay que agregar atrib altura al nodo */

import lineales.dinamicas.Lista;

public class Diccionario {
    
    private NodoAVLDicc raiz;

    public Diccionario() {
        this.raiz = null;
    }

    public boolean insertar(Comparable clave, Object dato) {
        boolean res = true;
        if (raiz == null) {
            raiz = new NodoAVLDicc(clave, dato);
        } else {
            res = insertarAux(raiz, clave, dato);
        }
        if (res) {
            autobalancear(raiz);
        }
        return res;
    }
    
    private boolean insertarAux(NodoAVLDicc aux, Comparable clave, Object dato) {
        boolean res = true;
        if (clave.equals(aux.getClave())) {
            res = false;
        } else {
            if (clave.compareTo(aux.getClave()) < 0) {
                if (aux.getIzquierdo() != null) {
                    res = insertarAux(aux.getIzquierdo(), clave, dato);
                } else {
                    aux.setIzquierdo(new NodoAVLDicc(clave, dato));
                }
            } else {
                if (aux.getDerecho() != null) {
                    res = insertarAux(aux.getDerecho(), clave, dato);
                } else {
                    aux.setDerecho(new NodoAVLDicc(clave, dato));
                }
            }
        }
        if (res) {
            if (aux.getIzquierdo() != null) {
                aux.setIzquierdo(autobalancear(aux.getIzquierdo()));
            }
            if (aux.getDerecho() != null) {
                aux.setDerecho(autobalancear(aux.getDerecho()));
            }
        }
        return res;
    }
    
    public boolean eliminar(Comparable clave) {
        boolean res = false;
        if (raiz != null) {
            if (raiz.getClave().equals(clave)) {
                if (esHoja(raiz)) {
                    raiz = null;
                } else if (soloHijoIzq(raiz)) {
                    raiz = raiz.getIzquierdo();
                } else if (soloHijoDer(raiz)) {
                    raiz = raiz.getDerecho();
                } else {
                    raiz = tercerCaso(raiz);
                }
                res = true;
            } else {
                res = eliminarAux(raiz, clave);
            }
        }
        if (res) {
            autobalancear(raiz);
        }
        return res;
    }
    
    private boolean eliminarAux(NodoAVLDicc aux, Comparable clave) {
        boolean res = false;
        if (aux != null) {
            if ((aux.getIzquierdo() != null) && (aux.getIzquierdo().getClave().equals(clave))) {
                if (esHoja(aux.getIzquierdo())) {
                    aux.setIzquierdo(null);
                } else if (soloHijoIzq(aux.getIzquierdo())) {
                    aux.setIzquierdo(aux.getIzquierdo().getIzquierdo());
                } else if (soloHijoDer(aux.getIzquierdo())) {
                    aux.setIzquierdo(aux.getIzquierdo().getDerecho());
                } else {
                    aux.setIzquierdo(tercerCaso(aux.getIzquierdo()));
                }
                res = true;
            } else if ((aux.getDerecho() != null) && (aux.getDerecho().getClave().equals(clave))) {
                if (esHoja(aux.getDerecho())) {
                    aux.setDerecho(null);
                } else if (soloHijoIzq(aux.getDerecho())) {
                    aux.setDerecho(aux.getDerecho().getIzquierdo());
                } else if (soloHijoDer(aux.getDerecho())) {
                    aux.setDerecho(aux.getDerecho().getDerecho());
                } else {
                    aux.setDerecho(tercerCaso(aux.getDerecho()));
                }
                res = true;
            } else {
                res = eliminarAux(aux.getIzquierdo(), clave);
                if (!res) {
                    res = eliminarAux(aux.getDerecho(), clave);
                }
            }
        }
        if (res) {
            autobalancear(aux);
        }
        return res;
    }
    
    private NodoAVLDicc autobalancear(NodoAVLDicc n) {
        int balanceP;
        balanceP = alturaAux(n.getIzquierdo()) - alturaAux(n.getDerecho());
        if (balanceP < -1) {
            int balanceHD = alturaAux(n.getDerecho().getIzquierdo()) - alturaAux(n.getDerecho().getDerecho());
            if (balanceHD < 0) {
                rotacionSimpleIzq(n);
            } else {
                rotacionSimpleDer(n.getDerecho());
                rotacionSimpleIzq(n);
            }
        } else if (balanceP > 1) {
            int balanceHI = alturaAux(n.getIzquierdo().getIzquierdo()) - alturaAux(n.getIzquierdo().getDerecho());
            if (balanceHI > 0) {
                rotacionSimpleDer(n);
            } else {
                rotacionSimpleIzq(n.getIzquierdo());
                rotacionSimpleDer(n);
            }
        }
        return n;
    }
    
    private NodoAVLDicc rotacionSimpleIzq(NodoAVLDicc n) {
        Comparable aux1 = n.getClave();
        Object aux2 = n.getDato();
        n.setClave(n.getDerecho().getClave());
        n.setDato(n.getDerecho().getDato());
        NodoAVLDicc temp = n.getDerecho().getIzquierdo();
        n.setDerecho(n.getDerecho().getDerecho());
        if (n.getIzquierdo() != null) {
            NodoAVLDicc nuevoIzq = new NodoAVLDicc(aux1, aux2);
            nuevoIzq.setIzquierdo(n.getIzquierdo());
            n.setIzquierdo(nuevoIzq);
            n.getIzquierdo().setDerecho(temp);
        } else {
            n.setIzquierdo(new NodoAVLDicc(aux1, aux2));
            n.getIzquierdo().setDerecho(temp);
        }
        return n;
    }

    private NodoAVLDicc rotacionSimpleDer(NodoAVLDicc n) {
        Comparable aux1 = n.getClave();
        Object aux2 = n.getDato();
        n.setClave(n.getIzquierdo().getClave());
        n.setDato(n.getIzquierdo().getDato());
        NodoAVLDicc temp = n.getIzquierdo().getDerecho();
        n.setIzquierdo(n.getIzquierdo().getIzquierdo());
        if (n.getDerecho() != null) {
            NodoAVLDicc nuevoDer = new NodoAVLDicc(aux1, aux2);
            nuevoDer.setDerecho(n.getDerecho());
            n.setDerecho(nuevoDer);
            n.getDerecho().setIzquierdo(temp);
        } else {
            n.setDerecho(new NodoAVLDicc(aux1, aux2));
            n.getDerecho().setIzquierdo(temp);
        }
        return n;
    }
    
    private boolean esHoja(NodoAVLDicc aux) {
        return ((aux.getDerecho() == null) && (aux.getIzquierdo() == null));
    }

    private boolean soloHijoDer(NodoAVLDicc aux) {
        return ((aux.getDerecho() != null) && (aux.getIzquierdo() == null));
    }

    private boolean soloHijoIzq(NodoAVLDicc aux) {
        return ((aux.getDerecho() == null) && (aux.getIzquierdo() != null));
    }

    private NodoAVLDicc tercerCaso(NodoAVLDicc aux) {
        NodoAVLDicc temp;
        temp = obtenerMinAux(aux.getDerecho());
        aux.setClave(temp.getClave());
        aux.setDato(temp.getDato());
        eliminarAux(aux, temp.getClave());
        return aux;
    }
    
    private int alturaAux(NodoAVLDicc aux) {
        int suma;
        if (aux == null) {
            suma = -1;
        } else {
            suma = 1 + Math.max(alturaAux(aux.getIzquierdo()),
                    alturaAux(aux.getDerecho()));
        }
        return suma;
    }
    
    private NodoAVLDicc obtenerMinAux(NodoAVLDicc aux) {
        NodoAVLDicc res;
        if (aux.getIzquierdo() == null) {
            res = aux;
        } else {
            res = obtenerMinAux(aux.getIzquierdo());
        }
        return res;
    }

    public boolean existeClave(Comparable clave) {
        boolean res = false;
        if (raiz != null) {
            res = existeClaveAux(raiz, clave);
        }
        return res;
    }

    private boolean existeClaveAux(NodoAVLDicc aux, Comparable clave) {
        boolean res = false;
        if (aux != null) {
            if (clave.equals(aux.getClave())) {
                res = true;
            } else {
                if (clave.compareTo(aux.getClave()) < 0) {
                    res = existeClaveAux(aux.getIzquierdo(), clave);
                } else {
                    res = existeClaveAux(aux.getDerecho(), clave);
                }
            }
        }
        return res;
    }
    
    public Object obtenerInformacion(Comparable clave) {
       Object res = null;
        if (raiz != null) {
            res = obtenerInformacionAux(raiz, clave);
        }
        return res;
    }

    private Object obtenerInformacionAux(NodoAVLDicc aux, Comparable clave) {
        Object dato = null;
        if (aux != null) {
            if (clave.equals(aux.getClave())) {
                dato = aux.getDato();
            } else {
                if (clave.compareTo(aux.getClave()) < 0) {
                    dato = obtenerInformacionAux(aux.getIzquierdo(), clave);
                } else {
                    dato = obtenerInformacionAux(aux.getDerecho(), clave);
                }
            }
        }
        return dato;
    }

    public Lista listarClaves() {
        Lista res = new Lista();
        if (raiz != null) {
            res = new Lista();
            listarClavesAux(res, raiz);
        }
        return res;
    }

    private void listarClavesAux(Lista res, NodoAVLDicc aux) {
        if (aux != null) {
            listarClavesAux(res, aux.getIzquierdo());
            res.insertar(aux.getClave(), res.longitud() + 1);
            listarClavesAux(res, aux.getDerecho());
        }
    }

    public Lista listarDatos() {
        Lista res = new Lista();
        if (raiz != null) {
            res = new Lista();
            listarDatosAux(res, raiz);
        }
        return res;
    }
    
    private void listarDatosAux(Lista res, NodoAVLDicc aux) {
        if (aux != null) {
            listarDatosAux(res, aux.getIzquierdo());
            res.insertar(aux.getDato(), res.longitud() + 1);
            listarDatosAux(res, aux.getDerecho());
        }
    }

    public Lista listarRango(Comparable ini, Comparable fin) {
        Lista res = new Lista();
        if (raiz != null) {
            res = new Lista();
            listarRangoAux(res, raiz, ini, fin);
        }
        return res;
    }

    private void listarRangoAux(Lista res, NodoAVLDicc aux, Comparable ini, Comparable fin) {
        if (aux != null) {
            if (aux.getClave().compareTo(ini) > 0) 
                listarRangoAux(res, aux.getIzquierdo(), ini, fin);
            if (aux.getClave().compareTo(ini) >= 0 && aux.getClave().compareTo(fin) <= 0)
                res.insertar(aux.getClave(), res.longitud() + 1); 
            if (aux.getClave().compareTo(fin) < 0) 
                    listarRangoAux(res, aux.getDerecho(), ini, fin);
        }
    }
    
    public boolean esVacio() {
        return (this.raiz == null);
    }
    
    @Override
    public String toString() {
        String res = "";
        if (raiz != null) {
            res += "{";
            res += toStringAux(raiz);
            res += "}";
        }
        else
            res = "{Ø}";
        return res;
    }

    private String toStringAux(NodoAVLDicc aux) {
        String res = aux.getClave().toString();
        res += "(";
        if (aux.getIzquierdo() != null) {
            res += aux.getIzquierdo().getClave().toString();
        } else {
            res += "null";
        }
        res += ", ";
        if (aux.getDerecho() != null) {
            res += aux.getDerecho().getClave().toString();
        } else {
            res += "null";
        }
        res += ")";
        if (aux.getIzquierdo() != null) {
            res += ", "+"\n"+ toStringAux(aux.getIzquierdo());
        }
        if (aux.getDerecho() != null) {
            res += ", "+"\n"+ toStringAux(aux.getDerecho());
        }
        return res;
    }
}