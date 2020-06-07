/*
 * @lc app=leetcode.cn id=70 lang=java
 *
 * [70] 爬楼梯
 */

// @lc code=start
class Solution {
    public int climbStairs(int n) {
        // // 斐波那契数
        // if (n == 1) {
        //     return 1;
        // }
        // int first = 1;
        // int second = 2;
        // for (int i = 3; i <= n; i++) {
        //     int third = first + second;
        //     first = second;
        //     second = third;
        // }
        // return second;

        // 动态规划
        if (n == 1) {
            return 1;
        }
        int[] dp = new int[n + 1];
        dp[1] = 1;
        dp[2] = 2;
        for (int i = 3; i <= n; i++) {
            dp[i] = dp[i - 1] + dp[i -2];
        }
        return dp[n];

        // 懵逼的时候：
        // 暴力？ 基本情况？
        
        // 找 最近 重复子问题

        // if else,
        // for while, recursion

        // 1: 1
        // 2: 2
        // 3: f(1) + f(2)
        // 4: f(2) + f(3)
        // f(n) = f(n-1) + f(n-2) : Fibonacci
    }
}
// @lc code=end

