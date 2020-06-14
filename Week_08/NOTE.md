# 学习笔记
## 1. 位运算
### 1.1 位运算符
为什么需要位运算
* 机器里的数字表示方式和存储格式就是二进制
* 十进制 <--> 二进制：如何转换？
  https://zh.wikipedia.org/wiki/%E4%BA%8C%E8%BF%9B%E5%88%B6#%E5%8D%81%E8%BF%9B%E6%95%B8%E8%BD%AC%E6%88%90%E4%BA%8C%E8%BF%9B%E6%95%B8
* 4(d):0100
  8(d):01000
  5(d):0101
  6(d):0110

### 1.2 算数移位与逻辑移位
位运算符
|     含义       |    运算符    |         示例           |
| --------      | ---------:  | :--------------:       |
|    左移        |     <<      |     0011 => 0110       |
|    右移        |     >>      |     0110 => 0011       |
|    按位或      |     l       |     0011 => 1011 1011  |
|    按位与      |     &       |     0011 => 0011 1011  |
|    按位取反     |     ~       |     0011 => 1100      |
|    按位异或     |     ^       |     0011 => 1000 1011 |

XOR - 异或
异或：相同为0，不同为1.也可用“不进位加法”来理解。
异或操作的一些特点：
* x ^ 0 = x
* x ^ 1s = ~x // 注意1s = ~0
* x ^ (~x) = 1s
* c = a ^ b => a ^ c = b, b ^ c = a //交换两个数
* a ^ b ^ c = a ^ (b ^ c) = (a ^ b) ^ c //associative

### 1.3 位运算的应用
指定位置的位运算
1. 将x最右边的n位清零：x & (~0 << n)
2. 获取x的第n位值(0或者1): (x >> n) & 1
3. 获取x的第n位的幂值：x & (1 << n)
4. 仅将第n位置为1：x | (1 << n)
5. 仅将第n位置为0：x & (~(1 << n))
6. 将x最高位至第n位（含）清零：x & ((1 << n) - 1)
   
实战位运算要点
* 判断奇偶：
  * x % 2 == 1 --> (x & 1) == 1 
  * x % 2 == 0 --> (x & 1) == 0
* x >> 1 --> x / 2
  * 即：x = x / 2; --> x = x >> 1;
  * mid = (left + right) / 2; --> mid = (left + right) >> 1;
* x = x & (x - 1)清零最低位的1
* x & -x => 得到最低位的1
* x & ~x => 0

## 2. 布隆过滤器和LRU缓存
### 2.1 布隆过滤器的实现及应用 Bloom Filter
Bloom Filter vs Hash Table
一个很长的二进制向量和一系列随机映射函数。布隆过滤器可以用于检索一个元素是否在一个集合中。

优点是空间效率和查询时间远远超过一般的算法，缺点是有一定的误识别率和删除困难。

案例
1. 比特币网络
2. 分布式系统（Map-Reduce） -- Hadoop、search engine
3. Redis缓存
4. 垃圾邮件、评论等的过滤

科普：https://www.cnblogs.com/cpselvis/p/6265825.html
https://blog.csdn.net/tianyaleixiaowu/article/details/74721877

### 2.2 LRU Cache的实现、应用和题解
Cache缓存
1. 记忆
2. 钱包 - 储物柜
3. 代码模块

LRU Cache
* 两个要素：大小、替换策略
* Hash Table + Double LinkedList
* O(1)查询
  O(1)修改、更新

替换策略
* LFU - least frequently used
* LRU - least recently used
替换算法总览：
https://en.wikipedia.org/wiki/Cache_replacement_policies

## 3. 排序算法
1. 比较类排序
   通过比较来决定元素间的相对次序，由于其时间复杂度不能突破O(nlogn)，因此也称为非线性时间比较类排序。
2. 非比较类排序：
   不通过比较来决定元素间的相对次序，它可以突破基于比较排序的时间下界，以线性时间非比较类排序。

排序算法
比较类排序
* 交换排序
  * 冒泡排序
  * 快速排序
* 插入排序
  * 简单插入排序
  * 希尔排序
* 选择排序
  * 简单选择排序
  * 堆排序
* 归并排序
  * 二路归并排序
  * 多路归并排序
  
非比较类排序
* 计数排序
* 桶排序
* 基数排序
  
|排序方法|时间复杂度（平均）|时间复杂度（最坏）|时间复杂度（最好）|空间复杂度| 稳定性   |
| ----- |:------------:| :-----------: |:------------: | :-----: | :-----: |
|插入排序|    O(n^2)    |    O(n^2)     |      O(n)     |   O(1)  |   稳定   |
|希尔排序|    O(n^1.3)  |    O(n^2)     |      O(n)     |   O(1)  |  不稳定  |
|选择排序|    O(n^2)    |    O(n^2)     |      O(n^2)   |   O(1)  |  不稳定  |
| 堆排序 |   O(nlog2n)  |   O(nlog2n)   |    O(nlog2n)  |   O(1)  |  不稳定  |
|冒泡排序|    O(n^2)    |    O(n^2)     |      O(n)     |   O(1)  |   稳定   |
|快速排序|   O(nlog2n)  |    O(n^2)     |     O(nlog2n) |O(nlog2n)|  不稳定  |
|归并排序|   O(nlog2n)  |   O(nlog2n)   |    O(nlog2n)  |   O(n)  |   稳定   |
|       |              |               |               |         |         |
|计数排序|              |               |               |         |         |
| 桶排序 |              |               |               |         |         |
|基数排序|              |               |               |         |         |

