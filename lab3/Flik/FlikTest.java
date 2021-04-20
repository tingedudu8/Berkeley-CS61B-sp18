import static org.junit.Assert.*;

import org.junit.Test;

public class FlikTest {
    @Test
    public void testFlik() {
        assertTrue(Flik.isSameNumber(111, 111));
        assertFalse(Flik.isSameNumber(111, 123));
    }
}
