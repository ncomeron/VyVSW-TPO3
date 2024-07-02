import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import grafos.Grafo;
import lineales.dinamicas.Lista;

public class GrafoTest {

    private Grafo grafo;

    @BeforeEach
    public void setUp() {
        grafo = new Grafo();
    }

    @Test
    public void testInsertarYEliminarVertice() {
        // Test insertarVertice
        Assertions.assertTrue(grafo.insertarVertice("Vertice1"));
        Assertions.assertTrue(grafo.existeVertice("Vertice1"));

        // Test eliminarVertice
        Assertions.assertTrue(grafo.eliminarVertice("Vertice1"));
        Assertions.assertFalse(grafo.existeVertice("Vertice1"));
    }

    @Test
    public void testInsertarYEliminarArco() {
        // Test insertarArco y existeArco
        grafo.insertarVertice("Vertice1");
        grafo.insertarVertice("Vertice2");
        Assertions.assertTrue(grafo.insertarArco("Vertice1", "Vertice2", 10));
        Assertions.assertTrue(grafo.existeArco("Vertice1", "Vertice2"));

        // Test eliminarArco
        Assertions.assertTrue(grafo.eliminarArco("Vertice1", "Vertice2"));
        Assertions.assertFalse(grafo.existeArco("Vertice1", "Vertice2"));
    }

    @Test
    public void testExisteArco() {
        grafo.insertarVertice("Vertice1");
        grafo.insertarVertice("Vertice2");
        grafo.insertarArco("Vertice1", "Vertice2", 10);

        // Verificar que existe el arco entre Vertice1 y Vertice2
        Assertions.assertTrue(grafo.existeArco("Vertice1", "Vertice2"));

        // Verificar que no existe un arco incorrecto
        Assertions.assertFalse(grafo.existeArco("Vertice2", "Vertice3"));
    }

    @Test
    public void testExisteCamino() {
        grafo.insertarVertice("Vertice1");
        grafo.insertarVertice("Vertice2");
        grafo.insertarVertice("Vertice3");
        grafo.insertarArco("Vertice1", "Vertice2", 10);
        grafo.insertarArco("Vertice2", "Vertice3", 20);

        // Verificar que existe un camino válido entre Vertice1 y Vertice3
        Assertions.assertTrue(grafo.existeCamino("Vertice1", "Vertice3"));

        // Verificar que existe un camino válido entre Vertice3 y Vertice1 ya que es
        // bidireccional
        Assertions.assertTrue(grafo.existeCamino("Vertice3", "Vertice1"));
    }

    @Test
    public void testCaminoMasCorto() {
        // Configurar el grafo con los nodos y arcos necesarios
        grafo.insertarVertice("A");
        grafo.insertarVertice("B");
        grafo.insertarVertice("C");
        grafo.insertarVertice("D");
        grafo.insertarArco("A", "B", 1);
        grafo.insertarArco("B", "C", 1);
        grafo.insertarArco("C", "D", 1);
        grafo.insertarArco("A", "C", 2);

        // Obtener el camino más corto
        Lista camino = grafo.caminoMasCorto("A", "D");

        // Crear la lista esperada manualmente
        Lista caminoEsperado = new Lista();
        caminoEsperado.insertar("A", 1);
        caminoEsperado.insertar("C", 2);
        caminoEsperado.insertar("D", 3);

        // Comparar elemento por elemento
        Assertions.assertEquals(caminoEsperado.longitud(), camino.longitud());
        for (int i = 1; i <= caminoEsperado.longitud(); i++) {
            Assertions.assertEquals(caminoEsperado.recuperar(i), camino.recuperar(i));
        }
    }

    @Test
    public void testCaminoMasLargo() {
        // Configurar el grafo con vértices y arcos
        grafo.insertarVertice("A");
        grafo.insertarVertice("B");
        grafo.insertarVertice("C");
        grafo.insertarVertice("D");
        
        grafo.insertarArco("A", "B", 1);
        grafo.insertarArco("B", "C", 1);
        grafo.insertarArco("C", "D", 1);
        grafo.insertarArco("A", "C", 2);
        grafo.insertarArco("B", "D", 2);

        // Camino más largo esperado
        Lista caminoEsperado = new Lista();
        caminoEsperado.insertar("A", caminoEsperado.longitud() + 1);
        caminoEsperado.insertar("B", caminoEsperado.longitud() + 1);
        caminoEsperado.insertar("C", caminoEsperado.longitud() + 1);
        caminoEsperado.insertar("D", caminoEsperado.longitud() + 1);

        // Obtener el camino más largo
        Lista caminoObtenido = grafo.caminoMasLargo("A", "D");

        //Comparar elemento por elemento
        Assertions.assertEquals(caminoEsperado.longitud(), caminoObtenido.longitud(), "Las longitudes de las listas no coinciden.");
        for (int i = 1; i <= caminoEsperado.longitud(); i++) {
            Assertions.assertEquals(caminoEsperado.recuperar(i), caminoObtenido.recuperar(i), "Los elementos en la posición " + i + " no coinciden.");
        } 
    }

