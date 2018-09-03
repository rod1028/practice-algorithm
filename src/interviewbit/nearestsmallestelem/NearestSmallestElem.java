package interviewbit.nearestsmallestelem;

import java.util.ArrayDeque;
import java.util.ArrayList;

/**
 * https://www.interviewbit.com/problems/nearest-smaller-element/
 */
public class NearestSmallestElem {
    public ArrayList<Integer> prevSmaller(ArrayList<Integer> A) {
        ArrayDeque<Integer> stack = new ArrayDeque<>(A.size());
        ArrayList<Integer> result = new ArrayList<>(A.size());

        for (Integer v : A) {
            //find the first previous value that is smaller
            while (stack.isEmpty() == false && stack.peek() >= v) {
                stack.pop();
            }

            //does not exists
            if (stack.isEmpty()) {
                result.add(-1);
            } else {
                result.add(stack.peek());
            }
            //add me for next items
            stack.push(v);
        }

        return result;
    }
}
