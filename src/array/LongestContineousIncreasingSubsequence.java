/*
test case:
1. common case: [1, 3, 5, 4, 7] => 3, [1, 3, 5, 4, 7, 9, 10]
2. edge case: null, empty, [2,2,2] => 1, [2]
3. large amount case: NA
*/
public class LongestContineousIncreasingSubsequence {

    /*
    solution: one for loop to track the max length and the current length.

    time complexity: O(n)
    space complexity: O(1)
    */
    public int findLengthOfLCIS(int[] nums) {
        if (nums == null || nums.length == 0) return 0;
        int maxLen = 1, currLen = 1;
        for (int i = 1; i < nums.length; i++) {
            if (nums[i] > nums[i-1]) currLen++;
            else currLen = 1;
            maxLen = Math.max(currLen, maxLen);
        }
        return maxLen;
    }
}
