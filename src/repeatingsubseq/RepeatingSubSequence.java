package repeatingsubseq;

/**
 * https://www.interviewbit.com/problems/repeating-subsequence/
 * https://en.wikipedia.org/wiki/Longest_common_subsequence_problem
 * Return 1 if any subsequence exists:
 * - 2+ length
 * - A has another subseq B
 * - both have the same characters in the same order
 * - but A[0] position in input must be different of the B[0] in input
 */
public class RepeatingSubSequence {
    //O(n^2) DP approach with space n^2
    public int anytwo(String a) {
        int min = 2;
        int len = a.length();

        int dp[][] = new int[len + 1][len + 1];

        for (int row = 1; row <= len; row++) {
            for (int col = 1; col <= len; col++) {
                boolean equal = row != col && a.charAt(row - 1) == a.charAt(col - 1);
                if (equal) {
                    dp[row][col] = 1 + dp[row - 1][col - 1];
                } else {
                    dp[row][col] = Math.max(dp[row - 1][col], dp[row][col - 1]);
                }

                //any two so ..we can stop at first
                if (dp[row][col] >= min) {
                    return 1;
                }
            }
        }

        return 0;
    }

    //O(n^3) but it works
    public int inneficient(String a) {
        int min = 2;

        for (int start = 0; start < a.length() - 1; start++) {
            int seqCount = 0;
            int maxId = 0;
            for (int i = start; i < a.length() - 1; i++) {
                for (int j = i + 1; j < a.length(); j++) {
                    if (i == j) {
                        continue; //must have different index
                    }
                    if (j <= maxId) {
                        continue;
                    }
                    if (a.charAt(i) != a.charAt(j)) {
                        continue;
                    }

                    maxId = j;
                    seqCount++;
                    if (seqCount >= min) {
                        return 1;
                    }
                }
            }
        }

        return 0;
    }
}
