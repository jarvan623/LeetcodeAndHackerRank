public class Q1011 {
  /* A conveyor belt has packages that must be shipped from one port to another within days days.

  The ith package on the conveyor belt has a weight of weights[i]. Each day, we load the ship with packages on the conveyor belt (in the order given by weights). We may not load more weight than the maximum weight capacity of the ship.

  Return the least weight capacity of the ship that will result in all the packages on the conveyor belt being shipped within days days.

  https://leetcode.cn/problems/capacity-to-ship-packages-within-d-days*/

  public static void main(String[] args) {
    int[] weights = new int[] {1, 2, 3, 1, 1};
    int day = 4;
    System.out.println(shipWithinDays(weights, day));
  }

  public static int shipWithinDays(int[] weights, int days) {
    int low = 0;
    int high = 0;
    for (int weight : weights) {
      //lowest capacity must bigger than biggest package
      low = Math.max(weight, low);
      //highest capacity would be the total weight
      high += weight;
    }
    while (low <= high) {
      int mid = low + (high - low) / 2;
      if (shipDays(weights, mid) > days) {
        low = mid + 1;
      } else if (shipDays(weights, mid) < days) {
        high = mid - 1;
      } else {
        //if high = mid, the while loop might be infinite if low=high
        high = mid - 1;
      }
    }
    return high + 1;
  }

  public static int shipDays(int[] weights, int capacity) {
    int answer = 0;
    int remain = capacity;
    for (int weight : weights) {
      if (remain - weight > 0) {
        remain -= weight;
      } else if (remain - weight == 0) {
        answer++;
        remain = capacity;
      } else {
        answer++;
        remain = capacity - weight;
      }
    }
    return remain == capacity ? answer : answer + 1;
  }
}
