import java.util.HashMap;
import java.util.Random;

public class Q710 {
  /*You are given an integer n and an array of unique integers blacklist.
  Design an algorithm to pick a random integer in the range [0, n - 1] that is not in blacklist.
  Any integer that is in the mentioned range and not in blacklist should be equally likely to be returned.

  Optimize your algorithm such that it minimizes the number of calls to the built-in random function of your language.

  Implement the Solution class:

  Solution(int n, int[] blacklist) Initializes the object with the integer n and the blacklisted integers blacklist.
  int pick() Returns a random integer in the range [0, n - 1] and not in blacklist.
  https://leetcode.cn/problems/random-pick-with-blacklist*/
  public static void main(String[] args) {
    Solution s = new Solution(7, new int[] {2, 3, 5});
    System.out.println(s.pick());
    System.out.println(s.pick());
    System.out.println(s.pick());
    System.out.println(s.pick());
    System.out.println(s.pick());
    System.out.println(s.pick());
    System.out.println(s.pick());
  }

  static class Solution {
    HashMap<Integer, Integer> map = new HashMap<>();
    int range;
    int right;
    Random random = new Random();

    public Solution(int n, int[] blacklist) {
      int size = blacklist.length;
      // range is for random, Random will select int in this range rather than in n
      this.range = n - size;
      this.right = n - size;
      for (int i : blacklist) {
        map.put(i, -1);
      }
      for (int i : blacklist) {
        // if in random range, this i would be replaced by int after range
        if (i < range) {
          // if pointer right already in map, means it needs no replace
          // because Random will never get int after size()-n
          while (map.containsKey(right)) {
            right++;
          }
          map.put(i, right);
          right++;
        }
      }
    }

    public int pick() {
      int rd = random.nextInt(range);
      return map.containsKey(rd) ? map.get(rd) : rd;
    }
  }
}
