/*
test case:
1. common case: [2,15,7,11] => return [0, 2]
2. edge case: [2, .., 7] => return [0, nums.length - 1]; [2],4 => cannot return [0,0] [10, -1, -8, 2], 2 => return [0, 2]
*/

public class TwoSum {
    /*
    solution: brutal forc

    time complexity: O(n^2)
    space complexity: O(1)
    */
    public int[] twoSum(int[] nums, int target) {
        int[] res = new int[2];
        for (int i = 0; i < nums.length; i++) {
            for (int j = i + 1; j < nums.length; j++) {
                if (nums[i] + nums[j] == target) {
                    res[0] = i;
                    res[1] = j;
                }
            }
        }
        return res;
    }

    /*
    solution: use a HashMap to record the element and its index, and then use another for-loop to get the other index

    time complexity: O(n)
    space complexity: O(n)

    history:
    1. forgot the add 'break;' => get wrong result: [3,2,4],6 => [2,1], correct answer is [1,2]
    */
    public int[] twoSum_improved_1(int[] nums, int target) {
        int[] res = new int[2];
        Map<Integer, Integer> map = new HashMap<>();
        for (int i = 0; i < nums.length; i++) {
            map.put(nums[i], i);
        }
        for (int i = 0; i < nums.length; i++) {
            int other = target - nums[i];
            if (map.get(other) != null && map.get(other) != i) {
                res[0] = i;
                res[1] = map.get(other);
                break;
            }
        }
        return res;
    }

    /*
    solution: use a HashMap to record the element and its index, and find the target all in one for-loop
    
    time complexity: O(n), compared with above solution, it iterate thru the array only once
    space complexity: O(n)
    */
    public int[] twoSum_improved_2(int[] nums, int target) {
        int[] res = new int[2];
        Map<Integer, Integer> map = new HashMap<>();
        for (int i = 0; i < nums.length; i++) {
            if (map.get(target - nums[i]) != null) {
                res[0] = map.get(target - nums[i]);
                res[1] = i;
                break;
            }
            map.put(nums[i], i);
        }
        return res;
    }
}
