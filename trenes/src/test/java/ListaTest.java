
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.Mockito.*;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import lineales.dinamicas.*;

class ListaTest {

    // prueba simple de insertar en una Lista
    @Test
    void testInsertar() {
        Lista lista = new Lista();
        lista.insertar(1, 1);
        assertEquals(1, lista.recuperar(1));
    }

    // prueba de eliminación de un elemento
    /*
     * @Test
     * void testEliminar() {
     * Lista lista = new Lista();
     * lista.insertar(1, 1);
     * lista.eliminar(1);
     * assertThrows(IndexOutOfBoundsException.class, () -> lista.recuperar(1));
     * }
     */

    // prueba de multiples inserciones y eliminaciones
    @Test
    void testMultipleInsertarYEliminar() {
        Lista lista = new Lista();
        lista.insertar(1, 0);
        lista.insertar(2, 1);
        lista.insertar(3, 2);
        assertEquals(3, lista.longitud());

        lista.eliminar(1);
        assertEquals(2, lista.longitud());
        assertEquals(3, lista.recuperar(1));
    }

    // Simulamos el comportamiento de la lista para verificar la inserción sin
    // realmente modificar la lista.
    @Test
    void testInsertarConMockito() {
        Lista mockLista = mock(Lista.class);
        mockLista.insertar(1, 1);
        mockLista.insertar(2, 2);

        verify(mockLista, times(1)).insertar(1, 1);
    }

    // Simulamos la recuperación de un elemento en una posición específica.
    @Test
    void testRecuperarConMockito() {
        Lista mockLista = mock(Lista.class);
        when(mockLista.recuperar(1)).thenReturn(1);

        assertEquals(1, mockLista.recuperar(1));
        verify(mockLista, times(1)).recuperar(0);
    }
    /*
     * // Simulamos la eliminación de un elemento en una posición específica
     * 
     * @Test
     * void testEliminarConMockito() {
     * Lista mockLista = mock(Lista.class);
     * doNothing().when(mockLista).eliminar(1);
     * 
     * mockLista.eliminar(0);
     * verify(mockLista, times(1)).eliminar(0);
     * }
     */

    // Simulamos la obtención del tamaño de la lista.
    @Test
    void testLongitudConMockito() {
        Lista mockLista = mock(Lista.class);
        when(mockLista.longitud()).thenReturn(3);

        assertEquals(3, mockLista.longitud());
        verify(mockLista, times(1)).longitud();
    }

    // Simulamos un comportamiento más complejo con múltiples operaciones.
    @Test
    void testComportamientoComplejo() {
        Lista mockLista = mock(Lista.class);

        mockLista.insertar(1, 1);
        when(mockLista.recuperar(1)).thenReturn(1);
        when(mockLista.recuperar(1)).thenReturn(2);
        when(mockLista.longitud()).thenReturn(2);

        mockLista.insertar(2, 2);
        mockLista.insertar(3, 3);

        assertEquals(2, mockLista.recuperar(2));
        assertEquals(3, mockLista.recuperar(3));
        assertEquals(2, mockLista.longitud());

        verify(mockLista, times(2)).insertar(anyInt(), anyInt());
        verify(mockLista, times(2)).recuperar(anyInt());
        verify(mockLista, times(1)).longitud();
    }

}




