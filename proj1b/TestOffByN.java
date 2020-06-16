import org.junit.Test;
import static org.junit.Assert.*;

public class TestOffByN {
    static CharacterComparator offByN = new OffByN(8);

    @Test
    public void testEqualChars() {
        assertTrue(offByN.equalChars('a', 'i'));
        assertTrue(offByN.equalChars('i', 'a'));
        assertTrue(offByN.equalChars('b', 'j'));
        assertFalse(offByN.equalChars('c', 'l'));
    }
}
