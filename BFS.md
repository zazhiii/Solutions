# P1443 马的遍历

https://www.luogu.com.cn/problem/P1443

有一个 $n \times m$ 的棋盘，在某个点 $(x, y)$ 上有一个马，要求你计算出马到达棋盘上任意一个点最少要走几步。

```java
import java.util.*;
public class Main {
	static int N, M, X, Y, res[][], used[][];//res存放结果，used标记去过的地方
	static Queue<Integer> xs = new LinkedList<>(), ys = new LinkedList<>(), steps = new LinkedList<>();
	static int[] dx = {-2, -2, -1, 1, 2, 2, 1, -1};
	static int[] dy = {-1, 1, 2, 2, 1, -1, -2, -2};
	public static void main(String[] args) {
		Scanner s = new Scanner(System.in);
		N = s.nextInt();
		M = s.nextInt();
		X = s.nextInt() - 1;
		Y = s.nextInt() - 1;
		used = new int[N][M];
		res = new int[N][M];
		xs.add(X);
		ys.add(Y);
		steps.add(0);
		used[X][Y] = 1;
		bfs();
		//输出答案
		for(int i = 0; i <= N-1; i++) {
			for(int j = 0;j <= M-1; j++) {
				System.out.printf("%-5d", used[i][j] == 1?res[i][j]:-1);
			}
			System.out.println();
		}
	}
	private static void bfs() {
		int x, y, step;
		while(!steps.isEmpty()) {
			x = xs.poll();
			y = ys.poll();
			step = steps.poll();
			res[x][y] = step;	
			for(int i = 0; i <= 7; i++) {
				int nx = x + dx[i];
				int ny = y + dy[i];
				if(ok(nx,ny)) {//如果新位置合法
					xs.add(nx);
					ys.add(ny);
					steps.add(step+1);
					used[nx][ny] = 1;
				}
				
			}
		}	
	}
	private static boolean ok(int nx, int ny) {//判断新位置是否合法，不能越界，防止重复搜索
		return nx >= 0 && nx < N && ny >= 0 && ny < M && used[nx][ny] == 0;
	}
}
```

# P1135 奇怪的电梯

https://www.luogu.com.cn/problem/P1135

呵呵，有一天我做了一个梦，梦见了一种很奇怪的电梯。大楼的每一层楼都可以停电梯，而且第 $i$ 层楼（$1 \le i \le N$）上有一个数字 $K_i$（$0 \le K_i \le N$）。电梯只有四个按钮：开，关，上，下。上下的层数等于当前楼层上的那个数字。当然，如果不能满足要求，相应的按钮就会失灵。例如： $3, 3, 1, 2, 5$ 代表了 $K_i$（$K_1=3$，$K_2=3$，……），从 $1$ 楼开始。在 $1$ 楼，按“上”可以到 $4$ 楼，按“下”是不起作用的，因为没有 $-2$ 楼。那么，从 $A$ 楼到 $B$ 楼至少要按几次按钮呢？

```java
import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;
public class Main {
	static Queue<Integer> steps = new LinkedList<Integer>();// 次数
	static Queue<Integer> pos = new LinkedList<Integer>();// 楼层
	static int N, A, B, res[], nums[];
	static boolean[] used;
	static int[] dh = {1,-1};//控制上下
	public static void main(String[] args){
		Scanner s = new Scanner(System.in);
		N = s.nextInt();
		A = s.nextInt();
		B = s.nextInt();
		nums = new int[N+1];
		for(int i = 1; i<=N;i++) {
			nums[i] = s.nextInt(); 
		}
		res = new int[N+1];
		used = new boolean[N+1];
		//初始化起点
		pos.add(A);
		used[A] = true;
		steps.add(0);
		bfs();
		System.out.println(used[B]?res[B]:-1);
}
	public static void bfs() {
		int h, step;//记录当前位置，和到当前位置要按几次按钮
		while(!steps.isEmpty()) {
			h = pos.poll();
			step = steps.poll();
			res[h] = step; 			
			for(int i = 0;i<=1;i++) {//上&下
				int newH = h+dh[i]*nums[h];
				if (newH>=1&&newH<=N&&!used[newH]) {//若可以到达
					used[newH] = true;
					pos.add(newH);
					steps.add(step+1);
				}
			}
		}
	}	
}
```

# P2895Meteor Shower S

https://www.luogu.com.cn/problem/P2895

如果将牧场放入一个直角坐标系中，贝茜现在的位置是原点，并且，贝茜不能踏上一块被流星砸过的土地。

根据预报，一共有 $M$ 颗流星 $(1\leq M\leq 50,000)$ 会坠落在农场上，其中第 $i$ 颗流星会在时刻 $T_i$（$0 \leq T _ i \leq 1000$）砸在坐标为 $(X_i,Y_i)(0\leq X_i\leq 300$，$0\leq Y_i\leq 300)$ 的格子里。流星的力量会将它所在的格子，以及周围 $4$ 个相邻的格子都化为焦土，当然贝茜也无法再在这些格子上行走。

贝茜在时刻 $0$ 开始行动，她只能在会在横纵坐标 $X,Y\ge 0$ 的区域中，平行于坐标轴行动，每 $1$ 个时刻中，她能移动到相邻的（一般是 $4$ 个）格子中的任意一个，当然目标格子要没有被烧焦才行。如果一个格子在时刻 $t$ 被流星撞击或烧焦，那么贝茜只能在 $t$ 之前的时刻在这个格子里出现。 贝茜一开始在 $(0,0)$。

请你计算一下，贝茜最少需要多少时间才能到达一个安全的格子。如果不可能到达输出 $−1$。