初级排序 - O(n^2)
1. 选择排序（Selection Sort）
   每次找最小值，然后放到待排序数组的起始位置。
2. 插入排序（Insertion）
   从前到后逐步构建有序序列；对于未排序数据，在已排序序列中从后向前扫描，找到相应位置并插入。
3. 冒泡排序（Bubble Sort）
   嵌套循环，每次查看相邻的元素如果逆序，则交换。

高级排序 - O(N*LogN)
* 快速排序（Quick Sort）
  数组取标杆pivot，将小元素放povit左边，大元素放右侧，然后依次对右边和右边的子数组继续快排；以达到这个序列有序。
* 归并排序（Merge Sort）- 分治
  1.把长度为n的输入序列分成两个长度为n/2的子序列
  2.对这两个子序列分别采用归并排序；
  3.将两个排序好的子序列合并成一个最终的排序序列。
* 堆排序（Heap Sort）- 堆插入O(longN)，取最大/小值（O(1)）
  1.数组元素一次建立小顶堆
  2.依次取堆顶元素，并删除

归并和快排具有相似性，但是步骤顺序相反
  归并：先排序左右子数组，然后合并两个有序子数组
  快排：先调配处左右子数组，然后对于左右子数组进行排序

快速排序
  ```java
  public static void quickSort(int[] array, int begin, int end) {
      if (end <= begin) return;
      int pivot = partition(array, begin, end);
      quickSort(array, begin, pivot - 1);
      quickSort(array, pivot + 1, end);
  }

  static int partition(int[] a, int begin, int end) {
      // pivot：标杆位置，counter：小于pivot的元素的个数
      int pivot = end, counter = begin;
      for (int i = begin; i < end; i++) {
          if (a[i] < a[povit]) {
              int temp = a[counter];
              a[counter] = a[i];
              a[i] = temp;
              counter++;
          }
          int temp = a[povit];
          a[povit] = a[counter];
          a[counter] = temp;
          return counter;
      }
  }
  ```
  调用方式：quickSort(a, 0, a.length - 1)

  归并排序
  ```java
  public static void mergeSort(int[] array, int left, int right) {
      if (right <= left) return;
      int mid = (left + right) >> 1; // (left + right) / 2

      mergeSort(array, left, mid);
      mergeSort(array, mid + 1, right);
      merge(array, left, mid, right);
  }

  public static void merge(int[] arr, int left, int mid, int right) {
      int[] temp = new int[right - left + 1];// 中间数组
      int i = left, j = mid + 1, k = 0;

      while (i <= mid && j <= right) {
          temp[k++] = arr[i] <= arr[j] ? arr[i++] : arr[j++];
      }

      while (i <= mid) temp[k++] = arr[i++];
      while (j <= right) temp[k++] = arr[j++];

      for (int p = 0; p < temp.length; p++) {
          arr[left + p] = temp[p];
      }
      // 也可以用System.arraycopy(a, start1, b, start2, length)
  }
  ```

  堆排序
  ```java
  static void heapify(int[] arry, int length, int i) {
      int left = 2 * i + 1, right = 2 * i + 2;
      int largest = i;

      if (left < length && array[left] > array[largest]) {
          largest = leftChild;
      }
      if (right < length && array[right] > array[largest]) {
          largest = right;
      }
      if (largest != i) {
          int temp = array[i];
          array[i] = array[largest];
          array[largest] = temp;
          heapify(array, length, largest);
      }
  }

  public static void heapSort(int[] array) {
      if (array.length == 0) return;

      int length = array.length;
      for (int i = length / 2 - 1; i >= 0; i--) {
          heapify(array, length, i);
      }

      for (int i = lenght - 1; i >= 0; i--) {
          int temp = array[0];
          array[0] = array[i];
          array[i] = temp;
          heapify(array, i, 0);
      }
  }
  ```

特殊排序
* 计数排序（Counting Sort）
  计数排序需要输入的数据必须是有确定范围的整数。将输入的数据值转化为键存储在额外开辟的数组空间中；然后依次把计数大于1的填充回原数组。
* 桶排序（Bucket Sort）
  桶排序（Bucket Sort）的工作原理：假设输入数据服从均匀分布，将数据分到有限的桶里，每个桶在分别排序（有可能再使用别的排序算法或是以递归方式继续使用桶排序进行排）。
* 基数排序（Radix Sort）
  基数排序是按照地位先排序，然后收集；再按照高位排序，然后再收集；依次类推，直到最高位。有时候有些属性是有优先级顺序的，先按低优先级排序，再按高优先级排序。


