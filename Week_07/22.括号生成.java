import java.util.ArrayList;
import java.util.List;

/*
 * @lc app=leetcode.cn id=22 lang=java
 *
 * [22] 括号生成
 */

// @lc code=start
class Solution {
    public List<String> generateParenthesis(int n) {
        /**
         * 方法1：分治
         */
        List<String> result  = new ArrayList<String>();
        generate(0, 0, n, "", result);
        return result;

        /**
         * 方法2：DP
         */
    }

    private void generate(int left, int right, int n, String s, List<String> result) {
        // terminator
        if (left == n && right == n) {
            result.add(s);
            return ;
        }

        // process current logic: left, right
        
        // drill down
        // left 随时可以加，只要别超标
        // right 左括号个数 > 右括号个数，才能再加一个右括号
        if (left < n)
            generate(left + 1, right, n, s + "(", result);

        if (left > right)
            generate(left, right + 1, n, s + ")", result);

        // reverse states
    }

}
// @lc code=end

