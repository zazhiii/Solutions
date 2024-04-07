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

# C

$C^0_n+C^1_n+C^2_n+⋯+C^n_n=2^n$

$C^0_n+C^2_n+C^4_n+⋯=C^1_n+C^3_n+C^5_n+⋯=2^{n−1}$

```java
import java.math.BigInteger;
import java.util.*;
public class Main {
	static int T;
	static BigInteger MOD = new BigInteger("1000000007"), res[], TOW = new BigInteger("2");
	public static void main(String[] args) {
		 Scanner s = new Scanner(System.in);
		 T = s.nextInt();
		 res = new BigInteger[T];
		 //T组数据
		 for(int i = 0; i<=T - 1; i++) {
			 int n = s.nextInt();
			 int[] a = new int[n];
			 int ji = 0, ou = 0;//奇、偶数个数
			 for(int j = 0; j<n; j++) {
				 a[j] = s.nextInt();
				 if(a[j]%2 == 0) ou ++;
				 else ji ++;
			 }
			 if(ji%2 != 0) {// 奇数个数奇数，没有方案
				 res[i] = new BigInteger("0");
				 continue;
			 }
			 ji = ji == 0?1:ji;//
			 res[i] = TOW.pow(ou+ji-1).mod(MOD);			 
		 }
		 for(int i = 0; i<=T-1; i++) {
			 System.out.println(res[i]);
		 }		 
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





# E

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
import java.util.Scanner;
public class Main {	
    public static void main(String[] args) {
        Scanner s = new Scanner(System.in);
        int N = s.nextInt();
        int[] X = new int[N+1];
        int[] A = new int[N+1];
        int[] B = new int[N+1];
        for (int i = 1; i <=N; i++) {
			X[i] = s.nextInt(); 
		}
        for (int i = 1; i <=N-1; i++) {
			A[i] = s.nextInt();
			B[i+1] = s.nextInt();
		}
        double [][] dp = new double [2][N+1];
        dp[0][1] = X[1];
        dp[1][1] = X[1]+A[1]/0.7;
        
        for (int j = 2; j <=N; j++) {
        	dp[0][j] = Math.min(dp[0][j-1]+X[j]-X[j-1],dp[1][j-1]+B[j]/1.3); 
        	dp[1][j]= Math.min(dp[0][j-1]+X[j]-X[j-1]+A[j]/0.7,dp[1][j-1]+Math.abs(A[j]-B[j])/(A[j]>B[j]?0.7:1.3)); 
		}

        System.out.printf("%.2f",dp[0][N]);
        }
    }

```

