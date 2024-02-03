# P1605迷宫

[P1605 迷宫 - 洛谷 | 计算机科学教育新生态 (luogu.com.cn)](https://www.luogu.com.cn/problem/P1605)

给定一个 $N \times M$ 方格的迷宫，迷宫里有 $T$ 处障碍，障碍处不可通过。

在迷宫中移动有上下左右四种方式，每次只能移动一个方格。数据保证起点上没有障碍。

给定起点坐标和终点坐标，每个方格最多经过一次，问有多少种从起点坐标到终点坐标的方案。

> tags: DFS、回溯

Ideas:

> DFS**四个方向**搜索路径，搜索过的地方标记，搜到终点了则方案+1
>
> 边界处理方法：
>
> 1. 将边界初始化为障碍
> 2. 在搜索函数里判断x，y的范围

```java
import java.util.Scanner;
public class Main {
	static int N,M,T,SX,SY,FX,FY;
	static int[][] arr;
	static int res = 0;
	public static void main(String[] args) {
	   Scanner s = new Scanner(System.in);
	   N = s.nextInt();
	   M = s.nextInt();
	   T = s.nextInt();
	   SX = s.nextInt();
	   SY = s.nextInt();
	   FX = s.nextInt();
	   FY = s.nextInt();
	   arr = new int[N+2][M+2];
	   for (int i = 1; i <=T; i++) {
		   int x = s.nextInt();
		   int y = s.nextInt();
		   arr[x][y]= 1; 
	   }
	   for (int i = 0; i <=N+1; i++) {
		   arr[i][0] = 1;
		   arr[i][M+1] = 1;
	   }
	   for (int i = 0; i <=M+1; i++) {
		   arr[0][i] = 1;
		   arr[N+1][i] = 1;
	   }
	   dfs(SX,SY);
	   System.out.println(res);
    }
	
	public static void dfs(int cx,int cy) {
		if (arr[cx][cy]==1) {//当前位置是障碍直接回溯
			return;
		}
		if (cx==FX&&cy==FY) {
			res++;
			return;
		}
			arr[cx][cy] = 1;
			dfs(cx+1, cy);
			dfs(cx-1, cy);
			dfs(cx, cy+1);
			dfs(cx, cy-1);
			arr[cx][cy] = 0;		
	}
}

```



# P1036选数

[P1036 [NOIP2002 普及组] 选数 - 洛谷 | 计算机科学教育新生态 (luogu.com.cn)](https://www.luogu.com.cn/problem/P1036)

已知 $n$ 个整数 $x_1,x_2,\cdots,x_n$，以及 $1$ 个整数 $k$（$k<n$）。从 $n$ 个整数中任选 $k$ 个整数相加，可分别得到一系列的和。现在，要求你计算出和为素数共有多少种。

> tags: DFS、质数判断

Ideas:

> 选数

```java
import java.util.Scanner;
public class Main {
	static int N, K;
	static int[] arr;
	static int count=0;
	static int res=0;
	static int sum=0;
    public static void main(String[] args) {
    	Scanner s = new Scanner(System.in);
    	N = s.nextInt();
    	K = s.nextInt();
    	arr = new int[N];
    	for (int i = 0; i <=N-1; i++) {
			arr[i]= s.nextInt(); 
		}
    	back(N,K,0);
    	System.out.println(res);    	
    }
    public static void back(int N, int K,int startIdx) {
    	if (count==K) {
			if (isPrime(sum)) {				
				res++;
			}
			return;
		}
    	for (int i = startIdx; i <=N-(K-count); i++) {   		
			sum+=arr[i];
			count++;
    		back(N, K, i+1);
    		sum-=arr[i];
			count--;
		}	
    }
	private static boolean isPrime(int n) {//质数判断
		if (n==1) return false;
		if (n==2) return true;
		for (int i = 2; i <=Math.sqrt(n); i++) {
			if (n%i==0) return false;
		}
		return true;
	}
}

```

# P2404自然数的拆分问题

[P2404 自然数的拆分问题 - 洛谷 | 计算机科学教育新生态 (luogu.com.cn)](https://www.luogu.com.cn/problem/P2404)

任何一个大于 $1$ 的自然数 $n$，总可以拆分成若干个小于 $n$ 的自然数之和。现在给你一个自然数 $n$，要求你求出 $n$ 的拆分成一些数字的和。每个拆分后的序列中的数字从小到大排序。然后你需要输出这些序列，其中字典序小的序列需要优先输出。

> tags:  dfs、选数

Ideas:

> 基础dfs：
>
> 第一层在$[1,n]$中选$t1$，第二层在$[t1,n]$中选……直到$t_1+t_2+...+t_m=n$.
>
> 记录三个数据：到$k$层$t_1+t_2+...+t_k$:`sum`、选了几个数`count`、存放$t_1、t_2...t_m$的数组`arr`
>
> 优化：
>
> 在每一层选数时与`sum`相加大于$n$的数就不可能选之，所以选数范围有所优化：
>
> 第一层在$[1,n]$中选$t1$，第二层在$\mathbf{[t1,n-sum]}$中选……直到$t_1+t_2+...+t_m=n$.

![image-20240204012716956](C:\Users\LXH15\AppData\Roaming\Typora\typora-user-images\image-20240204012716956.png)

```java
import java.util.Scanner;
public class Main {
	static int N;
	static int[] arr;
	static int sum;
	public static void main(String[] args) {
       //读数据
	   Scanner s = new Scanner(System.in);
	   N = s.nextInt();
	   arr = new int[N];
	   back(1,0);
    }
	//t:当前选择数大小
	public static void back(int t,int count) {
		if (sum==N&&count!=1) {
			String str = "";
			for (int i = 0; i <=count-2; i++) {
				str+=arr[i]+"+";
			}
			str+=arr[count-1];
			System.out.println(str);
			return;
		}
		//t~N-sum 剪枝
		for (int i = t; i <=N-sum; i++) {
			sum+=i;
			arr[count] = i;
			back(i,count+1);
			sum-=i;
			arr[count]=0;			
		}
	}
	}
```

# P1596 Lake Counting S

[P1596 [USACO10OCT\] Lake Counting S - 洛谷 | 计算机科学教育新生态 (luogu.com.cn)](https://www.luogu.com.cn/problem/P1596)

由于近期的降雨，雨水汇集在农民约翰的田地不同的地方。我们用一个 $N\times M(1\leq N\leq 100, 1\leq M\leq 100)$ 的网格图表示。每个网格中有水（`W`） 或是旱地（`.`）。一个网格与其周围的八个网格相连，而一组相连的网格视为一个水坑。约翰想弄清楚他的田地已经形成了多少水坑。给出约翰田地的示意图，确定当中有多少水坑。

> tags: DFS

Ideas:

> **DFS求连通块**；遍历区域，遇到`W`水坑数+1，从此处开始**八个方向**dfs，搜索出所有的连通域改为`.`，无需回溯（防止之后的遍历遇到该水坑的`W`.

```java

import java.util.Scanner;
public class Main {
	static int N,M;
	static char[][] arr;
	static int res = 0;
	public static void main(String[] args) {
       //读数据
	   Scanner s = new Scanner(System.in);
	   N = s.nextInt();
	   M = s.nextInt();
	   s.nextLine();
	   arr = new char[N][M];
	   for (int i = 0; i <=N-1; i++) {
		arr[i]=s.next().toCharArray();
	}
	   //----
	   for (int i = 0; i <=N-1; i++) {
		   for (int j = 0; j <=M-1; j++) {
			   if (arr[i][j]=='W') {
				   res++;
				   b(i,j);
			}
		}
	}
	   System.out.println(res);
}
	private static void b(int x, int y) {
		if (x<0||x>N-1||y<0||y>M-1) {//限制边界
			return;
		}
		if (arr[x][y]=='.') {
			return;
		}
		arr[x][y]='.';
		b(x+1, y);
		b(x-1, y);
		b(x, y+1);
		b(x, y-1);
		b(x+1, y+1);
		b(x+1, y-1);
		b(x-1, y+1);
		b(x-1, y-1);	
	}
	}
```

# P1162 填涂颜色

[P1162 填涂颜色 - 洛谷 | 计算机科学教育新生态 (luogu.com.cn)](https://www.luogu.com.cn/problem/P1162)

由数字 $0$ 组成的方阵中，有一任意形状的由数字 $1$ 构成的闭合圈。现要求把闭合圈内的所有空间都填写成 $2$。例如：$6\times 6$ 的方阵（$n=6$），涂色前和涂色后的方阵如下：

如果从某个 $0$ 出发，只向上下左右 $4$ 个方向移动且仅经过其他 $0$ 的情况下，无法到达方阵的边界，就认为这个 $0$ **在闭合圈内**。闭合圈不一定是环形的，可以是任意形状，但保证**闭合圈内**的 $0$ 是连通的（两两之间可以相互到达）。

> tag: DFS

Ideas：

> DFS求连通块；要操作闭合圆环内的区域，转换思路，操作圆环内区域的补集---圆环外的区域，没有接触边界的区域定义为圆环内的区域，则圆环外的区域一定是接触边界的，将边界初始化为圆环外的区域，那么所有圆环外的区域就变成了一个连通域，从区域的一角`dfs`则可以搜索出所有的圆环外的区域，无需回溯，全部使之变为2；输出时对2和0的元素操作即可。对于是1的元素直接输出，其他元素：`arr[i][j]`取负再+2即可将2变为0，0变为2。即得所求。

```java

import java.util.Scanner;
public class Main {
	static int N;
	static int[][] arr;
	public static void main(String[] args) {
       //读数据
	   Scanner s = new Scanner(System.in);
	   N = s.nextInt();
	   arr = new int[N+2][N+2];
	   for (int i = 1; i <=N; i++) {
		   for (int j = 1; j <=N; j++) {
			   arr[i][j] = s.nextInt();  
		}
	}
	   //----
	   bfs(0,0);
	   for (int i = 1; i <=N; i++) {
		   for (int j = 1; j <=N; j++) {
			   if (arr[i][j]!=1) {
				   System.out.print((-arr[i][j]+2)+" ");
			   }else {
				   System.out.print(1+" ");
			}
			}
		   System.out.println();
		}		   
	}	  
	private static void bfs(int x, int y) {
		if (x<0||x>N+1||y<0||y>N+1) {//防止越界
			return;
		}
		if (arr[x][y]!=0) {
			return;
		}
		arr[x][y]=2;
		bfs(x+1, y);
		bfs(x-1, y);
		bfs(x, y+1);
		bfs(x, y-1);
	}
}
```

