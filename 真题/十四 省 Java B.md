# A

> 阶乘，（有点点陷阱，39！之后的阶乘数后9位不变）

```java
public class Main {
    public static void main(String[] args) {
    		long L = Long.parseLong("202320232023");
        	long sum=0;
        	long mal=1;
        	for(long i=1;i<=L;i++) {
        		mal*=i;
        		mal%=(long)1e9;
        		sum+=mal;
        		sum%=(long)1e9;
        	}        	
        	System.out.println(sum%(long)1e9);        	      
    }
}
```



# B

> 进制转换

```java
public class Main {
	public static void main(String[] args) {
		int j  = 0;//第j个幸运数
		for (int i = 1; i <=1145144; i++) {
			if(isF(i)) {
				j++;
				System.out.println("第"+j+"个:"+i);
				if (j==2023) {
					break;
				}
			}
		}
	}
	private static boolean isF(int i) {
		int x = i;
		//二进制
		int sum = 0;
		while(x>0) {
			sum+=(x%2);
			x/=2;
		}
		if (i%sum!=0) return false;	
		//八进制
		x=i;sum=0;
		while(x>0) {
			sum+=(x%8);
			x/=8;
		}
		if (i%sum!=0) return false;
		
		//十进制
		x=i;sum=0;
		while(x>0) {
			sum+=(x%10);
			x/=10;
		}
		if (i%sum!=0) return false;		
		//十进制
		x=i;sum=0;
		while(x>0) {
			sum+=(x%16);
			x/=16;
		}
		if (i%sum!=0) return false;			
		return true;
	}
}
```

# C数组分割

小蓝有一个长度为 N 的数组 A = [A0, A1,..., AN−1]。现在小蓝想要从 A 对应的数组下标所构成的集合 I = {0, 1, 2, . . . , N − 1} 中找出一个子集 R1，那么 R1在 I 中的补集为 R2。记 S1=∑r∈R1Ar，S2 =∑r∈R2Ar，我们要求 S1 和 S2 均为偶数，请问在这种情况下共有多少种不同的 R1。当 R1 或 R2 为空集时我们将 S1 或 S2 视为 0。

> 组合数学、快速幂

> 记录奇数和偶数的个数，若奇数个数为奇数则不可能分成两个和为偶数的子集。
>
> 假设从中选出若干偶数若干奇数作为一个子集，偶数有$C^0_n+C^1_n+C^2_n+⋯+C^n_n=2^n$种选法，而奇数有$C^0_n+C^2_n+C^4_n+⋯=C^1_n+C^3_n+C^5_n+⋯=2^{n−1}$种选法。将两者相乘即可。

> $O(n)$

```java
import java.io.*;
import java.util.*;

public class Main {
    static Scanner sc = new Scanner(System.in);
    static PrintWriter pw = new PrintWriter(new BufferedWriter(new OutputStreamWriter(System.out)));
    static int n, a[], mod = 1000000007;
    public static void solve(){
        n = sc.nextInt();
        long e = 0, o = 0;
        for(int i = 0; i < n; i ++){
            int x = sc.nextInt();
            if(x % 2 == 0) e ++;
            else o ++;
        }
        if(o % 2 == 1){pw.println(0);return;}
        if(o == 0) o ++;
        pw.println(qpow(2, e + o - 1));
    }
    public static long qpow(long a, long n){
        a %= mod;
        long ans = 1;
        while(n > 0){
            if(n % 2 == 1) ans = ans * a % mod;
            a = a * a % mod;
            n >>>= 1;
        }
        return ans;
    }
    public static void main(String[] args) throws IOException {
        int T = sc.nextInt();
        while(T --> 0) solve();
        pw.flush();pw.close();
    }
}

```

# D(WA)

