package hw9;

import static org.junit.Assert.*;

import org.junit.Test;

public class ValidateTest {
    /*
     * Alphabet is ASCII characters 48-57, 65-90, and 97-122 for now
     * TODO: Add escape functionality to handle special chars in regex
     * TODO: Add more operators (currently only |, ., *)
     * TODO: Implement infix -> postfix conversion
     * TODO: Implement NFA to DFA
     */

    @Test
    public void singleChar() {
        Validate val = new Validate();
        for (int i = 48; i <= 57; i++) {
            val.setRegex(Character.toString((char) i));
            val.setValidate(Character.toString((char) i));
            String message = String.format("Failed on %d: %c", i, (char) i);
            assertTrue(message, val.matches());
        }
        for (int i = 65; i <= 90; i++) {
            val.setRegex(Character.toString((char) i));
            val.setValidate(Character.toString((char) i));
            String message = String.format("Failed on %d: %c", i, (char) i);
            assertTrue(message, val.matches());
        }
        for (int i = 97; i <= 122; i++) {
            val.setRegex(Character.toString((char) i));
            val.setValidate(Character.toString((char) i));
            String message = String.format("Failed on %d: %c", i, (char) i);
            assertTrue(message, val.matches());
        }
    }

    @Test
    public void concatTest() {
        Validate val = new Validate();

        val.setRegex("ab.cd..");
        val.setValidate("dcba");
        assertTrue(val.matches());
        val.setRegex("dc.ba..");
        val.setValidate("abcd");
        assertTrue(val.matches());
        val.setValidate("ab");
        assertFalse(val.matches());
        val.setValidate("abcc");
        assertFalse(val.matches());

        val.setRegex("ab.cd.ef.gh....");
        val.setValidate("hgfedcba");
        assertTrue(val.matches());
        val.setValidate("abcdefgh");
        assertFalse(val.matches());
    }
}
