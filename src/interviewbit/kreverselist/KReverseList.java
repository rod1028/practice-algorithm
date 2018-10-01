package interviewbit.kreverselist;

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
 * https://www.interviewbit.com/problems/k-reverse-linked-list/
 * https://leetcode.com/problems/reverse-nodes-in-k-group/description/
 */
public class KReverseList {
    public ListNode reverseList(ListNode head, int k) {
        if (head == null || head.next == null || k <= 1) {
            return head;
        }

        ListNode resultHead = null;
        ListNode resultTail = null;
        ListNode prevGroupHead = head;
        ListNode nextGroupHead;

        do {
            ListNode prevGroupTail = advanceK(prevGroupHead, k);
            nextGroupHead = prevGroupTail.next;
            prevGroupTail.next = null;//cut the list

            prevGroupHead = rotate(prevGroupHead);
            //by rotating it, we lost the tail
            if (resultHead == null) {
                resultHead = prevGroupHead;
                resultTail = findTail(prevGroupHead);
            } else {
                resultTail.next = prevGroupHead;
                resultTail = findTail(resultTail);
            }
            prevGroupHead = nextGroupHead;

        } while (nextGroupHead != null);

        return resultHead;
    }

    private ListNode findTail(ListNode head) {
        while (head.next != null) {
            head = head.next;
        }
        return head;
    }

    private ListNode rotate(ListNode head) {
        ArrayDeque<ListNode> stack = new ArrayDeque<>();
        while (head != null) {
            stack.push(head);
            head = head.next;
        }
        ListNode result = stack.pop();
        ListNode cursor = result;
        while (stack.isEmpty() == false) {
            cursor.next = stack.pop();
            cursor = cursor.next;
        }
        cursor.next = null;//the link is to the 2nd item, so will create a cycle
        return result;
    }

    private ListNode advanceK(ListNode cursor, int k) {
        while (k > 1) {
            cursor = cursor.next;
            k--;
        }
        return cursor;
    }
}
