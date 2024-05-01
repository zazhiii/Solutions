# Beginner Contest 350

**C - Sort**

给你一个 $(1,2,\ldots,N)$ 的排列组合 $A=(A_1, ..., A_n)$ 。  
请在 $0$ 和 $N-1$ 之间(包括首尾两次)进行以下运算，将 $A$ 转化为 $(1,2,\ldots,N)$ ：

- 运算：选择任意一对整数 $(i,j)$ ，使得 $1\leq i < j \leq N$ .交换 $A$ 的 $i$ -th 和 $j$ -th 位置上的元素。

可以证明，在给定的约束条件下，总是可以把 $A$ 变换成 $(1,2,\ldots,N)$ 。

- $2 \leq N \leq 2\times 10^5$

> 模拟

> 第$i$个元素应该放在$A_i$的位置，遍历，不断将当前位置的元素换到对应的位置

```java
import java.util.*;
import java.io.*;
public class Main {
    static Scanner s = new Scanner(System.in);
    static PrintWriter pw = new PrintWriter(new BufferedWriter(new OutputStreamWriter(System.out)));
    public static void main(String[] args){
        int n = s.nextInt();
        int[] a = new int[n+1];
        for(int i = 1; i <=n; i++) a[i] = s.nextInt();
        List<int[]> res = new ArrayList<>();
        int ans = 0;
        for(int i = 1; i <=n; i++) {
            while(a[i] != i){
                ans++;
                res.add(new int[]{i, a[i]});
                int l = i, r = a[i];
                int t = a[l];
                a[l] = a[r];
                a[r] = t;
            }
        }
        pw.println(ans);
        for (int[] re : res) {
            pw.println(re[0]+" "+re[1]);
        }
        pw.flush();
    }
}
```

**D - New Friends**

https://atcoder.jp/contests/abc350/tasks/abc350_d

$N$个用户，有 $M$ 对好友关系，其中 $i$ /th对由用户 $A_i$ 和 $B_i$ 组成。

请确定以下操作的最大执行次数：

- 操作：选择三个用户 X、Y 和 Z，使得 X 和 Y 是好友，Y 和 Z 是好友，但 X 和 Z 不是好友。让 X 和 Z 成为好友。

> 并查集

> 最后所有用户会形成若干个完全图，若每个集合人数为$num$，则每个集合最终好友对数为$num*(num-1)/2$，求和所有集合的好友对数，减去原来的好友对数，即答案。
>
> 遍历用户，若用户$i$的顶节点不在`Set`内，则记录该集合的好友对数
>
> **$num*(num-1)/2$会超`int`**！！！！！

```java
import java.util.*;
public class Main {
    static Scanner s = new Scanner(System.in);
    static int n, m, p[], size[], a, b;
    public static void main(String[] args) {
       n = s.nextInt();
       m = s.nextInt();
       int k = m;//记录一下m
       p = new int[n + 1];
       size = new int[n + 1];
       for(int i = 1; i <= n; i++) {
    	   p[i] = i;
    	   size[i] = 1;
       }
       while(m -- >0) {
    	   a = s.nextInt();
    	   b = s.nextInt();
    	   if(find(a)!=find(b)) {
    		   size[find(b)] += size[find(a)];
    		   p[find(a)] = find(b);
    	   }
       }
       long res = 0;
       Set<Integer> set = new HashSet<>();
       for(int i = 1; i<=n; i++) {
    	   if(!set.contains(find(i))) {
    		   set.add(find(i));
    		   int num = size[find(i)];
    		   res += 1l*(num)*(num-1)/2;
    	   }
       }
       System.out.print(res - k);
    }
	private static int find(int x) {
		if(p[x] != x) p[x] = find(p[x]);
		return p[x];
	}
}
```



# Beginner Contest 351

**c. Merge the balls**

https://atcoder.jp/contests/abc351/tasks/abc351_c

你有一个空序列和 $N$ 个球。第 $i$ 个球 $(1 \leq i \leq N)$ 的大小是 $2^{Ai}$ 。

你将进行 $N$ 次运算。  
在 $i$ /th操作中，你将 $i$ /th球添加到序列的右端，然后重复下面的步骤：

1.  如果序列中只有一个或更少的球，则结束操作。
2.  如果序列中最右边的球和第二最右边的球大小不同，结束操作。
3.  如果序列中最右边的球和最右边的第二个球的大小相同，则移除这两个球，并在序列的右端添加一个新球，其大小等于移除的两个球的大小之和。然后回到步骤 1，重复上述过程。

计算经过 $N$ 次操作后，序列中剩余的球数。

> 模拟

> 对于每一个球：先将第$i$个球加入序列，记录幂级数，若最后两个球一样，则倒数第二个球的幂级数$+1$，直到停止操作。

```java
import java.util.*; 
public class Main {
    static Scanner s = new Scanner(System.in);
    static int n, a[], p = 0;
    public static void main(String[] args) {
       n = s.nextInt();
       a = new int[n + 1];
       for(int i = 1; i<=n; i++) {
    	   a[p ++] = s.nextInt();
    	   while(p >=2&&a[p - 1] == a[p - 2]) {
    		   a[p - 2] += 1;
    		   p--;
    	   }
       }
       System.out.print(p);
    }
}
```



**D. Grid and Magnet**

https://atcoder.jp/contests/abc351/tasks/abc351_d
