import java.util.ArrayList;
import java.util.List;

public class Q77 {
  /*Given two integers n and k, return all possible combinations of k numbers chosen from the range [1, n].

  You may return the answer in any order.

  https://leetcode.cn/problems/combinations*/
  public static void main(String[] args) {
    List<List<Integer>> combine = combine(4, 2);
    for (List<Integer> list : combine) {
      System.out.println(list.toString());
    }
  }

  static List<List<Integer>> answer;

  public static List<List<Integer>> combine(int n, int k) {
    answer = new ArrayList<>();
    int[] nums = new int[n];
    // nums records elements for combination
    for (int i = 0; i < n; i++) {
      nums[i] = i + 1;
    }
    List<Integer> track = new ArrayList<>();
    backTrack(nums, track, k, 0);
    return answer;
  }

  private static void backTrack(int[] nums, List<Integer> track, int k, int begin) {
    if (track.size() == k) {
      // add this track to answer
      answer.add(new ArrayList<>(track));
      return;
    }
    // start from begin which could avoid repeat result like [1,2] and [2,1]
    for (int i = begin; i < nums.length; i++) {
      track.add(nums[i]);
      backTrack(nums, track, k, i + 1);
      track.remove(track.size() - 1);
    }
    return;
  }
}
