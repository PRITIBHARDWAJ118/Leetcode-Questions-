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
    public boolean isPalindrome(ListNode head) {
      ListNode slow=head;
      ListNode fast=head;
      while(fast!=null && fast.next!=null){
        slow=slow.next;
        fast=fast.next.next;
      }
      ListNode curr=slow;
      ListNode prev=null;
      while (curr != null) {

        ListNode next = curr.next; // Save next node

        curr.next = prev;          // Reverse link

        prev = curr;               // Move prev

        curr = next;               // Move curr
    }
    ListNode temp=head;
    while(prev!=null){
        if(temp.val!=prev.val){
            return false;
        }
        prev=prev.next;
        temp=temp.next;
    }
    return true;

      
        
    }
}