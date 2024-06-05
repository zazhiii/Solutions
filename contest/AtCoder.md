# Beginner Contest 350

**C - Sort**

https://atcoder.jp/contests/abc350/tasks/abc350_c

给你一个 $(1,2,\ldots,N)$ 的排列组合 $A=(A_1, ..., A_n)$ 。  
请在 $0$ 和 $N-1$ 之间(包括首尾两次)进行以下运算，将 $A$ 转化为 $(1,2,\ldots,N)$ ：

- 运算：选择任意一对整数 $(i,j)$ ，使得 $1\leq i < j \leq N$ .交换 $A$ 的 $i$ -th 和 $j$ -th 位置上的元素。

可以证明，在给定的约束条件下，总是可以把 $A$ 变换成 $(1,2,\ldots,N)$ 。

- $2 \leq N \leq 2\times 10^5$

> 模拟

> 第$i$个元素应该放在$A_i$的位置，遍历，不断将当前位置的元素换到对应的位置

```java
import java.util.*;
import java.io.*;
public class Main {
    static Scanner s = new Scanner(System.in);
    static PrintWriter pw = new PrintWriter(new BufferedWriter(new OutputStreamWriter(System.out)));
    public static void main(String[] args){
        int n = s.nextInt();
        int[] a = new int[n+1];
        for(int i = 1; i <=n; i++) a[i] = s.nextInt();
        List<int[]> res = new ArrayList<>();
        int ans = 0;
        for(int i = 1; i <=n; i++) {
            while(a[i] != i){
                ans++;
                res.add(new int[]{i, a[i]});
                int l = i, r = a[i];
                int t = a[l];
                a[l] = a[r];
                a[r] = t;
            }
        }
        pw.println(ans);
        for (int[] re : res) {
            pw.println(re[0]+" "+re[1]);
        }
        pw.flush();
    }
}
```

**D - New Friends**

https://atcoder.jp/contests/abc350/tasks/abc350_d

$N$个用户，有 $M$ 对好友关系，其中 $i$ /th对由用户 $A_i$ 和 $B_i$ 组成。

请确定以下操作的最大执行次数：

- 操作：选择三个用户 X、Y 和 Z，使得 X 和 Y 是好友，Y 和 Z 是好友，但 X 和 Z 不是好友。让 X 和 Z 成为好友。

> **并查集**

> 最后所有用户会形成若干个完全图，若每个集合人数为$num$，则每个集合最终好友对数为$num*(num-1)/2$，求和所有集合的好友对数，减去原来的好友对数，即答案。
>
> 遍历用户，若用户$i$的顶节点不在`Set`内，则记录该集合的好友对数
>
> **$num*(num-1)/2$会超`int`**！！！！！

```java
import java.util.*;
public class Main {
    static Scanner s = new Scanner(System.in);
    static int n, m, p[], size[], a, b;
    public static void main(String[] args) {
       n = s.nextInt();
       m = s.nextInt();
       int k = m;//记录一下m
       p = new int[n + 1];
       size = new int[n + 1];
       for(int i = 1; i <= n; i++) {
    	   p[i] = i;
    	   size[i] = 1;
       }
       while(m -- >0) {
    	   a = s.nextInt();
    	   b = s.nextInt();
    	   if(find(a)!=find(b)) {
    		   size[find(b)] += size[find(a)];
    		   p[find(a)] = find(b);
    	   }
       }
       long res = 0;
       Set<Integer> set = new HashSet<>();
       for(int i = 1; i<=n; i++) {
    	   if(!set.contains(find(i))) {
    		   set.add(find(i));
    		   int num = size[find(i)];
    		   res += 1l*(num)*(num-1)/2;
    	   }
       }
       System.out.print(res - k);
    }
	private static int find(int x) {
		if(p[x] != x) p[x] = find(p[x]);
		return p[x];
	}
}
```



# Beginner Contest 351

**c. Merge the balls**

https://atcoder.jp/contests/abc351/tasks/abc351_c

