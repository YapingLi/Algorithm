/*
test case:
1. common case: [1,2,2,3,1], [1,2,2,3,1,4,2]
2. edge case: [0],
3. large amount case: NA
*/
public class DegreeOfArray {
    /*
    solution:
    First, use HashMap to count the frequency of each element so the degree of the array and degree elements can be findMaxConsecutiveOnes_improved_1
    Second, use two pointers to find the subarray length of  the degree element

    time complexity: O(n)
    space complexity: O(n)
    */
    public int findShortestSubArray(int[] nums) {
        Map<Integer, Integer> map = new HashMap<>();

        int degree = Integer.MIN_VALUE;
        List<Integer> degreeElements = new ArrayList<>();
        for (int i = 0; i < nums.length; i++) {
            map.put(nums[i], map.getOrDefault(map.get(nums[i]), 0) + 1);
            if (map.get(nums[i]) == degree) {
                degreeElements.add(nums[i]);
            } else if (map.get(nums[i]) > degree) {
                degree = map.get(nums[i]);
                degreeElements.clear();
                degreeElements.add(nums[i]);
            }
        }

        int res = nums.length;
        for (Integer element : degreeElements) {
            System.out.println("element: " + element);
            int start = 0, end = nums.length - 1;
            while (start <= end) {
                if (nums[start] == element && nums[start] == nums[end]) {
                    break;
                } else if (nums[start] != element && nums[end] != element) {
                    start++;
                    end--;
                } else if (nums[start] == element && nums[end] != element) {
                    end--;
                } else if (nums[end] == element && nums[start] != element) {
                    start++;
                }
            }
            res = Math.min(res, end - start + 1);
        }

        return res; 
    }

}
