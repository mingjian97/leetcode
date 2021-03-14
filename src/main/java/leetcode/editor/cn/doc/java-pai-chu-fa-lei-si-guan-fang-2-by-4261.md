外层循环字符串列，内层循环元素。 if([j]>[j-1])将此元素设置为可以乱序，之后比较将可以忽略此元素。 如果此列被作废，将乱序设置切换回备份副本，再进入下一列。

代码

```java
public int minDeletionSize(String[] A) {
        char[][] chars = new char[A.length][A[0].length()];
        for (int i = 0; i < chars.length; i++) {
            chars[i] = A[i].toCharArray();
        }
        int[] lessAble = new int[chars.length];// 用来记录可以乱序的元素
        int ans = 0;

        outer:
        for (int i = 0; i < chars[0].length; i++) {// 循环字符串列
            boolean pass = true;
            int prev = chars[0][i];
            int[] copy = Arrays.copyOf(lessAble, lessAble.length);

            for (int j = 1; j < chars.length; j++) {// 循环元素
                int curr = chars[j][i];
                if (curr < prev) {
                    if (lessAble[j] != 1) {// 此列作废了
                        ans++;
                        lessAble = copy;// 将乱序设置回溯：切换为备份的lessAble
                        continue outer;
                    }
                    prev = curr;
                } else if (curr > prev) {// 比前面元素同列小
                    lessAble[j] = 1;// 后面的列可以乱序不用管了
                    prev = curr;
                } else {
                    if (lessAble[j] != 1) pass = false;// 相等且不允许乱序，必须深入下一列
                }
            }

            if (pass) return ans;// 一切顺利，直接返回结果
        }

        return ans;
    }
```
