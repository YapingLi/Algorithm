public class ShortestUnsortedContinuousSubarray {
    /*
    solution: sort the copy of the input array and then compare the two arrays to find out the difference

    time complexity: O(nlogn) + O(n)
    space complexity: O(n)

    history:
    1. int[] copy = nums; => it makes both copy and nums point to the same object reference. so sorting copy is actually sorting
    nums => it causes the answer is always 0

    note:
    1. two methods to copy array.
        (1). int[] newArray = Arrays.copyOf(originalArray, lengthOfNewArray)
        (2). System.arraycopy(originalArray, 0, newArray, startCopyingIndexOfNewArray, lengthOfCopiedElements);
    */
    public int findUnsortedSubarray(int[] nums) {
        int[] copy = Arrays.copyOf(nums, nums.length);
        Arrays.sort(copy);
        int i = 0, j = nums.length - 1, res1 = -1, res2 = -1;

        while (i < nums.length) {
            if (nums[i] != copy[i]) {
                res1 = i;
                while (res1 != -1 && j >= 0) {
                    if (nums[j] != copy[j]) {
                        res2 = j;
                        break;
                    } else {
                        j--;
                    }
                }
                break;
            } else {
                i++;
            }
        }

        return res1 == -1 ? 0 : res2 - res1 + 1;
    }

    /*
    solution:
    using stack data structure. the stack needs the value and the index of the element.

    first time traverse from the beginning index of input array. If current elment is smaller than the top element in stack, pop the
    element from stack until the top element in stack is smaller than the current element. So when we complete traverse, we can find
    the minimum index (it's also the left index) that needs to be moved in order to make the array sorted.

    second time traverse from the ending index of input array. If current element is bigger than the top element in stack, pop the
    element from stack until the top element in stack is bigger than the current element. So when we complete traverse, we can find
    the maximum index (it's also the right index) that needs to be moved in order to make the array sorted.

    time complexity: O(n)
    space complexity: O(n)

    history:
    1. forgot to main minimum leftindex and maximum rightindex

    note:
    1. initiazlize int[] array with values, WRONG - new int[2]{1,2}; CORRECT - new int[]{1,2}
    2. stack.empty();  queue.isEmpty();
    */
    public int findUnsortedSubarray_improved_1(int[] nums) {
        int len = nums.length;
        int leftIndex = len - 1, rightIndex = 0;
        Stack<int[]> stack = new Stack<>();
        stack.push(new int[]{nums[0], 0});

        for (int i = 1; i < len; i++) {
            if (!stack.empty()) {
                int[] top = stack.peek();
                if (nums[i] >= top[0]) {
                    stack.push(new int[]{nums[i], i});
                } else {
                    stack.pop();
                    leftIndex = Math.min(top[1], leftIndex);
                    if (leftIndex == 0) break;
                    i -= 1;
                }
            }
        }

        if (leftIndex == len - 1) return 0;

        stack = new Stack<>();
        stack.push(new int[]{nums[len - 1], len-1});
        for (int i = len - 2; i >= 0; i--) {
            if (!stack.empty()) {
                int[] top = stack.peek();
                if (nums[i] <= top[0]) {
                    stack.push(new int[]{nums[i], i});
                } else {
                    stack.pop();
                    rightIndex = Math.max(top[1], rightIndex);
                    if (rightIndex == nums.length-1) break;
                    i += 1;
                }
            }
        }

        return rightIndex - leftIndex + 1;
    }

    /*
    solution:
    first traverse the input array to get the min element in all wrong places.
    second travers the input array to get the max element in all wrong places
    third traverse starts from index 0, to find the first index that its value is bigger than the min element
    forth traverse starts from the end, to find the first index that its value is smaller than the max element

    time complexity: O(n)
    space complexity: O(1)
    */
    public int findUnsortedSubarray_improved_2(int[] nums) {
        int minWrong = Integer.MAX_VALUE, maxWrong = Integer.MIN_VALUE, len = nums.length;
        int leftIndex = len - 1, rightIndex = 0;

        for (int i = 1; i < len; i++) {
            if (nums[i] < nums[i-1]) {
                minWrong = Math.min(minWrong, nums[i]);
            }
        }

        for (int i = len-2; i >= 0; i--) {
            if (nums[i] > nums[i+1]) {
                maxWrong = Math.max(maxWrong, nums[i]);
            }
        }

        for (int i = 0; i < len; i++) {
            if (nums[i] > minWrong) {
                leftIndex = i;
                break;
            }
        }

        if (leftIndex == len - 1) return 0;

        for (int i = len - 1; i >= 0; i--) {
            if (nums[i] < maxWrong) {
                rightIndex = i;
                break;
            }
        }

        return rightIndex - leftIndex + 1;
    }
}
