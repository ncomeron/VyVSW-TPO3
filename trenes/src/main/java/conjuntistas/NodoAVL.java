package conjuntistas;

public class NodoAVL {
    
    private Comparable elem;
    private NodoAVL izquierdo;
    private NodoAVL derecho;
    //private int altura;

    public NodoAVL(Comparable elem) {
        this.elem = elem;
        this.izquierdo = null;
        this.derecho = null;
        //this.calcularAltura();
    }
    
    public NodoAVL(Comparable elem, NodoAVL izquierdo, NodoAVL derecho) {
        this.elem = elem;
        this.izquierdo = izquierdo;
        this.derecho = derecho;
        //this.calcularAltura();
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
    
    /*
    public void calcularAltura() {
        int altHI = -1;
        int altHD = -1;
        if (this.getDerecho() != null) 
            altHD = this.getDerecho().getAltura();
        if (this.getIzquierdo() != null) 
            altHI = this.getIzquierdo().getAltura();
        this.altura = (Math.max(altHD, altHI) + 1);
    }
    
    public int getAltura() {
        return altura;
    }

    public void setAltura(int altura) {
        this.altura = altura;
    }
    */
}
