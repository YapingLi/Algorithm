/*
test case:
1. common case: [1,3,5,6],5 => 2; [1,3,5,6],2 => 1;
2. edge case: null, empty, [1,3,5,6],0 => 0, [1,3,5,6],7 => 4
3. large amount case: NA
*/
public class SearchInsertPosition {
    /*
    solution: for loop the array to find the index

    time complexity: O(n)
    space complexity: O(1)
    */
    public int searchInsert(int[] nums, int target) {
        if (nums == null || nums.length == 0) return 0;

        if (target <= nums[0]) return 0;
        if (target > nums[nums.length-1]) return nums.length;

        int index = 0;
        for (int i = 0; i < nums.length; i++) {
            if (nums[i] == target) {
                index = i;
                break;
            } else if (target > nums[i] && target < nums[i+1]) {
                index = i + 1;
                break;
            }
        }

        return index;
    }
}
