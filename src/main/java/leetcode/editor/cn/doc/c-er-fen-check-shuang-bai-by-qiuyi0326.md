如果mid天满足，那么mid+1天也满足，满足单调性，可以二分check 对于当前天数mid，贪心计算有多少个连续的小于等于mid的k天，假设有cnt个连续k天，判断cnt是否大于等于m即可

```
class Solution {
public:
    bool check(vector<int>&a,int m,int k,int mid){
        int n=a.size();
        int cnt=0;
        int len=0;
        for(int i=0;i<n;i++){
            if(a[i]<=mid){
                len++;
                if(len==k){
                    cnt++;
                    len-=k;
                }
            }else{
                len=0;
            }
        }
        return cnt>=m;
    }
    int minDays(vector<int>& a, int m, int k) {
        int n=a.size();
        int l=1,r=1e9;
        int ans=-1;
        while(l<=r){
            int mid=(l+r)/2;
            if(check(a,m,k,mid)){
                ans=mid,r=mid-1;
            }else{
                l=mid+1;
            }
        }
        return ans;
    }
};
```
