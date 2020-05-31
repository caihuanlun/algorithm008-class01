# 学习笔记
## 1. 动态规划
### 1.1 定义
* 定义：https://en.wikipedia.org/wiki/Dynamic_programming
* "Simplifying a complicated problem by breaking it down into simpler sub-problems" (in a recursive manner)
* Divide & Conquer + Optimal substructure (分治 + 最优子结构)

### 1.2 代码模板

递归代码模板：
```python
def recursion(level, param1, parma2, ...):
    # recursion terminator
    if level > MAX_LEVEL:
        # process result
        return
    
    # process logic in current level
    prcess(level, data...)

    # drill down
    self.recursion(level + 1, p1, ...)

    # reverse the current level status if needed
```

```java
public void recursion(int level, int param) {
    // recursion terminator
    if (level > MAX_VALUE) {
        // process result
        return;
    }

    // process logic in current level
    process(level, param);

    // drill dwon
    recursion(level + 1, param);

    // reverse the current level status if needed
}
```
感触
* 人肉递归低效、很累
* 找到最近最简方法，将其拆解成可重复解决的问题
* 数学归纳法思维（抵制人肉递归的诱惑）

本质：寻找重复性 --> 计算机指令集

### 1.3 关键点
* 动态规划和递归或者分治没有根本上的区别（关键看有无最优子结构）
* 共性：找到重复子问题
* 差异性：最优子结构、中途可以淘汰次优解

## 2. 斐波那契数列
### 2.1 代码
```java
int fib (int n) {
    if (n <= 0) {
        return 0;
    } else if (n == 1) {
        return 1;
    } else {
        return fib (n - 1) + fib (n - 2);
    }
}
```

```java
int fib (int n) {
    return n <= 1 ? n : fib (n - 1) + fib(n - 2);
}
```

```java
int fib (int n, int[] memo) {
    iif (n <= 1) {
        return n;
    } 
    
    if (memo[n] == 0) {
        memo[n] = fib (n - 1) + fib (n - 2);
    }

    return memo[n];
}
```

### 2.2 Bottom Up 自底向上
* F[n] = F[n - 1] + F[n - 2]
* a[0], a[1] = 1;
*  for (int i = 2; i <=n; ++i) {
      a[i] = a[i - 1] + a[i - 2];
  }
* a[n]
* 0,1,1,2,3,5,8,13

## 3. Count the paths 路径计数
### 3.1 分解子问题
* paths(start, end) = paths(A, end) + paths(B, end)
* paths(A, end) = paths(D, end) + paths(C, end)
* paths(A, end) = paths(C, end) + paths(E, end)

```java
int countPaths (boolean[][] grid, int row, int col) {
    if (!validSquare(grid, row, col)) return 0;
    if (isAtEnd(grid, row, col)) return 1;
    return countPaths(grid, row + 1, col) 
    + countPaths (grid, row, col + 1);  
}
```
### 3.2 状态转移方程（DP方程）

    opt[i, j] = opt[i + 1, j] + opt[i, j + 1]
    完整逻辑：
    if a[i, j] = '空地'：
        opt[i, j] = opt[i + 1, j] + opt[i, j + 1]
    else:
        opt[i, j] = 0

### 3.3 动态规划关键点
1. 最优子结构 opt[n] = best_of(opt[n - 1], opt[n - 2], ...)
2. 存储中间状态：opt[i]
3. 递推公式（美其名曰：状态转移方程或者DP方程）
   
   Fib:opt[i] = opt[n - 1] + opt[n - 2]

   二维路径：opt[i, j] = opt[i + 1][j] + opt[i][j + 1]（且判断a[i, j]是否空地）

## 4. 最长公共子序列问题
### 4.1 所有的字符串问题大致都能转为为二维数组，再利用动态规划来解决

|     |    A  |   B    |    A   |    Z   |    D   |    C   |
| --- | -----:|:------:|:------:|:------:|:------:|:------:|
| B   |    0  |   1    |   1    |   1    |   1    |   1    |     
| A   |    1  |   1    |   2    |   2    |   2    |   2    |
| C   |    1  |   1    |   2    |   2    |   2    |   3    |
| B   |    1  |   2    |   2    |   2    |   2    |   3    |
| A   |    1  |   2    |   3    |   3    |   3    |   3    |
| D   |    1  |   2    |   3    |   3    |   4    |   4    |

### 4.2 DP方程
    
    if S1[-1] != S2[-1] : LCS[s1, s2] = Max(LCS[s1 - 1, s2], LCS[s1, s2 - 1])

    if S1[-1] == S2[-1] : LCS[s1, s2] = LCS[s1 - 1, s2 - 1] + 1


## 5. 动态规划小结
* 打破自己的思维惯性，形成机器思维
* 理解复杂逻辑的关键
* 同时也是职业进阶的要点要领

## 6. 遗留问题：爬楼梯问题：
1. 1，2，3
2. 相邻两步步数不能相同

## 7.总结
总的来说，DP问题就是三部曲：
* 分解子问题
* 定义状态数组
* DP方程

