public class MaximumAverageSubarrayI {
    /*
    solution: brutal force

    time complexity: O(n), most elements are calculated multiple times (4)
    space complexity: O(1)

    history:
    1. j <= j + k - 1; => get IndexOutOfBound error. it should be j <= i + k - 1;
    */
    public double findMaxAverage(int[] nums, int k) {
        int len = nums.length;
        double maxAvg = Integer.MIN_VALUE;
        for (int i = 0; i + k - 1 < len; i++) {
            int sum = 0;
            for (int j = i; j <= i + k - 1; j++) {
                sum += nums[j];
            }
            maxAvg = Math.max((double)sum / k, maxAvg);
        }
        return maxAvg;
    }

    /*
    solution: sliding window
    beats 15.7%

    time complexity: O(n): each element is calculated exactly once
    space complexity: O(1)

    history:
    1. maxAvg = Math.max(maxAvg, sum / k); => got wrong answer: 12.0000. It's supposed to
    be maxAvg = Math.max(maxAvg, (double)sum / k);
    */
    public double findMaxAverage_improved_1(int[] nums, int k) {
        int len = nums.length, sum = 0;
        for (int i = 0; i < k; i++) {
            sum += nums[i];
        }
        double maxAvg = (double)sum / k;
        for (int i = 1; i + k - 1 < len; i++) {
            sum = (sum - nums[i-1] + nums[i + k -1]);
            maxAvg = Math.max(maxAvg, (double)sum / k);
        }
        return maxAvg;
    }

    /*
    solution: compared to above, it reduces the operation to calculate average.
    beats 50.73%
    */
    public double findMaxAverage_improved_2(int[] nums, int k) {
        int len = nums.length;
        double sum = 0.0, maxSum = 0.0;
        for (int i = 0; i < k; i++) {
            sum += nums[i];
        }
        maxSum = sum;
        for (i = 1; i + k - 1 < len; i++) {
            sum = sum - nums[i-1] + nums[i + k - 1];
            if (maxSum < sum) maxSum = sum;
        }
        return maxSum / k;
    }
}
