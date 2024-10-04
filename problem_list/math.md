

# 质数

## 质数筛

https://www.luogu.com.cn/problem/P5736

输入 $n$ 个不大于 $10^5$ 的正整数。要求全部储存在数组中，去除掉不是质数的数字，依次输出剩余的质数。

---

求$1\sim n$中质因数个数大于2的数的个数。

[A-数数_牛客练习赛129 (nowcoder.com)](https://ac.nowcoder.com/acm/contest/90074/A)

> 筛出$1\sim n$中的质数，再将这些质数的倍数的位置标记 + 1，再遍历一遍，计数标记大于1的位置。

---

P5723 质数口袋

https://www.luogu.com.cn/problem/P5723

小 A 有一个质数口袋，里面可以装各个质数。他从 $2$ 开始，依次判断各个自然数是不是质数，如果是质数就会把这个数字装入口袋。

口袋的负载量就是口袋里的所有数字之和。

但是口袋的承重量有限，装的质数的和不能超过 $L$。给出 $L$，请问口袋里能装下几个质数？将这些质数从小往大输出，然后输出最多能装下的质数的个数，数字之间用换行隔开。

---

## 判断质数





## 质数分解

P10411树色尤含残雨

https://www.luogu.com.cn/problem/P10411?contestId=166040

有一个正整数 $x$。每次操作可以选择 $p_1,\alpha_1,p_2,\alpha_2$ 满足 $p_1,p_2$ 为两不同质数且 $\alpha_1,\alpha_2$ 为正整数，若 $x$ 是 $p_1^{\alpha_1}p_2^{\alpha_2}$ 的整数倍，就将 $x$ 除以 $p_1^{\alpha_1}p_2^{\alpha_2}$，否则操作无效。

请你求出通过若干次操作可以得到的最小的 $x$。

> 分解为下列形式：
>
> $n = p_1^{\alpha_1}\times p_2^{\alpha_2}\times ...\times p_k^{\alpha_k}$
>
> $p_1,p_2,...,p_k$为从小到大的质数
>
> 若$ \alpha_1 + \alpha_2 + ... + \alpha_k = k$ 且 $k$ 为奇数则答案为$p_1$
>
> 其他情况答案均为 $1$

```java
import java.io.*;
import java.util.*;
public class Main {
	static Scanner sc = new Scanner(System.in);
	static PrintWriter pw = new PrintWriter(new BufferedWriter(new OutputStreamWriter(System.out)));
	public static void main(String[] args) throws IOException{
		int n = sc.nextInt();
		int k = n;
		int pcnt = 0, ecnt = 0, minp = (int) 1e9;
		for(int i = 2; i<=n/i; i++) {
			if(n % i == 0) {
				pcnt ++;
				minp = Math.min(minp, i);
				while(n % i == 0) {
					n /= i;
					ecnt ++;
				}
			}
		}
		if(n != 1) {pcnt ++; ecnt ++;}
		if(pcnt == 1) {
			pw.print(k);
		}else {
			pw.print(pcnt == ecnt && pcnt % 2 != 0 ? minp : 1);	
		}		
		pw.flush();
	}
}

```



# 概率

---

[1227. 飞机座位分配概率 - 力扣（LeetCode）](https://leetcode.cn/problems/airplane-seat-assignment-probability/description/)

> 定义$f(n)$为第 `n` 位乘客坐在自己的座位上的概率。
>
> 若$n=1,f(n)=1$
>
> 若$n\ge2$：
>
> - 若第一个人坐在1位置，那么后面的所有人都能做到自己的位置，概率：$\frac{1}{n}$
> - 若第一个人坐在$i$位置，那么$2\sim i-1$可以做自己的位置，$i+1\sim n$这些人又变成了问题$f(n-i)$
>
> 那么$f(n)=\frac{1}{n}+\frac{1}{n}\times\sum_2^{n-1}f(i)$, 可以化简为$f(n)=f(n-1)$，又$f(2)=\frac{1}{2}$，那么$f(n)=\frac{1}{2}$

---
