# 学习笔记
## 1. 泛型递归、树的递归
### 1.1 递归的实现
递归 Recursion
递归 - 循环
通过函数体来进行的循环

盗梦空间
* 向下进入不同的梦境中，向上又回到原来的一层
* 通过声音同步回到上一层
* 每一层的环境和周围的人都是一份拷贝，主角等几个人穿越不同的梦境（发生和携带变化）
  
计算n!
  n! = 1 * 2 * * 4 * 5 ... n
```python
def Factorial(n):
    if n <= 1:
        return 1
    return n * Factorial(n - 1)
```
  
### 1.2 代码模板

```python
def recursion(level, param1, param2, ...)
    # recursion terminator
    if level > MAX_LEVEL:
        process_result
        return

    # process logic in current level
    process(level, data...)

    # drill down
    self.process(level + 1, p1...)

    # reverse the current level status if needed 
```

```java
public void recur(int level, int param) {
    // terminator
    if (level > MAX_LEVEL) {
        // process result
        return;
    }


    // process current logic
    process(level, param);

    // drill down
    recur(level:level + 1, newParam);

    // restore current status
        
}
```

### 1.3 递归的思维要点
* 不要人肉进行递归（最大误区）
* 找到最近最简方法，将其拆解成可重复解决的问题（重复子问题）
* 数学归纳法思维

## 2. 分治、回溯
### 2.1 分治 Divide & Conquer
代码模板：
```python
def divide_conquer(problem, param1, param2, ...):
    # recursion terminator
    if problem is None:
        print_result
        return
    # prepare data
    data = prepare_data(problem)
    subproblems = split_problem(problem, data)
    # conquer subproblems
    subresult1 = self.divide_conqure(subproblems[0], p1, ...)
    subresult2 = self.divide_conqure(subproblems[1], p1, ...)
    subresult3 = self.divide_conqure(subproblems[2], p1, ...)
    ...
    # prcess and generate the final result
    result = process_result(subresult1, subresult2, subresult3, ...)
```
### 2.2 回溯 Backtracking
#### 2.2.1 定义
回溯采用试错的思想，它尝试分步的去解决一个问题。在分步解决问题的过程中，当它通过尝试发现现在有的分步答案不能得到有效的正确的解答的时候，它将取消上一步甚至是上几步的计算，再通过其它的可能的分步解答再次尝试寻找问题的答案。
回溯法通常用最简单的递归方法来实现，在反复重复上述步骤后可能出现两种情况：
* 找到一个可能存在的正确的答案；
* 在尝试了所有可能的分步方法后宣告该问题没有答案。
  在最坏的情况，回溯法导致一次复杂度为指数时间的计算。

## 总结
掌握递归、分治、回溯的模板，并熟练使用四步法：1.terminator --> 2.process current logic --> 3.drill down --> 4.reverse current status


