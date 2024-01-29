package src;
import static org.junit.Assert.assertArrayEquals;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import org.junit.Test;
import src.Radio;

public class RadioTest {
	IRadio miRad = new Radio();
	
	@Test
	public void testCambioBinario() {
		assertTrue(miRad.cambioBinario(false));
		assertFalse(miRad.cambioBinario(true));
	}
	
	@Test
	public void testGuardarAM() {
		int[] listaAM = new int[12];
		listaAM = miRad.guardarAM(listaAM, 1, 570);
		assertEquals(570, listaAM[1]);
	}
	
    @Test
    public void testGuardarFM() {
        double[] listaFM = new double[12];
        listaFM = miRad.guardarFM(listaFM, 2, 98.5);
        assertEquals(98.5, listaFM[2], 0.001); 
    }

    @Test
    public void testAvanzarAM() {
        int estacionActual = 530;
        int nuevaEstacion = miRad.avanzarAM(estacionActual);
        assertEquals(540, nuevaEstacion);
    }

    @Test
    public void testAvanzarFM() {
        double estacionActual = 87.9;
        double nuevaEstacion = miRad.avanzarFM(estacionActual);
        assertEquals(88.1, nuevaEstacion, 0.001); 
    }

}
