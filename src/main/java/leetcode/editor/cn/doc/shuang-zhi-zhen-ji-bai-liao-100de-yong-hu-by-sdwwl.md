可以使用两种指针，一个从左边找最高山峰，一个从右边找最高山峰，最后判断找到的是不是同一个山峰
![image.png](https://pic.leetcode-cn.com/1604367864-BSFQoM-image.png)

```
    public boolean validMountainArray(int[] A) {
        int len = A.length;
        int left = 0;
        int right = len - 1;
        //从左边往右边找，一直找到山峰为止
        while (left + 1 < len && A[left] < A[left + 1])
            left++;
        //从右边往左边找，一直找到山峰为止
        while (right > 0 && A[right - 1] > A[right])
            right--;
        //判断从左边和从右边找的山峰是不是同一个
        return left > 0 && right < len - 1 && left == right;
    }
```

看下运行结果
![image.png](https://pic.leetcode-cn.com/1604367905-TnPBfN-image.png)



<br>

**如果觉得有用就给个赞吧，你的赞是给我最大的鼓励，也是我写作的最大动力**

>
> 查看更多答案，可扫码关注我微信公众号“**[数据结构和算法](https://img-blog.csdnimg.cn/20200807155236311.png)**”