public class Q27 {
  /* Given an integer array nums and an integer val, remove all occurrences of val in nums in-place. The relative order of the elements may be changed.

  Since it is impossible to change the length of the array in some languages, you must instead have the result be placed in the first part of the array nums. More formally, if there are k elements after removing the duplicates, then the first k elements of nums should hold the final result. It does not matter what you leave beyond the first k elements.

  Return k after placing the final result in the first k slots of nums.

  Do not allocate extra space for another array. You must do this by modifying the input array in-place with O(1) extra memory.

  https://leetcode.cn/problems/remove-element*/
  public static void main(String[] args) {
    int[] nums = new int[] {0, 1, 2, 2, 3, 0, 4, 2};
    System.out.println(removeElement(nums, 2));
  }

  public static int removeElement(int[] nums, int val) {
    int slow = 0;
    int fast = 0;
    while (fast < nums.length) {
      if (nums[fast] != val) {
        // if slow and fast are at same position, no change would be done
        // if different position, fast element would be placed on slow position and slow will move
        nums[slow] = nums[fast];
        slow++;
      }
      fast++;
    }
    return slow;
  }
}
