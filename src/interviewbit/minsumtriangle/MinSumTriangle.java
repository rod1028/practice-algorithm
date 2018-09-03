package interviewbit.minsumtriangle;

import java.util.ArrayList;
import java.util.HashMap;

/**
 * https://www.interviewbit.com/problems/min-sum-path-in-triangle/
 * Given a triangle, find the minimum path sum from top to bottom. Each step you may move to adjacent numbers on the row below.
 * <p>
 * For example, given the following triangle
 * <p>
 * [
 * [2],
 * [3,4],
 * [6,5,7],
 * [4,1,8,3]
 * ]
 * The minimum path sum from top to bottom is 11 (i.e., 2 + 3 + 5 + 1 = 11).
 */

//inplace aproach, iterative
class Solution {
    public int minimumTotal(ArrayList<ArrayList<Integer>> a) {
        int rows = a.size();
        //skip the last row, is already at its minimum
        for (int row = rows - 2; row >= 0; row--) {
            for (int index = 0; index < row + 1; index++) {
                int min = Math.min(a.get(row + 1).get(index),
                        a.get(row + 1).get(index + 1));
                int myVal = a.get(row).get(index);
                a.get(row).set(index, min + myVal);
            }
        }

        //if the rows count is 1 it just returns the first value
        return a.get(0).get(0);
    }
}

//standard DP, recursive, memoization, bottom-up approach
public class MinSumTriangle {
    public int minimumTotal(ArrayList<ArrayList<Integer>> a) {
        // for(ArrayList<Integer> row : a)
        // System.out.println(Arrays.asList(row));

        return rec(a, 0, 0, 0, new HashMap<Integer, HashMap<Integer, Integer>>(a.size()));
    }

    private static void addCache(HashMap<Integer, HashMap<Integer, Integer>> cache,
                                 int row, int index, int sum) {
        if (cache.containsKey(row) == false) {
            cache.put(row, new HashMap<Integer, Integer>(row + 1));
        }
        cache.get(row).put(index, sum);
    }

    private static int rec(ArrayList<ArrayList<Integer>> a, int row,
                           int myIndex, int sum, HashMap<Integer, HashMap<Integer, Integer>> cache) {
        // cache is [row][index]=maxSum
        if (cache.containsKey(row) && cache.get(row).containsKey(myIndex)) {
            return cache.get(row).get(myIndex);
        }

        //lower row neighbours indexs
        int bottomLeft = myIndex;
        int bottomRight = myIndex + 1;
        int nextRow = row + 1;
        int myVal = a.get(row).get(myIndex);
        int result = myVal;

        if (row < a.size() - 1) {
            //if is not base class, my best is best from children
            result += Math.min(rec(a, nextRow, bottomLeft, result, cache),
                    rec(a, nextRow, bottomRight, result, cache));
        }

        addCache(cache, row, myIndex, result);
        //19

        return result;
    }
}
