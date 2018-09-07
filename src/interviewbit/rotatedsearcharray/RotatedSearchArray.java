package interviewbit.rotatedsearcharray;

import java.util.List;

/**
 * https://www.interviewbit.com/problems/rotated-sorted-array-search/
 * https://leetcode.com/problems/search-in-rotated-sorted-array/
 */
public class RotatedSearchArray {
    private int getSmallestValueIndex(List<Integer> a) {
        int left = 0;
        int right = a.size() - 1;

        if (a.get(left) < a.get(right)) {
            return 0; //is not rotated
        }

        while (left < right) {//indexs
            int mid = (left + right) / 2;
            int midVal = a.get(mid);
            int leftVal = a.get(left);
            int rightVal = a.get(right);

            if (midVal > leftVal) {
                //it means the midVal is in the rotated part
                left = mid;
            } else {
                right = mid;
            }
        }

        return right + 1;
    }

    private int binarySearch(final List<Integer> a, int left, int right, int target) {
        while (left <= right) {
            int mid = (left + right) / 2;
            if (a.get(mid) == target) {
                return mid;
            }
            if (a.get(mid) > target) {
                right = mid - 1;
            } else {
                left = mid + 1;
            }
        }
        return -1;
    }

    // DO NOT MODIFY THE LIST
    public int search(final List<Integer> a, int target) {
        if (a.size() == 0) {
            return -1;
        }
        int minIndex = getSmallestValueIndex(a);

        //is not rotated
        if (minIndex == 0) {
            return binarySearch(a, 0, a.size() - 1, target);
        }

        int maxIndex = minIndex - 1;

        // System.out.println("smallest is" + pivot + ":" + a.get(pivot));
        int leftVal = a.get(0);
        int rightVal = a.get(a.size() - 1);

        if (target >= leftVal && target <= a.get(maxIndex)) {
            return binarySearch(a, 0, minIndex, target);
        }

        if (target >= a.get(minIndex) && target <= rightVal) {
            return binarySearch(a, minIndex, a.size(), target);
        }

        //cannot be in either "halfs"
        return -1;
    }

}
