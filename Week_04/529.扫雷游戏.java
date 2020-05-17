/*
 * @lc app=leetcode.cn id=529 lang=java
 *
 * [529] 扫雷游戏
 */

// @lc code=start
class Solution {
    /**
     * 方法1：DFS
     */
    // 8 directions
    private int dirs[][] = {{0, 1}, {0, -1}, {1, 0}, {-1, 0}, 
    {-1, -1}, {-1, 1}, {1, -1}, {1, 1}};

    public char[][] updateBoard(char[][] board, int[] click) {
        int m = board.length, n = board[0].length;
        int row = click[0], col = click[1];

        // terminator
        if (board[row][col] == 'M'
            || board[row][col] == 'X') {
                board[row][col] = 'X';
                return board;
        }

        // process
        int num = 0;
        for (int[] dir : dirs) {
            int newRow = dir[0] + row;
            int newCol = dir[1] + col;

            if (newRow >= 0 && newCol >= 0
                && newRow < m && newCol < n
                && board[newRow][newCol] == 'M') {
                num++;
            }
        }

        if (num > 0) {
            board[row][col] = (char) (num + '0');
            return board;
        }

        // drill down
        board[row][col] = 'B';
        for (int[] dir : dirs) {
            int newRow = dir[0] + row;
            int newCol = dir[1] + col;

            if (newRow >= 0 && newCol >= 0
                && newRow < m && newCol < n
                && board[newRow][newCol] == 'E') {
                updateBoard(board, new int[]{newRow, newCol});
            }
        }

        return board;
    }
}
// @lc code=end

