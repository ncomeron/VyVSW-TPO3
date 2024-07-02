import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import grafos.Grafo;

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

        // Verificar que existe un camino válido entre Vertice3 y Vertice1 ya que es bidireccional
        Assertions.assertTrue(grafo.existeCamino("Vertice3", "Vertice1"));
    }

}
