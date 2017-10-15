/*
Test case:
1. common case: [1, 2, 3, 4, 0, 0], [0, 0, 1, 0, 2]
2. edge case: nums == null, nums == empty, [1], [0]
3. large amount: NA
*/
public class MoveZeros {
    /*
    solution: use an outsider index to track the current potential zero

    Time Complexity: O(n), have redandent operations
    Space Complexity: O(1)

    History: first time bug free
    */
    public void moveZeros(int[] nums) {
        if (nums == null || nums.length == 0) return;

        int zeroIndex = 0;
        for (int i = 0; i < nums.length; i++) {
            if (nums[i] != 0) {
                swap(nums, i, zeroIndex);
                zeroIndex++;
            }
        }
    }

    public void swap(int[] nums, int i, int zeroIndex) {
        int temp = nums[zeroIndex];
        nums[zeroIndex] = nums[i];
        nums[i] = temp;
    }

    /*
    solution: minimal operations but need a swap method
    */
    public void moveZeros_improved_1(int[] nums) {
        if (nums == null || nums.length == 0) return;

        int zeroIndex = 0;
        for (int i = 0; i < nums.length; i++) {
            if (nums[i] != 0) {
                if (i != zeroIndex) {
                    swap(nums, i, zeroIndex);
                }
                zeroIndex++;
            }
        }
    }

    public void swap(int[] nums, int i, int zeroIndex) {
        int temp = nums[zeroIndex];
        nums[zeroIndex] = nums[i];
        nums[i] = temp;
    }

    /*
    solution: when meet a non-0, if current index is its best place, continue;
    if a previous index is its best, assign non-0 to its previous index, and set current index to value of 0

    Time Complexity: O(n): minimal operations without helper method
    Space Complexity: O(1)
    */
    public void moveZeros_improved_2(int[] nums) {
        if (nums == null || nums.length == 0) return;

        int zeroIndex = 0;
        for (int i = 0; i < nums.length; i++) {
            if (nums[i] != 0) {
                if (zeroIndex < i) {
                    nums[zeroIndex++] = nums[i];
                    nums[i] = 0;
                } else {
                    zeroIndex++;
                }
            }
        }
    }
}
