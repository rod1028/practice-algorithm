package copylistrandomp;

import java.util.HashMap;

class RandomListNode {
    int label;
    RandomListNode next, random;

    RandomListNode(int x) {
        this.label = x;
    }
}

/*
https://www.interviewbit.com/problems/copy-list/
 */
public class CopyListWithRandomP {
    /*
        O(n) time and space
     */
    private static RandomListNode getClone(HashMap<RandomListNode, RandomListNode> cache, RandomListNode node) {
        if (cache.containsKey(node)) {
            return cache.get(node);
        }
        RandomListNode clone = new RandomListNode(node.label);
        cache.put(node, clone);
        return clone;
    }

    public RandomListNode copyRandomList(RandomListNode head) {
        if (head == null) {
            return null;
        }
        HashMap<RandomListNode, RandomListNode> oldToNew = new HashMap<>();
        RandomListNode node = head;

        while (node != null) {
            RandomListNode clone = getClone(oldToNew, node);
            boolean hasNext = node.next != null;
            if (hasNext) {
                RandomListNode cloneNext = getClone(oldToNew, node.next);
                clone.next = cloneNext;
            }
            boolean hasRandom = node.random != null;
            if (hasRandom) {
                RandomListNode cloneRandom = getClone(oldToNew, node.random);
                clone.random = cloneRandom;
            }

            node = node.next;
        }

        return getClone(oldToNew, head);
    }
}
