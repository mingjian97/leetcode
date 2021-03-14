[直线的一般式方程](https://baike.baidu.com/item/%E7%9B%B4%E7%BA%BF%E7%9A%84%E4%B8%80%E8%88%AC%E5%BC%8F%E6%96%B9%E7%A8%8B/11052424?fr=aladdin)

直线的一般式方程:Ax + By + C = 0。

直线一般式方程适用于所有的二维空间直线。而其他直线方程，如点斜式、两点式，要么不能支持所有情况下的直线（比如跟坐标轴垂直或者平行），要么不能支持所有情况下的点（比如x坐标相等，或者y坐标相等）。所以一般式方程在用计算机处理二维图形数据时特别有用。

注意： 1）这里，要唯一定位一条直线。只需要知道三个数A、B、C即可。 不过，由于4x + 6y + 2 = 0、2x + 3y + 1 = 0其实是表示同一条直线。所以，要对A、B、C求最大公约数。 2）对于任意两点(X1,Y1)、(
X2,Y2)，都有 A = Y2 - Y1 B = X1 - X2 C = X2*Y1 - X1*Y2

```
    public int[] bestLine(int[][] points) {
        int max = 0;
        int maxHash = 0;
        int[] res = new int[2];
        HashMap<Integer,Integer> map = new HashMap<>();
        HashMap<Integer,int[]> record = new HashMap<>();
        for(int i = 0;i < points.length - 1;i++){
            for(int j = i + 1;j < points.length;j++){
                long A = points[j][1] - points[i][1];
                long B = points[i][0] - points[j][0];
                long C = ((long)points[j][0]) * points[i][1] - ((long)points[i][0]) * points[j][1];
                long gcd = gcd(gcd(A,B),C);
                A /= gcd;
                B /= gcd;
                C /= gcd;
                int hash = hash((int)A,(int)B,(int)C);
                int count = map.getOrDefault(hash,0) + 1;
                map.put(hash,count);
                if(count == 1) record.put(hash,new int[]{i,j});
                if(count > max){
                    max = count;
                    maxHash = hash;
                    res = record.get(hash);
                }else if(count == max){
                    int[] t1 = record.get(maxHash);
                    int[] t2 = record.get(hash);
                    if(t1[0] > t2[0] || t1[0] == t2[0] && t1[1] > t2[1]){
                        maxHash = hash;
                        res = t2;
                    }
                }
            }
        }
        return res;
    }

    private long gcd(long a,long b){
        return b == 0? a:gcd(b,a % b);
    }

    //随便写的hash方法。反正数据量也不大。冲突可能性小
    private int hash(int a,int b,int c){
        a = (a ^ (a >>> 16) & 0x0000ffff) << 20;
        b = (b ^ (b >>> 16) & 0x0000ffff) << 10;
        c = c ^ (c >>> 16) & 0x00000ffff;
        return a | b | c;
    }
```


