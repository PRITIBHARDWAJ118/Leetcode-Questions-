class Solution {
    public ListNode swapNodes(ListNode head, int k) {

        ListNode fast = head;

        // Reach kth node
        for (int i = 1; i < k; i++) {
            fast = fast.next;
        }

        ListNode first = fast;   // kth from beginning
        ListNode slow = head;

        // Move both until fast reaches last node
        while (fast.next != null) {
            fast = fast.next;
            slow = slow.next;
        }

        // slow is kth from end
        int temp = first.val;
        first.val = slow.val;
        slow.val = temp;

        return head;
    }
}