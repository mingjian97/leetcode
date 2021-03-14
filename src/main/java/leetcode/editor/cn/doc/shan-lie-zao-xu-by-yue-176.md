贪心的想法,我们从前往后的比较每一个字符,如果发现A[i][j]<A[i-1][j]
,那么就意味着这一列是必须删除的，但是这样做有一个问题：就是如果之前我们已经确定了这两列的大小关系，那么这个时候即使小于，也不用删除，比如acd,adc,对于这两个字符串来说，当我们比较前面两个字符时就已经确定大小关系了，那么当比较第三个字符时，即使d>
c，也是不需要删除的。 所以针对多个字符串，我们开一个数组vis[n],vis[i]表示第i行和第i-1行是否已经确定大小关系了。 思路：
从第0列开始比较，如果发现这一列中有不确定大小关系并且是小于前面行的，就意味着是必须删除的，这个时候就不需要更新vis，否则我们就跟新vis；

```java
class Solution {
    public int minDeletionSize(String[] A) {
        int n = A.length;
        int m = A[0].length();

        int[] vis = new int[n];
        int ans = 0;
        for (int i = 0; i < m; i++) {
            boolean isDelete=false;
            for (int j = 1; j < n; j++) {
                if(vis[j]==1)continue;
                if(A[j].charAt(i)<A[j-1].charAt(i)){
                    isDelete = true;
                    break;
                }
            }
            if(isDelete)ans++;
            else{
                for (int j = 1; j < n; j++) {
                    if(A[j].charAt(i)>A[j-1].charAt(i)){
                        vis[j]=1;
                    }
                }
            }
        }
        return ans;
    }
}
```
