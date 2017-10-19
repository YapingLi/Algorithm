
/*
test case:
1. common case: [-2,1,-3,4,-1,2,1,-5,4]
2. edge case: null, empty, [-3], [-2, 1]
*/
public class MaxSubarray {

    /*
    solution: 两个变量：1. maxSum; 2. currSum;
    怎样判定要不要把当前数加到currSum中呢？
    如果currSum＋currNum < currSum => 比较更新currSum，currSum ＝ currNum;
    如果currSum＋currNum >= currSum => 比较更新currSum

    time complexity: O(n)
    space complexity: O(1)

    history:
    1. currSum = nums[i] => compile error: missing ';'
    2. if (currSum + nums[i] > currSum) { => wrong answer
    */
    public int maxSubArray(int[] nums) {
        if (nums == null || nums.length == 0) return 0;

        int currSum = nums[0], maxSum = nums[0];
        for (int i = 1; i < nums.length; i++) {
            if (currSum + nums[i] > nums[i]) {
                currSum += nums[i];
                maxSum = Math.max(maxSum, currSum);
            } else {
                currSum = nums[i];
                maxSum = Math.max(maxSum, currSum); //[0,3,1]
            }
        }
        maxSum = Math.max(maxSum, currSum);

        return maxSum;
    }

    /*
    solution:
    It starts at the left end (nums[1]) and scans through to the right end (nums[n]), keeping track of the maximum
    sum subarray seen so far.
    The maximum is initially nums[0].
    Suppose we've solved the problem for nums[1, ..., i-1], how can we extend that to A[1, ..., i]? the maximum sum
    in the first i element is either the maximum sum in the first i-1 elements, or the maximum sum in the first i elements,
    depending on if the current nums[i] is more helpful when attached to previous subarray.

    time complexity: O(n), less operations than first solution, and less space then third solution
    space complexity: O(1)
    */
    public int maxSubArray_improved_2() {
        if (nums == null || nums.length == 0) return 0;

        int currSum = nums[0], maxSum = nums[0];
        for (int i = 1; i < nums.length; i++) {
            currSum = Math.max(currSum + nums[i], nums[i]);
            maxSum = Math.max(currSum, maxSum);
        }

        return maxSum;
    }

    /*
    solution:
    Dynamic Programming: 每个index都存当前的最大sum

    time complexity: O(n) less operations compared to first solution
    space complexity: O(n)
    */
    public int maxSubArray_improved_2(int[] nums) {
        if (nums == null || nums.length == 0) return 0;

        int dp[] = new int[nums.length];
        dp[0] = nums[0];
        int maxSum = dp[0];
        for (int i = 1; i < nums.length; i++) {
            dp[i] = nums[i] + (dp[i-1] > 0 ? dp[i-1] : 0);
            maxSum = Math.max(maxSum, dp[i]);
        }

        return maxSum;
    }
}
