/*
 * @lc app=leetcode.cn id=88 lang=java
 *
 * [88] 合并两个有序数组
 */

// @lc code=start
class Solution {
    public void merge(int[] nums1, int m, int[] nums2, int n) {
        // /**
        //  * 1.合并后排序，时间复杂度较差，为O((n+m)log(n+m))
        //  */
        // System.arraycopy(nums2, 0, nums1, m, n);

        /**
         * 2.双指针从后往前，时间复杂度为O(n + m)
         */
        int p1 = m - 1;
        int p2 = n - 1;
        int p = m + n - 1;
        while ((p1 >= 0) && (p2 >= 0)) {
            nums1[p--] = (nums1[p1] < nums2[p2]) ? nums2[p2--] : nums1[p1--];
        }

        System.arraycopy(nums2, 0, nums1, 0, p2 + 1);
    }
}
// @lc code=end

