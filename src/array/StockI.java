/*
test case:
1. common case: [7,1,5,3,6,4] => 5, [7,6,4,3,2,1] => 0
2. edge case: null, empty, [1] => 0, [1,1] => 0
3. large amount case: NA
*/
public class StockI {
    /*
    solution: maintain two variables: 1. smallest valley, 2. max profit

    time complexity: O(n)
    space complexity: O(1)

    */
    public int maxProfit(int[] prices) {
        if (prices == null || prices.length == 0) return 0;

        int minPrice = Integer.MAX_VALUE, maxProfit = 0;
        for (int i = 0; i < prices.length; i++) {
            if (prices[i] < minPrice) {
                minPrice = prices[i];
            } else { //it promises the prices[i] is always after minPrice
                maxProfit = Math.max(maxProfit, prices[i] - minPrice);
            }
        }
        return maxProfit;
    }
}
