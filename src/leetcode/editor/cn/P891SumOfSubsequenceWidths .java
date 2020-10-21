//给定一个整数数组 A ，考虑 A 的所有非空子序列。 
//
// 对于任意序列 S ，设 S 的宽度是 S 的最大元素和最小元素的差。 
//
// 返回 A 的所有子序列的宽度之和。 
//
// 由于答案可能非常大，请返回答案模 10^9+7。 
//
// 
//
// 示例： 
//
// 输入：[2,1,3]
//输出：6
//解释：
//子序列为 [1]，[2]，[3]，[2,1]，[2,3]，[1,3]，[2,1,3] 。
//相应的宽度是 0，0，0，1，1，2，2 。
//这些宽度之和是 6 。
// 
//
// 
//
// 提示： 
//
// 
// 1 <= A.length <= 20000 
// 1 <= A[i] <= 20000 
// 
// Related Topics 数组 数学 
// 👍 39 👎 0

package leetcode.editor.cn;

//java:子序列宽度之和
class P891SumOfSubsequenceWidths {
    public static void main(String[] args) {
        Solution solution = new P891SumOfSubsequenceWidths().new Solution();
    }

    //leetcode submit region begin(Prohibit modification and deletion)
    class Solution {
        int mod = 1000000007;
        int res = 0;
        public int sumSubseqWidths(int[] A) {
            if(A.length==1)return 0;
            boolean[] visited = new boolean[A.length];

            func(visited,1);

            return res;
        }
        public void func(boolean[]  visited,int k){
            if(k==visited.length+1)return;


        }
    }
//leetcode submit region end(Prohibit modification and deletion)

}