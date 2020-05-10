import java.util.HashMap;
import java.util.Map;

import javax.swing.tree.TreeNode;

/*
 * @lc app=leetcode.cn id=105 lang=java
 *
 * [105] 从前序与中序遍历序列构造二叉树
 */

// @lc code=start
/**
 * Definition for a binary tree node.
 * public class TreeNode {
 *     int val;
 *     TreeNode left;
 *     TreeNode right;
 *     TreeNode(int x) { val = x; }
 * }
 */
class Solution {
    int preIndex = 0;
    int[] preOrder;
    int[] inOrder;
    Map<Integer, Integer> indexMap = new HashMap<Integer, Integer>();
    public TreeNode buildTree(int[] preorder, int[] inorder) {
        this.preOrder = preorder;
        this.inOrder = inorder;
        int index = 0;
        for (int val : inorder) {
            indexMap.put(val, index++);
        }
        return helper(0, inorder.length);
    }

    private TreeNode helper(int inLeft, int inRight) {
        if (inLeft == inRight)
            return null;
        
        int rootVal = preOrder[preIndex];
        TreeNode root = new TreeNode(rootVal);

        int index = indexMap.get(rootVal);

        preIndex++;
        root.left = helper(inLeft, index);
        root.right = helper(index + 1, inRight);
        return root;
    }
}
// @lc code=end

