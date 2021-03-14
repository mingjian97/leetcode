### 解题思路

思路就递归回溯 + 动态规划， 计算出左边和右边得最大值，然后做判断，左边如果小于0，加上不会比根节点大，右边也一样， 每次都把当前节点作为路径得一部分时能产生得最大得路径加权返回上一层，上一层也做类似判定
然后再把当前节点得最大路径长度计算一下，更新到全局变量得max里

### 代码

```java
/**
 * Definition for a binary tree node.
 * public class TreeNode {
 *     int val;
 *     TreeNode left;
 *     TreeNode right;
 *     TreeNode(int x) { val = x; }
 * }
 */
class Solution {
    int max = Integer.MIN_VALUE;

    public int maxPathSum(TreeNode root) {
        if (root == null) return 0;
        travelNode(root);
        return max;
    }

    private int travelNode(TreeNode root) {
        if (root.left == null && root.right == null) {
            if (root.val > max) max = root.val;
            return root.val;
        }
        int leftVal = 0, rightVal = 0;
        if (root.left != null) {
            leftVal = travelNode(root.left);
        }
        if (root.right != null) {
            rightVal = travelNode(root.right);
        }
        int curVal = leftVal > rightVal ? leftVal : rightVal;
        curVal = curVal > 0 ? root.val + curVal : root.val;
        int curMax = root.val + (leftVal > 0 ? leftVal : 0) + (rightVal > 0 ? rightVal : 0);
        if (curMax > max) max = curMax;
        return curVal;
    }
}
```