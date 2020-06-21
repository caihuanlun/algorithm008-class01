import java.util.Arrays;
import java.util.Collections;

/*
 * @lc app=leetcode.cn id=151 lang=java
 *
 * [151] 翻转字符串里的单词
 */

// @lc code=start
class Solution {
    public String reverseWords(String s) {
        //1. split，reverse，join
        //2. reverse整个string， 然后在单独reverse每个单词
        String[] words = s.trim().split(" +");
        Collections.reverse(Arrays.asList(words));
        return String.join(" ", words);
    }
}
// @lc code=end

