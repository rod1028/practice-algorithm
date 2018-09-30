package interviewbit.removeduplicatesii;

class ListNode {
    public int val;
    public ListNode next;

    ListNode(int x) {
        val = x;
        next = null;
    }
}

public class RemoveDuplicatesSortedListII {
    public class Solution {
        public ListNode deleteDuplicates(ListNode A) {
            if (A == null || A.next == null) {
                return A;
            }

            ListNode head = null;//maybe first one has duplicate too
            ListNode tail = null;
            ListNode prev = A;
            ListNode next = A.next;
            boolean duplicateFound = false;

            while (next != null) {
                if (prev.val == next.val) {
                    duplicateFound = true;
                } else {
                    //new different value
                    if (duplicateFound) {
                        duplicateFound = false;
                        prev = next; //advance prev
                        //prev was duplicate, ignore it
                    } else {
                        ListNode toAdd = prev;
                        prev = next; //advance prev
                        toAdd.next = null;

                        //save prev
                        if (head == null) {
                            head = toAdd;
                            tail = toAdd;
                        } else {
                            tail.next = toAdd;
                            tail = tail.next;
                        }
                    }
                }
                next = next.next;
            }

            //when prev is the last, we have to add it too
            if (duplicateFound == false) {
                if (head == null) {
                    head = prev;
                } else {
                    tail.next = prev;
                }
            }
            return head;
        }
    }
}
