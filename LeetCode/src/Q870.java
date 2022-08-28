import java.util.Arrays;
import java.util.Comparator;
import java.util.PriorityQueue;

public class Q870 {
  /*You are given two integer arrays nums1 and nums2 both of the same length. The advantage of nums1 with respect to nums2 is the number of indices i for which nums1[i] > nums2[i].

  Return any permutation of nums1 that maximizes its advantage with respect to nums2

  https://leetcode.cn/problems/advantage-shuffle*/
  public static void main(String[] args) {
    int[] nums1 = new int[] {12, 24, 8, 32};
    int[] nums2 = new int[] {13, 25, 32, 11};
    System.out.println(Arrays.toString(advantageCount(nums1, nums2)));
  }

  public static int[] advantageCount(int[] nums1, int[] nums2) {
    int[] answer = new int[nums1.length];
    Arrays.sort(nums1);
    // use PQ to sort nums2 and record the index at the same time
    PriorityQueue<int[]> maxPq =
        new PriorityQueue(
            new Comparator<int[]>() {
              public int compare(int[] i1, int[] i2) {
                return i2[1] - i1[1];
              }
            });
    for (int i = 0; i < nums2.length; i++) {
      maxPq.add(new int[] {i, nums2[i]});
    }
    int tail = nums1.length - 1;
    int head = 0;
    while (!maxPq.isEmpty()) {
      int[] temp = maxPq.poll();
      // if bigger,give the current max value to answer
      if (nums1[tail] > temp[1]) {
        answer[temp[0]] = nums1[tail];
        tail--;
      } else {
        // if equal or smaller,give the current min value to answer
        answer[temp[0]] = nums1[head];
        head++;
      }
    }
    return answer;
  }
}
