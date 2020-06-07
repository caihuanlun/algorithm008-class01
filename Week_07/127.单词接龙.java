import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Queue;
import java.util.Set;

import javafx.util.Pair;

/*
 * @lc app=leetcode.cn id=127 lang=java
 *
 * [127] 单词接龙
 */

// @lc code=start
class Solution {
    private int L = 0;
    private Map<String, List<String>> allComboDict = new HashMap<>();

    public int ladderLength(String beginWord, String endWord, List<String> wordList) {
        // /**
        //  * 方法1：BFS
        //  */
        // if (!wordList.contains(endWord)) return 0;

        // Queue<String> queue = new LinkedList<>();
        // Set<String> visited = new HashSet<>();
        // queue.add(beginWord);
        // visited.add(beginWord);

        // int depth = 0;

        // while (queue.size() > 0) {
        //     int size = queue.size();
        //     depth++;
        //     for (int i = 0; i < size; i++) {
        //         String start = queue.poll();
        //         for (String s : wordList) {
        //             if (visited.contains(s)) continue;

        //             if (!canConvert(start, s)) continue;

        //             if (s.equals(endWord)) return depth + 1;

        //             visited.add(s);
        //             queue.add(s);
        //         }
        //     }
        // }

        // return 0;

        /**
         * 方法2：双向BFS
         */
        // if (!wordList.contains(endWord)) return 0;
        
        // // Since all words are of same length
        // L = beginWord.length();

        // wordList.forEach(
        //     word -> {
        //         for (int i = 0; i < L; i++) {
        //             String newWord = word.substring(0, i) 
        //             + "*" + word.substring(i + 1, L);
        //             List<String> transformations = allComboDict.getOrDefault(newWord, new ArrayList<>());
        //             transformations.add(word);
        //             allComboDict.put(newWord, transformations);
        //         }
        //     }
        // );

        // // BFS starting from beginWord
        // Queue<Pair<String, Integer>> qBegin = new LinkedList<>();
        // // BFS starting from endWord
        // Queue<Pair<String, Integer>> qEnd = new LinkedList<>();
        // qBegin.add(new Pair<String,Integer>(beginWord, 1));
        // qEnd.add(new Pair<String,Integer>(endWord, 1));

        // Map<String, Integer> visitedBegin = new HashMap<>();
        // Map<String, Integer> visitedEnd = new HashMap<>();
        // visitedBegin.put(beginWord, 1);
        // visitedEnd.put(endWord, 1);

        // while (!qBegin.isEmpty()
        //         && !qEnd.isEmpty()) {
        //     // One hop from begin word
        //     int result = visitWordNode(qBegin, visitedBegin, visitedEnd);
        //     if (result > -1) return result;

        //     // One hop from end word
        //     result = visitWordNode(qEnd, visitedEnd, visitedBegin);
        //     if (result > -1) return result;
        // }

        // return 0;

        /**
         * 方法2：双向BFS简单代码
         */
        //转换成set查询更快
        Set<String> wordListSet = new HashSet<String>(wordList);

        Set<String> beginSet = new HashSet<String>(), 
        endSet = new HashSet<String>();
        
        beginSet.add(beginWord);
        endSet.add(endWord);

        int len = 1;
        int strLen = beginWord.length();
        Set<String> visited = new HashSet<String>();

        // BFS start
        while (!beginSet.isEmpty() && !endSet.isEmpty()) {
            if (beginSet.size() > endSet.size()) {
                Set<String> set = beginSet;
                beginSet = endSet;
                endSet = set;
            }

            Set<String> temp = new HashSet<String>();
            for (String word : wordListSet) {
                char[] chs = word.toCharArray();

                for (int i = 0; i < chs.length; i++) {
                    for (char c = 'a'; c <= 'z'; c++) {
                        char old = chs[i];
                        chs[i] = c;
                        String target = String.valueOf(chs);

                        if (endSet.contains(target)) {
                            return len + 1;
                        }

                        if (!visited.contains(target) && wordList.contains(target)) {
                            temp.add(target);
                            visited.add(target);
                        }
                        // 还原
                        chs[i] = old;
                    }
                }
            }
            beginSet = temp;
            len++;
        }
    }

    private boolean canConvert(String start, String s) {
        if (start.length() != s.length()) return false;
        int count = 0;
        for (int i = 0; i < start.length(); i++) {
            if (start.charAt(i) != s.charAt(i)) {
                count++;
                if (count > 1) return false;
            }
        }
        return count == 1;
    }

    // private int visitWordNode(Queue<Pair<String, Integer>> Q,
    //         Map<String, Integer> visited,
    //         Map<String, Integer> othersVisited) {
    //     Pair<String, Integer> node = Q.remove();
    //     String word = node.getKey();
    //     int level = node.getValue();

    //     for (int i = 0; i < L; i++) {
    //         String newWord = word.substring(0, i) + "*" + word.substring(i + 1, L);
    //         for (String adjacentWord : allComboDict.getOrDefault(newWord, new ArrayList<>())) {
    //             if (othersVisited.containsKey(adjacentWord)) {
    //                 return level + othersVisited.get(adjacentWord);
    //             }

    //             if (!visited.containsKey(adjacentWord)) {
    //                 visited.put(adjacentWord, level + 1);
    //                 Q.add(new Pair<String,Integer>(adjacentWord, level + 1));
    //             }
    //         } 
    //     }
    //     return -1;
    // }
}
// @lc code=end

