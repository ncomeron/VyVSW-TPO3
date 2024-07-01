/* 
import static org.junit.Assert.*;
import static org.mockito.Mockito.*;

import grafos.Grafo;
import grafos.NodoVert;
import lineales.dinamicas.Lista;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
*/
import grafos.Grafo;

import static org.mockito.Mockito.mock;
//import static org.mockito.Mockito.doReturn;
//import static org.mockito.Mockito.spy;
import static org.mockito.Mockito.when;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
//import org.mockito.InjectMocks;
import org.mockito.Mock;
//import org.mockito.Spy;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
public class GrafoTest {
    @Mock
    Grafo grafoRieles;                           
    boolean a;
    //@Spy
    //Grafo grafoSpy = new Grafo();

    //@Mock

    //@InjectMocks 
    //private Grafo grafoRieles;
    @BeforeEach
    public void setUp() {
        a = true;
    }
    @Test
    public void testGrafo()  {



        when(grafoRieles.insertarVertice("A")).thenReturn(true);
        assertEquals(grafoRieles.insertarVertice("A"), true);

        
        when(grafoRieles.esVacio()).thenReturn(true);
        //when(grafoRieles.esVacio()).thenReturn(new Grafo());

        assertTrue(grafoRieles.esVacio());
        //assertFalse(a);
        

        // spy
        //doReturn(true).when(grafoSpy).esVacio();
        //assertTrue(grafoSpy.esVacio());
        //assertEquals(true, grafoSpy.esVacio());
    }

} 

/*
@RunWith(MockitoJUnitRunner.class)
public class GrafoTest {

    @InjectMocks
    private Grafo grafo;

    @Mock
    private NodoVert nodoOrigen;

    @Mock
    private NodoVert nodoDestino;

    @Mock
    private Lista caminoMock;

    @Mock
    private Lista caminoMasCortoMock;
   
    // Clase bajo prueba
    //private Grafo grafo;

    @Before
    public void setUp() {
        // Inicializar la clase bajo prueba
        //grafo = new Grafo();
        grafo = mock(Grafo.class);
    }

    @Test 
    public void testInsertarVertice() {
        when(grafo.insertarVertice("A")).thenReturn(true);
    }
    /* 
    @Test
    public void testInsertarVertice() {
        assertTrue(grafo.insertarVertice("A"));
        assertTrue(grafo.insertarVertice("B"));
        assertFalse(grafo.insertarVertice("A")); // Ya existe "A"
    }

    @Test
    public void testEliminarVertice() {
        grafo.insertarVertice("A");
        assertTrue(grafo.eliminarVertice("A"));
        assertFalse(grafo.eliminarVertice("B")); // No existe "B"
    }

    @Test
    public void testInsertarEliminarArco() {
        grafo.insertarVertice("A");
        grafo.insertarVertice("B");
        assertTrue(grafo.insertarArco("A", "B", 10));
        assertTrue(grafo.existeArco("A", "B"));
        assertTrue(grafo.eliminarArco("A", "B"));
        assertFalse(grafo.existeArco("A", "B"));
    }

    @Test
    public void testExisteCamino() {
        grafo.insertarVertice("A");
        grafo.insertarVertice("B");
        grafo.insertarArco("A", "B", 10);
        assertTrue(grafo.existeCamino("A", "B"));
        assertTrue(grafo.existeCamino("B", "A"));
        //assertFalse(grafo.existeCamino("B", "A"));
    }

    /* 
    @Test
    public void testListarArcos() {
        grafo.insertarVertice("A");
        grafo.insertarVertice("B");
        grafo.insertarArco("A", "B", 10);
        Lista arcList = grafo.listarArcos();
        assertEquals("{[A, B, 10min]}", arcList.toString());
    }
    */
    // Agrega más tests según los métodos que desees cubrir

//}