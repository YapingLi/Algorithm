/*
test case:
1. common case: [1,2,3]
2. edge case: [0], [9], [9,9]
*/
public class PlusOne {
    /*
    solution:
    1. start from the last digit to get the carry
    2. loop from end to beginning and update carry and digit
    3. when loop ends, check if it exceeds the digits bound
    4. return result

    time complexity: O(n)
    space complexity: O(n) or O(1)

    history:
    1. forgot to update carry if carry becomes zero, which causes a wrong answer: Input: [8,9,9,9] => Output: [1,9,0,0,0]
    */
    public int[] plusOne(int[] digits) {
        int len = digits.length, carry = 0, sum = 0;
        for (int i = len - 1; i >= 0; i--) {
            if (i == len - 1) {
                sum = digits[len - 1] + 1;
            } else {
                sum = digits[i] + carry;
            }
            if (sum / 10 < 1) {
                digits[i] = sum;
                carry = 0;
                break;
            } else {
                carry = sum / 10;
                digits[i] = sum % 10;
            }
        }

        if (carry > 0) {
            int[] res = new int[len+1];
            res[0] = carry;
            for (int i = 1; i < len + 1; i++) {
                res[i] = digits[i-1];
            }
            return res;
        }

        return digits;
    }
}
