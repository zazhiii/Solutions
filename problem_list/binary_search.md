# 1. 二分查找

## 基础二分查找

[704. 二分查找](https://leetcode.cn/problems/binary-search/)

给定一个 `n` 个元素有序的（升序）整型数组 `nums` 和一个目标值 `target` ，写一个函数搜索 `nums` 中的 `target`，如果目标值存在返回下标，否则返回 `-1`。

```java
class Solution {
    public int search(int[] a, int t) {
        int l = 0, r = a.length - 1;
        while(l <= r){
            int m = (l + r) >>> 1;
            if(a[m] < t) l = m + 1;
            else if(t < a[m]) r = m - 1;
            else return m;
        }
        return -1;
    }
}
```

## `lower_bound`

[35. 搜索插入位置](https://leetcode.cn/problems/search-insert-position/)

给定一个排序数组和一个目标值，在数组中找到目标值，并返回其索引。如果目标值不存在于数组中，返回它将会被按顺序插入的位置。

请必须使用时间复杂度为 `O(log n)` 的算法。

> 查找大于等于目标值的最小值的位置。

```java
class Solution {
    public int searchInsert(int[] a, int t) {
        int l = 0, r = a.length - 1;
        while(l <= r){
            int m = (l + r) >>> 1;
            if(a[m] >= t) r = m - 1;
            else l = m + 1;
        }
        return l;
    }
}
```

## `leftmost, rightmost`

[34. 在排序数组中查找元素的第一个和最后一个位置](https://leetcode.cn/problems/find-first-and-last-position-of-element-in-sorted-array/)

给你一个按照非递减顺序排列的整数数组 `nums`，和一个目标值 `target`。请你找出给定目标值在数组中的开始位置和结束位置。

如果数组中不存在目标值 `target`，返回 `[-1, -1]`。

你必须设计并实现时间复杂度为 `O(log n)` 的算法解决此问题。

```java
class Solution {
    public int[] searchRange(int[] a, int t) {
        int l = leftMost(a, t);
        int r = rightMost(a, t);
        return new int[]{l, r};
    }
    public int leftMost(int a[], int t){
        int l = 0, r = a.length - 1, ans = -1;
        while(l <= r){
            int m = (l + r) >>> 1;
            if(a[m] < t) l = m + 1;
            else if(t < a[m]) r = m - 1;
            else{ans = m; r = m - 1;}
        }
        return ans;
    }
    public int rightMost(int a[], int t){
        int l = 0, r = a.length - 1, ans = -1;
        while(l <= r){
            int m = (l + r) >>> 1;
            if(a[m] < t) l = m + 1;
            else if(t < a[m]) r = m - 1;
            else{ans = m; l = m + 1;}
        }
        return ans;
    }
}
```

# 2. 二分的应用

[153. 寻找旋转排序数组中的最小值](https://leetcode.cn/problems/find-minimum-in-rotated-sorted-array/)

已知一个长度为 `n` 的数组，预先按照升序排列，经由 `1` 到 `n` 次 **旋转** 后，得到输入数组。例如，原数组 `nums = [0,1,2,4,5,6,7]` 在变化后可能得到：

- 若旋转 `4` 次，则可以得到 `[4,5,6,7,0,1,2]`
- 若旋转 `7` 次，则可以得到 `[0,1,2,4,5,6,7]`

注意，数组 `[a[0], a[1], a[2], ..., a[n-1]]` **旋转一次** 的结果为数组 `[a[n-1], a[0], a[1], a[2], ..., a[n-2]]` 。

给你一个元素值 **互不相同** 的数组 `nums` ，它原来是一个升序排列的数组，并按上述情形进行了多次旋转。请你找出并返回数组中的 **最小元素** 。

你必须设计一个时间复杂度为 `O(log n)` 的算法解决此问题

> 由题目可知，该数组可以分为两段递增序列。在二分时，如果$a[i]$小于等于$a[n-1]$那么它肯定属于第二个序列，我们继续向左找，找到这段序列的最小值的位置；反之它属于第一段序列我们应该右查找。

> $O(\log n)$

```java
class Solution {
    public int findMin(int[] a) {
        int l = 0, r = a.length - 1;
        while(l <= r){
            int m = (l + r) >>> 1;
            if(a[m] <= a[a.length - 1]) r = m - 1;
            else l = m + 1;
        }
        return a[l];
    }
}
```

[33. 搜索旋转排序数组 - 力扣（LeetCode）](https://leetcode.cn/problems/search-in-rotated-sorted-array/description/?envType=study-plan-v2&envId=top-100-liked)

整数数组 `nums` 按升序排列，数组中的值 **互不相同** 。

在传递给函数之前，`nums` 在预先未知的某个下标 `k`（`0 <= k < nums.length`）上进行了 **旋转**，使数组变为 `[nums[k], nums[k+1], ..., nums[n-1], nums[0], nums[1], ..., nums[k-1]]`（下标 **从 0 开始** 计数）。例如， `[0,1,2,4,5,6,7]` 在下标 `3` 处经旋转后可能变为 `[4,5,6,7,0,1,2]` 。

给你 **旋转后** 的数组 `nums` 和一个整数 `target` ，如果 `nums` 中存在这个目标值 `target` ，则返回它的下标，否则返回 `-1` 。

你必须设计一个时间复杂度为 `O(log n)` 的算法解决此问题。

> 通过上一题的方法找出分界点，在用两次二分查找，查找目标值。

> $O(\log n)$

```java
class Solution {
    public int search(int[] a, int t) {
        int n = a.length;
        int l = 0, r = n - 1, idx = 0;
        while(l <= r){
            int m = (l + r) >>> 1;
            if(a[m] <= a[n - 1]){idx = m; r = m - 1;}
            else l = m + 1;
        }
        int ans = 0;
        ans = search(a, t, 0, idx - 1);
        if(ans != -1) return ans;
        ans = search(a, t, idx, n - 1);
        return ans;        
    }
    public int search(int a[], int t, int l, int r){
        while(l <= r){
            int m = (l + r) >>> 1;
            if(a[m] < t) l = m + 1;
            else if(t < a[m]) r = m - 1;
            else return m;
        }
        return -1;
    }
}
```

[74. 搜索二维矩阵 - 力扣（LeetCode）](https://leetcode.cn/problems/search-a-2d-matrix/description/?envType=study-plan-v2&envId=top-100-liked)

给你一个满足下述两条属性的 `m x n` 整数矩阵：

- 每行中的整数从左到右按非严格递增顺序排列。
- 每行的第一个整数大于前一行的最后一个整数。

给你一个整数 `target` ，如果 `target` 在矩阵中，返回 `true` ；否则，返回 `false` 。

> 先用二分查找目标值所在行，在二分查找所在列。

> $O(\log^2 n)$

```java
class Solution {
    public boolean searchMatrix(int[][] a, int t) {
        int n = a.length, m = a[0].length;
        int i = 0, j = n - 1;
        while(i <= j){
            int mid = (i + j) >>> 1;
            if(a[mid][m - 1] < t) i = mid + 1;
            else if(t < a[mid][0]) j = mid - 1;
            else{
                int L = 0, R = m - 1;
                while(L <= R){
                    int MID = (L + R) >>> 1;
                    if(a[mid][MID] < t) L = MID + 1;
                    else if(t < a[mid][MID]) R = MID - 1;
                    else return true;
                }
                return false;
            }
        }
        return false;
    }
}
```

[P1102 A-B 数对 - 洛谷 | 计算机科学教育新生态 (luogu.com.cn)](https://www.luogu.com.cn/problem/P1102)

给出一串正整数数列以及一个正整数 $C$，要求计算出所有满足 $A - B = C$ 的数对的个数（不同位置的数字一样的数对算不同的数对）。

对于 $100\%$ 的数据，$1 \leq N \leq 2 \times 10^5$，$0 \leq a_i <2^{30}$，$1 \leq C < 2^{30}$。

> leftmost、rightmost

```java
import java.io.*;
import java.util.*;

public class Main {
    static Scanner sc = new Scanner(System.in);
    static PrintWriter pw = new PrintWriter(new BufferedWriter(new OutputStreamWriter(System.out)));
    public static void main(String[] args) throws IOException {     
        int n = sc.nextInt();
        int t = sc.nextInt();
        int a[] = new int[n];
        for(int i = 0; i < n; i ++) a[i] = sc.nextInt();
        Arrays.sort(a);
        long ans = 0;
        for(int i = 0; i < n; i ++){
            int l = leftMost(a, i + 1, n - 1, 1l  * a[i] + t);
            int r = rightMost(a, i + 1, n - 1, 1l * a[i] + t);
            if(l != -1) ans += 1l * r - l + 1;
        }
        pw.println(ans);
        pw.flush();pw.close();
    }
    public static int leftMost(int a[], int l, int r, long t){
        int ans = -1;
        while(l <= r){
            int m = (l + r) >>> 1;
            if(a[m] < t) l = m + 1;
            else if(a[m] > t) r = m - 1;
            else{ans = m; r = m - 1;}
        } 
        return ans;
    }
    public static int rightMost(int a[], int l, int r, long t){
        int ans = -1;
        while(l <= r){
            int m = (l + r) >>> 1;
            if(a[m] < t) l = m + 1;
            else if(a[m] > t) r = m - 1;
            else{ans = m; l = m + 1;}
        } 
        return ans;
    }
}
```



[P1678 烦恼的高考志愿 - 洛谷 | 计算机科学教育新生态 (luogu.com.cn)](https://www.luogu.com.cn/problem/P1678)

现有 $m$ 所学校，每所学校预计分数线是 $a_i$。有 $n$ 位学生，估分分别为 $b_i$。

根据 $n$ 位学生的估分情况，分别给每位学生推荐一所学校，要求学校的预计分数线和学生的估分相差最小（可高可低，毕竟是估分嘛），这个最小值为不满意度。求所有学生不满意度和的最小值。

对于 $100\%$ 的数据，$1\leq n,m\leq100000$，估分和录取线 $\leq 1000000$ 且均为非负整数。

```java
import java.io.*;
import java.util.*;

public class Main {
    static Scanner sc = new Scanner(System.in);
    static PrintWriter pw = new PrintWriter(new BufferedWriter(new OutputStreamWriter(System.out)));
    static int n, m, a[], b[];
    public static void main(String[] args) throws IOException {     
        n = sc.nextInt();
        m = sc.nextInt();
        a = new int[n];
        b = new int[m];
        for(int i = 0; i < n; i ++) a[i] = sc.nextInt();
        for(int i = 0; i < m; i ++) b[i] = sc.nextInt();
        Arrays.sort(a);
        long ans = 0;
        for(int i = 0; i < m; i ++){
            if(b[i] < a[0]){ans += a[0] - b[i]; continue;}
            if(b[i] >= a[n - 1]){ans += b[i] - a[n - 1]; continue;}
            int l = 0, r = n - 1, res = 0;
            while(l <= r){
                int mid = (l + r) >>> 1;
                if(a[mid] <= b[i]){res = mid; l = mid + 1;}
                else r = mid - 1;
            }
            ans += Math.min(b[i] - a[res], a[res + 1] - b[i]);
        }
        pw.println(ans);
        pw.flush();pw.close();
    }
}
```



# 3. 小数二分

https://www.luogu.com.cn/problem/P1024

有形如：$a x^3 + b x^2 + c x + d = 0$  这样的一个一元三次方程。给出该方程中各项的系数（$a,b,c,d$ 均为实数），并约定该方程存在三个不同实根（根的范围在 $-100$ 至 $100$ 之间），且根与根之差的绝对值 $\ge 1$。要求由小到大依次在同一行输出这三个实根(根与根之间留有空格)，并精确到小数点后 $2$ 位。

提示：记方程 $f(x) = 0$，若存在 $2$ 个数 $x_1$ 和 $x_2$，且 $x_1 < x_2$，$f(x_1) \times f(x_2) < 0$，则在 $(x_1, x_2)$ 之间一定有一个根。

# 4. 二分答案



[P1873 [COCI 2011/2012 #5\] EKO / 砍树 - 洛谷 | 计算机科学教育新生态 (luogu.com.cn)](https://www.luogu.com.cn/problem/P1873)

伐木工人 Mirko 需要砍 $M$ 米长的木材。对 Mirko 来说这是很简单的工作，因为他有一个漂亮的新伐木机，可以如野火一般砍伐森林。不过，Mirko 只被允许砍伐一排树。

Mirko 的伐木机工作流程如下：Mirko 设置一个高度参数 $H$（米），伐木机升起一个巨大的锯片到高度 $H$，并锯掉所有树比 $H$ 高的部分（当然，树木不高于 $H$ 米的部分保持不变）。Mirko 就得到树木被锯下的部分。例如，如果一排树的高度分别为 $20,15,10$ 和 $17$，Mirko 把锯片升到 $15$ 米的高度，切割后树木剩下的高度将是 $15,15,10$ 和 $15$，而 Mirko 将从第 $1$ 棵树得到 $5$ 米，从第 $4$ 棵树得到 $2$ 米，共得到 $7$ 米木材。

Mirko 非常关注生态保护，所以他不会砍掉过多的木材。这也是他尽可能高地设定伐木机锯片的原因。请帮助 Mirko 找到伐木机锯片的最大的整数高度 $H$，使得他能得到的木材至少为 $M$ 米。换句话说，如果再升高 $1$ 米，他将得不到 $M$ 米木材。

> 二分锯片高度，再求和每棵树的锯片上方的高度，判断是否有$M$米



[P2440 木材加工 - 洛谷 | 计算机科学教育新生态 (luogu.com.cn)](https://www.luogu.com.cn/problem/P2440)

木材厂有 $n$ 根原木，现在想把这些木头切割成 $k$ 段长度**均**为 $l$ 的小段木头（木头有可能有剩余）。

当然，我们希望得到的小段木头越长越好，请求出 $l$ 的最大值。

木头长度的单位是 $\text{cm}$，原木的长度都是正整数，我们要求切割得到的小段木头的长度也是正整数。

> 二分$l$



[P2678 [NOIP2015 提高组\] 跳石头 - 洛谷 | 计算机科学教育新生态 (luogu.com.cn)](https://www.luogu.com.cn/problem/P2678)

这项比赛将在一条笔直的河道中进行，河道中分布着一些巨大岩石。组委会已经选择好了两块岩石作为比赛起点和终点。在起点和终点之间，有 $N$ 块岩石（不含起点和终点的岩石）。在比赛过程中，选手们将从起点出发，每一步跳向相邻的岩石，直至到达终点。

为了提高比赛难度，组委会计划移走一些岩石，使得选手们在比赛过程中的**最短跳跃距离尽可能长**。由于预算限制，组委会至多从起点和终点之间移走 $M$ 块岩石（不能移走起点和终点的岩石）。



[P3853 [TJOI2007\] 路标设置 - 洛谷 | 计算机科学教育新生态 (luogu.com.cn)](https://www.luogu.com.cn/problem/P3853)

我们把公路上相邻路标的最大距离定义为该公路的“空旷指数”。

现在政府决定在公路上增设一些路标，使得公路的“空旷指数”最小。他们请求你设计一个程序计算能达到的最小值是多少。请注意，公路的起点和终点保证已设有路标，公路的长度为整数，并且原有路标和新设路标都必须距起点整数个单位距离。



[P1182 数列分段 Section II - 洛谷 | 计算机科学教育新生态 (luogu.com.cn)](https://www.luogu.com.cn/problem/P1182)

对于给定的一个长度为N的正整数数列 $A_{1\sim N}$，现要将其分成 $M$（$M\leq N$）段，并要求每段连续，且每段和的最大值最小。



[P3743 kotori的设备 - 洛谷 | 计算机科学教育新生态 (luogu.com.cn)](https://www.luogu.com.cn/problem/P3743)

第 $i$ 个设备每秒消耗 $a_i$ 个单位能量。能量的使用是连续的，也就是说能量不是某时刻突然消耗的，而是匀速消耗。也就是说，对于任意实数，在 $k$ 秒内消耗的能量均为 $k\times a_i$ 单位。在开始的时候第 $i$ 个设备里存储着 $b_i$ 个单位能量。

同时 kotori 又有一个可以给任意一个设备充电的充电宝，每秒可以给接通的设备充能 $p$ 个单位，充能也是连续的，不再赘述。你可以在任意时间给任意一个设备充能，从一个设备切换到另一个设备的时间忽略不计。

kotori 想把这些设备一起使用，直到其中有设备能量降为  $0$。所以 kotori 想知道，在充电器的作用下，她最多能将这些设备一起使用多久。



https://www.luogu.com.cn/problem/P1902

迷阵由 $n\times m$ 个相同的小房间组成，每个房间与相邻四个房间之间有门可通行。在第 $n$ 行的 $m$ 个房间里有 $m$ 个机关，这些机关必须全部打开才可以进入大使馆。而第 $1$ 行的 $m$ 个房间有 $m$ 扇向外打开的门，是迷阵的入口。除了第 $1$ 行和第 $n$ 行的房间外，每个房间都被使馆的安保人员安装了激光杀伤装置，将会对进入房间的人造成一定的伤害。第 $i$ 行第 $j$ 列 造成的伤害值为 $p_{i,j}$（第 $1$ 行和第 $n$ 行的 $p$ 值全部为 $0$）。

现在某组织打算以最小伤害代价进入迷阵，打开全部机关，显然，他们可以选 择任意多的人从任意的门进入，但必须到达第 $n$ 行的每个房间。一个士兵受到的伤害值为他到达某个机关的路径上所有房间的伤害值中的最大值，整个部队受到的伤害值为所有士兵的伤害值中的最大值。现在，这个恐怖组织掌握了迷阵的情况，他们需要提前知道怎么安排士兵的行进路线可以使得整个部队的伤害值最小。

>    二分答案 + BFS



https://www.luogu.com.cn/problem/P1314

`小T` 是一名质量监督员，最近负责检验一批矿产的质量。这批矿产共有 $n$ 个矿石，从 $1$ 到 $n$ 逐一编号，每个矿石都有自己的重量 $w_i$ 以及价值 $v_i$ 。检验矿产的流程是：

1. 给定$ m$ 个区间 $[l_i,r_i]$；
2. 选出一个参数 $W$；
3. 对于一个区间 $[l_i,r_i]$，计算矿石在这个区间上的检验值 $y_i$：

$$y_i=\sum\limits_{j=l_i}^{r_i}[w_j \ge W] \times \sum\limits_{j=l_i}^{r_i}[w_j \ge W]v_j$$  

其中 $j$ 为矿石编号。

这批矿产的检验结果 $y$ 为各个区间的检验值之和。即：$\sum\limits_{i=1}^m y_i$  

若这批矿产的检验结果与所给标准值 $s$ 相差太多，就需要再去检验另一批矿产。`小T` 不想费时间去检验另一批矿产，所以他想通过调整参数 $W$ 的值，让检验结果尽可能的靠近标准值 $s$，即使得 $|s-y|$ 最小。请你帮忙求出这个最小值。

对于 $100\%$ 的数据，有 $ 1 ≤n ,m≤200,000$，$0 < w_i,v_i≤10^6$，$0 < s≤10^{12}$，$1 ≤l_i ≤r_i ≤n$ 。

>    二分 + 前缀和

>    二分$W$值；
>
>    若$Y > s$，$W$往大找。
>
>    若$Y<s$，$W$往小找。



[P1083 [NOIP2012 提高组\] 借教室 - 洛谷 | 计算机科学教育新生态 (luogu.com.cn)](https://www.luogu.com.cn/problem/P1083)

题目翻译：给定一个整数数组$a$，由$k$次操作，每次给定$l$，$r$使得$a_l\sim a_r$减少$d$，求第几次操作让数组首次出现负数。

> 二分、差分





https://www.luogu.com.cn/problem/P4343

自动刷题机刷题的方式非常简单：首先会瞬间得出题目的正确做法，然后开始写程序。每秒，自动刷题机的代码生成模块会有两种可能的结果：

1.写了 $x$ 行代码  
2.心情不好，删掉了之前写的 $y$ 行代码。（如果 $y$ 大于当前代码长度则相当于全部删除。）

对于一个 OJ，存在某个固定的正整数长度 $n$，一旦自动刷题机在某秒结束时积累了大于等于 $n$ 行的代码，它就会自动提交并 AC 此题，然后新建一个文件（即弃置之前的所有代码）并开始写下一题。SHTSC 在某个 OJ 上跑了一天的自动刷题机，得到了很多条关于写代码的日志信息。他突然发现自己没有记录这个 OJ 的 $n$ 究竟是多少。所幸他通过自己在 OJ 上的 Rank 知道了自动刷题机一共切了 $k$ 道题，希望你计算 $n$ 可能的最小值和最大值。

- 对于 $100\%$ 的数据，保证 $1 \leq l \le 10^5$，$-10^9 \le x_i \le 10^9$。

> 二分 

> 类似leftmost、rightmost
>
> **注意二分的上下边界！！！**





[1870. 准时到达的列车最小时速 - 力扣（LeetCode）](https://leetcode.cn/problems/minimum-speed-to-arrive-on-time/description/)

---

[注意二分上界和check函数及时返回] 

- [2187. 完成旅途的最少时间 - 力扣（LeetCode）](https://leetcode.cn/problems/minimum-time-to-complete-trips/description/)
- [*1500] [F. Final Boss](https://codeforces.com/contest/1985/problem/F)
- [3296. 移山所需的最少秒数 - 力扣（LeetCode）](https://leetcode.cn/problems/minimum-number-of-seconds-to-make-mountain-height-zero/description/)
- [*1100] [E. Building an Aquarium](https://codeforces.com/contest/1873/problem/E)
- [*1100] [E. Cardboard for Pictures](https://codeforces.com/contest/1850/problem/E)
