# Round 944 (Div. 4)

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

# Round 957 (Div. 3)

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

# [Round 958 (Div. 2)](https://codeforces.com/contest/1988)

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

# [Round 962 (Div. 3)](https://codeforces.com/contest/1996)

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

### [F. Bomb](https://codeforces.com/contest/1996/problem/F)（待补）

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



# [Pinely Round 4 (Div1 + 2) ](https://codeforces.com/contest/1991)

[Problem - B - Codeforces](https://codeforces.com/contest/1991/problem/B)

给你一个由 $n - 1$ 个整数组成的数组 $b$ 。

构造长度为$n$的数组$a$使得$b_i=a_i\&a_{i+1}$，不存在则输出$-1$

> 构造

> 由$b_i=a_i\&a_{i+1}、b_{i+1}=a_{i+1}\&a_{i+2}$，当$b_i$中为$1$的位$a_i\ a_{i+1}$中必为$1$，同理对于$b_{i+1}$中为$1$的位$a_{i+1}$中也应为$1$，所以$a_{i+1}=b_i|b_{i+1}$ ，这样可以计算出$a_2\sim a_{n-1}$，令$a_1=b_1,a_n=b_{n-1}$。再去遍历一遍$a$数组判断是否合法即可。

> $O(n)$

```java
import java.io.*;
import java.util.*;

public class Main {
    static Scanner sc = new Scanner(System.in);
    static PrintWriter pw = new PrintWriter(new BufferedWriter(new OutputStreamWriter(System.out)));
    public static void solve(){
        int a[], b[];
        int n = sc.nextInt();
        a = new int[n];
        b = new int[n - 1];
        for(int i = 0; i < n - 1; i ++) b[i] = sc.nextInt();
        a[0] = b[0];
        a[n - 1] = b[n - 2];
        for(int i = 1; i <= n - 2; i ++) a[i] = b[i - 1] | b[i];
        for(int i = 0; i <= n - 2; i ++){
            if((a[i] & a[i + 1]) != b[i]){
                pw.println(-1);
                return;
            }
        }
        for(int i = 0; i < n; i ++) pw.print(a[i] + " ");
        pw.print("\n");
    }
    public static void main(String[] args) throws IOException {
        int T = sc.nextInt();
        while(T --> 0) solve();
        pw.flush();pw.close();
    }
}
```

[Problem - C - Codeforces](https://codeforces.com/contest/1991/problem/C)

You are given an array $a$ of $n$ integers.

In one operation, you will perform the following two-step move:

1.  Choose an integer $x$ ($0 \le x \le 10^{9}$).
2.  Replace each $a_i$ with $|a_i - x|$.

For example, by choosing $x = 8$, the array $[5, 7, 10]$ will be changed into $[|5-8|, |7-8|, |10-8|] = [3,1,2]$.

Construct a sequence of operations to make all elements of $a$ equal to $0$ in at most $40$ operations or determine that it is impossible. You do **not** need to minimize the number of operations.

> 构造，贪心

> 首先，如果序列中同时存在奇数和偶数就不可能操作成功，因为操作不可能把他们变为同奇偶的数。
>
> 考虑每次选择$x=[max(a_i)-min(a_i)]/2$因为这样能使极差减小得最快，直到$max(a_i)=min(a_i)$即可停止操作，在此之后若数组肯定是一样的，若值大于$0$就在操作一次，反之就不用操作了。

> $O(\sum n\times\log V)\\V=\max(a_i)-\min(a_i)$

```java
import java.io.*;
import java.util.*;

public class Main {
    static Scanner sc = new Scanner(System.in);
    static PrintWriter pw = new PrintWriter(new BufferedWriter(new OutputStreamWriter(System.out)));
    public static void solve(){
        int n = sc.nextInt();
        int a[] = new int[n];
        int mx = 0, mn = (int)1e9;
        boolean odd = false, even = false;
        for(int i = 0; i < n; i ++){
            a[i] = sc.nextInt();
            mx = Math.max(mx, a[i]);
            mn = Math.min(mn, a[i]);
            if(a[i] % 2 == 0) even = true;
            else odd = true;
        }
        if(even && odd) {
            pw.println(-1);
            return;
        }
        Vector<Integer> ans = new Vector<>();
        while(mx > mn){
            int m = (mx + mn) >>> 1;
            ans.add(m);
            mx = 0; mn = (int)1e9;
            for(int i = 0; i < n; i ++) {
                a[i] = Math.abs(a[i] - m);
                mx = Math.max(mx, a[i]);
                mn = Math.min(mn, a[i]);
            }
        }
        if(mx > 0) ans.add(mx);
        if(ans.size() > 40){
            pw.println(-1);
            return;
        }
        pw.println(ans.size());
        for(int i : ans) pw.print(i + " ");
        pw.print("\n");
    }
    public static void main(String[] args) throws IOException {
        int T = sc.nextInt();
        while(T --> 0) solve();
        pw.flush();pw.close();
    }
}
```

# [Educational Round 168 (Div. 2)](https://codeforces.com/contest/1997)

[Problem - D - Codeforces](https://codeforces.com/contest/1997/problem/D)

给你一棵有根的树，由 $n$ 个顶点组成。树上的顶点从 $1$到 $n$ 编号，根是顶点 $1$ 。第 $i$ 个顶点上的值为 $a_i$。

你可以执行以下操作任意次(可以为零次):选择一个至少有一个子顶点的顶点 $v$; 将 $a_v$ 增加 $1$ 并且对于 $v$ 的子树中的所有顶点 $u$ 将 $a_u$ 减少 $1$ (除了 $v$ 本身)。但是，在每次操作之后，所有顶点上的值都应该是非负的。

你的任务是使用前面提到的运算来计算写在根上的最大可能值。

 $n$ 之和不超过 $ 2 \times 10^5 $。

> 二分答案（还有其他做法）

> 如何检验二分的答案是否合法？，dfs访问除了根节点的每个节点，如果该节点的值足够贡献出答案则继续往后搜索，如果不够贡献答案就需要向其子树借相差的值，即子树需要贡献更多答案。就这样一直推算到叶子节点，因为叶子节点无法借答案，若其值不足以贡献其应该贡献的答案那么这个二分的值就是不合法的。如果所有叶子节点都足以贡献其应该贡献的值，那么这个二分的值就是合法的。现在找出最大的合法的值就是答案。
>
> **注意**：在dfs中可能会爆long，需要贡献的答案到一定的值剩余节点就不可能能够支付了，即使退出。

> $O(n\times\log V) V为二分范围$

```java
import java.io.*;
import java.util.*;

public class Prac {
    static Scanner sc = new Scanner(System.in);
    static PrintWriter pw = new PrintWriter(new BufferedWriter(new OutputStreamWriter(System.out)));
    static int n, a[];
    static Vector<Integer>[] adj;
    public static void solve(){
        n = sc.nextInt();
        a = new int[n + 1];
        adj = new Vector[n + 1];
        Arrays.setAll(adj, i -> new Vector<>());
        for(int i = 1; i <= n; i ++) a[i] = sc.nextInt();
        for(int i = 2; i <= n; i ++){
            int u = sc.nextInt();
            adj[u].add(i);
        }
        int l = 0, r = (int)2e9, ans = 0;
        while(l <= r){
            int m = (l + r) >>> 1;
            if(check(m)){
                ans = m;
                l = m + 1;
            }else{
                r = m - 1;
            }
        }
        pw.println(ans);
    }
    static boolean f;
    static long lim = (long)1e10;
    private static boolean check(int m) {
        f = true;
        for(int v : adj[1]){
            dfs(v, m - a[1]);
        }
        return f;
    }
    private static void dfs(int u, long need) {
        if((adj[u].isEmpty() && a[u] < need) ||need > lim){
            f = false;
            return;
        }
        if(a[u] < need) need += (need - a[u]);
        for(int v : adj[u]){
            dfs(v, need);
        }
    }
    public static void main(String[] args) throws IOException {
        int T = sc.nextInt();
        while(T --> 0) solve();
        pw.flush();pw.close();
    }
}
```

# [Round 963 (Div. 2)](https://codeforces.com/contest/1993)

[Problem - B - Codeforces](https://codeforces.com/contest/1993/problem/B)

给定一个由 n 个正整数组成的数组 a 。

在一次操作中，你可以选取任意一对索引 $(i,j)$，且 $a_i$ 和 $a_j$ 具有**不同的**奇偶性，然后用它们的和替换较小的那个。

求使数组中所有元素具有相同奇偶性所需的最少操作数。

> 思维、贪心

> 首先合并两个奇偶不一的数得到的一定是一个奇数，那么最终结果一定是将数组中所有偶数都变为奇数。
>
> 我们将合并操作理解为一个较大数吃掉较小数，产生一个新的合并的数。贪心地，尽量用奇数去吃掉偶数，这样会直接减少一个偶数；而用偶数去吃奇数不会减少偶数个数。
>
> 接下来我们去判断一个尽量大地奇数是否能够将所有偶数吃完。首先，如果数组中地最大值是奇数，那么毫无疑问它可以吃掉所有偶数，吃地次数等于偶数个数。若最大值不是奇数，我们就用最大的奇数从小到大地吃偶数，过程中维护它吃了偶数之后变大地值，直到不能再吃偶数。如果它不能将偶数吃完，也就是吃到不能再吃了也无法大于最大的偶数值。那么就需要用最大的偶数值与一个较小的奇数值造一个最大的奇数出来，将剩余偶数吃掉，这个过程用了偶数数量+1次操作

> $O(n)$

```java
import java.io.*;
import java.util.*;

public class Prac {
    static Scanner sc = new Scanner(System.in);
    static PrintWriter pw = new PrintWriter(new BufferedWriter(new OutputStreamWriter(System.out)));
    public static void solve(){
        int n = sc.nextInt();
        int a[] = new int[n];
        int odd = 0, mxodd = 0;
        for(int i = 0; i < n; i ++) {
            a[i] = sc.nextInt();
            if(a[i] % 2 == 1) {
                odd ++;
                mxodd = Math.max(mxodd, a[i]);
            }
        }
        Arrays.sort(a);
        if(odd == 0) {
            pw.println(0);
            return;
        }
        if(a[n - 1] % 2 == 1) pw.println(n - odd);
        else{
            long sum = mxodd;
            for(int i = 0; i < n; i ++){
                if(a[i] % 2 == 0 && a[i] < sum) sum += a[i]; 
            }
            pw.println(sum > a[n - 1] ? n - odd : n - odd + 1);
        }
    }
    public static void main(String[] args) throws IOException {     
        int T = sc.nextInt();
        while(T --> 0) solve();
        pw.flush();pw.close();
    }
}

```

[Problem - C - Codeforces](https://codeforces.com/contest/1993/problem/C)

有一个由 n 个房间组成的公寓，每个房间的灯最初都是关着的。

为了控制这些房间的灯光，公寓的主人决定在房间里安装芯片，这样每个房间正好有一个芯片，芯片安装在不同的时间。具体来说，这些时间由数组 a1,a2,…,an 表示，其中 ai 是在 i /th房间安装芯片的时间(以分钟为单位)。

芯片安装后，每隔 kk 分钟就会改变房间的灯光状态--在 kk 分钟内打开灯光，然后在接下来的 kk 分钟内关闭灯光，再在接下来的 kk 分钟内重新打开灯光，以此类推。换句话说，芯片在 ai 、 ai+k 、 ai+2k 、 ai+3k 、 …… 分钟时改变 i /th房间的灯光状态。

公寓里所有房间最早亮灯的时刻是什么时候？

> 模拟？

> 求最早时刻所有灯都处于亮的状态。首先一定是在安装最后一个灯泡后出现这个时刻，且一定是出现在最后这个灯的第一个亮区间内。因为所有灯的周期是一样的，那么整体情况也会有一个周期且为$2k$，在最后这个灯的每个明亮区间内，所有灯的状态变化是一致的。如果第一个明亮区间没有出现全亮，就不可能出现全亮了。
>
> 我们求最后一个灯炮的第一个明亮区间和前面每个灯泡在这个时间区间内明亮区间的交集即可。

> $O(n\log n)$

```java
import java.io.*;
import java.util.*;

public class Main {
    static Scanner sc = new Scanner(System.in);
    static PrintWriter pw = new PrintWriter(new BufferedWriter(new OutputStreamWriter(System.out)));
    public static void solve(){
        int n = sc.nextInt();
        int k = sc.nextInt();
        int a[] = new int[n];
        for(int i = 0 ; i < n; i ++) a[i] = sc.nextInt();
        Arrays.sort(a);
        int l = a[n - 1], r = a[n - 1] + k;
        for(int i = n - 2; i >= 0; i --){
            int x = (a[n - 1] - a[i]) / k;
            if(x % 2 == 0){
                r = Math.min(r, a[i] + x * k + k);
            }else{
                l = Math.max(l, a[i] + x * k + k);
            }
        }
        pw.println(l < r ? l : -1);
    }
    public static void main(String[] args) throws IOException {     
        int T = sc.nextInt();
        while(T --> 0) solve();
        pw.flush();pw.close();
    }
}

```

