/*
 * @lc app=leetcode.cn id=191 lang=java
 *
 * [191] 位1的个数
 */

// @lc code=start
public class Solution {
    // you need to treat n as an unsigned value
    public int hammingWeight(int n) {
        /**
         * 1. for loop: 0 --> 32
         * 2. %2 , /2
         * 3. &1, x = x >> 1; (32)
         * 4. while (x > 0) {count++; x = x & (x - 1);}
         */
        // int bits = 0;
        // int mask = 1;
        // for (int i = 0; i < 32; i++) {
        //     if ((n & mask) != 0) {
        //         bits++;
        //     }
        //     mask <<= 1;
        // }
        // return bits;

        int sum = 0;
        while (n != 0) {
            sum++;
            n &= (n - 1);
        }
        return sum;
    }
}
// @lc code=end

