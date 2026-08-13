class Solution {
    static class Node {
        char leftChar, rightChar;
        int prefixMax, suffixMax, maxLen;

        Node(char c) {
            this.leftChar = c;
            this.rightChar = c;
            this.prefixMax = 1;
            this.suffixMax = 1;
            this.maxLen = 1;
        }

        Node() {}
    }

    private Node[] tree;
    private char[] chars;

    private Node merge(Node left, Node right, int leftLen, int rightLen) {
        Node res = new Node();
        res.leftChar = left.leftChar;
        res.rightChar = right.rightChar;

        // Base prefix and suffix
        res.prefixMax = left.prefixMax;
        res.suffixMax = right.suffixMax;
        res.maxLen = Math.max(left.maxLen, right.maxLen);

        // Check if middle boundary characters match
        if (left.rightChar == right.leftChar) {
            res.maxLen = Math.max(res.maxLen, left.suffixMax + right.prefixMax);

            // Extend prefix if left child is entirely uniform
            if (left.prefixMax == leftLen) {
                res.prefixMax = leftLen + right.prefixMax;
            }
            // Extend suffix if right child is entirely uniform
            if (right.suffixMax == rightLen) {
                res.suffixMax = rightLen + left.suffixMax;
            }
        }

        return res;
    }

    private void build(int node, int start, int end) {
        if (start == end) {
            tree[node] = new Node(chars[start]);
            return;
        }
        int mid = start + (end - start) / 2;
        build(2 * node, start, mid);
        build(2 * node + 1, mid + 1, end);
        tree[node] = merge(tree[2 * node], tree[2 * node + 1], mid - start + 1, end - mid);
    }

    private void update(int node, int start, int end, int idx, char val) {
        if (start == end) {
            chars[idx] = val;
            tree[node] = new Node(val);
            return;
        }
        int mid = start + (end - start) / 2;
        if (idx <= mid) {
            update(2 * node, start, mid, idx, val);
        } else {
            update(2 * node + 1, mid + 1, end, idx, val);
        }
        tree[node] = merge(tree[2 * node], tree[2 * node + 1], mid - start + 1, end - mid);
    }

    public int[] longestRepeating(String s, String queryCharacters, int[] queryIndices) {
        int n = s.length();
        int k = queryIndices.length;
        chars = s.toCharArray();
        tree = new Node[4 * n];

        build(1, 0, n - 1);

        int[] ans = new int[k];
        for (int i = 0; i < k; i++) {
            update(1, 0, n - 1, queryIndices[i], queryCharacters.charAt(i));
            ans[i] = tree[1].maxLen;
        }

        return ans;
    }
}