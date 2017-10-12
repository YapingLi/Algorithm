/*
Test case:
1. common case: [[1,2],[3,4]] => [1,2,3,4]
2. edge case: nums == null, nums.isEmpty(), r < 0, c < 0, invalid r and c
3. large amout case: no StackOverFlow happens
*/
public class ReshapeTheMatrix {
    /*
    Time Complexity: O(m*n) + O(r*c): visit each number twice
    Space Complexity: O(m*n) + O(m*n)

    Solution:
    1. For loop the 2-dimension input array and store elements to 1-dimension array
    2. For loop the 2-dimension output array and assign elements based on 1-dimension array

    History:
    1. two bugs: (1). reshapedMarix = copy[i++]; (2). x < xSize, y < ySize in second loop
    */
    public int[][] matrixReshape(int[][] nums, int r, int c) {
        if (nums == null || r < 0 || c < 0 || nums.length == 0) return nums;

        int xSize = nums.length, ySize = nums[0].length;
        if (xSize * ySize != r * c) return nums;

        int[][] reshapedMatrix = new int[r][c];
        int[] copy = new int[xSize * ySize];

        int i = 0;
        for (int x = 0; x < xSize; x++) {
            for (int y = 0; y < ySize; y++) {
                copy[i++] = nums[x][y];
            }
        }

        i = 0;
        for (int x = 0; x < r; x++) {
            for (int y = 0; y < c; y++) {
                reshapedMatrix[x][y] = copy[i++];
            }
        }

        return reshapedMatrix;
    }


    /*
    Time Complexity: O(m*n): visit each number once
    Space Complexity: O(m*n)

    Solution:
    1. For loop once via using formala to transfer 2-dimensiona array to 1-dimension array and then using formala to transfer 1-dimension array to 2-dimension array

    formula:
    1. arr2[x][y] => arr1[x * ySize + y]
    2. arr1[i] => arr2[i / ySize, i % ySize]
    */
    public int[][] matrixReshape_improved(int[][] nums, int r, int c) {
        if (nums == null || r < 0 || c < 0 || nums.length == 0) return nums;

        int xSize = nums.length, ySize = nums[0].length;
        if (xSize * ySize != r * c) return nums;

        int[][] reshapedMatrix = new int[r][c];
        for (int x = 0; x < r; x++) {
            for (int y = 0; y < c; y++) {
                int i = x * c + y;
                int oriX = i / ySize, oriY = i % ySize;
                reshapedMatrix[x][y] = nums[oriX][oriY];
            }
        }

        return reshapedMatrix;
    }


    /*
    Time Complexity: O(m*n): visit each number once
    Space Complexity: O(m*n)

    Solution:
    1. For loop once via using formala to tranfer index between 2-dimension array and 1-dimension array
    formula:
    1. arr1[i] => arr2[i / ySize, i % ySize]
    */
    public int[][] matrixReshape_improved_2(int[][] nums, int r, int c) {
        if (nums == null || r < 0 || c < 0 || nums.length == 0) return nums;

        int xSize = nums.length, ySize = nums[0].length;
        if (xSize * ySize != r * c) return nums;

        int[][] reshapedMatrix = new int[r][c];
        for (int i = 0; i < r * c; i++) {
            reshapedMatrix[i/c][i%c] = nums[i/ySize][i%ySize];
        }

        return reshapedMatrix;
    }
}
