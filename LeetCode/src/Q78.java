import java.util.ArrayList;
import java.util.List;

public class Q78 {
  /*Given an integer array nums of unique elements, return all possible subsets (the power set).

  The solution set must not contain duplicate subsets. Return the solution in any order.

  https://leetcode.cn/problems/subsets*/
  public static void main(String[] args) {
    int[] nums = new int[] {1};
    List<List<Integer>> subsets = subsets(nums);
    for (List<Integer> list : subsets) {
      System.out.println(list.toString());
    }
  }

  static List<List<Integer>> answer;

  public static List<List<Integer>> subsets(int[] nums) {
    answer = new ArrayList<>();
    List<Integer> track = new ArrayList<>();
    backTrack(track, 0, nums);
    return answer;
  }

  public static void backTrack(List<Integer> track, int begin, int[] nums) {
    if (track != null) {
      // every case would firstly add an empty list into answer
      // cause empty is not null
      answer.add(new ArrayList<>(track));
    }
    for (int i = begin; i < nums.length; i++) {
      track.add(nums[i]);
      // only try elements after Begin index to avoid repeat list
      // because [1,2]and[2,1]are same list
      backTrack(track, i + 1, nums);
      track.remove(track.size() - 1);
    }
  }
}
