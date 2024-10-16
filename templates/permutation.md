# `next_permutation`

```java
    /* 下一个排列 */
    public static boolean nextPermutation(int a[]){
        int n = a.length, i = n - 2;
        while(i >= 0 && a[i] > a[i + 1]) i --;
        if(i < 0) return false;
        int k = i + 1;
        while(k < n && a[k] > a[i]) k ++;
        {int t = a[i]; a[i] = a[k - 1]; a[k - 1] = t;}// swap(a[i], a[k - 1])
        Arrays.sort(a, i + 1, n);
        return true;
    }
```