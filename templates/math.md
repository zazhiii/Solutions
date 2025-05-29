# 质数

质数定义：在**大于1**的数中，只有1和本身这两个约数。

## 1. 判断质数

**较小质数判断**

1. 小于2的不是质数
2. 枚举$2 \sim \sqrt{n}$内的数，若有约数则不是质数，反之则是质数

```java
	private static boolean isPrime(long n) {
		if(n < 2) return false;
		for(int i = 2; i <= n / i; i ++) {		//枚举 2 ~ sqrt(n)
			if(n % i == 0) return false;
		}
		return true;
	}
```

**较大质数判断 ---- Miller-Rabin测试**

> Java中的`BigInteger`已经封装好了Miller-Rabin测试，直接调用即可。
>
> 题目：[U148828 素数判断(Miller-Rabin模板) - 洛谷](https://www.luogu.com.cn/problem/U148828)

```java
    static int s = 10; // 测试次数

    static public void solve() throws IOException{
        String n = rd.nextLine();
        BigInteger bg = new BigInteger(n);
        pw.println(bg.isProbablePrime(s) ? "Yes" : "No");
    }
```



## 2.分解质因数

将$n$分解为质因数相乘 $$ n = p_1^{\alpha_1}\times p_2^{\alpha_2}\times ...\times p_k^{\alpha_k} $$，$p_1,p_2,...,p_k$为质数

注意到：大于$\sqrt{n}$的质因数至多有1个。若有2个或以上的话，其相乘会大于$n$，所以不会有1个以上。

1. 枚举从$2 \sim \sqrt{n}$的数。
2. 若是$n$的因数，则$n$不断除这个数，累计次数。
3. 因为从小到大将质数一个一个除完了，所以只要枚举到了因数，一定是质因数。
4. 枚举完了$2 \sim \sqrt{n}$ 的质因数，剩下的为1或者最后一个质因数。

```java
	public void divide(int n) {
		for(int i = 2; i <= n / i; i ++) {//枚举 2~sqrt(n)
			if(n % i == 0) {//i一定为质数
				int s = 0;
				while(n % i == 0) {
					n /= i;
					s ++;
				}
				pw.println(i + " " + s);
			}
		}
		if(n > 1) pw.println(n + " " + 1);
	}
```



## 3. 质数筛



**埃氏筛** $O(n\log(\log n))$

从小到大寻找因数，筛掉每一个因数的倍数。在遇到一个没有被筛掉的数时它一定是质数，如果是合数那么他一定会被他之前的因数筛掉

```java
    // 筛出 [0, n] 范围内的质数
    public static List<Integer> sift(int n){
        List<Integer> res = new ArrayList<>(n);
        boolean[] vis = new boolean[n + 1];
        for(int i = 2; i <= n; i ++){
            if(!vis[i]){
                res.add(i);
                for(int j = i + i; j <= n; j += i) vis[j] = true;
            }
        }
        return res;
    }
```



**欧拉筛** $O(n)$

> 埃氏筛已经够用了。



# 快速幂

> $\log n$

```java
    public long qpow(long a, long n){
        long ans = 1;
        while(n > 0){
            if(n % 2 == 1) ans = ans * a % mod;
            a = a * a % mod;
            n >>= 1;
        }
        return ans;
    }
```



# 二进制位中1的个数

> 遍历每一位的方法比较基础，不再赘述



1. 巧用 `lowBit: n & (n - 1)`，每次去除最低位1

```java
    public int bitCount(long x){
        int cnt = 0;
        while(x > 0){
            cnt ++;
            x -= x & (x - 1); 
        }
        return cnt;
    }
```



2. Java自带函数

```java
    public int cntOnes(long x){
        return Long.bitCount(x);
    }
```

