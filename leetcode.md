[209. 长度最小的子数组](https://leetcode.cn/problems/minimum-size-subarray-sum/)

给定一个含有 `n` 个正整数的数组和一个正整数 `target` **。**

找出该数组中满足其总和大于等于 `target` 的长度最小的 **连续子数组**

`[numsl, numsl+1, ..., numsr-1, numsr]` ，并返回其长度**。**如果不存在符合条件的子数组，返回 `0` 。

> 前缀和+二分
>
> 枚举起点，二分查找终点的位置。
>
> 前缀和数组$s[r] - s[l - 1] = target$，则$s[r] = s[l - 1] + target$
>
> 枚举是$s[l]$，查找$s[r]$

```java
class Solution {
    public int minSubArrayLen(int target, int[] nums) {
        int N = nums.length;
        int[] s = new int[N + 1];
        for(int i = 1; i<=N; i++) s[i] = s[i - 1] + nums[i - 1];//初始化前缀和
        if(s[N] < target) return 0; //不存在
        int res = (int)1e5;
        for(int i = 1; i<=N; i++){
            if(s[N] - s[i-1] < target) continue;
            int tar = s[i - 1] + target;
            int tarIdx = 0;
            int l = i, r = N;
            while( l <= r){
                int m = (l + r) >>> 1;
                if(tar <= s[m]) {
                    tarIdx = m;
                    r = m - 1;
                }
                else {
                    l = m + 1;
                } 
            }
            res = Math.min(res, tarIdx - (i-1));
        }
        return res;
    }
}
```

[238. 除自身以外数组的乘积](https://leetcode.cn/problems/product-of-array-except-self/)

给你一个整数数组 `nums`，返回 *数组 `answer` ，其中 `answer[i]` 等于 `nums` 中除 `nums[i]` 之外其余各元素的乘积* 。

题目数据 **保证** 数组 `nums`之中任意元素的全部前缀元素和后缀的乘积都在 **32 位** 整数范围内。

请 **不要使用除法，**且在 `O(*n*)` 时间复杂度内完成此题。

> 类似前缀和
>
> 求出前缀积`l[]`，后缀积`r[]`。`res[i] = l[i - 1]*r[i + 1]`

```java
class Solution {
    public int[] productExceptSelf(int[] nums) {
        int N = nums.length;
        int[] res = new int[N];
        int[] l = new int[N + 1];
        int[] r = new int[N + 2];
        l[0] = 1;
        r[N + 1] = 1;
        for(int i = 1; i<=N; i ++) l[i] = l[i - 1]*nums[i - 1];
        for(int i = N; i>=1; i --) r[i] = r[i + 1]*nums[i - 1];
        for(int i = 1; i<=N; i ++){
            res[i - 1] = l[i - 1]*r[i + 1];
        }
        return res;
    }
}
```

[363. 矩形区域不超过 K 的最大数值和](https://leetcode.cn/problems/max-sum-of-rectangle-no-larger-than-k/)

给你一个 `m x n` 的矩阵 `matrix` 和一个整数 `k` ，找出并返回矩阵内部矩形区域的不超过 `k` 的最大数值和。

题目数据保证总会存在一个数值和不超过 `k` 的矩形区域。

> 枚举+二维前缀和
>
> 枚举所有矩阵，用二维前缀和数组求区间和

```java
class Solution {
    public int maxSumSubmatrix(int[][] matrix, int k) {
        int m = matrix.length;
        int n = matrix[0].length;
        int[][] s = new int[m + 1][n + 1];
        for (int i = 1; i <= m; i++) {
            for (int j = 1; j <= n; j++) {
                s[i][j] = s[i - 1][j] + s[i][j - 1] - s[i - 1][j - 1] + matrix[i - 1][j - 1];
            }
        }
        int res = (int)-1e5;
        for (int l = 1; l <= n; l++) {
            for (int h = 1; h <= m; h++) {
                for (int i = 0; i <= m - h; i++) {
                    for (int j = 0; j <= n - l; j++) {
                        int x1 = i + 1;
                        int y1 = j + 1;
                        int x2 = i + h;
                        int y2 = j + l;
                        int sum = s[x2][y2] - s[x1 - 1][y2] - s[x2][y1 - 1] + s[x1 - 1][y1 - 1];
                        if (sum <= k){
                            res = Math.max(res, sum);
                        }
                            
                    }
                }
            }
        }
        return res;
    }
}
```

[410. 分割数组的最大值](https://leetcode.cn/problems/split-array-largest-sum/)

给定一个非负整数数组 `nums` 和一个整数 `k` ，你需要将这个数组分成 `k` 个非空的连续子数组。

设计一个算法使得这 `k` 个子数组各自和的最大值最小。

> 二分答案

```java
class Solution {
    public int splitArray(int[] nums, int k) {
        int n = nums.length;
        int l = 0, r = (int)1e9;
        int res = 0;
        while(l <= r){
            int m = (l + r) >>> 1;
            if(check(m, k, nums)) {
                res = m;
                r = m - 1;
            }else{
                l = m + 1;
            }
        }
        return res;
    }
    public boolean check(int m, int k, int[] nums){
        int cnt = 0;
        int sum = 0;
        for(int i = 0; i<=nums.length - 1; i++){
            if(nums[i] > m) return false;
            sum+=nums[i];
            if(sum > m){
                sum = nums[i];
                cnt ++;
            }
        }
        cnt ++;
        return cnt <= k; 
    }
}
```

[523. 连续的子数组和](https://leetcode.cn/problems/continuous-subarray-sum/)

给你一个整数数组 `nums` 和一个整数 `k` ，编写一个函数来判断该数组是否含有同时满足下述条件的连续子数组：

- 子数组大小 **至少为 2** ，且
- 子数组元素总和为 `k` 的倍数。

如果存在，返回 `true` ；否则，返回 `false` 。

如果存在一个整数 `n` ，令整数 `x` 符合 `x = n * k` ，则称 `x` 是 `k` 的一个倍数。`0` 始终视为 `k` 的一个倍数。

> 前缀和+二分查找
>
> 

```java
class Solution {
    public boolean checkSubarraySum(int[] nums, int k) {
        int n = nums.length;
        if (n < 2)
            return false;
        if (k == 1)
            return true;
        int[] s = new int[n];
        s = new int[n + 1];
        for (int i = 1; i <= n; i++)
            s[i] = s[i - 1] + nums[i - 1];
        for (int i = 1; i <= n - 1; i++) {
            for (int x = 0;; x++) {
                int tar = k * x + s[i - 1];// 目标值
                if (tar > s[n])
                    break;
                int l = i + 1, r = n;
                while (l <= r) {
                    int m = (l + r) >>> 1;
                    if (tar < s[m])
                        r = m - 1;
                    else if (s[m] < tar)
                        l = m + 1;
                    else
                        return true;
                }
            }
        }
        return false;
    }
}
```



[525. 连续数组](https://leetcode.cn/problems/contiguous-array/)

给定一个二进制数组 `nums` , 找到含有相同数量的 `0` 和 `1` 的最长连续子数组，并返回该子数组的长度。

> 枚举+前缀和
>
> 从最长的子数组开始枚举

```java
class Solution {
    public int findMaxLength(int[] nums) {
        int n = nums.length;
        int[] s = new int[n + 1];
        for(int i = 1; i<=n; i ++) s[i] = s[i - 1] + nums[i - 1];
        for(int l = n - (n%2); l>=2; l -= 2){
            for(int i = 1; i <= n - l + 1; i++){
                if(s[i + l - 1] - s[i - 1] == l/2) {
                    return l;
                }
            }
        }
        return 0;
    }
}
```

