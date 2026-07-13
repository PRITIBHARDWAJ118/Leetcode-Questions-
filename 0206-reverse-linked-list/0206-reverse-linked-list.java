/**
 * Definition for singly-linked list.
 * public class ListNode {
 *     int val;
 *     ListNode next;
 *     ListNode() {}
 *     ListNode(int val) { this.val = val; }
 *     ListNode(int val, ListNode next) { this.val = val; this.next = next; }
 * }
 */
class Solution {
   public ListNode reverseList(ListNode head) {

    ListNode prev = null;
    ListNode curr = head;

    while (curr != null) {

        ListNode next = curr.next; // Save next node

        curr.next = prev;          // Reverse link

        prev = curr;               // Move prev

        curr = next;               // Move curr
    }

    return prev;
}
}