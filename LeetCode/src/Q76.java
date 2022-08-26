import java.util.HashMap;

public class Q76 {
  /*Given two strings s and t of lengths m and n respectively, return the minimum window substring of s such that every character in t (including duplicates) is included in the window. If there is no such substring, return the empty string "".

  The testcases will be generated such that the answer is unique.

  A substring is a contiguous sequence of characters within the string.
          https://leetcode.cn/problems/minimum-window-substring*/
  public static void main(String[] args) {
    String s = "ADOBECODEBANC";
    String t = "ABC";
    System.out.println(minWindow(s, t));
  }

  public static String minWindow(String s, String t) {
    HashMap<Character, Integer> targetMap = new HashMap<>();
    for (Character c : t.toCharArray()) {
      targetMap.put(c, targetMap.getOrDefault(c, 0) + 1);
    }
    int targetCount = targetMap.size();
    HashMap<Character, Integer> windowMap = new HashMap<>();
    int right = 0;
    int left = 0;
    int length = s.length();
    int count = 0;
    for (int i = 0; i < length; i++) {
      // get the position of first char in t, and initialise this position to both left and right
      // flags
      if (targetMap.containsKey(s.charAt(i))) {
        right = i;
        left = i;
        break;
      }
    }
    String answer = "";
    while (right < length) {
      char c = s.charAt(right);
      right++;
      if (targetMap.containsKey(c)) {
        windowMap.put(c, windowMap.getOrDefault(c, 0) + 1);
        if (windowMap.get(c).equals(targetMap.get(c))) {
          count++;
        }
        // while satisfying, cut the window
        while (count == targetCount) {
          if (answer.equals("") || answer.length() > right - left) {
            answer = s.substring(left, right);
          }
          char c1 = s.charAt(left);
          left++;
          if (targetMap.containsKey(c1)) {
            if (windowMap.get(c1).equals(targetMap.get(c1))) {
              count--;
            }
            windowMap.put(c1, windowMap.get(c1) - 1);
          }
        }
      }
    }
    return answer;
  }
}
