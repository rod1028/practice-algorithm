package interviewbit.mergeksortedlists;

/**
 * https://www.interviewbit.com/problems/merge-k-sorted-lists/
 */

import java.util.ArrayList;

class ListNode {
    public int val;
    public ListNode next;

    ListNode(int x) {
        val = x;
        next = null;
    }
}

public class MergeKSortedLists {
    private static ListNode advance(ArrayList<ListNode> a) {
        int minIndex = -1;
        ListNode minValue = new ListNode(Integer.MAX_VALUE);

        for (int i = 0; i < a.size(); i++) {
            ListNode val = a.get(i);
            if (val == null) {
                //an optimization can be done by removing this i element from list
                continue;
            }
            if (minIndex == -1 || val.val < minValue.val) {
                minIndex = i;
                minValue = val;
            }
        }
        if (minIndex == -1) {
            return null;
        }
        //advance it
        a.set(minIndex, minValue.next);
        return minValue;
    }

    public ListNode mergeKLists(ArrayList<ListNode> a) {
        ListNode result = advance(a);
        if (result == null) {
            return null; //empty or null nodes
        }

        ListNode next = advance(a);
        result.next = next;
        while (next != null) {
            next.next = advance(a);
            next = next.next;
        }
        return result;
    }
}
