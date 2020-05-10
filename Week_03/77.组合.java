import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

/*
 * @lc app=leetcode.cn id=77 lang=java
 *
 * [77] 组合
 */

// @lc code=start
class Solution {
    public List<List<Integer>> combine(int n, int k) {
        List<List<Integer>> result = new ArrayList<List<Integer>>();
        backTrack(n, k, 1, new LinkedList<Integer>(), result);
        return result;
    }

    private void backTrack(int n, int k, int level, LinkedList<Integer> curr, List<List<Integer>> result) {
        // terminator
        if (curr.size() == k) {
            result.add(new LinkedList<>(curr));
            return ;
        }

        // process
        for (int i = level; i < n + 1; i++) {
            // drill down
            curr.add(i);
            backTrack(n, k, i + 1, curr, result);
            // reverse
            curr.removeLast();
        }
    }
}
// @lc code=end

