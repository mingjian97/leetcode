## 解题思路

利用哈希表统计出元素的频数分布，然后利用滑动窗口对元素进行计数，交换元素可以通过指针滑动以及元素数目的临时统计数count同Hash中的值进行对比来完成。

### 代码

```python3
class Solution:
    def maxRepOpt1(self, text: str) -> int:
        # 统计各个字母的频数
        Hash = {}
        for i in text:
            Hash[i] = Hash.get(i, 0) + 1

        # 计算长度
        res = 0 # 输出结果
        change, count = True, 0 # change为交换元素的标记变量，count为临时字符统计数
        j, k = 0, 0 # j，k为窗口起点和终点
        while k < len(text):
            if text[j] == text[k] and count < Hash[text[j]]:
                k += 1
                count += 1
            else:
                if change and Hash[text[j]] > count:
                    k += 1
                    count += 1
                    change = False
                else:
                    res = max(res, k-j)
                    tmp = text[j]
                    while j < len(text) and text[j] == tmp:
                        j += 1
                    k = j
                    count = 0
                    change = True
            if k == len(text):
                res = max(res, k-j)
        
        return res

```

### 复杂度分析

时间复杂度：O(N)，指针同向移动，while循环部分为O(N)
空间复杂度：O(K)，K为字符种类数