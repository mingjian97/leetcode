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

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;

//java:最佳直线
class P面试题 16.14BestLineLcci{
    public static void main(String[] args){
        Solution solution = new P面试题 16.14BestLineLcci().new Solution();
    }
//leetcode submit region begin(Prohibit modification and deletion)
class Solution {
    //直线一般式 ax+by+c=0
    // a=y2-y1;b=x1-x2;c=x2y1-x1y2
    public int[] bestLine(int[][] points) {
        HashMap<String,Integer> map = new HashMap<>();
        HashMap<String,int[]> record = new HashMap<>();
        int max=0;
        String maxKey="";
        int[] res = new int[2];
        for(int i=0;i<points.length-1;i++){
            for(int j=i+1;j< points.length;j++){
                int a = points[j][1] - points[i][1];
                int b = points[i][0] - points[j][0];
                int c = points[j][0] * points[i][1] - points[i][0] * points[j][1];
                int g = gcd(gcd(a,b),c);
                a/=g;
                b/=g;
                c/=g;

                String key = a+"-"+b+"-"+c;
                int count = map.getOrDefault(key,0)+1;
                map.put(key,count);
                if(count == 1) record.put(key,new int[]{i,j});
                if(count>max){
                    max=count;
                    maxKey=key;
                    res = record.get(key);
                }else if(count==max){
                    int[] t1=record.get(maxKey);
                    int[] t2=record.get(key);
                    if(t1[0]>t2[0]||t1[0]==t2[0]&&t1[1]>t2[1]){
                        maxKey=key;
                        res=t2;
                    }
                }

            }
        }
        return res;

    }
    public int gcd(int a,int b){
        return b==0?a:gcd(b,a%b);
    }
}
//leetcode submit region end(Prohibit modification and deletion)

}