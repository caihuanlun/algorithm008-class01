/*
 * @lc app=leetcode.cn id=33 lang=java
 *
 * [33] 搜索旋转排序数组
 */

// @lc code=start
class Solution {
    public int search(int[] nums, int target) {
        /**
         * 五毒神掌；四步做题
         * 1.暴力：还原O(logN) -> 升序 -> 二分：O(logN) (写、总结)
         * 2.正解：二分查找，a.单调 b.边界 c.index
         * 直接使用二分查找，但是比较条件变了，
         * 要确定元素在左半部分还是右半部分
         */
        int left = 0, right = nums.length - 1;
        while (left < right) {
            int mid = (left + right) / 2;
            if (nums[0] <= nums[mid] 
                && (target > nums[mid] || target < nums[0])) {
                left = mid + 1;
            } else if (target > nums[mid]  && target < nums[0]) {
                left = mid + 1;
            } else {    
                right = mid;
            }
        }
        return left == right && nums[left] == target ? left : -1;
    }
}
// @lc code=end

