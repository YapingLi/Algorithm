public class MajorityElement {
    /*
    time complexity: O(n log(n))
    space complexity: O(1)

    Note:
    This algorithm offers O(n log(n)) performance on many data sets that cause other
    quicksorts to degrade to quadratic performance, and is typically faster than
    traditional (one-pivot) Quicksort implementations.
    */
    public int majorityElement(int[] nums) {
        Arrays.sort(nums);
        return nums[(nums.length - 1) / 2];
    }

    /*
    solution: use HashMap to count for each integer in the input array

    time complexity: O(n)
    space complexity: O(n)

    History:
    1. a bug: for (int i = 1; i < nums.length; i++) {...}
    2. return maxCount;
    */
    public int majorityElement_improved_1(int[] nums) {
        Map<Integer, Integer> map = new HashMap<>();

        for (int i = 0; i < nums.length; i++) {
            map.put(nums[i], map.getOrDefault(nums[i], 0) + 1);
        }

        int maxCount = 0, majorityElement = -1;
        for (Integer key : map.keySet()) {
            if (map.get(key) > maxCount) {
                maxCount = map.get(key);
                majorityElement = key;
            }
        }

        return majorityElement;
    }

    /*
    solution: 因为majority肯定存在，所以最后留下来的肯定是majority。
    update majority element when count < 0.

    time complexity: O(n)
    space complexity: O(1)

    history: bug free
    */
    public int majorityElement_improved_2(int[] nums) {
        int count = 1, majorityElement = nums[0];
        for (int i = 1; i < nums.length; i++) {
            if (nums[i] != majorityElement) {
                count--;
                if (count < 0) {
                    count = 1;
                    majorityElement = nums[i];
                }
            } else {
                count++;
            }
        }
        return majorityElement;
    }

    /*
    solution: strictly follows moore's voting algorithm. 如果majority存在，则return majority；如果不存在，则return null。
    step 1: find a cnadidate for majority element
    step 2: check if this candiate is a majority element

    time complexity: O(n)
    space complexity: O(1)
    */
    public int majorityElement_improved_3(int[] nums) {
        //step 1: find a candidate
        Integer candidate = null, count = 0;
        for (int i = 0; i < nums.length; i++) {
            if (count == 0) {//initialize candidate
                count = 1;
                candidate = nums[i];
            } else {
                if (nums[i] == candidate) {
                    count++;
                } else {
                    count--;
                }
            }
        }

        //step 2: check if the candidate is a majority
        count = 0;
        for (int i = 0; i < nums.length; i++) {
            if (nums[i] == candidate) {
                count++;
            }
        }
        return count > nums.length / 2 ? candidate : null;
    }
}
