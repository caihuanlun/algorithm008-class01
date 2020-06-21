/*
 * @lc app=leetcode.cn id=115 lang=java
 *
 * [115] 不同的子序列
 */

// @lc code=start
class Solution {
    public int numDistinct(String s, String t) {
        /**
         * DP
         * dp[i][j]代表T前i字符串可以由s前j字符串组成最多个数
         * 所以动态方程：
         * 当S[j] == T[i], dp[i][j] = dp[i - 1][j - 1] + dp[i][j - 1]
         * 当S[j] != T[i], dp[i][j] = dp[i][j - 1]
         */
        int m = t.length(), n = s.length();
        int[][] dp = new int[m + 1][n + 1];
        for (int i = 0; i < n + 1; i++) 
            dp[0][i] = 1;
        
        for (int i = 1; i < m + 1; i++) {
            for (int j = i; j < n + 1; j++) {
                if (t.charAt(i - 1) == s.charAt(j - 1)) {
                    dp[i][j] = dp[i][j - 1] + dp[i - 1][j - 1];
                } else {
                    dp[i][j] = dp[i][j - 1];
                }
            }
        }
        return dp[m][n];
    }
}
// @lc code=end

