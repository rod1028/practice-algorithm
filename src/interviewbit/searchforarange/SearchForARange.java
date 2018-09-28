package interviewbit.searchforarange;

import java.util.ArrayList;
import java.util.List;

/**
 * https://leetcode.com/problems/find-first-and-last-position-of-element-in-sorted-array/
 * https://www.interviewbit.com/problems/search-for-a-range/
 * <p>
 * Given a sorted array of integers, find the starting and ending position of a given target value.
 * Your algorithm’s runtime complexity must be in the order of O(log n).
 * If the target is not found in the array, return [-1, -1].
 * <p>
 * Example:
 * Given [5, 7, 7, 8, 8, 10]
 * and target value 8,
 * return [3, 4].
 */
public class SearchForARange {
    private static int findDuplicate(final List<Integer> a, int left,
                                     int right, int target, boolean searchLeft) {
        int result = -1;

        while (left <= right) {
            int mid = (left + right) / 2;
            int val = a.get(mid);
            if (val == target) {
                result = mid;
            }

            if ((val == target && searchLeft) || val > target) {
                right = mid - 1;
            } else {
                left = mid + 1;
            }
        }
        return result;
    }

    // DO NOT MODIFY THE LIST
    public ArrayList<Integer> searchRange(final List<Integer> a, int target) {
        int left = 0;
        int right = a.size() - 1;
        ArrayList<Integer> result = new ArrayList<Integer>() {{
            add(-1);
            add(-1);
        }};

        while (left <= right) {
            int mid = (left + right) / 2;
            int val = a.get(mid);

            if (val == target) {
                result.set(0, mid);
                result.set(1, mid);

                int leftMostDuplicate = findDuplicate(a, left, mid - 1, target, true);
                if (leftMostDuplicate > -1) {
                    result.set(0, leftMostDuplicate);
                }

                int rightMostDuplicate = findDuplicate(a, mid + 1, right, target, false);
                if (rightMostDuplicate > -1) {
                    result.set(1, rightMostDuplicate);
                }
                //search in both left and right for possible duplicates
                //a linear search surrounding the value can lead to O(n)
                //in worst case
                break;
            }

            if (val < target) {
                left = mid + 1;
            } else {
                right = mid - 1;
            }
        }

        return result;
    }

}
