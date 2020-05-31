import java.util.Arrays;

/*
 * @lc app=leetcode.cn id=621 lang=java
 *
 * [621] 任务调度器
 */

// @lc code=start
class Solution {
    public int leastInterval(char[] tasks, int n) {
        int[] dp = new int[26];
        for (char c : tasks) dp[c - 'A']++;
        Arrays.sort(dp);
        int max = dp[25] - 1, idelSlots = max * n;

        for (int i = 24; i >= 0 && dp[i] > 0; i--) {
            idelSlots = idelSlots - Math.min(dp[i], max);
        }

        return idelSlots > 0 ? idelSlots + tasks.length : tasks.length;
    }
}
// @lc code=end

