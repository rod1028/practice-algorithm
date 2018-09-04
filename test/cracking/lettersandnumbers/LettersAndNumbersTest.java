package cracking.lettersandnumbers;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

class LettersAndNumbersTest {

    @Test
    void basic() {
        char[] in = "aaaa1aa22a33aaa".toCharArray();
        char[] exp = "aa1aa22a33".toCharArray();
        char[] res = LettersAndNumbers.solution(in);

        Assertions.assertArrayEquals(exp, res, new String(exp) + " vs " + new String(res));
    }

    @Test
    void start() {
        char[] in = "abc123xxx".toCharArray();
        char[] exp = "abc123".toCharArray();
        char[] res = LettersAndNumbers.solution(in);

        Assertions.assertArrayEquals(exp, res, new String(exp) + " vs " + new String(res));
    }

    @Test
    void empty() {
        char[] in = "aaaaaaa".toCharArray();
        char[] res = LettersAndNumbers.solution(in);

        Assertions.assertNull(res);
    }
}