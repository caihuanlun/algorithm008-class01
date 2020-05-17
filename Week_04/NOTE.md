# 学习笔记
## 1. 深度优先搜索、广度优先搜索
### 1.1 深度优先搜索
**递归**
多叉树深度优先搜索示例代码：
```python
visited = set()
def DFS(node, visted):
    # terminator
    if node in visited:
        # already visited
        return

    visited.add(node)

    # process current node here
    ...
    for next_node in node_children():
        if not next_node in visited:
            DFS(next_node, visited)
``` 

二叉树深度优先搜索示例代码：
```python
def DFS(node):
    if node in visited:
        # already visted
        return

    visited.add(node)

    # process current here
    #...# logic here
    DFS(node.left)
    DFS(node.right)
```

**非递归**
```python
def DFS(self, tree):
    if tree.root is None:
        return []
    visited, stack = []. [tree, root]

    while stack:
        node = stakc.pop()
        visited.add(node)

        prcess (node)
        nodes = generate_related_nodes(node)
        stack.push(nodes)
```

### 1.2 广度优先搜索
```python
def BFS(graph, start, end):
    queue = []
    queue.append([start])
    visited.add(start)

    while queue:
        node = queue.pop()
        visited.add(node)

        process(node)
        nodes = generate_related_nodes(node)
        queue.push(nodes)
    
    # other processing work
    ...
```

## 2. 贪心算法 Greedy
### 2.1 定义
* 贪心算法是一种在每一步选择中都采取在当前状态下最好或最优（即最有利）的选择，从而希望导致结果是全局最好或者最优的算法。
* 贪心算法与动态规划的不同在于它对每个子问题的解决方案都做出选择，不能回退。动态规划则会保存以前的运算结果，并根据以前的结果对当前进行选择，有回退功能。
* 贪心：当下做局部最优判断
* 回溯：能投回退
* 动态规划：最优判断 + 回退
### 2.2 何种情况使用贪心算法
* 问题能够分解成子问题来解决，子问题的最优解能递推倒最终问题的最优解。这种子问题最优解称为最优子结构。
* 贪心算法与动态规划的不同在于它对每个子问题的解决方案都做出选择，不能回退。动态规划则会保存以前的运算结果，并根据以前的结果对当前进行选择，有回退功能。
* 注意：第一点，证明贪心可以获得全局最优解；第二点，可能从后往前贪心，或者局部切入贪心。

## 3. 二分查找
代码模板
```python
left, right = 0, len(array) - 1
while left <= right:
    mid = (left + right) / 2
    if array[mid] == target:
        # find the target!!
        break or return result
    elif array[mid] < target:
        left = mid + 1
    else:
        right = mid - 1
```