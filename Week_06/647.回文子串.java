/*
 * @lc app=leetcode.cn id=647 lang=java
 *
 * [647] 回文子串
 */

// @lc code=start
class Solution {
    public int countSubstrings(String s) {
            /**
         * 方法1：DP
         * a.分解子问题
         * b.状态定义
         * c.DP方程
         * 
         * 如果s[i] == s[j]s[i]==s[j]那么说明只要dp[i+1][j-1]dp[i+1][j−1]是回文子串，那么是dp[i][j]dp[i][j]也就是回文子串
         * 如果s[i] != s[j]那么说明dp[i][j]dp[i][j]必定不是回文子串
         */
        if (s == null || s.length() == 0) return 0;

        int length = s.length();
        int[][] dp = new int[length][length];
        for (int i = 0; i < length; i++) dp[i][i] = 0;

        int result = length;
        for (int i = length - 1; i >= 0; i--) {
            for (int j = i + 1; j < length; j++) {
                if (s.charAt(i) == s.charAt(j)) {
                    if (j - i == 1)
                    dp[i][j] = 0;
                else 
                    dp[i][j] = dp[i + 1][j - 1];
                } else {
                    dp[i][j] = 1;
                }
                if (dp[i][j] == 0) result++;
            }
        }
        return result;
    }
}
// @lc code=end

