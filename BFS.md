# P1443 马的遍历

https://www.luogu.com.cn/problem/P1443

有一个 $n \times m$ 的棋盘，在某个点 $(x, y)$ 上有一个马，要求你计算出马到达棋盘上任意一个点最少要走几步。

```java
import java.util.Scanner;
import java.util.Queue;
import java.util.LinkedList;
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

