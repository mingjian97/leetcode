//给定一个单链表 L：L0→L1→…→Ln-1→Ln ， 
//将其重新排列后变为： L0→Ln→L1→Ln-1→L2→Ln-2→… 
//
// 你不能只是单纯的改变节点内部的值，而是需要实际的进行节点交换。 
//
// 示例 1: 
//
// 给定链表 1->2->3->4, 重新排列为 1->4->2->3. 
//
// 示例 2: 
//
// 给定链表 1->2->3->4->5, 重新排列为 1->5->2->4->3. 
// Related Topics 链表 
// 👍 367 👎 0

package leetcode.editor.cn;

//java:重排链表
class P143ReorderList {
    public static void main(String[] args) {
        Solution solution = new P143ReorderList().new Solution();
    }

    /**
     * Definition for singly-linked list.
     */
    public class ListNode {
        int val;
        ListNode next;

        ListNode() {
        }

        ListNode(int val) {
            this.val = val;
        }

        ListNode(int val, ListNode next) {
            this.val = val;
            this.next = next;
        }
    }
    //leetcode submit region begin(Prohibit modification and deletion)

    class Solution {
        public void reorderList(ListNode head) {
            int length = 0;
            ListNode node = head;
            while (node != null) {//获取长度
                length++;
                node = node.next;
            }
            if(length<=1)return;
            node = head;
            int i = 1;
            while (i < length / 2) {//截取后半段
                node = node.next;
                i++;
            }
            ListNode second = node.next;
            node.next = null;
            //后半段reverse
            ListNode third = new ListNode();
            node = second;
            while (node != null) {
                ListNode tmp = node.next;
                node.next = third.next;
                third.next = node;
                node = tmp;
            }
            second = third.next;
            ListNode p = third;
            node = head;
            while (node != null && second != null) {
                ListNode tmp = node.next;
                p.next = node;
                p = p.next;
                node = tmp;
                tmp = second.next;
                p.next = second;
                p = p.next;
                second = tmp;
            }
            if (node != null) {
                p.next = node;
                p = p.next;
                p.next = null;
            } else if (second != null) {
                p.next = second;
                p = p.next;
                p.next = null;
            }
            head = third.next;


        }
    }
//leetcode submit region end(Prohibit modification and deletion)

}