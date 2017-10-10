/*
description: https://leetcode.com/problems/array-partition-i/description/
algorithm: greedy
solution:
    1. sort the array
    2. add the first, third, fifth, ect.. together
    3. return the sum
*/
public class ArrayPartitionI {
    public int arrayPairSum(int[] nums) {
        Arrays.sort(nums);

        int sum = 0;
        for (int i = 0; i < nums.length; i+=2) {
            sum += nums[i];
        }
        return sum;
    }
}
