import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.TreeMap;

import org.junit.Test;

public class MapFactoryTest {

    @Test
    public void testGetMapInstanceHashMap() {
        java.util.AbstractMap<String, Estudiante> mapa = MapFactory.getMapInstance(1);
        assertNotNull(mapa);
        assertTrue(mapa instanceof HashMap);
    }

    @Test
    public void testGetMapInstanceTreeMap() {
        java.util.AbstractMap<String, Estudiante> mapa = MapFactory.getMapInstance(2);
        assertNotNull(mapa);
        assertTrue(mapa instanceof TreeMap);
    }

    @Test
    public void testGetMapInstanceLinkedHashMap() {
        java.util.AbstractMap<String, Estudiante> mapa = MapFactory.getMapInstance(3);
        assertNotNull(mapa);
        assertTrue(mapa instanceof LinkedHashMap);
    }

    @Test(expected = IllegalArgumentException.class)
    public void testGetMapInstanceInvalidMapType() {
        MapFactory.getMapInstance(0);
    }
}
