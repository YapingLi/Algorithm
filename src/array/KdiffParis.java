/*
test case:
1. common case: [3,1,4,1,5],2 => 1; [1,2,3,4,5], 1 => 4
2. edge caes: [1,3,1,5,4],0 => 1; k = -1 => return 0
*/
public class KdiffParis {
    /*
    solution:
    traverse the array once to store the elemnt and its count in a HashMap
    traverse the HashMap to iterate the key and find if the corresponding pair exists to match the k-diff

    time complexity: O(n)
    space complexity: O(n)
    */
    public int findPairs(int[] nums, int k) {
        if (nums == null || nums.length < 2 || k < 0) return 0;
        Map<Integer, Integer> map = new HashMap<>();
        for (int i = 0; i < nums.length; i++) {
            map.put(nums[i], map.getOrDefault(nums[i], 0) + 1);
        }

        int res = 0;
        for (Integer key : map.keySet()) {
            if (k == 0) {
                if (map.get(key) >= 2) res++;
            } else {
                int target1 = k + key, target2 = key - k;
                if (map.get(target1) != null) {
                    res++;
                }
                if (map.get(target2) != null) {
                    res++;
                }
                map.put(key, null);
            }
        }
        return res;
    }

    /*
    solution:
    traverse the array once to store the element and its count in a HashMap
    traver the HashMap to iterate the key and find if the corresponding pari exists to match the k-diff (to avoid duplicates,
    use (key + k) to find pair.
    i.e. [1,3,5,7], k=2 =>
    for 1, pair = 1+k = 3,
    for 3, pair = 3+key = 5, and it won't count 1 as anothe pair;
    for 5, pair = 5+key = 7, and it won't count 3 as anothe pair;
    for 7, pair = 7+key = 9, and it won't count 5 as anothe pair;
    therefore, it doesn't need to manipulate HashMap during interation and reduce conditions

    time complexity: O(n)
    space complexity: O(n)
    */
    public int findPairs_improved_1(int[] nums, int k) {
        if (nums == null || nums.length < 2 || k < 0) return 0;
        Map<Integer, Integer> map = new HashMap<>();
        for (int i = 0; i < nums.length; i++) {
            map.put(nums[i], map.getOrDefault(nums[i], 0) + 1);
        }
        int res = 0;
        for (Integer key : map) {
            if (k == 0) {
                if (map.get(key) >= 2) res++;
            }
        } else {
            if (map.get(key + k) != null) res++;
        }
        return res;
    }
}
