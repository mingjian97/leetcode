//设想有个机器人坐在一个网格的左上角，网格 r 行 c 列。机器人只能向下或向右移动，但不能走到一些被禁止的网格（有障碍物）。设计一种算法，寻找机器人从左上角
//移动到右下角的路径。 
//
// 
//
// 网格中的障碍物和空位置分别用 1 和 0 来表示。 
//
// 返回一条可行的路径，路径由经过的网格的行号和列号组成。左上角为 0 行 0 列。如果没有可行的路径，返回空数组。 
//
// 示例 1: 
//
// 输入:
//[
//  [0,0,0],
//  [0,1,0],
//  [0,0,0]
//]
//输出: [[0,0],[0,1],[0,2],[1,2],[2,2]]
//解释: 
//输入中标粗的位置即为输出表示的路径，即
//0行0列（左上角） -> 0行1列 -> 0行2列 -> 1行2列 -> 2行2列（右下角） 
//
// 说明：r 和 c 的值均不超过 100。 
// Related Topics 动态规划 
// 👍 34 👎 0

package leetcode.editor.cn;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

//java:迷路的机器人
class P面试题0802RobotInAGridLcci {
    public static void main(String[] args) {
        Solution solution = new P面试题0802RobotInAGridLcci().new Solution();
    }

    //leetcode submit region begin(Prohibit modification and deletion)
    class Solution {

        int m, n;
        int[][] grid;

        public List<List<Integer>> pathWithObstacles(int[][] obstacleGrid) {
            this.m = obstacleGrid.length;
            this.n = obstacleGrid[0].length;
            this.grid = obstacleGrid;
            List<List<Integer>> res = new ArrayList<>();
            dfs(0, 0, new boolean[m][n], res);
            return res;
        }

        public boolean dfs(int row, int col, boolean[][] visited, List<List<Integer>> res) {
            if (row >= m || col >= n || visited[row][col] || grid[row][col] == 1) {
                return false;
            }

            res.add(Arrays.asList(row, col));
            if (row == m - 1 && col == n - 1) return true;
            visited[row][col] = true;
            if (dfs(row + 1, col, visited, res) || dfs(row, col + 1, visited, res)) {
                return true;
            }

            res.remove(res.size() - 1);
            return false;
        }
    }
//leetcode submit region end(Prohibit modification and deletion)

}