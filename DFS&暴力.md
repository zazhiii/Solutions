# 关于二维的DFS

## P1605迷宫

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
	static int N, M, T, SX, SY, FX, FY, a[][], res = 0;
	static int[] dx = {1, -1, 0, 0};
	static int[] dy = {0, 0, 1, -1};
	public static void main(String[] args){
		Scanner s = new Scanner(System.in);
		N = s.nextInt();
		M = s.nextInt();
		T = s.nextInt();
		a = new int[N+1][M+1];
		SX = s.nextInt();
		SY = s.nextInt();
		FX = s.nextInt();
		FY = s.nextInt();
		for(int i = 0; i <= T-1; i++) {
			int ox = s.nextInt();
			int oy = s.nextInt();
			a[ox][oy] = 1; 
		}
		dfs(SX, SY);
		System.out.println(res);
	}
	private static void dfs(int x, int y) {
		if (x < 1||x > N||y < 1||y > M||a[x][y] == 1) {//防止越界 遇到障碍返回
			return;
		}
		if (x == FX&&y == FY) {
			res++;
			return;
		}
		a[x][y] = 1;//走过的地方标记为障碍，防止走回来
		for(int i = 0; i<=3; i++) {//四个方向dfs
			dfs(x + dx[i], y + dy[i]);
		}
		a[x][y] = 0;//记得恢复状态
	}	
}

```

## P1596 Lake Counting S

[P1596 [USACO10OCT\] Lake Counting S - 洛谷 | 计算机科学教育新生态 (luogu.com.cn)](https://www.luogu.com.cn/problem/P1596)

由于近期的降雨，雨水汇集在农民约翰的田地不同的地方。我们用一个 $N\times M(1\leq N\leq 100, 1\leq M\leq 100)$ 的网格图表示。每个网格中有水（`W`） 或是旱地（`.`）。一个网格与其周围的八个网格相连，而一组相连的网格视为一个水坑。约翰想弄清楚他的田地已经形成了多少水坑。给出约翰田地的示意图，确定当中有多少水坑。

> tags: DFS

Ideas:

> **DFS求连通块**；遍历区域，遇到`W`水坑数+1，从此处开始**八个方向**dfs，搜索出所有的连通域改为`.`，无需回溯（防止之后的遍历遇到该水坑的`W`）.

```java
import java.util.Scanner;
public class Main {
	static int N, M, res = 0;
	static char[][] a;
	static int[] dx = {-1, -1, -1, 0, 1, 1, 1, 0};
	static int[] dy = {-1, 0, 1, 1, 1, 0, -1, -1};
	public static void main(String[] args){
		Scanner s = new Scanner(System.in);
		N = s.nextInt();
		M = s.nextInt();
		a = new char[N][M];
		for(int i = 0; i <= N - 1; i ++) {
			a[i] = s.next().toCharArray(); 
		}
		for(int i = 0; i<=N-1;i++) {
			for(int j = 0; j<=M-1;j++) {
				if (a[i][j] == 'W') {
					res++;
					dfs(i, j);
				}
			}
		}
		System.out.println(res);
	}
    
