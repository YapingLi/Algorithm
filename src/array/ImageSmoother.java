/*
test case:
1. common case: [[1,1,1], [1,0,1], [1,1,1]]
2. edge case: null, empty
3. large amount case: NA
*/
public class ImageSmoother {
    /*
    solution:
    two-layer loop to calculate the value for each square

    time complexity: O(m*n)
    space complexity: O(m*n)

    history: bug free
    */
    public int[][] imageSmoother(int[][] M) {
        if (M == null || M.length == 0) return M;

        int xSize = M.length, ySize = M[0].length;
        int[][] res = new int[xSize][ySize];
        for (int x = 0; x < xSize; x++) {
            for (int y = 0; y < ySize; y++) {
                res[x][y] = smooth(M, x, y);
            }
        }
        return res;
    }

    public int smooth(int[][] M, int x, int y) {
        int sum = M[x][y], count = 1;
        //top
        if (isInImage(M, x-1, y)) {
            sum += M[x-1][y];
            count++;
        }
        //down
        if (isInImage(M, x+1, y)) {
            sum += M[x+1][y];
            count++;
        }
        //left
        if (isInImage(M, x, y-1)) {
            sum += M[x][y-1];
            count++;
        }
        //right
        if (isInImage(M, x, y+1)) {
            sum += M[x][y+1];
            count++;
        }
        //top-left
        if (isInImage(M, x-1, y-1)) {
            sum += M[x-1][y-1];
            count++;
        }
        //top-right
        if (isInImage(M, x-1, y+1)) {
            sum += M[x-1][y+1];
            count++;
        }
        //down-left
        if (isInImage(M, x+1, y-1)) {
            sum += M[x+1][y-1];
            count++;
        }
        //down-right
        if (isInImage(M, x+1, y+1)) {
            sum += M[x+1][y+1];
            count++;
        }

        return (int)sum / count;
    }

    public boolean isInImage(int[][] M, int x, int y) {
        return x >= 0 && x < M.length && y >= 0 && y < M[0].length;
    }
}
