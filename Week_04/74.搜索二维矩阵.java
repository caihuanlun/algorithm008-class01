/*
 * @lc app=leetcode.cn id=74 lang=java
 *
 * [74] 搜索二维矩阵
 */

// @lc code=start
class Solution {
    public boolean searchMatrix(int[][] matrix, int target) {
        // /**
        //  * 方法1：暴力法
        //  */
        // int m = matrix.length;
        // if (m == 0) return false;
        // int n = matrix[0].length;

        // for (int i = 0; i < m; i++) {
        //     for (int j = 0; j < n; j++) {
        //         if (matrix[i][j] == target) {
        //             return true;
        //         }
        //     }
        // }
        // return false;

        /**
         * 方法2：二分查找
         */
        int m = matrix.length;
        if (m == 0) return false;
        int n = matrix[0].length;

        int left = 0 , right = m * n - 1;
        int midIdx, midElement;
        while (left <= right) {
            midIdx = (left + right) / 2;
            midElement = matrix[midIdx / n][midIdx % n];
            if (target == midElement) {
                return true;
            } else if (target < midElement) {
                right = midIdx - 1;
            } else {
                left = midIdx + 1;
            }
        }
        return false;
    }
}
// @lc code=end

