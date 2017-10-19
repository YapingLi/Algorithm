import java.util.HashMap;
import java.util.Map;
/*
test case:
1. common case: [1,2,2,3,1], [1,2,2,3,1,4,2]
2. edge case: [0],
3. large amount case: NA

Note:
import语句必须在java file的最最上面
*/

public class DegreeOfArray {
    /*
    solution:
    Using HashMap to track three maps:
    1. 数和它出现的次数； 2. 数和它第一次出现的index； 3. 数和它最后一次出现的index；
    所以当计算出degree后，rightIndex - leftIndex + 1 就是它所对应的length

    time complexity: O(n)
    space complexity: O(n)

    history:
    1. count.put(key, count.getOrDefault(count.get(key), 0) + 1); it should be count.put(key, count.getOrDefault(key, 0) + 1);
    */
    public static int findShortestSubArray(int[] nums) {
        Map<Integer, Integer> count = new HashMap<>();
        Map<Integer, Integer> left = new HashMap<>();
        Map<Integer, Integer> right = new HashMap<>();

        int degree = Integer.MIN_VALUE;
        for (int i = 0; i < nums.length; i++) {
            int key = nums[i];
            if (left.get(key) == null) {
                left.put(key, i);
            }
            right.put(key, i);
            count.put(key, count.getOrDefault(key, 0) + 1);
            degree = Math.max(degree, count.get(key));
        }

        int res = Integer.MAX_VALUE;
        for (Integer key : count.keySet()) {
            if (count.get(key) == degree) {
                res = Math.min(res, right.get(key) - left.get(key) + 1);
            }
        }

        return res;
    }
}
