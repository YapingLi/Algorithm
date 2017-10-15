/*
test case:
1. common case: [3,5,3,2,1] => true; [3,7,2,0] => false
2. edge case: null, empty
3. large amount case: NA
*/
public class ContainsDuplicates {
    /*
    solution: Set to record the appeared integer

    time complexity: O(n)
    space complexity: O(n)

    history: bug: (nums == null || nums.length = 0)

    Note:
    For certain test cases with not very large n, the runtime of this method
    can be slower than the next solution. The reason is hash table has some
    overhead in maintaining its property. One should keep in mind that real world
    performance can be different from what the Big-O notation says. The Big-O
    notation only tells us that for sufficiently large input, one will be faster
    than the other. Therefore, when nn is not sufficiently large, an O(n)
    algorithm can be slower than an O(n \log n) algorithm.
    */
    public boolean containsDuplicate(int[] nums) {
        if (nums == null || nums.length == 0) return false;

        Set<Integer> set = new HashSet<>();
        for (int i = 0; i < nums.length; i++) {
            if (set.contains(nums[i])) return true;
            set.add(nums[i]);
        }
        return false;
    }

    /*
    solution:
    sort first and then iterate. This is quicker than first solution.
    The implementation here modifies the original array by sorting it.
    In general, it is not a good practice to modify the input unless
    it is clear to the caller that the input will be modified.
    One may make a copy of nums and operate on the copy instead.

    time complexity: O(nlogn)
    space complexity: O(1)
    */
    public boolean containsDuplicate_improved_1(int[] nums) {
        if (nums == null || nums.length == 0) return false;

        Arrays.sort(nums);
        for (int i = 1; i < nums.length; i++) {
            if (nums[i] == nums[i-1]) return true;
        }
        return false;
    }
}
