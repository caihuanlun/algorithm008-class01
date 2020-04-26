# 学习笔记
## 1. 哈希表 Hash table
### 1.1 定义
哈希表（Hash table），也叫散列表，根据关键码值（Key value）而直接进行访问的数据结构。
它通过把关键码值映射到表中的一个位置来访问记录，以加快查找的速度。
这个映射函数叫做散列函数（Hash Function），存放记录的数组叫做哈希表（或散列表）。
### 1.2 工程实践
* 电话号码簿
* 用户信息表
* 缓存（LRU Cache）
* 键值对存储（Redis）
### 1.3 基本特性
|            | 平均         |  最坏        |
| --------   | -----:      | :----:      |
| 空间复杂度   |    O(n)     |     O(n)    |
| 查询        |    O(1)     |     O(n)    |
| 插入        |    O(1)     |     O(n)    |
| 删除        |    O(1)     |     O(n)    |
### 1.4 Java代码示例
Map: key-value,key不重复
```java
    new HashMap();
    new TreeMap();
    map.set(key, value);
    map.get(key);
    map.has(key);
    map.size();
    map.clear();
```

Set：不重复元素的集合
```java
    new HashSet();
    new TheeSet();
    set.add(value);
    set.delete(value);
    set.hash(value);
```

HashMap源码分析：
说明：HashMap在1.8以前是由数组+链表组成的，在1.8以后是由数组+链表+红黑树
**put方法的逻辑**
    1.如果HashMap未被初始化过，则初始化
    2.对Key求Hash值，然后再计算下标
    3.如果没有碰撞，直接放入桶中
    4.如果碰撞，以链表的方式链接到后面
    5.如果链表长度超过阈值，就把链表转成红黑树
    6.如果链表长度低于6，就把红黑树转回链表
    7.如果节点已经存在就替换旧值
    8.如果桶满了（容量16*加载因子0.75），就需要resize（扩容2倍后重排）
```java
    /**
     * Returns the value to which the specified key is mapped,
     * or {@code null} if this map contains no mapping for the key.
     *
     * <p>More formally, if this map contains a mapping from a key
     * {@code k} to a value {@code v} such that {@code (key==null ? k==null :
     * key.equals(k))}, then this method returns {@code v}; otherwise
     * it returns {@code null}.  (There can be at most one such mapping.)
     *
     * <p>A return value of {@code null} does not <i>necessarily</i>
     * indicate that the map contains no mapping for the key; it's also
     * possible that the map explicitly maps the key to {@code null}.
     * The {@link #containsKey containsKey} operation may be used to
     * distinguish these two cases.
     *
     * @see #put(Object, Object)
     */
    public V get(Object key) {
        Node<K,V> e;
        return (e = getNode(hash(key), key)) == null ? null : e.value;
    }

    /**
     * Implements Map.get and related methods.
     *
     * @param hash hash for key
     * @param key the key
     * @return the node, or null if none
     */
    final Node<K,V> getNode(int hash, Object key) {
        Node<K,V>[] tab; Node<K,V> first, e; int n; K k;
        if ((tab = table) != null && (n = tab.length) > 0 &&
            (first = tab[(n - 1) & hash]) != null) {
            if (first.hash == hash && // always check first node
                ((k = first.key) == key || (key != null && key.equals(k))))
                return first;
            if ((e = first.next) != null) {
                if (first instanceof TreeNode)
                    return ((TreeNode<K,V>)first).getTreeNode(hash, key);
                do {
                    if (e.hash == hash &&
                        ((k = e.key) == key || (key != null && key.equals(k))))
                        return e;
                } while ((e = e.next) != null);
            }
        }
        return null;
    }
```

