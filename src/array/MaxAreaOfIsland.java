public class MaxAreaOfIsland {

/*
Time Complexity: O(m*n), each 0 is visited once, most 1 is visited twice
Space Complexity: O(m*n)

Solution: Breadth first search via Queue structure

Bug history:
1. create a new array with initialized values: queue.add(new int[2]{x, y});

Note:
1. queue.add({x, y}); => it rises error: illegal start of expression
2. queue.add(new int[2]{x, y}); => it rises error: ')' expected
3. queue.add(new int[]{x, y}); => correct
*/
    public int maxAreaOfIsland(int[][] grids) {
        if (grids == null || grids.length == 0) return 0;

        int currMaxArea = 0, xSize = grids.length, ySize = grids[0].length;
        for (int x = 0; x < xSize; x++) {
            for (int y = 0; y < ySize; y++) {
                if (grids[x][y] == 1) {
                    currMaxArea = Math.max(currMaxArea, getCurrArea(grids, x, y));
                }
            }
        }

        return currMaxArea;
    }

    public int getCurrArea(int[][] grids, int x, int y) {
        Deque<int[]> queue = new LinkedList<>();
        queue.add(new int[]{x, y});
        grids[x][y] = 0;
        int currArea = 0;

        while (!queue.isEmpty()) {
            int[] curr = queue.pop();
            currArea++;
            int currX = curr[0], currY = curr[1];

            //up
            if (isInGrids(grids, currX-1, currY) && isIsland(grids, currX-1, currY)) {
                queue.add(new int[]{currX-1, currY});
                grids[currX-1][currY] = 0;
            }

            //down
            if (isInGrids(grids, currX+1, currY) && isIsland(grids, currX+1, currY)) {
                queue.add(new int[]{currX+1, currY});
                grids[currX+1][currY] = 0;
            }

            //left
            if (isInGrids(grids, currX, currY-1) && isIsland(grids, currX, currY-1)) {
                queue.add(new int[]{currX, currY-1});
                grids[currX][currY-1] = 0;
            }

            //right
            if (isInGrids(grids, currX, currY+1) && isIsland(grids, currX, currY+1)) {
                queue.add(new int[]{currX, currY+1});
                grids[currX][currY+1] = 0;
            }
        }

        return currArea;
    }

    public boolean isInGrids(int[][] grids, int x, int y) {
        return x >= 0 && x < grids.length && y >= 0 && y < grids[0].length;
    }

    public boolean isIsland(int[][] grids, int x, int y) {
        return grids[x][y] == 1;
    }


    /*
    Time Complexity: O(m*n), each 0 is visited once, most 1 is visited twice
    Space Complexity: O(1)

    Solution: Depth first search
    */
    public int maxAreaOfIsland_improved(int[][] grids) {
        if (grids == null || grids.length == 0) return 0;

        int currMaxArea = 0, xSize = grids.length, ySize = grids[0].length;
        for (int x = 0; x < xSize; x++) {
            for (int y = 0; y < ySize; y++) {
                if (grids[x][y] == 1) {
                    currMaxArea = Math.max(currMaxArea, getCurrArea(grids, x, y));
                }
            }
        }

        return currMaxArea;
    }

    public int getCurrArea(int[][] grid, int x, int y) {
        if (!isInGrids(grids, x, y)) return 0;
        if (isInGrids(grids, x, y) && !isIsland(grids, x, y)) return 0;

        grid[x][y] = 0 ;

        /*
        recursion style 1: running time the same as first solution
        */
        int upArea = 1 + getCurrArea(grids, x-1, y);//up
        int downArea = 1 + getCurrArea(grids, x+1, y);//down
        int leftArea = 1 + getCurrArea(grids, x, y-1);//left
        int rightArea = 1+ getCurrArea(grids, x, y+1);//right

        return upArea + downArea + leftArea + rightArea - 3;

        /*
        improved recursion: running time reduce 10ms compared with above recursion
        */
        return 1 + getCurrArea(grids, x-1, y) + getCurrArea(grids, x+1, y) + getCurrArea(grids, x, y-1) + getCurrArea(grids, x, y+1);
    }

    public boolean isInGrids(int[][] grids, int x, int y) {
        return x >= 0 && x < grids.length && y >= 0 && y < grids[0].length;
    }

    public boolean isIsland(int[][] grids, int x, int y) {
        return grids[x][y] == 1;
    }
}