```java
package algorithm;

import java.util.*;
public class Main {
	static int x1, y1, x2, y2, x3, y3, x4, y4;
	static long s1, s2, repe = 0, res;
	public static void main(String[] args) {
		 Scanner s = new Scanner(System.in);
		 x1 = s.nextInt();
		 y1 = s.nextInt();
		 x2 = s.nextInt();
		 y2 = s.nextInt();
		 x3 = s.nextInt();
		 y3 = s.nextInt();
		 x4 = s.nextInt();
		 y4 = s.nextInt();
		 s1 = (x2-x1)*(y2-y1);
		 s2 = (x4-x3)*(y4-y3);
		 case1();
		 case2();
		 case3();
		 case4();
		 case5();//一个矩形在另一个矩形内
		 case6();//
		 res = s1+s2-repe;
		 System.out.print(res);
		
	}
	
	private static void case6() {
		if(x3>=x1&&x4<=x2&&y3<=y1&&y4>=y2)repe = (x4-x3)*(y2-y1);
		if(y3>=y1&&y4<=y2&&x3<=x1&&x4>=x2)repe = (y4-y3)*(x2-x1);
		
	}

	private static void case5() {
		if(x2<=x4&&x1>=x3&&y2<=y4&&y1>=y3) {
			repe = s1;
		}
		if(x4<=x2&&x3>=x1&&y4<=y2&&y3>=y1) {
			repe = s2;
		}
	}
	

	public static void case1() {
		if(x3>=x1&&x3<=x2&&y3>=y1&&y3<=y2) {
			repe = Math.abs(x2-x3)*Math.abs(y2-y3);
		}
	}
	public static void case2() {
		if(x4>=x1&&x4<=x2&&y4>=y1&&y4<=y2) {
			repe = Math.abs(x4-x1)*Math.abs(y4-y1);
		}
	}
	public static void case3() {
		if(x3>=x1&&x3<=x2&&y4>=y1&&y4<=y2) {
			repe = Math.abs(x2-x3)*Math.abs(y4-y1);
		}
	}
	public static void case4() {
		if(x4>=x1&&x4<=x2&&y3>=y1&&y3<=y2) {
			repe = Math.abs(x4-x1)*Math.abs(y2-y3);
		}
	}
}
```





# E蜗牛

这天，一只蜗牛来到了二维坐标系的原点。

在 x 轴上长有 n 根竹竿。它们平行于 y 轴，底部纵坐标为 0，横坐标分别为 x1, x2, ..., xn。竹竿的高度均为无限高，宽度可忽略。蜗牛想要从原点走到第 n 个竹竿的底部也就是坐标 (xn, 0)。它只能在 x 轴上或者竹竿上爬行，在 x 轴上爬行速度为 1 单位每秒；由于受到引力影响，蜗牛在竹竿上向上和向下爬行的速度分别为 0.7 单位每秒和 1.3 单位每秒。

为了快速到达目的地，它施展了魔法，在第 i 和 i + 1 根竹竿之间建立了传送门（0 < i < n），如果蜗牛位于第 i 根竹竿的高度为 ai 的位置 (xi , ai)，就可以瞬间到达第 i + 1 根竹竿的高度为 bi+1 的位置 (xi+1, bi+1)，请计算蜗牛最少需要多少秒才能到达目的地。

> tags: 动态规划

Ideas:

> 1. 确定`dp[i][j]`的含义：`dp[i][j]`代表到当前位置最短用时，`i`代表状态在传送门或地面，`j`代表杆子位置
> 2. 状态转移方程：
> 3. 遍历顺序：对于每一根杆子其上的传送点和地面最快到时间都分别与前一根杆子的传送点地面的最快到达时间有关，所以需要求出第`j`根杆子的两个状态值，才能求出下一个

<img src="images/image-2023E.png" alt="image-20240207021440159" style="zoom: 33%;" /><img src="images/image-2023E(2).png" alt="image-20240207022327723" style="zoom: 25%;" />

```java
import java.io.*;
import java.util.*;

public class Main {
    static Scanner sc = new Scanner(System.in);
    static PrintWriter pw = new PrintWriter(new BufferedWriter(new OutputStreamWriter(System.out)));
    static int n, x[], a[], b[];
    public static void main(String[] args) throws IOException {
        n = sc.nextInt();
        x = new int[n + 1];
        a = new int[n + 1];
        b = new int[n + 1];
        for(int i = 1; i <= n; i ++) x[i] = sc.nextInt();
        for(int i = 1; i <= n - 1; i ++) {
            a[i] = sc.nextInt();
            b[i] = sc.nextInt();
        }
        double dp[][] = new double[n + 1][2];
        dp[1][0] = x[1] + 0;
        dp[1][1] = x[1] + a[1] / 0.7;
        for(int i = 2; i <= n; i ++){
            double t1 = x[i] - x[i - 1] + dp[i - 1][0];
            double t2 = b[i - 1] / 1.3 + dp[i - 1][1];
            dp[i][0] = Math.min(t1, t2);
            if(b[i - 1] > a[i]){
                t1 = (b[i - 1] - a[i]) / 1.3 + dp[i - 1][1];
            }else{
                t1 = (a[i] - b[i -1]) / 0.7 + dp[i - 1][1];
            }
            t2 = (x[i] - x[i - 1]) + a[i] / 0.7 + dp[i - 1][0];
            dp[i][1] = Math.min(t1, t2);
        }
        pw.printf("%.2f", Math.min(dp[n][0], dp[n][1]));
        pw.flush();pw.close();
    }
}

```

