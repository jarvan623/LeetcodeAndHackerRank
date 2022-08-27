public class Q704 {
  /*Given an array of integers nums which is sorted in ascending order, and an integer target, write a function to search target in nums. If target exists, then return its index. Otherwise, return -1.

  You must write an algorithm with O(log n) runtime complexity.

  https://leetcode.cn/problems/binary-search*/
  public static void main(String[] args) {
    int[] nums = new int[] {-1, 0, 3, 5, 9, 12};
    System.out.println(search(nums, 9));
    //
  }

  public static int search(int[] nums, int target) {
    // nums is ascd
    int low = 0;
    int high = nums.length - 1;
    // low<=high could ensure array with only 1 element could enter the loop
    while (low <= high) {
      // same as (low+high)/2 but could avoid overflow
      int mid = low + (high - low) / 2;
      if (nums[mid] == target) {
        return mid;
      }
      if (nums[mid] > target) {
        high = mid - 1;
      } else {
        low = mid + 1;
      }
    }
    return -1;
  }
}
