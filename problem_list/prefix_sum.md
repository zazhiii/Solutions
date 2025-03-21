---



给你n个数，分别是`a[1],a[2],...,a[n]`。求一个最长的区间`[x,y]`，使得区间中的数`(a[x],a[x+1],a[x+2],...,a[y-1],a[y])`的和能被7整除。输出最长区间长度。若没有符合要求的区间，输出0。$1\le n\le500000$

https://www.luogu.com.cn/problem/P3131

> [前缀和、哈希表]
>
> 用到一个性质$(a-b)\bmod k=0 \Rightarrow a\bmod k=b\bmod k$。
>
> 所以`(a[x] + a[x + 1] + ... + a[y]) % 7 == 0`转换为`pre[y] % k == pre[x - 1] % k`。问题转化为`pre[]`中模7相同的两个最远位置。记录每个余数最左侧的位置，往右遍历的时候若再次遇到这个余数则计算、记录答案。一开始吧`余数0 : 位置0`加入哈希表。**哈希表可以用数组代替。**
>
> https://atcoder.jp/contests/abc367/tasks/abc367_d

---

给三个整数数组`a, b, c`，元素之和均为`tot`，分别在三数组中取三个不相交区间$(l_a,r_a), (l_b,r_b),(l_c,r_c)$，使得每一个区间和均大于等于`tot`。若存在输出$l_a,r_a, l_b,r_b,l_c,r_c$，若不存在则输出$-1$

https://codeforces.com/problemset/problem/1983/C

> [前缀和、二分]
>
> 预处理前缀和，二分找出第一个数组区间`[1,x]`、第三个数组区间`[y, n]`满足题目条件，在计算判断第二个数组的`[x + 1, y - 1]`区间是否满足条件。枚举每一种数组的排列情况(6种)。

---

# 前缀和

## 一维前缀和

### 【模版】P8218求区间和

https://www.luogu.com.cn/problem/P8218

给定 $n$ 个正整数组成的数列 $a_1, a_2, \cdots, a_n$ 和 $m$ 个区间 $[l_i,r_i]$，分别求这 $m$ 个区间的区间和。对于所有测试数据，$n,m\le10^5,a_i\le 10^4$



### NC14556数圈圈

https://ac.nowcoder.com/acm/problem/14556

从a到b。 a到b之间有多少个圈。

>    `a[]` 存每个数的圈的个数，题目变为求区间和。



## 二维前缀和

###  P1719最大加权矩形

https://www.luogu.com.cn/problem/P1719

校长先给他们一个 $n\times n$ 矩阵。要求矩阵中最大加权矩形，即矩阵的每一个元素都有一权值，权值定义在整数集上。从中找一矩形，矩形大小无限制，是其中包含的所有元素的和最大 。矩阵的每个元素属于 $[-127,127]$ ,例如

>    枚举所有矩阵

### P2004领地选择

https://www.luogu.com.cn/problem/P2004

首都被认为是一个占地 $C\times C$ 的正方形。小 Z 希望你寻找到一个合适的位置，使得首都所占领的位置的土地价值和最高。



# 前缀异或和

https://leetcode.cn/problems/count-the-number-of-beautiful-subarrays 

# 差分

## 一维差分

### 【模版】P2367语文成绩

https://www.luogu.com.cn/problem/P2367

语文老师总是写错成绩，所以当她修改成绩的时候，总是累得不行。她总是要一遍遍地给某些同学增加分数，又要注意最低分是多少。你能帮帮她吗？

第一行有两个整数 $n$，$p$，代表学生数与增加分数的次数。

第二行有 $n$ 个数，$a_1 \sim a_n$，代表各个学生的初始成绩。

接下来 $p$ 行，每行有三个数，$x$，$y$，$z$，代表给第 $x$ 个到第 $y$ 个学生每人增加 $z$ 分。



### P3406海底高铁

https://www.luogu.com.cn/problem/P3406

该铁路经过 $N$ 个城市，每个城市都有一个站。不过，由于各个城市之间不能协调好，于是乘车每经过两个相邻的城市之间（方向不限），必须单独购买这一小段的车票。第 $i$ 段铁路连接了城市 $i$ 和城市 $i+1(1\leq i<N)$。如果搭乘的比较远，需要购买多张车票。第 $i$ 段铁路购买纸质单程票需要 $A_i$ 博艾元。

虽然一些事情没有协调好，各段铁路公司也为了方便乘客，推出了 IC 卡。对于第 $i$ 段铁路，需要花 $C_i$ 博艾元的工本费购买一张 IC 卡，然后乘坐这段铁路一次就只要扣 $B_i(B_i<A_i)$ 元。IC 卡可以提前购买，有钱就可以从网上买得到，而不需要亲自去对应的城市购买。工本费不能退，也不能购买车票。每张卡都可以充值任意数额。对于第 $i$ 段铁路的 IC 卡，无法乘坐别的铁路的车。

Uim 现在需要出差，要去 $M$ 个城市，从城市 $P_1$ 出发分别按照 $P_1,P_2,P_3,\cdots,P_M$ 的顺序访问各个城市，可能会多次访问一个城市，且相邻访问的城市位置不一定相邻，而且不会是同一个城市。

现在他希望知道，出差结束后，至少会花掉多少的钱，包括购买纸质车票、买卡和充值的总费用。

对于 $100\%$ 的数据 $M,N\leq 10^5，A_i,B_i,C_i\le10^5$。

>    用差分数组`vis[]` 求出每一段路经过的次数，在比较每一段路买票还是办卡

### P4552IncDec Sequence

https://www.luogu.com.cn/problem/P4552

给定一个长度为 $n$ 的数列 ${a_1,a_2,\cdots,a_n}$，每次可以选择一个区间$[l,r]$，使这个区间内的数都加 $1$ 或者都减 $1$。 

请问至少需要多少次操作才能使数列中的所有数都一样，并求出在保证最少次数的前提下，最终得到的数列有多少种。

>    要让所有$a_i$相等，只需让差分数组除了$b_1$意外的$b_i=0$，对于$i>=2$所有的$b_i$可以进行三种操作：1. 一个数$+1$，另一个数$-1$  2. 单独一个数$+1$ 3. 单独一个数$-1$   。为了使操作数最小，选择先整数$-1$，负数$+1$，使得其中一方为$0$之后，再单独对数$+1$或$-1$，前者操作玩不出花来，后者操作则可以选择单独对某个数$+1$或$-1$的同时再对$b_1$对应$-1$或$+1$。这样就有了“单独操作数 + 1”种结果（不对$b_1$操作也是一种情况）。
>
>    设$b_2,...b_n$中负数总和为`neg`，正数总和`pos`。
>
>    1.    操作次数：
>          -    同时$+1$, $-1$操作数：$min(-neg, pos)$, 单独操作数：$|pos+neg|$
>          -    即：$max(-neg, pos)$
>    2.    情况种数
>          -    $|pos+neg|+1$



## 二维差分

### P3397地毯

https://www.luogu.com.cn/problem/P3397

在 $n\times n$ 的格子上有 $m$ 个地毯。

给出这些地毯的信息，问每个点被多少个地毯覆盖。

第一行，两个正整数 $n,m$。意义如题所述。

接下来 $m$ 行，每行两个坐标 $(x_1,y_1)$ 和 $(x_2,y_2)$，代表一块地毯，左上角是 $(x_1,y_1)$，右下角是 $(x_2,y_2)$。

