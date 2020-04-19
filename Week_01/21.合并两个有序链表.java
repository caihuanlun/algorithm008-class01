/*
 * @lc app=leetcode.cn id=21 lang=java
 *
 * [21] 合并两个有序链表
 */

// @lc code=start
/**
 * Definition for singly-linked list.
 * public class ListNode {
 *     int val;
 *     ListNode next;
 *     ListNode(int x) { val = x; }
 * }
 */
class Solution {
    public ListNode mergeTwoLists(ListNode l1, ListNode l2) {
        // /**
        //  * 1.正常迭代：时间复杂度为O(m + n)，空间复杂度为O(1)
        //  */
        // ListNode prehead = new ListNode(-1);

        // ListNode prev = prehead;
        // while (l1 != null && l2 != null) {
        //     if (l1.val <= l2.val) {
        //         prev.next = l1;
        //         l1 = l1.next;
        //     } else {
        //         prev.next = l2;
        //         l2 = l2.next;
        //     }
        //     prev = prev.next;
        // }

        // prev.next = l1 == null ? l2 : l1;

        // return prehead.next;

        /**
         * 2.递归：容易理解代码简单，缺点空间复杂度高，
         * 时间复杂度为O(m + n), 空间复杂度为O(m + n)
         */
        if (l1 == null) return l2;
        if (l2 == null) return l1;

        if (l1.val < l2.val) {
            l1.next = mergeTwoLists(l1.next, l2);
            return l1;
        } else {
            l2.next = mergeTwoLists(l1, l2.next);
            return l2;
        }
    }
}
// @lc code=end

