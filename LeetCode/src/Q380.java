import java.util.ArrayList;
import java.util.HashMap;
import java.util.Random;

public class Q380 {

  /*RandomizedSet() Initializes the RandomizedSet object.
  bool insert(int val) Inserts an item val into the set if not present. Returns true if the item was not present, false otherwise.
          bool remove(int val) Removes an item val from the set if present. Returns true if the item was present, false otherwise.
  int getRandom() Returns a random element from the current set of elements (it's guaranteed that at least one element exists when this method is called).
  Each element must have the same probability of being returned.
  You must implement the functions of the class such that each function works in average O(1) time complexity.
  https://leetcode.cn/problems/insert-delete-getrandom-o1
  */
  public static void main(String[] args) {
    //
  }

  static class RandomizedSet {
    Random random = new Random();
    HashMap<Integer, Integer> valToIndex = new HashMap<>();
    ArrayList<Integer> list = new ArrayList<>();

    public RandomizedSet() {}

    public boolean insert(int val) {
      if (valToIndex.containsKey(val)) {
        return false;
      }
      list.add(val);
      valToIndex.put(val, list.size() - 1);
      return true;
    }

    public boolean remove(int val) {
      if (!valToIndex.containsKey(val)) {
        return false;
      }
      int index = valToIndex.get(val);
      int tail = list.get(list.size() - 1);
      list.set(index, tail);
      list.remove(list.size() - 1);
      // put then remove could ensure success even if the val is same as tail
      valToIndex.put(tail, index);
      valToIndex.remove(val);
      return true;
    }

    public int getRandom() {
      return list.get(random.nextInt(list.size()));
    }
  }
}