```java
    /**
     * Associates the specified value with the specified key in this map.
     * If the map previously contained a mapping for the key, the old
     * value is replaced.
     *
     * @param key key with which the specified value is to be associated
     * @param value value to be associated with the specified key
     * @return the previous value associated with {@code key}, or
     *         {@code null} if there was no mapping for {@code key}.
     *         (A {@code null} return can also indicate that the map
     *         previously associated {@code null} with {@code key}.)
     */
    public V put(K key, V value) {
        return putVal(hash(key), key, value, false, true);
    }

    /**
     * Implements Map.put and related methods.
     *
     * @param hash hash for key
     * @param key the key
     * @param value the value to put
     * @param onlyIfAbsent if true, don't change existing value
     * @param evict if false, the table is in creation mode.
     * @return previous value, or null if none
     */
    final V putVal(int hash, K key, V value, boolean onlyIfAbsent,
                   boolean evict) {
        Node<K,V>[] tab; Node<K,V> p; int n, i;
        if ((tab = table) == null || (n = tab.length) == 0)
            n = (tab = resize()).length;
        if ((p = tab[i = (n - 1) & hash]) == null)
            tab[i] = newNode(hash, key, value, null);
        else {
            Node<K,V> e; K k;
            if (p.hash == hash &&
                ((k = p.key) == key || (key != null && key.equals(k))))
                e = p;
            else if (p instanceof TreeNode)
                e = ((TreeNode<K,V>)p).putTreeVal(this, tab, hash, key, value);
            else {
                for (int binCount = 0; ; ++binCount) {
                    if ((e = p.next) == null) {
                        p.next = newNode(hash, key, value, null);
                        if (binCount >= TREEIFY_THRESHOLD - 1) // -1 for 1st
                            treeifyBin(tab, hash);
                        break;
                    }
                    if (e.hash == hash &&
                        ((k = e.key) == key || (key != null && key.equals(k))))
                        break;
                    p = e;
                }
            }
            if (e != null) { // existing mapping for key
                V oldValue = e.value;
                if (!onlyIfAbsent || oldValue == null)
                    e.value = value;
                afterNodeAccess(e);
                return oldValue;
            }
        }
        ++modCount;
        if (++size > threshold)
            resize();
        afterNodeInsertion(evict);
        return null;
    }
```

## 2. 树 
LinkedList是特殊化的Tree
Tree是特殊化的Graph（没有环的图就是树）

### 2.1 示例代码
```java
    public classs TreeNode {
        public int val;
        public TreeNode left, right;
        public TreeNode(int val) {
            this.val = val;
            this.left = null;
            this.right = null;
        }
    }
```

```python
    class TreeNode:
        def__init__(self, val):
            self.val = val;
            self.left, self.right = None, None
```

```c++
    struct TreeNode {
        int val;
        TreeNode *left;
        TreeNode *right;
        TreeNode(int x) : val(x), left(NULL), right(NULL) {}
    }
```

### 2.2 二叉树遍历 Pre-order/In-order/Post-order
* 前序（Pre-order）：根-左-右
* 中序（In-order）：左-根-右
* 后序（Post-order）:左-右-根
  树的递归调用实现简单，而循环实现相对较复杂

代码示例：
前序遍历
```java
    if (root != null) {
        res.add(root.val);
        if (root.left != null) helper(root.left, res);
        if (root.right != null) helper(root.right, res);
    }
```
中序遍历
```java
    if (root != null) {
        if (root.left != null) helper(root.left, res);
        res.add(root.val);
        if (root.right != null) helper(root.right, res);
    }
```
后续遍历
```java
    if (root != null) {
        if (root.left != null) helper(root.left, res);
        if (root.right != null) helper(root.right, res);
        res.add(root.val);
    }
```


### 2.3 二叉搜索树 Binary Search Tree
#### 2.3.1 定义
二叉树搜索树，也称二叉排序树、有序二叉树（Ordered Binary Tree）、排序二叉树（Sorted Binary Tree），是指一棵空树或者具有下列性质的二叉树；
1.左子树上所有结点的值均小于它的根结点的值；
2.右子树上所有结点的值均大于它的根节点的值；
3.以此类推：左、右子树也分别为二叉查找树。（重复性）

**中序遍历：升序排列**

