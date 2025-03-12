package hw9;

import static org.junit.Assert.*;

import org.junit.Test;

public class ValidateTest {

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

    @Test
    public void alternationTest() {
        Validate val = new Validate();

        val.setRegex("ab|");
        val.setValidate("a");
        assertTrue(val.matches());
        val.setValidate("b");
        assertTrue(val.matches());
        val.setValidate("");
        assertFalse(val.matches());
        val.setValidate("ab");
        assertFalse(val.matches());

        val.setRegex("ba.dc.|");
        val.setValidate("ab");
        assertTrue(val.matches());
        val.setValidate("cd");
        assertTrue(val.matches());
        val.setValidate("abc");
        assertFalse(val.matches());
    }

    @Test
    public void kleeneStarTest() {
        Validate val = new Validate();

        val.setRegex("e*");
        val.setValidate("");
        assertTrue(val.matches());

        val.setValidate("e");
        assertTrue(val.matches());
        val.setValidate("ee");
        assertTrue(val.matches());
        val.setValidate("eee");
        assertTrue(val.matches());
        val.setValidate("eeee");
        assertTrue(val.matches());
        val.setValidate("eeeee");
        assertTrue(val.matches());
        val.setValidate("eeeeee");
        assertTrue(val.matches());
        val.setValidate("ef");
        assertFalse(val.matches());
        val.setValidate("eef");
        assertFalse(val.matches());
        val.setValidate("fe");
        assertFalse(val.matches());
        val.setValidate("free");
        assertFalse(val.matches());
        val.setValidate("eeece");
        assertFalse(val.matches());

        val.setRegex("ba.*");
        val.setValidate("ab");
        assertTrue(val.matches());
        val.setValidate("");
        assertTrue(val.matches());
        val.setValidate("abab");
        assertTrue(val.matches());
        val.setValidate("ababab");
        assertTrue(val.matches());

        val.setValidate("ba");
        assertFalse(val.matches());
        val.setValidate("aba");
        assertFalse(val.matches());
        val.setValidate("abb");
        assertFalse(val.matches());
        val.setValidate("cab");
        assertFalse(val.matches());
        val.setValidate("abc");
        assertFalse(val.matches());
    }

    @Test
    public void optionalTest() {
        Validate val = new Validate();

        val.setRegex("e?");
        val.setValidate("");
        assertTrue(val.matches());
        val.setValidate("e");
        assertTrue(val.matches());
        val.setValidate("ee");
        assertFalse(val.matches());
        val.setValidate("fe");
        assertFalse(val.matches());

        val.setRegex("ba.?");
        val.setValidate("ab");
        assertTrue(val.matches());
        val.setValidate("");
        assertTrue(val.matches());
        val.setValidate("ba");
        assertFalse(val.matches());
        val.setValidate("bac");
        assertFalse(val.matches());

        val.setRegex("ba.dc.|?");
        val.setValidate("ab");
        assertTrue(val.matches());
        val.setValidate("cd");
        assertTrue(val.matches());
        val.setValidate("");
        assertTrue(val.matches());

    }

    @Test
    public void Temp() {
        Validate val = new Validate();
        val.setRegex("aa.a|");
        val.setValidate("a");
        assertTrue(val.matches());
        val.setValidate("aa");
        assertTrue(val.matches());
        val.setValidate("aaa");
        assertFalse(val.matches());

        val.setRegex("aa.a|*");
        val.setValidate("aaaaaaaaaaaaaaaaa");
        assertTrue(val.matches());
        val.setValidate("aaa");
        assertTrue(val.matches());
    }

    @Test
    public void timingTest() {
        Validate val = new Validate();
        long start, finish;

        String[] testStrings = { "aaaaaaaaaaaaaaaaaaaaaaaaaaaaaac",
                "aaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaac",
                "aaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaac",
                "aaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaac",
                "aaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaac",
                "aaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaac",
        };

        System.out.println("Starting build: (a|aa)*b");
        start = System.nanoTime();
        val.setRegex("aa.a|*b.");
        finish = System.nanoTime();
        System.out.printf("Regex took %d.%dns to build\n\n", (finish - start) / 1000, (finish - start) % 1000);

        System.out.println("Starting string validation tests");
        for (String s : testStrings) {
            System.out.printf("Testing string:  %s\n", s);
            start = System.nanoTime();
            val.setValidate(s);
            assertFalse(val.matches());
            finish = System.nanoTime();
            System.out.printf("Validated string %s in %d.%dns\n\n", s, (finish - start) / 1000,
                    (finish - start) % 1000);
        }
    }
}
