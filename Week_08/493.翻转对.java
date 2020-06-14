import java.util.Arrays;

/*
 * @lc app=leetcode.cn id=493 lang=java
 *
 * [493] 翻转对
 */

// @lc code=start
class Solution {
    public int reversePairs(int[] nums) {
        //1. 暴力法：两个嵌套循环：O(n^2)
        //2. merge-sort O(nlogn)
        //3. 树状数组 O(nlogn)
        if (nums == null && nums.length == 0) return 0;
        return mergeSort(nums, 0, nums.length - 1);
    }

    public static int mergeSort(int[] nums, int left, int right) {
        if (left >= right) return 0;
        int mid = left + (right - left) / 2;
        int count = mergeSort(nums, left, mid) + mergeSort(nums, mid + 1, right);
        // 左右之间重要逆序对
        int[] cache = new int[right - left + 1];
        int i = left, t = left, c = 0;
        for (int j = mid + 1; j <= right; j++, c++) {
            while (i <= mid && nums[i] <= 2 * (long)nums[j]) i++;
            // 归并
            while(t <= mid && nums[t] < nums[j]) cache[c++] = nums[t++];
            cache[c] = nums[j];
            count += mid - i + 1;
        }
        while (t <= mid) cache[c++] = nums[t++];
        System.arraycopy(cache, 0, nums, left, right - left + 1);
        return count;
    }

    // private static void merge(int[] array, int left, int mid, int right) {
    //     int[] temp = new int[right - left + 1];//中间数组
    //     int i = left, j = mid + 1, k = 0;

    //     while (i <= mid && j <= right) {
    //         temp[k++] = array[i] < array[j] ? array[i++] : array[j++];
    //     }

    //     while (i <= mid) temp[k++] = array[i++];
    //     while (j <= right) temp[k++] = array[j++];

    //     for (int p = 0; p < temp.length; p++) {
    //         array[left + p] = temp[p];
    //     }
    // }
}
// @lc code=end

