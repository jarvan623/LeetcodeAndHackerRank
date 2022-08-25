public class Q303 {
  public static void main(String[] args) {
    int[] nums = new int[] {-2, 0, 3, -5, 2, -1};
    NumArray numArray = new NumArray(nums);
    System.out.println(numArray.sumRange(0, 2));
    System.out.println(numArray.sumRange(2, 5));
    System.out.println(numArray.sumRange(0, 5));
  }

  static class NumArray {
    int[] sums;

    public NumArray(int[] nums) {
      int length = nums.length;
      sums = new int[length + 1];
      for (int i = 1; i < length + 1; i++) {
        sums[i] = sums[i - 1] + nums[i - 1];
      }
    }

    public int sumRange(int left, int right) {
      return sums[right + 1] - sums[left];
    }
  }
}
