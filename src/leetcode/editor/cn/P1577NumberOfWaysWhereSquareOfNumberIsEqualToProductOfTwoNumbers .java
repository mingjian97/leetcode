//给你两个整数数组 nums1 和 nums2 ，请你返回根据以下规则形成的三元组的数目（类型 1 和类型 2 ）： 
//
// 
// 类型 1：三元组 (i, j, k) ，如果 nums1[i]2 == nums2[j] * nums2[k] 其中 0 <= i < nums1.len
//gth 且 0 <= j < k < nums2.length 
// 类型 2：三元组 (i, j, k) ，如果 nums2[i]2 == nums1[j] * nums1[k] 其中 0 <= i < nums2.len
//gth 且 0 <= j < k < nums1.length 
// 
//
// 
//
// 示例 1： 
//
// 输入：nums1 = [7,4], nums2 = [5,2,8,9]
//输出：1
//解释：类型 1：(1,1,2), nums1[1]^2 = nums2[1] * nums2[2] (4^2 = 2 * 8) 
//
// 示例 2： 
//
// 输入：nums1 = [1,1], nums2 = [1,1,1]
//输出：9
//解释：所有三元组都符合题目要求，因为 1^2 = 1 * 1
//类型 1：(0,0,1), (0,0,2), (0,1,2), (1,0,1), (1,0,2), (1,1,2), nums1[i]^2 = nums2[
//j] * nums2[k]
//类型 2：(0,0,1), (1,0,1), (2,0,1), nums2[i]^2 = nums1[j] * nums1[k]
// 
//
// 示例 3： 
//
// 输入：nums1 = [7,7,8,3], nums2 = [1,2,9,7]
//输出：2
//解释：有两个符合题目要求的三元组
//类型 1：(3,0,2), nums1[3]^2 = nums2[0] * nums2[2]
//类型 2：(3,0,1), nums2[3]^2 = nums1[0] * nums1[1]
// 
//
// 示例 4： 
//
// 输入：nums1 = [4,7,9,11,23], nums2 = [3,5,1024,12,18]
//输出：0
//解释：不存在符合题目要求的三元组
// 
//
// 
//
// 提示： 
//
// 
// 1 <= nums1.length, nums2.length <= 1000 
// 1 <= nums1[i], nums2[i] <= 10^5 
// 
// Related Topics 哈希表 数学 
// 👍 6 👎 0

package leetcode.editor.cn;

import java.util.Arrays;

//java:数的平方等于两数乘积的方法数
class P1577NumberOfWaysWhereSquareOfNumberIsEqualToProductOfTwoNumbers {
    public static void main(String[] args) {
        Solution solution = new P1577NumberOfWaysWhereSquareOfNumberIsEqualToProductOfTwoNumbers().new Solution();
    }

    //leetcode submit region begin(Prohibit modification and deletion)
    class Solution {
        public int numTriplets(int[] nums1, int[] nums2) {
            Arrays.sort(nums1);
            Arrays.sort(nums2);
            return getTotal(nums1, nums2) + getTotal(nums2, nums1);
        }

        public int getTotal(int[] nums1, int[] nums2) {
            int res = 0;
            for (int i = 0; i < nums1.length; i++) {
                long square = (long)nums1[i] * nums1[i];
                int left = 0, right = nums2.length - 1;
                //nums2中最小数的平方都比square大，不可能存在解，跳过
                if ((long) nums2[left] * nums2[left] > square) continue;
                //nums2中最大数的平方都比square小，不可能存在解，跳过,后面也都不用判断了
                if ((long) nums2[right] * nums2[right] < square) break;

                while (left < right) {
                    long multi = (long)nums2[left] * nums2[right];
                    if (multi < square) left++;
                    else if (multi > square) right--;
                    else {
                        //nums2[left...right]中的数完全相同
                        if(nums2[left]==nums2[right]){
                            res+=(right-left+1)*(right-left)/2;
                            break;
                        }else{
                            //nums2[left...m),nums2(n,right] 两个子数组里面的数分别相等，求组合数
                            int m=left+1,n=right-1;
                            while(nums2[m]==nums2[left]){
                                m++;
                            }
                            while(nums2[n]==nums2[right]){
                                n--;
                            }
                            res+=(m-left)*(right-n);
                            left=m;
                            right=n;
                        }
                    }
                }
            }
            return res;
        }
    }
//leetcode submit region end(Prohibit modification and deletion)

}