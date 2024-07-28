# Codeforces Round 944 (Div. 4)

**C. Clock and Strings**

[Problem - C - Codeforces](https://codeforces.com/contest/1971/problem/C)

如下图所示，有一个时钟，上面按顺时针顺序标有 $$1$$$ 到 $$$12$$ 的数字。

![](https://espresso.codeforces.com/61e348551b1e468c2730b6e3b36e33b76e3132ca.png)

在本例中， $$(a,b,c,d)=(2,9,10,6)$$ 和字符串相交。

有四个**不同的**整数 $a$$$ 、 $$$b$$$ 、 $$$c$$$ 、 $$$d$$$ ，且不大于 $$$12$$$ 。爱丽丝用红色字符串连接 $$$a$$$ 和 $$$b$$$ ，鲍勃用蓝色字符串连接 $$$c$$$ 和 $$$d$ 。这两条线相交吗？(字符串是直线段）。

> 从$a，b$中较小值遍历到较大值，若其中包含$c,d$中的0或2个则不相交；反之则相交
>
> 注意：处理从12 到 1点

```java
import java.io.*;
import java.util.*;
public class Main {
    static Scanner sc = new Scanner(System.in);
    static PrintWriter pw = new PrintWriter(new BufferedWriter(new OutputStreamWriter(System.out)));
    static int T, a, b, c, d;
    public static void main(String[] args) throws IOException {
    	T = sc.nextInt();
    	while(T --> 0) {
    		a = sc.nextInt();b = sc.nextInt();
    		c = sc.nextInt();d = sc.nextInt();
    		int s = Math.min(a, b);
    		int e = Math.max(a, b);
    		int k = s, ans = 0;
    		for(int i = s; i<=e; i++) {
    			if(k == c || k == d) ans ++;
    			k ++;
    			if(k == 13) k = 1;
    		}
    		pw.println(ans == 0 || ans == 2? "NO":"YES");
    	}
    	pw.flush();
    }
}
```



**D. Binary Cut**

[Problem - D - Codeforces](https://codeforces.com/contest/1971/problem/D)

给你一个01串。请找出最少需要切割成多少个片段，以便将得到的片段重新排列成一个有序的二进制字符串。

![](https://espresso.codeforces.com/017a3c6905b518ac44137d28698d74b2aa8782ea.png)

> 若有0连着1的片段则其中一个这种片段不用切割，其他的0或1片段需要切割；
>
> 统计0片段和1片段的数量`cnt`
>
> 1. 有0连1的 ans = cnt - 1
> 2. 没有0连1的 ans = cnt

```java
import java.io.*;
import java.util.*;
public class Main {
	static Scanner sc = new Scanner(System.in);
	static PrintWriter pw = new PrintWriter(new BufferedWriter(new OutputStreamWriter(System.out)));

	static int T;
	public static void main(String[] args) throws IOException{
		T = sc.nextInt();
		while( T --> 0 ) {
			char[] s = sc.next().toCharArray();
			boolean f = false;
			int cnt = 0;
			for(int i = 0; i<s.length - 1; i++) {
				if(s[i] != s[i + 1]) cnt ++;
				if(s[i] == '0' && s[i + 1] == '1') f = true;
			}
			pw.println(f ? cnt:cnt + 1);
		}
		pw.flush();
	}
}

```



**E. Find the Car**

[Problem - E - Codeforces](https://codeforces.com/contest/1971/problem/E)

铁木尔乘坐的汽车在数线上从 $$$0$$$ 点行驶到 $$$n$$$ 点。汽车在 $$$0$$$ 分钟时从 $$$0$$$ 点开始行驶。

在$0, a_1, a_2, \dots, a_k$$ 点的数线上有 $$$k+1$$$ 个标志，帖木儿知道汽车将分别在$$0, b_1, b_2, \dots, b_k$$ 分钟到达那里。序列 $$$a$$$ 和 $$$b$$$ 与 $ $a_k = n$ 严格递增。

![](https://espresso.codeforces.com/f47fa2c499bddfe3b55a4bc380f760025cccdd9e.png)

在任意两个相邻的标志牌之间，汽车以**恒速**行驶。帖木儿有 $$q$$$ 个查询：每个查询都是一个整数 $$$d$$$ ，帖木儿希望你输出汽车到达点 $$$d$$ 需要多少分钟，**四舍五入为最接近的整数**。

> 二分查找刚好小于等于$d$的位置$a_i$，加上该位置的时间$b_i$，再计算该位置到$d$的时间加上($\frac{d - a_i}{\frac{a_{i+1}-a_i}{b_{i+1}-b_i}}$)

```java
package algorithm;

import java.io.*;
import java.util.*;
public class Main {
	static Scanner sc = new Scanner(System.in);
	static PrintWriter pw = new PrintWriter(new BufferedWriter(new OutputStreamWriter(System.out)));
	static Read rd = new Read();
	static int T, n, k, q, a[], b[], d;
	static double eps = 0.000000000000000000000000000000000000000000000000000;
	public static void main(String[] args) throws IOException{
		T = rd.nextInt();
		while( T --> 0 ) {
			n = rd.nextInt();
			k = rd.nextInt();
			q = rd.nextInt();
			a = new int[k + 1];
			b = new int[k + 1];
			for(int i = 1; i<=k; i++) a[i] = rd.nextInt();
			for(int j = 1; j<=k; j++) b[j] = rd.nextInt();
			
			while(q --> 0) {
				long ans = 0;
				d = rd.nextInt();
				int l = 0, r = k, i = 0;
				while(l <= r) {
					int m = (l + r) >>> 1;
					if(a[m] <= d) {
						i = m;
						l = m + 1;
					}else {
						r = m - 1;
					}
				}
				ans = b[i];
				if(i != k) {
                    //坑死了。。
					pw.print((ans + 1l * (d - a[i] ) * (b[i + 1] - b[i]) / (a[i + 1] - a[i]) )+ " ");
				}else {
					pw.print(ans + " ");
				}
				
			}
			pw.println();
		}
		pw.flush();
	}
}
class Read{
	StreamTokenizer st = new StreamTokenizer(new BufferedReader(new InputStreamReader(System.in)));
	public int nextInt() throws IOException{
		st.nextToken();
		return (int)st.nval;
	}
}
```



### **F - Circle Perimeter**

[F - Circle Perimeter](https://codeforces.com/contest/1971/problem/F)

# Codeforces Round 957 (Div. 3)

[C - Gorilla and Permutation](https://codeforces.com/contest/1992/problem/C)

给出$n, m,k \ (m<k)$。构造$n$的排列。

$g(i)$表示前$i$个数中小于等于$m$的数的和。

$f(i)$表示前$i$个数中大于等于$k$的数的和。

构造$n$的排列使得$\sum_{i=1}^nf(i)-\sum_{i=1}^ng(i)$最大。

> 贪心、构造

> 式中$f(i)$取得正，$g(i)$取的负。所以将大于等于$k$的数中较大的尽量往前方放，将小于等于$m$中的数的较大者尽量往后放。最终构造出：$[n,n-1,...,k,...,1,2,...,m]$
>
> **直接输出即可**
>
> $O(n)$

```java
import java.io.*;
import java.util.*;

public class Main {
    static Scanner sc = new Scanner(System.in);
    static PrintWriter pw = new PrintWriter(new BufferedWriter(new OutputStreamWriter(System.out)));
    public static void solve(){
        int n, m, k;
        n = sc.nextInt();
        m = sc.nextInt();
        k = sc.nextInt();
        for(int i = n; i >= m + 1; i --) pw.print(i + " ");
        for(int i = 1; i <= m; i ++) pw.print(i + " ");
        pw.println();
    }
    public static void main(String[] args) throws IOException {
        int T = sc.nextInt();
        while(T --> 0) solve();
        pw.flush();pw.close();
    }
}
```

[D - Test of Love](https://codeforces.com/contest/1992/problem/D)

给出$n,m,k$。表示河道长度，最远跳跃距离，最多游泳距离。

河道用$L, C, W$表示木桩，鳄鱼，水。

在木桩上和起点能跳跃，在水中能游泳，不能在有鳄鱼的地方。

是否能在$k$次游泳之内从起点$0$到达终点$n+1$

> dp

> 1. $dp[i]$：到第$i$个位置最少需要游泳多少距离
> 2. 初始化$dp[0]=0, dp[i] =inf(i>0)$
> 3. 状态转移（两种方式）：
>    1. 可以从前面有木桩的地方或起点(距离$m$以内)或前一格有水的地方转移到当前位置
>    2. 若当前为起点或木桩，可以从当前状态转移到前面(距离$m$以内)地方。
> 4. 判断$dp[n+1]$是否小于$k$即可。

```java
import java.io.*;
import java.util.*;

public class Main {
    static Scanner sc = new Scanner(System.in);
    static PrintWriter pw = new PrintWriter(new BufferedWriter(new OutputStreamWriter(System.out)));
    public static void solve(){
        int n, m, k, inf = (int)2e9;
        n = sc.nextInt();
        m = sc.nextInt();
        k = sc.nextInt();
        char[] c = sc.next().toCharArray();
        int dp[] = new int[n + 2];
        Arrays.fill(dp, inf);
        dp[0] = 0;
        for(int i = 1; i <= n + 1; i ++){
            if(i <= n && c[i - 1] == 'C') continue;
            dp[i] = dp[i - 1] + 1;
            for(int j = 1; j <= m && i - j >= 0; j ++){
                if(i - j == 0 || c[i - j - 1] == 'L') dp[i] = Math.min(dp[i], dp[i - j]);
            }
        }
        pw.println(dp[n + 1] <= k ? "YES" : "NO");
    }
    public static void main(String[] args) throws IOException {
        int T = sc.nextInt();
        while(T --> 0) solve();
        pw.flush();pw.close();
    }
}
---------------------------------------------------------------------
import java.io.*;
import java.util.*;

public class Main {
    static Scanner sc = new Scanner(System.in);
    static PrintWriter pw = new PrintWriter(new BufferedWriter(new OutputStreamWriter(System.out)));
    public static void solve(){
        int n, m, k, inf = (int)2e9;
        n = sc.nextInt();
        m = sc.nextInt();
        k = sc.nextInt();
        char[] c = sc.next().toCharArray();
        int dp[] = new int[n + 2];
        Arrays.fill(dp, inf);
        dp[0] = 0;
        for(int i = 0; i < n + 1; i ++){
            if(dp[i] == inf) continue;
            if(i == 0 || c[i - 1] == 'L'){
                for(int j = 1; j <= m && i + j <= n + 1; j ++){
                    dp[i + j] = Math.min(dp[i], dp[i + j]);
                }
            }else if(c[i - 1] == 'W'){
                dp[i + 1] = Math.min(dp[i] + 1, dp[i + 1]);
            }
        }
        pw.println(dp[n + 1] <= k ? "YES" : "NO");
    }
    public static void main(String[] args) throws IOException {
        int T = sc.nextInt();
        while(T --> 0) solve();
        pw.flush();pw.close();
    }
}

```

# [Codeforces Round 958 (Div. 2)](https://codeforces.com/contest/1988)

[Problem - C - Codeforces](https://codeforces.com/contest/1988/problem/C)

给定正整数 $n$. 构造**最长**的序列 $a=[a_1,a_2,\ldots,a_k]$ 满足:

- $a_i\le n$ for all $1\le i\le k$.
- $a$ 严格递增
- $a_i\,|\,a_{i-1}=n$ for all $2\le i\le k$, |代表按位异或

> 位运算， lowbit

> 注意到要序列**最长**，从最大数开始构造，最大那个数一定是$n$本身，次大值一定为$n - lowbit(n)$一次类推，可以构造出长度为$bitcount(n) + 1$的序列。

> $O(n + n\log n)$瓶颈在排序。

```java
import java.io.*;
import java.util.*;

public class Main {
    static Scanner sc = new Scanner(System.in);
    static PrintWriter pw = new PrintWriter(new BufferedWriter(new OutputStreamWriter(System.out)));
    public static void solve(){
        long n = sc.nextLong();
        Vector<Long> ans = new Vector<>();
        long x = n;
        while(x > 0){
            long e = n - (x & -x);
            if(e != 0) ans.add(e);
            x -= (x & -x);
        }
        ans.add(n);
        Collections.sort(ans);
        pw.println(ans.size());
        for(long e : ans) pw.print(e + " ");
        pw.println();
    }
    public static void main(String[] args) throws IOException {
        int T = sc.nextInt();
        while(T --> 0){solve();}
        pw.flush();pw.close();
    }
}
```

# [Codeforces Round 962 (Div. 3)](https://codeforces.com/contest/1996)

[Problem - C - Codeforces](https://codeforces.com/contest/1996/problem/C)

给你两个字符串 $a$ 和 $b$ 长度为 $n$。对于 $q$  次询问。

每次询问，给定 $l$ 和 $r$. 在一次操作中可以选择一个 $i$ ($l \leq i \leq r$) 使 $a_i$ 变为任何一个字母. 输出最小操作次数使得 $\texttt{sorted(a[l..r])} = \texttt{sorted(b[l..r])}$. 

> 前缀和

> 意思就是使区间$l\sim r$中每一种字母的个数变为相等。用$26$个前缀和数组记录每个字母在该区间的数量，计算两个字符串在该区间的每个字母数量的差值的总和。答案为总差值的一半。（改一个字母可以消除两点差值）

> $O(T\times26\times(n + q))$

```java
import java.io.*;
import java.util.*;

public class Prac {
    static Scanner sc = new Scanner(System.in);
    static PrintWriter pw = new PrintWriter(new BufferedWriter(new OutputStreamWriter(System.out)));
    
    static int n, q, pre1[][], pre2[][];
    static char a[], b[];
    public static void solve(){
        n = sc.nextInt();
        q = sc.nextInt();
        a = sc.next().toCharArray();
        b = sc.next().toCharArray();
        pre1 = new int[n + 1][26];
        pre2 = new int[n + 1][26];
        for(int i = 1; i <= n; i ++){
            for(int j = 0; j < 26; j ++){
                pre1[i][j] = pre1[i - 1][j];
                pre2[i][j] = pre2[i - 1][j];
            }
            pre1[i][a[i - 1] - 'a'] ++;
            pre2[i][b[i - 1] - 'a'] ++;
        }
        while(q --> 0){
            long ans = 0;
            int l = sc.nextInt();
            int r = sc.nextInt();
            l --;
            for(int i = 0; i < 26; i ++){
                ans += Math.abs(pre1[r][i] - pre1[l][i] - (pre2[r][i] - pre2[l][i]));
            }
            pw.println(ans / 2);
        }
    }
    public static void main(String[] args) throws IOException {
        int T = sc.nextInt();
        while(T --> 0) solve();
        pw.flush();pw.close();
    }
}
```

[Problem - D - Codeforces](https://codeforces.com/contest/1996/problem/D)

给定 $n$ 和 $x$, 寻找正整数 ($a,b,c$) 满足 $ab + ac + bc \le n$ and $a + b + c \le x$.

注意顺序 (e.g. ($1, 1, 2$) and ($1, 2, 1$) 被视为不同) 且 $a$, $b$, $c$ 严格大于$0$.

> 枚举

> 先枚举$a$，再枚举$b$，再计算$c$的个数。
>
> 由于$a,b,c$为正整数，所以$1\le a\le \min(x-2,\frac{n -1}{2})$
>
> 当$a$已确定，那么$b$的范围也随之缩小$1\le b\le\min (x-a-1,\frac{n-a}{a+1})$
>
> 对于每一对已经确定的$a和b$，范围$1\le c \le \min(x-a-b,\frac{n-ab}{a+b})$中的$c$都满足条件。即对于每一个确定的$a和b$都有$\min(x-a-b,\frac{n-ab}{a+b})$个$c$满足条件。累加这些$c$的数量即为答案。

> $O(???)$

```java
import java.io.*;
import java.util.*;

public class Main {
    static Scanner sc = new Scanner(System.in);
    static PrintWriter pw = new PrintWriter(new BufferedWriter(new OutputStreamWriter(System.out)));
    
    static int n, x;
    public static void solve(){
        n = sc.nextInt();
        x = sc.nextInt();
        long ans = 0;
        for(int a = 1; a <= Math.min(x - 2, (n - 1) / 2); a ++){
            for(int b = 1; b <= Math.min(x - a - 1, (n - a) / (a + 1)); b ++){
                ans += Math.min(x - a - b, (n - a * b) / (a + b));
            }
        }
        pw.println(ans);
    }
    public static void main(String[] args) throws IOException {
        int T = sc.nextInt();
        while(T --> 0) solve();
        pw.flush();pw.close();
    }
}
```

[Problem - E - Codeforces](https://codeforces.com/contest/1996/problem/E)

给你一个长度为 $n$的二进制字符串 $s$

求出有多少种 $(l, r)$ $(1 \leq l \leq r \leq n)$， $(x, y)$ $(l \leq x \leq y \leq r)$ 子串 $s_xs_{x+1}...s_y$.中$0,1$数量相同。

> 前缀和，

> 一段$1$和$0$数量相等的字串，设左右端点为$l,r$（从$1$开始）, 对答案的贡献数量为$l\times (n-r+1)$。
>
> 去枚举这样的区间是$n^2$的复杂度，现在问题变为如何线性地计算出所有这样的子串的贡献。
>
> 用前缀和数组$pre$记录$1,0$的个数，用$1$表示$'1'$，$-1$表示$'0'$。对于这样的字串对应$pre[r]-pre[l-1]=0$
>
> 即在该数组中$l-1$位置和$r$位置的数是一样的。
>
> 遍历前缀和数组，对于当前位置，它可能是区间的左端点，也可能是右端点。我们计算它为右端点时对答案的贡献，对于前面每一个左端点$l$我们都要计算一次$l\times (n-r+1)$，所以我们考虑将前面左端点$l$累加起来再与$n-r+1$相乘。这样就能线性地算出总答案了。
>
> 我们用$Map$存当前位置对应的左端点的总和。
>
> 注意：0位置也要算进去（$pre$从1开始的）。

> $O(T\times n)$

```java
import java.io.*;
import java.util.*;

public class Main {
    static Scanner sc = new Scanner(System.in);
    static PrintWriter pw = new PrintWriter(new BufferedWriter(new OutputStreamWriter(System.out)));
    static int n, pre[], mod = (int)1e9 + 7;
    public static void solve(){
        char c[] = sc.next().toCharArray();
        Map<Integer, Long> map = new HashMap<>();
        n = c.length;
        pre = new int[n + 1];
        for(int i = 1; i <= n; i ++){
            pre[i] = pre[i - 1] + (c[i - 1] == '1' ? 1 : -1);
        }
        long ans = 0;
        map.put(0, 1l);
        for(int i = 1; i <= n; i ++){
            long preSum = map.getOrDefault(pre[i],0l);
            ans = (ans + 1l * preSum * (n - i + 1) % mod) % mod;
            map.put(pre[i], preSum + i + 1);
        }
        pw.println(ans);
    }
    public static void main(String[] args) throws IOException {
        int T = sc.nextInt();
        while(T --> 0) solve();
        pw.flush();pw.close();
    }
}
```

## [F. Bomb](https://codeforces.com/contest/1996/problem/F)（待补）

 给你两个长度为 $n$ 的数组 $a$ 和 $b$ 。最初，您的分数是 $0$ 。在一次操作中，您可以选择一个整数 $i$ 并将 $a_i$ 添加到您的分数中。然后，您必须设置 $a_i$ = $\max(0, a_i - b_i)$ 。

在 $k$ 次操作后，您能获得的最高分数是多少？

```java
import java.io.*;
import java.util.*;

public class Prac {
    static Scanner sc = new Scanner(System.in);
    static PrintWriter pw = new PrintWriter(new BufferedWriter(new OutputStreamWriter(System.out)));
    static int n, k, a[], b[];
    public static void solve(){
        n = sc.nextInt();
        k = sc.nextInt();
        a = new int[n];
        b = new int[n];
        for(int i = 0; i < n; i ++) a[i] = sc.nextInt();
        for(int i = 0; i < n; i ++) b[i] = sc.nextInt();
        int l = 0, r = (int)1e9 + 1, x = 0;
        while(l <= r){
            int m = (l + r) >>> 1;
            if(get(m)[0] <= k){
                x = m;
                r = m - 1;
            }else{
                l = m + 1;
            }
        }
        long p[] = get(x);
        // pw.print(x + " ");
        long cnt = p[0];
        long sum = p[1];
        if(x > 0){
            sum += (k - cnt) * (x - 1);
        }
        pw.println(sum);
    }
    private static long[] get(int m) {
        long cnt = 0;
        long sum = 0;
        for(int i = 0; i < n; i ++){
            if(a[i] >= m) {
                long t = (a[i] - m) / b[i] + 1;// times
                cnt += t;
                sum += a[i] * t - (t - 1) * t / 2 * b[i];
            }
        }
        return new long[]{cnt, sum};
    }
    public static void main(String[] args) throws IOException {
        int T = sc.nextInt();
        while(T --> 0) solve();
        pw.flush();pw.close();
    }
}
```







