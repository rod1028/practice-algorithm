package distinctsubseq;

/**
 * https://www.interviewbit.com/problems/distinct-subsequences/
 * Trickier problem, similar with LCSubstring and LCSubsequence
 * <p>
 * O(mn) time and same space complexity, appling DP
 */
public class DistinctSubsequence {
    public int numDistinct(String S, String T) {
        if (T == null || S.equals(T) || T.length() == 0) {
            return 1;
        }
        if (S == null || S.length() == 0) {
            return 0;
        }

        int lenS = S.length();
        int lenT = T.length();

        int[][] dp = new int[lenS + 1][lenT + 1];
        //to achieve empty string from S is only 1 solution
        //to elimitte all the characters, so .. first col is 1
        for (int row = 0; row <= lenS; row++) {
            dp[row][0] = 1;
        }

        //we cannot remove from an empty string (row 0)
        //to match any character
        //no need to do it, is the zero value in java already
        // for (int col = 1 ; col <= lenT ; col++){
        //     dp[0][col] = 0;
        // }

        //now we can start matching, from first character of S
        //adding new one each row
        for (int row = 1; row <= lenS; row++) {
            Character charS = S.charAt(row - 1);

            for (int col = 1; col <= lenT; col++) {
                Character charT = T.charAt(col - 1);

                if (charS == charT) {
                    //with this character solutions + without it (we have a choice)
                    //and the answer wants all solutions
                    dp[row][col] = dp[row - 1][col - 1] + dp[row - 1][col];
                } else {
                    //we take the best without this character, so up value
                    dp[row][col] = dp[row - 1][col];
                }
            }
        }
        return dp[lenS][lenT];
    }
}
