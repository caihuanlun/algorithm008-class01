import java.util.HashMap;

/*
 * @lc app=leetcode.cn id=387 lang=java
 *
 * [387] 字符串中的第一个唯一字符
 */

// @lc code=start
class Solution {
    public int firstUniqChar(String s) {
        /**
         * 方法1. brute-force
         * i枚举所有字符，j枚举i后面的所有字符找重复
         * O(n^2)
         */

         /**
          * 方法2. map(hashmap O(1), treemap O(lngN))
          * O(N) or O(NlongN)
          */
          HashMap<Character, Integer> map = new HashMap<>();
          for (int i = 0; i < s.length(); i++) { 
            map.put(s.charAt(i), map.getOrDefault(s.charAt(i), 0) + 1);
          }
          for (int i = 0; i < s.length(); i++) {
            if (map.get(s.charAt(i)) == 1) return i;
          }
          return -1;

          /**
           * 方法3. hash table
           */
    }
}
// @lc code=end

