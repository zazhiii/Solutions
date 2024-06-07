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

