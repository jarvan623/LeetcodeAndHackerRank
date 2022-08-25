/*There are n flights that are labeled from 1 to n.

You are given an array of flight bookings bookings, where bookings[i] = [firsti, lasti, seatsi] represents a booking for flights firsti through lasti (inclusive) with seatsi seats reserved for each flight in the range.

Return an array answer of length n, where answer[i] is the total number of seats reserved for flight i.

https://leetcode.cn/problems/corporate-flight-bookings*/

import java.util.Arrays;

public class Q1109 {
  public static void main(String[] args) {
    int[][] bookings = new int[][] {{1, 2, 10}, {2, 3, 20}, {2, 5, 25}};
    System.out.println(Arrays.toString(corpFlightBookings(bookings, 5)));
  }

  public static int[] corpFlightBookings(int[][] bookings, int n) {
    int[] diff = new int[n];
    for (int[] booking : bookings) {
      int start = booking[0];
      int end = booking[1];
      int seats = booking[2];
      diff[start - 1] += seats;
      if (end < n) {
        diff[end] -= seats;
      }
    }
    for (int i = 1; i < n; i++) {
      diff[i] = diff[i] + diff[i - 1];
    }
    return diff;
  }
}
