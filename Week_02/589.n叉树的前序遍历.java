import java.util.ArrayList;

/*
 * @lc app=leetcode.cn id=589 lang=java
 *
 * [589] N叉树的前序遍历
 */

// @lc code=start
/*
// Definition for a Node.
class Node {
    public int val;
    public List<Node> children;

    public Node() {}

    public Node(int _val) {
        val = _val;
    }

    public Node(int _val, List<Node> _children) {
        val = _val;
        children = _children;
    }
};
*/

class Solution {
    public List<Integer> preorder(Node root) {
        List<Integer> res = new ArrayList<Integer>();
        helper(root, res);
        return res;
    }

    private void helper(Node root, List<Integer> res) {
        if (root != null) {
            res.add(root.val);

            int size = root.children.size();
            for (int i = 0; i < size; i++) {
                helper(root.children.get(i), res);
            }
        }
    }
}
// @lc code=end

