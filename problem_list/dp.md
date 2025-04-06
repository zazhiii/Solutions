# 一. 动态规划入门

> 一些简单的动态规划问题，感受一下「递推」

[509. 斐波那契数](https://leetcode.cn/problems/fibonacci-number/)

[70. 爬楼梯](https://leetcode.cn/problems/climbing-stairs/)

[746. 使用最小花费爬楼梯](https://leetcode.cn/problems/min-cost-climbing-stairs/)

[P1216 [USACO1.5\] [IOI1994]数字三角形 Number Triangles - 洛谷 | 计算机科学教育新生态 (luogu.com.cn)](https://www.luogu.com.cn/problem/P1216)



# 二. 背包

## 2.1 `01`背包

[01背包](https://ac.nowcoder.com/acm/problem/226514) 【模板】

[P1048 [NOIP2005 普及组] 采药 - 洛谷 | 计算机科学教育新生态 (luogu.com.cn)](https://www.luogu.com.cn/problem/P1048) 【模板】

## 2.2 完全背包

[完全背包](https://ac.nowcoder.com/acm/problem/226516) 【模板】

[P1616 疯狂的采药 - 洛谷 | 计算机科学教育新生态 (luogu.com.cn)](https://www.luogu.com.cn/problem/P1616)

## 2.3 多重背包

[P1833 樱花 - 洛谷](https://www.luogu.com.cn/problem/P1833) [二进制优化多重背包模板题] 

# 三. 线性`dp`

## 经典问题

**子序列问题**

https://ac.nowcoder.com/acm/problem/226831「最长递增子序列 `LIS`」

[P1115 最大子段和 - 洛谷 | 计算机科学教育新生态 (luogu.com.cn)](https://www.luogu.com.cn/problem/P1115) 「最大子序列和」

**最长公共子序列**

>    若$A_i=B_i$，$dp[i][j] = dp[i-1][j-1]+1$。
>
>    否则，$dp[i][j] = max(dp[i-1][j],dp[i][j-1])$

https://www.luogu.com.cn/problem/P2758 「编辑距离」



---

https://www.luogu.com.cn/problem/P2679

> `dp[i][j][k][1|0]`：用`A`的前`i`个字符取出`k`个字符组成`B`的前`j`个字符的方案数量，`[1|0]`表示第`i`个字符是否使用



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

[63. 不同路径 II](https://leetcode.cn/problems/unique-paths-ii/)

[P1002 [NOIP2002 普及组] 过河卒 - 洛谷 | 计算机科学教育新生态 (luogu.com.cn)](https://www.luogu.com.cn/problem/P1002)

https://leetcode.cn/problems/solving-questions-with-brainpower/description/ 

# 四. 区间dp

https://www.luogu.com.cn/problem/P1775

[3472. 至多 K 次操作后的最长回文子序列 - 力扣（LeetCode）](https://leetcode.cn/problems/longest-palindromic-subsequence-after-at-most-k-operations/description/) 1884

[D-智乃与长短期主义者博弈_牛客小白月赛110](https://ac.nowcoder.com/acm/contest/101918/D) 



# 五. 记忆化搜索

https://www.luogu.com.cn/problem/P1434

[P4017 最大食物链计数 - 洛谷](https://www.luogu.com.cn/problem/P4017) 记忆化搜索 | 拓扑排序

[1387. 将整数按权重排序 - 力扣（LeetCode）](https://leetcode.cn/problems/sort-integers-by-the-power-value/description/) 1507



# 六. 树型dp

[C-Cidoai的树上方案_牛客练习赛128 (nowcoder.com)](https://ac.nowcoder.com/acm/contest/88880/C)



# 七. 期望/概率dp

[F-口吃_牛客周赛 Round 60](https://ac.nowcoder.com/acm/contest/90070/F)

[F-小红的小球染色期望_牛客周赛 Round 79](https://ac.nowcoder.com/acm/contest/100902/F) 



# 八. 数位dp

[902. 最大为 N 的数字组合 - 力扣（LeetCode）](https://leetcode.cn/problems/numbers-at-most-n-given-digit-set/description/) 1990

[2719. 统计整数数目 - 力扣（LeetCode）](https://leetcode.cn/problems/count-of-integers/description/) 2355

[2376. 统计特殊整数 - 力扣（LeetCode）](https://leetcode.cn/problems/count-special-integers/description/) 2120

[1012. 至少有 1 位重复的数字 - 力扣（LeetCode）](https://leetcode.cn/problems/numbers-with-repeated-digits/description/) 2230

[P2657 [SCOI2009] windy 数 - 洛谷](https://www.luogu.com.cn/problem/P2657) 蓝

[P3413 SAC#1 - 萌数 - 洛谷](https://www.luogu.com.cn/problem/P3413) 紫；**正难则反**

[600. 不含连续1的非负整数 - 力扣（LeetCode）](https://leetcode.cn/problems/non-negative-integers-without-consecutive-ones/description/) 二进制位的数位dp

https://atcoder.jp/contests/abc387/tasks/abc387_c 1249
