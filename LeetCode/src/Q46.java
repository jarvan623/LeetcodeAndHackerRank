import java.util.ArrayList;
import java.util.List;

public class Q46 {
  /*Given an array nums of distinct integers, return all the possible permutations.
  You can return the answer in any order.
  https://leetcode.cn/problems/permutations*/
  public static void main(String[] args) {
    int[] nums = new int[] {1, 2, 3, 4};
    List<List<Integer>> permute = permute(nums);
    for (List<Integer> list : permute) {
      System.out.println(list.toString());
    }
  }

  static List<List<Integer>> answer = new ArrayList<>();

  public static List<List<Integer>> permute(int[] nums) {
    List<Integer> track = new ArrayList<>();
    backTrack(track, nums);
    return answer;
  }

  public static void backTrack(List<Integer> track, int[] nums) {
    if (track.size() == nums.length) {
      // must add new list otherwise track would finally go empty
      // and all tracks in answer would all be empty because it is reference
      answer.add(new ArrayList(track));
    }
    for (int i = 0; i < nums.length; i++) {
      // already in track, skip to next element
      if (track.contains(nums[i])) {
        continue;
      }
      // add current element in track
      track.add(nums[i]);
      // keep doing backtrack for adding other elements
      backTrack(track, nums);
      // remove current element at current position and do other permutation
      track.remove(track.size() - 1);
    }
  }
}
