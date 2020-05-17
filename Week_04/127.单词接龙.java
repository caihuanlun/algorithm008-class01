import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Queue;

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
        if (!wordList.contains(endWord)) return 0;
        
        // Since all words are of same length
        L = beginWord.length();

        wordList.forEach(
            word -> {
                for (int i = 0; i < L; i++) {
                    String newWord = word.substring(0, i) 
                    + "*" + word.substring(i + 1, L);
                    List<String> transformations = allComboDict.getOrDefault(newWord, new ArrayList<>());
                    transformations.add(word);
                    allComboDict.put(newWord, transformations);
                }
            }
        );

        // BFS starting from beginWord
        Queue<Pair<String, Integer>> qBegin = new LinkedList<>();
        // BFS starting from endWord
        Queue<Pair<String, Integer>> qEnd = new LinkedList<>();
        qBegin.add(new Pair<String,Integer>(beginWord, 1));
        qEnd.add(new Pair<String,Integer>(endWord, 1));

        Map<String, Integer> visitedBegin = new HashMap<>();
        Map<String, Integer> visitedEnd = new HashMap<>();
        visitedBegin.put(beginWord, 1);
        visitedEnd.put(endWord, 1);

        while (!qBegin.isEmpty()
                && !qEnd.isEmpty()) {
            // One hop from begin word
            int result = visitWordNode(qBegin, visitedBegin, visitedEnd);
            if (result > -1) return result;

            // One hop from end word
            result = visitWordNode(qEnd, visitedEnd, visitedBegin);
            if (result > -1) return result;
        }

        return 0;
    }

    private int visitWordNode(Queue<Pair<String, Integer>> Q, Map<String, Integer> visited,
            Map<String, Integer> othersVisited) {
        Pair<String, Integer> node = Q.remove();
        String word = node.getKey();
        int level = node.getValue();

        for (int i = 0; i < L; i++) {
            String newWord = word.substring(0, i) + "*" + word.substring(i + 1, L);
            for (String adjacentWord : allComboDict.getOrDefault(newWord, new ArrayList<>())) {
                if (othersVisited.containsKey(adjacentWord)) {
                    return level + othersVisited.get(adjacentWord);
                }

                if (!visited.containsKey(adjacentWord)) {
                    visited.put(adjacentWord, level + 1);
                    Q.add(new Pair<String,Integer>(adjacentWord, level + 1));
                }
            } 
        }
        return -1;
    }
}
// @lc code=end

