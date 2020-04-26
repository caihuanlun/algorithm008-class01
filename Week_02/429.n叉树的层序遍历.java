import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

import com.sun.corba.se.impl.orbutil.graph.Node;

/*
 * @lc app=leetcode.cn id=429 lang=java
 *
 * [429] N叉树的层序遍历
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
    // List<List<Integer>> result = new ArrayList<List<Integer>>();

    public List<List<Integer>> levelOrder(Node root) {
        /**
         * 1.利用队列实现广度优先搜索
         */
        List<List<Integer>> result = new ArrayList<List<Integer>>();
        if (root == null) return result;
        Queue<Node> queue = new LinkedList<>();
        queue.add(root);
        while (!queue.isEmpty()) {
            List<Integer> level = new ArrayList<>();
            int size = queue.size();
            for (int i = 0; i < size; i++) {
                Node node = queue.poll();
                level.add(node.val);
                queue.addAll(node.children);
            }
            result.add(level);
        }
        return result;


        // /**
        //  * 2.递归
        //  */
        // if (root != null) {
        //     traverseNode(root, 0);
        // }
        // return result;
    }

    // private void traverseNode(Node node, int level) {
    //     if (result.size() <= level) {
    //         result.add(new ArrayList<>());
    //     }

    //     result.get(level).add(node.val);

    //     for (Node clild : node.children) {
    //         traverseNode(node, level);
    //     }
    // }
}
// @lc code=end

