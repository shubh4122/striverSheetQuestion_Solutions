package striversSheet.medium;

import java.util.HashMap;


//https://leetcode.com/problems/lru-cache/editorial/
public class stack_Q_LRUcache {
    /*
        Working:
        Head always points to Most recent used node.
        The DLL is used to keep track of LRU.(least recent used = tail.prev)
        While Map stores val and size check
     */
    int size;
    HashMap<Integer, Node> map;//<Key, node>
    Node head, tail;

    //Standard LRU cache. O(1) approach
    public stack_Q_LRUcache(int capacity) {
        size = capacity;
        map = new HashMap<>();
        head = new Node(-1, -1);
        tail = new Node(-1, -1);

        head.next = tail;
        head.prev = null;

        tail.next = null;
        tail.prev = head;
    }


    public int get(int key) {
        if (map.containsKey(key)) {
            Node node = map.get(key);
            //Update LRU - remove and re-insert.
            removeLRU(node);
            insertLRU(node);
        }
        return map.getOrDefault(key, new Node(-1, -1)).val;
    }

    public void put(int key, int value) {
        if (!map.containsKey(key)) {
            if (map.size() == size) {
                removeLRU(tail.prev);
            }
        }
        else {
            //remove already existing key-val, and reinsert - UPDATION
            removeLRU(map.get(key));
        }
        insertLRU(new Node(key, value));
    }


    //UTILS
    private void removeLRU(Node node) {
        map.remove(node.key);
        node.prev.next = node.next;
        node.next.prev = node.prev;
    }

    private void insertLRU(Node node) {
        map.put(node.key, node);
        Node temp = head.next;

        head.next = node;
        node.next = temp;
        node.prev = head;
        temp.prev = node;
    }

    //Doubly LL node
    class Node {
        Node prev, next;
        int key, val;
        Node(int k, int v) {
            key = k;
            val = v;
        }
    }


    //O(n) approach - Mine

//    int size;
//    HashMap<Integer, Integer> map;
//    LinkedList<Integer> lru;
//
//    public stack_Q_LRUcache(int capacity) {
//        size = capacity;
//        map = new HashMap<>();
//        lru = new LinkedList<>();
//    }
//
//    public int get(int key) {
//        if (map.containsKey(key)){
//            //updating position of elem/key. Remove from current location
//            lru.removeFirstOccurrence(key);//o(n)
//            lru.add(key);//add to last, meaning it was recently used.
//        }
//
//        return map.getOrDefault(key, -1);
//    }
//
//    public void put(int key, int value) {
//        //insertion of new key
//        if(!map.containsKey(key)) {
//            lru.add(key);
//            if (lru.size() > size) {
//                int temp = lru.remove();//the front elem of Q will be the least used
//                map.remove(temp);
//            }
//
//            map.put(key, value);
//        }
//        //updation
//        else {
//            map.put(key, value);
//            //updating position of elem/key. Remove from current location
//            lru.removeFirstOccurrence(key);
//            lru.add(key);//add to last, meaning it was recently used.
//        }
//    }
}
