/*
Blog: http://blog.csdn.net/yutianzuijin/article/details/53861485

Test case:
1. common case: [1,3,3,4] => return [2]
2. edge case: nums == null, nums == empty, [0]
3. large amount case: NA
*/

public class FindDisappearedNumbers {
    /*
    Solution: use boolean array to record the appearence of the integers

    Time Complexity: O(n) : exactly twice for each integer
    Space Complexity: O(n)

    History: first time bug free
    */
    public List<Integer> findDisappearedNumbers(int[] nums) {
        List<Integer> res = new ArrayList<>();

        if (nums == null || nums.length == 0) return res;

        int n = nums.length;
        boolean[] appearence = new boolean[n+1];

        for (int i = 0; i < n; i++) {
            if (!appearence[nums[i]]) appearence[nums[i]] = true;
        }

        for (int i = 1; i < n + 1; i++) {
            if (!appearence[i]) res.add(i);
        }

        return res;
    }


    /*
    Solution: 元素归位法.
    把n个元素交换到它应该在的位置。注意：将某个元素交换到正确位置可能会导致当前元素还不在正确位置，
    需要继续交换知道不能交换位置。
    Use first for loop to place the value to its corresponding index, and mark appeared value to negative.
    Use second for loop to add positive value(disappeared) to result

    Time Complexity: O(n), some integers are visitied more than twice
    Sapce Complexity: O(1), no extra space except the returened result

    note：
    for-while循环，复杂度是不是O(n2)呢？不是，复杂度还是O(n)，这个可以通过均摊分析来解释：如果满足交换条件，
    则每次都会使一个元素处在正确位置，因为总共有n个元素，所以至多需要n-1次交换（交换完n-1个元素，第n个元素自动满足）
    即可使所有的元素处在正确位置，也即while循环至多执行O(n)次，每次的平摊代价是O(1)。所以上述交换操作的复杂度为O(n)。
    */
    public List<Integer> findDisappearedNumbers_improved_1(int[] nums) {
        List<Integer> res = new ArrayList<>();

        if (nums == null || nums.length == 0) return res;

        for (int i = 0; i < nums.length; i++) {
            //to ascertain that all integers are placed into corresponding index
            while (nums[nums[i]-1] != nums[i]) {
                swap(nums, i, nums[i]-1);
            }
        }

        for (int i = 0; i < nums.length; i++) {
            if (nums[i] != i+1) res.add(i+1);
        }

        return res;
    }

    public void swap(int[] nums, int i, int matchedIndex) {
        int temp = nums[matchedIndex];
        nums[matchedIndex] = nums[i];
        nums[i] = temp;
    }


    /*
    Solution: 取负法。
    把当前元素对应的index位置的integer取负. 所以第二遍loop的时候，如果所在值为负，
    说明当前index对应的integer出现过，如果为正，说明未出现过。

    Time Complexity: O(n), exactly twice for each integer, faster improved_1 solution
    Space Complexity: O(1)
    */
    public List<Integer> findDisappearedNumbers_improved_2(int[] nums) {
        List<Integer> res = new ArrayList<>();
        if (nums == null || nums.length == 0) return res;

        for (int i = 0; i < nums.length; i++) {
            int matchedIndex = Math.abs(nums[i]) - 1;
            nums[matchedIndex] = -Math.abs(nums[matchedIndex]);
        }

        for (int i = 0; i < nums.length; i++) {
            if (nums[i] > 0) res.add(i+1);
        }

        return res;
    }
}
