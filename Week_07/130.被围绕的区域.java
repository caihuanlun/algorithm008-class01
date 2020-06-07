/*
 * @lc app=leetcode.cn id=130 lang=java
 *
 * [130] 被围绕的区域
 */

// @lc code=start
class Solution {
    public void solve(char[][] board) {
        /**
         * 方法1：DFS递归
         */
        if (board == null || board.length == 0) return;
        int m = board.length;
        int n = board[0].length;

        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                boolean isEdge = i == 0 || j == 0 || i == m - 1 || j == n - 1;
                if (isEdge && board[i][j] == 'O') {
                    DFS(board, i, j);
                }
            }
        }

        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if (board[i][j] == 'O') {
                    board[i][j] = 'X';
                }
                if (board[i][j] == '#') {
                    board[i][j] = 'O';
                }
            }
        }
    }

    private void DFS(char[][] board, int i, int j) {
        if (i < 0 || j < 0 || i >= board.length || j >= board[0].length 
        || board[i][j] == 'X' || board[i][j] == '#') {
            return;
        }

        board[i][j] = '#';
        DFS(board, i - 1, j);
        DFS(board, i + 1, j);
        DFS(board, i, j - 1);
        DFS(board, i, j + 1);
    }
}
// @lc code=end

