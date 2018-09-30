package interviewbit.removeduplicatelist;

class ListNode {
    public int val;
    public ListNode next;

    ListNode(int x) {
        val = x;
        next = null;
    }
}

/**
 * https://www.interviewbit.com/courses/programming/topics/linked-lists/
 */
public class RemoveDuplicateSortedList {
    public ListNode deleteDuplicates(ListNode A) {
        int lastVal = A.val;
        ListNode prev = A;
        ListNode next = A.next;

        while (next != null) {
            if (lastVal == next.val) {
                prev.next = next.next;
            } else {
                lastVal = next.val;
                prev = next;
            }
            next = next.next;
        }
        return A;
    }
}
