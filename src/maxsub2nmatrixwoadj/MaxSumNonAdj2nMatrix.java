package maxsub2nmatrixwoadj;

import java.util.ArrayList;
import java.util.HashMap;

/**
 * https://www.interviewbit.com/problems/max-sum-without-adjacent-elements/
 * Given a 2 * N Grid of numbers, choose numbers such that the sum of the numbers
 * is maximum and no two chosen numbers are adjacent horizontally, vertically or diagonally, and return it.
 * <p>
 * Example:
 * <p>
 * Grid:
 * 1 2 3 4
 * 2 3 4 5
 * so we will choose
 * 3 and 5 so sum will be 3 + 5 = 8
 * <p>
 * <p>
 * Note that you can choose more than 2 numbers
 */
public class MaxSumNonAdj2nMatrix {
    public int adjacent(ArrayList<ArrayList<Integer>> a) {
        if (a == null) {
            return 0;
        }
        int len = a.get(0).size();

        //because of the size (2) and rules (diag) we can reduce to a 1*n array
        ArrayList<Integer> max = new ArrayList<>(len);
        for (int i = 0; i < len; i++) {
            max.add(Math.max(a.get(0).get(i), a.get(1).get(i)));
        }

        //now is a problem of finding the max sum of array for non-adj
        //O(n) if we use DP and remember the previous best answers
        //shrinks down to DP[i] = max(DP[i-1], DP[i-2]+a[i])
        HashMap<Integer, Integer> cache = new HashMap<>(len);
        int result = 0;

        for (int i = 0; i < max.size(); i++) {
            int best = Math.max(cache.getOrDefault(i - 1, 0), //without me
                    cache.getOrDefault(i - 2, 0) + max.get(i)); //with me
            cache.put(i, best);
            if (best > result) {
                result = best;
            }
        }
        return result;
    }
}