你有一个空序列和 $N$ 个球。第 $i$ 个球 $(1 \leq i \leq N)$ 的大小是 $2^{Ai}$ 。

你将进行 $N$ 次运算。  
在 $i$ /th操作中，你将 $i$ /th球添加到序列的右端，然后重复下面的步骤：

1.  如果序列中只有一个或更少的球，则结束操作。
2.  如果序列中最右边的球和第二最右边的球大小不同，结束操作。
3.  如果序列中最右边的球和最右边的第二个球的大小相同，则移除这两个球，并在序列的右端添加一个新球，其大小等于移除的两个球的大小之和。然后回到步骤 1，重复上述过程。

计算经过 $N$ 次操作后，序列中剩余的球数。

> 模拟

> 对于每一个球：先将第$i$个球加入序列，记录幂级数，若最后两个球一样，则倒数第二个球的幂级数$+1$，直到停止操作。

```java
import java.util.*; 
public class Main {
    static Scanner s = new Scanner(System.in);
    static int n, a[], p = 0;
    public static void main(String[] args) {
       n = s.nextInt();
       a = new int[n + 1];
       for(int i = 1; i<=n; i++) {
    	   a[p ++] = s.nextInt();
    	   while(p >=2&&a[p - 1] == a[p - 2]) {
    		   a[p - 2] += 1;
    		   p--;
    	   }
       }
       System.out.print(p);
    }
}
```

### **D. Grid and Magnet**

https://atcoder.jp/contests/abc351/tasks/abc351_d

# Beginner Contest 352

**D - Permutation Subsequence**

https://atcoder.jp/contests/abc352/tasks/abc352_d

给你一个 $(1, 2, \dots, N)$ 的排列组合 $P = (P_1, P_2, \dots, P_N)$ 

如果一个索引序列 $(i_1, i_2, \dots, i_K)$ 同时满足以下两个条件，那么这个索引序列被称为**好索引序列**：

- $1 \leq i_1 < i_2 < \dots < i_K \leq N$.
- 子序列 $(P_{i_1}, P_{i_2}, \dots, P_{i_K})$ 可以通过重新排列一些连续的 $K$ 整数而得到。  
    形式上，存在一个整数 $a$ ，使得 $\lbrace P_{i_1},P_{i_2},\dots,P_{i_K} \rbrace = \lbrace a,a+1,\dots,a+K-1 \rbrace$ .

求所有好的索引序列中 $i_K - i_1$ 的最小值。可以证明，在此问题的约束条件下，至少存在一个好的索引序列。

> **单调队列**、**区间最大值**

> 记录每个数的位置，按数大小排序，遍历，计算长度为$k$的连续序列中位置的最大值与最小值的差，记录最小值

> $P_i:$$10\ 1\ 6\ 8\ 7\ 2\ 5\ 9\ 3\ 4$			--->     $P_i:1\ 2\ 3\ 4\ 5\ 6\ 7\ 8\ 9\ 10$			--->      5 6 7 8 9	--->  $8 - 3 = 5$
>
> $i:\ \ 1\ \ 2\ 3\ 4\ 5\ 6\ 7\ 8\ 9\ 10 	$		            	$i : 2\ 6\ 9\ 10\ 7\ 3\ 5\ 4\ 8\ 1$				    7 3 5 4 8
>
> 区间内最大值最小值用**单调队列维护**

