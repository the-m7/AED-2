import static org.junit.Assert.*;

import org.junit.Test;

public class HashFactoryTest {

    @Test
    public void testGetHashInstanceSHA() {
        IFuncionesHash hash = HashFactory.getHashInstance(1);
        assertNotNull(hash);
        assertTrue(hash instanceof SHA);
    }

    @Test
    public void testGetHashInstanceMD5() {
        IFuncionesHash hash = HashFactory.getHashInstance(2);
        assertNotNull(hash);
        assertTrue(hash instanceof MD5);
    }

    @Test
    public void testGetHashInstanceOrganico() {
        IFuncionesHash hash = HashFactory.getHashInstance(3);
        assertNotNull(hash);
        assertTrue(hash instanceof organico);
    }

    @Test
    public void testGetHashInstanceDefault() {
        IFuncionesHash hash = HashFactory.getHashInstance(0);
        assertNotNull(hash);
        assertTrue(hash instanceof SHA);
    }
}
