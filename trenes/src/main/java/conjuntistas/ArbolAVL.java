package conjuntistas;

/* Ineficiencia a la hora de autobalancear debido al alturaAux(), hay que agregar atrib altura al nodo */

import lineales.dinamicas.Lista;

public class ArbolAVL {
    
    private NodoAVL raiz;
    
    public ArbolAVL() {
        this.raiz = null;
    }
    
    public boolean insertar(Comparable elem) {
        boolean res = true;
        if (raiz == null) {
            raiz = new NodoAVL(elem);
        } else {
            res = insertarAux(raiz, elem);
        }
        if (res) {
            autobalancear(raiz);
        }
        return res;
    }
    
    private boolean insertarAux(NodoAVL aux, Comparable elem) {
        boolean res = true;
        if (elem.equals(aux.getElem())) {
            res = false;
        } else {
            if (elem.compareTo(aux.getElem()) < 0) {
                if (aux.getIzquierdo() != null) {
                    res = insertarAux(aux.getIzquierdo(), elem);
                    //aux.calcularAltura();
                } else {
                    aux.setIzquierdo(new NodoAVL(elem));
                    //aux.calcularAltura();
                }
            } else {
                if (aux.getDerecho() != null) {
                    res = insertarAux(aux.getDerecho(), elem);
                    //aux.calcularAltura();
                } else {
                    aux.setDerecho(new NodoAVL(elem));
                    //aux.calcularAltura();
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
    
    public boolean eliminar(Comparable elem) {
        boolean res = false;
        if (raiz != null) {
            if (raiz.getElem().equals(elem)) {
                if (esHoja(raiz)) {
                    raiz = null;
                    //raiz.calcularAltura();
                } else if (soloHijoIzq(raiz)) {
                    raiz = raiz.getIzquierdo();
                    //raiz.calcularAltura();
                } else if (soloHijoDer(raiz)) {
                    raiz = raiz.getDerecho();
                    //raiz.calcularAltura();
                } else {
                    raiz = tercerCaso(raiz);
                }
                res = true;
            } else {
                res = eliminarAux(raiz, elem);
            }
        }
        if (res) {
            autobalancear(raiz);
        }
        return res;
    }
    
    private boolean eliminarAux(NodoAVL aux, Comparable elem) {
        boolean res = false;
        if (aux != null) {
            if ((aux.getIzquierdo() != null) && (aux.getIzquierdo().getElem().equals(elem))) {
                if (esHoja(aux.getIzquierdo())) {
                    aux.setIzquierdo(null);
                    //aux.calcularAltura();
                } else if (soloHijoIzq(aux.getIzquierdo())) {
                    aux.setIzquierdo(aux.getIzquierdo().getIzquierdo());
                    //aux.calcularAltura();
                } else if (soloHijoDer(aux.getIzquierdo())) {
                    aux.setIzquierdo(aux.getIzquierdo().getDerecho());
                    //aux.calcularAltura();
                } else {
                    aux.setIzquierdo(tercerCaso(aux.getIzquierdo()));
                    //aux.calcularAltura();
                }
                res = true;
            } else if ((aux.getDerecho() != null) && (aux.getDerecho().getElem().equals(elem))) {
                if (esHoja(aux.getDerecho())) {
                    aux.setDerecho(null);
                    //aux.calcularAltura();
                } else if (soloHijoIzq(aux.getDerecho())) {
                    aux.setDerecho(aux.getDerecho().getIzquierdo());
                    //aux.calcularAltura();
                } else if (soloHijoDer(aux.getDerecho())) {
                    aux.setDerecho(aux.getDerecho().getDerecho());
                    //aux.calcularAltura();
                } else {
                    aux.setDerecho(tercerCaso(aux.getDerecho()));
                    //aux.calcularAltura();
                }
                res = true;
            } else {
                res = eliminarAux(aux.getIzquierdo(), elem);
                if (!res) {
                    res = eliminarAux(aux.getDerecho(), elem);
                }
            }
        }
        if (res) {
            autobalancear(aux);
        }
        return res;
    }
    
    private NodoAVL autobalancear(NodoAVL n) {
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
    
    /*
    private NodoAVL autobalancear(NodoAVL n) {
        int balanceP = calcularBalance(n);
        
        if (balanceP < -1)  {
            NodoAVL aux = n.getDerecho();
            int balanceHD = calcularBalance(aux);
            
            if (balanceHD < 0) {
                rotacionSimpleIzq(n);
            } else {
                rotacionSimpleDer(n.getDerecho());
                rotacionSimpleIzq(n);
            }
        } else if (balanceP > 1) {
            NodoAVL aux = n.getIzquierdo();
            int balanceHI = calcularBalance(aux);
            
            if (balanceHI > 0) {
                rotacionSimpleDer(n);
            } else {
                rotacionSimpleIzq(n.getIzquierdo());
                rotacionSimpleDer(n);
            }
        }
        return n;
    }
    
    private int calcularBalance(NodoAVL n) {
        int balance;
        if (n.getIzquierdo() != null && n.getDerecho() != null)
            balance = (n.getIzquierdo().getAltura()) - (n.getDerecho().getAltura());
        else {
            if (n.getIzquierdo() == null && n.getDerecho() == null)
                balance = 0;
            else {
                if (n.getIzquierdo() != null && n.getDerecho() == null) 
                    balance = (n.getIzquierdo().getAltura() - (-1));
                else
                    balance = ((-1) - (n.getDerecho().getAltura())); 
            }
        }
        return balance;
    }
    */

    private NodoAVL rotacionSimpleIzq(NodoAVL n) {
        Comparable aux = n.getElem();
        n.setElem(n.getDerecho().getElem());
        NodoAVL temp = n.getDerecho().getIzquierdo();
        n.setDerecho(n.getDerecho().getDerecho());
        if (n.getIzquierdo() != null) {
            NodoAVL nuevoIzq = new NodoAVL(aux);
            nuevoIzq.setIzquierdo(n.getIzquierdo());
            n.setIzquierdo(nuevoIzq);
            n.getIzquierdo().setDerecho(temp);
        } else {
            n.setIzquierdo(new NodoAVL(aux));
            n.getIzquierdo().setDerecho(temp);
        }
        return n;
    }

    private NodoAVL rotacionSimpleDer(NodoAVL n) {
        Comparable aux = n.getElem();
        n.setElem(n.getIzquierdo().getElem());
        NodoAVL temp = n.getIzquierdo().getDerecho();
        n.setIzquierdo(n.getIzquierdo().getIzquierdo());
        if (n.getDerecho() != null) {
            NodoAVL nuevoDer = new NodoAVL(aux);
            nuevoDer.setDerecho(n.getDerecho());
            n.setDerecho(nuevoDer);
            n.getDerecho().setIzquierdo(temp);
        } else {
            n.setDerecho(new NodoAVL(aux));
            n.getDerecho().setIzquierdo(temp);
        }
        return n;
    }
    
    private boolean esHoja(NodoAVL aux) {
        return ((aux.getDerecho() == null) && (aux.getIzquierdo() == null));
    }

    private boolean soloHijoDer(NodoAVL aux) {
        return ((aux.getDerecho() != null) && (aux.getIzquierdo() == null));
    }

    private boolean soloHijoIzq(NodoAVL aux) {
        return ((aux.getDerecho() == null) && (aux.getIzquierdo() != null));
    }

    private NodoAVL tercerCaso(NodoAVL aux) {
        NodoAVL temp;
        temp = obtenerMinAux(aux.getDerecho());
        aux.setElem(temp.getElem());
        eliminarAux(aux, temp.getElem());
        return aux;
    }
    
    public int altura() {
        int res = -1;
        if (raiz != null) {
            res = alturaAux(raiz);
        }
        return res;
    }

    private int alturaAux(NodoAVL aux) {
        int suma;
        if (aux == null) {
            suma = -1;
        } else {
            suma = 1 + Math.max(alturaAux(aux.getIzquierdo()),
                    alturaAux(aux.getDerecho()));
        }
        return suma;
    }
    
    public boolean pertenece(Comparable elem) {
        boolean res = false;
        if (raiz != null) {
            res = perteneceAux(raiz, elem);
        }
        return res;
    }

    private boolean perteneceAux(NodoAVL aux, Comparable elem) {
        boolean res = false;
        if (aux != null) {
            if (elem.equals(aux.getElem())) {
                res = true;
            } else {
                if (elem.compareTo(aux.getElem()) < 0) {
                    res = perteneceAux(aux.getIzquierdo(), elem);
                } else {
                    res = perteneceAux(aux.getDerecho(), elem);
                }
            }
        }
        return res;
    }

    public boolean esVacio() {
        return (raiz == null);
    }

    public Comparable obtenerMin() {
        Comparable res;
        if (raiz != null) {
            res = obtenerMinAux(raiz).getElem();
        } else {
            res = null;
        }
        return res;
    }

    private NodoAVL obtenerMinAux(NodoAVL aux) {
        NodoAVL res;
        if (aux.getIzquierdo() == null) {
            res = aux;
        } else {
            res = obtenerMinAux(aux.getIzquierdo());
        }
        return res;
    }

    public void eliminarMin() {
        if (raiz != null) {
            raiz = eliminarMinAux(raiz);
        }
    }

    private NodoAVL eliminarMinAux(NodoAVL aux) {
        if (aux.getIzquierdo() == null) {
            aux = aux.getDerecho();
        } else {
            aux.setIzquierdo(eliminarMinAux(aux.getIzquierdo()));
        }
        return aux;
    }

    public Comparable obtenerMax() {
        Comparable res;
        if (raiz != null) {
            res = obtenerMaxAux(raiz).getElem();
        } else {
            res = null;
        }
        return res;
    }

    private NodoAVL obtenerMaxAux(NodoAVL aux) {
        NodoAVL res;
        if (aux.getDerecho() == null) {
            res = aux;
        } else {
            res = obtenerMaxAux(aux.getDerecho());
        }
        return res;
    }

    public void eliminarMax() {
        if (raiz != null) {
            raiz = eliminarMaxAux(raiz);
        }
    }

    private NodoAVL eliminarMaxAux(NodoAVL aux) {
        if (aux.getDerecho() == null) {
            aux = aux.getIzquierdo();
        } else {
            aux.setDerecho(eliminarMaxAux(aux.getDerecho()));
        }
        return aux;
    }

    public Lista listar() {
        Lista res = new Lista();
        if (raiz != null) {
            res = new Lista();
            listarAux(res, raiz);
        }
        return res;
    }

    private void listarAux(Lista res, NodoAVL aux) {
        if (aux != null) {
            listarAux(res, aux.getIzquierdo());
            res.insertar(aux.getElem(), res.longitud() + 1);
            listarAux(res, aux.getDerecho());
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

    private void listarRangoAux(Lista res, NodoAVL aux, Comparable ini, Comparable fin) {
        if (aux != null) {
            if (aux.getElem().compareTo(ini) > 0) {
                listarRangoAux(res, aux.getIzquierdo(), ini, fin);
            }
            if (aux.getElem().compareTo(ini) >= 0 && aux.getElem().compareTo(fin) <= 0) {
                res.insertar(aux.getElem(), res.longitud() + 1);
            }
            if (aux.getElem().compareTo(fin) < 0) {
                listarRangoAux(res, aux.getDerecho(), ini, fin);
            }
        }
    }

    public void vaciar() {
        raiz = null;
    }

    public ArbolAVL clonar() {
        ArbolAVL clon = new ArbolAVL();
        if (raiz != null) {
            clon.raiz = new NodoAVL(raiz.getElem());
            clonarAux(raiz, clon.raiz);
        }
        return clon;
    }

    private void clonarAux(NodoAVL aux, NodoAVL nuevo) {
        if (aux.getIzquierdo() != null) {
            nuevo.setIzquierdo(new NodoAVL(aux.getIzquierdo().getElem()));
            clonarAux(aux.getIzquierdo(), nuevo.getIzquierdo());
        }
        if (aux.getDerecho() != null) {
            nuevo.setDerecho(new NodoAVL(aux.getDerecho().getElem()));
            clonarAux(aux.getDerecho(), nuevo.getDerecho());
        }
    }

    @Override
    public String toString() {
        String res = "";
        if (raiz != null) {
            res += "[";
            res += toStringAux(raiz);
            res += "]";
        }
        return res;
    }

    private String toStringAux(NodoAVL aux) {
        String res = aux.getElem().toString();
        res += "(";
        if (aux.getIzquierdo() != null) {
            res += aux.getIzquierdo().getElem().toString();
        } else {
            res += "null";
        }
        res += ", ";
        if (aux.getDerecho() != null) {
            res += aux.getDerecho().getElem().toString();
        } else {
            res += "null";
        }
        res += ")";
        if (aux.getIzquierdo() != null) {
            res += ", " + toStringAux(aux.getIzquierdo());
        }
        if (aux.getDerecho() != null) {
            res += ", " + toStringAux(aux.getDerecho());
        }
        return res;
    }
}
