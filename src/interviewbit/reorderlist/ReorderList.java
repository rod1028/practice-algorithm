package interviewbit.reorderlist;

import java.util.ArrayDeque;

class ListNode {
    public int val;
    public ListNode next;

    ListNode(int x) {
        val = x;
        next = null;
    }
}

/**
 * https://www.interviewbit.com/problems/reorder-list/
 * https://leetcode.com/problems/reorder-list/
 * 0->n->1->n-1->....->half->n-half+1
 */
public class ReorderList {
    public ListNode reorderList(ListNode A) {
        if (A == null || A.next == null) {
            return A;
        }

        ListNode mid = getMid(A);
        ListNode next = mid.next;

        ArrayDeque<ListNode> stack = new ArrayDeque<>();
        while (next != null) {
            stack.push(next);
            next = next.next;
        }

        ListNode cursor = A;
        //cut the list so we know when to stop
        mid.next = null;
        while (cursor != null && stack.isEmpty() == false) {
            ListNode n = cursor.next;
            cursor.next = stack.pop();
            cursor.next.next = n;
            cursor = n;
        }

        return A;
    }

    private ListNode getMid(ListNode A) {
        ListNode turtle = A;
        ListNode rabbit = A;

        while (rabbit != null) {
            rabbit = rabbit.next;
            if (rabbit != null) {
                turtle = turtle.next;
                rabbit = rabbit.next;
            }
        }
        return turtle;
    }
}
