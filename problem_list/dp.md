# 一. 动态规划入门

> 感受一下「递推」

[509. 斐波那契数](https://leetcode.cn/problems/fibonacci-number/)

[70. 爬楼梯](https://leetcode.cn/problems/climbing-stairs/)

[746. 使用最小花费爬楼梯](https://leetcode.cn/problems/min-cost-climbing-stairs/)

[P1216 [USACO1.5\] [IOI1994]数字三角形 Number Triangles - 洛谷 | 计算机科学教育新生态 (luogu.com.cn)](https://www.luogu.com.cn/problem/P1216)



# 二. 01背包

[01背包](https://ac.nowcoder.com/acm/problem/226514) 【模板】

[P1048 [NOIP2005 普及组] 采药 - 洛谷 | 计算机科学教育新生态 (luogu.com.cn)](https://www.luogu.com.cn/problem/P1048) 【模板】



# 三. 完全背包

[完全背包](https://ac.nowcoder.com/acm/problem/226516) 【模板】

[P1616 疯狂的采药 - 洛谷 | 计算机科学教育新生态 (luogu.com.cn)](https://www.luogu.com.cn/problem/P1616)



# 四.  不同路径

[63. 不同路径 II](https://leetcode.cn/problems/unique-paths-ii/)

[P1002 [NOIP2002 普及组] 过河卒 - 洛谷 | 计算机科学教育新生态 (luogu.com.cn)](https://www.luogu.com.cn/problem/P1002)



# 五. 线性`dp`

## 子序列问题

### 最长递增子序列 `LIS`

https://ac.nowcoder.com/acm/problem/226831

给你一个长度为n的数组，求最长的严格上升子序列的长度。

>    $f[i] = max(f[j]) + 1,(1\leq j \leq i-1, a_j \le a_i)$

>    优化
>
>    将最长子递增子序列按长度分类，用`p[]`记录长度为$i$的序列末尾元素的最小值（可以证明该数组**严格**递增），对于将要加入的新元素$a_i$，找到末尾元素**小于**（没有等于）$a_i$的最长的子序列（二分查找），该元素可以作为比当前序列长1的序列的最小末尾元素（加入后序列仍然严格递增，因为它只会让后一个序列的最小末尾元素更小），若没有比当前序列长1的序列则会增加一个新长度（更长）的序列，记录长度的最大值。
>
>    新问题：
>
>    至少有多少个严格递增序列？
>
>    最长不严格递增序列？
>
>    至少有多少个不严格递增序列？

```java
import java.util.*;
public class Main {
    static Scanner s = new Scanner(System.in);
    static int n, a[], p[];
    public static void main(String[] args) {
    	n = s.nextInt();
    	a = new int[n + 1];
    	p = new int[n + 2];
    	int len = 0;
    	p[0] = -(int)2e9; //确保第一个数能找到位置（有负数元素）
    	for(int i = 1; i <= n; i++) {
    		a[i] = s.nextInt();
    		int l = 0, r = len, idx = 0;
    		while(l <= r) {
    			int m = (l + r) >>> 1;
    			if(p[m] < a[i]) {
    				idx = m;
    				l = m + 1;
    			}else {
    				r = m - 1;
    			}
    		}
    		p[idx + 1] = a[i];
    		len = Math.max(len, idx + 1);
    	}
    	System.out.print(len);
    }
}
```



### 最大子序列和

[P1115 最大子段和 - 洛谷 | 计算机科学教育新生态 (luogu.com.cn)](https://www.luogu.com.cn/problem/P1115)

给出一个长度为 $n$ 的序列 $a$，选出其中连续且非空的一段使得这段和最大。

```java
import java.util.Scanner;
public class Main {
	public static void main(String[] args) {
        Scanner s = new Scanner(System.in);
        int N = s.nextInt();
        int[] arr = new int[N];
        for (int i = 0; i <=N-1; i++) {
			arr[i] = s.nextInt();
		}
        long[] dp = new long[N];
        dp[0] = arr[0];
        long res = dp[0];
        for (int i = 1; i <=N-1; i++) {
					dp[i] = Math.max(dp[i-1]+arr[i],arr[i]);//对于当前元素加入或者重新计数
					res = Math.max(res, dp[i]);
        }
        System.out.println(res);      
    }
 }
```

### 最长公共子序列

>    若$A_i=B_i$，$dp[i][j] = dp[i-1][j-1]+1$。
>
>    否则，$dp[i][j] = max(dp[i-1][j],dp[i][j-1])$

## P2758编辑距离

https://www.luogu.com.cn/problem/P2758

设 $A$ 和 $B$ 是两个字符串。我们要用最少的字符操作次数，将字符串 $A$ 转换为字符串 $B$。这里所说的字符操作共有三种：

1. 删除一个字符；
2. 插入一个字符；
3. 将一个字符改为另一个字符。

$A, B$ 均只包含小写字母。

>    若$A_i ==B_i$，$dp[i][j] = dp[i-1][j-1]$
>
>    否则，$dp[i][j] = min(dp[i-1][j], dp[i][j-1],dp[i-1][j-1])$（分别对应 删 增 改） 
>
>    **注意：**
>
>    1.    $dp$数组初始化
>    2.    若$A_i \neq B_i$，操作数记得$+1$

```java
import java.util.*;
public class Main {
	static String A, B;
	static int l1, l2, dp[][];
	public static void main(String[] args) {
		Scanner s = new Scanner(System.in);
		A = s.next();
		B = s.next();
		l1 = A.length();
		l2 = B.length();
		dp = new int[l1+1][l2+1];
		for(int i = 1; i<=l2; i++) dp[0][i] = i;
		for(int j = 1; j<=l1; j++) dp[j][0] = j;
		for(int i = 1; i<=l1; i++) {
			for(int j = 1;j<=l2; j++) {
				if(A.charAt(i-1) == B.charAt(j-1)) dp[i][j] = dp[i-1][j-1];
				else dp[i][j] = Math.min(Math.min(dp[i-1][j]+1, dp[i][j-1]+1), dp[i-1][j-1]+1);
			}
		}
		System.out.print(dp[l1][l2]);
	}
}
```



---

https://www.luogu.com.cn/problem/P2679

> `dp[i][j][k][1|0]`：用`A`的前`i`个字符取出`k`个字符组成`B`的前`j`个字符的方案数量，`[1|0]`表示第`i`个字符是否使用

https://www.luogu.com.cn/problem/P1833 [二进制优化多重背包模板题] 

https://www.luogu.com.cn/problem/P2340

> `dp[i][j]`：前`i`只牛总智商为`j`的最大总情商

https://www.luogu.com.cn/problem/P1854

> `dp[i][j]`：第`i`行选第`j`个数能获得的最大总和
>
> 对于每次选数，枚举选哪个，再枚举上一个数选哪个，转移过来取最大值，`dp[i][j] = max(dp[i - 1][k])`，注意其中`j` `k`的范围，用`path[i][j]`记录`dp[i][j]`取到最大值的时候上一个数选的哪个位置，这样可以通过答案往回找到整个转移路径。

[1884. 鸡蛋掉落-两枚鸡蛋 - 力扣（LeetCode）](https://leetcode.cn/problems/egg-drop-with-2-eggs-and-n-floors/description/)

> 法一：`dp[i]`：`i`楼测出答案的最少次数。$dp[i]=\min^i_{j=1}(\max(dp[i-j]+1,j))$
>
> 法二：计算仍$i$次最多能测出多高楼层的答案



[P1044  栈 - 洛谷](https://www.luogu.com.cn/problem/P1044)  dp | 记忆化搜索

[P1990 覆盖墙壁 - 洛谷](https://www.luogu.com.cn/problem/P1990)  线性dp

[2266. 统计打字方案数 - 力扣（LeetCode）](https://leetcode.cn/problems/count-number-of-texts/description/) 1857 计数 线性dp

[P1113 杂务 - 洛谷](https://www.luogu.com.cn/problem/P1113) 黄；不止一种方法。

[2209. 用地毯覆盖后的最少白色砖块 - 力扣（LeetCode）](https://leetcode.cn/problems/minimum-white-tiles-after-covering-with-carpets/description/) 2106

[132. 分割回文串 II - 力扣（LeetCode）](https://leetcode.cn/problems/palindrome-partitioning-ii/description/) 困难



# 六. 区间dp

## P1775 石子合并（弱化版）

https://www.luogu.com.cn/problem/P1775

设有 $N(N \le 300)$ 堆石子排成一排，其编号为 $1,2,3,\cdots,N$。每堆石子有一定的质量 $m_i\ (m_i \le 1000)$。现在要将这 $N$ 堆石子合并成为一堆。每次只能合并相邻的两堆，合并的代价为这两堆石子的质量之和，合并后与这两堆石子相邻的石子将和新堆相邻。合并时由于选择的顺序不同，合并的总代价也不相同。试找出一种合理的方法，使总的代价最小，并输出最小代价。

---

1.    $dp[i][j]$：将$[i,j]$的石子合并的最小花费
2.    $dp[i][j] = min(dp[i][k] +dp[k+1][j])+\sum_i^ja_l,(i+1 \le k \le j-1)$
3.    初始化：$dp[i][j] = INF$

4.    遍历顺序：
      -    枚举长度：$l\in[2, n]$
      -    枚举左端点：$i\in[1,n-l+1]$，则右端点为：$j = i+l - 1$
      -    枚举分割点：$k \in [i,j-1]$，则将$[i,j]$分割为$[i,k] \ \ [k+1,j]$
5.    时间复杂度：$O(n^3)$



[3472. 至多 K 次操作后的最长回文子序列 - 力扣（LeetCode）](https://leetcode.cn/problems/longest-palindromic-subsequence-after-at-most-k-operations/description/) 1884

[D-智乃与长短期主义者博弈_牛客小白月赛110](https://ac.nowcoder.com/acm/contest/101918/D) 

# 七. 计数类dp

# 八. 记忆化搜索

https://www.luogu.com.cn/problem/P1434



[P4017 最大食物链计数 - 洛谷](https://www.luogu.com.cn/problem/P4017) 记忆化搜索 | 拓扑排序

[1387. 将整数按权重排序 - 力扣（LeetCode）](https://leetcode.cn/problems/sort-integers-by-the-power-value/description/) 1507



# 树型dp

[C-Cidoai的树上方案_牛客练习赛128 (nowcoder.com)](https://ac.nowcoder.com/acm/contest/88880/C)

# 期望/概率dp

[F-口吃_牛客周赛 Round 60](https://ac.nowcoder.com/acm/contest/90070/F)

[F-小红的小球染色期望_牛客周赛 Round 79](https://ac.nowcoder.com/acm/contest/100902/F) 
