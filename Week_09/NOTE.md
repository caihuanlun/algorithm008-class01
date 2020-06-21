# 学习笔记
## 1. 递归、分治、回溯、动态规划复习
### 1.1 递归 - 函数自己调用自己
递归代码模板
```java
public void recur(int level, int param) {
    // terminator
    if (level > MAX_LEVEL) {
        // prcess result
        return;
    }

    // process current logic
    process(level, param);

    // drill down
    recur(level:level + 1, newParam);

    // restore current status
}
```
### 1.2 分治
分治代码模板
```python
def devide_conquer(problem, param1, param2, ...):
    # recursion terminator
    if problem is None:
        print_result
        return
    
    # prepare data
    data = perpare_data(problem)
    subproblems = split_problem(problem, data)

    # conquer subproblems
    subresult1 = self.divede_conquer(subproblems[0], p1, ...)
    subresult2 = self.devide_conquer(subproblems[1], p1, ...)
    subresult3 = self.devide_conquer(subproblems[2], p1, ...)
    ...

    # process and generate the final result
    result = process_result(subresult1, subresult2, subresult3, ...)

    # revert the current level states
```

感触
1. 人肉递归低效、很累
2. 找到最近最简方法，将其拆解成可重复解决的问题
3. 数学归纳法思维
   
本质：寻找重复性 --> 计算机指令集

### 1.3 动态规划 Dynamic Programming
1. "Simplifying a complicated problem by breaking it down into simpler sub-problems" (in a recursive manner)
2. Divide & Conquer + Optimal substructure (分治 + 最优子结构)
3. 顺推形式：动态递推

DP 顺推模板
```
function DP():
    dp = [][] #二维情况，DP状态的定义，现实的问题定义成一个数组
    
    for i = 0..M {
        for j = 0..N {
            // DP方程：最小值、最大值、累加累减，从之前k个状态找出最值
            dp[i][j] = _Function(dp[i'][j']...)
        }
    }

    return dp[M][N];
```

**关键点**

动态规划和递归或者分治没有根本上的区别（关键看有无最优的子结构）
拥有共性：找到重复子问题

**差异性：** 最优子结构、中途可以淘汰次优解

**复杂度来源**
1. 状态拥有更多维度（二维、三维、或者更多、甚至需要压缩）
2. 状态方程更加复杂

**本质：** 内功、逻辑思维、数学


## 2. 字符串算法
### 2.1 字符串基础算法
string immutable：https://lemire.me/blog/2017/07/07/are-your-strings-immutable/

遍历字符串
* Python：
```python
for ch in "abbc":
    print(ch)
```
* Java:
```java
String x = "abbc";
for (int i = 0; i < x.size(); i++) {
    char ch = x.charAt(i);
}
```
* C++
```c++
string x("abbc");
for (int i = 0; i < s1.length(); i++) {
    cout << x[i];
}
```

### 2.2 高级字符串算法
最长子串、子序列
1. Longest common sequence（最长子序列）
   
   https://leetcode-cn.com/problems/longest-common-subsequence/
   
   ```
    if (s1[i - 1] == s2[j - 1])
        dp[i][j] = dp[i - 1][j - 1] + 1
    else 
        do[i][j] = max(dp[i - 1][j], dp[i][j - 1])
   ```

2. Longest common substring（最长子串）
   ```
    if (s1[i - 1] == s2[j - 1])
        dp[i][j] = dp[i - 1][j - 1] + 1
    else 
        dp[]i[j] = 0
   ```

3. Edit distance （编辑距离）
   


### 2.3 字符串匹配算法
1. 暴力法（brute force）
2. Rabin-Karp算法
3. KMP算法
   
   https://www.ruanyifeng.com/blog/2013/05/boyer-moore_string_search_algorithm.html

   https://blog.csdn.net/u012505432/article/details/52210975

暴力法
```java
public static int forceSearch(String txt, String pat) {
    int M = txt.length();
    int N = pat.length();

    for (int i = 0; i <= M - N; i++) {
        int j;
        for (j = 0; j < N; j++) {
            if (txt.charAt(i + j) != pat.charAt(j))
                break;
        }
        if (j == N) {
            return i;
        }
    }
    return -1;  
}
```

**Rabin-Karp 算法**

