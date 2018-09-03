package interviewbit.containerMostWater;

import java.util.ArrayList;

/**
 * https://www.interviewbit.com/problems/container-with-most-water/
 */
public class ContainerMostWater {
    private static int getArea(ArrayList<Integer> list, int a, int b) {
        return (b - a) * Math.min(list.get(a), list.get(b));
    }

    public int maxArea(ArrayList<Integer> A) {
        if (A == null || A.size() <= 1) {
            return 0;
        }

        if (A.size() == 2) {
            return Math.min(A.get(0), A.get(1));
        }

        int left = 0;
        int right = A.size() - 1;
        int best = Integer.MIN_VALUE;

        while (left < right) {
            best = Math.max(best, getArea(A, left, right));

            //move the pointer that is the smallest, because is multiplication
            //and we want a bigger value
            if (A.get(left) > A.get(right)) {
                right--;
            } else {
                left++;
            }
        }
        return best;
    }
}
