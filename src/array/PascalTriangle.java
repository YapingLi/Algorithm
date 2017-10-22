public class PascalTriangle {

    /*
    solution:
    1. consider edge case: numRows < 1
    2. initialize the first row
    3. generate the next row based on previous row
    4. return result

    time complexity: O(n(n+1)/2)
    space complexity: O(n + n(n+1)/2)
    */
    public List<List<Integer>> generate(int numRows) {
        List<List<Integer>> res = new ArrayList<>();
        if (numRows < 1) return res;

        //initialize row1
        List<Integer> row1 = new ArrayList<>();
        row1.add(1);
        res.add(row1);

        //generate the next row
        int currRow = 2;
        while (currRow <= numRows) {
            generateCurrRow(res, currRow);
            currRow += 1;
        }
        return res;
    }

    public void generateCurrRow(List<List<Integer>> res, int currSize) {
        List<Integer> prev = res.get(res.size() - 1);
        List<Integer> curr = new ArrayList<>();
        for (int i = 0; i < currSize; i++) {
            if (i - 1 >= 0 && i < prev.size()) {
                curr.add(prev.get(i-1) + prev.get(i));
            } else if (i - 1 < 0) {
                curr.add(prev.get(i));
            } else {
                curr.add(prev.get(i-1));
            }
        }
        res.add(curr);
    }
}
