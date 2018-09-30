package interviewbit.removenthnodefromendlist;

class ListNode {
    public int val;
    public ListNode next;

    ListNode(int x) {
        val = x;
        next = null;
    }
}

/**
 * https://www.interviewbit.com/problems/remove-nth-node-from-list-end/
 * <p>
 * For a non-recursive solution (it will fail for long lists):
 * * tranverse once and get the length
 * * tranverse 2nd time if needed and remove it
 * O(2n) and space O(1)
 */
public class RemoveNthFromEndList {

    public ListNode removeNthFromEnd(ListNode head, int n) {
        int count = remove(head, n);

        if (count <= n) {
            return head.next;
        }

        return head;
    }

    private int remove(ListNode next, int n) {
        //base class
        if (next.next == null) {
            return 1;
        }

        int count = remove(next.next, n);

        if (count == n) {
            next.next = next.next.next;
        }

        return count + 1;
    }
}
