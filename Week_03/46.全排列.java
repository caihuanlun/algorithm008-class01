import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/*
 * @lc app=leetcode.cn id=46 lang=java
 *
 * [46] 全排列
 */

// @lc code=start
class Solution {
    public List<List<Integer>> permute(int[] nums) {
        List<List<Integer>> result = new ArrayList<List<Integer>>();
        List<Integer> curr = new ArrayList<Integer>();
        for (int num : nums) {
            curr.add(num);
        }
        backTrack(nums, 0, curr, result);
        return result;
    }

    private void backTrack(int[] nums, int level, List<Integer> curr, List<List<Integer>> result) {
        // terminator
        if (level == nums.length) {
            result.add(new ArrayList<Integer>(curr));
            return;
        }

        // process
        for (int i = level; i < nums.length; i++) {
            // drill down
            Collections.swap(curr, level, i);
            backTrack(nums, level + 1, curr, result);
            // reverse
            Collections.swap(curr, level, i);
        }
    }
}
// @lc code=end

