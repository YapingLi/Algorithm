public class MergeSortedArray
    /*
    time complexity: O(m + n)
    space complexity: O(1)
    */
    public void merge(int[] nums1, int m, int[] nums2, int n) {
        int i = m - 1, j = n - 1, k = 1;
        while (j >= 0) {
            if (i >= 0) {
                nums1[m+n-k] = nums1[i] >= nums2[j] ? nums1[i--] : nums2[j--];
            } else {
                nums1[m+n-k] = nums2[j--];
            }

            k += 1;
        }
    }

    /*
    solution: less code, no variable iniatialization,

    time complexity: O(m + n)
    space complexity: O(1)
    */
    public void merge_improved_1(int[] nums1, int m, int[] nums2, int n) {
        while (n-1 >= 0) {
            nums1[m+n-1] = m-1 >= 0 && nums1[m-1] >= nums2[n-1] ? nums1[--m] : nums2[--n];
        }
    }
}
