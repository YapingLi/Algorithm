/*
test case:
1. common case: [1,2,3]
2. edge case: [1,2], [1,2,2]
*/
public class ThirdMaxNumber {
    /*
    solution:
    use max1, max2, max3 to track the three maximums in a traversal

    time complexity: O(n)
    space complexity: O(1)

    history:
    1. wrong answer: [2,2,3,1]
    2. wrong answer: [1,2,-2147483648]
    3. wrong answer: [1,2,2,5,3,5]
    4. wrong answer: [1, -2147483648, 2]

    note:
    Because once you initializing them with Integer.MIN_VALUE, you have to add more logic to figure out the value
    from the initialization or from the array.

    If i were the interviewer I would ask "what is not ideal about this solution?"
    And the answer I expect would be that it's not easily modifiable, for example if you want to find 7th maximum instead of 3rd.
    I like the answers using dynamic data structures more because of that reason. (I used a linked list)
    */
    public int thirdMax(int[] nums) { //not working, since not able to identify if min3 comes from initialization or nums
        int max1 = Integer.MIN_VALUE, max2 = Integer.MIN_VALUE, max3 = Integer.MIN_VALUE;
        boolean isMax3Updated = false;
        for (int i = 0; i < nums.length; i++) {
            if (nums[i] >= max3) {
                if (nums[i] >= max1) {
                    if (nums[i] == max1) continue;
                    max3 = max2;
                    max2 = max1;
                    max1 = nums[i];
                } else if (nums[i] >= max2) {
                    if (nums[i] == max2) continue;
                    max3 = max2;
                    max2 = nums[i];
                } else {
                    if (nums[i] == max3) {
                        isMax3Updated = true;
                        continue;
                    }
                    max3 = nums[i];
                }
            }
        }
        return max3 != Integer.MIN_VALUE || isMax3Updated ? max3 : max1;
    }

    /*
    solution:
    use Integer array to initialize the array with scalability. it's easy to identify if the third max is found or not.

    time complexity: O(n)
    space complexity: O(1)

    history:
    1. java.lang.NullPointerException: [1, -2147483648, 2]
    2. java.lang.NullPointerException: [1,2]
    */
    public int thirdMax_improved_1(int[] nums) {
        Integer[] largest = new Integer[3];
        for (int i = 0; i < nums.length; i++) {
            //handle first time initialization
            if (largest[0] == null) {
                largest[0] = nums[i];
                continue;
            }
            if (largest[0] != null && nums[i] < largest[0] && largest[1] == null) {
                largest[1] = nums[i];
                continue;
            }
            if (largest[1] != null && nums[i] < largest[1] && largest[2] == null) {
                largest[2] = nums[i];
                continue;
            }

            //handle duplicates
            if (largest[0] == nums[i] || (largest[1] != null && largest[1] == nums[i]) || (largest[2] != null && largest[2] == nums[i])) continue;

            //handle updates
            if (nums[i] > largest[0]) {
                largest[2] = largest[1];
                largest[1] = largest[0];
                largest[0] = nums[i];
            } else if (nums[i] > largest[1]) {
                largest[2] = largest[1];
                largest[1] = nums[i];
            } else if (nums[i] > largest[2]) {
                largest[2] = nums[i];
            }
        }

        return largest[2] == null ? largest[0] : largest[2];
    }

    /*
    solution:
    similar to above solution. But use Integer num : nums to iterate so that it can use num.equals to handle duplicates
    */
    public int thirdMax_improved_2(int[] nums) {
        Integer[] largest = new Integer[3];
        for (Integer num : nums) {
            //handle first time initialization
            if (largest[0] == null) {
                largest[0] = num;
                continue;
            }
            if (largest[0] != null && num < largest[0] && largest[1] == null) {
                largest[1] = num;
                continue;
            }
            if (largest[1] != null && num < largest[1] && largest[2] == null) {
                largest[2] = num;
                continue;
            }

            //handle duplicates
            if (num.equals(largest[0]) || num.equals(largest[1]) || num.equals(largest[0])) continue;

            //handle updates
            if (num > largest[0]) {
                largest[2] = largest[1];
                largest[1] = largest[0];
                largest[0] = num;
            } else if (nums > largest[1]) {
                largest[2] = largest[1];
                largest[1] = num;
            } else if (num > largest[2]) {
                largest[2] = num;
            }
        }

        return largest[2] == null ? largest[0] : largest[2];
    }


    /*
    solution:
    use Arrays.sort() to sort the array first and then find the third maximum number.

    time complexity: O(nlogn)
    space complexity: O(n)
    */
    public int thirdMax_improved_2(int[] nums) {
        Arrays.sort(nums);
        Integer max1 = nums[nums.length-1], max2 = null, max3 = null;
        for (int i = nums.length-2; i >= 0; i--) {
            if (nums[i] == max1) continue;
            if (max2 == null) {
                max2 = nums[i];
                continue;
            } else if (nums[i] == max2) continue;
            if (max3 == null) {
                max3 = nums[i];
                break;
            }
        }
        return max3 == null ? max1 : max3;
    }
}
