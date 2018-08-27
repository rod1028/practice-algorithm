package cracking.minimizeabsdiff3arr;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

/**
 * https://www.interviewbit.com/problems/minimize-the-absolute-difference/
 */
public class MinimizeAbsDiffArrays {
    public static void main(String[] args) {

    }

    /**
     * @return true if one pointer was advanced. False all reached the end
     */
    private static boolean advanceSmallestPointer(ArrayList<ArrayList<Integer>> lists, HashMap<Integer, Integer> pointers) {
        int smallestList = -1;
        int smallestValue = Integer.MAX_VALUE;

        for (int i = 0; i < lists.size(); i++) {
            ArrayList<Integer> list = lists.get(i);
            int index = pointers.get(i);
            if (index >= list.size() - 1) {
                continue; //this list is already at the end, each list can have its own size
            }
            int value = list.get(index);
            if (value < smallestValue) {
                smallestList = i;
                smallestValue = value;
            }
        }
        if (smallestList == -1) {
            return false;
        }

        //increment it
        int oldIndex = pointers.get(smallestList);
        pointers.put(smallestList, oldIndex + 1);

        return true;
    }

    private static int getAbsDiff(ArrayList<ArrayList<Integer>> lists, HashMap<Integer, Integer> pointers) {
        int max = Integer.MIN_VALUE;
        int min = Integer.MAX_VALUE;
        for (Map.Entry<Integer, Integer> pointer : pointers.entrySet()) {
            ArrayList<Integer> list = lists.get(pointer.getKey());
            int index = pointer.getValue();
            int value = list.get(index);
            max = Math.max(max, value);
            min = Math.min(min, value);
        }
        return Math.abs(max - min);
    }

    public int solve(ArrayList<Integer> A, ArrayList<Integer> B, ArrayList<Integer> C) {

        //the lists and pointers, solves the problem with any number of lists
        ArrayList<ArrayList<Integer>> lists = new ArrayList<>(3);

        if (A != null && A.size() > 0) lists.add(A);
        if (B != null && B.size() > 0) lists.add(B);
        if (C != null && C.size() > 0) lists.add(C);

        HashMap<Integer, Integer> pointers = new HashMap<>(3);
        for (int i = 0; i < lists.size(); i++) {
            pointers.put(i, 0);
        }

        int result = getAbsDiff(lists, pointers);
        while (advanceSmallestPointer(lists, pointers)) {
            result = Math.min(result, getAbsDiff(lists, pointers));
        }

        return result;
    }
}
