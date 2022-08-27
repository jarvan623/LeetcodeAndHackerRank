public class Q875 {
  public static void main(String[] args) {
    /*Koko loves to eat bananas. There are n piles of bananas, the ith pile has piles[i] bananas. The guards have gone and will come back in h hours.

            Koko can decide her bananas-per-hour eating speed of k. Each hour, she chooses some pile of bananas and eats k bananas from that pile. If the pile has less than k bananas, she eats all of them instead and will not eat any more bananas during this hour.

            Koko likes to eat slowly but still wants to finish eating all the bananas before the guards return.

    Return the minimum integer k such that she can eat all the bananas within h hours.

    https://leetcode.cn/problems/koko-eating-bananas*/

    int[] piles = new int[] {805306368, 805306368, 805306368};
    int h = 1000000000;
    System.out.println(minEatingSpeed(piles, h));
  }

  public static int minEatingSpeed(int[] piles, int h) {
    int low = 1;
    int high = 0;
    for (int pile : piles) {
      // because h > piles.length,thus the max speed is the max pile which will be finished no
      // bigger than h
      high = Math.max(pile, high);
    }
    while (low <= high) {
      int mid = low + (high - low) / 2;
      if (hours(piles, mid) > h) {
        low = mid + 1;
      } else if (hours(piles, mid) < h) {
        high = mid - 1;
      } else {
        high = mid - 1;
      }
    }
    return high + 1;
  }
  // use long to record hour to avoid int overflow cases
  public static long hours(int[] piles, int speed) {
    long hour = 0;
    for (int pile : piles) {
      if (pile <= speed) {
        hour++;
      } else {
        hour += pile % speed == 0 ? pile / speed : pile / speed + 1;
      }
    }
    return hour;
  }
}
