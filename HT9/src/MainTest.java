import org.junit.*;

import static org.junit.Assert.assertEquals;

public class MainTest {

    @Test
    public void testBinarioADecimal() {
        assertEquals(10, Main.binarioADecimal(1010));
        assertEquals(27, Main.binarioADecimal(11011));
        assertEquals(0, Main.binarioADecimal(0));
        assertEquals(1, Main.binarioADecimal(1));
    }
}
