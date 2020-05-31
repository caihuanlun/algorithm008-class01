/*
 * @lc app=leetcode.cn id=64 lang=java
 *
 * [64] 最小路径和
 */

// @lc code=start
class Solution {
    public int minPathSum(int[][] grid) {
        /**
         * 方法1：DP方程
         * a.分解子问题
         * b.状态定义
         * c.DP方程
         * dp[i][j] : 向前走一步 min value ：dp[i + 1][j] 或者 dp[i][j + 1]
         * dp[i][j] : Min(dp[i + 1][j], dp[i][j + 1])
         * dp[i][j] = Min(dp[i + 1][j], dp[i][j + 1]) + grid[i][j]
         */
         int n = grid.length;
         int m = grid[0].length;

         int[][] dp = new int[n][m];

         for (int i = n - 1; i >= 0; i--) {
            for (int j = m - 1; j >= 0; j--) {
                if(i == n - 1 && j != m - 1)
                    dp[i][j] = grid[i][j] +  dp[i][j + 1];
                else if(i != n - 1 && j == m - 1)
                    dp[i][j] = grid[i][j] + dp[i + 1][j];
                else if(i != n - 1 && j != m - 1)
                    dp[i][j] = grid[i][j] + Math.min(dp[i + 1][j], dp[i][j + 1]);
                else
                    dp[i][j] = grid[i][j];
             }
         }
         return dp[0][0];
    }
}
// @lc code=end

