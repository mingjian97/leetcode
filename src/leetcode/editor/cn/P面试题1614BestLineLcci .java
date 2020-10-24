//给定一个二维平面及平面上的 N 个点列表Points，其中第i个点的坐标为Points[i]=[Xi,Yi]。请找出一条直线，其通过的点的数目最多。 
// 设穿过最多点的直线所穿过的全部点编号从小到大排序的列表为S，你仅需返回[S[0],S[1]]作为答案，若有多条直线穿过了相同数量的点，则选择S[0]值较小
//的直线返回，S[0]相同则选择S[1]值较小的直线返回。 
// 示例： 
// 输入： [[0,0],[1,1],[1,0],[2,0]]
//输出： [0,2]
//解释： 所求直线穿过的3个点的编号为[0,2,3]
// 
// 提示： 
// 
// 2 <= len(Points) <= 300 
// len(Points[i]) = 2 
// 
// Related Topics 几何 哈希表 
// 👍 8 👎 0

package leetcode.editor.cn;

import java.util.*;

//java:最佳直线
class P面试题1614BestLineLcci{
    public static void main(String[] args){
        Solution solution = new P面试题1614BestLineLcci().new Solution();
        int[][] points={{0,0},{1,1},{1,0},{2,0}};
        int[] res=solution.bestLine(points);
        for(int i:res){
            System.out.print(i+" ");
        }
    }
//leetcode submit region begin(Prohibit modification and deletion)
class Solution {
    public int[] bestLine(int[][] points) {
        HashMap<String, List<Integer>> map = new HashMap<>();
        StringBuilder xsb = new StringBuilder();
        StringBuilder ysb = new StringBuilder();
        xsb.append("x");
        ysb.append("y");
        for(int i=0;i<points.length;i++){
            int x = points[i][0];
            int y = points[i][1];
            xsb.append(x);
            ysb.append(y);

            if(map.containsKey(xsb.toString())){
                map.get(xsb.toString()).add(i);
            }else{
                map.put(xsb.toString(), new ArrayList<Integer>());
                map.get(xsb.toString()).add(i);
            }

            if(map.containsKey(ysb.toString())){
                map.get(ysb.toString()).add(i);
            }else{
                map.put(ysb.toString(), new ArrayList<Integer>());
                map.get(ysb.toString()).add(i);
            }

            xsb.replace(0,xsb.length(),"x");
            ysb.replace(0,ysb.length(),"y");
        }
        List<List<Integer>> lists = new ArrayList<>();
        int max=0;
        for(List<Integer> li : map.values()){
            if(li.size()==1){
                continue;
            }
            if(li.size()>max){
                max=li.size();
                lists.clear();
                lists.add(li);
            }else if(li.size()==max){
                lists.add(li);
            }
        }
        Collections.sort(lists, (o1, o2)->{
            if(o1.get(0)==o2.get(0))
                return o1.get(1)-o2.get(1);
            return o1.get(0)-o2.get(0);
        });
        return new int[]{lists.get(0).get(0),lists.get(0).get(1)};
    }
}
//leetcode submit region end(Prohibit modification and deletion)

}