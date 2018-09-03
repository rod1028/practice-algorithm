package cracking.lru;

import java.util.HashMap;
import java.util.LinkedList;

//immutable node
//for optimization (memory and GC) purposes we can reuse the nodes
//instead of removing it from LRU and add a new one
class Node {
    public final int value;
    //we keep the key too so we don't have to search it trough all map entries
    //at every delete, when it becomes the LRU
    public final int key;

    public Node(int value, int key) {
        this.value = value;
        this.key = key;
    }
}

public class LRUCache {
    private HashMap<Integer, Node> map;
    private LinkedList list;
    private int cap;

    public LRUCache(int capacity) {
        map = new HashMap<>(capacity);
        list = new LinkedList();
        this.cap = capacity;
    }

    public int get(int key) {
        if (map.containsKey(key) == false) {
            return -1;
        }
        Node n = map.get(key);
        update(n);
        return n.value;
    }

    private void update(Node n) {
        //put it at the front of the queue, because it was accessed
        list.remove(n);
        list.addLast(n);
    }

    public void set(int key, int value) {
        Node n = new Node(value, key);
        //if the key exists, update it
        if (map.containsKey(key)) {
            Node oldNode = map.get(key);
            list.remove(oldNode);
            map.replace(key, n);
        } else {
            //if the size is at max, remove the LRU
            if (map.size() >= this.cap) {
                Node oldestNode = (Node) list.removeFirst();
                map.remove(oldestNode.key);
            }
            map.put(key, n);
        }
        //update the list queue
        update(n);
    }
}

