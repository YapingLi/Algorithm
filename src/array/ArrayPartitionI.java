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

/*
延伸：
1. 找到两两配对中较大的值的和的最小值？
solution: 排序后把挨着的两两配对就可以尽多的使用小的数
i.e. [1,3,4,5,6,7] =》[1,3],[4,5],[6,7] => sum = 15
2. 找到两两配对中较小值的和的最小值？
solution: 排序后直接把前n个数加起来即可。 i.e. [1,5],[3,6],[4,7]
3. 找到两两配对中的较大值的最大值？
solution: 排序后直接把后n个数加起来即可。 i.e. [1,5],[3,6],[4,7]
*/
