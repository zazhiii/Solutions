# C

```java
import java.util.*;
public class Main {
	static String str;
	static int count[], max = 0, N;
	public static void main(String[] args) {
		Scanner s = new Scanner(System.in);
		String str = s.next();
		N = str.length();
		count = new int[N];
		for(int i = 0; i<=N-1; i++) {
			int idx = str.charAt(i)-'A';
			count[idx]++;
			max = Math.max(max, count[idx]);
		}
		for(int i = 0; i<=N-1; i++) {
			if(count[i] == max) {
				System.out.print((char)(i+'A'));
			}
		}
	}
}
```

# D

> 三种情况
>
> 1 2 3 4 5
>
> 1 3 3 4 5
>
> 1 2 3 3 5

```java
import java.util.*;
public class Main {
	static int N, a[], find[];
	public static void main(String[] args){
		Scanner s = new Scanner(System.in);
		N = s.nextInt();
		a = new int[N];
		find = new int[N];
		for(int i = 0; i<=N-1 ;i++) {
			a[i] = s.nextInt();
			find[i] = a[i];
		}
		Arrays.sort(find);
		int cmp = find[N/2];
		int big = 0, small = 0;
		boolean midflag = false;
		for(int i = 0; i<=N-1;i++) {
			if(find[i]<cmp) small++;
			else if(find[i]>cmp)big++;
		}
		
		int target = small>big?find[N/2]:find[N/2]+1;
		if(small == big) midflag = true;
		for(int i = 0; i<=N-1; i++) {
			int res;
			if(midflag) {
				res = a[i] < cmp? target - a[i]:0;
			}else {
				res = a[i] <= cmp? target - a[i]:0;
			}
			
			System.out.print(res+" ");
		}
	}
}
```

# E

> 阶乘末尾多少个0
>
> 二分查找

```java
import java.util.*;
public class Main {
	static long k;
	static long res;
	public static void main(String[] args){
		Scanner s = new Scanner(System.in);
		k = s.nextLong();
//		System.out.print(zeros(15));
		long l = 1, r = Long.MAX_VALUE;
		 while(l<=r) {
			 long m = (l+r)>>>1;
//			 System.out.println(m);
			 if(zeros(m)>=k) {
				 res = m;
				 r = m - 1;
			 }else {
				 l = m + 1;
			 }
			 
		 }
		 System.out.print(zeros(res)==k?res:-1);
		 
	
	}
	
	public static long zeros(long n) {//n的阶乘末尾有多少个0
		long count = 0;
		long x = n;
		while(x!=0) {
			count+=(x/5);
			x/=5;
		}
		return count;
	}
}
```

# G

```java
package prac;

import java.util.*;
public class Main {
	static int n, a[], dp[], mod = 1000000007;
	public static void main(String[] args){
		Scanner s = new Scanner(System.in);
		n = s.nextInt();
		a = new int[n+1];
		dp = new int[n+1];
		for(int i = 1; i<=n; i++) {
			a[i] = s.nextInt();
		}
		dp[0] = 1;
		for(int i = 1; i<=n; i++) {
			int max = a[i], min = a[i];
			for(int j = i;j>=1;j--) {
				max = Math.max(max, a[j]);
				min = Math.min(min, a[j]);
				if(i-j==max-min) dp[i]=(dp[i]+dp[j-1])%mod;
			}
//			System.out.print(dp[i]);
		}
		System.out.print(dp[n]%mod);
	}
}
```

