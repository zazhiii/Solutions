# 第 397 场周赛

[100296. 两个字符串的排列差](https://leetcode.cn/problems/permutation-difference-between-two-strings/)

给你两个字符串 `s` 和 `t`，每个字符串中的字符都不重复，且 `t` 是 `s` 的一个排列。

**排列差** 定义为 `s` 和 `t` 中每个字符在两个字符串中位置的绝对差值之和。

返回 `s` 和 `t` 之间的 **排列差** 。

>    哈希表存储`s`的字母的位置，询问`t`字母找出在s中的位置和当前位置求绝对值，累加

```java
class Solution {
	    Map<Character, Integer> map = new HashMap<>();
	    public int findPermutationDifference(String s, String t) {
            int n = s.length();
	        for(int i = 0; i<=n -1; i++){
	            map.put(s.charAt(i), i);//存 字母--位置
	        }
	        int ans = 0;
	        for(int i = 0; i<=s.length() - 1; i++){
	            ans += Math.abs(map.get(t.charAt(i)) - i);//计算位置
	        }
			return ans;
	    }
	}
```

B[100274. 从魔法师身上吸取的最大能量](https://leetcode.cn/problems/taking-maximum-energy-from-the-mystic-dungeon/)

在神秘的地牢中，`n` 个魔法师站成一排。每个魔法师都拥有一个属性，这个属性可以给你提供能量。有些魔法师可能会给你负能量，即从你身上吸取能量。

你被施加了一种诅咒，当你从魔法师 `i` 处吸收能量后，你将被立即传送到魔法师 `(i + k)` 处。这一过程将重复进行，直到你到达一个不存在 `(i + k)` 的魔法师为止。

换句话说，你将选择一个起点，然后以 `k` 为间隔跳跃，直到到达魔法师序列的末端，**在过程中吸收所有的能量**。

给定一个数组 `energy` 和一个整数`k`，返回你能获得的 **最大** 能量。

>    第$i$个位置由第$i-k$跳过来。
>
>    $dp[i] = dp[i-k]+energy[i]$
>
>    $ans = max(dp[j]),(j\in[n-k,n-1])$

```java
	class Solution {
	    public int maximumEnergy(int[] energy, int k) {
            int e[] = energy.clone();
            int n = energy.length;
			int[] dp = new int[n];
	    	for(int i= 0; i<=n - 1; i ++) dp[i] = e[i];
            int ans = -(int)1e9;
	    	for(int i = k; i<= n - 1; i++) {
	    		dp[i] = Math.max(dp[i - k] + energy[i], e[i]);
	    	}
            for(int i = n - 1; i >= n - k; i --){
                ans = Math.max(ans, dp[i]);
            }
	    	return ans;
	    }
	}
```

C[100281. 矩阵中的最大得分](https://leetcode.cn/problems/maximum-difference-score-in-a-grid/)

给你一个由 **正整数** 组成、大小为 `m x n` 的矩阵 `grid`。你可以从矩阵中的任一单元格移动到另一个位于正下方或正右侧的任意单元格（不必相邻）。从值为 `c1` 的单元格移动到值为 `c2` 的单元格的得分为 `c2 - c1` 。

你可以从 **任一** 单元格开始，并且必须至少移动一次。

返回你能得到的 **最大** 总得分。

>    1.    $dp[i][j]$含义：以$i,j$为结束位置能获得的最大能量
>
>    2.    状态转移：当前位置可由上方或者左方转移过来，对于单个方向分成上一个点为起点或不是起点两种情况（为什么要分这两种情况呢，因为有可能从其他地方转移到上一个位置的分数为负数，还不如从上一个位置重新为起点开始计算）。计算四种情况的最大值则为$dp[i][j]$的值。
>
>          无论怎么转移分数都要减去上一个位置的数，加上当前位置的数。手动推一遍这个过程就能理解了QAQ。
>
>          $dp[i][j] = MAX(max(dp[i][j-1], 0)+ a[i][j]-a[i][j-1],max(dp[i-1][j],0)+a[i][j]-a[i-1][j])$
>
>    3.    初始化边界：转移方程比一般情况减少一个方向即可，另外$dp[0][0]=0$（因为不可能以这里为终点）
>
>    4.    结果取除了$dp[0][0]$的最大值
>
>    $O(mn)$

```java
class Solution {
    public int maxScore(List<List<Integer>> grid) {
        int n = grid.size();
        int m = grid.get(0).size();
        int a[][] = new int[n][m];
        for(int i = 0; i<n; i++)//转成数组方便取
            for(int j = 0; j<m; j++)
                a[i][j] = grid.get(i).get(j);
        int dp[][] = new int[n][m];
        dp[0][0] = 0;
        for(int i = 1; i<n; i++) 
            dp[i][0] = Math.max(dp[i - 1][0], 0) + a[i][0] - a[i - 1][0];//初始化边界
        for(int j = 1; j<m; j++)
            dp[0][j] = Math.max(dp[0][j - 1], 0) + a[0][j] - a[0][j - 1];//初始化边界
        //状态转移
        for(int i = 1; i<n; i++){
            for(int j = 1; j<m; j++){
                dp[i][j] = Math.max(dp[i - 1][j], 0) + a[i][j] - a[i - 1][j];
                dp[i][j] = Math.max(dp[i][j], Math.max(dp[i][j - 1], 0) + a[i][j] - a[i][j - 1]);
            }
        }
        int ans = -(int)1e9;
        for(int i = 0; i<n; i++){
            for(int j = 0; j<m; j++){
               if(i == 0 && j == 0) {continue;}
                ans = Math.max(ans, dp[i][j]);
            }
        }
        return ans;
    }
}
```

# 第 398 场周赛

B. [100308. 特殊数组 II](https://leetcode.cn/contest/weekly-contest-398/problems/special-array-ii/)

如果数组的每一对相邻元素都是两个奇偶性不同的数字，则该数组被认为是一个 **特殊数组** 。

周洋哥有一个整数数组 `nums` 和一个二维整数矩阵 `queries`，对于 `queries[i] = [fromi, toi]`，请你帮助周洋哥检查子数组 `nums[fromi..toi]` 是不是一个 **特殊数组** 。

返回布尔数组 `answer`，如果 `nums[fromi..toi]` 是特殊数组，则 `answer[i]` 为 `true` ，否则，`answer[i]` 为 `false` 。

> tag：**前缀和**

> $st[i]$记录$0\sim i$有多少对不合法的数对。
>
> 对于每次询问的范围$l\sim r$，若中间没有不合法的数对则$st[r]=st[l]$，即特殊数组

```java
class Solution {
    public boolean[] isArraySpecial(int[] nums, int[][] queries) {
        int n = nums.length;
        int m = queries.length;
        boolean ans[] = new boolean[m];
        int s = 0;
        int st[] = new int[n];
        for(int i = 0; i < n - 1; i ++){
            if((nums[i] + nums[i + 1]) % 2 == 0) s ++;
            st[i + 1] = s;
        }
        for(int i = 0; i < m; i++){
            int l = queries[i][0];
            int r = queries[i][1];
            if(st[r] - st[l] > 0) ans[i] = true;
        }
        for(int i = 0; i < m; i++) ans[i] = !ans[i];
        return ans;
    }
}
```

# 第 399 场周赛

C. [3164. 优质数对的总数 II](https://leetcode.cn/problems/find-the-number-of-good-pairs-ii/)

给你两个整数数组 `nums1` 和 `nums2`，长度分别为 `n` 和 `m`。同时给你一个**正整数** `k`。

如果 `nums1[i]` 可以被 `nums2[j] * k` 整除，则称数对 `(i, j)` 为 **优质数对**（`0 <= i <= n - 1`, `0 <= j <= m - 1`）。

返回 **优质数对** 的总数。

>    **若$a\%b=0$则$b$一定是$a$的一个因子。**
>
>    枚举$nums_1$中所有数的因子以及每个因子的数量（即有多少个$nums[i]$包含这个因子）。
>
>    枚举$nums_2[i]\times k$判断是否在上述因子集合中，若在答案数累加上因子数
>
>    $O(n\times \sqrt{10^6} + m)$

```java
class Solution {
    public long numberOfPairs(int[] nums1, int[] nums2, int k) {
        int n = nums1.length;
        int m = nums2.length;
        Map<Integer, Integer> map = new HashMap<>();
        for(int i = 0; i < n; i ++){
            if(nums1[i] % k != 0) continue;
            for(int j = 1; j <= nums1[i] / j; j ++){
                if(nums1[i] % j == 0){
                    map.put(j, map.getOrDefault(j, 0) + 1);
                    if(j < nums1[i] / j) {
                        map.put(nums1[i] / j, map.getOrDefault(nums1[i] / j, 0) + 1);
                    }
                }
            }
        }
        long ans = 0;
        for(int x : nums2){
           ans += map.getOrDefault(x * k, 0);
        }
        return ans;
    }
}
```



# 第 400 场周赛

B	[100311. 无需开会的工作日](https://leetcode.cn/problems/count-days-without-meetings/)

给你一个正整数 `days`，表示员工可工作的总天数（从第 1 天开始）。另给你一个二维数组 `meetings`，长度为 `n`，其中 `meetings[i] = [start_i, end_i]` 表示第 `i` 次会议的开始和结束天数（包含首尾）。

返回员工可工作且没有安排会议的天数

>    **区间合并**
>
>    按照左端点排序，遍历每个区间，分类讨论一下下一个区间和当前区间的关系，答案加上超出的部分。
>
>    $O(m\log m + m) $

```java
class Solution {
    public int countDays(int days, int[][] meetings) {
        int n = meetings.length;
        Arrays.sort(meetings, (m1, m2) -> m1[0] - m2[0]);
        int ans = days;
        int r = 0;
        for(int i = 0; i < n; i ++){
            if(meetings[i][0] > r){
                ans -= meetings[i][1] - meetings[i][0] + 1;
                r = meetings[i][1];
            }else if(meetings[i][1] > r){
                ans -= meetings[i][1] - r;
                r = meetings[i][1];
            }
        }
        return ans;
    }
}
```

C	[100322. 删除星号以后字典序最小的字符串](https://leetcode.cn/problems/lexicographically-minimum-string-after-removing-stars/)

给你一个字符串 `s` 。它可能包含任意数量的 `'*'` 字符。你的任务是删除所有的 `'*'` 字符。

当字符串还存在至少一个 `'*'` 字符时，你可以执行以下操作：

-    删除最左边的 `'*'` 字符，同时删除该星号字符左边一个字典序 **最小** 的字符。如果有多个字典序最小的字符，你可以删除它们中的任意一个。

请你返回删除所有 `'*'` 字符以后，剩余字符连接而成的 字典序最小 的字符串。

>    显然删除靠后的最小字幕最优。
>
>    遍历，用26个栈存储每种字母的下标，遇到*则弹出字典序最小的字母对应的非空栈的栈顶元素（即最靠后的），弹出后用一个数组标记位置，计算答案时忽略该位置。

```java
class Solution {
    public String clearStars(String s) {
        char c[] = s.toCharArray();
        int n = s.length();
        boolean[] k = new boolean[n];
        List<Integer>[] words = new LinkedList[26];
        for(int i = 0; i < 26; i ++){
            words[i] = new LinkedList<>();
        }
        for(int i = 0; i < n; i ++){
            if(c[i] != '*'){
                words[c[i] - 'a'].add(i);
            }else{
                for(int j = 0; j < 26; j ++){
                    if(!words[j].isEmpty()){
                        k[words[j].get(words[j].size() - 1)] = true;
                        words[j].remove(words[j].size() - 1);
                        break;
                    }
                }
            }
        }
        String ans = "";
        for(int i = 0; i < n; i ++){
            if(c[i] != '*' && !k[i]) ans += c[i];
        }
        return ans;
    }
}
```

# 第 401 场周赛

### T3	[100320. 执行操作可获得的最大总奖励 II](https://leetcode.cn/problems/maximum-total-reward-using-operations-ii/)

给你一个整数数组 `rewardValues`，长度为 `n`，代表奖励的值。

最初，你的总奖励 `x` 为 0，所有下标都是 **未标记** 的。你可以执行以下操作 **任意次** ：

- 从区间 `[0, n - 1]` 中选择一个 **未标记** 的下标 `i`。
- 如果 `rewardValues[i]` **大于** 你当前的总奖励 `x`，则将 `rewardValues[i]` 加到 `x` 上（即 `x = x + rewardValues[i]`），并 **标记** 下标 `i`。

以整数形式返回执行最优操作能够获得的 **最大** 总奖励。

### T4	[100320. 执行操作可获得的最大总奖励 II](https://leetcode.cn/problems/maximum-total-reward-using-operations-ii/)

给你一个整数数组 `rewardValues`，长度为 `n`，代表奖励的值。

最初，你的总奖励 `x` 为 0，所有下标都是 **未标记** 的。你可以执行以下操作 **任意次** ：

- 从区间 `[0, n - 1]` 中选择一个 **未标记** 的下标 `i`。
- 如果 `rewardValues[i]` **大于** 你当前的总奖励 `x`，则将 `rewardValues[i]` 加到 `x` 上（即 `x = x + rewardValues[i]`），并 **标记** 下标 `i`。

以整数形式返回执行最优操作能够获得的 **最大** 总奖励。

# 第 402 场周赛

[3185. 构成整天的下标对数目 II](https://leetcode.cn/problems/count-pairs-that-form-a-complete-day-ii/)

给你一个整数数组 `hours`，表示以 **小时** 为单位的时间，返回一个整数，表示满足 `i < j` 且 `hours[i] + hours[j]` 构成 **整天** 的下标对 `i`, `j` 的数目。

**整天** 定义为时间持续时间是 24 小时的 **整数倍** 。

例如，1 天是 24 小时，2 天是 48 小时，3 天是 72 小时，以此类推。

> 类似两数之和的哈希表做法。
>
> 另外若$(a+b) \mod 24 = 0 $且$a、b$不是24的倍数的话、$a\mod 24+b\mod 24=24$。
>
> 先将所有元素$\mod 24$
>
> 单独统计24的倍数`cnt`。再统计$a+b=24$的对数（两数之和）
>
> 其中哈希表可以用一个长度$24$的数组优化。

```java
class Solution {
    public long countCompleteDayPairs(int[] a) {
        long ans = 0, n = a.length, cnt = 0;
        Map<Integer, Integer> map = new HashMap<>();
        for(int i = 0; i < n; i ++) {
            a[i] %= 24;
            if(a[i] == 0) cnt ++;
            else{
                ans += map.getOrDefault(24 - a[i], 0);
                map.put(a[i], map.getOrDefault(a[i], 0) + 1);
            }
        }
        ans += (cnt - 1) * cnt / 2;
        return ans;
    }
}
```

[3186. 施咒的最大总伤害](https://leetcode.cn/problems/maximum-total-damage-with-spell-casting/)

给你一个数组 `power` ，其中每个元素表示一个咒语的伤害值，可能会有多个咒语有相同的伤害值。

已知魔法师使用伤害值为 `power[i]` 的咒语时，他们就 **不能** 使用伤害为 `power[i] - 2` ，`power[i] - 1` ，`power[i] + 1` 或者 `power[i] + 2` 的咒语。

每个咒语最多只能被使用 **一次** 。

请你返回这个魔法师可以达到的伤害值之和的 **最大值** 

> 状态机dp

> 法一：双指针、哈希表、dp
>
> 将咒语大小和数量存入哈希表，将键取出排序。
>
> $dp[i]$：表示在$0\sim i$个数中能取到的最大值。
>
> $dp[i] =max(dp[i-1],dp[j]+a[i]*(number\ of\ a[i])$，其中$j$表示小于$a[i]-2$的最大数的下标。
>
> 对于$i$增加$j$单调不减，所以用一个指针寻找$j$
>
> 排序 ： $O(n\log n)$ 双指针：$O(n)$

```java
class Solution {
    public long maximumTotalDamage(int[] p) {
        Map<Integer, Integer> map = new HashMap<>();
        for(int x : p) map.put(x, map.getOrDefault(x, 0) + 1);
        int n = map.size();
        int a[] = new int[n], k = 0;
        for(int x : map.keySet()) a[k ++] = x;
        Arrays.sort(a);
        long dp[] = new long[n + 1];
        for(int i = 0, j = 0; i < n; i ++){
            while(a[j] < a[i] - 2) j ++;
            dp[i + 1] = Math.max(dp[i], dp[j] + 1l * a[i] * map.get(a[i]));
        }
        return dp[n];
    }
}
```

> 法二：排序、二分查找、dp
>
> $dp[i][1/0]$：选/不选第$i$个数$0\sim i$能取到的最大值。
>
> 从左往右遍历，分情况讨论$i$和$i -1$的关系，写对应的转移方程。（见代码）
>
> $O(n\log n)$

```java
class Solution {
    public long maximumTotalDamage(int[] p) {
        int n = p.length, a[] = new int[n + 1], k = 1;
        for(int x : p) a[k ++] = x;
        Arrays.sort(a);
        long dp[][] = new long[n + 1][2];
        for(int i = 1; i <= n; i ++){
            if(a[i] - a[i - 1] > 2){
                dp[i][0] = Math.max(dp[i - 1][0], dp[i - 1][1]);
                dp[i][1] = dp[i][0] + a[i];
            }else if(a[i] == a[i - 1]){
                dp[i][0] = dp[i - 1][0];
                dp[i][1] = dp[i - 1][1] + a[i];
            }else{
                int l = 0, r = i - 1, idx = 0;
                while(l <= r){
                    int m = (l + r) >> 1;
                    if(a[m] < a[i] - 2){
                        idx = m;
                        l = m + 1;
                    }else{
                        r = m - 1;
                    }
                }
                dp[i][0] = Math.max(dp[i - 1][0], dp[i - 1][1]);
                dp[i][1] = Math.max(dp[idx][0], dp[idx][1]) + a[i];
            }
        }            
        return Math.max(dp[n][0], dp[n][1]);
    }
}
```





# 第 403 场周赛

**T3** 	[100337. 最大化子数组的总成本](https://leetcode.cn/problems/maximize-total-cost-of-alternating-subarrays/)

给你一个长度为 `n` 的整数数组 `nums`。

子数组 `nums[l..r]`（其中 `0 <= l <= r < n`）的 **成本** 定义为：

```
cost(l, r) = nums[l] - nums[l + 1] + ... + nums[r] * (−1)r − l
```

你的任务是将 `nums` 分割成若干子数组，使得所有子数组的成本之和 **最大化**，并确保每个元素 **正好** 属于一个子数组。

返回在最优分割方式下的子数组成本之和的最大值。

```java
class Solution {
    /*  dp[i][0/1]: 第i个数取正/负0~i最大成本
        dp[i][0] = max(dp[i - 1][0], dp[i - 1][1]) + a[i]
        dp[i][1] = dp[i - 1][0] - a[i]
     */
    public long maximumTotalCost(int[] a) {
        long ans = 0;
        int n = a.length;
        long dp[][] = new long[n + 1][2];
        dp[1][0] = a[0];
        dp[1][1] = a[0];
        for(int i = 2; i <= n; i ++){
            dp[i][0] = Math.max(dp[i - 1][0], dp[i - 1][1]) + a[i - 1];
            dp[i][1] = dp[i - 1][0] - a[i - 1];
        }
        return Math.max(dp[n][0], dp[n][1]);
    }
}
```

# 第 133 场双周赛

**T3** [100346. 使二进制数组全部等于 1 的最少操作次数 II](https://leetcode.cn/problems/minimum-operations-to-make-binary-array-elements-equal-to-one-ii/)

给你一个二进制数组 `nums` 。

你可以对数组执行以下操作 **任意** 次（也可以 0 次）：

- 选择数组中 **任意** 一个下标 `i` ，并将从下标 `i` 开始一直到数组末尾 **所有** 元素 **反转** 。

**反转** 一个元素指的是将它的值从 0 变 1 ，或者从 1 变 0 。

请你返回将 `nums` 中所有元素变为 1 的 **最少** 操作次数。

> 思维

> 从数组开始位置思考，若第一个为1，则考虑的范围变为$1\sim n-1$，若第一个为0，则反转一次，考虑范围再次变为$1\sim n-1$。
>
> 对于最左侧的$0$，必须从这里反转一次，则后面的1将会变为0，这时变为往后碰到第一个1时再次反转，这样不断交替。可以发现反转次数为偶数时，后面的元素相当于没有反转，对碰到的0计数；反转次数为奇数时，后面的1变为0，对碰到的1计数。
>
> $O(n)$

```java
class Solution {
    public int minOperations(int[] a) {
        int ans = 0, n = a.length;
        for(int i = 0; i < n; i ++){
            if(a[i] == ans % 2) ans ++; 
        }
        return ans;
    }
}
```

# ✅第 406 场周赛

[3216. 交换后字典序最小的字符串 - 力扣（LeetCode）](https://leetcode.cn/problems/lexicographically-smallest-string-after-a-swap/description/)

给你一个仅由数字组成的字符串 `s`，在最多交换一次 **相邻** 且具有相同 **奇偶性** 的数字后，返回可以得到的字典序最小的字符串。

> 贪心

> 因为只能操作一次，那么交换靠左侧的数一定能让字典序尽可能地小，从左往右遍历，遇到第一对相同奇偶且交换后能使字典序更小地数对时，交换这两个数即可。

> $O(n)$

```java
class Solution {
    public String getSmallestString(String s) {
        char c[] = s.toCharArray();
        for(int i = 0; i < c.length - 1; i ++){
            if(c[i] % 2 == c[i + 1] % 2 && c[i] > c[i + 1]){
                char t = c[i];
                c[i] = c[i + 1];
                c[i + 1] = t;
                break;
            }
        }
        return String.valueOf(c);
    }
}
```

[3217. 从链表中移除在数组中存在的节点](https://leetcode.cn/problems/delete-nodes-from-linked-list-present-in-array/)

给你一个整数数组 `nums` 和一个链表的头节点 `head`。从链表中**移除**所有存在于 `nums` 中的节点后，返回修改后的链表的头节点。

> 数据结构

> 添加一个头部哨兵节点，**删除某个元素只能通过前面一个节点改变其下一个指向来删除**，从哨兵节点开始遍历判断下一个节点是否需要删除。

> $O(n)$

```java
/**
 * Definition for singly-linked list.
 * public class ListNode {
 *     int val;
 *     ListNode next;
 *     ListNode() {}
 *     ListNode(int val) { this.val = val; }
 *     ListNode(int val, ListNode next) { this.val = val; this.next = next; }
 * }
 */
class Solution {
    public ListNode modifiedList(int[] nums, ListNode head) {
        Set<Integer> set = new HashSet<>();
        for(int i = 0; i < nums.length; i ++) set.add(nums[i]);
        ListNode hh = new ListNode(0, head);
        ListNode cur = hh;
        while(cur.next != null){
            if(set.contains(cur.next.val)){
                cur.next = cur.next.next;
            }else{
                cur = cur.next;
            }
        }
        return hh.next;
    }
}
```

[3219. 切蛋糕的最小总开销 II](https://leetcode.cn/problems/minimum-cost-for-cutting-cake-ii/)

有一个 `m x n` 大小的矩形蛋糕，需要切成 `1 x 1` 的小块。

给你整数 `m` ，`n` 和两个数组：

- `horizontalCut` 的大小为 `m - 1` ，其中 `horizontalCut[i]` 表示沿着水平线 `i` 切蛋糕的开销。
- `verticalCut` 的大小为 `n - 1` ，其中 `verticalCut[j]` 表示沿着垂直线 `j` 切蛋糕的开销。

一次操作中，你可以选择任意不是 `1 x 1` 大小的矩形蛋糕并执行以下操作之一：

1. 沿着水平线 `i` 切开蛋糕，开销为 `horizontalCut[i]` 。
2. 沿着垂直线 `j` 切开蛋糕，开销为 `verticalCut[j]` 。

每次操作后，这块蛋糕都被切成两个独立的小蛋糕。

每次操作的开销都为最开始对应切割线的开销，并且不会改变。

请你返回将蛋糕全部切成 `1 x 1` 的蛋糕块的 **最小** 总开销。

> 贪心

> 注意到切割次数是不变的，那么让开销大的少切，开销小的多切。
>
> 每次考虑一切到底，每次**横/竖**一切到底需要切的次数和**竖/横**已经切了几次有关。用两个变量记录横竖已经切了几次，再用当前没切过的最大开销的切法计算当前一步的开销。

> $O(n)$

```java
class Solution {
    public long minimumCost(int m, int n, int[] h, int[] v) {
        Arrays.sort(h);Arrays.sort(v);
        int hh = 0, vv = 0;
        long ans = 0;
        int i, j;
        for(i = m - 2, j = n - 2; i >= 0 && j >= 0;){
            if(h[i] > v[j]){
                hh ++;
                ans += (vv + 1) * h[i];
                i --;
            }else{
                vv ++;
                ans += (hh + 1) * v[j];
                j --;
            }
        }
        if(j >= 0){
            for(int k = j; k >= 0; k --) ans += (hh + 1) * v[k];
        }
        if(i >= 0){
            for(int k = i; k >= 0; k --) ans += (vv + 1) * h[k];
        }
        return ans;
    }
}
```

# 第 136 场双周赛（待补T4）

[3240. 最少翻转次数使二进制矩阵回文 II - 力扣（LeetCode）](https://leetcode.cn/problems/minimum-number-of-flips-to-make-binary-grid-palindromic-ii/description/)

给你一个 `m x n` 的二进制矩阵 `grid` 。

如果矩阵中一行或者一列从前往后与从后往前读是一样的，那么我们称这一行或者这一列是 **回文** 的。

你可以将 `grid` 中任意格子的值 **翻转** ，也就是将格子里的值从 `0` 变成 `1` ，或者从 `1` 变成 `0` 。

请你返回 **最少** 翻转次数，使得矩阵中 **所有** 行和列都是 **回文的** ，且矩阵中 `1` 的数目可以被 `4` **整除** 。

> 思维

> 做思维题时，从最简单的情况开始思考。
>
> 1. 有四个镜像的地方一定满足1的个数是4的倍数，设四个位置有$c$个$1$操作数加$\min(c,4-c)$
> 2. 对于有中间一行或一列的格子，记录不需要改变的$1$的个数为$cnt$，需要操作的次数$diff$，$cnt\mod4$一定为2或0，若为0那么把$diff$操作的全变为0即可；若为2，那么从$diff$中拿一次出来变为1即可，若$diff$为0，就不能拿一次出来，就只能从$cnt$中将两个1变为0。
> 3. 对于有正中心的格子，这个格子一定要为$0$，不然1的总数一定为奇数，不可能为4的倍数

```java
class Solution {
    public int minFlips(int[][] a) {
        int n = a.length;
        int m = a[0].length;
        int ans = 0;
        for(int i = 0; i < n / 2; i ++){
            for(int j = 0; j < m / 2; j ++){
                int c = 0;
                c += a[i][j];
                c += a[i][m - 1 - j];
                c += a[n - 1 - i][j];
                c += a[n - 1 - i][m - 1 - j];
                ans += Math.min(c, 4 - c);
            }
        }
        int cnt = 0, diff = 0;
        if(n % 2 == 1){
            for(int j = 0; j < m / 2; j ++){
                if(a[n / 2][j] != a[n / 2][m - 1 - j]) diff ++;
                else cnt += a[n / 2][j] * 2;
            }
        }
        if(m % 2 == 1){
            for(int i = 0; i < n / 2; i ++){
                if(a[i][m / 2] != a[n - 1 - i][m / 2]) diff ++;
                else cnt += a[i][m / 2] * 2;
            }
        }
        if(n % 2 == 1 && m % 2 == 1) ans += a[n / 2][m / 2];
        
        if(diff > 0) ans += diff;
        else ans += cnt % 4;
        return ans;
    }
}
```

# 第 408 场周赛（待补T3、T4）

# 第 409 场周赛（待补T4）

[3244. 新增道路查询后的最短距离 II - 力扣（LeetCode）](https://leetcode.cn/problems/shortest-distance-after-road-addition-queries-ii/description/)

给你一个整数 `n` 和一个二维整数数组 `queries`。

有 `n` 个城市，编号从 `0` 到 `n - 1`。初始时，每个城市 `i` 都有一条**单向**道路通往城市 `i + 1`（ `0 <= i < n - 1`）。

`queries[i] = [ui, vi]` 表示新建一条从城市 `ui` 到城市 `vi` 的**单向**道路。每次查询后，你需要找到从城市 `0` 到城市 `n - 1` 的**最短路径**的**长度**。

所有查询中不会存在两个查询都满足 `queries[i][0] < queries[j][0] < queries[i][1] < queries[j][1]`。

返回一个数组 `answer`，对于范围 `[0, queries.length - 1]` 中的每个 `i`，`answer[i]` 是处理完**前** `i + 1` 个查询后，从城市 `0` 到城市 `n - 1` 的最短路径的*长度*。

> 区间并查集

```java
class Solution {
    int p[];
    public int[] shortestDistanceAfterQueries(int n, int[][] q) {
        p = new int[n];
        int ans[] = new int[q.length];
        for(int i = 0; i < n; i ++) p[i] = i;
        int cnt = n - 1;
        for(int i = 0; i < q.length; i ++){
            int l = q[i][0];
            int r = q[i][1] - 1;
            for(int j = find(l); j < r; j = find(j + 1)){
                p[j] = find(r);
                cnt --;
            }
            ans[i] = cnt;
        }
        return ans;
    }
    public int find(int x){
        if(p[x] != x) p[x] = find(p[x]);
        return p[x];
    }
}
```

# 第 411 场周赛（待补T3）



[3260. 找出最大的 N 位 K 回文数 - 力扣（LeetCode）](https://leetcode.cn/problems/find-the-largest-palindrome-divisible-by-k/description/)



[3261. 统计满足 K 约束的子字符串数量 II](https://leetcode.cn/problems/count-substrings-that-satisfy-k-constraint-ii/)

给你一个 **二进制** 字符串 `s` 和一个整数 `k`。另给你一个二维整数数组 `queries` ，其中 `queries[i] = [li, ri]` 。

如果一个 **二进制字符串** 满足以下任一条件，则认为该字符串满足 **k 约束**：

- 字符串中 `0` 的数量最多为 `k`。
- 字符串中 `1` 的数量最多为 `k`。

返回一个整数数组 `answer` ，其中 `answer[i]` 表示 `s[li..ri]` 中满足 **k 约束** 的 子字符串 的数量。

> 前缀和、双指针、二分

> 枚举所有点`j`作为右端点，求出以该点为右端点的最长字串的左端点`i`，这一步用双指针实现：当因为右端点右移导致字串不合法时，移动左端点直到合法。用`left[]`记录每个右端点`j`的最远合法左端点`i`。
>
> 设询问区间`[l, r]`，其中一些点`j`的`left[j]`小于`l`，这些区间`[l, j]`中所有子串都合法，用公式求和字串数量$\frac{(len+1)\times len}{2}$；
>
> 对于某些点`k`的`left[k]`大于等于`l`，这些区间的字串用`k - left[k] + 1`计算一个点的合法子串数，对于所有这些点，用前缀和计算。
>
> 对于这两种点的分割点，用二分查找实现，因为`left[]`具有单调性，查找最右侧`left[i] < l`的点`i`即为第一种点的最右侧点

```java
class Solution {
    public long[] countKConstraintSubstrings(String s, int k, int[][] q) {
        char c[] = s.toCharArray();
        int n = c.length;
        // 记录以i为右端点的最长合法子串的左端点left[i]
        int left[] = new int[n];
        int t0 = 0, t1 = 0;
        for(int i = 0, j = 0; j < n; j ++){
            if(c[j] == '1') t1 ++;
            else t0 ++;
            while(t0 > k && t1 > k){
                if(c[i] == '1') t1 --;
                else t0 --;
                i ++;
            } 
            left[j] = i;
        }
        //前缀和
        long pre[] = new long[n + 1];
        for(int i = 0; i < n; i ++) pre[i + 1] = pre[i] + i - left[i] + 1;

        long ans[] = new long[q.length];
        for(int i = 0; i < q.length; i ++){
            //查找分界点
            int l = q[i][0], r = q[i][1], idx = q[i][0] - 1;
            while(l <= r){
                int m = (l + r) >>> 1;
                if(left[m] < q[i][0]) {idx = m; l = m + 1;}
                else r = m - 1;
            }
            // 公式计算前半段，前缀和计算后半段
            int len = idx - q[i][0] + 1;
            ans[i] += 1l * (len + 1) * len / 2;
            ans[i] += pre[q[i][1] + 1] - pre[idx + 1];
        }
        return ans;
    }
}
```

