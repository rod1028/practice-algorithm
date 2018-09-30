package interviewbit.rotatelist;


class ListNode {
    public int val;
    public ListNode next;

    ListNode(int x) {
        val = x;
        next = null;
    }
}

/**
 * https://www.interviewbit.com/problems/rotate-list/
 * https://leetcode.com/problems/rotate-list/description/
 * Custom solution O(2n) space O(1)
 */
public class RotateList {
    public ListNode rotateRight(ListNode head, int k) {
        if (k < 1 || head == null || head.next == null) {
            return head;
        }

        //with one pass we can get the tail and size
        //because Java doesn't have multiple return values ...I prefer
        //to pollute this function :(
        ListNode tail = head;
        int size = 1;
        while (tail.next != null) {
            size++;
            tail = tail.next;
        }

        if (k > size) {
            k = k % size;//no use to rotate it more than 1 full cycle
        }

        if (k == size || k == 0) {
            return head;
        }

        ListNode cursor = head;
        int count = size - k - 1;
        while (count > 0) {
            count--;
            cursor = cursor.next;
        }
        ListNode newHead = cursor.next;
        cursor.next = null;//cut it
        tail.next = head;

        return newHead;
    }
}
