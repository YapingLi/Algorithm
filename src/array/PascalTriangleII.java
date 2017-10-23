public class PascalTriangleII {
    /*
    solution:
    1. initialize first row
    2. get the second row based on the previous row

    time complexity: O(k^2)
    space complexity: O(k)
    */
    public List<Integer> getRow(int rowIndex) {
        List<Integer> res = new ArrayList<>();
        if (rowIndex < 0) return res;

        res.add(1);
        int currRow = 1;

        while (currRow <= rowIndex) {
            res = generateRow(res);
            currRow += 1;
        }

        return res;
    }

    public List<Integer> generateRow(List<Integer> prev) {
        List<Integer> res = new ArrayList<>();
        int prevSize = prev.size();

        for (int i = 0; i <= prevSize; i++) {
            if (i - 1 >= 0 && i < prevSize) {
                res.add(prev.get(i-1) + prev.get(i));
            } else if (i - 1 < 0) {
                res.add(prev.get(i));
            } else {
                res.add(prev.get(i-1));
            }
        }

        return res;
    }

    /*
    solution:
    1. for each row, keep the first index and the last index to be 1.
    2. in place update the value between the first and the last.

    time complexity: O(k^2), less operation than above solution
    space complexity: O(k), less space than above solution
    */
    public List<Integer> getRow_improved_1(int rowIndex) {
        Integer[] row = new Integer[rowIndex + 1];
        row[0] = 1;
        for (int i = 1; i <= rowIndex; i++) {
            row[i] = 1;
            for (int j = i - 1; j >= 1; j--) {
                row[j] += row[j-1];
            }
        }
        return Arrays.asList(row);
    }
}
