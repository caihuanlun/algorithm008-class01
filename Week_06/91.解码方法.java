/*
 * @lc app=leetcode.cn id=91 lang=java
 *
 * [91] 解码方法
 */

// @lc code=start
class Solution {
    public int numDecodings(String s) {
        /**
         * 方法1：DP
         * a.分解子问题
         * b.状态定义
         * c.DP方程
         * 
         * dp[i] : 前一位或者前两位加上自身 dp[i - 1] 或者 dp[i - 2]
         * 个位数：dp[i - 1] 十位数：dp[i - 2]
         * dp[i] = dp[i] + dp[i -1]或者dp[i] + dp[i - 2]
         */
        if (s == null || s.length() == 0) return 0;

        int length = s.length();
        int[] dp = new int[length + 1];
        dp[0] = 1;
        dp[1] = s.charAt(0) != '0' ? 1 : 0;//注意首起：10的情况
        for (int i = 2; i <= length; i++) {
            int first = Integer.valueOf(s.substring(i - 1, i));
            int second = Integer.valueOf(s.substring(i - 2, i));
            if (first >= 1 && first <= 9)
                dp[i] = dp[i] + dp[i - 1];
            if (second >= 10 && second <= 26)
                dp[i] = dp[i] + dp[i - 2];
        }
        return dp[length];
    }
}
// @lc code=end

