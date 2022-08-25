public class Q1094 {
  /*There is a car with capacity empty seats. The vehicle only drives east (i.e., it cannot turn around and drive west).

  You are given the integer capacity and an array trips where trips[i] = [numPassengersi, fromi, toi] indicates that the ith trip has numPassengersi passengers and the locations to pick them up and drop them off are fromi and toi respectively. The locations are given as the number of kilometers due east from the car's initial location.

  Return true if it is possible to pick up and drop off all passengers for all the given trips, or false otherwise.
          https://leetcode.cn/problems/car-pooling*/
  public static void main(String[] args) {
    int[][] trips = new int[][] {{9, 0, 1}, {3, 3, 7}};
    System.out.println(carPooling(trips, 4));
  }

  public static boolean carPooling(int[][] trips, int capacity) {
    int[] diff = new int[1001];
    for (int[] trip : trips) {
      int num = trip[0];
      int from = trip[1];
      int to = trip[2];
      diff[from] += num;
      if (to < 1000) {
        diff[to] -= num;
      }
    }
    if (diff[0] > capacity) {
      // corner case for first(0) station
      return false;
    }
    for (int i = 1; i < 1001; i++) {
      diff[i] = diff[i - 1] + diff[i];
      if (diff[i] > capacity) {
        return false;
      }
    }
    return true;
  }
}