    @Test
    public void testVaciar() {
        // Configurar el grafo con vértices y arcos
        grafo.insertarVertice("A");
        grafo.insertarVertice("B");
        grafo.insertarVertice("C");
        
        grafo.insertarArco("A", "B", 1);
        grafo.insertarArco("B", "C", 1);

        // Asegurarse de que el grafo no está vacío antes de vaciarlo
        Assertions.assertTrue(grafo.existeVertice("A"));
        Assertions.assertTrue(grafo.existeVertice("B"));
        Assertions.assertTrue(grafo.existeVertice("C"));

        // Vaciar el grafo
        grafo.vaciar();

        // Verificar que el grafo está vacío
        Assertions.assertFalse(grafo.existeVertice("A"));
        Assertions.assertFalse(grafo.existeVertice("B"));
        Assertions.assertFalse(grafo.existeVertice("C"));
        Assertions.assertFalse(grafo.existeVertice("D")); // Vértice que nunca se agregó, debe devolver falso
    }
    @Test
    public void testEsVacio() {
        // Verificar que un grafo recién creado está vacío
        Assertions.assertTrue(grafo.esVacio());

        // Agregar un vértice y verificar que ya no está vacío
        grafo.insertarVertice("A");
        Assertions.assertFalse(grafo.esVacio());

        // Vaciar el grafo y verificar que está vacío nuevamente
        grafo.vaciar();
        Assertions.assertTrue(grafo.esVacio());
    }
    @Test
    public void testCaminoMayorLongitud() {
        // Insertar vértices y arcos en el grafo
        grafo.insertarVertice("A");
        grafo.insertarVertice("B");
        grafo.insertarVertice("C");
        grafo.insertarVertice("D");
        grafo.insertarVertice("E");
    
        grafo.insertarArco("A", "B", 1);
        grafo.insertarArco("B", "C", 1);
        grafo.insertarArco("C", "D", 1);
        grafo.insertarArco("A", "D", 2);
        grafo.insertarArco("D", "E", 1);
        grafo.insertarArco("B", "E", 3);
    
        // Obtener el camino de mayor longitud entre "A" y "E"
        Lista camino = grafo.caminoMayorLongitud("A", "E");
    
        // Crear una lista esperada
        Lista caminoEsperado = new Lista();
        caminoEsperado.insertar("A", caminoEsperado.longitud() + 1);
        caminoEsperado.insertar("D", caminoEsperado.longitud() + 1);
        caminoEsperado.insertar("C", caminoEsperado.longitud() + 1);
        caminoEsperado.insertar("B", caminoEsperado.longitud() + 1);
        caminoEsperado.insertar("E", caminoEsperado.longitud() + 1);
    
        // Verificar que el camino devuelto es igual al esperado
        Assertions.assertEquals(caminoEsperado.longitud(), camino.longitud());
    
        for (int i = 1; i <= caminoEsperado.longitud(); i++) {
            Assertions.assertEquals(caminoEsperado.recuperar(i), camino.recuperar(i));
        }
    }

    @Test
    public void testClonar() {
        // Insertar vértices y arcos en el grafo original
        grafo.insertarVertice("A");
        grafo.insertarVertice("B");
        grafo.insertarVertice("C");
        grafo.insertarArco("A", "B", 1);
        grafo.insertarArco("B", "C", 2);

        // Clonar el grafo
        Grafo grafoClonado = grafo.clonar();

        // Verificar que el grafo clonado es diferente del original
        Assertions.assertNotSame(grafo, grafoClonado);

        // Verificar que los vértices del grafo clonado son iguales a los del original
        Assertions.assertTrue(grafoClonado.existeVertice("A"));
        Assertions.assertTrue(grafoClonado.existeVertice("B"));
        Assertions.assertTrue(grafoClonado.existeVertice("C"));

        // Verificar que los arcos del grafo clonado son iguales a los del original
        Assertions.assertTrue(grafoClonado.existeArco("A", "B"));
        Assertions.assertTrue(grafoClonado.existeArco("B", "C"));
    }
}
