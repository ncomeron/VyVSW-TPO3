import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;

import especificos.Diccionario;

import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.assertArrayEquals;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.ArgumentMatchers.argThat;
import static org.mockito.Mockito.doReturn;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.mockito.Mockito.times;

import java.util.HashMap;

import org.mockito.Spy;

import grafos.Grafo;
import lineales.dinamicas.Lista;
import trenesSubterraneos.Estacion;
import trenesSubterraneos.TrenesSubterraneos;


@ExtendWith(MockitoExtension.class)
public class TrenesSubterraneosTest {
    
    //Dependencias
    //@Mock
    //private Grafo redRieles;

    //@Mock
    //private Diccionario estaciones;

    @Spy
    private HashMap trenesSpy = new HashMap<>();
    //private HashMap<String, String> trenesSpy = new HashMap<>();

    @Spy
    private HashMap lineasSpy = new HashMap();
    //private HashMap<String, String> lineasSpy = new HashMap<>();

    @Spy
    private Grafo redDeRieles = new Grafo();
    
    @Spy
    private Diccionario estaciones = new Diccionario();
    //Clase a testear
    private TrenesSubterraneos subte;

    /* 
    @BeforeEach
    public void setup() {
        estaciones = mock(Diccionario.class);
        subte = new TrenesSubterraneos(estaciones);
    }
    */

    @BeforeEach
    public void setup() {
        subte = new TrenesSubterraneos(trenesSpy, lineasSpy,redDeRieles,estaciones);
    }

    @Test
    public void testAltaTren() {
        doReturn(false).when(trenesSpy).containsKey(anyString());
        doReturn(false).when(lineasSpy).containsKey(anyString());
        subte.altaTren("001", "A");

        assertTrue(trenesSpy.isEmpty());
        assertEquals(0, lineasSpy.size());
        assertNull(lineasSpy.get("A"));

        verify(lineasSpy).containsKey(anyString());
        //verify(lineasSpy, times(2)).containsKey(anyString());  DESCOMENTAR
        
        //assertThat(aSpyList).hasSize(2);
    }

    @Test
    public void testAltaTramoDeRieles() {
        // Definimos los valores de prueba para origen, destino, y tiempo.
        String origen = "A";
        String destino = "B";
        int tiempo = 10;

        /*Configuramos el comportamiento del método insertarArco del objeto espía redDeRieles para 
        que devuelva true cuando se llame con los argumentos especificados. Luego llamamos al método altaTramoDeRieles.*/
        doReturn(true).when(redDeRieles).insertarArco(origen, destino, tiempo);
        boolean resultado = subte.altaTramoDeRieles(origen, destino, tiempo);

        /* Verificamos que el resultado sea true y que el método insertarArco del objeto 
        espía redDeRieles haya sido llamado exactamente una vez con los argumentos especificados. */
        assertTrue(resultado);
        verify(redDeRieles, times(1)).insertarArco(origen, destino, tiempo);
    }

    @Test
    public void testBajaTramoDeRieles() {
        String origen = "A";
        String destino = "B";

    
        doReturn(true).when(redDeRieles).eliminarArco(origen, destino);
        boolean resultado = subte.bajaTramoDeRieles(origen, destino);

    
        assertTrue(resultado);
        verify(redDeRieles, times(1)).eliminarArco(origen, destino);
    }

    @Test
    public void testCaminoMenosEstaciones() {
        String origen = "Origen";
        String destino = "Destino";
        
        // Lista esperada para el camino más corto
        Lista listaEsperada = new Lista();
        listaEsperada.insertar("Estacion1", 1);
        listaEsperada.insertar("Estacion2", 2);
        listaEsperada.insertar("Destino", 3);

        // Configuración del comportamiento esperado en el spy de Grafo
        doReturn(listaEsperada).when(redDeRieles).caminoMasCorto(origen, destino);

        // Llamada al método que queremos probar
        Lista resultado = subte.caminoMenosEstaciones(origen, destino);

        // Verificación de que se obtuvo la lista esperada
        assertEquals(listaEsperada, resultado);
        // Verificación de que se llamó a caminoMasCorto con los parámetros esperados
        verify(redDeRieles, times(1)).caminoMasCorto(origen, destino);
    }

    @Test
    public void testCaminoMenorTiempo() {
        String origen = "Origen";
        String destino = "Destino";

        // Arreglo esperado para el camino más rápido
        Object[] arregloEsperado = new Object[]{"Estacion1", "Estacion2", "Destino"};

        // Configuración del comportamiento esperado en el spy de Grafo
        doReturn(arregloEsperado).when(redDeRieles).caminoMenorTiempo(origen, destino);

        // Llamada al método que queremos probar
        Object[] resultado = subte.caminoMenorTiempo(origen, destino);

        // Verificación que se obtuvo el arreglo esperado
        assertArrayEquals(arregloEsperado, resultado);

        // Verificación que se llamó a caminoMenorTiempo con los parámetros esperados
        verify(redDeRieles, times(1)).caminoMenorTiempo(origen, destino);
    }

    
}
