import java.util.HashMap;
import java.util.LinkedHashMap;

public class Q146 {
  /*Design a data structure that follows the constraints of a Least Recently Used (LRU) cache.

  Implement the LRUCache class:

  LRUCache(int capacity) Initialize the LRU cache with positive size capacity.
  int get(int key) Return the value of the key if the key exists, otherwise return -1.
  void put(int key, int value) Update the value of the key if the key exists. Otherwise, add the key-value pair to the cache. If the number of keys exceeds the capacity from this operation, evict the least recently used key.
  The functions get and put must each run in O(1) average time complexity.
  https://leetcode.cn/problems/lru-cache*/
  public static void main(String[] args) {
    LRUCache2 cache = new LRUCache2(2);
    cache.put(1, 1);
    cache.put(2, 2);
    cache.put(1, 88);
    cache.put(3, 3);
    System.out.println(cache.get(1));
    System.out.println(cache.get(2));
    System.out.println(cache.get(3));
  }
}

class LRUCache1 {
  // LinkedHashMap easy to understand
  LinkedHashMap<Integer, Integer> map = new LinkedHashMap<>();
  int cap;

  public LRUCache1(int capacity) {
    cap = capacity;
  }

  public int get(int key) {
    if (map.containsKey(key)) {
      // if exists, make it to the tail of map
      makeRecent(key);
      return map.get(key);
    } else {
      return -1;
    }
  }

  public void put(int key, int value) {
    if (map.containsKey(key)) {
      // if exists, make it to the tail of map
      map.put(key, value);
      makeRecent(key);
      return;
    } else {
      // if not exists, firstly check the size
      if (cap == map.size()) {
        // remove the first(leastRecent) element
        int leastRecentKey = map.keySet().iterator().next();
        map.remove(leastRecentKey);
      }
      map.put(key, value);
    }
  }

  public void makeRecent(int key) {
    int value = map.get(key);
    map.remove(key);
    map.put(key, value);
  }
}

class LRUCache2 {
  // double list has prev and next which means add and remove run in O(1)
  // with HashMap, can easily update, remove or move any node
  DoubleList cache = new DoubleList();
  HashMap<Integer, Node> map = new HashMap<>();
  int cap;

  public LRUCache2(int capacity) {
    this.cap = capacity;
  }

  public int get(int key) {
    if (map.containsKey(key)) {
      Node x = map.get(key);
      // make this node mostRecent
      makeRecent(x);
      return x.val;
    } else {
      return -1;
    }
  }

  public void put(int key, int value) {
    if (map.containsKey(key)) {
      // if already in the cache, update it
      map.get(key).val = value;
      makeRecent(map.get(key));
      return;
    } else {
      // if not in cache, which means to insert a new node, so firstly check the size
      if (cache.size() == cap) {
        Node node = cache.removeFirst();
        map.remove(node.key);
      }
      Node x = new Node(key, value);
      map.put(key, x);
      cache.addLast(x);
    }
  }

  public void makeRecent(Node x) {
    cache.remove(x);
    cache.addLast(x);
  }
}

class Node {
  public int key, val;
  public Node prev, next;

  public Node(int k, int v) {
    this.key = k;
    this.val = v;
  }
}

class DoubleList {
  private Node head, tail;
  private int size;

  public DoubleList() {
    head = new Node(0, 0);
    tail = new Node(0, 0);
    head.next = tail;
    tail.prev = head;
    size = 0;
  }

  public void addLast(Node x) {
    tail.prev.next = x;
    x.next = tail;
    x.prev = tail.prev;
    tail.prev = x;
    size++;
  }

  public int size() {
    return size;
  }

  public void remove(Node x) {
    x.prev.next = x.next;
    x.next.prev = x.prev;
    size--;
  }

  public Node removeFirst() {
    Node first = head.next;
    remove(first);
    return first;
  }
}
