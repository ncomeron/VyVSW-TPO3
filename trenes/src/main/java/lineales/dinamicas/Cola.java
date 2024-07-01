package lineales.dinamicas;
/**
 *
 * @author Nicolas
 */
public class Cola {

    private Nodo frente;
    private Nodo fin;

    public Cola() {
        this.frente = null;
        this.fin = null;
    }

    public boolean poner(Object elem) {
        Nodo nuevo = new Nodo(elem, null);

        if (this.esVacia()) {
            this.frente = nuevo;
        } else {
            this.fin.setEnlace(nuevo);
        }
        this.fin = nuevo;

        return true;
    }

    public boolean sacar() {
        boolean exito = (this.frente != null);

        if (exito) {
            this.frente = this.frente.getEnlace();

            if (this.frente == null) {
                this.fin = null;
            }
        }
        return exito;
    }

    public Object obtenerFrente() {
        Object res = null;

        if (!this.esVacia()) {
            res = this.frente.getElem();
        }

        return res;
    }

    public boolean esVacia() {
        return (this.frente == null);
    }

    public void vaciar() {
        this.frente = null;
        this.fin = null;
    }

    public Cola clonar() {
        Cola nueva = new Cola();

        if (!this.esVacia()) {
            Nodo aux = new Nodo(this.frente.getElem());
            nueva.frente = aux;

            aux = this.frente.getEnlace();
            nueva.fin = nueva.frente;

            while (aux != null) {
                Nodo nuevo = new Nodo(aux.getElem());

                nueva.fin.setEnlace(nuevo);
                nueva.fin = nueva.fin.getEnlace();

                aux = aux.getEnlace();
            }
        }
        return nueva;
    }

    public String toString() {
        String s = "[]";
        Object elem = null;

        if (!this.esVacia()) {
            s = "[";
            Nodo aux = this.frente;

            while (aux != null) {
                elem = aux.getElem();

                // el objeto almacenado es de tipo String?
                if (elem.getClass().equals(String.class)) {
                    s += (String) elem;
                } else {
                    // el objeto almacenado es de tipo Integer?
                    if (elem.getClass().equals(Integer.class)) {
                        s += (Integer) elem;
                    } else{
                        // será de tipo char?
                        if (elem.getClass().equals(char.class)) {
                            s += (char) elem;
                        } else{
                            // nada conocido, concatenamos como está
                            s += elem; 
                        }
                    }
                }
                aux = aux.getEnlace();

                if (aux != null) {
                    s += ", ";
                }
            }
            s += "]";
        }
        return s;

    }
}
