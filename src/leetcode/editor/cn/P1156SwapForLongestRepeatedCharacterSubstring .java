//如果字符串中的所有字符都相同，那么这个字符串是单字符重复的字符串。 
//
// 给你一个字符串 text，你只能交换其中两个字符一次或者什么都不做，然后得到一些单字符重复的子串。返回其中最长的子串的长度。 
//
// 
//
// 示例 1： 
//
// 输入：text = "ababa"
//输出：3
// 
//
// 示例 2： 
//
// 输入：text = "aaabaaa"
//输出：6
// 
//
// 示例 3： 
//
// 输入：text = "aaabbaaa"
//输出：4
// 
//
// 示例 4： 
//
// 输入：text = "aaaaa"
//输出：5
// 
//
// 示例 5： 
//
// 输入：text = "abcdef"
//输出：1
// 
//
// 
//
// 提示： 
//
// 
// 1 <= text.length <= 20000 
// text 仅由小写英文字母组成。 
// 
// Related Topics 字符串 
// 👍 54 👎 0

package leetcode.editor.cn;

//java:单字符重复子串的最大长度
class P1156SwapForLongestRepeatedCharacterSubstring {
    public static void main(String[] args) {
        Solution solution = new P1156SwapForLongestRepeatedCharacterSubstring().new Solution();
    }

    //leetcode submit region begin(Prohibit modification and deletion)
    class Solution {
        public int maxRepOpt1(String text) {
            int len = text.length();
            int[] ch_count = new int[26];
            for(int i = 0;i<len;i++){
                ch_count[text.charAt(i)-'a']++;
            }

            char last_ch = text.charAt(0);
            int count = 1, res = 1;
            for(int i =1;i<len;i++){
                if(last_ch!=text.charAt(i)){
                    int tmp_idx = i+1;
                    while(tmp_idx<len&&text.charAt(tmp_idx)==last_ch){
                        tmp_idx++;
                        count++;
                    }
                    if(ch_count[last_ch-'a']>count){
                        count++;
                    }
                    res = res>count?res:count;
                    count = 1;
                    last_ch=text.charAt(i);
                }else{
                    count++;
                }
            }
            if(count>1&&ch_count[last_ch-'a']>count)
                count++;

            return res>count?res:count;
        }
    }
//leetcode submit region end(Prohibit modification and deletion)

}