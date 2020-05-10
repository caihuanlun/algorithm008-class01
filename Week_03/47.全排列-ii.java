import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/*
 * @lc app=leetcode.cn id=47 lang=java
 *
 * [47] 全排列 II
 */

// @lc code=start
class Solution {
    public List<List<Integer>> permuteUnique(int[] nums) {
        List<List<Integer>> result = new ArrayList<List<Integer>>();
        if (nums == null || nums.length == 0) return result;
        boolean[] used = new boolean[nums.length];
        List<Integer> list = new ArrayList<Integer>();
        Arrays.sort(nums);
        DFS(nums, used, list, result);
        return result;
    }

    private void DFS(int[] nums, boolean[] used, List<Integer> list, List<List<Integer>> result) {
        // terminator
        if (list.size() == nums.length) {
            result.add(new ArrayList<Integer>(list));
            return ;
        }

        // process
        for (int i = 0; i < nums.length; i++) {
            if (used[i]) continue;
            if (i > 0 && nums[i - 1] == nums[i] && !used[i - 1]) continue;
            used[i] = true;
            list.add(nums[i]);
            DFS(nums, used, list, result);
            used[i] = false;
            list.remove(list.size() - 1);
        }
    }
}
// @lc code=end

