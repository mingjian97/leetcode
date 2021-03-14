//给你一个以字符串形式表述的 布尔表达式（boolean） expression，返回该式的运算结果。 
//
// 有效的表达式需遵循以下约定： 
//
// 
// "t"，运算结果为 True 
// "f"，运算结果为 False 
// "!(expr)"，运算过程为对内部表达式 expr 进行逻辑 非的运算（NOT） 
// "&(expr1,expr2,...)"，运算过程为对 2 个或以上内部表达式 expr1, expr2, ... 进行逻辑 与的运算（AND） 
// "|(expr1,expr2,...)"，运算过程为对 2 个或以上内部表达式 expr1, expr2, ... 进行逻辑 或的运算（OR） 
// 
//
// 
//
// 示例 1： 
//
// 输入：expression = "!(f)"
//输出：true
// 
//
// 示例 2： 
//
// 输入：expression = "|(f,t)"
//输出：true
// 
//
// 示例 3： 
//
// 输入：expression = "&(t,f)"
//输出：false
// 
//
// 示例 4： 
//
// 输入：expression = "|(&(t,f,t),!(t))"
//输出：false
// 
//
// 
//
// 提示： 
//
// 
// 1 <= expression.length <= 20000 
// expression[i] 由 {'(', ')', '&', '|', '!', 't', 'f', ','} 中的字符组成。 
// expression 是以上述形式给出的有效表达式，表示一个布尔值。 
// 
// Related Topics 字符串 
// 👍 44 👎 0

package leetcode.editor.cn;

import java.util.ArrayDeque;
import java.util.Deque;

//java:解析布尔表达式
class P1106ParsingABooleanExpression {
    public static void main(String[] args) {
        Solution solution = new P1106ParsingABooleanExpression().new Solution();
        String exp = "|(&(t,f,t),!(t))";
        System.out.println(solution.parseBoolExpr(exp));
    }

    //leetcode submit region begin(Prohibit modification and deletion)
    class Solution {
        public boolean parseBoolExpr(String expression) {
            char[] chars = expression.toCharArray();
            Deque<Character> deque = new ArrayDeque<>();
            Deque<Character> que = new ArrayDeque<>();
            int i = 0;
            while (i < chars.length) {
                char c = chars[i];
                if (c == ')') {
                    char s = deque.pollLast();
                    boolean res;
                    while (s != '(') {
                        que.add(s);
                        s = deque.pollLast();
                    }
                    s = deque.pollLast();
                    if (s == '!') {
                        res = false;
                        char tmp = que.pollLast();
                        if (tmp == 't') {
                            res = false;
                        } else if (tmp == 'f') {
                            res = true;
                        }
                        deque.addLast(res ? 't' : 'f');
                    } else if (s == '&') {
                        res = true;
                        while (!que.isEmpty()) {
                            res = (que.pollLast() == 't' ? true : false) && res;
                        }
                        deque.addLast(res ? 't' : 'f');
                    } else if (s == '|') {
                        res = false;
                        while (!que.isEmpty()) {
                            boolean re = que.pollLast() == 't' ? true : false;
                            if (res || re) {
                                res = true;
                            }
                        }
                        deque.addLast(res ? 't' : 'f');
                    } else {
                        deque.addLast(s);
                    }
                } else if (c != ',') {
                    deque.offerLast(c);
                }
                i++;
            }
            return deque.pollLast() == 't' ? true : false;
        }
    }
//leetcode submit region end(Prohibit modification and deletion)

}