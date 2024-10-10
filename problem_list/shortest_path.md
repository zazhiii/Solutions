# Dijkstra

[3286. 穿越网格图的安全路径 - 力扣（LeetCode）](https://leetcode.cn/problems/find-a-safe-walk-through-a-grid/description/)





# 分图层最短路

[P4568 飞行路线 - 洛谷 | 计算机科学教育新生态 (luogu.com.cn)](https://www.luogu.com.cn/problem/P4568)

> 每个节点视作有`k`个状态，整个图相当于变成了`k`层。`d[v][k]`表示到节点`v`用了`k`次免费的最小花费，然后再跑$dijkstra$，对免费或不免费分别做两次松弛操作

```java
static int n, m, K, s, t, d[][];
    static boolean vis[][];
    static List<int[]> adj[];
    public static void solve() throws IOException{
        n = rd.nextInt();
        m = rd.nextInt();
        K = rd.nextInt();
        s = rd.nextInt() + 1;
        t = rd.nextInt() + 1;
        adj = new List[n + 1];
        Arrays.setAll(adj, i -> new ArrayList<>());
        d = new int[n + 1][K + 1];
        vis = new boolean[n + 1][K + 1];
        while(m --> 0){
            int u = rd.nextInt() + 1;
            int v = rd.nextInt() + 1;
            int w = rd.nextInt();
            adj[u].add(new int[]{v, w});
            adj[v].add(new int[]{u, w});
        }

        Queue<int[]> que = new PriorityQueue<>((a, b) -> a[2] - b[2]);
        for(int i = 0; i <= n; i ++) Arrays.fill(d[i], (int)1e9);

        que.add(new int[]{s, 0, 0}); // u节点，k次免费，w花费
        d[s][0] = 0;
        while(!que.isEmpty()){
            int p[] = que.poll(); int u = p[0]; int k = p[1], w = p[2];
            if(vis[u][k]) continue;
            vis[u][k] = true;
            for(int v[] : adj[u]){
                if(k < K && d[v[0]][k + 1] > w){ // 免费通行到下一个节点（保证免费次数没超过K)
                    d[v[0]][k + 1] = w;
                    que.add(new int[]{v[0], k + 1, w});
                }

                if(d[v[0]][k] > w + v[1]){ // 不免费同行到下一个节点
                    d[v[0]][k] = w + v[1];
                    que.add(new int[]{v[0], k, w + v[1]});
                }
            }
        }

        // 取到目标节点t的k个状态的最小值
        int ans = (int)1e9;
        for(int i = 0; i <= K; i ++) ans = Math.min(ans, d[t][i]);
        pw.println(ans);
    }
```

[Problem - E - Codeforces](https://codeforces.com/contest/2014/problem/E)

>每个节点视作骑马和不骑马两个状态，视作两层图，`d[u][k]`表示到节点`u`且是否(1/0)时骑马过来的。再跑dijkstra，做松弛操作时，分成三种情况讨论即可。跑两次dijkstra，算出从1出发到各个点的花费和从n出发到各个点的花费，答案为到某个点会和两人花费的较大值的最小值。

```java
    static int n, m, h;
    static List<int[]> adj[];
    static Set<Integer> horse;
    public static void solve(int T) throws IOException{
        n = rd.nextInt();
        m = rd.nextInt();
        h = rd.nextInt();
        horse = new HashSet<>();
        while(h --> 0){
            int x = rd.nextInt();
            horse.add(x);
        }    
        adj = new ArrayList[n + 1];
        Arrays.setAll(adj, i -> new ArrayList<>());
        while(m --> 0){
            int u = rd.nextInt();
            int v = rd.nextInt();
            int w = rd.nextInt();
            adj[u].add(new int[]{v, w});
            adj[v].add(new int[]{u, w});
        }
        long d1[][] = new long[n + 1][2];
        long d2[][] = new long[n + 1][2];
        for(int i = 0; i <= n; i ++) {
            Arrays.fill(d1[i], (long)2e18);
            Arrays.fill(d2[i], (long)2e18);
        }
        dijk(1, d1);
        dijk(n, d2);
        long ans = (long)2e18;
        for(int i = 1; i <= n; i ++){
            ans = Math.min(ans, Math.max(Math.min(d1[i][0], d1[i][1]), Math.min(d2[i][0], d2[i][1])));
        }
        pw.println(ans == (long)2e18 ? -1 : ans);
    }
    public static void dijk(int s, long d[][]){
        boolean vis[][] = new boolean[n + 1][2];
        Queue<long[]> que = new PriorityQueue<>((a, b) -> a[2] > b[2] ? 1 : -1);
        que.add(new long[]{s, 0, 0}); // u节点，k是否(1/0)骑马，w花费
        d[s][0] = 0;
        while(!que.isEmpty()){
            long p[] = que.poll(); int u = (int)p[0]; int k = (int)p[1];
            if(vis[u][k]) continue;
            vis[u][k] = true;
            for(int nex[] : adj[u]){
                int v = nex[0]; int w = nex[1];
                if(k == 0 && horse.contains(u) && d[u][k] + w / 2 < d[v][1]){
                    d[v][1] = d[u][k] + w / 2;
                    que.add(new long[]{v, 1, d[v][1]});
                }
                if(k == 0 && d[u][k] + w < d[v][0]){
                    d[v][0] = d[u][k] + w;
                    que.add(new long[]{v, 0, d[v][0]});
                }
                if(k == 1 && d[u][k] + w / 2 < d[v][1]){
                    d[v][1] = d[u][k] + w / 2;
                    que.add(new long[]{v, 1, d[v][1]});
                }
            }
        }
    }
```

