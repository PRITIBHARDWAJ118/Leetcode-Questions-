import java.util.*;

class Solution {
    public List<Integer> remainingMethods(int n, int k, int[][] invocations) {
        // Build adjacency list for directed graph
        List<List<Integer>> graph = new ArrayList<>();
        for (int i = 0; i < n; i++) {
            graph.add(new ArrayList<>());
        }
        for (int[] inv : invocations) {
            graph.get(inv[0]).add(inv[1]);
        }

        // 1. Identify all suspicious methods starting from method k (BFS/DFS)
        boolean[] isSuspicious = new boolean[n];
        Queue<Integer> queue = new LinkedList<>();
        
        queue.offer(k);
        isSuspicious[k] = true;

        while (!queue.isEmpty()) {
            int current = queue.poll();
            for (int neighbor : graph.get(current)) {
                if (!isSuspicious[neighbor]) {
                    isSuspicious[neighbor] = true;
                    queue.offer(neighbor);
                }
            }
        }

        // 2. Check if any non-suspicious method invokes a suspicious method
        boolean cannotRemove = false;
        for (int[] inv : invocations) {
            int u = inv[0];
            int v = inv[1];
            if (!isSuspicious[u] && isSuspicious[v]) {
                cannotRemove = true;
                break;
            }
        }

        // 3. Construct the result list based on the check
        List<Integer> result = new ArrayList<>();
        for (int i = 0; i < n; i++) {
            if (cannotRemove || !isSuspicious[i]) {
                result.add(i);
            }
        }

        return result;
    }
}