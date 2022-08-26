import java.util.HashMap;

public class Q567 {
  /*Given two strings s1 and s2, return true if s2 contains a permutation of s1, or false otherwise.

  In other words, return true if one of s1's permutations is the substring of s2.

  https://leetcode.cn/problems/permutation-in-string*/

  public static void main(String[] args) {
    String s1 = "abc";
    String s2 = "eidbaooo";
    System.out.println(checkInclusion(s1, s2));
  }

  public static boolean checkInclusion(String s1, String s2) {
    HashMap<Character, Integer> targetMap = new HashMap<>();
    for (char c : s1.toCharArray()) {
      targetMap.put(c, targetMap.getOrDefault(c, 0) + 1);
    }
    int targetCount = targetMap.size();
    HashMap<Character, Integer> windowMap = new HashMap<>();
    int count = 0;
    int left = 0;
    int right = 0;
    int len = s1.length();
    while (right < s2.length()) {
      char c1 = s2.charAt(right);
      right++;
      if (targetMap.containsKey(c1)) {
        windowMap.put(c1, windowMap.getOrDefault(c1, 0) + 1);
        if (windowMap.get(c1).equals(targetMap.get(c1))) {
          count++;
        }
      }
      while (count == targetCount) {
        if (right - left == len) {
          return true;
        }
        char c2 = s2.charAt(left);
        left++;
        if (targetMap.containsKey(c2)) {
          if (targetMap.get(c2).equals(windowMap.get(c2))) {
            count--;
          }
          windowMap.put(c2, windowMap.get(c2) - 1);
        }
      }
    }
    return false;
  }
}
