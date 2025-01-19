# 线性dp

---

https://www.luogu.com.cn/problem/P2679

有两个仅包含小写英文字母的字符串 $A$ 和 $B$。从字符串 $A$ 中取出 $k$ 个互不重叠的非空子串，然后把这 $k$ 个子串按照其在字符串 $A$ 中出现的顺序依次连接起来得到一个新的字符串。请问有多少种方案可以使得这个新串与字符串 $B$ 相等？注意：子串取出的位置不同也认为是不同的方案。
$1≤n≤1000,1≤m≤200,1≤k≤m$。

> `dp[i][j][k][1|0]`：用`A`的前`i`个字符取出`k`个字符组成`B`的前`j`个字符的方案数量，`[1|0]`表示第`i`个字符是否使用

---

[二进制优化多重背包模板题] https://www.luogu.com.cn/problem/P1833

---

https://www.luogu.com.cn/problem/P2340

> `dp[i][j]`：前`i`只牛总智商为`j`的最大总情商

---

https://www.luogu.com.cn/problem/P1854

有$n$行$m$列数，从上往下每一行选一个数，每次只能选上一行选的数的右侧位置的数，求选数的最大总和、每个数在一行中的位置。$n,m\le100$

> `dp[i][j]`：第`i`行选第`j`个数能获得的最大总和
>
> 对于每次选数，枚举选哪个，再枚举上一个数选哪个，转移过来取最大值，`dp[i][j] = max(dp[i - 1][k])`，注意其中`j` `k`的范围，用`path[i][j]`记录`dp[i][j]`取到最大值的时候上一个数选的哪个位置，这样可以通过答案往回找到整个转移路径。

---

[1884. 鸡蛋掉落-两枚鸡蛋 - 力扣（LeetCode）](https://leetcode.cn/problems/egg-drop-with-2-eggs-and-n-floors/description/)

> 法一：`dp[i]`：`i`楼测出答案的最少次数。$dp[i]=\min^i_{j=1}(\max(dp[i-j]+1,j))$
>
> 法二：计算仍$i$次最多能测出多高楼层的答案

---

[P1044  栈 - 洛谷](https://www.luogu.com.cn/problem/P1044)  dp | 记忆化搜索

[P1990 覆盖墙壁 - 洛谷](https://www.luogu.com.cn/problem/P1990)  线性dp

[2266. 统计打字方案数 - 力扣（LeetCode）](https://leetcode.cn/problems/count-number-of-texts/description/) 1857 计数 线性dp

# 记忆化搜索

---

https://www.luogu.com.cn/problem/P4017

给出$n$个节点、$m$条边的有向无环图，求入度为0的节点到出度为0的节点的路径总数量。$n\le5\times10^3,m\le5\times10^5$

> 记忆化搜索 | 拓扑排序

https://leetcode.cn/problems/sort-integers-by-the-power-value/	leetcode 1507

---

# 树型dp

[C-Cidoai的树上方案_牛客练习赛128 (nowcoder.com)](https://ac.nowcoder.com/acm/contest/88880/C)
