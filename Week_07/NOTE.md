# 学习笔记
## 1. 字典树Trie 
### 1.1 基本结构
字典树，即Trie树，又称单词查找树或键树，是一种树结构。典型应用是用于统计和排序大量的字符串（但又不限于字符串），所以经常被搜索引擎系统用于文本词频统计。

它的优点是：最大限度地减少无谓的字符串比较，查询效率比哈希表高。

### 1.2 基本性质
1. 结点本身不存完整单词；
2. 从根节点到某一结点，路径上经过的字符连接起来，为该结点对应的字符串；
3. 每个结点的所有子结点路径代表的字符都不同。

### 1.3 核心思想
Trie树的核心思想是空间换时间。
利用字符串的公共前缀来降低查询时间的开销以达到提高效率的目的。

### 1.4 代码模板

```java
class Trie {
    private boolean isEnd;
    private Trie[] next;

    public Trie() {
        isEnd = false;
        next = new Trie(26);
    }

    public void insert(String word) {
        if (word == null || word.length() == 0) return;
        Trie curr = this;
        char[] words = word.toCharArray();
        for (int i = 0; i < words.length; i++) {
            int n = words[i] - 'a';
            if (curr.next[n] == null) curr.next[n] = new Trie();
            curr = curr.next[n]; 
        }
        curr.isEnd = true;
    }

    public boolean search(String word) {
        Trie node = searchPrefix(word);
        return node != null && node.isEnd;
    }

    public boolean startWith(String prefix) {
        Trie node = searchPrefix(prefix);
        return node != null;
    }

    public Trie searchPrefix(Stirng word) {
        Trie node = this;
        char[] words = word.toCharArray();
        for (int i = 0; i < words.length; i++) {
            node = node.next[words[i] - 'a'];
            if (node == null) return null;
        }
        return node;
    }
}
```

## 2. 并查集 Disjoint Set
适用场景
* 组团、配对问题
* Group or not ?
  
基本操作
* makeSet(s)：建立一个新的并查集，其中包含s个单元素集合。
* uninSet(x, y)：把元素x和元素y所在的集合合并，要求x和y所在的集合不相交，如果相交则不合并。
* find(x)：找到元素x所在的集合的代表，该操作也可以用于判断两个元素是否位于同一个集合，只要将它们各自的代表比较一下就可以了。
  

## 3. 高级搜索
1. 初级搜索
2. 优化方式：不重复（fibonacci）、剪枝（生成括号问题）
3. 搜索方向：
   
   DFS:depth first search 深度优先搜索

   BFS：breath first search 广度优先搜索

   双向搜索、启发式搜索

DFS代码 - 递归写法
```python
visited = set()

def dfs(node, visited):
    if node in visited:#terminator
        #already vistited
        return
    
    visited.add(node)

    #process current node here

    ...
    for next_node in node.children():
        if not next_node in visited:
            dfs(next_node, visited)
```

DFS 代码 - 非递归写法
```python
def DFS(self, tree):
    if tree.root is None:
        return []

    visited,stack = [],[tree.root]

    while stack:
        node = stack.pop()
        visited.add(node)

        process (node)
        nodes = generate_related_nodes(node)
        stack.push(nodes)
    
    # other processing work
```

```python
def BFS(graph, start, end):
    queue = []
    queue.append([start])
    visited.add(start)

    while queue:
        node = queue.pop()
        visited.add(node)

        process(ndoe)
        nodes = generate_related_nodes(node)
        queue.push(nodes)
```

**回溯法**

回溯法采用试错的思想，它尝试分布的去解决一个问题。在分布解决问题的过程中，当它通过尝试发现现有的分布答案不能得到有效的正确的解答的时候，它将取消上一步甚至是上几步的计算，再通过其它的可能的分布解答再次尝试寻找问题的答案。

回溯法通常用最简单的递归方法来实现，在反复重复上述的步骤后可能出现两种情况：

* 找到一个可能存在的正确的答案
* 在尝试了所有可能的分步方法后宣告该问题没有答案

在最坏的情况下，回溯法会导致一次复杂度为指数时间的计算

### 3.1 剪枝的实现和特性

### 3.2 双向BFS的实现和特性

### 3.3 启发式搜索的实现和特性

**估价函数**

启发式函数：h(n)，它用来评价哪些结点最有希望的是一个我们要找的结点，h(n)会返回一个非负数，也可以认为是从结点n的目标结点路径的估计成本。

启发式函数是一种告知搜索方向的方法。它提供了一种明智的方法来猜测哪个邻居结点会导向一个目标。

## 4. 红黑树和AVL树
### 4.1 树的分类
* 树 Tree

* 二叉树 Binary Tree

* 二叉搜索树 Binary Search Tree
左子树 < 根 < 右子树

### 4.2 树的遍历
1. 前序（Pre-Order）：根-左-右
2. 中序（In-order）:左-根-右
3. 后序（Post-Order）：左-右-根

### 4.3 树的性能
极端条件下退化为一维，变成链表

保证性能的关键
1. 保证二维维度 -> 左右子树节点平衡（recursively）
2. Balance
3. https://en.wikipedia.org/wiki/Self-balancing_binary_search_tree

AVL、B-tree、Red-black tree

### 4.4 AVL树
#### 4.4.1 定义
1. 发明者G.M.Adelson-Velsky和Evgenii Landis
2. Blance Factor（平衡因子）：
   是它的左子树的高度减去它的右子树的高度（有时候相反）。
   balance factor = {-1, 0, 1}

   原因：树的查询时间复杂度跟树的深度有关，平衡因子代表左右树的深度差
3. 通过旋转操作来进行平衡（四种）
4. https://en.wikipedia.org/wiki/Self-balancing binary search tree

#### 4.4.2 旋转操作
**旋转操作**
1. 左旋
2. 右旋
3. 左右旋
4. 右左旋
   
子树挂载方法：
左旋挂左，右旋挂右

#### 4.4.3 AVL总结
1. 平衡二叉搜索树
2. 每个结点存balance factor = {-1, 0, 1}
3. 四种旋转操作

不足：结点需要存储额外信息、且调整次数频繁

### 4.5 红黑树 Red-black Tree
红黑树是一种近似平衡的二叉搜索树（Binary Search Tree）, 它能够确保任何一个结点的左右子树的高度差小于两倍。具体来说，红黑树是满足如下条件的二叉树：
* 每个结点要么是红色的，要么是黑色
* 根结点是黑色
* 每个叶节点（NIL结点，空结点）是黑色的
* 不能有相邻的两个红色结点
* 从任何一结点到其每个叶子的所有路径都包含相同数据的黑色结点

关键性质
从根到叶子的最长的可能路径不多于最短的可能路径的两倍长。

**对比**

* AVL trees provide **faster lookups** than Red Black Trees because they are **more strictly blance.**
* Red Black Trees provide **faster insertion an removal operations** than AVLtrees as fewer rotations are done due to relatively relaxed balancing.
* AVLtrees store balance **factors or heights** with each node, thus requires storage for an integer per node whereas RedBlack Tree requires only 1 bit of information per node.(存储空间)
* RedBlack Trees are used in most of the **language libraries like map，multimap， multisetin C++** whereas AVLtrees are used in **databases** where faster retrievals are required.(读多写少，写多读少)