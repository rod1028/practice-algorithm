package windowstring;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class WindowStringTest {

    @Test
    void testMinWindow() {
        String S = "ADOBECODEBANC";
        String T = "ABC";
        String should = "BANC";

        String is = WindowString.minWindow(S, T);
        assertEquals(should, is);
    }
}