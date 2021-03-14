#### 解题思路

- 用Map存储第一个数组的第i个元素与第j个元素的乘积
- 遍历第二个数组的每个元素的平方，判断Map中是否存在
- 运行时间154ms，思路有待改进

- 存在的坑：Java编译，Int类型数字会溢出，需要先将Int转为Long
    - 测试用例： long tmp = 43024 * 99908; tmp的结果是3474496
    - 换个写法： long tmp = (long)43024 * (long)99908; tmp结果是4298441792
    - 只有后一个写法才能得到正确结果。（周赛的时候就是卡在这里过不去...）

#### 代码

~~~ java
class Solution {
    public int numTriplets(int[] nums1, int[] nums2) {
        return function(nums1,nums2) + function(nums2,nums1);
    }
    public int function(int[] nums1, int[] nums2){
        int res = 0;
        if(nums1.length <2){
            return res;
        }
        Map<Long,Integer> map = new HashMap<>();
        for(int i=0; i<nums1.length-1; i++){
            for(int j=i+1; j<nums1.length; j++){
                long tmp = (long)nums1[i] * (long)nums1[j];
                map.put(tmp, map.getOrDefault(tmp,0)+1);
            }
        }
        for(int i=0; i<nums2.length; i++){
            long tmp = (long)nums2[i] * (long)nums2[i];
            if(map.containsKey(tmp)){
                res += map.get(tmp);
            }
        }
        return res;
    }
}
~~~