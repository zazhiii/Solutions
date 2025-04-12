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

https://www.luogu.com.cn/problem/P8218【模版】

https://ac.nowcoder.com/acm/problem/14556



## 二维前缀和

https://www.luogu.com.cn/problem/P1719

https://www.luogu.com.cn/problem/P2004



# 前缀异或和

https://leetcode.cn/problems/count-the-number-of-beautiful-subarrays 

# 差分

## 一维差分

[P2367 语文成绩 - 洛谷](https://www.luogu.com.cn/problem/P2367) 【模版】

[P3406 海底高铁 - 洛谷](https://www.luogu.com.cn/problem/P3406) 黄

[D - Coming of Age Celebration](https://atcoder.jp/contests/abc388/tasks/abc388_d) 659（黄）

[P4552 [Poetize6] IncDec Sequence - 洛谷](https://www.luogu.com.cn/problem/P4552) 绿





## 二维差分

https://www.luogu.com.cn/problem/P3397