```java
import java.io.*;
import java.util.*;
public class Main {
    static Scanner s = new Scanner(System.in);
    static PrintWriter pw = new PrintWriter(new BufferedWriter(new OutputStreamWriter(System.out)));
    static Read r = new Read();
    static int n, k, a[], q[], min[], max[];
    public static void main(String[] args) throws IOException {
    	n = r.nextInt();
    	k = r.nextInt();
    	a = new int[n + 1];
    	q = new int[n + 1];
    	min = new int[n + 1];
    	max = new int[n + 1];
    	for(int i = 1; i<=n; i++) a[r.nextInt()] = i;
    	int hh = 0, tt = -1;
    	for(int i = 1; i<=n; i++) {
    		if(hh <= tt && q[hh] < i - k + 1) hh ++;
    		while(hh <= tt && a[q[tt]] >= a[i]) tt --;
    		q[ ++ tt] = i;
    		if(i >= k) min[i] = a[q[hh]];
    	}
    	hh = 0; tt = -1;
    	for(int i = 1; i<=n; i++) {
    		if(hh <= tt && q[hh] < i - k + 1) hh ++;
    		while(hh <= tt && a[q[tt]] <= a[i]) tt --;
    		q[ ++ tt] = i;
    		if(i >= k) max[i] = a[q[hh]];
    	}
    	int res = (int)1e6;
    	for(int i = k; i<=n; i++) res = Math.min(res, max[i] - min[i]);
    	pw.print(res);
	    pw.flush();
    }
}

class Read{
	StreamTokenizer st = new StreamTokenizer(new BufferedReader(new InputStreamReader(System.in)));
	public int nextInt() throws IOException {
		st.nextToken();
		return (int)st.nval;
	}
}
```

# Beginner Contest 353

**C - Sigma Problem**

