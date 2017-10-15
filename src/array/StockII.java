/*
Test case:
1. common case: [4,6,8,2,3,2,6], [3,2,1]
2. edge case: prices == null, prices == empty, [2]
3. large amount case: NA
*/
public class StockII {
    /*
    solution: greedy algorithm,
    buy when next day has higher price, sell when next day has lower price

    Time Complexity: O(n)
    Space Complexity: O(1)

    History:
    first time bug free
    */

    public int maxProfit(int[] prices) {
        if (prices == null || prices.length == 0) return 0;

        int profit = 0;
        for (int i = 1; i < prices.length; i++) {
            if (prices[i] > prices[i-1]) {
                profit += (prices[i] - prices[i-1]);
            }
        }

        return profit;
    }

    /*
    Same time and space complexity as above case but only less code
    */
    public int maxProfit_improved_1(int[] prices) {
        if (prices == null || prices.length == 0) return 0;

        int profit = 0;
        for (int i = 1; i < prices.length; i++) {
            profit += Math.max(prices[i] - prices[i-1], 0);
        }

        return profit;
    }
}
