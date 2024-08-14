# 排列问题

# 组合问题

## 77. 组合

[77. 组合](https://leetcode.cn/problems/combinations/)

给定两个整数 `n` 和 `k`，返回范围 `[1, n]` 中所有可能的 `k` 个数的组合。

你可以按 **任何顺序** 返回答案。

> DFS

```java
class Solution {
    List<List<Integer>> ans = new LinkedList<>();
    List<Integer> tmp = new LinkedList<>();
    int n, k;
    public List<List<Integer>> combine(int n, int k) {
        this.n = n; this.k = k;
        dfs(1);
        return ans;
    }
    public void dfs(int sIdx){
        if(tmp.size() == k){
            ans.add(new LinkedList(tmp));
            return;
        }
        for(int i = sIdx; i <= n; i ++){
            tmp.add(i);
            dfs(i + 1);
            tmp.removeLast();
        }
    }
}
```

> 剪枝优化
>
> 已经选了`tmp.size()`个数了，还需要选`k - tmp.size()`个数，所以最多从`n - (k - tmp.size()) + 1`开始选。
>
> `i <= n - (k - tmp.size()) + 1`

```java
class Solution {
    List<List<Integer>> ans = new LinkedList<>();
    List<Integer> tmp = new LinkedList<>();
    int n, k;
    public List<List<Integer>> combine(int n, int k) {
        this.n = n; this.k = k;
        dfs(1);
        return ans;
    }
    public void dfs(int sIdx){
        if(tmp.size() == k){
            ans.add(new LinkedList(tmp));
            return;
        }
        for(int i = sIdx; i <= n - (k - tmp.size()) + 1; i ++){
            tmp.add(i);
            dfs(i + 1);
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

> DFS

```java
class Solution {
    List<List<Integer>> ans = new LinkedList<>();
    List<Integer> tmp = new LinkedList<>();
    int k, n;
    public List<List<Integer>> combinationSum3(int k, int n) {
        this.k = k; this.n = n;
        dfs(1, 0);
        return ans;
    }

    public void dfs(int sIdx, int sum){
        if(tmp.size() == k){
            if(sum == n) ans.add(new LinkedList<>(tmp));
            return;
        }
        for(int i = sIdx; i <= 9; i ++){
            tmp.add(i);
            dfs(i + 1, sum + i);
            tmp.removeLast();
        }
    }
}
```

> 当没有选到$k$个数$sum$就已经大于$n$了可以提前结束搜索，选数时不可能选到超过$n-sum$的数。最多从`n - (k - tmp.size()) + 1`开始选。

```java
class Solution {
    List<List<Integer>> ans = new LinkedList<>();
    List<Integer> tmp = new LinkedList<>();
    int k, n;
    public List<List<Integer>> combinationSum3(int k, int n) {
        this.k = k; this.n = n;
        dfs(1, 0);
        return ans;
    }

    public void dfs(int sIdx, int sum){
        if(sum > n) return;
        if(tmp.size() == k){
            if(sum == n) ans.add(new LinkedList<>(tmp));
            return;
        }
        for(int i = sIdx; i <= Math.min(9, n - sum) && i <= 9 - (k - tmp.size()) + 1; i ++){
            tmp.add(i);
            dfs(i + 1, sum + i);
            tmp.removeLast();
        }
    }
}
```

## 39. 组合总和

[39. 组合总和](https://leetcode.cn/problems/combination-sum/)

给你一个 **无重复元素** 的整数数组 `candidates` 和一个目标整数 `target` ，找出 `candidates` 中可以使数字和为目标数 `target` 的 所有 **不同组合** ，并以列表形式返回。你可以按 **任意顺序** 返回这些组合。

`candidates` 中的 **同一个** 数字可以 **无限制重复被选取** 。如果至少一个数字的被选数量不同，则两种组合是不同的。 

对于给定的输入，保证和为 `target` 的不同组合数少于 `150` 个。

> DFS

> 在适当的时候退出搜索

```java
class Solution {
    List<List<Integer>> ans = new LinkedList<>();
    List<Integer> tmp = new LinkedList<>();
    int t, a[];
    public List<List<Integer>> combinationSum(int[] a, int t) {
        this.t = t; this.a = a;
        dfs(0, 0);
        return ans;
    }
    public void dfs(int sIdx, int sum){
        if(sum > t) return;
        if(sum == t){
            ans.add(new LinkedList<>(tmp));
            return;
        }
        for(int i = sIdx; i < a.length; i ++){
            tmp.add(a[i]);
            dfs(i, sum + a[i]);
            tmp.removeLast();
        }
    }
}
```

## 40. 组合总和 II

[40. 组合总和 II](https://leetcode.cn/problems/combination-sum-ii/)

给定一个候选人编号的集合 `candidates` 和一个目标数 `target` ，找出 `candidates` 中所有可以使数字和为 `target` 的组合。

`candidates` 中的每个数字在每个组合中只能使用 **一次** 。

**注意：**解集不能包含重复的组合。

> 对于每一次搜索，在选第$i$个数时不能选相等的数。
>
> 用`used[]`数组去重

```java
class Solution {
    List<List<Integer>> ans = new LinkedList<>();
    List<Integer> tmp = new LinkedList<>();
    boolean used[];
    int t, a[];
    public List<List<Integer>> combinationSum2(int[] a, int t) {
        this.t = t; this.a = a;
        used = new boolean[a.length];
        Arrays.sort(a);
        dfs(0, 0);
        return ans;
    }
    public void dfs(int sIdx, int sum){
        if(sum > t) return;
        if(sum == t){
            ans.add(new LinkedList<>(tmp));
            return;
        }
        for(int i = sIdx; i < a.length; i ++){
            if(i > 0 && a[i] == a[i - 1] && used[i - 1] == false) continue;
            used[i] = true;
            tmp.add(a[i]);
            dfs(i + 1, sum + a[i]);
            tmp.removeLast();
            used[i] = false;
        }
    }
}
```

> 还可以剪枝

```java
class Solution {
    List<List<Integer>> ans = new LinkedList<>();
    List<Integer> tmp = new LinkedList<>();
    boolean used[];
    int t, a[];
    public List<List<Integer>> combinationSum2(int[] a, int t) {
        this.t = t; this.a = a;
        used = new boolean[a.length];
        Arrays.sort(a);
        dfs(0, 0);
        return ans;
    }
    public void dfs(int sIdx, int sum){
        if(sum > t) return;
        if(sum == t){
            ans.add(new LinkedList<>(tmp));
            return;
        } 									/ * 剪枝 * /
        for(int i = sIdx; i < a.length && a[i] <= t - sum; i ++){
            if(i > 0 && a[i] == a[i - 1] && used[i - 1] == false) continue;
            used[i] = true;
            tmp.add(a[i]);
            dfs(i + 1, sum + a[i]);
            tmp.removeLast();
            used[i] = false;
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

一个 $N\times M(1\leq N\leq 100, 1\leq M\leq 100)$ 的网格图。每个网格中有水（`W`） 或是旱地（`.`）。一个网格与其周围的八个网格相连，而一组相连的网格视为一个水坑。确定当中有多少水坑。

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



# 树形的DFS

## P1219 八皇后 

[P1219八皇后 Checker Challenge](https://www.luogu.com.cn/problem/P1219)

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

## P1036选数

[P1036 选数](https://www.luogu.com.cn/problem/P1036)

已知 $n$ 个整数 $x_1,x_2,\cdots,x_n$，以及 $1$ 个整数 $k$（$k<n$）。从 $n$ 个整数中任选 $k$ 个整数相加，可分别得到一系列的和。现在，要求你计算出和为素数共有多少种。

$1\le n\le20$

> tags: DFS、质数判断

> $2^{20}\approx10^6$，放心大胆地枚举每一种情况。可以用**dfs枚举**所有情况。

```java
import java.io.*;
import java.util.*;

public class Main {
    static Scanner sc = new Scanner(System.in);
    static PrintWriter pw = new PrintWriter(new BufferedWriter(new OutputStreamWriter(System.out)));
    static int n, k, a[], ans = 0;
    public static void main(String[] args) throws IOException {
        n = sc.nextInt();
        k = sc.nextInt();
        a = new int[n];
        for(int i = 0; i < n; i ++) a[i] = sc.nextInt();
        dfs(0, 0, 0);
        pw.println(ans);
        pw.flush();
    }   
    public static void dfs(int sIdx, int cnt, int sum){
        if(cnt == k){
            if(isPrime(sum)) ans ++;
            return;
        }
        for(int i = sIdx; i < n; i ++){
            dfs(i + 1, cnt + 1, sum + a[i]);
        }
    }
    public static boolean isPrime(int x){
        if(x < 2) return false;
        for(int i = 2; i * i <= x; i ++){
            if(x % i == 0) return false;
        }
        return true;
    } 
}
```

## 131. 分割回文串

[131. 分割回文串](https://leetcode.cn/problems/palindrome-partitioning/)

给你一个字符串 `s`，请你将 `s` 分割成一些子串，使每个子串都是 

**回文串** 。返回 `s` 所有可能的分割方案。

> DFS枚举所有子串的长度。
>
> **预处理子串是否为回文串**，用$st[i][j]$表示从$i \to j$的子串是否为回文串。若$s[i] =s[j](i < j)$，当$s_{i+1,j-1}$为回文串时，$s_{i,j}$为回文串，反之则不是回文串；若$s[i] \neq s[j]$，则$s_{i,j}$不是回文串。（有些类似区间dp）
>
> **预处理子串**，用$s[i][j]$存储$i \to j$的字串。

```java
class Solution {
    List<List<String>> ans = new LinkedList<>();
    List<String> tmp = new LinkedList<>();
    char c[];
    boolean st[][];
    String s[][];
    int n;
    public List<List<String>> partition(String str) {
        c = str.toCharArray();
        n = c.length;
        s = new String[n][n];
        st = new boolean[n][n];
        for(int l = 1; l <= n; l ++){
            for(int i = 0; i <= n - l; i ++){
                int j = i + l - 1;
                if(l == 1) {
                    st[i][j] = true;
                    s[i][j] = c[i] + "";
                }else if(l == 2) {
                    st[i][j] = c[i] == c[j];
                    s[i][j] = "" + c[i] + c[j];
                }
                else {
                    if(c[i] == c[j]) st[i][j] = st[i + 1][j - 1];
                    s[i][j] = c[i] + s[i + 1][j - 1] + c[j];
                }
            }
        }
        dfs(0, 0);
        return ans;
    }
    public void dfs(int l, int sum){
        if(sum == n){
            ans.add(new LinkedList<>(tmp));
            return;
        }
        for(int i = 1; i <= n - sum; i ++){
            int r = l + i - 1;
            if(!st[l][r]) continue;
            tmp.add(s[l][r]);
            dfs(r + 1, sum + i);
            tmp.removeLast();
        }
    }
}
```

## P2404自然数的拆分问题

[P2404 自然数的拆分问题 ](https://www.luogu.com.cn/problem/P2404)

任何一个大于 $1$ 的自然数 $n$，总可以拆分成若干个小于 $n$ 的自然数之和。现在给你一个自然数 $n$，要求你求出 $n$ 的拆分成一些数字的和。每个拆分后的序列中的数字从小到大排序。然后你需要输出这些序列，其中字典序小的序列需要优先输出。

> 每一层选取一个大于等于前一次选过的数，在和大于等于的时候退出、处理答案。

```java
import java.io.*;
import java.util.*;
public class Main {
    static Scanner sc = new Scanner(System.in);
    static PrintWriter pw = new PrintWriter(new BufferedWriter(new OutputStreamWriter(System.out)));
    static int n;
    static Deque<Integer> que = new ArrayDeque<>();
    public static void main(String[] args) throws IOException {
        n = sc.nextInt();
        dfs(1, 0);
        pw.flush();
    }   
    public static void dfs(int sIdx, int sum){
        if(sum > n) return;
        if(sum == n){
            String ans = "";
            for(int x : que) ans += x + "+";
            pw.println(ans.substring(0, ans.length() - 1));
            return;
        }
        for(int i = sIdx; i < n; i ++){
            que.addLast(i);
            dfs(i, sum + i);
            que.removeLast();
        }
    }
}
```

## P2036 PERKET

[P2036 [COCI2008-2009 #2\] PERKET - 洛谷 | 计算机科学教育新生态 (luogu.com.cn)](https://www.luogu.com.cn/problem/P2036)

Perket 是一种流行的美食。为了做好 Perket，厨师必须谨慎选择食材，以在保持传统风味的同时尽可能获得最全面的味道。你有 $n$ 种可支配的配料。对于每一种配料，我们知道它们各自的酸度 $s$ 和苦度 $b$。当我们添加配料时，总的酸度为每一种配料的酸度总乘积；总的苦度为每一种配料的苦度的总和。

众所周知，美食应该做到口感适中，所以我们希望选取配料，以使得酸度和苦度的绝对差最小。

另外，我们必须添加至少一种配料，因为没有任何食物以水为配料的。

> 枚举总食材集合的所有子集，记录最小酸度和苦度。

```java
import java.io.*;
import java.util.*;
public class Main {
    static Scanner sc = new Scanner(System.in);
    static PrintWriter pw = new PrintWriter(new BufferedWriter(new OutputStreamWriter(System.out)));
    static int n, s[], b[], ans = (int)1e9;
    public static void main(String[] args) throws IOException {
        n = sc.nextInt();
        s = new int[n];
        b = new int[n];
        for(int i = 0; i < n; i ++){
            s[i] = sc.nextInt();
            b[i] = sc.nextInt();
        }
        dfs(0, 1, 0);
        pw.println(ans);
        pw.flush();
    }   
    public static void dfs(int sIdx, int S, int B){
        //至少选择一个食材
        if(sIdx != 0) ans = Math.min(ans, Math.abs(S - B));
        for(int i = sIdx; i < n; i ++){
            dfs(i + 1, S * s[i], B + b[i]);
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

[P1088 火星人](https://www.luogu.com.cn/problem/P1088)

给出一个$1\sim N$的排列，求按字典序排序该排列后的第$M$个排列。

> 排列，nextPermutation

Ideas:

> 使用nextPermutation计算当前排列后的第$m$个排列。
>
> ---
>
> tips: 有个数据点要用快速读写

```java
import java.io.*;
import java.util.*;

public class Main {
    static Read rd = new Read();
    static PrintWriter pw = new PrintWriter(new BufferedWriter(new OutputStreamWriter(System.out)));
    public static boolean nextPermunation(int a[]){
        int n = a.length, i = n - 2;
        while(i >= 0 && a[i] > a[i + 1]) i --;
        if(i < 0) return false;
        int k = i + 1;
        while(k < n && a[k] > a[i]) k ++;
        {int t = a[i]; a[i] = a[k - 1]; a[k - 1] = t;}// swap(a[i], a[k - 1])
        Arrays.sort(a, i + 1, n);
        return true;
    }
    public static void main(String[] args) throws IOException {
        int a[], n, m;
        n = rd.nextInt();
        m = rd.nextInt();
        a = new int[n];
        for(int i = 0; i < n; i ++) a[i] = rd.nextInt();
        while(m --> 0) nextPermunation(a);
        for(int i = 0; i < n; i ++) pw.print(a[i] + " ");
        pw.flush();pw.close();
    }
}

class Read {
	StreamTokenizer st = new StreamTokenizer(new BufferedReader(new InputStreamReader(System.in)));
	public int nextInt() throws IOException {st.nextToken();return (int)st.nval;}
}
```

