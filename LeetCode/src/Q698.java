import java.util.Arrays;

public class Q698 {
  /*Given an integer array nums and an integer k
  return true if it is possible to divide this array into k non-empty subsets whose sums are all equal.

  https://leetcode.cn/problems/partition-to-k-equal-sum-subsets*/
  public static void main(String[] args) {
    int[] nums = new int[] {4, 3, 2, 3, 5, 2, 1};
    System.out.println(canPartitionKSubsets(nums, 4));
  }

  static int target;
  static int[] bucket;

  public static boolean canPartitionKSubsets(int[] nums, int k) {
    // clear some simple conditions
    if (k == 1) {
      // any arrays would be divided into one peace
      return true;
    }
    if (k > nums.length) {
      // if k is bigger than array's size, it is impossible
      return false;
    }
    int total = 0;
    int min = 99999;
    int max = 0;
    for (int i : nums) {
      min = Math.min(min, i);
      max = Math.max(max, i);
      total += i;
    }
    if (total % k != 0) {
      return false;
    }
    target = total / k;
    if (max > target || max < target && (max + min) > target) {
      return false;
    }
    // traverse from the biggest element which could reduce time consumption
    Arrays.sort(nums);
    // bucket represents k bucket need to be filled with water
    bucket = new int[k];
    // traverse from the biggest element which could reduce time consumption
    return backTrack(nums, nums.length - 1);
  }

  static boolean backTrack(int[] nums, int index) {
    if (index == -1) {
      // when index is -1, all water has been tried
      // only if all bucket is target value, return true
      for (int i : bucket) {
        if (i != target) {
          return false;
        }
      }
      return true;
    }
    for (int i = 0; i < bucket.length; i++) {
      // if current will exceed current bucket, try next bucket
      if (nums[index] + bucket[i] > target) {
        continue;
      }
      // water go in bucket
      bucket[i] += nums[index];
      if (backTrack(nums, index - 1)) {
        // try next water,if true,true
        return true;
      }
      // if false,put this water to next bucket and go on...
      bucket[i] -= nums[index];
    }
    return false;
  }
}
