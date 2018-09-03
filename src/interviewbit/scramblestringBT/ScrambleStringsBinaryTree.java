package interviewbit.scramblestringBT;

/**
 * https://www.interviewbit.com/problems/scramble-string/
 */
//not optimal solution, basic DP, recursive, no memoization yet
public class ScrambleStringsBinaryTree {
    private static boolean sameChars(String a, String b) {
        if (a.length() != b.length()) return false;
        if (a.equals(b)) return true;

        //works only for lower case ASCII letters
        int[] chars = new int[122 - 60];
        for (int i = 0; i < a.length(); i++) {
            chars[a.charAt(i) - 'A']++;
            chars[b.charAt(i) - 'A']--;
        }
        for (int count : chars) {
            if (count != 0) {
                return false;
            }
        }
        return true;
    }

    // DO NOT MODIFY THE LIST. IT IS READ ONLY
    public int isScramble(final String A, final String B) {
        //TODO add memoization for A+B=1 or 0

        if (sameChars(A, B) == false) {
            return 0;
        }
        //base case, same letters
        if (A.equals(B)) {
            return 1;
        }
        int len = A.length();

        //we try to find the root of the tree, for the current strings
        //they have to be the same length and contain same characters
        for (int i = 1; i < len; i++) {
            String leftA = A.substring(0, i);
            String leftB = B.substring(0, i);

            String rightA = A.substring(i, len);
            String rightB = B.substring(i, len);
            if (isScramble(leftA, leftB) == 1 && isScramble(rightA, rightB) == 1) {
                return 1;
            }

            //the root itself may be swapped, then B is reversed
            //then leftA is the rightB
            leftB = B.substring(0, len - i);
            rightB = B.substring(len - i, len);
            if (isScramble(leftA, rightB) == 1 && isScramble(rightA, leftB) == 1) {
                return 1;
            }
        }
        return 0;
    }
}
