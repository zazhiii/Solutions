可以用deque实现

```java
for(int i = 0; i < n - 1; i ++){
	if(!dq.isEmpty() && dq.getFirst() < i - m + 1) dq.pollFirst();
 	while(!dq.isEmpty() && a[dq.getLast()] >= a[i]) dq.pollLast();
 	dq.addLast(i);
 	pw.println(a[dq.peekFirst()]);
} 
```



# P1886 【模板】滑动窗口 /单调队列

https://www.luogu.com.cn/problem/P1886

有一个长为 $n$ 的序列 $a$，以及一个大小为 $k$ 的窗口。现在这个从左边开始向右滑动，每次滑动一个单位，求出每次滑动后窗口中的最大值和最小值。

例如，对于序列 $[1,3,-1,-3,5,3,6,7]$ 以及 $k = 3$，有如下过程：

$$\def\arraystretch{1.2}
\begin{array}{|c|c|c|}\hline
\textsf{窗口位置} & \textsf{最小值} & \textsf{最大值} \\ \hline
\verb![1   3  -1] -3   5   3   6   7 ! & -1 & 3 \\ \hline
\verb! 1  [3  -1  -3]  5   3   6   7 ! & -3 & 3 \\ \hline
\verb! 1   3 [-1  -3   5]  3   6   7 ! & -3 & 5 \\ \hline
\verb! 1   3  -1 [-3   5   3]  6   7 ! & -3 & 5 \\ \hline
\verb! 1   3  -1  -3  [5   3   6]  7 ! & 3 & 6 \\ \hline
\verb! 1   3  -1  -3   5  [3   6   7]! & 3 & 7 \\ \hline
\end{array}
$$

```java
import java.io.*;
import java.util.*;
public class Main {
    static Scanner s = new Scanner(System.in);
    static PrintWriter pw = new PrintWriter(new BufferedWriter(new OutputStreamWriter(System.out)));
    static Read r = new Read();
    static int n, k, a[], q[];
    public static void main(String[] args) throws IOException {
    	n = r.nextInt();
    	k = r.nextInt();
    	a = new int[n + 1];
    	q = new int[n + 1];
    	for(int i = 1; i <= n; i ++) a[i] = r.nextInt();
    	//------------区间最小值
    	int hh = 0, tt = -1;
    	for(int i = 1; i<=n; i++) {
    		if(hh <= tt && q[hh] < i - k + 1) hh ++;
    		while(hh <= tt && a[q[tt]] >= a[i]) tt --;
    		q[ ++ tt] = i;
    		if(i >= k) pw.print(a[q[hh]] + " ");
    	}
    	pw.println();
    	//------------区间最大值
    	hh = 0; tt = -1;
    	for(int i = 1; i <= n; i++) {
    		// 队头元素不在维护范围内了
    		if(hh <= tt && q[hh] < i - k + 1) hh ++; 
    		// 队尾元素比当前元素小，不可能为区间最大值，删去
    		while(hh <= tt && a[q[tt]] <= a[i]) tt --;
    		//添加当前元素
    		q[ ++ tt] = i;
    		//输出最大值 q[hh]存的索引，a[q[hh]]	
    		if(i >= k) pw.print(a[q[hh]] + " ");
    	}
	    pw.flush();
    }
}

class Read{
	StreamTokenizer st = new StreamTokenizer(new BufferedReader(new InputStreamReader(System.in)));
	public int nextInt() throws IOException {
		st.nextToken();
		return (int)st.nval;
	}
}
```

