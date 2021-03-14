//给定两个单词 word1 和 word2，找到使得 word1 和 word2 相同所需的最小步数，每步可以删除任意一个字符串中的一个字符。 
//
// 
//
// 示例： 
//
// 输入: "sea", "eat"
//输出: 2
//解释: 第一步将"sea"变为"ea"，第二步将"eat"变为"ea"
// 
//
// 
//
// 提示： 
//
// 
// 给定单词的长度不超过500。 
// 给定单词中的字符只含有小写字母。 
// 
// Related Topics 字符串 
// 👍 155 👎 0

package leetcode.editor.cn;

//java:两个字符串的删除操作
class P583DeleteOperationForTwoStrings {
    public static void main(String[] args) {
        Solution solution = new P583DeleteOperationForTwoStrings().new Solution();
    }

    //leetcode submit region begin(Prohibit modification and deletion)
    class Solution {
        public int minDistance(String word1, String word2) {
//            return word1.length()+word2.length()-2*lcs(word1,word2,word1.length(),word2.length());
            int len1 = word1.length();
            int len2 = word2.length();
            int[][] memo = new int[len1 + 1][len2 + 1];
            return len1 + len2 - 2 * lcs(word1, word2, len1, len2, memo);
        }

        public int lcs(String word1, String word2, int m, int n, int[][] memo) {
            if (m == 0 || n == 0) return 0;
            if (memo[m][n] > 0) return memo[m][n];
            if (word1.charAt(m - 1) == word2.charAt(n - 1))
                memo[m][n] = 1 + lcs(word1, word2, m - 1, n - 1, memo);
            else
                memo[m][n] = Math.max(lcs(word1, word2, m - 1, n, memo), lcs(word1, word2, m, n - 1, memo));
            return memo[m][n];
        }
        //超时
//        public int lcs(String word1,String word2,int m,int n){
//            if(m==0||n==0)return 0;
//            if(word1.charAt(m-1)==word2.charAt(n-1))return 1+lcs(word1,word2,m-1,n-1);
//            return Math.max(lcs(word1,word2,m-1,n),lcs(word1,word2,m,n-1));
//        }
    }
//leetcode submit region end(Prohibit modification and deletion)

}