import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class Q438 {
  /*Given two strings s and p, return an array of all the start indices of p's anagrams in s. You may return the answer in any order.

  An Anagram is a word or phrase formed by rearranging the letters of a different word or phrase, typically using all the original letters exactly once.

  https://leetcode.cn/problems/find-all-anagrams-in-a-string*/
  public static void main(String[] args) {
    String s = "abab";
    String p = "ab";
    System.out.println(findAnagrams(s, p));
  }

  public static List<Integer> findAnagrams(String s, String p) {
    List<Integer> answer = new ArrayList<>();
    HashMap<Character, Integer> targetMap = new HashMap<>();
    for (char c1 : p.toCharArray()) {
      targetMap.put(c1, targetMap.getOrDefault(c1, 0) + 1);
    }
    int targetCount = targetMap.size();
    HashMap<Character, Integer> windowMap = new HashMap<>();
    int left = 0;
    int right = 0;
    int count = 0;
    while (right < s.length()) {
      char c2 = s.charAt(right);
      right++;
      if (targetMap.containsKey(c2)) {
        windowMap.put(c2, windowMap.getOrDefault(c2, 0) + 1);
        if (windowMap.get(c2).equals(targetMap.get(c2))) {
          count++;
        }
      }
      while (count == targetCount) {
        if (right - left == p.length()) {
          answer.add(left);
        }
        char c3 = s.charAt(left);
        left++;
        if (targetMap.containsKey(c3)) {
          if (windowMap.get(c3).equals(targetMap.get(c3))) {
            count--;
          }
          windowMap.put(c3, windowMap.get(c3) - 1);
        }
      }
    }
    return answer;
  }
}
