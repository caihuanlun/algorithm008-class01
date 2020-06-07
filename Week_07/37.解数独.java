/*
 * @lc app=leetcode.cn id=37 lang=java
 *
 * [37] 解数独
 */

// @lc code=start
class Solution {
    // public void solveSudoku(char[][] board) {
    //     if (board == null || board.length == 0) return;
    //     solve(board);
    // }

    // private boolean solve(char[][] board) {
    //     for (int i = 0; i < board.length; i++) {
    //         for (int j = 0; j < board[0].length; j++) {
    //             if (board[i][j] == '.') {
    //                 for (char c = '1'; c <= '9'; c++) {
    //                     if (isValid(board, i, j, c)) {
    //                         board[i][j] = c;

    //                         if (solve(board)) return true;
    //                         else board[i][j] = '.';
    //                     }
    //                 }
    //                 return false;
    //             }
    //         }
    //     }
    //     return true;
    // }

	// private boolean isValid(char[][] board, int row, int col, char c) {
	// 	for (int i = 0; i < 9; i++) {
    //         if (board[i][row] != '.' && board[i][col] == c) return false;
    //         if (board[row][i] != '.' && board[row][i] == c) return false;
    //         if (board[3 * (row / 3) + i / 3][3 * (col / 3) + i % 3] != '.' && 
    //         board[3 * (row / 3) + i / 3][3 * (col / 3) + i % 3] == c) return false; 
    //     }
    //     return true;
    // }
    
    public void solveSudoku(char[][] board) {
        if(board == null || board.length == 0)
            return;
        solve(board);
    }
    
    public boolean solve(char[][] board){
        for(int i = 0; i < board.length; i++){
            for(int j = 0; j < board[0].length; j++){
                if(board[i][j] == '.'){
                    for(char c = '1'; c <= '9'; c++){//trial. Try 1 through 9
                        if(isValid(board, i, j, c)){
                            board[i][j] = c; //Put c for this cell
                            
                            if(solve(board))
                                return true; //If it's the solution return true
                            else
                                board[i][j] = '.'; //Otherwise go back
                        }
                    }
                    
                    return false;
                }
            }
        }
        return true;
    }
    
    private boolean isValid(char[][] board, int row, int col, char c){
        for(int i = 0; i < 9; i++) {
            if(board[i][col] != '.' && board[i][col] == c) return false; //check row
            if(board[row][i] != '.' && board[row][i] == c) return false; //check column
            if(board[3 * (row / 3) + i / 3][ 3 * (col / 3) + i % 3] != '.' && 
board[3 * (row / 3) + i / 3][3 * (col / 3) + i % 3] == c) return false; //check 3*3 block
        }
        return true;
    }
}
// @lc code=end

