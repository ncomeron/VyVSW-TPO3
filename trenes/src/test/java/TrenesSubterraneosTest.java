import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;

import especificos.Diccionario;

import static org.mockito.Mockito.mock;
import org.mockito.Mock;
import grafos.Grafo;
import trenesSubterraneos.TrenesSubterraneos;


@ExtendWith(MockitoExtension.class)
public class TrenesSubterraneosTest {
    
    //Dependencias
    @Mock
    private Grafo redRieles;

    //@Mock
    private Diccionario estaciones;

    //Clase a testear
    private TrenesSubterraneos subte;

    @BeforeEach
    public void setup() {
        estaciones = mock(Diccionario.class);
        subte = new TrenesSubterraneos(estaciones);
    }

    @Test
    public void test() {

    }
}
