### 解题思路

本题解 借鉴了 **官方题解** 的思路：
> 首先根据原字符串，得出一个lastIndex数组，用于存储 一个字母 的 最后一次出现下标
> 再次遍历该字符串，取出当前最大的 最后一个下标，
> 若该下标与当前正在遍历到的数组下标相等，则之前的所有元素，都仅在 i之前出现，可以记录结果

### 运行结果

![image.png](https://pic.leetcode-cn.com/1603331363-baDEHb-image.png)

### 代码

```java
class Solution {
    public List<Integer> partitionLabels(String S) {
        ArrayList<Integer> result = new ArrayList<>();
        if (S == null || S.length() <= 0) {
            return result;
        }

        /*
            填充 lastIndex数组
         */
        int[] lastIndex = new int[26]; // 存储 一个字母 的 最后一次出现下标
        Arrays.fill(lastIndex, -1);
        int length = S.length();
        for (int i = 0; i < length; i++) {
            lastIndex[S.charAt(i) - 'a'] = i;
        }

        /*
            计算 结果数组
         */
        int startIndex = 0;
        int endIndex = 0;
        for (int i = 0; i < length; i++) {
            endIndex = Math.max(endIndex, lastIndex[S.charAt(i) - 'a']);    // 取出当前最大的 最后一个下标
            if (i == endIndex) {    // 若 相等，则之前的所有元素，都仅在 i之前出现，可以记录结果
                result.add(endIndex - startIndex + 1);
                startIndex = endIndex + 1;
            }
        }

        return result;
    }
}
```

打卡第92天，加油！！！