### 2.2 基本特性
|            | 平均         |  最坏        |
| --------   | -----:      | :----:      |
| 空间复杂度   |    O(n)     |     O(n)    |
| 查询        |    O(logn)  |     O(n)    |
| 插入        |    O(logn)  |     O(n)    |
| 删除        |    O(logn)  |     O(n)    |

## 3. 堆和二叉堆
### 3.1 堆 Heap 定义
Heap：可以迅速找到一堆数中的最大或最小值的数据结构

将根节点最大的堆叫做大顶堆或大根堆，根节点最小的堆叫做最小堆或小根堆。
常见的堆有二叉堆、斐波那契堆。

假设大顶堆，则常见操作（API）：
find-max：O(1)
delete-max：O(logN)
insert(create)：O(logN) or O(1)

不同的实现比较：https://en.wikipedia.org/wiki/Heap_(data_structure)

### 3.2 二叉堆性质
通过完全二叉树来实现（注意：不是二叉搜索树）；
二叉堆（大顶）它满足下列性质：
【性质一】是一棵完全树。
【性质二】树中任意节点的值总是>=其子节点的值；

#### 3.3 二叉堆实现细节
 1. 二叉堆一般都通过“数组”来实现
 2. 假设“第一个元素”在数组中的索引为0的话，则父节点和子节点的位置关系如下：
   （0） 根节点（堆顶元素）是：a[0]
   （1）索引为i的左孩子索引是（2 * i + 1）
   （2）索引为i的有孩子索引是（2 * i + 2）
   （3）索引为i的父节点索引是floor((i - 1) / 2)

Insert 插入操作 - O(longN)
1. 新元素一律先插入到堆的尾部
2. 依次向上调整整个堆的结构（一直到根即可）
   HeapifyUp

Delete Max 删除堆顶操作 - O(longN)
1. 将堆尾元素替换到顶部（即堆顶被替代删除掉）
2. 依次从根部向下调整整个堆的结构（一直到堆尾即可）
   HeapfiyDown

注意：二叉堆是堆（优先队列priority_queue）的一种常见切简单的实现；但是并不是最优的实现。

```java
    public class HeapSort {
        public void sort(int arr[]) {
            for (int i = n / 2 - 1; i >= 0; i--) {
                heapfiy(arr, n, i);
            }

            for (int i = n - 1; i > 0; i--) {
                int temp = arr[0];
                arr[0] = arr[i];
                arr[i] = temp;

                heapify(arr, i, 0);
            }

            void heapify(int arr[], int n, int i) {
                int largest = i;
                int l = 2 * i + 1;
                int r = 2 * i + 2;

                if (l < n && arr[l] arr[largest]) {
                    largest = l;
                }

                if (r < n && arr[r] > arr[largest]) {
                    largest = r;
                }

                if (largest != i) {
                    int swap = arr[i];
                    arr[i] = arr[largest];
                    arr[largest] = swap;

                    heapfiy(arr, n, largest);
                }
            }

            static void printArray(int arr[]) {
                int n = arr.length;
                for (int i = 0; i < n; i++) {
                    System.out.println(arr[i] + " ");
                }
            }

            public static void main(String args[]) {
                int arr[] = {12, 11, 13, 5, 6, 7};
                int n = arr.length;

                HeapSort ob = new HeapSort();
                ob.sort(arr);

                System.out.println("Sortted array is");
                printArray(arr);
            }
        }
    }
```

## 4. 图的实现和特性
### 4.1 图的属性
* Graph(V, E)
* V - vertex：点
  1.度 - 入度和出度
  2.点与点之间：连通与否
* E - edge：边
  1.有向和无向（单行线）
  2.权重（边长）
### 4.2 图的分类
* 无向无权图
* 有向无权图
* 无向有权图
* 有向有权图
### 4.3 基于图的常见算法
* DFS（图的深度优先遍历）
* BFS（图的广度优先遍历）
  **注意与树的区别，在于树没有环而图有环。**