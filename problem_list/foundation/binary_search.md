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

[33. 搜索旋转排序数组 - 力扣（LeetCode）](https://leetcode.cn/problems/search-in-rotated-sorted-array/description/?envType=study-plan-v2&envId=top-100-liked)

[74. 搜索二维矩阵 - 力扣（LeetCode）](https://leetcode.cn/problems/search-a-2d-matrix/description/?envType=study-plan-v2&envId=top-100-liked)

[P1102 A-B 数对 - 洛谷](https://www.luogu.com.cn/problem/P1102)

[P1678 烦恼的高考志愿 - 洛谷](https://www.luogu.com.cn/problem/P1678)

# 3. 小数二分

https://www.luogu.com.cn/problem/P1024

# 4. 二分答案



[P1873 [COCI 2011/2012 #5] EKO / 砍树 - 洛谷](https://www.luogu.com.cn/problem/P1873)



[P2440 木材加工 - 洛谷](https://www.luogu.com.cn/problem/P2440)



[P2678 [NOIP2015 提高组] 跳石头 - 洛谷](https://www.luogu.com.cn/problem/P2678)



[P3853 [TJOI2007] 路标设置 - 洛谷](https://www.luogu.com.cn/problem/P3853)



[P1182 数列分段 Section II - 洛谷 ](https://www.luogu.com.cn/problem/P1182) 黄

[1011. 在 D 天内送达包裹的能力 - 力扣（LeetCode）](https://leetcode.cn/problems/capacity-to-ship-packages-within-d-days/description/) 1725



[P3743 kotori的设备 - 洛谷](https://www.luogu.com.cn/problem/P3743)



[P1902 刺杀大使 - 洛谷](https://www.luogu.com.cn/problem/P1902) 绿



[P1314 [NOIP 2011 提高组] 聪明的质监员 - 洛谷](https://www.luogu.com.cn/problem/P1314) 绿



[P1083 [NOIP2012 提高组] 借教室 - 洛谷](https://www.luogu.com.cn/problem/P1083) 黄



https://www.luogu.com.cn/problem/P4343

> **注意二分的上下边界**

[1870. 准时到达的列车最小时速 - 力扣（LeetCode）](https://leetcode.cn/problems/minimum-speed-to-arrive-on-time/description/)

---

[注意二分上界和check函数及时返回] 

- [2187. 完成旅途的最少时间 - 力扣（LeetCode）](https://leetcode.cn/problems/minimum-time-to-complete-trips/description/)
- [*1500] [F. Final Boss](https://codeforces.com/contest/1985/problem/F)
- [3296. 移山所需的最少秒数 - 力扣（LeetCode）](https://leetcode.cn/problems/minimum-number-of-seconds-to-make-mountain-height-zero/description/)
- [*1100] [E. Building an Aquarium](https://codeforces.com/contest/1873/problem/E)
- [*1100] [E. Cardboard for Pictures](https://codeforces.com/contest/1850/problem/E)

[1760. 袋子里最少数目的球 - 力扣（LeetCode）](https://leetcode.cn/problems/minimum-limit-of-balls-in-a-bag/description/) 1940

[1552. 两球之间的磁力 - 力扣（LeetCode）](https://leetcode.cn/problems/magnetic-force-between-two-balls/description/) 1920
