package cracking.listcycle;

/**
 * https://www.interviewbit.com/problems/list-cycle/
 * https://leetcode.com/problems/linked-list-cycle/description/
 * https://leetcode.com/problems/linked-list-cycle-ii/description/
 */
public class ListCycle {
    public static void main(String[] args) {
        ListNode three = new ListNode(3);
        ListNode two = new ListNode(2);
        ListNode one = new ListNode(1);
        ListNode head = new ListNode(0);

        head.next = one;
        one.next = two;
        two.next = three;
        three.next = one;

        ListNode result = solution(head);
        if (result != one) {
            System.out.println("failed solution, got: " + result);
        } else {
            System.out.println("OK");
        }
    }

    private static ListNode solution(ListNode head) {
        if (head == null || head.next == null || head.next.next == null) {
            return null;
        }
        ListNode turtle = head;
        ListNode hare = head;

        //we go to the end or until they met
        do {
            if (hare == null || hare.next == null) {
                return null;//there is not a cycle
            }
            turtle = turtle.next;
            hare = hare.next.next;
        } while (turtle != hare);

        //now we use the hare as a new turtle
        //and they should meet again at the conjuction
        turtle = head;
        while (turtle != hare) {
            turtle = turtle.next;
            hare = hare.next;
        }
        return turtle;//or hare
    }
}

class ListNode {
    private int value;
    ListNode next;

    ListNode(int value) {
        this.value = value;
    }

    public String toString() {
        return "[" + this.value + "]";
    }
}
