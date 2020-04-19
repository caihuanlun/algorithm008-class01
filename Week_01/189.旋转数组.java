/*
 * @lc app=leetcode.cn id=189 lang=java
 *
 * [189] 旋转数组
 */

// @lc code=start
class Solution {
    public void rotate(int[] nums, int k) {
        // /**
        //  * 1.暴力法
        //  */
        // if (k == 0) return;
        // int temp, last;
        // for (int i = 0; i < k; i++) {
        //     last = nums[nums.length - 1];
        //     for (int j = 0; j < nums.length; j++) {
        //         temp = nums[j];
        //         nums[j] = last;
        //         last = temp;
        //     }
        // }

        /**
         * 2.使用反转：全部反转->反转前k个元素->反转(n - k)个元素
         */
        k %= nums.length;
        reverse(nums, 0, nums.length - 1);
        reverse(nums, 0, k - 1);
        reverse(nums, k, nums.length - 1);
    }

    private void reverse(int[] nums, int start, int end) {
        int temp;
        while (start < end) {
            temp = nums[start];
            nums[start] = nums[end];
            nums[end] = temp;
            start++;
            end--;
        }
    }
}
// @lc code=end

