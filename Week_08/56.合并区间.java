import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

/*
 * @lc app=leetcode.cn id=56 lang=java
 *
 * [56] 合并区间
 */

// @lc code=start
class Solution {
    public int[][] merge(int[][] intervals) {
        int n = intervals.length;
        if (n <= 1) return intervals;

        int[] start = new int[n];
        int[] end = new int[n];
        for (int i = 0; i < n; i++) {
            start[i] = intervals[i][0];
            end[i] = intervals[i][1];
        }
        Arrays.sort(start);
        Arrays.sort(end);
        int startIndex = 0;
        int endIndex = 0;
        List<int[]> result = new LinkedList<>();
        while (endIndex < n) {
            if (endIndex == n - 1 || start[endIndex + 1] > end[endIndex]) {
                result.add(new int[]{start[startIndex], end[endIndex]});
                startIndex = endIndex + 1;
            }
            endIndex++;
        }
        return result.toArray(new int[result.size()][]);
    }
}
// @lc code=end

