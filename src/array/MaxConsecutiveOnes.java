/*
Test case:
1. common case: [0, 1, 1, 1, 0, 0, 1], [1, 1, 0, 1, 1]
2. edge case: nums == null, nums.length = 0, nums = [1], nums = [0, 1]
3. large amount: NA

Time Complexity: O(n)
Space Complexity: O(1)

bug history:
1. failed at nums = [1]
2, failed at nums = [0, 1], [0, 1, 1, 1]
*/
public class MaxConsecutiveOnes {
    public int findMaxConsecutiveOnes(int[] nums) {
        if (nums == null || nums.length == 0) return 0;

        int currMax = 0, currLen = 0;

        if (nums[0] == 1) currLen++;

        for (int i = 1; i < nums.length; i++) {
            currMax = Math.max(currMax, currLen);
            if (nums[i-1] == 0 && nums[i] == 1) {
                currLen = 1;
            }
            if (nums[i-1] == 1 && nums[i] == 1) {
                currLen++;
            }
        }

        return currMax;
    }

    public int findMaxConsecutiveOnes_improved_1(int[] nums) {
        if (nums == null || nums.length == 0) return 0;
        int currMax = 0, currLen = 0;;
        for (int i = 0; i < nums.length; i++) {
            if (nums[i] == 1) {
                currLen++;
            } else {
                currLen = 0;
            }
            currMax = Math.max(currMax, currLen);
        }

        return currMax;
    ｝

    /*
    Solution: reset sum to 0 when meet 0, otherwise increments sum and updates currMax
    Pros: less code because of no else-if conditions
    */
    public int findMaxConsecutiveOnes_improved_2(int[] nums) {
        if (nums == null || nums.length == 0) return 0;
        int currMax = 0, sum = 0;;
        for (int i = 0; i < nums.length; i++) {
            sum = (sum + nums[i]) * nums[i];
            currMax = Math.max(currMax, sum);
        }
        return currMax;
    }
}
