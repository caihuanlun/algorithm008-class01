/*
 * @lc app=leetcode.cn id=231 lang=java
 *
 * [231] 2的幂
 */

// @lc code=start
class Solution {
    public boolean isPowerOfTwo(int n) {
        // /**
        //  * 1.数学方法：O(long n)
        //  */
        // if (n <= 0) return false;

        // while (n % 2 == 0) {
        //     n /= 2;
        // }
        // return n == 1;

        /**
         * 2.Bitwise（n & (n - 1)）:相当于清除最后一位1，判断最后是否为0
         */
        return n > 0 && (n & (n - 1)) == 0;
    }
}
// @lc code=end

