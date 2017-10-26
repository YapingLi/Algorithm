/*
test case:
1. common case: [1,2,3]
2. edge case: [1,2], [1,2,2]
*/
public class ThirdMaxNumber {
    /*
    solution:
    use max1, max2, max3 to track the three maximums in a traversal

    time complexity: O(n)
    space complexity: O(1)

    history:
    1. wrong answer: [2,2,3,1]
    2. wrong answer: [1,2,-2147483648]
    3. wrong answer: [1,2,2,5,3,5]
    */
    public int thirdMax(int[] nums) {
        int max1 = Integer.MIN_VALUE, max2 = Integer.MIN_VALUE, max3 = Integer.MIN_VALUE;
        boolean isMax3Updated = false;
        for (int i = 0; i < nums.length; i++) {
            if (nums[i] >= max3) {
                if (nums[i] >= max1) {
                    if (nums[i] == max1) continue;
                    max3 = max2;
                    max2 = max1;
                    max1 = nums[i];
                } else if (nums[i] >= max2) {
                    if (nums[i] == max2) continue;
                    max3 = max2;
                    max2 = nums[i];
                } else {
                    max3 = nums[i];
                    isMax3Updated = true;
                }
            }
        }
        return isMax3Updated || max3 != Integer.MIN_VALUE ? max3 : max1;
    }
}
