import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.LinkedHashSet;

public class Q460 {
  /*Design and implement a data structure for a Least Frequently Used (LFU) cache.

  Implement the LFUCache class:

  LFUCache(int capacity) Initializes the object with the capacity of the data structure.
  int get(int key) Gets the value of the key if the key exists in the cache. Otherwise, returns -1.
  void put(int key, int value) Update the value of the key if present,
  or inserts the key if not already present. When the cache reaches its capacity,
  it should invalidate and remove the least frequently used key before inserting a new item.For this problem, when there is a tie (i.e., two or more keys with the same frequency), the least recently used key would be invalidated.
  To determine the least frequently used key, a use counter is maintained for each key in the cache. The key with the smallest use counter is the least frequently used key.
  When a key is first inserted into the cache, its use counter is set to 1 (due to the put operation). The use counter for a key in the cache is incremented either a get or put operation is called on it.
  The functions get and put must each run in O(1) average time complexity.
  https://leetcode.cn/problems/lfu-cache*/
  public static void main(String[] args) {
    // FreqToRec
    // lestFreq
    //
    LFUCache3 cache = new LFUCache3(10);
    int[][] input =
        new int[][] {
          {10, 13}, {3, 17}, {6, 11}, {10, 5}, {9, 10}, {13}, {2, 19}, {2}, {3}, {5, 25}, {8},
          {9, 22}, {5, 5}, {1, 30}, {11}, {9, 12}, {7}, {5}, {8}, {9}, {4, 30}, {9, 3}, {9}, {10},
          {10}, {6, 14}, {3, 1}, {3}, {10, 11}, {8}, {2, 14}, {1}, {5}, {4}, {11, 4}, {12, 24},
          {5, 18}, {13}, {7, 23}, {8}, {12}, {3, 27}, {2, 12}, {5}, {2, 9}, {13, 4}, {8, 18},
          {1, 7}, {6}, {9, 29}, {8, 21}, {5}, {6, 30}, {1, 12}, {10}, {4, 15}, {7, 22}, {11, 26},
          {8, 17}, {9, 29}, {5}, {3, 4}, {11, 30}, {12}, {4, 29}, {3}, {9}, {6}, {3, 4}, {1}, {10},
          {3, 29}, {10, 28}, {1, 20}, {11, 13}, {3}, {3, 12}, {3, 8}, {10, 9}, {3, 26}, {8}, {7},
          {5}, {13, 17}, {2, 27}, {11, 15}, {12}, {9, 19}, {2, 15}, {3, 16}, {1}, {12, 17}, {9, 1},
          {6, 19}, {4}, {5}, {5}, {8, 1}, {11, 7}, {5, 2}, {9, 28}, {1}, {2, 2}, {7, 4}, {4, 22},
          {7, 24}, {9, 26}, {13, 28}, {11, 26}
        };
    String[] operation =
        new String[] {
          "put", "put", "put", "put", "put", "get", "put", "get", "get", "put", "get", "put", "put",
          "put", "get", "put", "get", "get", "get", "get", "put", "put", "get", "get", "get", "put",
          "put", "get", "put", "get", "put", "get", "get", "get", "put", "put", "put", "get", "put",
          "get", "get", "put", "put", "get", "put", "put", "put", "put", "get", "put", "put", "get",
          "put", "put", "get", "put", "put", "put", "put", "put", "get", "put", "put", "get", "put",
          "get", "get", "get", "put", "get", "get", "put", "put", "put", "put", "get", "put", "put",
          "put", "put", "get", "get", "get", "put", "put", "put", "get", "put", "put", "put", "get",
          "put", "put", "put", "get", "get", "get", "put", "put", "put", "put", "get", "put", "put",
          "put", "put", "put", "put", "put"
        };

    for (int i = 0; i < input.length; i++) {
      if (operation[i].equals("put")) {
        cache.put(input[i][0], input[i][1]);
      } else {
        System.out.println(cache.get(input[i][0]));
      }
    }
  }

  static class LFUCache3 {
    // LinkedHashSet use less memory than LinkedHashMap because value only be stored for once
    HashMap<Integer, Integer> keyToValue = new HashMap<>();
    HashMap<Integer, Integer> keyToFreq = new HashMap<>();
    HashMap<Integer, LinkedHashSet<Integer>> freqToKeys = new HashMap<>();
    int cap;
    int totalSize = 0;
    int minFreq = 1;

    public LFUCache3(int capacity) {
      cap = capacity;
    }

    public int get(int key) {
      if (keyToValue.containsKey(key)) {
        updateFreq(key);
        return keyToValue.get(key);
      } else {
        return -1;
      }
    }

    public void updateFreq(int key) {
      int freq = keyToFreq.get(key);
      keyToFreq.put(key, freq + 1);
      LinkedHashSet<Integer> oldSet = freqToKeys.get(freq);
      oldSet.remove(key);
      if (oldSet.size() == 0 && minFreq == freq) {
        minFreq++;
      }
      freqToKeys.putIfAbsent(freq + 1, new LinkedHashSet<>());
      freqToKeys.get(freq + 1).add(key);
    }