在朴素算法中，我们需要挨个比较所有字符，才知道目标字符串中是否包含子串。那么，是否有别的方法可以用来判断目标字符串是否包含子串呢？

答案是肯定的，确实存在一种更快的方法。为了避免挨个字符对目标字符串和子串进行比较，我们可以尝试一次性判断两者是否相等。因此，我们需要一个好的哈希函数（hash function）。通过哈希函数，我们可以算出子串的哈希值，然后将它和目标字符串中的子串的哈希值进行比较。这个新方法在速度上比暴力法有显著提升。

Rabin-Karp算法的思想：
1. 假设子串的长度为M(part)，目标字符串的长度为N(txt)
2. 计算子串的hash值hash_pat
3. 计算目标字符串txt中每个长度为M的子串的hash值（共需要计算N - M + 1次）
4. 比较hash值：如果hash值不同，字符串必然不匹配；如果hash值相同，还需要使用朴素算法再次判断

```java
public final static int D = 256;
public final static int Q = 9997;

static int RabinKarpSearch(String txt, String pat) {
    int M = pat.length();
    int N = txt.length();
    int i,j;
    int patHash = 0, txtHash = 0;
    for (int i = 0; i < M; i++) {
        patHash = (D * patHash + pat.charAt(i) % Q);
        txtHash = (D * txtHash + txt.charAt(i) % Q);
    }

    int highest = 1; //pow(256, M - 1);
    for (i = 0; i < M - 1; i++) {
        highestPow = (highestPow * D) % Q;
    }

    for (i = 0; i <= N - M; i++) {
        if (patHash == txtHash) {
            for (j = 0; j < M; j++) {
                if (txt.charAt(i + j) != pat.charAt(j))
                    break;
            }

            if (i < N - M) {
                txtHash = (D * (txtHash - txt.charAt(i) * highestPow) + txt.charAt(i + M)) % Q;
                if (txtHash < 0) 
                    txtHash += Q;
            }
        }
    }

    return -1;
}
```

**KMP 算法**

KMP算法（Knuth-Morris-Pratt）的思想就是，当子串与目标字符串不匹配时，其实你已经知道了前面已经匹配成功那一部分的字符（包括子串与目标字符串）。以阮一峰的文章为例，当空格与D不匹配时，你其实知道前面六个字符是“ABCDAB”。KMP算法的想法是，设法利用这个已知信息，不要把“搜索位置”移回已经比较过的位置，继续把它向后移，这样就提高了效率。

https://www.bilibili.com/video/BV1hW411a7ys/?spm_id_from=trigger_reload

http://www.ruanyifeng.com/blog/2013/05/Knuth%E2%80%93Morris%E2%80%93Pratt_algorithm.html

```java
public class KMP {

    /**
     * 求出一个字符数组的next数组
     * @param t 字符数组
     * @return next数组
     */
    public static int[] getNextArray(char[] t) {
        int[] next = new int[t.length];
        next[0] = -1;
        next[1] = 0;
        int k;
        for (int j = 2; j < t.length; j++) {
            k=next[j-1];
            while (k!=-1) {
                if (t[j - 1] == t[k]) {
                    next[j] = k + 1;
                    break;
                }
                else {
                    k = next[k];
                }
                next[j] = 0;  //当k==-1而跳出循环时，next[j] = 0，否则next[j]会在break之前被赋值
            }
        }
        return next;
    }

    /**
     * 对主串s和模式串t进行KMP模式匹配
     * @param s 主串
     * @param t 模式串
     * @return 若匹配成功，返回t在s中的位置（第一个相同字符对应的位置），若匹配失败，返回-1
     */
    public static int kmpMatch(String s, String t){
        char[] s_arr = s.toCharArray();
        char[] t_arr = t.toCharArray();
        int[] next = getNextArray(t_arr);
        int i = 0, j = 0;
        while (i<s_arr.length && j<t_arr.length){
            if(j == -1 || s_arr[i]==t_arr[j]){
                i++;
                j++;
            }
            else
                j = next[j];
        }
        if(j == t_arr.length)
            return i-j;
        else
            return -1;
    }

    public static void main(String[] args) {
        System.out.println(kmpMatch("abcabaabaabcacb", "abaabcac"));
    }

}
```