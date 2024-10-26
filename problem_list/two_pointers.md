[2024. 考试的最大困扰度 - 力扣（LeetCode）](https://leetcode.cn/problems/maximize-the-confusion-of-an-exam/description/) 维护 某个元素的个数不超过k的 最大区间

# 滑动窗口

解决子数组和子串问题

## 不定长滑窗

### 经典例题

[209. 长度最小的子数组 - 力扣（LeetCode）](https://leetcode.cn/problems/minimum-size-subarray-sum/description/) 

[3. 无重复字符的最长子串 - 力扣（LeetCode）](https://leetcode.cn/problems/longest-substring-without-repeating-characters/description/) 

[76. 最小覆盖子串 - 力扣（LeetCode）](https://leetcode.cn/problems/minimum-window-substring/) 

[134. 加油站 - 力扣（LeetCode）](https://leetcode.cn/problems/gas-station/submissions/) 判断是否有长度>=n的子数组满足其前缀和都>=0

[1234. 替换子串得到平衡字符串 - 力扣（LeetCode）](https://leetcode.cn/problems/replace-the-substring-for-balanced-string/) 转化为[76.最小覆盖子串]，最小子串覆盖所有超过n/4的部分

[992. K 个不同整数的子数组 - 力扣（LeetCode） ](https://leetcode.cn/problems/subarrays-with-k-different-integers/description/) 求不同数字数等于k 转化为 不同数字数<=k 减去 不同数字数 <= k - 1

待做	[395. 至少有 K 个重复字符的最长子串 - 力扣（LeetCode）](https://leetcode.cn/problems/longest-substring-with-at-least-k-repeating-characters/description/)

---

[学习滑窗思想]  [2962. 统计最大元素出现至少 K 次的子数组 - 力扣（LeetCode）](https://leetcode.cn/problems/count-subarrays-where-max-element-appears-at-least-k-times/description/)

[76题的计数版本]  [3298. 统计重新排列后包含另一个字符串的子字符串数目 II - 力扣（LeetCode）](https://leetcode.cn/problems/count-substrings-that-can-be-rearranged-to-contain-a-string-ii/description/)

### 求最长/最大

移动一次右端点，缩减左端点至区间合法之后记录答案

 [3090. 每个字符最多出现两次的最长子字符串](https://leetcode.cn/problems/maximum-length-substring-with-two-occurrences/)  类似：3. 无重复字符的最长子串

[1493. 删掉一个元素以后全为 1 的最长子数组 - 力扣（LeetCode）](https://leetcode.cn/problems/longest-subarray-of-1s-after-deleting-one-element/)  包含<=1个0的最长子串

[1208. 尽可能使字符串相等 - 力扣（LeetCode）](https://leetcode.cn/problems/get-equal-substrings-within-budget/description/)  总和不超过max的最长子数组

[2730. 找到最长的半重复子字符串 - 力扣（LeetCode）](https://leetcode.cn/problems/find-the-longest-semi-repetitive-substring/description/) 

# 双指针

[922. 按奇偶排序数组 II - 力扣（LeetCode）](https://leetcode.cn/problems/sort-array-by-parity-ii/) 用两个奇偶指针操作
