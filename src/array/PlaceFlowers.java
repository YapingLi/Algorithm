public class PlaceFlowers {
    /*
    time complexity: O(n)
    space complexity: O(1)

    history:
    1. wrong answer for test case: [0], 1
    2. wrong answer for test case: [0,0,0,0,0,1,0,0], 0 (if n == 0 no need to go thru for-loop)
    */
    public boolean canPlaceFlowers(int[] flowerbed, int n) {
        int len = flowerbed.length;

        if ((len % 2 == 0 && len / 2 < n) || (len % 2 != 0 && len / 2 + 1 < n)) return false;
        if (n == 0) return true;

        for (int i = 0; i < len; i++) {
            if (flowerbed[i] == 0) {
                if ((i - 1 < 0 && i + 1 == len) || (i ==0 && i + 1 < len && flowerbed[i+1] == 0) || (i == len - 1 && i - 1 >= 0 && flowerbed[i-1] == 0)
                    || (i - 1 >= 0 && flowerbed[i-1] == 0 && i + 1 < len && flowerbed[i+1] == 0)) {
                    n -= 1;
                    i += 1;
                    if (n == 0) break;
                }
            }
        }
        return n == 0;
    }
}
