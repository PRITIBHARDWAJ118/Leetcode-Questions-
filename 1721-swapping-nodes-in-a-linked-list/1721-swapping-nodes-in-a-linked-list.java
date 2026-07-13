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
    public ListNode swapNodes(ListNode head, int k) {
        ListNode temp1=head;
        ListNode temp2=head;
        int count=0;
        while(temp1!=null){
            count++;
            temp1=temp1.next;
        }
        temp1=head;
        for (int i=0;i<k-1;i++){
           temp1=temp1.next;}

        for (int i=0;i<(count-k);i++){
            temp2= temp2.next;
        }
        int temporary= temp1.val;
        temp1.val=temp2.val;
        temp2.val=temporary;
        return head;
    }
}