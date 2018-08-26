package slidingwindowmax;

import java.util.*;

/**
 * https://www.interviewbit.com/problems/sliding-window-maximum/
 * A long array A[] is given to you. There is a sliding window of size w which is moving from the very left of the array to the very right. You can only see the w numbers in the window. Each time the sliding window moves rightwards by one position. You have to find the maximum for each window. The following example will give you more clarity.
 * <p>
 * Example :
 * <p>
 * The array is [1 3 -1 -3 5 3 6 7], and w is 3.
 * <p>
 * Window position	Max
 * <p>
 * [1 3 -1] -3 5 3 6 7	3
 * 1 [3 -1 -3] 5 3 6 7	3
 * 1 3 [-1 -3 5] 3 6 7	5
 * 1 3 -1 [-3 5 3] 6 7	5
 * 1 3 -1 -3 [5 3 6] 7	6
 * 1 3 -1 -3 5 [3 6 7]	7
 * Input: A long array A[], and a window width w
 * Output: An array B[], B[i] is the maximum value of from A[i] to A[i+w-1]
 * Requirement: Find a good optimal way to get B[i]
 * <p>
 * Note: If w > length of the array, return 1 element with the max of the array.
 */
public class SlidingWindowMax {
    public ArrayList<Integer> slidingMaximum(final List<Integer> list, int w) {
        ArrayList<Integer> result = new ArrayList<>(list.size() - w);

        if (w >= list.size()) {
            result.add(Collections.max(list));
            return result;
        }

        //first fill
        PriorityQueue<Integer> window = new PriorityQueue<>(Comparator.reverseOrder());
        int endIndex = 0;
        while (endIndex < w) {
            window.add(list.get(endIndex));
            endIndex++;
        }
        result.add(window.peek());

        //move the window one step
        do {
            window.remove(list.get(endIndex - w));
            window.add(list.get(endIndex));
            result.add(window.peek());
            endIndex++;
        } while (endIndex < list.size());

        //move the window and save each result
        return result;
    }

}
