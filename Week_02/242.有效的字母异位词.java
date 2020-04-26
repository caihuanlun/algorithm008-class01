import java.util.Arrays;

/*
 * @lc app=leetcode.cn id=242 lang=java
 *
 * [242] 有效的字母异位词
 */

// @lc code=start
class Solution {
    public boolean isAnagram(String s, String t) {
        /**
         * 1.clarification
         * 2.possible solutions --> optimal(time & space)
         * 3.code
         * 4.test case
         */

        /**
         * 1. 暴力法：转为字符数组，sort排序，比较是否相等
         */
        if (s.length() != t.length()) return false;

        char[] str1 = s.toCharArray();
        char[] str2 = t.toCharArray();
        Arrays.sort(str1);
        Arrays.sort(str2);

        return Arrays.equals(str1, str2);

        // /**
        //  * 2. 计数器法：s的字符++，t的字符--，最终判断是否每一位为0
        //  */
        // if (s.length() != t.length()) return false;
        
        // int[] counter = new int[26];
        // for (int i = 0; i < s.length(); i++) {
        //     counter[s.charAt(i) - 'a']++;
        //     counter[t.charAt(i) - 'a']--;
        // }

        // for (int count : counter) {
        //     if (count != 0) return false;
        // }

        // return true;
    }
}
// @lc code=end

