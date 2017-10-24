/*
test case:
1. common case: [1,2,3,4]
2. edge case: null, empty, [1,2,2,2], [1,1,1,2]
*/
public class RemoveDuplicates {
    /*
    solution:
    it's a sorted array, use the new length as the indexo of next available non-duplicate element.

    time complexity: O(n)
    space complexity: O(1)
    */
    public int removeDuplicates(int[] nums) {
        if (nums == null || nums.length == 0) return 0;
        int res = 1;
        for (int i = 1; i < nums.length; i++) {
            if (nums[i] != nums[i-1]) {
                nums[res++] = nums[i];
            }
        }
        return res;
    }

    /*
    same time complexity and space complexity as above solution, but with less assign operation
    */
    public int removeDuplicates_improved_1(int[] nums) {
        if (nums == null || nums.length == 0) return 0;
        int res = 1;
        for (int i = 1; i < nums.length; i++) {
            if (nums[i] != nums[i-1]) {
                if (res != i) {
                    nums[res] = nums[i];
                }
                res++;
            }
        }
        return res;
    }
}