	private static void dfs(int i, int j) {
		if (i < 0 || i > N - 1 || j < 0 || j > M - 1||a[i][j] == '.') {//越界 遇到不是水坑
			return;
		}		
		a[i][j] = '.'; 
		for(int k = 0; k<=7; k++) {//八个方向
			dfs(i + dx[k], j + dy[k]);
		}
	}	
}
```

# 

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

![image-20240204012716956](images/image-P2404.png)

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
	//t:上一层选择数大小
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

# P1219八皇后 Checker Challenge

[P1219 [USACO1.5\] 八皇后 Checker Challenge - 洛谷 | 计算机科学教育新生态 (luogu.com.cn)](https://www.luogu.com.cn/problem/P1219)

一个如下的 $6 \times 6$ 的跳棋棋盘，有六个棋子被放置在棋盘上，使得每行、每列有且只有一个，每条对角线（包括两条主对角线的所有平行线）上至多有一个棋子。

![](https://cdn.luogu.com.cn/upload/image_hosting/3h71x0yf.png)

上面的布局可以用序列 $2\ 4\ 6\ 1\ 3\ 5$ 来描述，第 $i$ 个数字表示在第 $i$ 行的相应位置有一个棋子，如下：

行号 $1\ 2\ 3\ 4\ 5\ 6$

列号 $2\ 4\ 6\ 1\ 3\ 5$

这只是棋子放置的一个解。请编一个程序找出所有棋子放置的解。  
并把它们以上面的序列方法输出，解按字典顺序排列。  
请输出前 $3$ 个解。最后一行是解的总个数。

> tags: DFS

Ideas:

> 第一行选一个位置放置，再在第二行选择一个合法位置...第N行...
>
> 记录棋子放置数量`n`，当`n==N`则为一个解；`n`不能`==N`则回溯找其他位置
>
> 用`used`数组标记哪些地方不能放棋子，（标记此处`used[i][j]+1`, 撤销标记则`used[i][j]-1`，这样不会将其他棋子得标记撤销,用`boolean`出现了这样的问题！）

![image-20240204114906712](images/image-P1219.png)

```java
import java.util.Scanner;
public class Main {
	static int N;
	static int res = 0;
	static int[][] used;//标记位置是否能使用
	static int[] p;//结果数组
	public static void main(String[] args) {
       //读数据
	   Scanner s = new Scanner(System.in);
	   N = s.nextInt();
	   used = new int[N][N];
	   p=new int[N];
	   //	   
	   bfs(0);
	   System.out.println(res);
	}	
	//n记录当前摆放了几个棋子
	private static void bfs(int n) {
		if (n>N-1) {
			if (res<=2) {//前三次
				for (int i = 0; i <=N-1; i++) {
					System.out.print(p[i]+" ");
				}
				System.out.println();
			}
			res++;
			return;
		}
		for (int k = 0; k <=N-1; k++) {//每一行选位置摆放棋子
			if (used[n][k]!=0) {
				continue;
			} 			
				fillUsed(n,k,1);
				p[n]=k+1;
				bfs(n+1);
				p[n]=0; 
				fillUsed(n,k,-1);			
		}		
	}
	private static void fillUsed(int i, int j, int b) {//标记行列对角线
		for (int k = i; k <=N-1; k++) {
			used[k][j]+=b;
		}
		int x = i;
		int y = j;
		while(y<=N-1&&x<=N-1) {
			used[x++][y++]+=b;
		}
		x = i;
		y = j;
		while(y>=0&&x<=N-1){
			used[x++][y--]+=b;
		}		
	}
}
```

# P2036 PERKET

[P2036 [COCI2008-2009 #2\] PERKET - 洛谷 | 计算机科学教育新生态 (luogu.com.cn)](https://www.luogu.com.cn/problem/P2036)

Perket 是一种流行的美食。为了做好 Perket，厨师必须谨慎选择食材，以在保持传统风味的同时尽可能获得最全面的味道。你有 $n$ 种可支配的配料。对于每一种配料，我们知道它们各自的酸度 $s$ 和苦度 $b$。当我们添加配料时，总的酸度为每一种配料的酸度总乘积；总的苦度为每一种配料的苦度的总和。

众所周知，美食应该做到口感适中，所以我们希望选取配料，以使得酸度和苦度的绝对差最小。

另外，我们必须添加至少一种配料，因为没有任何食物以水为配料的。

> tags: DFS

Ideas:

> 简单的DFS；所有食材当作一层，一次选取一个食材，记录目前选取的食材的酸度`sour`、苦度`bitter`, 记录最小值。

![image-20240204132617367](images/image-P2036.png)

```java
import java.util.Scanner;
public class Main {
	static int N;
	static int[] sour,bitter;
	static int allSour=1,allBitter=0;
	static int res = 100000000;
	public static void main(String[] args) {
       //读数据
	   Scanner s = new Scanner(System.in);
	   N = s.nextInt();
	   sour = new int[N];
	   bitter = new int[N];
	   for (int i = 0; i <=N-1; i++) {
		sour[i] = s.nextInt();
		bitter[i] = s.nextInt(); 
	}
	   //
	   dfs(0);
	   System.out.println(res);
	}
	public static void dfs(int startIdx) {
		if (startIdx>N-1) {
			return;
		}
		for (int i = startIdx; i <=N-1; i++) {
			allSour*=sour[i];
			allBitter+=bitter[i];
			res = Math.min(res, Math.abs(allBitter-allSour));
			dfs(i+1);
			allSour/=sour[i];
			allBitter-=bitter[i];
		}
	}
}
```

---

# P1618三连击（升级版）

[P1618 三连击（升级版） - 洛谷 | 计算机科学教育新生态 (luogu.com.cn)](https://www.luogu.com.cn/problem/P1618)

将 $1, 2,\ldots, 9$ 共 $9$ 个数分成三组，分别组成三个三位数，且使这三个三位数的比例是 $A:B:C$，试求出所有满足条件的三个三位数，若无解，输出 `No!!!`。

> tags: DFS、全排列

Ideas：

> 全排列之后分为三组

```java
import java.util.Scanner;
public class Main {
	static int A,B,C;
	static boolean[] used = new boolean[11];
	static int num = 0;
	static int flag = 0;
	public static void main(String[] args) {
       //读数据
	   Scanner s = new Scanner(System.in);
	   A = s.nextInt();
	   B = s.nextInt();
	   C = s.nextInt();	   
	   dfs(1);
	   if (flag==0) {
		System.out.println("No!!!");
	}
	}	
	public static void dfs(int idx) {
		if (idx>9) {
			//输出
			double a = num/1000000;
			double b = num%1000000/1000;
			double c = num%1000;
			if (a/b==(double)A/B&&b/c==(double)B/C&&a%A==0) {
				flag++;
				System.out.print((int)a+" "+(int)b+" "+(int)c+"\n");
			}
		return;
		}		
		for(int i = 1;i<=9;i++) {
			if (used[i]==true) {
				continue;
			}
			num = num*10+i;
			used[i]=true; 
			dfs(idx+1);
			num = (num-i)/10;
			used[i]= false; 
		}
	}
}
```

# P1088火星人

[P1088 [NOIP2004 普及组\] 火星人 - 洛谷 | 计算机科学教育新生态 (luogu.com.cn)](https://www.luogu.com.cn/problem/P1088)

火星人用一种非常简单的方式来表示数字――掰手指。火星人只有一只手，但这只手上有成千上万的手指，这些手指排成一列，分别编号为 $1,2,3,\cdots$。火星人的任意两根手指都能随意交换位置，他们就是通过这方法计数的。

一个火星人用一个人类的手演示了如何用手指计数。如果把五根手指――拇指、食指、中指、无名指和小指分别编号为 $1,2,3,4$ 和 $5$，当它们按正常顺序排列时，形成了 $5$ 位数 $12345$，当你交换无名指和小指的位置时，会形成 $5$ 位数 $12354$，当你把五个手指的顺序完全颠倒时，会形成 $54321$，在所有能够形成的 $120$ 个 $5$ 位数中，$12345$ 最小，它表示 $1$；$12354$ 第二小，它表示 $2$；$54321$ 最大，它表示 $120$。下表展示了只有 $3$ 根手指时能够形成的 $6$ 个 $3$ 位数和它们代表的数字：

现在你有幸成为了第一个和火星人交流的地球人。一个火星人会让你看他的手指，科学家会告诉你要加上去的很小的数。你的任务是，把火星人用手指表示的数与科学家告诉你的数相加，并根据相加的结果改变火星人手指的排列顺序。输入数据保证这个结果不会超出火星人手指能表示的范围。

共三行。  
第一行一个正整数 $N$，表示火星人手指的数目（$1 \le N \le 10000$）。  
第二行是一个正整数 $M$，表示要加上去的小整数（$1  \le  M  \le  100$）。  
下一行是 $1$ 到 $N$ 这 $N$ 个整数的一个排列，用空格隔开，表示火星人手指的排列顺序。

> tags: 排列

Ideas:

> 所有数字全排列会超时；所以选择从$N$排列到$M$，关键在于在全排列中找到$N$代表的排列（直接对$N$代表的排列最全排列的话会有错误，eg：1 3 2当作起始排列的话，下一排列为1 2 3 ，与题意不符合）
>
> 如何控制排列在$N--M$之间？
>
> 第一：跳过之前的排列
>
> 首先我们用`count`记录完成排列的次数，`idx`记录排列到第几个数。
>
> 我们从$1，2，... M$开始排列，快速找到$N$所代表的排列。记$N$所代表的排列为`init[]`
>
> 在第一次排列中也就是`count==0`时，我们在选数出来排列时，如果当前选择的数字(`i`)与`init[]`在当前位置(`idx`)不同那么则快速跳过这个数字（`continue`），这样能够最快速定位`init[]`，以便继续后续往后排列
>
> 第二：取消之后的排列
>
> 在`count==M`时，我们就得到了所求得排列，后面得排列则可以一并跳过，即`return`
>
> ---
>
> tips: 有个数据点要用快速读写

```java
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.io.StreamTokenizer;
public class Main {
	static int N,M;
	static int[] init,used,arr;
	static int count=0;
	static Reader rd = new Reader(); 
	static PrintWriter pw = new PrintWriter(new BufferedWriter(new OutputStreamWriter(System.out)));
	public static void main(String[] args) throws IOException {
       //读数据
	   N = rd.nextInt();
	   M = rd.nextInt();
	   init = new int[N];
	   used = new int[N];
	   arr = new int[N];
	   for (int i = 0; i <=N-1; i++) {
		init[i]= rd.nextInt(); 
	   }
	   dfs(0);	   
	}	
	public static void dfs(int idx) {
		if (count>M) {
			return;
			}
		if (idx==N) {			
			if (count==M) {
				for (int i = 0; i <=N-1; i++) {
					pw.print(arr[i]+" ");
				}
				pw.flush();
			}				
			count++;
			return;
			}	
		for (int i = 0; i <=N-1; i++) {
			if (used[i]==1||i+1!=init[idx]&&count==0) {
				continue;
			}
			arr[idx]=i+1;
			used[i]= 1;
			dfs(idx+1);
			arr[idx]=0;
			used[i]=0; 			
		}
		}	
}
 class Reader {
	StreamTokenizer st = new StreamTokenizer(new BufferedReader(new InputStreamReader(System.in)));
	public int nextInt() throws IOException {
		st.nextToken();
		return (int)st.nval;
	}
}
```

# P1101单词方阵

给一 $n \times n$ 的字母方阵，内可能蕴含多个 `yizhong` 单词。单词在方阵中是沿着同一方向连续摆放的。摆放可沿着 $8$ 个方向的任一方向，同一单词摆放时不再改变方向，单词与单词之间可以交叉，因此有可能共用字母。输出时，将不是单词的字母用 `*` 代替，以突出显示单词。

```java
import java.util.Scanner;
public class Main {
	static String target = "yizhong";
	static int n;
	static String[] strs;
	static int[] dx = {-1, -1, -1, 0, 1, 1, 1, 0};
	static int[] dy = {-1, 0, 1, 1, 1, 0, -1, -1};
	static int[] xs = new int[7], ys = new int[7];//记录搜索路径
	static char[][] res;//记录结果
	public static void main(String[] args){
		Scanner s = new Scanner(System.in);
		n = s.nextInt();
		res = new char[n][n];
		strs = new String[n];
		for(int i = 0; i <= n-1; i++) {
			strs[i] = s.next(); 
		}
		
		for (int i = 0; i <=n-1; i++) {
			for(int j = 0;j <= n-1; j++) {
					for(int k = 0; k<=7; k++) {//八个方向
						dfs(i, j, dx[k], dy[k], 0);
				}
			}
		}
		for (int i = 0; i <=n-1; i++) {//输出答案
			for(int j = 0; j<=n-1; j++) {
				System.out.print(res[i][j]==0?'*':res[i][j]);
			}
			System.out.println();
		}
}
	public static void dfs(int x, int y, int dx, int dy, int Idx) {
		if (Idx==7) {//处理答案	
			for (int k = 0; k <=6; k++) {
				res[xs[k]][ys[k]] = target.charAt(k); 
			}
			return;
		}
		//越界 或 跟目标字符串不匹配了
		if (x<0||x>n-1||y<0||y>n-1||strs[x].charAt(y)!=target.charAt(Idx)) {
			return;
		}
		xs[Idx] = x;
		ys[Idx] = y; 
		dfs(x+dx, y+dy, dx, dy, Idx+1);
		xs[Idx] = 0;
		ys[Idx] = 0; 		
	}	
}
```

