import java.util.HashMap;

public class Q3 {
  /*Given a string s, find the length of the longest substring without repeating characters.

  https://leetcode.cn/problems/longest-substring-without-repeating-characters*/
  public static void main(String[] args) {
    String s = "pwwkew";
    System.out.println(lengthOfLongestSubstring(s));
  }

  public static int lengthOfLongestSubstring(String s) {
    HashMap<Character, Integer> map = new HashMap<>();
    int left = 0;
    int right = 0;
    int answer = 0;
    while (right < s.length()) {
      char c1 = s.charAt(right);
      right++;
      map.put(c1, map.getOrDefault(c1, 0) + 1);
      // when facing repeat letters, cut the window until no repeat
      while (map.get(c1) > 1) {
        char c2 = s.charAt(left);
        left++;
        map.put(c2, map.get(c2) - 1);
      }
      // when no repeat,try to refresh the answer
      answer = Math.max(answer, right - left);
    }
    return answer;
  }
}
