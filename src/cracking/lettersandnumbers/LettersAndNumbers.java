package cracking.lettersandnumbers;

import java.util.Arrays;
import java.util.HashMap;

public class LettersAndNumbers {
    private static boolean isDigit(char c) {
        //only for ASCII digits
        return c >= '0' && c <= '9';
    }

    public static char[] solution(char[] list) {
        if (list.length <= 1) {
            return null;
        }

        int[][] table = new int[3][list.length];
        int LET = 0;//no const in Java :(
        int DIG = 1;
        int DIFF = 2;

        HashMap<Integer, Integer> firstDiffIndex = new HashMap<>(list.length);
        int longestStart = -1;
        int longestSize = 0;

        for (int i = 0; i < list.length; i++) {
            if (i > 0) {
                table[DIG][i] = table[DIG][i - 1];
                table[LET][i] = table[LET][i - 1];
            }

            if (isDigit(list[i])) {
                table[DIG][i]++;
            } else {
                table[LET][i]++;
            }
            int diff = table[DIG][i] - table[LET][i];
            table[DIFF][i] = diff;

            if (firstDiffIndex.containsKey(diff) || diff == 0) {
                int startIndex = diff == 0 ? 0 : firstDiffIndex.get(diff) + 1;
                int len = i - startIndex + 1;
                if (len > longestSize) {
                    longestSize = len;
                    longestStart = startIndex;
                }
            } else {
                firstDiffIndex.put(diff, i);
            }
        }
        if (longestSize == 0) {
            return null;
        }
        return Arrays.copyOfRange(list, longestStart, longestStart + longestSize);
    }
}
