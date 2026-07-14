/*
// Definition for a Node.
class Node {
    public int val;
    public Node prev;
    public Node next;
    public Node child;
};
*/

class Solution {
    public Node flatten(Node head) {
      
    dfs(head);
    return head;
}

Node dfs(Node head) {
    Node curr = head;
    Node last = null;

    while (curr != null) {

        Node next = curr.next;

        if (curr.child != null) {

            Node childHead = curr.child;
            Node childTail = dfs(childHead);

            // Connect current with child
            curr.next = childHead;
            childHead.prev = curr;
            curr.child = null;

            // Connect child's tail with next
            if (next != null) {
                childTail.next = next;
                next.prev = childTail;
            }

            last = childTail;
            curr = childTail;
        } else {
            last = curr;
        }

        curr = curr.next;
    }

    return last;
}
    }
