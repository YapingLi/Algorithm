/*
test case:
1. common case: [3,4,5], [-5, -4, -2, -1, 4], [-5, -2, -1, -1], [-10, -5, 2, 3]
2. edge case: [-1, 0, 2, 3], [-5, -4, -3, 0]
3. large amount case: NA

Note:
public static void sort(Object[] a)
Sorts the specified array of objects into ascending order, according to the natural ordering of its elements.
All elements in the array must implement the Comparable interface. Furthermore, all elements in the array must
be mutually comparable (that is, e1.compareTo(e2) must not throw a ClassCastException for any elements e1 and
e2 in the array).
This sort is guaranteed to be stable: equal elements will not be reordered as a result of the sort.

Implementation note: This implementation is a stable, adaptive, iterative mergesort that requires far fewer than
n lg(n) comparisons when the input array is partially sorted, while offering the performance of a traditional
mergesort when the input array is randomly ordered. If the input array is nearly sorted, the implementation
requires approximately n comparisons. Temporary storage requirements vary from a small constant for nearly
sorted input arrays to n/2 object references for randomly ordered input arrays.

The implementation takes equal advantage of ascending and descending order in its input array, and can take advantage
of ascending and descending order in different parts of the the same input array. It is well-suited to merging two or
more sorted arrays: simply concatenate the arrays and sort the resulting array.

The implementation was adapted from Tim Peters's list sort for Python ( TimSort). It uses techniques from Peter
McIlroy's "Optimistic Sorting and Information Theoretic Complexity", in Proceedings of the Fourth Annual ACM-SIAM
Symposium on Discrete Algorithms, pp 467-474, January 1993.
*/

public class MaxProductOfThreeNumbers {
    /*
    solution:
    sort the array and then get all possible combinations

    time complexity: O(nlogn)
    space complexity: O(logn)
    */
    public int maximumProduct(int[] nums) {
        Arrays.sort(nums);
        int size = nums.length;
        //three positives OR three negatives
        int p1 = nums[size-1] * nums[size-2] * nums[size-3];
        //two positives OR one positive
        int p2 = nums[size-1] * nums[0] * nums[1];

        return Math.max(p1, p2);
    }

    /*
    solution:
    Based on above solution, we can iterate the array once only to find the largest three numbers and two
    smallest numbers to get the result.

    note:
    iterate an unsorted integer array is able to find the certain largest/smallest number.
    System.out.println(Integer.MIN_VALUE * Integer.MIN_VALUE * Integer.MAX_VALUE); => 0
    System.out.println(Integer.MIN_VALUE * Integer.MAX_VALUE * Integer.MAX_VALUE); => -2147483648
    System.out.println(Integer.MIN_VALUE * Integer.MIN_VALUE * Integer.MIN_VALUE); => 0

    time complexity: O(n)
    space complexity: O(1)

    history:
    1. [1,2,3] => 27
    2. [-1,-2,-3] => -1: https://leetcode.com/submissions/detail/123718493/
    */
    public int maximumProduct_improved_1(int[] nums) {
        int max1 = Integer.MIN_VALUE, max2 = Integer.MIN_VALUE, max3 = Integer.MIN_VALUE,
        min1 = Integer.MAX_VALUE, min2 = Integer.MAX_VALUE;

        for (int i = 0; i < nums.length; i++) {
            if (nums[i] < min2) {
                if (nums[i] < min1) {
                    min2 = min1;
                    min1 = nums[i];
                } else {
                    min2 = nums[i];
                }
            }

            if (nums[i] > max3) {
                if (nums[i] > max1) {
                    max3 = max2;
                    max2 = max1;
                    max1 = nums[i];
                } else if (nums[i] > max2) {
                    max3 = max2;
                    max2 = nums[i];
                } else {
                    max3 = nums[i];
                }
            }
        }

        return Math.max(max3 * max2 * max1, max3 * min1 * min2);
    }
}
