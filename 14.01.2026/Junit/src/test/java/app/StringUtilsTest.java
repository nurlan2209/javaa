package app;

import org.junit.Test;
import static org.junit.Assert.*;

public class StringUtilsTest {

    @Test
    public void testToUpperCase() {
        StringUtils utils = new StringUtils();
        assertEquals("HELLO", utils.toUpperCase("hello"));
    }

    @Test
    public void testLength() {
        StringUtils utils = new StringUtils();
        assertEquals(5, utils.length("JUnit"));
    }
}
