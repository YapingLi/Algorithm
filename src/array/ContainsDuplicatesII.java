/*
1. common case: [1,2,3,2,2,6,1], 1 => return true;
*/
public class ContainsDuplicatesII {
    /*
    solution:
    build a HashMap to store the element and its index. If current element is already stored in the map,
    it means the current element has appered before, and now it's the time to calculate the absolute distance. If
    distance is at most k, return true, otherwise, update the index of the current element to the current index and continue.

    time complexity: O(n)
    space complexity: O(n)
    */
    public boolean containsNearbyDuplicate(int[] nums, int k) {
        if (nums == null || nums.length < 2 || k < 0) return false;
        Map<Integer, Integer> map = new HashMap<>();
        for (int i = 0; i < nums.length; i++) {
            if (map.get(nums[i]) != null && i - map.get(nums[i]) <= k) return true;
            map.put(nums[i], i);
        }
        return false;
    }

    /*
    solution: sliding window. Using a Set to main K size of element. Within in K size Set, if set.add(nums[i]) == false, it means
    the element has appeared before, and they two are within K distance, so return true; otherwise, the Set will add the current
    element. Besides, using sliding window logic, when it moves right for a step, the Set need to remove the very first element.

    time complexity: O(n)
    space complexity: O(1), it always maintain the size of K.
    */
    public boolean containsNearbyDuplicate_improved_1(int[] nums, int k) {
        if (nums == null || nums.length < 2 || k < 0) return false;
        Set<Integer> set = new HashSet<>();
        for (int i = 0; i < nums.length; i++) {
            if (i > k) set.remove(nums[i - k - 1]);
            if (!set.add(nums[i])) return true;
        }
        return false;
    }
}
