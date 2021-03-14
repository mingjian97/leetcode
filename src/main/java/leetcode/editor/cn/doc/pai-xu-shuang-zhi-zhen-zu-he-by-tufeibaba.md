耗时8ms。 1）先排序，以便使用双指针，来进行目标查找。 2）在双指针查找目标过程中，

- nums2[m] * nums2[n] < target，左指针右移：m++
- nums2[m] * nums2[n] > target，右指针左移，n--
- nums2[m] * nums2[n] == target呢？

情况比较复杂。由于数组可能存在重复元素，所以，在处理重复元素时，就需要考虑指针该如何移动了。重复元素分两种：

1. nums2[m..n]，即左右指针范围内的元素完全相同，求组合数即可。即C(n - m + 1，2）= (n - m + 1) * (n - m) / 2。
2. nums2[m..j)、nums2(k..n]。这两个子数组里的元素，完全相等。可能的结果，就是分别从两个数组里面，抽一个数，组合数为：(j - m) * (n - k)。这时候，nums2[j..k]
   之间可能还存在解。所以，左指针移到j处，右指针移到k处。

```
class Solution {
    public int numTriplets(int[] nums1, int[] nums2) {
        Arrays.sort(nums1);
        Arrays.sort(nums2);
        return total(nums1,nums2) + total(nums2,nums1);
    }
    
    private int total(int[] nums1,int[] nums2){
        int res = 0;
        for(int i = 0;i < nums1.length;i++){
            long sum = (long)nums1[i] * nums1[i];
            int m = 0,n = nums2.length - 1;
            //nums2中最小的数的平方，都比sum大，所以，不可能存在解，跳过
            if((long)nums2[m] * nums2[m] > sum) continue;
            //nums2中最大的数的平方，都比sum小，所以，不可能存在解，跳过
            //并且，nums1[i + 1] >= nums1[i]，所以，直接break即可
            if((long)nums2[n] * nums2[n] < sum) break;
            while(m < n){
                if((long)nums2[m] * nums2[n] < sum) m++;
                else if((long)nums2[m] * nums2[n] > sum) n--;
                else {
                    //nums2[m..n]，这里面的数完全相同
                    //从里面抽2个数，求组合数即可。
                    //组合数就是(n - m + 1) * (n - m) / 2
                    if(nums2[m] == nums2[n]){
                        int total = (n - m + 1) * (n - m) / 2;
                        res += total;
                        //直接break即可。
                        break;
                    }else{
                        //nums2[m..j)、nums2(k..n]，这两个子数组里的数都相同
                        //从这两个子数组分别抽1个数出来，求组合数即可。
                        //组合数就是(j - m) * (n - k)
                        int j = m + 1,k = n - 1;
                        while(nums2[j] == nums2[m]){
                            j++;
                        }
                        while(nums2[k] == nums2[n]){
                            k--;
                        }
                        res += (j - m) * (n - k);
                        m = j;
                        n = k;
                        //不break,nums2[j..k]之间可能还存在解
                        //比如sum为24时，[3,3,4,5,6,8,8],经过上述操作,剩余子数组[4,5,6]，
                        //即nums2[j..k]，仍存在解(4,6)
                    }
                }
            }
        }
        return res;
    }
}
```
