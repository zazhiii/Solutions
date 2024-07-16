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

