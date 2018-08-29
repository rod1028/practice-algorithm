package cracking.longestincsub;

import java.util.ArrayList;
import java.util.List;

/**
 * https://www.interviewbit.com/problems/longest-increasing-subsequence/
 * https://www.geeksforgeeks.org/longest-increasing-subsequence-dp-3/
 */
public class LongestIncreasingSubseq {
    public int lis(final List<Integer> A) {
        if (A.size() <= 1) {
            return A.size();//does not work for A null
        }

        int result = 1;
        //the count of items of the best sequence that finishes at A[i] inclusive
        //is best[i]
        ArrayList<Integer> best = new ArrayList<>(A.size());
        best.add(1);//first item is the longest too

        for (int i = 1; i < A.size(); i++) {
            int longestPrev = 1;
            int val = A.get(i);

            for (int p = 0; p < i; p++) {
                int prevVal = A.get(p);
                if (prevVal < val) {
                    int bestAtP = best.get(p);
                    longestPrev = Math.max(longestPrev, bestAtP + 1);
                }
            }
            best.add(longestPrev);//same as set(i, longestPrev)
            result = Math.max(result, longestPrev);
        }

        return result;
    }
}
