import java.util.Comparator;
import java.util.PriorityQueue;

public class Q295 {
  /*The median is the middle value in an ordered integer list. If the size of the list is even,
  there is no middle value and the median is the mean of the two middle values.
  For example, for arr = [2,3,4], the median is 3.
  For example, for arr = [2,3], the median is (2 + 3) / 2 = 2.5.
  Implement the MedianFinder class:
  MedianFinder() initializes the MedianFinder object.
  void addNum(int num) adds the integer num from the data stream to the data structure.
  double findMedian() returns the median of all elements so far. Answers within 10-5 of the actual answer will be accepted.
      https://leetcode.cn/problems/find-median-from-data-stream*/
  public static void main(String[] args) {
    //
  }

  static class MedianFinder {
    PriorityQueue<Integer> bigNumbers = new PriorityQueue<>();
    PriorityQueue<Integer> smallNumbers =
        new PriorityQueue(
            new Comparator<Integer>() {
              @Override
              public int compare(Integer o1, Integer o2) {
                return o2 - o1;
              }
            });

    public MedianFinder() {}

    public void addNum(int num) {
      // first number go into small queue and go to bigqueue
      // second number go into big queue and compare with first number then decide which one go to
      // smallqueue
      // in this way,ensure numbers in small are smaller than any numbers in big
      if (smallNumbers.size() >= bigNumbers.size()) {
        smallNumbers.add(num);
        bigNumbers.add(smallNumbers.poll());
      } else {
        bigNumbers.add(num);
        smallNumbers.add(bigNumbers.poll());
      }
    }

    public double findMedian() {
      if (smallNumbers.size() > bigNumbers.size()) {
        return smallNumbers.peek();
      } else if (smallNumbers.size() < bigNumbers.size()) {
        return bigNumbers.peek();
      } else {
        return (smallNumbers.peek() + bigNumbers.peek()) / 2.0;
      }
    }
  }
}
