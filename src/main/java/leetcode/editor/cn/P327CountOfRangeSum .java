//给定一个整数数组 nums，返回区间和在 [lower, upper] 之间的个数，包含 lower 和 upper。 
//区间和 S(i, j) 表示在 nums 中，位置从 i 到 j 的元素之和，包含 i 和 j (i ≤ j)。 
//
// 说明: 
//最直观的算法复杂度是 O(n2) ，请在此基础上优化你的算法。 
//
// 示例: 
//
// 输入: nums = [-2,5,-1], lower = -2, upper = 2,
//输出: 3 
//解释: 3个区间分别是: [0,0], [2,2], [0,2]，它们表示的和分别为: -2, -1, 2。
// 
// Related Topics 排序 树状数组 线段树 二分查找 分治算法 
// 👍 225 👎 0

package leetcode.editor.cn;

//java:区间和的个数
class P327CountOfRangeSum {
    public static void main(String[] args) {
        Solution solution = new P327CountOfRangeSum().new Solution();
    }

    //leetcode submit region begin(Prohibit modification and deletion)
    class Solution {
        public int countRangeSum(int[] nums, int lower, int upper) {
            long s = 0;
            long[] sum = new long[nums.length + 1];
            for (int i = 0; i < nums.length; i++) {
                s += nums[i];
                sum[i + 1] = s;
            }
            return countRangeSumRecursive(sum, lower, upper, 0, nums.length);
        }

        public int countRangeSumRecursive(long[] sum, int lower, int upper, int left, int right) {
            if (left == right) return 0;

            int mid = (left + right) / 2;
            int n1 = countRangeSumRecursive(sum, lower, upper, left, mid);
            int n2 = countRangeSumRecursive(sum, lower, upper, mid + 1, right);
            int ret = n1 + n2;

            int i = left;
            int l = mid + 1, r = mid + 1;
            while (i <= mid) {
                while (l <= right && sum[l] - sum[i] < lower) {
                    l++;
                }
                while (r <= right && sum[r] - sum[i] <= upper) {
                    r++;
                }

                ret += r - l;
                i++;
            }

            long sorted[] = new long[right - left + 1];
            l = left;
            r = mid + 1;
            i = 0;
            while (l <= mid || r <= right) {
                if (l > mid) {
                    sorted[i++] = sum[r++];
                } else if (r > right) {
                    sorted[i++] = sum[l++];
                } else {
                    if (sum[l] < sum[r]) {
                        sorted[i++] = sum[l++];
                    } else {
                        sorted[i++] = sum[r++];
                    }
                }
            }
            for (i = 0; i < sorted.length; i++) {
                sum[left + i] = sorted[i];
            }
            return ret;
        }

        //O(n^2)
//    public int countRangeSum(int[] nums, int lower, int upper) {
//        int cnt = 0;
//        for(int i = 0;i<nums.length;i++){
//            long sum = 0;
//            for(int j=i;j<nums.length;j++){
//                sum+=nums[j];
//                if(sum<=upper&&sum>=lower){
//                    cnt++;
//                }
//            }
//        }
//        return cnt;
//    }
    }
//leetcode submit region end(Prohibit modification and deletion)

}