/*
Test case:
1. common case: [2,3,7,15] target=9
2. edge case: numbers==null, numbers==empty, numbers.length==1
3. large amount case: two integer sum > MAX_VALUE or < MIN_VALUE (NA)
*/

public class TwoSumII {
    /*
    solution: two pointers

    Time Complexity: O(n)
    Space Complexity: O(1)

    History: first time bug free
    */
    public int[] twoSum(int[] numbers, int target) {
        int[] res = new int[2];
        if (numbers == null || numbers.length < 2) return res;

        int start = 0, end = numbers.length - 1;
        while (start < end) {
            int sum = numbers[start] + numbers[end];
            if (sum == target) {
                res[0] = start + 1;
                res[1] = end + 1;
                break;
            } else if (sum < target) {
                start++;
            } else {
                end--;
            }
        }
        return res;

        /*
        nicer
        */
        while (start < end) {
            int sum = numbers[start] + numbers[end];
            if (sum == target) break;
            if (sum < target) start++;
            else end--;
        }
        return new int[2]{start, end}
}

    /*
    solution: use binary search to update end or start indexes.

    time Complexity: O(logn)
    space Complexity: O(1)
    */
    public int[] twoSum_improved_1(int[] numbers, int target) {
        int[] res = new int[2];
        if (numbers == null || numbers.length < 2) return res;

        int start = 0, end = numbers.length - 1;
        while (start < end) {
            int sum = numbers[start] + numbers[end];
            if (sum == target) {
                res[0] = start + 1;
                res[1] = end + 1;
                break;
            } else if (sum < target) {
                start = updateStartViaBinarySearch(numbers, start, end, target - numbers[end]);
            } else {
                end = updateEndViaBinarySearch(numbers, start, end, target - numbers[start]);
            }
        }

        return res;
    }

    //find the index with the value that is largest smaller than the target or equal to target
    public int updateEndViaBinarySearch(int[] numbers, int left, int right, int target) {
        while (left <= right) {
            int mid = (right - left) / 2 + left;
            if (numbers[mid] == target) {
                return mid;
            } else if (numbers[mid] < target) {
                left = mid + 1;
            } else {
                right = mid - 1;
            }
        }
        return right;
    }

    //find the index with the value that is smallest larger than the target or equal to target
    public int updateStartViaBinarySearch(int[] numbers, int left, int right, int target) {
        while (left <= right) {
            int mid = (right - left) / 2 + left;
            if (numbers[mid] == target) {
                return mid;
            } else if (numbers[mid] < target) {
                left = mid + 1;
            } else {
                right = mid - 1;
            }
        }
        return left;
    }
}
