package interviewbit.palindromelist;

class ListNode {
    public int val;
    public ListNode next;

    ListNode(int x) {
        val = x;
        next = null;
    }
}

/**
 * https://www.interviewbit.com/problems/palindrome-list/
 * https://www.geeksforgeeks.org/function-to-check-if-a-singly-linked-list-is-palindrome/
 */
public class PalindromeList {
    ListNode head;

    public int lPalin(ListNode A) {
        if (A == null || A.next == null) {
            return 1;
        }

        head = A;
        return check(A.next) ? 1 : 0;
    }

    private boolean check(ListNode tail) {
        //base case, we reached the end
        if (tail == null) {
            return true;
        }

        ListNode myTail = tail;
        if (check(tail.next) == false) {
            return false;
        }

        ListNode myHead = head;
        if (myHead.val != myTail.val) {
            return false;
        }

        head = head.next;
        return true;
    }
}
