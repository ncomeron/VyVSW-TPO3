package conjuntistas;

public class NodoAVL {
    
    private Comparable elem;
    private NodoAVL izquierdo;
    private NodoAVL derecho;

    public NodoAVL(Comparable elem) {
        this.elem = elem;
        this.izquierdo = null;
        this.derecho = null;
    }
    
    public NodoAVL(Comparable elem, NodoAVL izquierdo, NodoAVL derecho) {
        this.elem = elem;
        this.izquierdo = izquierdo;
        this.derecho = derecho;
    }

    public Comparable getElem() {
        return elem;
    }

    public void setElem(Comparable elem) {
        this.elem = elem;
    }

    public NodoAVL getIzquierdo() {
        return izquierdo;
    }

    public void setIzquierdo(NodoAVL izquierdo) {
        this.izquierdo = izquierdo;
    }

    public NodoAVL getDerecho() {
        return derecho;
    }

    public void setDerecho(NodoAVL derecho) {
        this.derecho = derecho;
    }
}