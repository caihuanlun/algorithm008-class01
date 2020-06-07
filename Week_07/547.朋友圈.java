/*
 * @lc app=leetcode.cn id=547 lang=java
 *
 * [547] 朋友圈
 */

// @lc code=start
class Solution {
    public int findCircleNum(int[][] M) {
        /**
         * 1. DFS， BFS （类似 岛屿问题）
         * 2. 并查集
         */

         /**
          * 使用一个visited数组，一次判断每个节点
          * 如果其未访问，朋友圈数加1并对该节点进行DFS搜索标记所有访问的节点 
          */
          boolean[] visited = new boolean[M.length];
          int result = 0;
          for (int i = 0; i < M.length; i++) {
              if (!visited[i]) {
                  DFS(M, visited, i);
                  result++;
              }
          }
          return result;
    }

    private void DFS (int[][] M, boolean[] visited, int i) {
        for (int j = 0; j < M.length; j++) {
            if (M[i][j] == 1 && !visited[j]) {
                visited[j] = true;
                DFS(M, visited, j);
            }
        }
    }
}
// @lc code=end

