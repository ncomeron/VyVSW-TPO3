package lineales.dinamicas;

public class Lista {

    private Nodo cabecera;
    private int longitud;
    
    public Lista (){
       this.cabecera = null;
    }

    public boolean insertar(Object elem, int pos) {
        Nodo nuevo = new Nodo(elem);
        boolean res = true;
        if (cabecera == null) {
            cabecera = nuevo;
        } else if ((pos <= this.longitud() + 1) && (pos > 0)) {
            if (pos == 1) {
                nuevo.setEnlace(cabecera);
                cabecera = nuevo;
            } else {
                Nodo aux = cabecera;
                int i = 1;
                while (i < pos - 1) {
                    aux = aux.getEnlace();
                    i++;
                }
                nuevo.setEnlace(aux.getEnlace());
                aux.setEnlace(nuevo);
            }
        } else {
            res = false;
        }

        return res;
    }

    public boolean eliminar(int pos) {
        boolean res;
        if ((cabecera != null) && (pos <= this.longitud()) && (pos > 0)) {
            if (pos == 1) {
                cabecera = cabecera.getEnlace();
            } else {
                Nodo aux = cabecera;
                int i = 1;
                while (i < pos - 1) {
                    aux = aux.getEnlace();
                    i++;
                }
                aux.setEnlace(aux.getEnlace().getEnlace());
            }
            res = true;
        } else {
            res = false;
        }
        return res;
    }

    public Object recuperar(int pos) {
        Object res = null;
        if ((cabecera != null) && (pos <= this.longitud()) && (pos > 0)) {
            Nodo aux = cabecera;
            int i = 1;
            while (i < pos) {
                aux = aux.getEnlace();
                i++;
            }
            res = aux.getElem();
        }
        return res;
    }

    public int localizar(Object elem) {
        int res = -1;
        boolean flag = false;
        if (cabecera != null) {
            Nodo aux = cabecera;
            int i = 1;
            do {
                if (elem.equals(aux.getElem())) {
                    res = i;
                    flag = true;
                }
                aux = aux.getEnlace();
                i++;
            } while ((aux != null) && (!flag));
        }
        return res;
    }

    public int longitud() {
        int i = 0;
        if (cabecera != null) {
            Nodo aux = cabecera;
            do {
                aux = aux.getEnlace();
                i++;
            } while (aux != null);
        }
        return i;
    }

    public boolean esVacia() {
        return (cabecera == null);
    }

    public void vaciar() {
        cabecera = null;
    }

    public Lista clonar() {
        Lista clon = new Lista();
        clon.cabecera = new Nodo(cabecera.getElem());
        Nodo original = cabecera.getEnlace();
        Nodo aux = clon.cabecera;
        while (original != null) {
            aux.setEnlace(new Nodo(original.getElem()));
            aux = aux.getEnlace();
            original = original.getEnlace();
        }
        return clon;
    }

    public void copiar(Lista lista) {
        if (lista.cabecera != null) {
            this.cabecera = new Nodo(lista.cabecera.getElem());
            Nodo aux = cabecera;
            Nodo auxCopia = lista.cabecera.getEnlace();
            while (auxCopia != null) {
                aux.setEnlace(new Nodo(auxCopia.getElem()));
                aux = aux.getEnlace();
                auxCopia = auxCopia.getEnlace();
            }
        }
    }

    @Override
    public String toString() {
        String res = "";
        if (cabecera != null) {
            Nodo aux = cabecera;
            int i = 1;
            res = "{";
            do {
                res += aux.getElem().toString();
                aux = aux.getEnlace();
                if (aux != null) {
                    res += ", ";
                }
                i++;
            } while (aux != null);
            res += "}";
        }
        else 
            res = "{Ø}";
        return res;
    }
}
