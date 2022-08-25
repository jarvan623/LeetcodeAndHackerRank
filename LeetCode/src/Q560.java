import java.util.HashMap;

public class Q560 {
  public static void main(String[] args) {
    int[] nums =
        new int[] {
          1, 2, 1, 2, 3, 1, 3, 2, 2, 1, 1, 1, 3, 2, 1, 2, 3, 1, 2, 3, 1, 1, 2, 2, 1, 1, 2, 2, 1, 1,
          2, 1, 1, 2, 1, 1, 2, 2
        };
    System.out.println(subarraySum(nums, 3));
  }

  public static int subarraySum(int[] nums, int k) {
    int[] sums = new int[nums.length + 1];
    for (int i = 1; i < nums.length + 1; i++) {
      sums[i] = sums[i - 1] + nums[i - 1];
    }
    int count = 0;
    // double for loops n^2
    /*for(int i = 1;i<sums.length;i++){
        for(int j = 0;j<i;j++){
            if(sums[i]-sums[j]==k){
                count++;
            }
        }
    }*/
    HashMap<Integer, Integer> map = new HashMap<>();
    map.put(0, 1);
    for (int i = 1; i < sums.length; i++) {
      int gap = sums[i] - k;
      if (map.get(gap) != null) {
        count += map.get(gap);
      }
      map.put(sums[i], map.getOrDefault(sums[i], 0) + 1);
    }
    return count;
  }
}