```java
import java.io.*;
import java.util.*;
public class Main {
	static Scanner sc = new Scanner(System.in);
	static PrintWriter pw = new PrintWriter(new BufferedWriter(new OutputStreamWriter(System.out)));
	static Queue<Integer> xs = new LinkedList<>();
	static Queue<Integer> ys = new LinkedList<>();
	static Queue<Integer> ts = new LinkedList<>();
	static int[] dx4 = {-1, 0, 1, 0, 0}, dy4 = {0, 1, 0, -1, 0};
	static int n, a[][], ans, N = 310;
	static boolean vis[][];
 	public static void main(String[] args) throws IOException{
		n = sc.nextInt();
		a = new int[N][N];
		vis = new boolean[N][N];
		for(int i = 0; i<N;i++) 
			for(int j = 0; j<N; j++)
				a[i][j] = 1010;
 		while(n --> 0) {
 			int x = sc.nextInt();
 			int y = sc.nextInt();
 			int t = sc.nextInt();
 			for(int i = 0; i<5; i++) {
 				int xx = x + dx4[i];
 				int yy = y + dy4[i];
 				if(xx >= 0 && yy >= 0) {
 					a[xx][yy] = Math.min(a[xx][yy], t);
 				}
 			}
 		}
 		xs.add(0);
 		ys.add(0);
 		ts.add(0);
 		vis[0][0] = true;
 		ans = -1;
 		bfs();
 		pw.print(ans);
		pw.flush();		
	}
	private static void bfs() {
		int x, y, t;
		while(!ts.isEmpty()) {
			x = xs.poll();
			y = ys.poll();
			t = ts.poll();
			for(int i = 0; i < 4; i++) {
				int xx = x + dx4[i];
				int yy = y + dy4[i];
				if(xx >=0 && yy >=0 && a[xx][yy] > t + 1 && !vis[xx][yy]) {//不能搜走过的地方
					if(a[xx][yy] == 1010) {
						ans = t + 1;
						return;
					}
					vis[xx][yy] = true;
					xs.add(xx);
					ys.add(yy);
					ts.add(t + 1);
				}
				
			}
		}
		
	}
}
```

# P1825 Corn Maze S

https://www.luogu.com.cn/problem/P1825

奶牛们去一个 $N\times M$ 玉米迷宫，$2 \leq N \leq 300,2 \leq M \leq300$。

迷宫里有一些传送装置，可以将奶牛从一点到另一点进行瞬间转移。这些装置可以双向使用。

如果一头奶牛处在这个装置的起点或者终点，这头奶牛就必须使用这个装置，奶牛在传送过后不会立刻进行第二次传送，即不会卡在传送装置的起点和终点之间来回传送。

玉米迷宫除了唯一的一个出口都被玉米包围。

迷宫中的每个元素都由以下项目中的一项组成：

1. 玉米，`#` 表示，这些格子是不可以通过的。
1. 草地，`.` 表示，可以简单的通过。
1. 传送装置，每一对大写字母 $\tt{A}$ 到 $\tt{Z}$ 表示。
1. 出口，`=` 表示。
1. 起点， `@` 表示

奶牛能在一格草地上可能存在的四个相邻的格子移动，花费 $1$ 个单位时间。从装置的一个结点到另一个结点不花时间。

```java
import java.io.*;
import java.util.*;
public class Main {
	static Scanner sc = new Scanner(System.in);
	static PrintWriter pw = new PrintWriter(new BufferedWriter(new OutputStreamWriter(System.out)));
	static int n, m, ans = -1, sx = 0, sy = 0, fx = 0, fy = 0, tp[][];
	static char a[][];
	static Queue<int[]> q = new LinkedList<>();
	static int dx[] = {-1, 0, 1, 0}, dy[] = {0, 1, 0, -1};
 	public static void main(String[] args) throws IOException{
 		n = sc.nextInt();
 		m = sc.nextInt();
 		a = new char[n][m];
 		tp = new int[30][4];

 		for(int i = 0; i < n; i++) {
 			a[i] = sc.next().toCharArray();
 			for(int j = 0; j < m; j++) {
 				if(a[i][j] == '@') {sx = i; sy = j;}
 				else if(a[i][j] == '=') {fx = i; fy = j;}
 				else if(a[i][j] >= 'A' && a[i][j] <= 'Z') {
 					int p = a[i][j] - 'A';
 					if(tp[p][0] == 0) {//这也没bug？
 						tp[p][0] = i;tp[p][1] = j;
 					}else {
 						tp[p][2] = i;tp[p][3] = j;
 					}
 				}
 				
 			}
    }
 		q.add(new int[]{sx, sy, 0});//从起点开始搜索
 		a[sx][sy] = '#';
 		bfs();
 		pw.print(ans);
		pw.flush();		
	}
	private static void bfs() {
		int x, y, step;
		while(!q.isEmpty()) {
			int[] pos = q.poll();
			x = pos[0];
			y = pos[1];
			step = pos[2];
			if(x == fx && y == fy) {
				ans = step;
				return;
			}
			for(int i = 0; i < 4; i++) {
				int nx = x + dx[i];
				int ny = y + dy[i];
				if(nx >= 0 && nx < n && ny >= 0 && ny < m && a[nx][ny] != '#') {
					//如果是传送门对门入队
					if(a[nx][ny] >= 'A' && a[nx][ny] <= 'Z') {
						int p = a[nx][ny] - 'A';
						if(tp[p][0] == nx && tp[p][1] == ny) {
							q.add(new int[] {tp[p][2], tp[p][3], step + 1});
						}else {
							q.add(new int[] {tp[p][0], tp[p][1], step + 1});
						}
					}else {
						a[nx][ny] = '#';
						q.add(new int[] {nx, ny, step + 1});
					}
				}
			}
		}
	}		
}
```

