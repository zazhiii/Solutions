# 牛客小白月赛91

链接：https://ac.nowcoder.com/acm/contest/78807/D

有一个长度为$n$ 的数字字符串$S$，该字符串仅包含[0,9][0,9][0,9]的数字
从中挑选出若干个字符，然后按照其相对顺序重新拼接而成一个数字，使其是一个**无前导**$0$**的偶数**。

 例如：当$n=3,S=100$。 其包含的偶数数字有$0,0,10,10,100$。而$00$是不符合条件的，因为其含有前导0。

 由于字符串实在是太长了，他一个人数不过来，请您帮他计算一下该字符串中含有的偶数方案总数， 结果对$10^9+7$取模。

```java
import java.io.*;
import java.util.*;
public class Main {
	static Read r = new Read();
	static Scanner s = new Scanner(System.in);
	static BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
	static int n, maxn = 200000, mod = (int)1e9+7;
	static char[] str;
	public static void main(String[] args) throws IOException {
		n = s.nextInt();
		str = s.next().toCharArray();
//		int[] dp = new int[maxn+1];
		long res = 0, cnt = 0;
		for(int i = 0;i<=n-1;i++) {
			int x = str[i]-'0';
			if(x==0) {
				res = (res + cnt +1)%mod;
				cnt=(cnt*2)%mod;
			}else if(x%2==0) {
				res = (res + cnt +1)%mod;
				cnt=(cnt*2+1)%mod;
			}else {
				cnt=(cnt*2+1)%mod;
			}
		}
		
		
		System.out.print(res);
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



# 牛客小白月赛92

**B采矿时间到！**

链接：https://ac.nowcoder.com/acm/contest/81126/B

这一天你挖了一条长度为 $n$，宽度为 $1$ 的矿道，你最多只能在这条矿道向左/右正方向拓宽$2$格，并且你只能**垂直**于矿道挖掘。

```
##*#############**##
#########*##########
....................
#####*######**######
#*##################
```

 如上图所示，'.' 表示矿道，'#' 表示的是圆石，'*' 表示的是矿石。
 本题固定第三行为矿道，第一二行 为你的左侧，第四/五行 为你的右侧。
 因为你只能站在矿道上，至多向左/右正方向拓宽 $2$ 格，所以本题只给出 $5n$ 的俯视图。

每拓宽一格，需要花费$1$ 点体力。现在您有 h\mathit hh 点体力，问你**最多**能得到多少矿石？

> 模拟，贪心

> 先玩第2，4行的。若矿石后面（1，5行）还有矿石，则挖掉。
>
> 再取挖1，5行的埋在2，4行#后面的矿石，知道体力耗尽。

```java
import java.util.*;
public class Main {
	static Scanner s = new Scanner(System.in);
	public static void main(String[] args) {
		String[] str = new String[5];
		int[] num = new int[5];
		int l = s.nextInt();
		int h = s.nextInt();
		for(int i = 0 ;i<=4;i++) {
			str[i] = s.next();
		}
		int res = 0;
		for(int i = 0; i<=l -1; i++) {
			if(h <=0) break;
			if(str[1].charAt(i) == '*') {
				h --;
				res ++;
			}
			if(h <=0) break;
			if(str[1].charAt(i) == '*'&&str[0].charAt(i) == '*') {
				h --;
				res ++;
			}
		}
		for(int i = 0; i<=l -1; i++) {
			if(h <=0) break;
			if(str[3].charAt(i) == '*') {
				h --;
				res ++;
			}
			if(h <=0) break;
			if(str[3].charAt(i) == '*'&&str[4].charAt(i) == '*') {
				h --;
				res ++;
			}
		}
		for(int i = 0; i<=l -1; i++) {
			if(h <=1) break;
			if(str[0].charAt(i) == '*'&&str[1].charAt(i)=='#') {
				h-=2;
				res ++;
			}
			if(h <=1) break;
			if(str[4].charAt(i) == '*'&&str[3].charAt(i)=='#') {
				h-=2;
				res ++;
			}
		}
		System.out.print(res);
	}
}
```



**C耕种时间到！**

链接：https://ac.nowcoder.com/acm/contest/81126/C

定义等级为 $\mathit x$ 的小麦，收割后可以得到 $2$ 枚 等级为 $\lceil\dfrac{x}{3}\rceil$ 的小麦种子。现在你有 $n$ 枚小麦种子，第 $i$ 枚种子的等级为 $a_i$，你可以全部种下，也可以选择全部都不种下。小麦成熟以后，你可以进行收割，收割必须收割所有种下的小麦。现在你想知道，在任意时刻(收割前或收割后)最多能拥有多少枚等级为 $x$ 的小麦种子？

> 模拟

> 用两个数组记录等级和数量，每一轮种植更新等级并记录$x$等级的数量，记录最大值。直到所有种子等级小于$x$

```java
import java.util.*;
public class Main {
	static Scanner s = new Scanner(System.in);
	public static void main(String[] args) {
		int n = s.nextInt();
		int[] a = new int[n];
		int maxa = 0;
		for(int i = 0; i<=n-1; i++) {
			a[i] = s.nextInt();
			maxa = Math.max(maxa, a[i]);
		}
		long [] num = new long[n];
		for(int i = 0; i<=n -1; i++) num[i] =1;
		int x = s.nextInt();
		long max = 0;
		while(maxa>1) {
			long res = 0;
			for(int i = 0; i<=n-1; i++) {
				if(a[i] == x) res+=num[i];
			}	
			max = Math.max(max, res);
			for(int i = 0; i<=n-1; i++) {
				a[i] = (int) Math.ceil((a[i]/3.0));
				num[i] *=2;
			}
			maxa = (int) Math.ceil((maxa/3.0));
		}
		System.out.print(max);
	}
}
```

**D探索的时光**

链接：https://ac.nowcoder.com/acm/contest/81126/D

目前你已知 $n$ 个生物群系的位置(从 $1$ 到 $n$编号)，你需要去探索，第$i$ 个生物群系的危险系数为 aia_iai。定义第 iii 个生物群系的危险度为$ f(i)=(x−i)^2∗a_i$，$x$ 为庇护所 所在生物群系的编号。现在你可以选择一个生物群系作为自己的庇护所，你想要知道所有可能情况下危险度之和的**最小值**是多少？

> 即求$\sum_{i=1}^{n}(x-i)^2*a_i$最小值，等于$\sum_{i=1}^{n}a_ix^2-2\sum_{i=1}^{n}ia_ix+\sum_{i=1}^{n}i^2a_i$。遍历$x$的值记录最小值
>
> **注意：$i^2、ia_i$都有可能超过`int`，乘以一个`Long`类型的`1L`转换**

```java
import java.util.*;
public class Main {
    static Scanner s = new Scanner(System.in);
    static int n;
    static long a = 0,b = 0,c = 0;
    public static void main(String[] args) {
        n = s.nextInt();
        for(int i = 1; i<=n;i++) {
            long ai = s.nextLong();
            a += 1l*ai;
            b += 2l*i*ai;
            c += 1l*i*i*ai;
        }
        long res = Long.MAX_VALUE;
        for(int x = 1; x<=n;x++) res = Math.min(res, a*x*x-b*x+c);
        System.out.print(res);
    }
}
```





链接：https://ac.nowcoder.com/acm/contest/81126/E
一枚煤炭可以在熔炉内燃烧 $y$ 秒融化至多 $x$ 单位的铁矿石。

而一枚暗物质可以在熔炉内燃烧$y/2$ 秒融化至多 $2x$ 单位的铁矿石。

同一时刻，熔炉只能燃烧一枚燃料。燃料均不可重复利用。燃料燃烧完之前，你不可以获取熔炉中的矿物。

你有一个神奇的魔法，可以将一枚煤炭升级成暗物质，这个魔法至多只能使用**一次**。

现在你有 $1$ 个熔炉，$n$ 枚煤炭和 $m$ 单位铁矿石，问烧炼 $m$ 单位铁矿石**至少**需要多长时间

>    1.    $dp[i][j][0/1]$：考虑前$i$个煤炭烧$j$个矿石最少需要的时间，$0/1$代表到目前为止是否使用魔法
>
>    2.    初始化：对于$0$颗煤炭烧所有正数数的矿石需要无穷时间，$0$颗矿石则需要$0$的时间；
>
>          $dp[0][j][0/1] = INF(j>=1)、dp[i][0][0/1] = 0$
>
>    3.    对于当前元素，考虑选/不选。 若选且未使用魔法则在加使用魔法的情况。
>
>          $dp[i][j][0] = min(dp[i-1][j][0], dp[i][max(0,j-x[i])][0]+y[i])$
>
>          ​	对于上式右侧两项：第一项为不选，第二项为选
>
>          $dp[i][j][1] = min(dp[i-1][j][1],dp[i-1][max(0,j-x[i])][1]+y[i]),dp[i-1][max(0,j-2x[i])][0]+y[i]/2)])$
>
>          ​	对于上式右侧三项：第一项表示不选，第二项表示选且没用魔法，第三项表示选且用魔法
>
>    4.    结果取$dp[n][m][1]、dp[n][m][0]$较小值

```java
import java.util.*;
public class Main {
    static Scanner s = new Scanner(System.in);
    static long INF = (long)2e18;
    static int n, m, x[], y[];
    static long dp[][][];
    public static void main(String[] args) {
        n = s.nextInt();
        m = s.nextInt();
        x = new int[n+1];
        y = new int[n+1];
        for(int i = 1; i<=n; i++) {
            x[i] = s.nextInt();
            y[i] = s.nextInt();
        }
        dp = new long[n+1][m+1][2];
        for(int j = 1;j<=m;j++) {
            dp[0][j][0] = INF;
            dp[0][j][1] = INF;
        }        
        for(int i = 1; i<=n;i++) {
            for(int j = 0; j<=m;j++) {
                dp[i][j][0] = Math.min(dp[i-1][Math.max(0, j-x[i])][0]+y[i], dp[i-1][j][0]);
                dp[i][j][1] = Math.min(dp[i-1][Math.max(0, j-2*x[i])][0]+y[i]/2, dp[i-1][Math.max(0, j-x[i])][1]+y[i]);
                dp[i][j][1] = Math.min(dp[i][j][1], dp[i-1][j][1]);
            }
        }
        System.out.print(Math.min(dp[n][m][1], dp[n][m][0]));
    }
}
```



# 牛客周赛 Round 41

**B.小红的排列构造**

https://ac.nowcoder.com/acm/contest/80742/B

定义两个数组$a$和$b$的汉明距离为：有多少个下标$i$满足$a_i≠b_i$。例如，$[2,3,1]$和$[1,3,1]$的汉明距离是$1$。
 现在小红拿到了一个长度为$n$的排列$p$，她希望你构造一个长度为$n$的排列$q$，满足$p$和$q$的汉明距离恰好等于$k$。

 排列指长度为$n$的数组，其中$1$到$n$每个元素恰好出现了一次

> 若$k > n $ 或者$k=1$，显然无解。
>
> 因为每个元素都只出现一次，交换两个无汉明距离的元素必定产生增加汉明距离， 交换一个有汉明距离的元素和一个无汉明距离的元素必定增加一个汉明距离。
>
> 遍历序列，设第$i$个数为$a_i$，不断让$a_i$和$a_{i+1}$交换，交换$k-1$次即可。
>
> ——第一次交换产生 2 个距离，后面每一次产生 1 个距离。

```java
import java.util.*;
public class Main {
    static Scanner s = new Scanner(System.in);
    static int n, k, a[];
    public static void main(String[] args){
    	n = s.nextInt();
    	k = s.nextInt();
    	a = new int[n];
    	if(k > n || k == 1) {
    		System.out.print(-1);
    		return;
    	}
    	for(int i = 0; i<n; i++) a[i] = s.nextInt();
    	for(int i = 0; i<=k - 2; i++) {
    		int t = a[i];
    		a[i] = a[i + 1];
    		a[i + 1] = t;
    	}
    	for(int i = 0; i<n; i++) System.out.print(a[i] + " ");
    }
}
```





**C. 小红的循环移位**

https://ac.nowcoder.com/acm/contest/80742/C

小红拿到了一个数字串，她每次操作可以使得其向左循环移动一位。
 将串 $s=s_0s_1...s_{n−1}$​ 向左循环移动一位，将得到串$s_1...s_{n−1}s_0$
 小红想知道，使得该数字串变成4的倍数，需要最少操作多少次？（可以包含前导零）

> 枚举一下 4 的倍数：4  8  **12  16  20  24  28  32  36  40 44 ......**
>
> 发现规律：若数字位数大于1位，若个位数字为 2/6且高一位为奇数，则为4的倍数；若个位数字为4/8/0且高一位为偶数，则为4的倍数。
>
> 有了该规律就可以遍历一遍的情况下找出最小操作次数。
>
> 1. 特判一下一位数
> 2. 先判断 0 次操作是否可行。
> 3. 再判断$1 \sim n-1$次操作，设$a_i$为从左往右第$i$位数字，若$a_i$与$a_{i - 1}$满足规律，则需要操作$i$次
> 4. 若都不行则输出$ - 1$
>
> 时间复杂度：$O(n)$

```java
import java.util.*;
public class Main {
    static Scanner s = new Scanner(System.in);
    public static void main(String[] args){
    	String num = s.next();
    	int n = num.length();   	
    	//特判 一位数
    	if(n == 1) {
    		int x = num.charAt(0) - '0';
    		if(x == 4 || x == 8) {
    			System.out.print(0);return;
    		}else {
    			System.out.print(-1);return;
    		}
    	}
    	// 0 次操作
    	int a = num.charAt(n - 2) - '0';
		int b = num.charAt(n - 1) - '0';
		if(isVlid(a, b)) {System.out.print(0);return;}
		// 1 ~ n - 1次操作
    	for(int i = 0; i<=n - 2; ++ i) {
     		a = num.charAt(i - 1 < 0 ? n - 1 : 0) - '0';//
    		b = num.charAt(i) - '0';
    		if(isVlid(a, b)) {
    			System.out.print(i + 1);return;
    		}
    	}
    	System.out.print(-1);
    }
    public static boolean isVlid(int a, int b) {
    	if(a % 2 == 0) {
			if(b == 4 || b == 8 || b == 0) {
				return true;
			}
		}else {
			if(b == 2 || b == 6) {
				return true;
			}
		}
    	return false;
    }
}
```

