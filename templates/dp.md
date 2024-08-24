# 背包

## 01背包

[【模板】01背包 (nowcoder.com)](https://ac.nowcoder.com/acm/problem/226514)

你有一个背包，最多能容纳的体积是$V$。 

  现在有$n$个物品，第$i$个物品的体积为$v_i$ ,价值为$w_i$。 

  （1）求这个背包至多能装多大价值的物品？ 

  （2）若背包**恰好装满**，求至多能装多大价值的物品？

> 二维dp数组

```java
import java.io.*;
import java.util.*;
public class Main {
    static Scanner sc = new Scanner(System.in);
    static PrintWriter pw = new PrintWriter(System.out);
    static int n, V, v[], w[], dp1[][], dp2[][];
    public static void main(String[] args) throws IOException {
        n = sc.nextInt();
        V = sc.nextInt();
        v = new int[n + 1];
        w = new int[n + 1];
        for(int i = 1; i <= n; i ++){
            v[i] = sc.nextInt();
            w[i] = sc.nextInt();
        }
        // dp1[i][j]: 在前i个物品中，容量为j的背包能装的最大价值
        dp1 = new int[n + 1][V + 1];
        // dp2[i][j]: 在前i个物品中，容量为j的背包能恰好装满的最大价值
        dp2 = new int[n + 1][V + 1];
        // 不能从0转移出去那些容量将会是-inf，即这些状态不可达
        for(int i = 0; i <= n; i ++){
            Arrays.fill(dp2[i], Integer.MIN_VALUE);
            dp2[i][0] = 0;
        }
        for(int i = 1; i <= n; i ++){
            for(int j = 1; j <= V; j ++){
                if(j < v[i]){
                    // 背包容量小于第i个物品的体积，只能不选。
                    dp1[i][j] = dp1[i - 1][j];
                    dp2[i][j] = dp2[i - 1][j];
                }else{
                    // 考虑: 选 | 不选
                    dp1[i][j] = Math.max(dp1[i - 1][j], dp1[i - 1][j - v[i]] + w[i]);
                    dp2[i][j] = Math.max(dp2[i - 1][j], dp2[i - 1][j - v[i]] + w[i]);
                }
            }
        }
        pw.println(dp1[n][V]);
        pw.println(dp2[n][V] < 0 ? 0 : dp2[n][V]);
        pw.flush();
    }   
}
```

> 滚动数组将`dp`数组优化为一维

```java
import java.io.*;
import java.util.*;
public class Main {
    static Scanner sc = new Scanner(System.in);
    static PrintWriter pw = new PrintWriter(System.out);
    static int n, V, v[], w[], dp1[], dp2[];
    public static void main(String[] args) throws IOException {
        n = sc.nextInt();
        V = sc.nextInt();
        v = new int[n + 1];
        w = new int[n + 1];
        for(int i = 1; i <= n; i ++){
            v[i] = sc.nextInt();
            w[i] = sc.nextInt();
        }

        dp1 = new int[V + 1];
        dp2 = new int[V + 1];

        Arrays.fill(dp2, Integer.MIN_VALUE);
        dp2[0] = 0;

        for(int i = 1; i <= n; i ++){
            for(int j = V; j >= v[i]; j --){
                dp1[j] = Math.max(dp1[j], dp1[j - v[i]] + w[i]);
                dp2[j] = Math.max(dp2[j], dp2[j - v[i]] + w[i]);
            }
        }
        pw.println(dp1[V]);
        pw.println(dp2[V] < 0 ? 0 : dp2[V]);
        pw.flush();
    }   
}
```

## 完全背包

```java
import java.io.*;
import java.util.*;
public class Main {
    static Scanner sc = new Scanner(System.in);
    static PrintWriter pw = new PrintWriter(System.out);
    static int n, V, v[], w[], dp1[][], dp2[][];
    public static void main(String[] args) throws IOException {
        n = sc.nextInt();
        V = sc.nextInt();
        v = new int[n + 1];
        w = new int[n + 1];
        for(int i = 1; i <= n; i ++){
            v[i] = sc.nextInt();
            w[i] = sc.nextInt();
        }
        // dp1[i][j]: 在前i个物品中，容量为j的背包能装的最大价值
        dp1 = new int[n + 1][V + 1];
        // dp2[i][j]: 在前i个物品中，容量为j的背包能恰好装满的最大价值
        dp2 = new int[n + 1][V + 1];
        // 不能从0转移出去那些容量将会是-inf，即这些状态不可达
        for(int i = 0; i <= n; i ++){
            Arrays.fill(dp2[i], Integer.MIN_VALUE);
            dp2[i][0] = 0;
        }
        for(int i = 1; i <= n; i ++){
            for(int j = 1; j <= V; j ++){
                if(j < v[i]){
                    // 背包容量小于第i个物品的体积，只能不选。
                    dp1[i][j] = dp1[i - 1][j];
                    dp2[i][j] = dp2[i - 1][j];
                }else{
                    // 与01背包不同：
                    //     01背包选了当前物品，剩余的j - v[i]的容量只能从物品[1, i - 1]中选；
                    //     完全背包选了当前物品，剩余的j - v[i]的容量可以从物品[1, i]中选。
                    // 理解为 "选一个" 当前物品。
                    // 考虑: 不选 | 选一个
                    dp1[i][j] = Math.max(dp1[i - 1][j], dp1[i][j - v[i]] + w[i]);
                    dp2[i][j] = Math.max(dp2[i - 1][j], dp2[i][j - v[i]] + w[i]);
                }
            }
        }
        pw.println(dp1[n][V]);
        pw.println(dp2[n][V] < 0 ? 0 : dp2[n][V]);
        pw.flush();
    }   
}
```

```java
import java.io.*;
import java.util.*;
public class Main {
    static Scanner sc = new Scanner(System.in);
    static PrintWriter pw = new PrintWriter(System.out);
    static int n, V, v[], w[], dp1[], dp2[];
    public static void main(String[] args) throws IOException {
        n = sc.nextInt();
        V = sc.nextInt();
        v = new int[n + 1];
        w = new int[n + 1];
        for(int i = 1; i <= n; i ++){
            v[i] = sc.nextInt();
            w[i] = sc.nextInt();
        }

        dp1 = new int[V + 1];
        dp2 = new int[V + 1];

        Arrays.fill(dp2, Integer.MIN_VALUE);
        dp2[0] = 0;

        for(int i = 1; i <= n; i ++){
            for(int j = v[i]; j <= V; j ++){
                dp1[j] = Math.max(dp1[j], dp1[j - v[i]] + w[i]);
                dp2[j] = Math.max(dp2[j], dp2[j - v[i]] + w[i]);
            }
        }
        pw.println(dp1[V]);
        pw.println(dp2[V] < 0 ? 0 : dp2[V]);
        pw.flush();
    }   
}
```

