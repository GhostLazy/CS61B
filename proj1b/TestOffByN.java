import org.junit.Test;
import static org.junit.Assert.*;

public class TestOffByN {
    @Test
    public void testOffByN() {
        OffByN offByN1 = new OffByN(2);
        assertTrue(offByN1.equalChars('a', 'c'));
        assertTrue(offByN1.equalChars('i', 'k'));
        assertFalse(offByN1.equalChars('j', 'm'));

        OffByN offByN2 = new OffByN(5);
        assertTrue(offByN2.equalChars('a', 'f'));
        assertTrue(offByN2.equalChars('i', 'd'));
        assertFalse(offByN2.equalChars('j', 'm'));
    }
}
