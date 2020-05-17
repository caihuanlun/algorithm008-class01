import java.util.HashSet;
import java.util.Set;

/*
 * @lc app=leetcode.cn id=874 lang=java
 *
 * [874] 模拟行走机器人
 */

// @lc code=start
class Solution {
    public int robotSim(int[] commands, int[][] obstacles) {
        // North, direction = 0, directions[direction] = {0, 1}
        // East,  direction = 1, directions[direction] = {1, 0}
        // South, direction = 2, directions[direction] = {0, -1}
        // West,  direction = 3, directions[direction] = {-1, 0}
        int[][] dirs = {{0, 1}, {1, 0}, {0, -1}, {-1, 0}};

        Set<String> obstaclesSet = new HashSet<>();
        for (int[] obstacle : obstacles) {
            obstaclesSet.add(obstacle[0] + " " + obstacle[1]);
        }

        int x = 0, y = 0, dir = 0, maxDistSquare = 0;
        for (int i = 0; i < commands.length; i++) {
            if (commands[i] == -2) { // turn left
                dir = (dir + 3) % 4;
            } else if (commands[i] == -1) { // turn right
                dir = (dir + 1) % 4;
            } else {// moves forward commands[i] steps
                int step = 0;
                while (step < commands[i]
                    && (!obstaclesSet.contains(x + dirs[dir][0]
                        + " "
                        + (y + dirs[dir][1])))) {
                    x += dirs[dir][0];
                    y += dirs[dir][1];
                    step++;
                }
            }
            // compare and replace
            maxDistSquare = Math.max(maxDistSquare, x * x + y * y);
        }
        return maxDistSquare;
    }
}
// @lc code=end

