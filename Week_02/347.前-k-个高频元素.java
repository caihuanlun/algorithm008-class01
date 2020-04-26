import java.util.HashMap;
import java.util.PriorityQueue;

/*
 * @lc app=leetcode.cn id=347 lang=java
 *
 * [347] 前 K 个高频元素
 */

// @lc code=start
class Solution {
    public int[] topKFrequent(int[] nums, int k) {
        /**
         * 1.堆：手动维护建立一个堆，
         * hashmap记录，key->元素，value->元素出现的次数，
         * poll前k个元素
         */
        HashMap<Integer, Integer> map = new HashMap<>();

        for (int num : nums) {
            map.put(num, map.getOrDefault(num, 0) + 1);
        }

        PriorityQueue<Integer> heap = new PriorityQueue<Integer>((n1, n2) 
        -> map.get(n1) - map.get(n2));

        for (int num : map.keySet()) {
            heap.add(num);
            if (heap.size() > k) heap.poll();
        }

        int[] topK = new int[k];
        for (int i = 0; i < k; i++) {
            if (!heap.isEmpty()) topK[i] = heap.poll();
        }
        return topK;
    }
}
// @lc code=end

