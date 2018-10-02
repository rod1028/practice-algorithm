package interviewbit.submatriceswithsumzero;

import java.util.ArrayList;
import java.util.HashMap;

/**
 * https://www.interviewbit.com/problems/sub-matrices-with-sum-zero/
 * <p>
 * suproblem: https://www.geeksforgeeks.org/find-if-there-is-a-subarray-with-0-sum/
 */
public class SubMatricesWithSumZero {
    public int solve(ArrayList<ArrayList<Integer>> mat) {
        if (mat == null) {
            return 0;
        }

        int rows = mat.size();
        if (rows == 0) {
            return 0;
        }
        int cols = mat.get(0).size();
        if (cols == 0) {
            return 0;
        }

        int result = 0;

        for (int rowTop = 0; rowTop < rows; rowTop++) {
            int[] sums = new int[cols];

            for (int rowBot = rowTop; rowBot < rows; rowBot++) {
                // System.out.println("line " + rowBot);
                addRow(sums, mat.get(rowBot));
                result += countZeroSums(sums);
            }
        }

        return result;
    }

    private int countZeroSums(int[] line) {
        int result = 0;
        //the same sum can be reached multiple times, creating more matrixes
        HashMap<Integer, Integer> sumOcc = new HashMap<>();
        sumOcc.put(0, 1);

        int wantedSum = 0;
        int sumSoFar = 0;
        for (int col = 0; col < line.length; col++) {
            sumSoFar += line[col];
            int v = sumOcc.getOrDefault(sumSoFar, 0);
            result += v;
            v++;//this occurence
            sumOcc.put(sumSoFar, v);
        }

        return result;
    }

    private void addRow(int[] sum, ArrayList<Integer> row) {
        for (int col = 0; col < row.size(); col++) {
            sum[col] += row.get(col);
        }
    }
}
