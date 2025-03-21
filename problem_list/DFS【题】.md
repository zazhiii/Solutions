# 网格图

## 连通块

> 四联通、八联通。可以用DFS、BFS、并查集来做。

[200. 岛屿数量](https://leetcode.cn/problems/number-of-islands/)

给你一个由 `'1'`（陆地）和 `'0'`（水）组成的的二维网格，请你计算网格中岛屿的数量。

岛屿总是被水包围，并且每座岛屿只能由水平方向和/或竖直方向上相邻的陆地连接形成

> DFS|BFS**求连通块数量**

```java
class Solution {
    public int numIslands(char[][] grid) {
        int n = grid.length;
        int m = grid[0].length;
        int ans = 0;
        for(int i = 0; i < n; i ++){
            for(int j = 0; j < m; j ++){
                if(grid[i][j] == '1'){
                    ans ++;
                    bfs(i, j, grid);
                }
            }
        }
        return ans;
    }
    int[] dx = {1, -1, 0, 0}, dy = {0, 0, 1, -1};
    public void bfs(int x0, int y0, char[][] grid){
        int n= grid.length;
        int m = grid[0].length;
        Queue<int[]> que = new LinkedList<>();
        que.add(new int[]{x0, y0});
        grid[x0][y0] = '0';
        while(!que.isEmpty()){
            int x, y, pos[];
            pos = que.poll();
            x = pos[0];
            y = pos[1];
            for(int i = 0; i < 4; i ++){
                int nx = x + dx[i];
                int ny = y + dy[i];
                if(nx >= 0 && nx < n && ny >= 0 && ny < m && grid[nx][ny] == '1'){
                    que.add(new int[]{nx, ny});
                    grid[nx][ny] = '0';//入队即访问过
                }
            }
        }
    }
}
```

[695. 岛屿的最大面积](https://leetcode.cn/problems/max-area-of-island/)

给你一个大小为 `m x n` 的二进制矩阵 `grid` 。

**岛屿** 是由一些相邻的 `1` (代表土地) 构成的组合，这里的「相邻」要求两个 `1` 必须在 **水平或者竖直的四个方向上** 相邻。你可以假设 `grid` 的四个边缘都被 `0`（代表水）包围着。

岛屿的面积是岛上值为 `1` 的单元格的数目。

计算并返回 `grid` 中最大的岛屿面积。如果没有岛屿，则返回面积为 `0` 。

> DFS|BFS求**联通块大小**

```java
class Solution {
    int ans = 0, cnt;
    public int maxAreaOfIsland(int[][] grid) {
        int n = grid.length;
        int m = grid[0].length;
        for(int i = 0; i < n; i ++){
            for(int j = 0; j < m; j ++){
                if(grid[i][j] == 1){
                    cnt = 0;
                    bfs(i, j, grid);
                    ans = Math.max(ans, cnt);
                }
            }
        }
        return ans;
    }
    int[] dx = {1, -1, 0, 0}, dy = {0, 0, 1, -1};
    public void bfs(int x0, int y0, int[][] grid){
        int n = grid.length;
        int m = grid[0].length;
        Queue<int[]> que = new LinkedList<>();
        que.add(new int[]{x0, y0});
        cnt ++;
        grid[x0][y0] = 0;
        while(!que.isEmpty()){
            int x, y, pos[];
            pos = que.poll();
            x = pos[0];
            y = pos[1];
            for(int i = 0; i < 4; i ++){
                int nx = x + dx[i];
                int ny = y + dy[i];
                if(nx >= 0 && nx < n && ny >= 0 && ny < m && grid[nx][ny] == 1){
                    que.add(new int[]{nx, ny});
                    cnt ++;//记录连通块大小
                    grid[nx][ny] = 0;
                }
            }
        }

    }
}
```

[463. 岛屿的周长](https://leetcode.cn/problems/island-perimeter/)

给定一个 `row x col` 的二维网格地图 `grid` ，其中：`grid[i][j] = 1` 表示陆地， `grid[i][j] = 0` 表示水域。

网格中的格子 **水平和垂直** 方向相连（对角线方向不相连）。整个网格被水完全包围，但其中恰好有一个岛屿（或者说，一个或多个表示陆地的格子相连组成的岛屿）。

岛屿中没有“湖”（“湖” 指水域在岛屿内部且不和岛屿周围的水相连）。格子是边长为 1 的正方形。网格为长方形，且宽度和高度均不超过 100 。计算这个岛屿的周长。

> 每次搜索到边界外的的点答案数+1

```java
class Solution {
    int n, m, ans = 0;
    boolean vis[][];
    int[] dx = {1, -1, 0, 0}, dy = {0, 0, 1, -1};
    public int islandPerimeter(int[][] grid) {
        n = grid.length;
        m = grid[0].length;
        vis = new boolean[n][m];
        for(int i = 0; i < n; i ++){
            for(int j = 0; j < m; j ++){
                if(grid[i][j] == 1 && !vis[i][j]) {
                    bfs(i, j, grid);
                }
            }
        }
        return ans;
    }
    public void bfs(int x0, int y0, int[][] grid){
        Queue<int[]> que = new LinkedList<>();
        que.add(new int[]{x0, y0});
        vis[x0][y0] = true;
        while(!que.isEmpty()){
            int x, y, pos[];
            pos = que.poll();
            x = pos[0];
            y = pos[1];
            for(int i = 0; i < 4; i ++){
                int nx = x + dx[i];
                int ny = y + dy[i];
                if(nx >= 0 && nx < n && ny >= 0 && ny < m){
                    if(grid[nx][ny] == 0) ans ++;
                    else if(!vis[nx][ny]){
                        que.add(new int[]{nx, ny});
                        vis[nx][ny] = true;
                    }
                }else{
                    ans ++;
                }
            }
        }
    }
}
```

[1034. 边界着色](https://leetcode.cn/problems/coloring-a-border/)

给你一个大小为 `m x n` 的整数矩阵 `grid` ，表示一个网格。另给你三个整数 `row`、`col` 和 `color` 。网格中的每个值表示该位置处的网格块的颜色。

如果两个方块在任意 4 个方向上相邻，则称它们 **相邻** 。

如果两个方块具有相同的颜色且相邻，它们则属于同一个 **连通分量** 。

**连通分量的边界** 是指连通分量中满足下述条件之一的所有网格块：

- 在上、下、左、右任意一个方向上与不属于同一连通分量的网格块相邻
- 在网格的边界上（第一行/列或最后一行/列）

请你使用指定颜色 `color` 为所有包含网格块 `grid[row][col]` 的 **连通分量的边界** 进行着色。

并返回最终的网格 `grid` 。

> 若从该点能搜索到不是该连通块的点则染色。

```java
class Solution {
    int[] dx = {1, -1, 0, 0}, dy = {0, 0, 1, -1};
    public int[][] colorBorder(int[][] grid, int row, int col, int color) {
        int n = grid.length;
        int m = grid[0].length;
        int init = grid[row][col];
        boolean[][] vis = new boolean[n][m];
        Queue<int[]> que = new LinkedList<>();
        que.add(new int[]{row, col});
        vis[row][col] = true;
        while(!que.isEmpty()){
            int x, y, pos[];
            pos = que.poll();
            x = pos[0];
            y = pos[1];
            for(int i = 0; i < 4; i ++){
                int nx = x + dx[i];
                int ny = y + dy[i];
                if(nx >= 0 && nx < n && ny >= 0 && ny < m ){
                    if(!vis[nx][ny] && grid[nx][ny] == init){
                        que.add(new int[]{nx,ny});
                        vis[nx][ny] = true;
                    }else if(!vis[nx][ny]){
                        grid[x][y] = color;
                    }
                }else{
                    grid[x][y] = color;
                }
            }
        }
        return grid;
    }
}
```











# 组合

[USACO05DEC] Scales S

https://www.luogu.com.cn/problem/P5194

有 $ N \ ( 1 \leq N \leq 1000 ) $ 个已知质量的砝码（砝码质量`int`范围内）。

**从第 $3$ 个砝码开始，每个砝码的质量至少等于前面两个砝码**（也就是质量比它小的砝码中质量最大的两个）的质量的和。

选出不超过$c$的质量最大砝码组合数。

> DFS、剪枝、枚举答案

```java
import java.io.*;
import java.util.*;
public class Main {
    static Scanner sc = new Scanner(System.in);
    static PrintWriter pw = new PrintWriter(new BufferedWriter(new OutputStreamWriter(System.out)));
    static int n, c;
    static long a[], s[], ans = 0;
    public static void main(String[] args) throws Exception {
        n = sc.nextInt();
        c = sc.nextInt();
        a = new long[n + 1];
        s = new long[n + 1];
        for(int i = 1; i <= n; i ++){
            a[i] = sc.nextInt();
            s[i] = s[i - 1] + a[i];
        }
        dfs(n, 0);        
        pw.print(ans);
        pw.flush();
        pw.close(); 
    }
    //from [1, eIdx] select next number, now sum:sum
    static public void dfs(int eIdx, long sum){
        if(s[eIdx] + sum < ans) return;//前面的全选也不能作为ans
        if(sum > c) return;//不能再选了
        if(s[eIdx] + sum <= c){//前面能全选就全选
            ans = Math.max(ans, s[eIdx] + sum);
            return;
        }
        ans = Math.max(ans, sum);
        for(int i = 1; i <= eIdx; i ++){
            dfs(i - 1, sum + a[i]);//select i-th number
        }
    }
}
```

