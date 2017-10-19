/*
test case:
1. common case: [3,3,2,3], 3 => return length = 2; [0,0,0,8,7,4],4 => return length = 5
2. edge case: [0],0 => return length = 0; [0],-1 => return length = 1; [0,0],-3 => return 2;
*/
public class RemoveElement {
    /*
    solution:
    two pointers: one from the beginning the other from the end.
    the end pointer track the non-target index, used for swap
    the beginning pointer go thru the array to find the target.

    time complexity: O(n)
    space complexity: O(1)
    */
    public int removeElement(int[] nums, int val) {
        if (nums == null || nums.length == 0) return 0;

        int start = 0, end = nums.length - 1;
        while (start <= end) { //within array bound
            if (nums[end] == val) {
                end--;

            } else {
                if (nums[start] != val) {
                    start++;
                } else {
                    nums[start] = nums[end];
                    end--;
                    start++;
                }
            }
        }

        return end + 1;
    }

    /*
    solution:
    two pointers with less comparisons, no need to swap because we don't care about what left beyong the new length

    time complexity: O(n): no need to swap => less operations; less comparisona too.
    space complexity: O(1)
    */
    public int removeElement_improved_1(int[] nums, int val) {
        if (nums == null || nums.length == 0) return 0;
        int start = 0, end = nums.length - 1;
        while (start <= end) {
            if (nums[start] == val) {
                nums[start] = nums[end];
                end--;
            } else {
                start++;
            }
        }
        return end + 1;
    }
}
