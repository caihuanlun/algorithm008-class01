/*
 * @lc app=leetcode.cn id=860 lang=java
 *
 * [860] 柠檬水找零
 */

// @lc code=start
class Solution {
    public boolean lemonadeChange(int[] bills) {
        int count5 = 0, count10 = 0;
        for (int b : bills) {
            if (b == 5) {
                count5++;
            } else if (b == 10) {
                if (count5 == 0) {
                    return false;
                } else {
                    count5--;
                    count10++;
                } 
            } else {
                if (count5 > 0 && count10 > 0) {
                    count5--;
                    count10++;
                } else if (count5 > 3) {
                    count5 -= 3;
                    continue;
                } else {
                    return false;
                }
            }
        }
        return false;
    }
}
// @lc code=end

