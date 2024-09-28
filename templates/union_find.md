```java
    static int N = (int)1e5 + 10;
    static int p[] = new int[N], size[] = new int[N];
    static{
        for(int i = 0; i < N; i ++){
            p[i] = i;
            size[i] = 1;
        }
    }

    public static int find(int x){
        if(p[x] != x) p[x] = find(p[x]);
        return p[x];
    }

    public static void union(int x, int y){
        int fx = find(x), fy = find(y);
        if(fx != fy){
            p[fx] = fy;
            size[fy] += size[fx];
        }
    }
```

