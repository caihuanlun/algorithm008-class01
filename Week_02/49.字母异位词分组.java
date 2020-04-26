import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

/*
 * @lc app=leetcode.cn id=49 lang=java
 *
 * [49] 字母异位词分组
 */

// @lc code=start
class Solution {
    public List<List<String>> groupAnagrams(final String[] strs) {
        if (strs.length == 0) return new ArrayList();
        final Map<String, List<String>> ans = new HashMap<String, List<String>>();
        for (final String s : strs) {
            final char[] ca = s.toCharArray();
            Arrays.sort(ca);
            final String key = String.valueOf(ca);
            if (!ans.containsKey(key)) ans.put(key, new ArrayList());
            ans.get(key).add(s);
        }
        return new ArrayList(ans.values());
    }
}
// @lc code=end

