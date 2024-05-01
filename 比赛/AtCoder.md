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



https://atcoder.jp/contests/abc350/tasks/abc350_d





# Beginner Contest 351

**c. Merge the balls**

https://atcoder.jp/contests/abc351/tasks/abc351_c

**D. Grid and Magnet**

https://atcoder.jp/contests/abc351/tasks/abc351_d
