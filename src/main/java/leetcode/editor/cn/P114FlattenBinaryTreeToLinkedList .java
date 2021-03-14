//给定一个二叉树，原地将它展开为一个单链表。 
//
// 
//
// 例如，给定二叉树 
//
//     1
//   / \
//  2   5
// / \   \
//3   4   6 
//
// 将其展开为： 
//
// 1
// \
//  2
//   \
//    3
//     \
//      4
//       \
//        5
//         \
//          6 
// Related Topics 树 深度优先搜索 
// 👍 603 👎 0

package leetcode.editor.cn;

//java:二叉树展开为链表
class P114FlattenBinaryTreeToLinkedList {
    public static void main(String[] args) {
        Solution solution = new P114FlattenBinaryTreeToLinkedList().new Solution();
    }

    // Definition for a binary tree node.
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
        public void flatten(TreeNode root) {
            recursion(root);
        }

        public TreeNode recursion(TreeNode root) {
            if (root == null) return null;
            if (root.left == null && root.right == null) return root;
            TreeNode left = root.left;
            TreeNode right = root.right;
            if (left == null) {
                return recursion(right);
            } else if (right == null) {
                root.left = null;
                root.right = left;
                return recursion(left);
            } else {
                root.right = left;
                root.left = null;
                TreeNode last = recursion(left);
                last.right = right;
                return recursion(right);
            }
        }
    }
//leetcode submit region end(Prohibit modification and deletion)

}