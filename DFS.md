# 排列问题

# 组合问题

## 77. 组合

[77. 组合](https://leetcode.cn/problems/combinations/)

给定两个整数 `n` 和 `k`，返回范围 `[1, n]` 中所有可能的 `k` 个数的组合。

你可以按 **任何顺序** 返回答案。

```java
class Solution {
    List<List<Integer>> res = new LinkedList<>();
    List<Integer> tmp = new LinkedList<>();

    public List<List<Integer>> combine(int n, int k) {
        dfs(1, k, n);
        return res;

    }

    public void dfs(int startIdx, int k, int n) {
        if (tmp.size() == k) {
            res.add(new LinkedList(tmp));
            return;
        }
        for (int i = startIdx; i <= n; i++) {
            tmp.add(i);
            dfs(i + 1, k, n);
            tmp.removeLast();
        }
    }
}
```

> 剪枝优化
>
> `i <= n - (k - tmp.size()) + 1`

```java
class Solution {
    List<List<Integer>> res = new LinkedList<>();
    List<Integer> tmp = new LinkedList<>();

    public List<List<Integer>> combine(int n, int k) {
        dfs(1, k, n);
        return res;

    }

    public void dfs(int startIdx, int k, int n) {
        if (tmp.size() == k) {
            res.add(new LinkedList(tmp));
            return;
        }
        for (int i = startIdx; i <= n - (k - tmp.size()) + 1; i++) {
            tmp.add(i);
            dfs(i + 1, k, n);
            tmp.removeLast();
        }
    }
}
```

## 216. 组合总和 III

[216. 组合总和 III](https://leetcode.cn/problems/combination-sum-iii/)

找出所有相加之和为 `n` 的 `k` 个数的组合，且满足下列条件：

- 只使用数字1到9
- 每个数字 **最多使用一次** 

返回 *所有可能的有效组合的列表* 。该列表不能包含相同的组合两次，组合可以以任何顺序返回

> 可以减枝优化

```java
class Solution {
    List<List<Integer>> res = new LinkedList<>();
    List<Integer> tmp = new LinkedList<>();
    int sum = 0;

    public List<List<Integer>> combinationSum3(int k, int n) {
        dfs(1, k, n);
        return res;
    }

    public void dfs(int startIdx, int k, int n) {
        if (tmp.size() > k || sum > n) { // 超过k个数，或者总和超过sum
            return;
        }
        if (tmp.size() == k && sum == n) {
            res.add(new LinkedList(tmp));
            return;
        }

        for (int i = startIdx; i <= 9; i++) {
            sum += i;
            tmp.add(i);
            dfs(i + 1, k, n);
            tmp.removeLast();
            sum -= i;
        }
    }
}
```

## 39. 组合总和

[39. 组合总和](https://leetcode.cn/problems/combination-sum/)

给你一个 **无重复元素** 的整数数组 `candidates` 和一个目标整数 `target` ，找出 `candidates` 中可以使数字和为目标数 `target` 的 所有 **不同组合** ，并以列表形式返回。你可以按 **任意顺序** 返回这些组合。

`candidates` 中的 **同一个** 数字可以 **无限制重复被选取** 。如果至少一个数字的被选数量不同，则两种组合是不同的。 

对于给定的输入，保证和为 `target` 的不同组合数少于 `150` 个。

```java
class Solution {
    List<List<Integer>> res = new LinkedList<>();
    List<Integer> tmp = new LinkedList<>();
    int sum = 0;

    public List<List<Integer>> combinationSum(int[] candidates, int target) {
        dfs(0, candidates, target);
        return res;

    }

    public void dfs(int startIdx, int[] a, int t) {
        if (sum > t)
            return;
        if (sum == t) {
            res.add(new LinkedList(tmp));
            return;
        }
        for (int i = startIdx; i <= a.length - 1; i++) {
            tmp.add(a[i]);
            sum += a[i];
            dfs(i, a, t);
            tmp.removeLast();
            sum -= a[i];
        }
    }
}
```

## 40. 组合总和 II

[40. 组合总和 II](https://leetcode.cn/problems/combination-sum-ii/)

给定一个候选人编号的集合 `candidates` 和一个目标数 `target` ，找出 `candidates` 中所有可以使数字和为 `target` 的组合。

`candidates` 中的每个数字在每个组合中只能使用 **一次** 。

**注意：**解集不能包含重复的组合。

> 用`used[]`数组去重

```java
class Solution {
    List<List<Integer>> res = new LinkedList<>();
    List<Integer> tmp = new LinkedList<>();
    boolean[] used;
    int sum = 0;

    public List<List<Integer>> combinationSum2(int[] candidates, int target) {
        used = new boolean[candidates.length];
        Arrays.sort(candidates);
        dfs(0, candidates, target);
        return res;

    }

    public void dfs(int startIdx, int[] a, int t) {
        if (sum > t)
            return;
        if (sum == t) {
            res.add(new LinkedList(tmp));
            return;
        }
        for (int i = startIdx; i <= a.length - 1; i++) {
            if (i != 0 && a[i] == a[i - 1] && used[i - 1] == false)
                continue;
            tmp.add(a[i]);
            used[i] = true;
            sum += a[i];
            dfs(i + 1, a, t);
            tmp.removeLast();
            used[i] = false;
            sum -= a[i];
        }
    }
}
```

## 78. 子集

[78. 子集](https://leetcode.cn/problems/subsets/)

给你一个整数数组 `nums` ，数组中的元素 **互不相同** 。返回该数组所有可能的子集（幂集）。

解集 **不能** 包含重复的子集。你可以按 **任意顺序** 返回解集。

```java
class Solution {
    List<List<Integer>> res = new LinkedList<>();
    List<Integer> tmp = new LinkedList<>();

    public List<List<Integer>> subsets(int[] nums) {
        dfs(0, nums);
        return res;
    }

    public void dfs(int startIdx, int[] a) {
        res.add(new LinkedList(tmp));
        for (int i = startIdx; i <= a.length - 1; i++) {
            tmp.add(a[i]);
            dfs(i + 1, a);
            tmp.removeLast();
        }
    }
}
```

## 90. 子集 II

[90. 子集 II](https://leetcode.cn/problems/subsets-ii/)

给你一个整数数组 `nums` ，其中可能包含重复元素，请你返回该数组所有可能的子集（幂集）。

解集 **不能** 包含重复的子集。返回的解集中，子集可以按 **任意顺序** 排列。

> 用`used[]`数组去重

```java
class Solution {
    List<List<Integer>> res = new LinkedList<>();
    List<Integer> tmp = new LinkedList<>();
    boolean[] used;

    public List<List<Integer>> subsetsWithDup(int[] nums) {
        used = new boolean[nums.length];
        Arrays.sort(nums);
        dfs(0, nums);
        return res;
    }

    public void dfs(int startIdx, int[] a) {
        res.add(new LinkedList(tmp));
        for (int i = startIdx; i <= a.length - 1; i++) {
            if (i != 0 && a[i] == a[i - 1] && used[i - 1] == false)
                continue;
            tmp.add(a[i]);
            used[i] = true;
            dfs(i + 1, a);
            used[i] = false;
            tmp.removeLast();
        }
    }

}
```



# 关于二维的DFS

## P1605迷宫

https://www.luogu.com.cn/problem/P1605

给定一个 $N \times M$ 方格的迷宫，迷宫里有 $T$ 处障碍，障碍处不可通过。

在迷宫中移动有上下左右四种方式，每次只能移动一个方格。数据保证起点上没有障碍。

给定起点坐标和终点坐标，每个方格最多经过一次，问有多少种从起点坐标到终点坐标的方案。

$1≤𝑁,𝑀≤5$

> tags: DFS

Ideas:

> DFS**四个方向**搜索路径，搜索过的地方标记，搜到终点了则方案+1
>
> 边界处理方法：
>
> 1. 将边界初始化为障碍
> 2. 在搜索函数里判断x，y的范围

```java
import java.io.*;
import java.util.*;
public class Main {
    static Scanner sc = new Scanner(System.in);
    static PrintWriter pw = new PrintWriter(new BufferedWriter(new OutputStreamWriter(System.out)));
    static int N, M, T, sx, sy, fx, fy;
    static boolean vis[][];    
    public static void main(String[] args) throws Exception {
        N = sc.nextInt();
        M = sc.nextInt();
        T = sc.nextInt();
        sx = sc.nextInt();
        sy = sc.nextInt();
        fx = sc.nextInt();
        fy = sc.nextInt();
        vis = new boolean[N  + 1][M + 1];
        while(T --> 0){
            int x = sc.nextInt();
            int y = sc.nextInt();
            vis[x][y] = true;
        }
        dfs(sx, sy);
        pw.println(ans);
        pw.flush();
    }
    static int ans = 0;
    static int dx[] = {1, -1, 0, 0}, dy[] = {0, 0, 1, -1};
    private static void dfs(int x, int y) {
        if(x == fx && y == fy){
            ans ++;
            return;
        }
        vis[x][y] = true; // 标记当前位置
        for(int i = 0; i < 4; i ++){
            int nx = x + dx[i];
            int ny = y + dy[i];
            if(nx >= 1 && nx <= N && ny >= 1 && ny <= M && !vis[nx][ny]){//符合条件再dfs
                dfs(nx, ny);
            }
        }
        vis[x][y] = false;// 取消标记
    }
}
```

## P1596 Lake Counting S

https://www.luogu.com.cn/problem/P1596

由于近期的降雨，雨水汇集在农民约翰的田地不同的地方。我们用一个 $N\times M(1\leq N\leq 100, 1\leq M\leq 100)$ 的网格图表示。每个网格中有水（`W`） 或是旱地（`.`）。一个网格与其周围的八个网格相连，而一组相连的网格视为一个水坑。约翰想弄清楚他的田地已经形成了多少水坑。给出约翰田地的示意图，确定当中有多少水坑。

Ideas:

> **DFS求连通块个数**；遍历区域，遇到`W`水坑数+1，从此处开始**八个方向**dfs，搜索出所有的连通域改为`.`，无需回溯（防止之后的遍历遇到该水坑的`W`）.

```java
import java.io.*;
import java.util.*;
public class Main {
    static Scanner sc = new Scanner(System.in);
    static PrintWriter pw = new PrintWriter(new BufferedWriter(new OutputStreamWriter(System.out)));
    static int n, m, ans = 0;
    static char[][] a;
    static boolean vis[][];   
    public static void main(String[] args) throws Exception {
        n = sc.nextInt();
        m = sc.nextInt();
        a = new char[n][m];
        vis = new boolean[n][m];
        for(int i = 0; i < n; i ++) a[i] = sc.next().toCharArray();
        for(int i = 0; i < n; i ++){
            for(int j = 0; j < m; j ++){
                if(a[i][j] == 'W'){
                    ans ++;
                    dfs(i, j);
                }
            }
        }
        pw.println(ans);
        pw.flush();
    }
    static int[] dx = {-1, -1, -1, 0, 1, 1, 1, 0}, dy = {-1, 0, 1, 1, 1, 0, -1, -1};
    private static void dfs(int x, int y) {
        a[x][y] = '.';
        for(int i = 0 ; i < 8; i ++){
            int nx = x + dx[i];
            int ny = y + dy[i];
            if(nx >= 0 && nx < n && ny >= 0 && ny < m && a[nx][ny] == 'W'){
                dfs(nx, ny);
            }
        }
    }
}

```

> BFS做法
>
> **注意：**对于一个'W'点入队则将其改为'.' 而非出队才标记。这样防止入队之后还没来得及出队标记又被搜索到入队（爆内存）

```java
import java.io.*;
import java.util.*;
public class Main {
    static Scanner sc = new Scanner(System.in);
    static PrintWriter pw = new PrintWriter(new BufferedWriter(new OutputStreamWriter(System.out)));
    static int n, m, ans = 0;
    static char[][] a;
    static boolean vis[][];   
    public static void main(String[] args) throws Exception {
        n = sc.nextInt();
        m = sc.nextInt();
        a = new char[n][m];
        vis = new boolean[n][m];
        for(int i = 0; i < n; i ++) a[i] = sc.next().toCharArray();
        for(int i = 0; i < n; i ++){
            for(int j = 0; j < m; j ++){
                if(a[i][j] == 'W'){
                    ans ++;
                    bfs(i, j);
                }
            }
        }
        pw.println(ans);
        pw.flush();
    }
    static int[] dx = {-1, -1, -1, 0, 1, 1, 1, 0}, dy = {-1, 0, 1, 1, 1, 0, -1, -1};
    private static void bfs(int x0, int y0) {
        Queue<int[]> que = new LinkedList<>();
        que.add(new int[]{x0, y0});
        int pos[], x, y;
        while(!que.isEmpty()){
            pos = que.poll();
            x = pos[0];
            y = pos[1];
            for(int i = 0; i < 8; i ++){
                int nx = x + dx[i];
                int ny = y + dy[i];
                if(nx >= 0 && nx < n && ny >= 0 && ny < m && a[nx][ny] == 'W'){
                    que.add(new int[]{nx, ny});
                    a[nx][ny] = '.'; // 在入队则标记
                }
            }
        }
    }
}
```



## P1162 填涂颜色

https://www.luogu.com.cn/problem/P1162

由数字 $0$ 组成的方阵中，有一任意形状的由数字 $1$ 构成的闭合圈。现要求把闭合圈内的所有空间都填写成 $2$。例如：$6\times 6$ 的方阵（$n=6$），涂色前和涂色后的方阵如下：

如果从某个 $0$ 出发，只向上下左右 $4$ 个方向移动且仅经过其他 $0$ 的情况下，无法到达方阵的边界，就认为这个 $0$ **在闭合圈内**。闭合圈不一定是环形的，可以是任意形状，但保证**闭合圈内**的 $0$ 是连通的（两两之间可以相互到达）。

Ideas：

> DFS求连通块；要操作闭合圆环内的区域，转换思路，操作圆环内区域的补集---圆环外的区域，没有接触边界的区域定义为圆环内的区域，则圆环外的区域一定是接触边界的，将边界初始化为圆环外的区域，那么所有圆环外的区域就变成了一个连通域，从区域的一角`dfs`则可以搜索出所有的圆环外的区域，无需回溯，全部使之变为2；输出时对2和0的元素操作即可。对于是1的元素直接输出，其他元素：`arr[i][j]`取负再+2即可将2变为0，0变为2。即得所求。

```java
import java.util.Scanner;
public class Main {
	static int N, a[][];
	static int[] dx = {1, -1, 0, 0};
	static int[] dy = {0, 0, 1, -1};
	public static void main(String[] args) {
		Scanner s = new Scanner(System.in);
		N = s.nextInt();
		a = new int[N + 2][N + 2];
		for(int i = 1; i <= N; i++) {
			for(int j = 1; j <= N; j++) {
				a[i][j] = s.nextInt();
			}
		}
		//
		dfs(0,0);   
		for(int i = 1; i <= N; i++) {//输出答案
			for(int j = 1; j <= N; j++) {
				System.out.print((a[i][j] == 1? 1:-a[i][j]+2) + " ");
			}
			System.out.println();
    }		
	}
	private static void dfs(int i, int j) {
		if(i<0||i>N+1||j<0||j>N+1||a[i][j] != 0)return;	//防止越界 防止重复搜索
		a[i][j] = 2;
		for(int k = 0; k<=3; k++) {
			dfs(i + dx[k], j + dy[k]);
		}
	}
}
```

## P1101单词方阵

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



# 决策树

## P1036选数

[P1036 [NOIP2002 普及组] 选数 - 洛谷 | 计算机科学教育新生态 (luogu.com.cn)](https://www.luogu.com.cn/problem/P1036)

已知 $n$ 个整数 $x_1,x_2,\cdots,x_n$，以及 $1$ 个整数 $k$（$k<n$）。从 $n$ 个整数中任选 $k$ 个整数相加，可分别得到一系列的和。现在，要求你计算出和为素数共有多少种。

> tags: DFS、质数判断

Ideas:

> 选数

```java
import java.util.Scanner;
public class Main {
	static int N, K, a[], count = 0, sum = 0, res = 0;
	public static void main(String[] args) {
		Scanner s = new Scanner(System.in);
		N = s.nextInt();
		K = s.nextInt();
		a = new int[N];
		for(int i = 0; i<=N-1; i++) {
			a[i] = s.nextInt();
		}
		dfs(0);
		System.out.print(res);
	}
	private static void dfs(int startIdx) {
		if(count == K) {
			if(isPrime(sum)) res++;
			return;
		}	
		for(int i = startIdx;i<=N-1;i++) {
			count ++;
			sum += a[i];
			dfs(i+1);
			sum -= a[i];
			count --;
		}
	}
	private static boolean isPrime(int n) {
		if(n == 1) return false;
		if(n == 2) return true;
		for(int i = 2; i<=Math.sqrt(n); i++) {
			if(n%i == 0)return false;
		}
		return true;
	}
}
```

## P2404自然数的拆分问题

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
> 第一层在$[1,n]$中选$t1$，第二层在$\mathbf{[t_1,n-sum]}$中选……直到$t_1+t_2+...+t_m=n$.

![image-20240204012716956](images\image-P2404.png)

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

```java
import java.util.Scanner;
public class Main {
	static int N, sum = 0, a[], count = 0;
	public static void main(String[] args) {
		Scanner s = new Scanner(System.in);
		N = s.nextInt();
		a = new int[N];
		dfs(1);
	}

	private static void dfs(int startIdx) {
		if(sum==N&&count != 1) {
			String res = "" + a[0];
			for(int i = 1;i<=N-1&&a[i]!=0;i++) {
				res+=("+"+a[i]);
			}
			System.out.println(res);
			return;
		}
		for(int i = startIdx;i<=N-sum;i++) {
			a[count] = i;
			count ++;
			sum += i;
			dfs(i);
			sum -= i;
			count --;
			a[count] = 0;			
		}
	}
}
```

## P1219八皇后 Checker Challenge

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

![image-20240204114906712](images\image-P1219.png)

```java
import java.util.Scanner;
public class Main {
	static int N, res, used[][], p[];
	public static void main(String[] args) {
	   Scanner s = new Scanner(System.in);
	   N = s.nextInt();
	   used = new int[N][N];
	   p=new int[N];
	   //	   
	   dfs(0);
	   System.out.println(res);
	}	
	//n记录当前摆第几个棋子
	private static void dfs(int n) {
		if (n>N-1) {
      res++;
			if (res<=3) {//前三次
				for (int i = 0; i <=N-1; i++)System.out.print(p[i]+" ");
				System.out.println();
			}
			return;
		}
		for (int k = 0; k <=N-1; k++) {//每一行选位置摆放棋子
			if (used[n][k]!=0)continue;		
				fillUsed(n,k,1);
				p[n]=k+1;
				dfs(n+1);
				p[n]=0; 
				fillUsed(n,k,-1);			
		}		
	}
	private static void fillUsed(int i, int j, int b) {//标记行列对角线
		for (int k = i; k <=N-1; k++)used[k][j]+=b;
		int x = i，y = j;
		while(y<=N-1&&x<=N-1)used[x++][y++]+=b;
		x = i;
		y = j;
		while(y>=0&&x<=N-1)used[x++][y--]+=b;	
	}
}
```

## P2036 PERKET

[P2036 [COCI2008-2009 #2\] PERKET - 洛谷 | 计算机科学教育新生态 (luogu.com.cn)](https://www.luogu.com.cn/problem/P2036)

Perket 是一种流行的美食。为了做好 Perket，厨师必须谨慎选择食材，以在保持传统风味的同时尽可能获得最全面的味道。你有 $n$ 种可支配的配料。对于每一种配料，我们知道它们各自的酸度 $s$ 和苦度 $b$。当我们添加配料时，总的酸度为每一种配料的酸度总乘积；总的苦度为每一种配料的苦度的总和。

众所周知，美食应该做到口感适中，所以我们希望选取配料，以使得酸度和苦度的绝对差最小。

另外，我们必须添加至少一种配料，因为没有任何食物以水为配料的。

> tags: DFS

Ideas:

> 枚举所有食材组合
>
> 简单的DFS；所有食材当作一层，一次选取一个食材，记录目前选取的食材的酸度`sour`、苦度`bitter`, 记录最小值。

![image-20240204132617367](images\image-P2036.png)

```java
import java.util.Scanner;
public class Main {
	static int N, sour[], bit[], sumSour = 1, sumBit = 0, res = Integer.MAX_VALUE;
	public static void main(String[] args) {
		Scanner s = new Scanner(System.in);
		N = s.nextInt();
		sour = new int[N];
		bit = new int[N];
		for(int i = 0; i <= N-1; i++) {
		sour[i] = s.nextInt();
		bit[i] = s.nextInt();
		}
		dfs(0);
		System.out.print(res);
	}
	private static void dfs(int idx) {
		for(int i = idx; i <= N-1; i++) {
			sumSour *= sour[i];
			sumBit += bit[i];
			res = Math.min(res, Math.abs(sumBit - sumSour));
			dfs(i+1);
			sumSour /= sour[i];
			sumBit -= bit[i];
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
import java.io.*;
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

