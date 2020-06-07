import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

/*
 * @lc app=leetcode.cn id=51 lang=java
 *
 * [51] N皇后
 */

// @lc code=start
class Solution {
    public List<List<String>> solveNQueens(int n) {
        // init
        char[][] board = new char[n][n];
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                board[i][j] = '.';
            }
        }

        List<List<String>> result = new ArrayList<List<String>>();
        DFS(board, 0, result);
        return result;
    }

    private void DFS(char[][] board, int colIndex, List<List<String>> result) {
        // terminator
        if (colIndex == board.length) {
            result.add(construct(board));
            return ;
        }

        // process
        for (int row = 0; row < board.length; row++) {
            // dirll down
            if (validate(board, row, colIndex)) {
                board[row][colIndex] = 'Q';
                DFS(board, colIndex + 1, result);
                board[row][colIndex] = '.';
            }
        }
    }

    private boolean validate(char[][] board, int row, int colIndex) {
        for (int i = 0; i < board.length; i++) {
            for (int j = 0; j < colIndex; j++){
                if (board[i][j] == 'Q'
                    && (row + j == i + colIndex 
                    || row + colIndex == i + j
                    || row == i))
                    return false;
            }
        }
        return true;
    }

    private List<String> construct(char[][] board) {
        List<String> res = new LinkedList<String>();
        for (int i = 0; i < board.length; i++) {
            String s = new String(board[i]);
            res.add(s);
        }
        return res;
    }

}
// @lc code=end

