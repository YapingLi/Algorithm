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


    /*
    solution: compared to above solution, it only has less code but longer running time

    time complexity: O(n)
    space complexity: O(1)
    */
    public int searchInsert_improved_1(int[] nums, int target) {
        if (nums == null || nums.length == 0) return 0;
        for (int i = 0; i < nums.length; i++) {
            if (nums[i] >= target) {
                return i;
            }
        }
        return nums.length;
    }

    /*
    solution: binary search with minor chagne on the sorted array.
    when nums[mid] < target, it tells that the target index is definitely on the right side of mid
    when nums[mid] >= target, it tells that the target index is definitely on the left side of mid or it's mid itself

    time complexity: O(n)
    space complexity: O(1)
    */
    public int searchInsert_improved_2(int[] nums, int target) {
        if (nums == null || nums.length == 0) return 0;
        int low = 0, high = nums.length - 1;
        while (low <= high) {
            int mid = (high - low) / 2 + low;
            if (nums[mid] < target) {
                low = mid + 1;
            } else {
                high = mid - 1;
            }
        }
        return low;
    }
}