[C - Sigma Problem (atcoder.jp)](https://atcoder.jp/contests/abc353/tasks/abc353_c)

对于正整数 $x$ 和 $y$ ，定义 $f(x, y)$ 为 $(x + y)$ 除以 $10^8$ 的余数。

给你一个长度为 $N$ 的正整数序列  $A = (A_1, \ldots, A_N)$ 。求下面表达式的值：

$\displaystyle \sum_{i=1}^{N-1}\sum_{j=i+1}^N f(A_i,A_j)$ .

-    $2≤N≤3×10^5$
-    $1≤A_i<10^8$

>    若不算$mod$操作求和为$(n-1)\times \sum A_i$，若两个数加起来大于了$10^8$，mod则会减去$10^8$。所以计算一下有多少对加起来大于$10^8$的数，然后求和减去这么多个$10^8$即可
>
>    遍历每一个数，对于当前数，**找右侧与他的和大于$10^8$的最小的数位置**，计算出对数。

```java
import java.io.*;
import java.util.*;
public class Main {
    static Scanner sc = new Scanner(System.in);
    static PrintWriter pw = new PrintWriter(new BufferedWriter(new OutputStreamWriter(System.out)));
    public static void main(String[] args) throws IOException {
    	int mod = (int)1e8;
    	int n = sc.nextInt();
    	long a[] = new long[n + 1];
    	long sum = 0;
    	for(int i = 1; i<=n; i++) {
    		a[i] = sc.nextInt();
    		sum += a[i] * (n - 1);
    	}
    	Arrays.sort(a);
    	//查找有多少对 a[i] + a[j] >= mod
    	long cnt = 0;
    	for(int i = 1; i<=n - 1; i++) {
    		if(a[i] + a[i + 1] >= mod) {
    			cnt += n - i;
    		}else {
    			int l = i + 1, r = n, idx = n + 1;
    			while(l <= r) {
    				int m = (l + r) >>1;
    				if(a[m] >= mod - a[i]) {
    					idx = m;
    					r = m - 1;
    				}else {
    					l = m + 1;
    				}
    			}
    			cnt += n - idx + 1;
    		}
    	}
    	pw.print(sum - cnt * mod);
    	pw.flush();
    }
}
```

**D - Another Sigma Problem**

[D - Another Sigma Problem](https://atcoder.jp/contests/abc353/tasks/abc353_d)

>    快速幂

>    找一下有什么规律：3 14 15
>
>    $3\times10^2+14$
>
>    $+$
>
>    $(3+14)\times10^2+15\times2$
>
>    
>
>    $\sum_{i=1}^n\sum_{j=1}^{i-1}a_j\times 10^{len(a_i)}+a_i\times(i-1)$

```java
import java.io.*;
import java.util.*;
public class Main {
    static Scanner sc = new Scanner(System.in);
    static PrintWriter pw = new PrintWriter(new BufferedWriter(new OutputStreamWriter(System.out)));
    static int mod = 998244353;
    public static void main(String[] args) throws IOException {
    	int n = sc.nextInt();
    	long a[] = new long[n + 1];
    	for(int i = 1; i<=n; i++) a[i] = sc.nextLong();
    	long ans = 0, sum = a[1];
    	for(int i = 2; i<=n; i++) {
    		ans += sum * qpow(10, len(a[i])) % mod + a[i] * (i - 1) % mod;
    		sum = (sum + a[i]) % mod;
    	}
    	pw.print(ans % mod);
    	pw.flush();
    }
	private static long qpow(long a, long n) {
		long ans = 1;
		a %= mod;
		while(n > 0) {
			if( (n & 1) == 1) ans = (ans * a) % mod;
			a = a*a%mod;
			n >>= 1;
		}
		return ans;
	}
	private static long len(long n) {
		return (n+"").length();
	}
}

```

### **E - Yet Another Sigma Problem**

[E - Yet Another Sigma Problem](https://atcoder.jp/contests/abc353/tasks/abc353_e)

# Beginner Contest 354

[C - AtCoder Magics](https://atcoder.jp/contests/abc354/tasks/abc354_c)

高桥有纸牌游戏 "AtCoder Magics "中的 $N$ 张纸牌。其中的 $i$ 张卡将被称为 $i$ 张卡。每张卡都有两个参数：强度和成本。卡片 $i$ 的强度为 $A_i$ ，成本为 $C_i$ 。

他不喜欢弱牌，所以他会弃掉它们。具体来说，他会重复下面的操作，直到无法再进行为止：

- 选择两张牌 $x$ 和 $y$ ，即 $A_x > A_y$ 和 $C_x < C_y$ 。弃牌 $y$ 。

可以证明，当无法再进行操作时，剩下的牌的集合是唯一确定的。请找出这组牌。

>排序

>**注意看题！！！/(ㄒoㄒ)/~~**
>
>- $A_1,A_2,…,A_N$ are all distinct.
>- $𝐶_1,𝐶_2,…,𝐶_𝑁$are all distinct.
>
>每个卡牌按照$C_i$排序。对于第$i$张卡只需要比较$A_i$和$max(A_j),j\in[0,i-1]$即可直到是否应该被删除（因为已知$C_i>C_j$)。标记应该被删除的卡，在按照序号排序回来，输出答案。

```java
import java.io.*;
import java.util.*;
public class Main {
	static Scanner sc = new Scanner(System.in);
	static PrintWriter pw = new PrintWriter(new BufferedWriter(new OutputStreamWriter(System.out)));
	static int n;
	static boolean st[];
	static class Card{
		int a, c, idx;
		boolean k;
	}
	static Card cards[];
	public static void main(String[] args) throws IOException{
		n = sc.nextInt();
		cards = new Card[n];
		st = new boolean[n];
		for(int i = 0; i < n; i ++) {
			Card card = new Card();
			card.a = sc.nextInt();
			card.c = sc.nextInt();
			card.idx = i + 1; 
			cards[i] = card;
		}
		Arrays.sort(cards, (c1, c2) -> c1.c - c2.c);
		int maxa = 0, ans = n;
		for(int i = 0; i < n; i++) {
			if(cards[i].a < maxa) {
				cards[i].k = true;
				ans --;
			}else {
				maxa = cards[i].a;
			}
		}
		pw.println(ans);
		Arrays.sort(cards, (c1, c2) -> c1.idx - c2.idx);
		for(int i = 0; i < n; i++) 
			if(!cards[i].k) pw.print((i + 1) + " ");
		pw.flush();
	}
}
```

# Beginner Contest 355

**D - Intersecting Intervals**

https://atcoder.jp/contests/abc355/tasks/abc355_d

给你 $N$ 个实数区间。 $i$ $(1 \leq i \leq N)$ 区间为 $[l_i, r_i]$ 。求 $i$ -th 和 $j$ -th区间相交的 $(i, j),(1 \leq i < j \leq N)$ 对的个数。

>    排序 + 二分｜排序 + 扫描

> 法一：
>
> **当直接求不好求时，求对立情况来容斥出待求情况**

> 用不相交的区间对数容斥相交的区间
>
> $l_i$标记0，$r_i$标记1。按照左端点排序
>
> 遇到每一个左端点，若前面有$x$个结束了的区间则不相交对数$+x$
>
> $O(n)$

```java
    static int n;
    static List<int[]> p = new LinkedList<>();
    private static <T> void solve() throws IOException {
        n = rd.nextInt();
        for(int i = 1; i <= n; i ++){
            p.add(new int[]{rd.nextInt(), 0});
            p.add(new int[]{rd.nextInt(), 1});
        }
        //按照端点排序 注意处理开始点和结束点重合的情况
        Collections.sort(p, (p1, p2) -> p1[0] == p2[0] ? p1[1] - p2[1] : p1[0] - p2[0]);
        long ans = n*(n - 1l)/2;
        int ended = 0;
        for(int[] p : p){
            if(p[1] == 1) ended ++;
            else ans -= ended;
        }
        pw.println(ans);
    }
```

>    法二：二分
>
>    按左端点排序，枚举每一个线段$i$的右端点，二分找出最大且小于等于该右端点的左端点所在的线段$j$（即与该线段相交的最右侧的那一根线段），$ans = ans + j - i$。
>
>    $O(n \log n)$

```java
import java.io.*;
import java.util.*;
public class Main {
    static Scanner sc = new Scanner(System.in);
    static PrintWriter pw = new PrintWriter(new BufferedWriter(new OutputStreamWriter(System.out)));
    static int n, a[][];
    public static void main(String[] args) {
        n = sc.nextInt();
        a = new int[n][2];
        for(int i = 0; i < n; i ++){
            int l = sc.nextInt();
            int r = sc.nextInt();
            a[i] = new int[]{l, r};
        }
        Arrays.sort(a, (a1, a2) -> a1[0] - a2[0]);
        //枚举每一个右端点i，查找最大且<=该右端点的线段j ans += j - i
        long ans = 0;
        for(int i = 0; i < n; i ++){
            int cr = a[i][1];
            int l = i, r = n - 1, j = l;
            while(l <= r){
                int m = (r + l) >>> 1;
                if(a[m][0] <= cr){
                    j = m;
                    l = m + 1;
                }else{
                    r = m - 1;
                }
            }
            ans += j - i;
        }
        pw.println(ans);
        pw.flush();
    }
}
```



# Beginner Contest 356

[C - Keys](https://atcoder.jp/contests/abc356/tasks/abc356_c)

>    DFS

>    $1\leq N \leq15$，考虑枚举所有钥匙的真假组合情况，再判断每一种情况是否满足所有测试，满足则答案数+1
>
>    $O(M\times2^N)$

```java
import java.io.*;
import java.util.*;
public class Main {
    static Scanner sc = new Scanner(System.in);
    static PrintWriter pw = new PrintWriter(new BufferedWriter(new OutputStreamWriter(System.out)));
    static int n, m, k, a[][], num[], ans, key[];
    static char r[];
    public static void main(String[] args) {
        n = sc.nextInt();
        m = sc.nextInt();
        k = sc.nextInt();
        a = new int[m][n];
        r = new char[m];
        num = new int[m];
        key = new int[n + 1];
        for(int i = 0; i < m; i ++){
            num[i] = sc.nextInt();
            for(int j = 0; j < num[i]; j ++) a[i][j] = sc.nextInt();
            r[i] = sc.next().charAt(0);
        }
        dfs(1);
        pw.println(ans);
        pw.flush();
    }
    private static void dfs(int cnt) {
        if(cnt == n + 1){
            for(int i = 0; i < m; i ++){
                int tk = 0;
                for(int j = 0; j < num[i]; j ++){
                    if(key[a[i][j]] == 1) tk ++;
                }
                if(tk >= k && r[i] == 'x') return;
                if(tk < k && r[i] == 'o') return;
            }
            ans ++;
            return;
        }
        for(int i = 0; i <= 1; i ++){
            key[cnt] = i;
            dfs(cnt + 1);
        }
    }
}
```

