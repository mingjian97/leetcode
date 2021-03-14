//给定一个非空二叉树，返回其最大路径和。 
//
// 本题中，路径被定义为一条从树中任意节点出发，沿父节点-子节点连接，达到任意节点的序列。该路径至少包含一个节点，且不一定经过根节点。 
//
// 
//
// 示例 1： 
//
// 输入：[1,2,3]
//
//       1
//      / \
//     2   3
//
//输出：6
// 
//
// 示例 2： 
//
// 输入：[-10,9,20,null,null,15,7]
//
//   -10
//   / \
//  9  20
//    /  \
//   15   7
//
//输出：42 
// Related Topics 树 深度优先搜索 
// 👍 758 👎 0

package leetcode.editor.cn;

//java:二叉树中的最大路径和
class P124BinaryTreeMaximumPathSum {
    public static void main(String[] args) {
        Solution solution = new P124BinaryTreeMaximumPathSum().new Solution();
    }

    //Definition for a binary tree node.
    public class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;

        TreeNode() {
        }

        TreeNode(int val) {
            this.val = val;
        }

        TreeNode(int val, TreeNode left, TreeNode right) {
            this.val = val;
            this.left = left;
            this.right = right;
        }
    }

//leetcode submit region begin(Prohibit modification and deletion)

    class Solution {
        int max = Integer.MIN_VALUE;

        public int maxPathSum(TreeNode root) {
            if (root == null) return 0;
            recursion(root);
            return max;
        }

        public int recursion(TreeNode root) {
            if (root == null) return 0;
            if (root.left == null && root.right == null) {
                if (root.val > max)
                    max = root.val;
                return root.val;
            }
            int left = recursion(root.left);
            int right = recursion(root.right);
            int curValue = left > right ? left : right;
            curValue = curValue > 0 ? (root.val + curValue) : root.val;
            int curMax = root.val + (left > 0 ? left : 0) + (right > 0 ? right : 0);
            if (curMax > max) max = curMax;
            return curValue;

        }
    }
//leetcode submit region end(Prohibit modification and deletion)

}