package interviewbit.largestrectanglehistogram;

import java.util.ArrayDeque;
import java.util.ArrayList;

/**
 * https://www.geeksforgeeks.org/largest-rectangle-under-histogram/
 * https://www.interviewbit.com/problems/largest-rectangle-in-histogram/
 * https://leetcode.com/problems/largest-rectangle-in-histogram/description/
 */
public class LargestRectangleHistogram {
    public int largestRectangleArea(ArrayList<Integer> A) {

        int size = A.size();
        if (size == 1) {
            return A.get(0);
        }
        int max = 0;
        if (size == 0) {
            return max;
        }

        ArrayDeque<Integer> stack = new ArrayDeque<>();
        for (int i = 0; i < size; i++) {
            Integer val = A.get(i);

            while (stack.isEmpty() == false && val < A.get(stack.peek())) {
                Integer rightIndex = stack.pop();
                Integer height = A.get(rightIndex);

                Integer width = stack.isEmpty() ? i : i - stack.peek() - 1;

                int area = width * height;
                max = Math.max(max, area);
            }
            stack.push(i);
        }

        while (stack.isEmpty() == false) {
            int i = stack.pop();
            int height = A.get(i);

            int area = height * (stack.isEmpty() ? size : size - 1 - stack.peek());
            max = Math.max(max, area);
        }
        return max;
    }
}
