public class MissingNumber {

    /*
    solution: use a boolean array to track the appearance of each element

    time complexity: O(n) each element is visited twice
    space complexity: O(n)

    history:
    1. if (!boolean[i]) res = i; => error: ']' is expected
    */
    public int missingNumber(int[] nums) {
        if (nums == null || nums.length == 0) return 0;
        boolean[] appeared = new boolean[nums.length + 1];
        for (int i : nums) {
            appeared[i] = true;
        }
        int res = 0;
        for (int i = 0; i < appeared.length; i++) {
            if (!appeared[i]) {
                res = i;
                break;
            }
        }
        return res;
    }

    /*
    solution: firstly sort and then look for the missing element

    time complexity: O(nlogn)
    space complexity: implicitly sort() needs O(n) space
    */
    public int missingNumber_improved_1(int[] nums) {
        if (nums == null || nums.length == 0) return 0;
        Arrays.sort(nums);
        int res = -1;
        for (int i = 0; i < nums.length; i++) {
            if (nums[i] != i) {
                res = i;
                break;
            }
        }

        return res == -1 ? nums.length : res;
    }

    /*
    solution: in the for looop, calculate the expected and actual to get the missing number

    time complexity: O(n)
    space complexity: O(1)
    */
    public int missingNumber_improved_2(int[] nums) {
        if (nums == null || nums.length == 0) return 0;
        int sum = 0;
        for (int i = 0; i < nums.length; i++) {
            sum += (i+1) - nums[i];
        }

        /*
        stackoverflow can occur
        return (size * (size + 1) / 2) - sum;
        */

        return sum;
    }

    /*
    solution: use bit manipulation, 利用value和index相互抵消。 XOR（它符合交换律，而且 a^ b ^ b = a i.e. 0 ^ 8 = 8）
    i.e. (val0 ^ 0) ^ (val1 ^ 1) ^ (val2 ^ 2) ^... ^ (valn ^ n)
    if val2 is missing, which means all other vals exists, which means all other pairs result in 0, which means
    the final result would be 2. Therefore, the missing number is 2.

    time complexity: O(n)
    space complexity: O(1)
    */
    public int missingNumber_improved_3(int[] nums) {
        if (nums == null || nums.length == 0) return 0;
        int res = 0;
        for (int i = 0; i < nums.length; i++) {
            res ^= nums[i] ^ (i+1);
        }
        return res;

        //OR
        int res = 0;
        for (int i = 0; i < nums.length; i++) {
            res ^= nums[i] ^ (i);
        }
        return res ^ i;
    }

}
