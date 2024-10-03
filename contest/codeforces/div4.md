# ✅Codeforces Round 944 (Div. 4)

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

[F - Circle Perimeter](https://codeforces.com/contest/1971/problem/F)

> 二分

# 

# [Codeforces Round 964 (Div. 4)](https://codeforces.com/contest/1999) （exc：G1 G2）

[Problem - 1999E - Codeforces](https://codeforces.com/problemset/problem/1999/E)

Ivy 在黑板上写下了在 $l$ 到 $r$ 之间的所有整数。

在一次运算中，她做了以下操作：

- 在黑板上选出任意两个数字 $x$ 和 $y$ ，将它们擦掉，然后在它们的位置上写下数字 $3x$ 和 $\lfloor \frac{y}{3} \rfloor$ 。(这里的 $\lfloor x\rfloor$ 表示取整，即四舍五入到最接近的整数）。

要使黑板上的所有数字都等于 $0$ ，Ivy 最少需要进行多少次运算？可以证明一定有解。

第一行包含一个正整数 $t$ ( $1 \leq t \leq 10^4$ )，表示测试用例的数量。

对于每个测试用例，仅一行包含两个正整数 $l$ 和 $r$ ( $1 \leq l <  r \leq 2 \cdot 10^5$ )。

> 预处理

> 首先先把一个数变为$0$，再用这个数将其他数变为$0$。
>
> 预处理$1\sim2\times10^5$之间的每一个数需要除几次能为$0$，在$3^k\sim 3^{k+1}-1$之间的数需要除$k+1$次能为$0$
>
> 答案为所有数变为$0$的操作次数，再加上最小的那个数变为$0$的操作次数

```java
import java.io.*;
import java.util.*;
public class Main{
    static Scanner sc = new Scanner(System.in);
    static PrintWriter pw = new PrintWriter(System.out);
    static long pow3[] = new long[15];
    static long cnt[] = new long[(int)2e5 + 1];
    static long pre[] = new long[(int)2e5 + 1];
    public static void init(){
        pow3[0] = 1;
        for(int i = 1; i < 15; i ++) pow3[i] = pow3[i - 1] * 3;
        int k = 0;
        for(int i = 1; i < (int)2e5 + 1; i ++){
            if(i >= pow3[k] && i <= pow3[k + 1] - 1){
                cnt[i] = k + 1;
            }else{
                k ++;
                cnt[i] = k + 1; 
            }
            pre[i] = pre[i - 1] + cnt[i];
        }
    }
    public static void solve(){
        int l = sc.nextInt();
        int r = sc.nextInt();
        pw.println(pre[r] - pre[l - 1] + cnt[l]);
    }
    public static void main(String[] args) throws IOException {
        int T = sc.nextInt();
        init();
        while(T --> 0) solve();
        pw.flush();
    }   
}
```

[Problem - F - Codeforces](https://codeforces.com/contest/1999/problem/F)

Arul has a **binary** array$^{\text{∗}}$ $a$ of length $n$.

He will take all subsequences$^{\text{†}}$ of length $k$ ($k$ is odd) of this array and find their median.$^{\text{‡}}$

What is the sum of all these values?

As this sum can be very large, output it modulo $10^9 + 7$. In other words, print the remainder of this sum when divided by $10^9 + 7$.

$^{\text{∗}}$A binary array is an array consisting only of zeros and ones.

$^{\text{†}}$An array $b$ is a subsequence of an array $a$ if $b$ can be obtained from $a$ by the deletion of several (possibly, zero or all) elements. Subsequences **don't** have to be contiguous.

$^{\text{‡}}$The median of an array of odd length $k$ is the $\frac{k+1}{2}$\-th element when sorted.

> 组合数

> 要使得$k$子序列中的中位数为$1$，那么其中至少有$\lceil \frac{k}{2} \rceil$个$1$，
>
> 从所有$1$中选$i(\lceil \frac{k}{2} \rceil\le i\le k)$个$1$出来，再从所有$0$中选$k-i$个$0$出来。
>
> $\sum^{k}_{i=\lceil \frac{k}{2} \rceil}C_{one}^{i}\times C_{zero}^{k-i}$

```java
import java.io.*;
import java.util.*;
public class Main {
    static Scanner sc = new Scanner(System.in);
    static PrintWriter pw = new PrintWriter(System.out);

    static int mod = (int)1e9 + 7;
    static int max = (int)2e5 + 10;
    static long f[] = new long[max];
    static long invf[] = new long[max];

    public static long qpow(long a, long n){
        long ans = 1;
        while(n > 0){
            if(n % 2 == 1) ans = ans * a % mod;
            a = a * a % mod;
            n >>>= 1;
        }
        return ans;
    }
    public static void init(){
        f[0] = invf[0] = 1;
        for(int i = 1; i < max; i ++){
            f[i] = f[i - 1] * i % mod;
            invf[i] = invf[i - 1] * qpow(i, mod - 2) % mod;
        }
    }
    public static long C(int a, int b){
        if(b > a) return 0;
        return f[a] * invf[b] % mod * invf[a - b] % mod;
    }
    public static void solve(){
        int n = sc.nextInt();
        int k = sc.nextInt();
        int zero = 0, one = 0;
        for(int i = 0; i < n; i ++){
            int x = sc.nextInt();
            if(x == 1) one ++;
            else zero ++;
        }
        long ans = 0;
        //c[one][i] * c[zero][k - i];
        for(int i = k / 2 + 1; i <= k; i ++){
            ans = (ans + C(one, i) * C(zero, k - i)) % mod;
        }
        pw.println(ans);
    }
    public static void main(String[] args) throws IOException {
        int T = sc.nextInt();
        init();
        while(T --> 0) solve();
        pw.flush();
    }   
}
```

