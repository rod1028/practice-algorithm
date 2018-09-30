package interviewbit.sortlist;

class ListNode {
    public int val;
    public ListNode next;

    ListNode(int x) {
        val = x;
        next = null;
    }
}

/**
 * Sort a singly linked list in O(nlogn) time and O(1) space
 * https://www.interviewbit.com/problems/sort-list/
 */
public class SortList {

    public ListNode sortList(ListNode A) {
        if (A == null || A.next == null) {
            return A;
        }

        ListNode[] halfs = split(A);
        ListNode left = sortList(halfs[0]);
        ListNode right = sortList(halfs[1]);

        return merge(left, right);
    }

    private static ListNode merge(ListNode left, ListNode right) {
        ListNode head = null;
        ListNode min = null;
        ListNode next = null;

        while (left != null && right != null) {
            if (left.val < right.val) {
                min = left;
                left = left.next;
            } else {
                min = right;
                right = right.next;
            }
            if (head == null) {
                head = min;
                next = min;
            } else {
                next.next = min;
                next = next.next;
            }
        }
        //left overs in the other half
        if (left == null) {
            next.next = right;
        } else {
            next.next = left;
        }
        return head;
    }

    private static ListNode[] split(ListNode A) {
        ListNode turtle = A;
        ListNode rabbit = A;

        while (rabbit.next != null) {
            rabbit = rabbit.next;
            if (rabbit.next != null) {
                rabbit = rabbit.next;
                turtle = turtle.next;
            }
        }
        ListNode right = turtle.next;
        turtle.next = null;
        return new ListNode[]{A, right};
    }

}
