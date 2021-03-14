//输入整数数组 arr ，找出其中最小的 k 个数。例如，输入4、5、1、6、2、7、3、8这8个数字，则最小的4个数字是1、2、3、4。 
//
// 
//
// 示例 1： 
//
// 输入：arr = [3,2,1], k = 2
//输出：[1,2] 或者 [2,1]
// 
//
// 示例 2： 
//
// 输入：arr = [0,1,2,1], k = 1
//输出：[0] 
//
// 
//
// 限制： 
//
// 
// 0 <= k <= arr.length <= 10000 
// 0 <= arr[i] <= 10000 
// 
// Related Topics 堆 分治算法 
// 👍 156 👎 0

package leetcode.editor.cn;

import java.util.Arrays;
import java.util.Random;

//java:最小的k个数
class P剑指Offer40ZuiXiaoDeKgeShuLcof {
    public static void main(String[] args) {
        Solution solution = new P剑指Offer40ZuiXiaoDeKgeShuLcof().new Solution();
    }

    //leetcode submit region begin(Prohibit modification and deletion)
    class Solution {
        //法二：快排
        public int[] getLeastNumbers(int[] arr, int k) {
            quickSort(arr, 0, arr.length - 1, k);
            return Arrays.copyOfRange(arr, 0, k);
        }

        public void quickSort(int[] arr, int l, int r, int k) {
            if (l >= r) return;
            int pos = partition(arr, l, r);
            int num = pos - l + 1;
            if (num == k) return;
            else if (num < k) {
                quickSort(arr, pos + 1, r, k - num);
            } else {
                quickSort(arr, l, pos - 1, k);
            }
        }

        public int partition(int[] arr, int l, int r) {
            int i = new Random().nextInt(r - l + 1) + l;
            int pivot = arr[i];
            swap(arr, l, i);
            i = l;
            while (l < r) {
                while (l < r && arr[r] >= pivot) {
                    r--;
                }
                while (l < r && arr[l] <= pivot) {
                    l++;
                }
                swap(arr, l, r);
            }
            swap(arr, l, i);
            return l;
        }

        public void swap(int[] arr, int i, int j) {
            int tmp = arr[i];
            arr[i] = arr[j];
            arr[j] = tmp;
        }


        //法一
//    public int[] getLeastNumbers(int[] arr, int k) {
//        Arrays.sort(arr);
//        return Arrays.copyOfRange(arr,0,k);
//    }
    }
//leetcode submit region end(Prohibit modification and deletion)

}