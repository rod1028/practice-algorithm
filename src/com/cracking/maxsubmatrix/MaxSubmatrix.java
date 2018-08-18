package com.cracking.maxsubmatrix;

import java.security.InvalidParameterException;

public class MaxSubmatrix {
    public static void main(String[] args) {

        int[][] input = {{1, 2, 3}, {-2, -3, -4}, {4, 5, -1}};
        int should = 9;
        try {
            int got = calculate(input);
            System.out.printf("want %d got %d", should, got);
        } catch (Exception e) {
            e.printStackTrace();
            System.err.println(e.getMessage());
        }
    }

    private static int calculate(int[][] in) {
        int best = Integer.MIN_VALUE;
        if (in == null) {
            throw new NullPointerException();
        }
        int rows = in.length;
        if (rows == 0) {
            return best;
        }
        int cols = in[0].length;

        for (int rowStart = 0; rowStart < rows; rowStart++) {
            int[] sums = new int[cols];
            if (in[rowStart] == null) {
                throw new NullPointerException();
            }

            for (int rowEnd = rowStart; rowEnd < rows; rowEnd++) {
                //add the next row to the sums
                addRow(sums, in[rowEnd]);

                //get the max of current data
                int max = getMaxSubarray(sums);

                //check for max best
                if (max > best) {
                    best = max;
                }
            }
        }
        return best;
    }

    private static void addRow(int[] src, int[] add) {
        if (src.length != add.length) {
            throw new InvalidParameterException("addRow not equal size arrays");
        }

        for (int i = 0; i < src.length; i++) {
            src[i] += add[i];
        }
    }

    private static int getMaxSubarray(int[] src) {
        if (src.length == 0) {
            return Integer.MIN_VALUE;
        }

        int best = src[0];
        int max = best;

        for (int i = 1; i < src.length; i++) {
            int v = src[i];
            max = Math.max(max, max + v);
            best = Math.max(best, max);
        }
        return best;
    }
}