    public void put(int key, int value) {
      if (cap == 0) {
        return;
      }
      if (keyToValue.containsKey(key)) {
        keyToValue.put(key, value);
        updateFreq(key);
      } else {
        if (cap == totalSize) {
          LinkedHashSet<Integer> old = freqToKeys.get(minFreq);
          int keyToDelete = old.iterator().next();
          old.remove(keyToDelete);
          keyToValue.remove(keyToDelete);
          keyToFreq.remove(keyToDelete);
          totalSize--;
        }
        totalSize++;
        keyToValue.put(key, value);
        keyToFreq.put(key, 1);
        freqToKeys.putIfAbsent(1, new LinkedHashSet<>());
        freqToKeys.get(1).add(key);
        minFreq = 1;
      }
    }
  }

  static class LFUCache1 {
    // use LinkedHashMap is easier
    HashMap<Integer, Integer> keyToValue = new HashMap<>();
    HashMap<Integer, Integer> keyToFreq = new HashMap<>();
    HashMap<Integer, LinkedHashMap<Integer, Integer>> freqToKeys = new HashMap<>();
    int totalSize;
    int cap;
    int minFreq = 0;

    public LFUCache1(int capacity) {
      this.cap = capacity;
      totalSize = 0;
    }

    public int get(int key) {
      if (cap == 0) {
        return -1;
      }
      if (keyToValue.containsKey(key)) {
        int value = keyToValue.get(key);
        updateFreqRec(key, value);
        return value;
      } else {
        return -1;
      }
    }

    public void put(int key, int value) {
      if (cap == 0) {
        return;
      }
      if (keyToValue.containsKey(key)) {
        keyToValue.put(key, value);
        updateFreqRec(key, value);
        return;
      } else {
        if (totalSize == cap) {
          totalSize--;
          LinkedHashMap<Integer, Integer> temp = freqToKeys.get(minFreq);
          int keyToDelete = temp.keySet().iterator().next();
          temp.remove(keyToDelete);
          keyToValue.remove(keyToDelete);
          keyToFreq.remove(keyToDelete);
        }
        minFreq = 1;
        totalSize++;
        keyToValue.put(key, value);
        keyToFreq.put(key, 1);
        freqToKeys.putIfAbsent(1, new LinkedHashMap<>());
        freqToKeys.get(1).put(key, value);
      }
    }

    public void updateFreqRec(int key, int value) {
      int freq = keyToFreq.get(key);
      keyToFreq.put(key, freq + 1);
      LinkedHashMap<Integer, Integer> oldList = freqToKeys.get(freq);
      oldList.remove(key);
      if (oldList.size() == 0 && minFreq == freq) {
        minFreq++;
      }
      freqToKeys.putIfAbsent(freq + 1, new LinkedHashMap<>());
      freqToKeys.get(freq + 1).put(key, value);
    }
  }

  static class LFUCache2 {
    int minFreq;
    int totalCount;
    int cap;
    HashMap<Integer, DoubleList> freqToRecent = new HashMap<>();
    HashMap<Integer, Node> keyToNode = new HashMap<>();
    HashMap<Integer, Integer> keyToFreq = new HashMap<>();

    public LFUCache2(int capacity) {
      this.cap = capacity;
      totalCount = 0;
      minFreq = 1;
    }

    public int get(int key) {
      if (cap == 0) {
        return -1;
      }
      if (keyToNode.containsKey(key)) {
        Node x = keyToNode.get(key);
        makeFreqAndRecent(x);
        return x.value;
      }
      return -1;
    }

    public void put(int key, int value) {
      if (cap == 0) {
        return;
      }
      if (keyToNode.containsKey(key)) {
        Node x = keyToNode.get(key);
        x.value = value;
        makeFreqAndRecent(x);
      } else {
        if (totalCount == cap) {
          // if overflow, first remove the least recent node in the least freq list
          DoubleList list = freqToRecent.get(minFreq);
          Node x = list.removeFirst();
          keyToNode.remove(x.key);
          keyToFreq.remove(x.key);
          // remember to minus the total size
          totalCount--;
        }
        totalCount++;
        Node temp = new Node(key, value);
        keyToNode.put(key, temp);
        keyToFreq.put(key, 1);
        freqToRecent.putIfAbsent(1, new DoubleList());
        freqToRecent.get(1).addLast(temp);
        minFreq = 1;
      }
    }

    private void makeFreqAndRecent(Node x) {
      // update freq
      int key = x.key;
      int freq = keyToFreq.get(key);
      keyToFreq.put(key, freq + 1);
      DoubleList list = freqToRecent.get(freq);
      list.remove(x);
      if (list.size() == 0 && minFreq == freq) {
        // if current node is the last one in the least freq list
        minFreq++;
      }
      freqToRecent.putIfAbsent(freq + 1, new DoubleList());
      freqToRecent.get(freq + 1).addLast(x);
    }
  }
  // class Node is duplicate with other question, here is just for exercise and easy reading/review
  static class Node {
    public int key, value;
    public Node prev, next;

    public Node(int k, int v) {
      this.key = k;
      this.value = v;
    }
  }
  // class DoubleList is duplicate with other question, here is just for exercise
  static class DoubleList {
    private Node head, tail;
    private int size;

    public DoubleList() {
      head = new Node(0, 0);
      tail = new Node(0, 0);
      head.next = tail;
      tail.prev = head;
      size = 0;
    }

    public int size() {
      return size;
    }

    public void addLast(Node x) {
      tail.prev.next = x;
      x.next = tail;
      x.prev = tail.prev;
      tail.prev = x;
      size++;
    }

    public void remove(Node x) {
      x.prev.next = x.next;
      x.next.prev = x.prev;
      size--;
    }

    public Node removeFirst() {
      Node x = head.next;
      remove(x);
      return x;
    }
  }
}
