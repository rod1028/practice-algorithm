package interleavingstrings;

/**
 * https://www.interviewbit.com/problems/interleaving-strings/
 * Given s1, s2, s3, find whether s3 is formed by the interleaving of s1 and s2.
 * <p>
 * Example,
 * Given:
 * <p>
 * s1 = "aabcc",
 * s2 = "dbbca",
 * When s3 = "aadbbcbcac", return true.
 * When s3 = "aadbbbaccc", return false.
 * <p>
 * Return 0 / 1 ( 0 for false, 1 for true ) for this problem
 */
public class InterleavingStrings {
    public int isInterleave(String A, String B, String C) {
        if (A == null || B == null || C == null ||
                C.length() != A.length() + B.length()) {
            return 0;
        }

        if (A.length() == 0) {
            return B.equals(C) ? 1 : 0;
        }
        if (B.length() == 0) {
            return A.equals(C) ? 1 : 0;
        }

        if (inner(A, B, C, 0, 0)) {
            return 1;
        }
        return 0;
    }

    private static boolean inner(String A, String B, String C, int indexA, int indexB) {

        //TODO add memoization (indexA-indexB=>result), but I cant find the cases that repeat
        int indexC = indexA + indexB;
        if (indexC >= C.length() - 1) {
            return true;
        }

        Character charC = C.charAt(indexC);

        if (indexA < A.length()) {
            Character charA = A.charAt(indexA);
            if (charA == charC && inner(A, B, C, indexA + 1, indexB)) {
                return true;
            }
        }

        if (indexB < B.length()) {
            Character charB = B.charAt(indexB);
            if (charB == charC && inner(A, B, C, indexA, indexB + 1)) {
                return true;
            }
        }

        //c character does not match any of current A and B
        return false;
    }
}
