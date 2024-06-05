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

