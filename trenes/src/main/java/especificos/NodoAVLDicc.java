package especificos;

public class NodoAVLDicc {
    
    private Comparable clave;
    private Object dato;
    private NodoAVLDicc izquierdo;
    private NodoAVLDicc derecho;
    //private int altura;

    public NodoAVLDicc(Comparable clave, Object dato) {
        this.clave = clave;
        this.dato = dato;
        this.izquierdo = null;
        this.derecho = null;
        //this.calcularAltura();
    }
    
    public NodoAVLDicc(Comparable clave, Object dato, NodoAVLDicc izquierdo, NodoAVLDicc derecho) {
        this.clave = clave;
        this.dato = dato;
        this.izquierdo = izquierdo;
        this.derecho = derecho;
        //this.calcularAltura();
    }

    public Comparable getClave() {
        return clave;
    }

    public void setClave(Comparable clave) {
        this.clave = clave;
    }
    
    public Object getDato() {
        return dato;
    }

    public void setDato(Object dato) {
        this.dato = dato;
    }

    public NodoAVLDicc getIzquierdo() {
        return izquierdo;
    }

    public void setIzquierdo(NodoAVLDicc izquierdo) {
        this.izquierdo = izquierdo;
    }

    public NodoAVLDicc getDerecho() {
        return derecho;
    }

    public void setDerecho(NodoAVLDicc derecho) {
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
