/*
 * @lc app=leetcode.cn id=5 lang=java
 *
 * [5] 最长回文子串
 */

// @lc code=start
class Solution {
    // private int lo, maxLen;

    public String longestPalindrome(String s) {
        /**
         * 暴力法：嵌套循环，枚举i，j（起点，终点），判断该子串是否回文O(n^3)
         */

        // /**
        //  * 中间向两边扩张O(n^2)
        //  */
        // int length = s.length();
        // if (length < 2) return s;

        // for (int i = 0; i < length - 1; i++) {
        //     extendPalindrome(s, i, i); // odd length
        //     extendPalindrome(s, i, i + 1);// even length
        // }
        // return s.substring(lo, lo + maxLen);

        /**
         * DP 
         * 首先定义P(i, j):
         * P(i, j) = true s[i, j]是回文串
         * P(i, j) = flase s[i, j]不是a回文串
         * 接下来
         * P(i, j) = (P(i + 1, j - 1) && S[i] == S[j])
         */
        int n = s.length();
        String res = "";
        boolean[][] dp = new boolean[n][n];

        for (int i = n - 1; i >= 0; i--) {
            for (int j = i; j < n; j++) {
                dp[i][j] = s.charAt(i) == s.charAt(j) 
                && (j - i < 2 || dp[i + 1][j - 1]);
                if (dp[i][j] && j - i + 1 > res.length()) {
                    res = s.substring(i, j + 1);
                }
            }
        }
        return res;
    }
    //  private void extendPalindrome(String s, int j, int k) {
    //      while (j >= 0 && k < s.length() && s.charAt(j) == s.charAt(k)) {
    //          j--;
    //          k++;
    //      }

    //      if (maxLen < k - j - 1) {
    //          lo = j + 1;
    //          maxLen = k - j - 1;
    //      }
    //  }
}
// @lc code=end

