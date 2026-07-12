/**
 * Definition for singly-linked list.
 * public class ListNode {
 *     int val;
 *     ListNode next;
 *     ListNode(int x) {
 *         val = x;
 *         next = null;
 *     }
 * }
 */
public class Solution {
    public ListNode getIntersectionNode(ListNode headA, ListNode headB) {
        int countA=0;
        int countB=0;
        ListNode temp=headA;
        ListNode temp1=headB;
        while(temp!=null){
            countA++;
            temp=temp.next;
        }
        while(temp1!=null){
            countB++;
            temp1=temp1.next;

        }
        int diff = Math.abs(countA - countB);
        temp=headA;
        temp1=headB;
        if(countA>=countB){
            for(int i=0;i<diff;i++){
               
                  temp=temp.next;
              
            }
        }
        else{
             for(int i=0;i<diff;i++){
                
                temp1=temp1.next;
            }
        }
        while (temp != null && temp1 != null) {
            if (temp == temp1) {
                return temp;
            }
            temp = temp.next;
            temp1 = temp1.next;
        }
        return null;
    }
}